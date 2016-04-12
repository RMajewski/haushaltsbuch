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

package tables.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datas.MoneyDetailsData;
import db.DbController;
import elements.StatusBar;

public class MoneyDetailsListModel extends AbstractTableModel 
								   implements DbModelInterface {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -1514193705980956810L;
	
	/**
	 * Speichert die Liste der Daten
	 */
	private List<MoneyDetailsData> _list;
	
	/**
	 * Speichert die ID vom Money-Datensatz
	 */
	private int _moneyId;
	
	/**
	 * Initalisiert das Model
	 * 
	 * @param money ID des dazugehörigen Money-Datensatzes
	 */
	public MoneyDetailsListModel(int money) {
		super();
		
		_list = new ArrayList<MoneyDetailsData>();
		_moneyId = money;
		
		dataRefresh(false);
	}

	/**
	 * Gibt die Anzahl der Spalten zurück.
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return DbController.queries().moneyDetails().getCloumnCount();
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
		// Welche Spalte?
		switch(col) {
			// ID
			case 0:
				return _list.get(row).getId();
				
			// MoneyID
			case 1:
				return _list.get(row).getMoneyId();
				
			// Kategorie
			case 2:
				return _list.get(row).getCategoryId();
				
			// Geschäft
			case 3:
				return _list.get(row).getSectionId();
				
			// Betrag
			case 4:
				return _list.get(row).getMoney();
				
			// Beschreibung
			case 5:
				return _list.get(row).getComment();
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
	 * @see tables.models.DbModelInterface
	 */
	@Override
	public void dataRefresh(boolean repaint) {
		// Liste mit Daten leeren
		_list.clear();
		
		// Daten aus Datenbank lesen
		try {
			DbController db = DbController.getInstance();
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(DbController.queries().moneyDetails().select(_moneyId));
			while(rs.next()) {
				_list.add(new MoneyDetailsData(
							rs.getInt("id"),
							rs.getInt("moneyid"),
							rs.getInt("categoryid"),
							rs.getInt("sectionid"),
							rs.getDouble("money"),
							rs.getString("comment") ));
			}
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
	 * @param row Zeile, die als Datensatz zurück gegeben werden soll.
	 * 
	 * @return Datensatz der angegeben Zeile
	 */
	@Override
	public MoneyDetailsData getRowDataAt(int row) {
		if (row == -1)
			return new MoneyDetailsData();
		else
			return _list.get(row);
	}

}