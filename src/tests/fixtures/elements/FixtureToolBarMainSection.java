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

import haushaltsbuch.windows.internal.WndSectionList;
import tests.tests.elements.TestToolBarMainFitSection;

/**
 * Implementiert die Schnittstelle zwischen den Fit-Tests für die
 * {@link haushaltsbuch.elements.ToolBarMain} und dem Testprogramm
 * {@link tests.tests.elements.TestToolBarMainFitSection}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureToolBarMainSection extends FixtureToolBarMain {

	/**
	 * Initalisiert den Test
	 * 
	 * @throws Exception
	 */
	public FixtureToolBarMainSection() throws Exception {
		_test = new TestToolBarMainFitSection();
	}
	
	/**
	 * Drückt auf den Menü-Eintrag Datenbank -> Geschäft
	 */
	public void pushDatabaseSection() {
		_test.pushMenu("Datenbank|Geschäfte");
		_test.openDatabaseWindow(WndSectionList.WND_TITLE);
	}

}
