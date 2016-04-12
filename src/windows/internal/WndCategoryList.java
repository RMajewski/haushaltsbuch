package windows.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import datas.IdNameData;
import db.DbController;
import elements.StatusBar;
import listener.PopupMenuMouseListener;
import menus.PopupCategoryList;
import tables.models.IdNameListModel;

/**
 * In diesen Dialog werden die einzelnen Kategorien angezeigt.
 * 
 * @author René Majewski
 */
public class WndCategoryList extends WndInternalFrame implements ActionListener {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3602076466416544711L;
	
	/**
	 * Speichert das Popup-Menü
	 */
	private PopupCategoryList _popup;
	
	/**
	 * Speichert die Tabelle mit den Kategorien.
	 */
	private JTable _table;
	
	/**
	 * Initalisiert den Dialog und die Tabelle. Anschließend wird der
	 * Dialog angezeigt.
	 */
	public WndCategoryList() {
		// Dialog initalieren
		super();
		
		// Titel
		setTitle("Kategorien");
		
		// Tabelle initalisieren
		_table = new JTable(new IdNameListModel(DbController.queries().category().select()));
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");
		_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_table.setRowSelectionAllowed(true);
		JScrollPane pane = new JScrollPane(_table);
		add(pane);
		
		// Popup-Menü initalisieren
		_popup = new PopupCategoryList(this);
		PopupMenuMouseListener listener = new PopupMenuMouseListener(_popup);
		pane.addMouseListener(listener);
		_table.addMouseListener(listener);
		
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
		IdNameListModel model = (IdNameListModel)_table.getModel();

		try {
			Statement stm = DbController.getInstance().createStatement();
			
			// Welcher Popup-Menü-Punkt wurde ausgewählt?
			switch (ae.getActionCommand()) {
				// Neu
				case PopupCategoryList.NEW:
					String nc = JOptionPane.showInputDialog(this, "Neue Kategorie", "Kategorie erstellen", JOptionPane.OK_CANCEL_OPTION);
					if (nc != null) {
						if (stm.executeUpdate(DbController.queries().category().insert(nc)) > 0) {
							StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusInsertOk());
						} else {
							StatusBar.getInstance().setMessageAsError(DbController.queries().category().statusInsertError());
						}

						// Tabelle neu zeichnen
						model.dataRefresh(true);
					}
					break;
					
				// Löschen
				case PopupCategoryList.DELETE:
					if (_table.getSelectedRow() >= 0) {
						// Daten ermitteln
						IdNameData data = model.getRowDataAt(_table.getSelectedRow());
						
						// Kategorie löschen? 
						int d = JOptionPane.showConfirmDialog(this, "Soll die ausgewählte Kategorie '" + data.getName() +"'(" + data.getId() + ") wirklich gelöscht werden?", "Kategorie löschen", JOptionPane.YES_NO_OPTION);
						if (d == 0) {
							if (stm.executeUpdate(DbController.queries().category().delete(data.getId())) > 0) {
								StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusDeleteOk(data.getId()));
							} else {
								StatusBar.getInstance().setMessageAsError(DbController.queries().category().statusDeleteError(data.getId()));
							}
							
							// Tabelle neu zeichnen
							model.dataRefresh(true);

						}
					}
					break;
					
				// Ändern
				case PopupCategoryList.CHANGE:
					if (_table.getSelectedRow() >= 0) {
						// Daten ermitteln
						IdNameData data = model.getRowDataAt(_table.getSelectedRow());
						
						// Kategorie ändern
						String cc = JOptionPane.showInputDialog(this, "Neuer Name", "Kategorie ändern", JOptionPane.OK_CANCEL_OPTION);
						if ((cc != null) && (cc.compareTo(data.getName()) != 0)) {
							if (stm.executeUpdate(DbController.queries().category().update(data.getId(), cc)) > 0) {
								StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusUpdateOk(data.getId()));
							} else {
								StatusBar.getInstance().setMessageAsError(DbController.queries().category().statusUpdateError(data.getId()));
							}

							// Tabelle neu zeichnen
							model.dataRefresh(true);
						}
					}
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.printStackTrace();
		}
	}
}
