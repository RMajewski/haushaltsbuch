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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
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
