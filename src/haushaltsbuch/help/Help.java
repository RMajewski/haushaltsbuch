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

package haushaltsbuch.help;

import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;

import haushaltsbuch.elements.StatusBar;

public class Help {
	/**
	 * Speichert die Instanz der Hilfe.
	 */
	private static Help _help = new Help();
	
	/**
	 * Speichert die Instanz des HelpSet.
	 */
	private HelpSet _hs;
	
	/**
	 * Speichert die Instanz des HelpBroker.
	 */
	private HelpBroker _broker;
	
	/**
	 * Initialisiert die Hilfe.
	 */
	private Help() {
		try {
			_hs = new HelpSet(null, HelpSet.findHelpSet(null, 
					"help/jhelpset.hs"));
			_hs.setHomeID("main");
			_broker = _hs.createHelpBroker();
		} catch (HelpSetException e) {
			StatusBar.getInstance().setMessageAsError(e);
		}
	}
	
	/**
	 * Gibt die Instanz der Hilfe zurück.
	 * 
	 * @return Instanz der Hilfe.
	 */
	public static Help getInstance() {
		if (_help == null)
			_help = new Help();
		return _help;
	}
	
	/**
	 * Gibt die Instanz des HelpSet zurück.
	 * 
	 * @return Instanz des HelpSet
	 */
	public HelpSet getHelpSet() {
		return _hs;
	}
	
	/**
	 * Gibt die Instanz des HelpBroker zurück.
	 * 
	 * @return Instanz des HelpBroker.
	 */
	public HelpBroker getHelpBroker() {
		return _broker;
	}
}
