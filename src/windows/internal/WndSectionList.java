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
 * In diesen Dialog werden die einzelnen Geschäfte angezeigt.
 * 
 * @author René Majewski
 */
public class WndSectionList extends JInternalFrame implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -6848374575961659809L;
	
	/**
	 * Speichert das Popup-Menü
	 */
	private PopupCategoryList _popup;
	
	/**
	 * Speichert die Tabelle mit den Geschäften
	 */
	private JTable _table;
	
	/**
	 * Initalisiert den Dialog und die Tabelle. Anschließend wird der Dialog
	 * angezeigt.
	 */
	public WndSectionList() {
		// Dialog initalisieren
		super();
		
		// Größe
		setSize(600, 400);
		
		// Title
		setTitle("Geschäfte");
		
		// Eigenschaften des Fensters
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(false);
		
		// Tabelle initalisieren
		_table = new JTable(new IdNameListModel(DbController.queries().section().select()));
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Geschäft");
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
					String nc = JOptionPane.showInputDialog(this, "Neues Geschäft", "Geschäft erstellen", JOptionPane.OK_CANCEL_OPTION);
					if (nc != null) {
						if (stm.executeUpdate(DbController.queries().section().insert(nc)) > 0) {
							StatusBar.getInstance().setMessageAsOk("Neues Geschäft in der Datenbank gespeichert.");
						} else {
							StatusBar.getInstance().setMessageAsError("Das neue Geschäft '" + nc + "' konnte nicht hinzugefügt werden.");
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
						int d = JOptionPane.showConfirmDialog(this, "Soll das ausgewählte Geschäft '" + data.getName() +"'(" + data.getId() + ") wirklich gelöscht werden?", "Geschäft löschen", JOptionPane.YES_NO_OPTION);
						if (d == 0) {
							if (stm.executeUpdate(DbController.queries().section().delete(data.getId())) > 0) {
								StatusBar.getInstance().setMessageAsOk("Das Geschäft '" + data.getName() + "' (ID = " + data.getId() +") wurde gelöscht");
							} else {
								StatusBar.getInstance().setMessageAsError("Das Geschäft '" + data.getName() + "' konnte nicht gelöscht werden.");
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
						String cc = JOptionPane.showInputDialog(this, "Neuer Name", "Geschäft ändern", JOptionPane.OK_CANCEL_OPTION);
						if ((cc != null) && (cc.compareTo(data.getName()) != 0)) {
							if (stm.executeUpdate(DbController.queries().section().update(data.getId(), cc)) > 0) {
								StatusBar.getInstance().setMessageAsOk("Das Geschäft '" + data.getName() +"' wurde in '" + cc + "' geändert.");
							} else {
								StatusBar.getInstance().setMessageAsError("Der Name des Geschäftes '" + data.getName() + "' konnte nicht geändert werden.");
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
