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

package menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Initalisiert das Popup-Menü für die Kategorie-Tabelle.
 * 
 * @author René Majewski
 */
public class PopupStandardList extends JPopupMenu {
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
	 * Gibt an, ob der Eintrag "Neu" benutzbar sein soll oder nicht.
	 */
	public static final int VISIBLE_NEW = 0;
	
	/**
	 * Gibt an, ob der Eintrag "Ändern" benutzbar sein soll oder nicht.
	 */
	public static final int VISIBLE_CHANGE = 1;
	
	/**
	 * Gibt an, ob der Eintrag "Löschen" benutzbar sein soll oder nicht.
	 */
	public static final int VISIBLE_DELETE = 2;
	
	/**
	 * Initalisiert das Popup-Menü
	 * 
	 * @param listener Wer reagiert auf die einzelnen Einträge?
	 */
	public PopupStandardList(ActionListener listener) {
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
	
	/**
	 * Es wird die Benutzbarkeit des angegebenen Menü-Eintrages ensprechend
	 * gesetzt.
	 * 
	 * @param item Menü-Eintrag, dessen Benutzbarkeit entsprechend gesetzt
	 * werden soll.
	 * 
	 * @param enable true = Benutzbar, false = Unbenutzbar
	 */
	public void setMenuItemEnable(int item, boolean enable) {
		getComponent(item).setEnabled(enable);
	}
}
