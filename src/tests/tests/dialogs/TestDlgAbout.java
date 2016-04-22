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
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

import haushaltsbuch.dialogs.DlgAbout;
import tests.apps.TestDialogApplication;

/**
 * Testet den Dialog "Über"
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 */
public class TestDlgAbout implements Scenario {
	
	/**
	 * Speichert das Hauptfenster der Test-Applikation
	 */
	private JFrameOperator _frame;
	
	/**
	 * Speichert den Dialog
	 */
	private JDialogOperator _dlg;
	
	/**
	 * Speichert den Button
	 */
	private JButtonOperator _btn;
	
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestDlgAbout() throws Exception {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
		
		// Dialog aufrufen
		new JButtonOperator(_frame,
				TestDialogApplication.DIALOG_ABOUT).pushNoBlock();
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame, DlgAbout.DIALOG_TITLE);
		
		// Button ermitteln
		_btn = new JButtonOperator(_dlg, "Ok");
	}
	
	/**
	 * Führt einen Klick auf den Button aus
	 */
	public void pushOk() {
		_btn.push();
	}
	
	/**
	 * Überprüft, ob der Dialog angezeigt wird.
	 */
	public boolean dlgIsVisible() {
		return _dlg.isVisible();
	}

	/**
	 * Führt die Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Dialog Sichtbar?
			if (!_dlg.isVisible())
				throw new Exception();
			
			// Dialog modal
			if (!_dlg.isModal())
				throw new Exception();
			
			// Button drücken
			pushOk();
			
			// Dialog sichtbar?
			if (_dlg.isVisible())
				throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	/**
	 * Startet den Test
	 */
	public static void main(String[] args) {
		Test.main(new String[] {"tests.tests.dialogs.TestDlgAbout"});
	}
}
