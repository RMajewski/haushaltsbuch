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

package tests.fixtures.dialogs;

import haushaltsbuch.dialogs.DlgInputChange;
import tests.tests.dialogs.TestDlgInputChange;

/**
 * Beinhaltet die einzelnen Test-Schritte, die im FIT-Dokument angegeben sind.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class FixtureDlgInputChange extends FixtureDialogs {
	/**
	 * Initalisiert diese Klasse
	 */
	public FixtureDlgInputChange() throws Exception {
	}
	
	/**
	 * Startet den Dialog für eine neue Kategorie eingeben.
	 * @throws Exception
	 */
	public void startCategoryInsert() throws Exception {
		_test = new TestDlgInputChange(DlgInputChange.WND_CATEGORY, 
				DlgInputChange.TYPE_INSERT);
	}
	
	/**
	 * Startet den Dialog für eine Kategorie ändern.
	 * @throws Exception
	 */
	public void startCategoryChange() throws Exception {
		_test = new TestDlgInputChange(DlgInputChange.WND_CATEGORY, 
				DlgInputChange.TYPE_CHANGE);
	}
	
	/**
	 * Startet den Dialog für ein neues Geschäft eingeben.
	 * @throws Exception
	 */
	public void startSectionInsert() throws Exception {
		_test = new TestDlgInputChange(DlgInputChange.WND_SECTION, 
				DlgInputChange.TYPE_INSERT);
	}
	
	/**
	 * Startet den Dialog für ein Geschäft ändern.
	 * @throws Exception
	 */
	public void startSectionChange() throws Exception {
		_test = new TestDlgInputChange(DlgInputChange.WND_SECTION, 
				DlgInputChange.TYPE_CHANGE);
	}

	/**
	 * Überprüft, ob der Dialog modal ist.
	 * 
	 * @return Ist der Dialog modal oder nicht?
	 */
	public boolean isModal() {
		return ((TestDlgInputChange)_test).isDialogModal();
	}

	/**
	 * Überprüft, ob der OK-Button angezeigt wird.
	 * 
	 * @return Wird der Ok-Button angezeigt?
	 */
	public boolean haveOkButton() {
		return ((TestDlgInputChange)_test).isButtonOkVisible();
	}

	/**
	 * Überprüft, ob der Abbrechen-Button angezeigt wird.
	 * 
	 * @return Wird der Abbrechen-Button angezeigt?
	 */
	public boolean haveCancelButton() {
		return ((TestDlgInputChange)_test).isButtonCancelVisible();
	}
	
	/**
	 * Ermittelt den Titel des Dialoges
	 * 
	 * @return Titel des Dialoges
	 */
	public String getTitle() {
		return ((TestDlgInputChange)_test).getDialogTitle();
	}
	
	/**
	 * Ermittelt den Text des Labels.
	 * 
	 * @return Text des Labels im Dialog.
	 */
	public String getLabelText() {
		return ((TestDlgInputChange)_test).getDialogLabelText();
	}
	
	/**
	 * Ermittelt den Text des Eingabe-Feldes im Dialog
	 */
	public String getText() {
		return ((TestDlgInputChange)_test).getDialogEditFieldText();
	}
	
	/**
	 * Drückt auf den Button zum Abbrechen
	 */
	public void pushCancel() {
		((TestDlgInputChange)_test).pushCancel();
	}
	
	/**
	 * Ermittelt den Exit-Status des Dialoges
	 * 
	 * @return Exit-Status des Dialoges
	 */
	public int getExitStatus() {
		return ((TestDlgInputChange)_test).getDialogExitStatus();
	}
	
	/**
	 * Setzt den Text im Text-Feld des Dialoges
	 * 
	 * @param text Text, der in das Eingabe-Feld des Dialoges geschrieben werden
	 * soll.
	 */
	public void setText(String text) {
		((TestDlgInputChange)_test).setDialogTextFieldText(text);
	}
}
