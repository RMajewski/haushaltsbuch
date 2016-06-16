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

import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.helper.HelperNumbersOut;

/**
 * Gibt die Daten für die Tabelle 'outstanding' aus.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class OutstandingListModel extends AbstractTableModel 
		implements DbModelInterface {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die Liste mit den Datensätzen
	 */
	private List<OutstandingData> _list;
	
	/**
	 * Initialisiert das Model
	 */
	public OutstandingListModel() {
		// Klasse initaliseren
		super();
		
		// Tabelle vorbereiten
		_list = new ArrayList<OutstandingData>();
		
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
		return DbController.queries().outstanding().getCloumnCount();
	}

	/**
	 * Gibt die Anzahl der Zeilen zurück.
	 * 
	 * @return Anzahl der Zeilen
	 */
	@Override
	public int getRowCount() {
		return _list.size();
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
		// Welche Spalte soll ausgegeben werden?
		switch (col) {
			// ID
			case 0:
				return _list.get(row).getId();
				
			// Name des Geschäftes
			case 1:
				return searchName(DbController.queries().section().search("id", 
						_list.get(row).getSectionId()));
				
			// Höhe der Schulden
			case 2:
				return HelperNumbersOut.sum(_list.get(row).getMoney());
				
			// Anzahl der Monate
			case 3:
				return _list.get(row).getMonths();
				
			// Datum der 1. Rate
			case 4:
				return HelperCalendar.dateToString(_list.get(row).getStart());
				
			// Höhe der monatlichen Rate
			case 5:
				return HelperNumbersOut.sum(_list.get(row).getMonthMoney());
				
			// Kommentar
			case 6:
				return _list.get(row).getComment();
		}
		
		// Standart-Rückgabe-Wert
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
		_list.clear();
		
		// Daten aus Datenbank lesen
		try {
			DbController db = DbController.getInstance();
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(DbController.queries().outstanding().select());
			while(rs.next()) {
				_list.add(new OutstandingData(rs.getInt("id"), 
						rs.getInt("sectionid"), rs.getDouble("money"),
						rs.getInt("months"), rs.getLong("start"), 
						rs.getDouble("monthmoney"), rs.getString("comment")));
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
	public OutstandingData getRowDataAt(int row) {
		if (row > -1)
			return _list.get(row);
		
		return new OutstandingData();
	}
	
	/**
	 * Stellt die Abfrage an die Datenbank und gibt das Ergebenis zurück.
	 * 
	 * @param sql SQL-Abfrage, die gestellt werden soll.
	 * 
	 * @return Der zu suchende Name
	 */
	private String searchName(String sql) {
		String ret = new String();
		try {
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.getString("name") != null && !rs.getString("name").isEmpty())
				ret = rs.getString("name");
			rs.close();
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(
					DbController.statusDbError(), e);
		}
		return ret;
	}

}
