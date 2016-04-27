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
import haushaltsbuch.windows.internal.WndInternalFrame;

/**
 * Implementiert die Methoden, die in allen Aktionen der ToolBar vorhanden sein
 * sollen.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
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
	 * Speichert das Fenster, in welchem die Aktion durchgeführt werden soll.
	 */
	protected WndInternalFrame _frame;
	
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
		
		// Kein Fenster zum ausführen der Aktion vorhanden
		deleteFrame();
		
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
	
	/**
	 * Setzt das Fenster, in dem die Aktion ausgeführt werden soll.
	 * 
	 * @param frame Fenster, in dem die Aktion ausgeführt werden soll.
	 * 
	 * @throws IllegalArgumentException Dieser Fehler wird ausgelöst, wenn null
	 * als Parameter übergeben wird.
	 */
	public void setFrame(WndInternalFrame frame) 
			throws IllegalArgumentException {
		if (frame == null)
			throw new IllegalArgumentException("Where was null as a paramter.");
		_frame = frame;
	}
	
	/**
	 * Gibt das Fenster zurück, in welchem die Aktion ausgeführt werden soll.
	 * 
	 * @return Gespeichertes Fenster, in dem die Aktion ausgeführt werden soll.
	 */
	public WndInternalFrame getFrame() {
		return _frame;
	}
	
	/**
	 * Lösche das Fenster, in welchem die Aktion ausgeführt werden soll.
	 */
	public void deleteFrame() {
		_frame = null;
	}

}
