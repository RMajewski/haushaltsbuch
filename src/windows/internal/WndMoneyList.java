package windows.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import datas.MoneyData;
import db.DbController;
import elements.StatusBar;
import listener.PopupMenuMouseListener;
import menus.PopupMoneyList;
import tables.models.MoneyListModel;

/**
 * In diesem Fenster werden die einzelnen Money-Datensätze angezeigt.
 * 
 * @author René Majewski
 */
public class WndMoneyList extends WndInternalFrame implements ActionListener {

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

		// Titel
		setTitle("Einnahmen und Ausgaben");
		
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
		PopupMoneyList popup = new PopupMoneyList(this);
		PopupMenuMouseListener listener = new PopupMenuMouseListener(popup);
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
			case PopupMoneyList.NEW:
				newWindow(new WndMoneyChange(null, this));
				break;
				
			// Einen Eintrag ändern
			case PopupMoneyList.CHANGE:
				// Wurde ein Datensatz ausgewählt?
				if (_table.getSelectedRow() > -1) {
					// Datensatz ermitteln
					MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
					
					// Fenster zum ändern der Daten anzeigen
					newWindow(new WndMoneyChange(data, this));
				}
				break;
				
			// Einen Eintrag löschen
			case PopupMoneyList.DELETE:
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
								StatusBar.getInstance().setMessageAsOk(DbController.queries().money().statusDeleteOk(data.getId()));
							} else {
								StatusBar.getInstance().setMessageAsError(DbController.queries().money().statusDeleteError(data.getId()));
							}
						} catch (SQLException e) {
							StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
							e.printStackTrace();
						}
						
						// Tabelle neu zeichnen
						((MoneyListModel)_table.getModel()).dataRefresh(true);
					}
				}
				break;
				
			// Details anzeigen
			case PopupMoneyList.DETAILS:
				MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(_table.getSelectedRow());
				newWindow(new WndMoneyDetailsList(data));
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
