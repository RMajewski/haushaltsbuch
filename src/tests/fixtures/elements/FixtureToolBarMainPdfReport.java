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

import tests.tests.elements.TestToolbarMainFitPdfReport;

/**
 * Implementiert die Schnittstelle zwischen den Fit-Tests für die
 * {@link haushaltsbuch.elementes.ToolBarMain} und dem Testprogramm
 * {@link tests.tests.elements.TestToolbarMainPdfReport}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureToolBarMainPdfReport extends FixtureToolBarMain {

	/**
	 * Initalisiert die Schnittstelle
	 * 
	 * @throws Exception
	 */
	public FixtureToolBarMainPdfReport() throws Exception {
		super();
		_test = new TestToolbarMainFitPdfReport();
	}
	
	/**
	 * Ermittelt, ob der ToolBar-Button "PDF-Report" ausführbar ist oder nicht.
	 * 
	 * @return Ist der Button "PDF-Report" anklickbar?
	 */
	public String isPdfReportEnabled() {
		return String.valueOf(((TestToolbarMainFitPdfReport)_test).istButtonPdfReportEnabled());
	}
	
	/**
	 * Drück im Dialog den Button OK.
	 */
	public void pushDialogOk() {
		((TestToolbarMainFitPdfReport)_test).pushDialogReportCreate();
	}
	
	/**
	 * Drückt den ToolBar-Button "PDF-Report"
	 */
	public void pushPdfReport() {
		((TestToolbarMainFitPdfReport)_test).pushToolBarButtonPdfReport();
	}

}
