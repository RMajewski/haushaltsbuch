package tables.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datas.SectionData;
import db.DbController;

/**
 * FIXME SectionListModel und CategoryListModel zusammenfassen -> String für
 * SQL-Abfrage bei Konstruktor übergeben.
 */

/**
 * Zeigt die einzelnen Geschäfte an.
 * 
 * @author René Majewski
 * 
 * @deprecated
 */
public class SectionListModel extends AbstractTableModel {

	/**
	 * Setilisation ID
	 */
	private static final long serialVersionUID = -3026528956670640425L;
	
	/**
	 * Speichert eine Liste mit den Daten
	 */
	private List<SectionData> _list;
	
	/**
	 * Initalisiert das Model. Danach werden die Daten aus der Datenbank
	 * gelesen.
	 */
	public SectionListModel() {
		super();
		
		_list = new ArrayList<SectionData>();
		
		dataRefresh();
	}
	

	/**
	 * 
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return _list.size();
	}

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
	 * Liest die Daten aus der Datenbank neu ein.
	 */
	public void dataRefresh() {
		// Liste mit Daten leeren
		_list.clear();
		
		try {
			DbController db = DbController.getInstance();
			
			Statement stm = db.createStatement();
			ResultSet rs = stm.executeQuery(DbController.queries().section().select());
			while(rs.next()) {
				_list.add(new SectionData(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			System.err.println("Fehler beim abrufen von Daten aus der Datenbank");
			e.printStackTrace();
		}
	}

}
