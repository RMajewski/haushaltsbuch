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

package tests.tests.dialogs;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;

import haushaltsbuch.dialogs.DlgLicense;
import tests.apps.TestDialogApplication;
import tests.testcase.TestDialogs;

/**
 * Testet den Dialog "Lizenz"
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 */
public class TestDlgLicense extends TestDialogs {
	/**
	 * Speichert die Text-Area
	 */
	private JTextAreaOperator _txt;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestDlgLicense() throws Exception {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
		
		// Dialog aufrufen
		new JButtonOperator(_frame,
				TestDialogApplication.DIALOG_LICENSE).pushNoBlock();
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame, DlgLicense.DIALOG_TITLE);
		
		// Text-Area ermitteln
		_txt = new JTextAreaOperator(_dlg);
		
		// Button ermitteln
		_btnOk = new JButtonOperator(_dlg, "Ok");
	}
	
	/**
	 * Gibt die Anzahl der Zeilen aus.
	 */
	public int lineCount() {
		return _txt.getLineCount();
	}
	
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object o) {
		// Anzahl der Linien ermitteln
		if (lineCount() < 300)
			return 1;

		if (super.runIt(o) != 0)
			return 1;
		
		return 0;
	}
	
	/**
	 * Startet den Test
	 */
	public static void main(String[] args) {
		Test.main(new String[] {"tests.tests.dialogs.TestDlgLicense"});
	}

}
