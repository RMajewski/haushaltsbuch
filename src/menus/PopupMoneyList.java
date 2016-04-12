package menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * Initalisiert das Standart-Popup-Menü um Einträge, die speziell im
 * Fenster {@link windows.internal.WndMoneyList} gebraucht werden.
 * 
 * @author René Majewski
 *
 * @see menus.PopupCategoryList
 */
public class PopupMoneyList extends PopupCategoryList {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 2081083156312324016L;
	
	/**
	 * ActionCommand, um die Datails zum ausgewählten Datensatz anzuzeigen
	 */
	public static final String DETAILS = "PopupMoneyDetails";

	/**
	 * Initalisiert das Popup-Menü
	 * 
	 * @param listener Wer reagiert auf die einzelnen Einträge
	 */
	public PopupMoneyList(ActionListener listener) {
		// Standart-Menü-Einträge
		super(listener);
		
		// Linie einfügen.
		addSeparator();
		
		// Details anzeigen
		JMenuItem item = new JMenuItem("Details");
		item.setMnemonic('D');
		item.setActionCommand(DETAILS);
		item.addActionListener(listener);
		add(item);
	}

}
