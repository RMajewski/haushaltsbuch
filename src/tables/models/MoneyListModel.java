package tables.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datas.Data;
import datas.MoneyData;
import db.DbController;
import elements.StatusBar;

/**
 * Gibt die Daten der Liste für die Tabelle 'money' aus.
 * 
 * @author René Majewski
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
		return DbController.queries().money().getCloumnCount();
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
				// TODO Einnahme oder Ausgabe hin schreiben.
				return _list.get(row).getInOut();
				
			// Beschreibung
			case 3:
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
			ResultSet rs = stmt.executeQuery(DbController.queries().money().select());
			while(rs.next()) {
				_list.add(new MoneyData(rs.getInt("id"), rs.getLong("date"), rs.getBoolean("inout"), rs.getString("comment")));
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
	public MoneyData getRowDataAt(int row) {
		if (row == -1)
			return new MoneyData();
		else
			return _list.get(row);
	}

}
