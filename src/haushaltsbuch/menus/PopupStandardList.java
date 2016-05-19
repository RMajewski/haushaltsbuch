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

package haushaltsbuch.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import haushaltsbuch.elements.ToolBarMain;

/**
 * Initalisiert das Popup-Menü für die Kategorie-Tabelle.
 * 
 * In Version 0.2 werden die Einträge "Neu", "Datensatz ändern" und "Datensatz
 * löschen" aus der {@link haushaltsbuch.elements.TollBarMain} geladen.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 */
public class PopupStandardList extends JPopupMenu {
	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -141127052107997553L;

	/**
	 * Initalisiert das Popup-Menü
	 * 
	 * @param tbmain Standard Toolbar
	 */
	public PopupStandardList(ToolBarMain tbMain) {
		super();
		
		// Neuer Eintrag hinzu fügen
		JMenuItem item = new JMenuItem(tbMain.getDbInsert());
		item.setName("Neu");
		item.setMnemonic('N');
		add(item);
		
		// Eintrag ändern
		item = new JMenuItem(tbMain.getDbChange());
		item.setName("Ändern");
		item.setMnemonic('Ä');
		add(item);
		
		// Eintrag Löschen
		item = new JMenuItem(tbMain.getDbDelete());
		item.setName("Löschen");
		item.setMnemonic('L');
		add(item);
	}
}
