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

package tests.fixtures.dialogs;

import fit.ActionFixture;
import tests.testcase.TestDialogs;

/**
 * Implementiert die standard Aktionen der Dialoge.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureDialogs extends ActionFixture {
	/**
	 * Speichert die Test-Klasse
	 */
	protected TestDialogs _test;
	
	/**
	 * Drückt den Button, damit das Fenster beendet wird.
	 */
	public void pushOk() {
		_test.pushOk();
	}
	
	/**
	 * Überprüft, ob der Dialog angezeigt wird.
	 */
	public String isVisible() {
		return String.valueOf(_test.dlgIsVisible());
	}
	
	/**
	 * Beendet die Test-Applikation
	 */
	public void applicationEnd() {
		if (_test.getFrame() != null)
			_test.getFrame().dispose();
	}
	
}
