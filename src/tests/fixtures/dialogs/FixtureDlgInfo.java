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

import tests.tests.dialogs.TestDlgInfo;

/**
 * Beinhaltet die einzelnen Test-Schritte, die im FIT-Dokument angegeben sind.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureDlgInfo extends FixtureDialogs {
	/**
	 * Initalisiert diese Klasse
	 * 
	 * @throws Exception 
	 */
	public FixtureDlgInfo() throws Exception {
		_test = new TestDlgInfo();
	}
	
	/**
	 * Überprüft, ob der Dialog modal ist.
	 * 
	 * @return Ist der Dialog modal oder nicht?
	 */
	public String isModal() {
		return String.valueOf(((TestDlgInfo)_test).isDialogModal());
	}
	
	/**
	 * Überprüft, ob der Dialog einen Ok-Button hat.
	 * 
	 * @return Hat der Dialog einen Ok-Button.
	 */
	public String haveOkButton() {
		return String.valueOf(((TestDlgInfo)_test).getOkButton() != null);
	}
	
	/**
	 * Überprüft, ob der Dialog einen Info-Icon hat.
	 * 
	 * @return Hat der Dialog einen Info-Icon.
	 */
	public String haveInfoIcon() {
		return String.valueOf(((TestDlgInfo)_test).getInfoIcon() != null);
	}
	
	/**
	 * Ermittelt die angezeigte Nachricht
	 * 
	 * @return Angezeigte Nachricht
	 */
	public String getMessage() {
		return ((TestDlgInfo)_test).getMessage();
	}
	
	/**
	 * Ermittelt den Titel des Dialoges
	 * 
	 * @return Angezeigter Dialog-Titel
	 */
	public String getTitle() {
		return ((TestDlgInfo)_test).getDialogTitle();
	}
}
