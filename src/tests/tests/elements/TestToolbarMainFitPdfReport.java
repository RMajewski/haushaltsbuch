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

package tests.tests.elements;

import javax.swing.JButton;

import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;

/**
 * Implementiert das Testprogramm, um jemmy- und Fit-Tests auszufügen, um die
 * {@link haushaltsbuch.elementes.ToolBarMain} zu testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolbarMainFitPdfReport extends TestToolBarMainFit {

	/**
	 * Initalisiert die Tests.
	 * 
	 * @throws Exception
	 */
	public TestToolbarMainFitPdfReport() throws Exception {
		super();
	}
	
	/**
	 * Ermittelt, ob der Button "PDF-Report" anklickbar ist oder nicht.
	 * 
	 * @return Ist der Button "PDF-Report" anklickbar?
	 */
	public boolean istButtonPdfReportEnabled() {
		return new JButtonOperator((JButton)_toolbar.getComponent(5)).isEnabled();
	}
	
	/**
	 * Drückt im Report-Dialog den Button "Report Erstellen".
	 */
	public void pushDialogReportCreate() {
		new JButtonOperator(_dlg, "Report erstellen").push();
	}
	
	/**
	 * Drückt den ToolBar-Button "PDF-Report".
	 */
	public void pushToolBarButtonPdfReport() {
		new JButtonOperator((JButton)_toolbar.getComponent(5)).pushNoBlock();
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Parameter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[]
				{"tests.tests.elements.TestToolBarMainFitPdfReport"});
	}


}
