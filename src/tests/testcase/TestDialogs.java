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

package tests.testcase;

import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

public abstract class TestDialogs implements Scenario {
	
	/**
	 * Speichert das Hauptfenster der Test-Applikation
	 */
	protected JFrameOperator _frame;
	
	/**
	 * Speichert den Dialog
	 */
	protected JDialogOperator _dlg;
	
	/**
	 * Speichert den Button
	 */
	protected JButtonOperator _btnOk;
	
	/**
	 * Führt einen Klick auf den Button aus
	 */
	public void pushOk() {
		_btnOk.push();
	}
	
	/**
	 * Überprüft, ob der Dialog angezeigt wird.
	 */
	public boolean dlgIsVisible() {
		return _dlg.isVisible();
	}
	
	/**
	 * Gibt das Hauptfenster zurück
	 * 
	 * @return Hauptfenster
	 */
	public JFrameOperator getFrame() {
		return _frame;
	}

	/**
	 * Führt die Tests aus
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

}
