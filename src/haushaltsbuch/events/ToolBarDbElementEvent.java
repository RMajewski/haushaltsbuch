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

/**
 * Speichert die Daten
 * @author René Majewski
 *
 */
public class ToolBarDbElementEvent extends EventObject {
	/**
	 * Speichert, ob die Elemente "Ändern" und "Löschen" benutzbar sein sollen
	 * oder nicht.
	 */
	private boolean _enable;

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -5747678507721562862L;

	/**
	 * Initalisiert die Daten.
	 * 
	 * @param source Von welchen Element wurde das Event ausgelöst?
	 * 
	 * @param enable Sollen die Elemente "Ändern" und "Löschen" benutzbar sein?
	 */
	public ToolBarDbElementEvent(Object source, boolean enable) {
		super(source);
		_enable = enable;
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

}
