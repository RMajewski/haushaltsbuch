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

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.helper.HelperNumbersOut;

/**
 * Gibt die Daten für die Tabelle 'money' aus.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 */
public class MoneyListModel extends AbstractTableModel 
							implements DbModelInterface {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -2551193734532013738L;
	
	/**
	 * Speichert die Liste mit den Datensätzen
	 */
	private List<MoneyData> _list;
	
	/**
	 * Initalisiert das Model
	 */
	public MoneyListModel() {
		// Klasse initaliseren
		super();
		
		// Tabelle vorbereiten
		_list = new ArrayList<MoneyData>();
		
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
		return DbController.queries().money().getCloumnCount() + 1;
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
				
			// Datum
			case 1:
				return _list.get(row).getDateAsString();
				
			// Einnahme oder Ausgabe
			case 2:
				return _list.get(row).getInOutAsString();
				
			// Betrag
			case 3:
				double ret = 0;
				// Summe aus der Datenbank lesen
				try {
					Statement stm = DbController.getInstance().createStatement();
					ResultSet rs = stm.executeQuery(DbController.queries().moneyDetails().sumMoneyId(_list.get(row).getId()));
					ret = rs.getDouble(1);
					rs.close();
				} catch (SQLException e) {
					StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
					e.printStackTrace();
				}
				return HelperNumbersOut.sum(ret);
			
			// Beschreibung
			case 4:
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
			ResultSet rs = stmt.executeQuery(DbController.queries().money().select());
			while(rs.next()) {
				_list.add(new MoneyData(rs.getInt("id"), rs.getLong("date"), rs.getBoolean("inout"), rs.getString("comment")));
			}
			rs.close();
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError("Fehler beim abrufen von Daten aus der Datenbank");
			e.printStackTrace();
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
	public MoneyData getRowDataAt(int row) {
		if (row > -1)
			return _list.get(row);
		
		return new MoneyData();
	}

}
