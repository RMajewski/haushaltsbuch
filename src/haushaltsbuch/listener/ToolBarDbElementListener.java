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

package haushaltsbuch.listener;

import java.util.EventListener;

import haushaltsbuch.events.ToolBarDbElementEvent;

/**
 * Wird von der ToolBar implementiert, damit sie auf auf das Ereignis
 * {@link haushaltsbuch.events.ToolBarDbElementEvent} reagieren kann, und die
 * Elemente "Ändern" und "Löschen" benutzbar machen kann oder nicht.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public interface ToolBarDbElementListener extends EventListener {
	/**
	 * Wird aufgerufen, wenn die Datenbank-Elemente in der ToolBar benutzbar
	 * bzw. nicht benutzbar gemacht werden können.
	 * 
	 * @param e Ereignis-Daten
	 */
	public void setDbElementsEnable(ToolBarDbElementEvent e);
}
