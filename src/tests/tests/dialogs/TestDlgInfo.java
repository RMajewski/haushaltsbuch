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

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;

import tests.apps.TestDialogApplication;
import tests.testcase.TestDialogs;

/**
 * Testet den Info-Dialog
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestDlgInfo extends TestDialogs {
	/**
	 * Initalisiert die Klasse
	 */
	public TestDlgInfo() throws Exception {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
		
		// Dialog aufrufen
		new JButtonOperator(_frame,
				TestDialogApplication.DIALOG_INFO).pushNoBlock();
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame, "Test");
		
		// Button ermitteln
		_btnOk = new JButtonOperator(_dlg, "Ok");
	}
	
	/**
	 * Ermittelt, ob der Dialog Modal ist.
	 * 
	 * @return Ist der Dialog modal oder nicht?
	 */
	public boolean isDialogModal() {
		return _dlg.isModal();
	}
	
	/**
	 * Gibt den Ok-Button zurück
	 * 
	 * @return Ok-Button
	 */
	public JButton getOkButton() {
		return (JButton)_btnOk.getSource();
	}
	
	/**
	 * Gibt den Info-Icon zurück.
	 * 
	 * @return Info-Icon
	 */
	public Icon getInfoIcon() {
		return ((JLabel)new JLabelOperator(_dlg, 0).getSource()).getIcon();
	}
	
	/**
	 * Gibt den Text der angezeigten Nachricht wieder.
	 * 
	 * @return Angezeigte Nachricht.
	 */
	public String getMessage() {
		return ((JLabel)new JLabelOperator(_dlg, 1).getSource()).getText();
	}
	
	/**
	 * Ermittelt den Titel und gibt diesen zurück.
	 * 
	 * @return Titel des Dialogs
	 */
	public String getDialogTitle() {
		return _dlg.getTitle();
	}
	
	/**
	 * Startet den Test
	 */
	public static void main(String[] args) {
		Test.main(new String[] {"tests.tests.dialogs.TestDlgInfo"});
	}

}
