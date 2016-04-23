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
import tests.tests.dialogs.TestDlgLicense;

public class FixtureDlgLicense extends ActionFixture {
	/**
	 * Speichert die Test-Klasse
	 */
	private TestDlgLicense _test;
	
	/**
	 * Initalisiert diese Klasse
	 * 
	 * @throws Exception 
	 */
	public FixtureDlgLicense() throws Exception {
		_test = new TestDlgLicense();
	}
	
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
	 * Gibt die Anzahl der Linien zurück
	 */
	public String lineCount() {
		return String.valueOf(_test.lineCount());
	}

}
