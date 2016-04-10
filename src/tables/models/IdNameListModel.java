package tables.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datas.IdNameData;
import db.DbController;
import elements.StatusBar;

public class IdNameListModel extends AbstractTableModel
							 implements DbModelInterface{

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 8370733487854077309L;
	
	/**
	 * Speichert die SQL-Abfrage
	 */
	private String _sql;
	
	/**
	 * Speichert die List mit den Daten
	 */
	private List<IdNameData> _list;
	
	/**
	 * Initalisiert das Model
	 * 
	 * @param sql SQL-Abfrage, die genutzt werden soll, ob die Daten
	 * abzufragen.
	 */
	public IdNameListModel(String sql) {
		// Klasse initalisieren
		super();
		
		// Liste initalisieren
		_list = new ArrayList<IdNameData>();
		
		// SQL-Befehl speichern
		_sql = sql;
		
		// Daten abrufen
		dataRefresh(false);
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
			ResultSet rs = stmt.executeQuery(_sql);
			while(rs.next()) {
				_list.add(new IdNameData(rs.getInt("id"), rs.getString("name")));
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
	 * Liefert die Anzahl der Spalten.
	 * 
	 * @return Anzahl der Spalten.
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}

	/**
	 * Liefert die Anzahl der Zeilen.
	 * 
	 * @return Anzahl der Zeilen.
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
		// ID anzeigen
		if (col == 0)
			return _list.get(row).getId();
		
		// Namen anzeigen
		else
			return _list.get(row).getName();
	}
	
	/**
	 * Gibt die Daten der angegebenen Zeile wieder.
	 * 
	 * @param row Zeile, von der die Daten zurück gegeben werden sollen.
	 * 
	 * @return Daten der angegeben Zeile
	 */
	public IdNameData getRowDataAt(int row) {
		if (row == -1)
			return new IdNameData();
		
		return _list.get(row);
	}

}
