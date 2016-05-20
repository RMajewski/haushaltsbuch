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

import tests.tests.elements.TestToolBarMainFitPdfReport;
import tests.tests.elements.TestToolBarMainFitPrint;

/**
 * Implementiert die Schnittstelle zwischen den Fit-Tests für die
 * {@link haushaltsbuch.elements.ToolBarMain} und dem Testprogramm
 * {@link tests.tests.elements.TestToolBarMainFitPrint}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class FixtureToolBarMainPrint extends FixtureToolBarMain {

	/**
	 * Initalisiert die Schnittstelle
	 */
	public FixtureToolBarMainPrint() throws Exception {
		super();
		_test = new TestToolBarMainFitPrint();
	}
	
	/**
	 * Ermittelt, ob der Toolbar-Eintrag "Drucken" enabled ist.
	 * 
	 * @return Ist "Drucken" enabled?
	 */
	public boolean isPrintEnable() {
		return ((TestToolBarMainFitPrint)_test).isButtonPrintEnabled();
	}

	/**
	 * Drückt auf Datenbank -> Kategorien
	 */
	public void pressDbCategory() {
		_test.pushMenu("Datenbank|Kategorien");
	}
	
	/**
	 * Drückt auf Datenbank -> Geschäfte
	 */
	public void pushDbSection() {
		_test.pushMenu("Datenbank|Geschäfte");
	}
	
	/**
	 * Das Fenster für die Kategorien erhält den Focus
	 */
	public void dbCategoryFocus() {
		((TestToolBarMainFitPrint)_test).dbCategoryFocus();
	}
}
