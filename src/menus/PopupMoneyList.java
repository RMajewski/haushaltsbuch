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
