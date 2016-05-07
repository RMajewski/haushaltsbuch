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

package haushaltsbuch.events;

import java.util.EventObject;

import haushaltsbuch.windows.internal.WndTableFrame;

/**
 * Speichert die Daten für das Ereignis, wenn ein Datenbank-Fenster der ToolBar
 * mitteilt, ob die Datenbank-Elemente aktiviert oder deaktiviert werden sollen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ToolBarDbElementEvent extends EventObject {
	/**
	 * Speichert, ob die Elemente "Ändern" und "Löschen" benutzbar sein sollen
	 * oder nicht.
	 */
	private boolean _enable;
	
	/**
	 * Speichert das Datenbank-Fenster, dass das Ereignis ausgelöst hat
	 */
	private WndTableFrame _frame;

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -5747678507721562862L;

	/**
	 * Initalisiert die Daten.
	 * 
	 * @param src Von welchen Element wurde das Event ausgelöst?
	 * 
	 * @param enable Sollen die Elemente "Ändern" und "Löschen" benutzbar sein?
	 * 
	 * @param frame Datenbank-Fenster, dass das Ergeignis ausgelöst hat.
	 */
	public ToolBarDbElementEvent(Object src, boolean enable,
			WndTableFrame frame) {
		super(src);
		_enable = enable;
		
		if (frame == null)
			throw new IllegalArgumentException("There was null as a parameter.");
		_frame = frame;
	}
	
	/**
	 * Ermittelt, ob die Elemente "Ändern" und "Löschen" angezeigt werden sollen
	 * oder nicht.
	 * 
	 * @return Sollen die Elemente "Ändern" und "Löschen" angezeigt werden?
	 */
	public boolean isEnable() {
		return _enable;
	}

	/**
	 * Gibt das Datenbank-Fenster zurück, welches das Ereignis ausgelöst hat.
	 * 
	 * @return Datenbank-Fenster, dass dieses Ereignis ausgelöst hat.
	 */
	public WndTableFrame getFrame() {
		return _frame;
	}
}
