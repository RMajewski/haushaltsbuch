package windows.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
		//pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die einzelnen Einträge im Popup-Menü
	 * 
	 * param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
	}
}
