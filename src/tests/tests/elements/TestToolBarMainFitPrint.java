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

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;

/**
 * Implementiert das Testprogramm, um jemmy- und Fit-Tests auszuführen, um die
 * {@link haushaltsbuch.elements.ToolBarMain} zu testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class TestToolBarMainFitPrint extends TestToolBarMainFit {
	/**
	 * Initalisiert die Tests.
	 * 
	 * @throws Exception
	 */
	public TestToolBarMainFitPrint() throws Exception {
		super();
	}

	/**
	 * Ermittelt ob der Toolbar-Eintrag "Drucken" enabled ist.
	 * 
	 * @return Ist "Drucken" enabled?
	 */
	public boolean isButtonPrintEnabled() {
		return new JButtonOperator((JButton)_toolbar.getComponent(7)).isEnabled();
	}
	
	/**
	 * Das Fenster Kategorien erhält den Focus
	 */
	public void dbCategoryFocus() {
		new JInternalFrameOperator(_wnd, "Kategorien").getFocus();;
	}
}
