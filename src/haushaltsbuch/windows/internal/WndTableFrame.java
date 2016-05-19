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

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Query;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.events.ToolBarDbElementEvent;
import haushaltsbuch.events.ToolBarDbElementEventMulticaster;
import haushaltsbuch.listener.PopupMenuMouseListener;
import haushaltsbuch.listener.ToolBarDbElementListener;
import haushaltsbuch.menus.PopupStandardList;
import haushaltsbuch.tables.models.DbModelInterface;

/**
 * Erzeugt das Fenster mit einer Tabelle. Von diesem Fenster werden alle
 * Fenster abgeleitet, die Tabellen benutzen, um Daten anzuzeigen.
 * 
 * In der Version 0.2 wird eine Cast-Klasse implementiert, die die Listener
 * verwaltet, an die das Ereignis ToolBarDbElementEvent gesendet werden soll.
 * Es werden auch noch abstrakte Methoden hinzugefügt, die das Fenster zum
 * eingeben eines neues Datensatzes, zum ändern eines Datensatzes und zum
 * löschen Datensatzes öffnen.
 * 
 * In der Version 0.3 übernimmt die Toolbar das Enabled und Disablen der
 * Popup-Menü-Einträge.
 * 
 * @author René Majewski
 * 
 * @version 0.3
 * @since 0.1
 */
public abstract class WndTableFrame extends WndInternalFrame
	implements ListSelectionListener {

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
	 * Speichert die Verwaltung der Listener für das Ereignis
	 * ToolBarDbElementEvent.
	 */
	private ToolBarDbElementEventMulticaster _dbEventListener;
	
	/**
	 * Im Konstruktor wird "nur" das Popup-Menü initalisiert.
	 * 
	 * @param desktop Desktop des Hauptfensters.
	 */
	public WndTableFrame(Desktop desktop) {
		// Klasse initalisieren
		super(desktop);
		
		// Initalisiert die Verwaltung der ToolBarDbElementListener
		_dbEventListener = new ToolBarDbElementEventMulticaster();
		
		// Speichern, dass es ein Datenbank-Fenster ist
		setEnableDbElements(true);
		
		// Größenänderung des Fenster zulassen 
		setResizable(true);
		setMinimumSize(new Dimension(600, 400));
		
		// Popup-Menü initalisieren
		_popup = new PopupStandardList(desktop.getToolBar());
	}
	
	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 */
	abstract protected void tableRowDoubleClick();
	
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
		
		// Listener zur Tabelle hinzufügen
		_table.getSelectionModel().addListSelectionListener(this);
		
		// Auf Doppelklick in einer Tabellen-Zeile reagieren
		_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					tableRowDoubleClick();
				}
			}
		});
		
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

	/**
	 * Wird aufgerufen, wenn eine Zeile selektiert wurde.
	 * 
	 * @param e Event-Daten
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (_table.getSelectedRow() > -1) {
			fireSetDbElementsEnable(true);
		} else {
			fireSetDbElementsEnable(false);
		}
	}
	
	/**
	 * Fügt einen ToolBarDbEventListener zur Liste hinzu.
	 * 
	 * @param listener Neuer Listener, der zu Liste hinzugefügt werden soll.
	 */
	public void addToolBarDbEventListener(ToolBarDbElementListener listener) {
		_dbEventListener.add(listener);
	}
	
	/**
	 * Löscht den angegebenen Listener aus der Liste der ToolBarDbEventListener.
	 * 
	 * @param listener Listener, der aus der Liste gelöscht werden soll.
	 */
	public void removeToolBarDbEventListener(ToolBarDbElementListener listener) {
		_dbEventListener.remove(listener);
	}
	
	/**
	 * Erzeugt das Ereignis setDbElementsEnable und schickt es an die
	 * eingetragenen Listener.
	 * 
	 * @param enable Sollen die Datenbank-Element "Ändern" und "Löschen"
	 * benutzbar sein oder nicht?
	 */
	public void fireSetDbElementsEnable(boolean enable) {
		_dbEventListener.setDbElementsEnable(new ToolBarDbElementEvent(_table,
				enable, this));
	}
	
	/**
	 * Wird aufgerufen, wenn ein neuer Datensatz eingefügt werden soll.
	 */
	public abstract void insert();
	
	/**
	 * Wird aufgerufen, wenn ein Datensatz geändert werden soll.
	 */
	public abstract void change();
	
	/**
	 * Wird aufgerufen, wenn ein Datenatz gelöscht werden soll.
	 */
	public abstract void delete();
}
