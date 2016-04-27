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

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;

import haushaltsbuch.db.DbController;
import tests.apps.TestToolBarApplication;

/**
 * Implementiert das Testprogramm, um jemmy- und Fit-Tests auszufügen, um die
 * {@link haushaltsbuch.elementes.ToolBarMain} zu testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarMainFitMoney extends TestToolBarMainFit {
	
	/**
	 * Initalisiert die Tests
	 * @throws Exception
	 */
	public TestToolBarMainFitMoney() throws Exception {
		super();
	}
	
	/**
	 * Drückt auf das Element "Einfügen"
	 */
	@Override
	public void pushInsert() {
		new JButtonOperator((JButton)_toolbar.getComponent(0)).push();
		_frame = new JInternalFrameOperator(_wnd,
				"Datensatz erstellen");
	}
	
	/**
	 * Druck auf das Element "Ändern"
	 */
	@Override
	public void pushChange() {
		new JButtonOperator((JButton)_toolbar.getComponent(1)).push();
		_frame = new JInternalFrameOperator(_wnd,
				"Datensatz ändern");
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Parameter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] 
				{"tests.tests.elements.TestToolBarMainFitMoney"});
	}
}
