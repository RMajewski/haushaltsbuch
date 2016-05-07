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

package tests.fixtures.windows;

import fit.ActionFixture;
import tests.tests.windows.TestWndMain;

/**
 * Implementiert die Methoden für die Fit-Tests für das Hauptfenster
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndMain extends ActionFixture{
	/**
	 * Speichert die Test-Klasse
	 */
	protected TestWndMain _test;
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @throws Exception 
	 */
	public FixtureWndMain() throws Exception {
		_test = new TestWndMain();
	}
	
	/**
	 * Überprüft, ob das MainMenu eingefügt wurde
	 */
	public String haveAMainMenu() {
		return String.valueOf(_test.havMainMenu());
	}
	
	/**
	 * Überprüft, ob der Desktop initalisiert wurde.
	 */
	public String isDesktopInit() {
		return String.valueOf(_test.isDekstopInit());
	}
	
	/**
	 * Überprüft, ob die Statusbar initalisiert und angezeigt wurde
	 */
	public String haveAStatusBar() {
		return String.valueOf(_test.haveAStatusBar());
	}
	
	/**
	 * Beendet das Programm über Datei -> Beenden
	 */
	public void pushFileEnd() {
		_test.pushFileEnde();
	}
	
	/**
	 * Ermittelt, ob das Hauptfenster angezeigt wird.
	 * 
	 * @return Wird das Hauptfenster angezeigt?
	 */
	public String isWindowVisible() {
		return String.valueOf(_test.isWindowVisible());
	}
}
