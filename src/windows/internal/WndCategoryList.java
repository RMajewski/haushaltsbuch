package windows.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JInternalFrame;
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
public class WndCategoryList extends JInternalFrame implements ActionListener {

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
		
		// Größe
		setSize(600, 400);
		
		// Titel
		setTitle("Kategorien");
		
		// Eigenschaften des Fensters
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(false);
		
		// Tabelle initalisieren
		_table = new JTable(new IdNameListModel(DbController.queries().category().select()));
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");
		_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_table.setRowSelectionAllowed(true);
		add(new JScrollPane(_table));
		
		// Popup-Menü initalisieren
		_popup = new PopupCategoryList(this);
		_table.addMouseListener(new PopupMenuMouseListener(_popup));
		
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
							StatusBar.getInstance().setMessageAsOk("Neue Kategory in der Datenbank gespeichert.");
						} else {
							StatusBar.getInstance().setMessageAsError("Die Neue Kategorie '" + nc + "' konnte nicht hinzugefügt werden.");
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
								StatusBar.getInstance().setMessageAsOk("Die Kategorie '" + data.getName() + "' (ID = " + data.getId() +") wurde gelöscht");
							} else {
								StatusBar.getInstance().setMessageAsError("Die Kategory '" + data.getName() + "' konnte nicht gelöscht werden.");
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
								StatusBar.getInstance().setMessageAsOk("Die Kategorie '" + data.getName() +"' wurde in '" + cc + "' geändert.");
							} else {
								StatusBar.getInstance().setMessageAsError("Der Name der Kategorie '" + data.getName() + "' konnte nicht geändert werden.");
							}

							// Tabelle neu zeichnen
							model.dataRefresh(true);
						}
					}
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError("Fehler beim Zugriff auf die Datenbank.");
			e.printStackTrace();
		}
	}
}
