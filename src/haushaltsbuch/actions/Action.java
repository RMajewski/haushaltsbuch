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

package haushaltsbuch.actions;

import java.io.File;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import haushaltsbuch.elements.Desktop;

public abstract class Action extends AbstractAction {
	
	/**
	 * Speichert den Phat zu den Icons
	 */
	public static final String ICON_PATH = "icons" + File.separator;

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -3686095313000985478L;
	
	/**
	 * Speichert den Desktop vom Hauptfenster.
	 */
	protected Desktop _desktop;
	
	/**
	 * Initalisiert die Klasse. Und legt die übergebenen Icons fest. Es wird
	 * geprüft, ob die Icons geladen werden kann. Ist dies nicht der Fall, so
	 * wird nicht versucht diese zu laden.
	 * 
	 * @param small Name des Icons für das Menü
	 * 
	 * @param big Name des Icons für die ToolBar
	 * 
	 * @param desktop Dekstop des Hauptfensters
	 */
	public Action(String small, String big, Desktop desktop) {
		// Klasse initalisieren
		super();
		
		// Dekstop speichern
		_desktop = desktop;
		
		// Icon für Menüs
		URL url = Action.class.getClassLoader().getResource(ICON_PATH + small);
		if (url != null)
			putValue(Action.SMALL_ICON, new ImageIcon(url));
		
		// Icon für ToolBar
		url = Action.class.getClassLoader().getResource(ICON_PATH + big);
		if (url != null)
			putValue(Action.LARGE_ICON_KEY, new ImageIcon(url));
	}

}
