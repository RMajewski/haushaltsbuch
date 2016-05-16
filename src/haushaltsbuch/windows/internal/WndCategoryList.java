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

package haushaltsbuch.windows.internal;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.datas.IdNameData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.menus.PopupStandardList;
import haushaltsbuch.tables.models.IdNameListModel;

/**
 * In diesen Dialog werden die einzelnen Kategorien angezeigt.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndCategoryList extends WndTableFrame {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3602076466416544711L;
	
	/**
	 * Speichert den Fenster-Titel
	 */
	public static final String WND_TITLE = "Kategorien";
	
	/**
	 * Initalisiert den Dialog und die Tabelle. Anschließend wird der
	 * Dialog angezeigt.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public WndCategoryList(Desktop desktop) {
		// Dialog initalieren
		super(desktop);
		
		// Tabelle initalisieren
		IdNameListModel model = new IdNameListModel(DbController.queries().category().select());
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");

		// Sorter initalisieren
		TableRowSorter<IdNameListModel> sorter = 
				new TableRowSorter<IdNameListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);

		// Titel
		setTitle(WND_TITLE);
		
		// Anzeigen
		pack();
		setVisible(true);
	}
	
	/**
	 * Reagiert auf die einzelnen Einträge im PopupMenu.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Welcher Popup-Menü-Punkt wurde ausgewählt?
		switch (ae.getActionCommand()) {
			// Neu
			case PopupStandardList.NEW:
				insert();
				break;
				
			// Löschen
			case PopupStandardList.DELETE:
				delete();
				break;
				
			// Ändern
			case PopupStandardList.CHANGE:
				tableRowDoubleClick();
				break;
		}
	}

	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() >= 0) {
			try {
				// Daten ermitteln
				IdNameData data = 
						((IdNameListModel)_table.getModel()).getRowDataAt(
								_table.getRowSorter().convertRowIndexToModel(
										_table.getSelectedRow()));
				
				// Kategorie ändern
				String cc = JOptionPane.showInputDialog(this, "Neuer Name", "Kategorie ändern", JOptionPane.OK_CANCEL_OPTION);
				if ((cc != null) && !cc.isEmpty() && (cc.compareTo(data.getName()) != 0)) {
					Statement stm = DbController.getInstance().createStatement();
					if (stm.executeUpdate(DbController.queries().category().update(data.getId(), cc)) > 0) {
						StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusUpdateOk(data.getId()));
					} else {
						StatusBar.getInstance().setMessageAsError(DbController.queries().category().statusUpdateError(data.getId()));
					}
	
					// Tabelle neu zeichnen
					((IdNameListModel)_table.getModel()).dataRefresh(true);
				}
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Wird aufgerufen, wenn ein neuer Datensatz eingefügt werden soll.
	 */
	@Override
	public void insert() {
		IdNameListModel model = (IdNameListModel)_table.getModel();

		try {
			Statement stm = DbController.getInstance().createStatement();
				
			String nc = JOptionPane.showInputDialog(this, "Neue Kategorie", "Kategorie erstellen", JOptionPane.OK_CANCEL_OPTION);
			if (nc != null && !nc.isEmpty()) {
				ResultSet rs = stm.executeQuery(DbController.queries().category().search("name", nc));
				if (!rs.next()) {
					if (stm.executeUpdate(DbController.queries().category().insert(nc)) > 0) {
						StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusInsertOk());
					} else {
						StatusBar.getInstance().setMessageAsError(DbController.queries().category().statusInsertError());
					}
				} else {
					StatusBar.getInstance().setMessage("Kategorie schon verhanden");
				}
	
				// Tabelle neu zeichnen
				model.dataRefresh(true);
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.printStackTrace();
		}
	}

	/**
	 * Wird aufgerufen, wenn ein Datensatz geändert werden soll.
	 */
	@Override
	public void change() {
		tableRowDoubleClick();
	}

	/**
	 * Wird aufgerufen, wenn ein Datensatz gelöscht werden soll.
	 */
	@Override
	public void delete() {
		IdNameListModel model = (IdNameListModel)_table.getModel();
		
		System.out.println("WndCategoryList.delete() wurde aufgerufen"); 

		if (_table.getSelectedRow() > -1)
			delete(model.getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow())).getId(), 
					DbController.queries().category());
	}
}
