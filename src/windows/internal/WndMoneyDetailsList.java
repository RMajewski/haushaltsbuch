package windows.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import datas.MoneyData;
import listener.PopupMenuMouseListener;
import menus.PopupCategoryList;
import tables.models.MoneyDetailsListModel;

/**
 * In diesem Fenster wird zum angegeben Datensatz aus der Tabelle 'money' die
 * Detail-Datensätze als Liste angezeigt.
 * 
 * @author René Majewski
 */
public class WndMoneyDetailsList extends WndInternalFrame implements ActionListener{

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 8514536537238375532L;
	
	/**
	 * Speichert den Datensatz aus der Tabelle 'money'
	 */
	private MoneyData _money;
	
	/**
	 * Speichert die Tabelle
	 */
	private JTable _table;

	/**
	 * Initalisiert das Fenster und speichert die Daten des angegebenen
	 * Money-Datensatzes.
	 * 
	 * @param money Zugehöriger Money-Datensatz
	 */
	public WndMoneyDetailsList(MoneyData money) {
		// Klasse initalisiern
		super();
		
		// Datsatz aus 'money' speichern
		_money = money;
		
		// Titel des Fensters
		setTitle("Details zur " + _money.getInOutAsString() + " vom " +
				_money.getDateAsString());
		
		// Größe
		setSize(1000, 400);
		
		// Tabelle initalisieren
		_table = new JTable(new MoneyDetailsListModel(_money.getId()));
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("MoneyID");
		_table.getColumnModel().getColumn(2).setHeaderValue("Kategorie");
		_table.getColumnModel().getColumn(3).setHeaderValue("Geschäft");
		_table.getColumnModel().getColumn(4).setHeaderValue("Betrag");
		_table.getColumnModel().getColumn(5).setHeaderValue("Beschreibung");
		
		// Tabelle in Scrollbereich anzeigen
		JScrollPane scroll = new JScrollPane(_table);
		add(scroll);
		
		// Popup-Menü initalisieren
		PopupCategoryList popup = new PopupCategoryList(this);
		PopupMenuMouseListener listener = new PopupMenuMouseListener(popup);
		scroll.addMouseListener(listener);
		_table.addMouseListener(listener);
		
		// Fenster anzeigen
		//pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die einzelnen Einträge im Popup-Menü
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
	}
}
