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

import javax.swing.JLabel;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import haushaltsbuch.dialogs.DlgInputChange;
import tests.apps.TestDialogApplication;
import tests.testcase.TestDialogs;

/**
 * Testet den Eingabe-Dialog für Kategorien und Geschäfte.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class TestDlgInputChange extends TestDialogs {
	/**
	 * Speichert den Button zum Abbrechen.
	 */
	private JButtonOperator _btnCancel;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestDlgInputChange(int wnd, boolean type) throws Exception {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
		
		// Dialog aufrufen
		JButtonOperator btn = null;
		if (wnd == DlgInputChange.WND_CATEGORY) {
			if (type == DlgInputChange.TYPE_INSERT)
				btn = new JButtonOperator(_frame,
						TestDialogApplication.DIALOG_INPUTCHANGE_CATEGORY_INSERT);
			else {
				btn = new JButtonOperator(_frame, 
						TestDialogApplication.DIALOG_INPUTCHANGE_CATEGORY_CHANGE);
			}
		} else if (wnd == DlgInputChange.WND_SECTION) {
			if (type == DlgInputChange.TYPE_INSERT)
				btn = new JButtonOperator(_frame,
						TestDialogApplication.DIALOG_INPUTCHANGE_SECTION_INSERT);
			else
				btn = new JButtonOperator(_frame,
						TestDialogApplication.DIALOG_INPUTCHANGE_SECTION_CHANGE);
		}
		
		if (btn != null)
			btn.pushNoBlock();;
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame);
		
		// Button ermitteln
		_btnOk = new JButtonOperator(_dlg, "Ok");
		_btnCancel = new JButtonOperator(_dlg, "Abbrechen");
	}
	
	/**
	 * Ermittelt, ob der Dialog modal ist.
	 * 
	 * @return Ist der Dialog modal?
	 */
	public boolean isDialogModal() {
		return _dlg.isModal();
	}
	
	/**
	 * Ermittelt, ob der Ok-Button angezeigt wird.
	 * 
	 * @return Wird der Ok-Button angezeigt?
	 */
	public boolean isButtonOkVisible() {
		return _btnOk.isVisible();
	}
	
	/**
	 * Ermittelt, ob der Abbrechen-Button angezeigt wird.
	 * 
	 * @return Wird der Abbrechen-Button angezeigt?
	 */
	public boolean isButtonCancelVisible() {
		return _btnCancel.isVisible();
	}
	
	/**
	 * Ermittelt den Titel des Dialoges
	 * 
	 * @return Titel des Dialoges
	 */
	public String getDialogTitle() {
		return _dlg.getTitle();
	}
	
	/**
	 * Ermittelt den Text Labels im Dialog
	 * 
	 * @return Text des Dialog Labels.
	 */
	public String getDialogLabelText() {
		return ((JLabel)_dlg.getContentPane().getComponent(
				_dlg.getContentPane().getComponentCount() -1)).getText();
	}

	/**
	 * Ermittelt den Text des Eingabe-Feldes im Dialog.
	 * 
	 * @return Text des Eingabe-Feldes im Dialog.
	 */
	public String getDialogEditFieldText() {
		return ((DlgInputChange)_dlg.getSource()).getInput();
	}
	
	/**
	 * Simuliert einen Klick auf den Abbrechen-Button.
	 */
	public void pushCancel() {
		_btnCancel.push();
	}
	
	/**
	 * Ermittelt den Exit-Status des Dialoges
	 * 
	 * @return Exit-Status des Dialoges
	 */
	public int getDialogExitStatus() {
		return ((DlgInputChange)_dlg.getSource()).getExit();
	}

	/**
	 * Setzt den Text im Text-Feld des Dialoges
	 * 
	 * @param text Text, der in das Eingabe-Feld des Dialoges geschrieben werden
	 * soll.
	 */
	public void setDialogTextFieldText(String text) {
		new JTextFieldOperator(_dlg).setText(text);
	}
}
