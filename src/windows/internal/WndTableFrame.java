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

package windows.internal;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import db.DbController;
import db.query.Query;
import elements.StatusBar;
import listener.PopupMenuMouseListener;
import menus.PopupStandardList;
import tables.models.DbModelInterface;
import tables.models.IdNameListModel;

/**
 * Erzeugt das Fenster mit einer Tabelle. Von diesem Fenster werden alle
 * Fenster abgeleitet, die Tabellen benutzen, um Daten anzuzeigen.
 * 
 * @author René Majewski
 */
public abstract class WndTableFrame extends WndInternalFrame implements ActionListener {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 5150752770244965004L;

	/**
	 * Tabelle, die die Daten anzeigen soll.
	 */
	protected JTable _table;
	
	/**
	 * PopupMenu, welches angezeigt werden soll
	 */
	protected JPopupMenu _popup;
	
	/**
	 * Im Konstruktor wird "nur" das Popup-Menü initalisiert.
	 */
	public WndTableFrame() {
		// Klasse initalisieren
		super();
		
		// Popup-Menü initalisieren
		_popup = new PopupStandardList(this);
	}
	
	/**
	 * Initalisiert das Fenster.
	 * 
	 * Es wird die Tabelle initalisiert und angezeigt. Zudem wird das
	 * Popup-Menü zum Anzeigen vorbereitet.
	 * 
	 * @param model Model, dass die Tabelle
	 */
	protected void initTable(AbstractTableModel model) {
		// Tabelle initalisieren
		_table = new JTable(model);
		_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_table.setRowSelectionAllowed(true);
		JScrollPane pane = new JScrollPane(_table);
		add(pane);
		
		// Popup-Menü initalisieren
		PopupMenuMouseListener listener = new PopupMenuMouseListener(_popup);
		pane.addMouseListener(listener);
		_table.addMouseListener(listener);
	}
	
	/**
	 * Gibt die Tabelle zurück.
	 * 
	 * @return Tabelle mit den Daten.
	 */
	public JTable getTable() {
		return _table;
	}
	
	/**
	 * Es wird der Benutzer gefragt, ob er den Datensatz wirklich löschen
	 * möchte. Drückt er ja, wird der Datensatz gelöscht. Wenn nicht, bleibt
	 * der Datensatz bestehen.
	 * 
	 * @param id ID des Datensatzes, der gelöscht werden soll.
	 * 
	 * @param query Die Abfragen-Klasse, die die SQL-Abfrage bereit stellt,
	 * mit der Datensatz gelöscht wird.
	 */
	protected void delete(int id, Query query) {
		// Überprüfen ob eine id übergeben wurde und ob eine Zeile in der
		// Tabelle selektiert wurde.
		if (id > -1) {
			try {
				// Vorbereitungen
				Statement stm = DbController.getInstance().createStatement();
				
				int d = JOptionPane.showConfirmDialog(this, "Soll der Datensatz mit der ID " + id + " aus der Tabelle '" + query.getTableName() + "' wirklich gelöscht werden?", "Datensatz löschen", JOptionPane.YES_NO_OPTION);
				if (d == 0) {
					if (stm.executeUpdate(query.delete(id)) > 0) {
						StatusBar.getInstance().setMessageAsOk(query.statusDeleteOk(id));
					} else {
						StatusBar.getInstance().setMessageAsError(query.statusDeleteError(id));
					}
					
					// Tabelle neu zeichnen
					((DbModelInterface)_table.getModel()).dataRefresh(true);
				}
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
				e.printStackTrace();
			}
		}
	}
}
