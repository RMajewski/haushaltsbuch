package tables.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datas.CategoryData;
import db.DbController;

/**
 * Zeigt die einzelnen Kategorien an.
 * 
 * @author René Majewski
 */
public class CategoryListModel extends AbstractTableModel {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3545515413194132904L;
	
	/**
	 * Speichert die Daten
	 */
	private List<CategoryData> _datas;
	
	/**
	 * Ruft den Kontruktor der Vater-Klasse auf. Danach werden die Daten aus
	 * der Datenbank gelesen.
	 */
	public CategoryListModel() {
		super();
		
		_datas = new ArrayList<CategoryData>();
		
		dataRefresh();
		
	}

	/**
	 * Gibt die Anzahl der Spalten zurück.
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}

	/**
	 * Gibt die Anzahl der Zeilen zurück.
	 * 
	 * @return Anzahl der Zeilen
	 */
	@Override
	public int getRowCount() {
		return _datas.size();
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
		// ID anzeigen
		if (col == 0)
			return _datas.get(row).getId();
		
		// Namen anzeigen
		else
			return _datas.get(row).getName();
	}
	
	/**
	 * Gibt die Daten der angegebenen Zeile wieder.
	 * 
	 * @param row Zeile, von der die Daten zurück gegeben werden sollen.
	 * 
	 * @return Daten der angegeben Zeile
	 */
	public CategoryData getRowDataAt(int row) {
		if (row == -1)
			return new CategoryData();
		
		return _datas.get(row);
	}

	/**
	 * Liest die Daten aus der Datenbank neu ein
	 */
	public void dataRefresh() {
		// Liste mit Daten leeren
		_datas.clear();
		
		// Daten aus Datenbank lesen
		try {
			DbController db = DbController.getInstance();
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM category ORDER BY id ASC;");
			while(rs.next()) {
				_datas.add(new CategoryData(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			System.err.println("Fehler beim abrufen von Daten aus der Datenbank");
			e.printStackTrace();
		}
	}
}
