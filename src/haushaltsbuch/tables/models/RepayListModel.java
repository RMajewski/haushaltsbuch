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

import haushaltsbuch.datas.RepayData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;

/**
 * Gibt die Daten für die Rückzahlungen aus.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class RepayListModel extends AbstractTableModel 
		implements DbModelInterface {

	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die Liste mit den Datensätzen
	 */
	private List<RepayData> _list;
	
	/**
	 * Initialisiert die Daten
	 */
	public RepayListModel() {
		_list = new ArrayList<RepayData>();
		
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
		return DbController.queries().repay().getCloumnCount() + 2;
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
		try {
			switch (col) {
				case 0:
					return _list.get(row).getId();
					
				case 1:
					return _list.get(row).getDetailsId();
					
				case 2:
					return _list.get(row).getOutstandingId();
					
				case 3:
					Statement stm = DbController.getInstance().createStatement();
					ResultSet rs = stm.executeQuery(DbController.queries()
							.moneyDetails().getDate(
									_list.get(row).getDetailsId()));
					String date = HelperCalendar.dateToString(
							rs.getLong("date"));
					rs.close();
					return date;
					
				case 4:
					stm = DbController.getInstance().createStatement();
					rs = stm.executeQuery(DbController.queries()
							.moneyDetails().getMoney(
									_list.get(row).getDetailsId()));
					double money = rs.getLong("money");
					rs.close();
					return money;
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(
					DbController.statusDbError(), e);
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
		_list.clear();
		
		// Daten aus Datenbank lesen
		try {
			DbController db = DbController.getInstance();
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(DbController.queries().repay()
					.select());
			while(rs.next()) {
				_list.add(new RepayData(rs.getInt("id"), 
						rs.getInt("money_detailsid"), 
						rs.getInt("outstandingid")));
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
	public RepayData getRowDataAt(int row) {
		if (row > -1)
			return _list.get(row);
		
		return new RepayData();
	}

}
