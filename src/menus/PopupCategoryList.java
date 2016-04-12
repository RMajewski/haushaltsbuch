package menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Initalisiert das Popup-Menü für die Kategorie-Tabelle.
 * 
 * @author René Majewski
 */
public class PopupCategoryList extends JPopupMenu {
	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -141127052107997553L;
	
	/**
	 * ActionCommand zum Löschen der ausgewählten Kategorie.
	 */
	public static final String DELETE = "PopupCategoryDelete";
	
	/**
	 * ActionCommand zum Ändern der ausgewählten Kategorie.
	 */
	public static final String CHANGE = "PopupCategoryChange";
	
	/**
	 * ActionCommand, um eine neue Kategorie erstellen.
	 */
	public static final String NEW = "PopupCategoryNew";
	
	/**
	 * Initalisiert das Popup-Menü
	 * 
	 * @param listener Wer reagiert auf die einzelnen Einträge?
	 */
	public PopupCategoryList(ActionListener listener) {
		super();
		
		// Neuer Eintrag hinzu fügen
		JMenuItem item = new JMenuItem("Neu");
		item.setMnemonic('N');
		item.setActionCommand(NEW);
		item.addActionListener(listener);
		add(item);
		
		// Eintrag ändern
		item = new JMenuItem("Ändern");
		item.setMnemonic('Ä');
		item.setActionCommand(CHANGE);
		item.addActionListener(listener);
		add(item);
		
		// Eintrag Löschen
		item = new JMenuItem("Löschen");
		item.setMnemonic('L');
		item.setActionCommand(DELETE);
		item.addActionListener(listener);
		add(item);
	}
}
