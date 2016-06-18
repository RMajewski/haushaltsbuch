/* 
* Copyright 2016 René Majewski
*  
* Lizenziert unter der EUPL, Version 1.1 oder - sobald diese von der
* Europäischen Kommission genehmigt wurden - Folgeversionen der EUPL
* ("Lizenz"); Sie dürfen dieses Werk ausschließlich gemäß dieser Lizenz
* nutzen. 
* 
* Eine Kopie der Lizenz finden Sie hier: 
* https://joinup.ec.europa.eu/software/page/eupl
*  
* Sofern nicht durch anwendbare Rechtsvorschriften gefordert oder in 
* schriftlicher Form vereinbart, wird die unter der Lizenz verbreitete 
* Software "so wie sie ist", OHNE JEGLICHE GEWÄHRLEISTUNG ODER BEDINGUNGEN -
* ausdrücklich oder stillschweigend - verbreitet.
* Die sprachspezifischen Genehmigungen und Beschränkungen unter der Lizenz
* sind dem Lizenztext zu entnehmen.
*/ 

package haushaltsbuch.tables.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.testsuite.helper.HelperCalendar;

import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.datas.RepayData;
import haushaltsbuch.datas.RepaySearchData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;

/**
 * Gibt die Daten für die gefunden Datensätze für die Rückzahlungen aus.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class RepaySearchListModel extends AbstractTableModel 
		implements DbModelInterface {
	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Speichert die Liste mit den Datensätzen der bereits gespeicherten
	 * Rückzahlungen.
	 */
	private List<RepayData> _repay;
	
	/**
	 * Speichert die Liste mit den gefundenen Datensätzen, die noch nicht in der
	 * Liste der Rückzahlungen gespeichert sind.
	 */
	private List<RepaySearchData> _search;
	
	/**
	 * Speichert den Datensatz für die Schulden
	 */
	private OutstandingData _data;
	
	/**
	 * Initialisiert das Model
	 */
	public RepaySearchListModel(OutstandingData data) {
		// Klasse initaliseren
		super();
		
		_data = data;
		
		// Tabellen vorbereiten
		_repay = new ArrayList<RepayData>();
		_search = new ArrayList<RepaySearchData>();
		
		// Daten initalisieren
		dataRefresh(false);
	}

	/**
	 * Gibt die Anzahl der Spalten zurück.
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return RepaySearchData.COLUMNS;
	}

	/**
	 * Gibt die Anzahl der Zeilen zurück.
	 * 
	 * @return Anzahl der Zeilen
	 */
	@Override
	public int getRowCount() {
		return _search.size();
	}

	/**
	 * Gibt den Wert für die angegebene Zelle wieder.
	 * 
	 * @param row Zeile, in der die Zelle liegt
	 * 
	 * @param col Spalte, in der die Zelle liegt
	 * 
	 * @return Wert der Zelle
	 */
	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
			case 0:
				return _search.get(row).getTake();
				
			case 1:
				return _search.get(row).getDetailsId();
				
			case 2:
				return -1;
				
			case 3:
				return _search.get(row).getDate();
				
			case 4:
				return _search.get(row).getMoney();
				
			case 5:
				return _search.get(row).getComment();
		}
		return null;
	}

	/**
	 * Ermittelt die Daten aus der Datenbank und speichert diese in die Liste.
	 * 
	 * @param repaint Wird <b>true</b> übergeben, so wird die zu Grunde
	 * liegende Tabelle angewiesen, dass neu gezeichnet werden muss. Bei
	 * <b>false</b> wird dies nicht ausgelöst.
	 * 
	 * @see haushaltsbuch.tables.models.DbModelInterface
	 */
	@Override
	public void dataRefresh(boolean repaint) {
		// Liste mit Daten leeren
		_repay.clear();
		_search.clear();
		
		// Daten aus Datenbank lesen
		try {
			DbController db = DbController.getInstance();
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(DbController.queries().repay()
					.select());
			while(rs.next()) {
				_repay.add(new RepayData(rs.getInt("id"), 
						rs.getInt("money_detailsid"),
						rs.getInt("outstandingid")));
			}
			rs = stmt.executeQuery(DbController.queries().moneyDetails()
					.searchPay(_data.getSectionId()));
			while(rs.next()) {
				boolean insert = true;
				for (int i = 0; i < _repay.size(); i++)
					if (_repay.get(i).getDetailsId() == rs.getInt("id")) {
						insert = false;
						break;
					}
				
				if (insert) {
					_search.add(new RepaySearchData(-1, rs.getInt("id"), -1, 
							HelperCalendar.dateToString(rs.getLong("date")),
							rs.getDouble("money"),
							rs.getString("comment")));
				}
			}
			rs.close();
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError("Fehler beim abrufen von Daten aus der Datenbank", e);
		}
		
		// Überprüfen ob die Tabelle neugezeichnet werden soll
		if (repaint)
			fireTableDataChanged();
	}

	/**
	 * Gibt den Datensatz der angegeben Zeile zurück. Wurde <b>-1</b> als ID
	 * übergeben, so wird ein leerer Datensatz zurück gegeben.
	 * 
	 * @param row Zeile, in der der Datensatz steht.
	 * 
	 * @return Selektierter Datensatz
	 */
	@Override
	public RepaySearchData getRowDataAt(int row) {
		if (row > -1)
			return _search.get(row);
		
		return new RepaySearchData();
	}
	
	/**
	 * Gibt die Klasse für die entsprechende Spalte aus.
	 * 
	 * @param column Index der Spalte.
	 * 
	 * @return Klasse für die angegebene Spalte.
	 */
	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 0)
			return Boolean.class;
		
		return super.getColumnClass(column);
	}
	
	/**
	 * Die Spalte 0 (mit Check-Boxen) soll editierbar sein.
	 * 
	 * @param row Zeile, in der sich die Zelle befindet.
	 * 
	 * @param col Spalte, in der sich die Zelle befindet.
	 * 
	 * @return Ist die Zelle editierbar?
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 0;
	}
	
	/**
	 * Speichert den übergebenen Wert im entsprechenden Datensatz. Wenn der Wert
	 * geändert wurde, so wird der Tabelle mitgeteilt, welche Zelle sich
	 * geändert hat.
	 * 
	 * @param value Wert der gespeichert werden soll.
	 * 
	 * @param row Zeile, in der die Zelle sich befindet.
	 * 
	 * @param col Spalte, in der die die Zelle sich befindet.
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {
		if (col == 0) {
			_search.get(row).setTake(((Boolean)value).booleanValue());
			fireTableCellUpdated(row, col);
		}
	}
	
	/**
	 * Gibt die ID vom Schulden-Datensatz zurück.
	 * 
	 * @return ID des Schulden-Datensatzes.
	 */
	public int getOutstandingId() {
		return _data.getId();
	}
}
