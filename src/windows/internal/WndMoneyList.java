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

import datas.MoneyData;
import db.DbController;
import elements.StatusBar;
import listener.PopupMenuMouseListener;
import menus.PopupCategoryList;
import tables.models.MoneyListModel;

/**
 * In diesem Fenster werden die einzelnen Money-Datensätze angezeigt.
 * 
 * @author René Majewski
 */
public class WndMoneyList extends JInternalFrame implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die Tabelle
	 */
	private JTable _table;

	/**
	 * Initalisiert das Fenster
	 */
	public WndMoneyList() {
		// Fenster initalisieren
		super();
		
		// Größe
		setSize(600, 400);

		// Titel
		setTitle("Einnahmen und Ausgaben");
		
		// Eigenschaften des Fensters
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(false);
		
		// Tabelle initalisieren
		_table = new JTable(new MoneyListModel());
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Datum");
		_table.getColumnModel().getColumn(2).setHeaderValue("Was?");
		_table.getColumnModel().getColumn(3).setHeaderValue("Beschreibung");
		_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_table.setRowSelectionAllowed(true);
		
		
		// Tabelle in Scroll-Bereich anzeigen
		JScrollPane scroll = new JScrollPane(_table);
		add(scroll);
		
		// Popup-Menü initalisieren
		PopupCategoryList _popup = new PopupCategoryList(this);
		PopupMenuMouseListener listener = new PopupMenuMouseListener(_popup);
		scroll.addMouseListener(listener);
		_table.addMouseListener(listener);
		
		// Fenster anzeigen
		pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die einzelnen Einträge im Popup-Menü
	 * 
	 * param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Welcher Menü-Eintrag wurde gedrückt?
		switch (ae.getActionCommand()) {
			// Neuen Eintrag erstellen
			case PopupCategoryList.NEW:
				WndMoneyChange wnd = new WndMoneyChange(null, this);
				getDesktopPane().add(wnd);
				wnd.moveToFront();
				try {
					wnd.setSelected(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			// Einen Eintrag ändern
			case PopupCategoryList.CHANGE:
				// Wurde ein Datensatz ausgewählt?
				if (_table.getSelectedRow() > -1) {
					// Datensatz ermitteln
					MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
					
					// Fenster zum ändern der Daten anzeigen
					WndMoneyChange w = new WndMoneyChange(data, this);
					getDesktopPane().add(w);
					w.moveToFront();
					try {
						w.setSelected(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			// Einen Eintrag löschen
			case PopupCategoryList.DELETE:
				// Wurde ein Datensatz ausgewählt?
				if (_table.getSelectedRow() > -1) {
					// Datensatz ermitteln
					MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
					
					// Benutzer Fragen ob Datensatz gelöscht werden soll
					// Kategorie löschen? 
					int d = JOptionPane.showConfirmDialog(this, "Soll der Datensatz mit der ID" + data.getId() + " wirklich gelöscht werden?", "Datensatz löschen", JOptionPane.YES_NO_OPTION);
					if (d == 0) {
						try {
							Statement stm = DbController.getInstance().createStatement();
							if (stm.executeUpdate(DbController.queries().money().delete(data.getId())) > 0) {
								StatusBar.getInstance().setMessageAsOk("Der Datensatz mit der ID " + data.getId() + " wurde aus der Datenbank-Tabelle 'money' gelöscht");
							} else {
								StatusBar.getInstance().setMessageAsError("Der Datensatz mit der ID " + data.getId() + " konnte nicht aus der Datenbank-Tabelle 'money' gelöscht werden.");
							}
						} catch (SQLException e) {
							StatusBar.getInstance().setMessageAsError("Fehler beim Zugriff auf die Datenbank.");
							e.printStackTrace();
						}
						
						// Tabelle neu zeichnen
						((MoneyListModel)_table.getModel()).dataRefresh(true);
					}
				}
				break;
		}
	}
	
	/**
	 * Gibt die Tabelle zurück.
	 * 
	 * @return Tabelle
	 */
	public JTable getTable() {
		return _table;
	}
}
