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

package tests.fixtures.elements;

import fit.ActionFixture;
import tests.tests.elements.TestToolBarMainFit;

/**
 * Implementiert die Schnittstelle zwischen den Fit-Tests für die
 * {@link haushaltsbuch.elementes.ToolBarMain} und dem Testprogramm
 * {@link tests.tests.elements.TestToolbarMain}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureToolBarMain extends ActionFixture {

	/**
	 * Speichert die Instanz des Testprogrammes.
	 */
	private TestToolBarMainFit _test;
	
	/**
	 * Initalisiert die Klasse
	 */
	public FixtureToolBarMain() throws Exception {
		_test = new TestToolBarMainFit();
	}
	
	/**
	 * Gibt den Namen der ToolBar zurück.
	 * 
	 * @return Name der ToolBar
	 */
	public String getToolBarName() {
		return _test.getToolBarName();
	}
	
	/**
	 * Drückt den Button für den Report.
	 */
	public void pushReport() {
		_test.pushReport();
	}
	
	/**
	 * Ermittelt ob der Dialog angezeigt wird.
	 * 
	 * @return Wird der Dialog angezeigt?
	 */
	public String isDialogVisible() {
		return String.valueOf(_test.isDialogVisible());
	}
	
	/**
	 * Ermittelt den Namen des Dialoges
	 */
	public String getDialogTitle() {
		return _test.getDialogTitle();
	}
}
