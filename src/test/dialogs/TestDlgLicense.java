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

package test.dialogs;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;

import test.GuiTest;
import windows.WndMain;

/**
 * Testet, ob der Lizenz-Dialog mit Hilfe->Lizenz aufgerufen werden kann und
 * ob er mit einen Button beendet werden kann.
 * 
 * 
 * @author René Majewski
 */
public class TestDlgLicense extends GuiTest {

	@Override
	public int runIt(Object arg0) {
		try {
			// Bundle Erstellen
			// Start des Haupt-Programms
			new ClassReference("Main").startApplication();
			
			// Fenster des Hauptprogrammes
			JFrameOperator wnd = new JFrameOperator(WndMain.TITLE);
			
			// MainMenu-Bar laden und Help -> "Über ..." aufrufen
			JMenuBarOperator menu = new JMenuBarOperator(wnd);
			menu.getTimeouts().setTimeout("JMenuOperator.PushMenuTimeout", 600000);
			menu.pushMenuNoBlock("Hilfe|Lizenz...");
			
			// Dialog-Fenster abfangen
			JDialogOperator dlg = new JDialogOperator(wnd, "Lizenz");
			
			// TextArea ermitteln und Tests durchführen
			JTextAreaOperator txt = new JTextAreaOperator(dlg);
			test("Überprüfen, ob es eine TextArea gibt und ob sie angezeigt wird",
					txt.isVisible());
			
			test("Überprüfen, ob die TextArea im nur Lesemodus ist",
					!txt.isEditable());
			
			test("Überprüfen, ob der Lizenz-Text geladen wurde",
					txt.getLineCount() > 0);
			
			test("Überprüfen, ob der Dialog Modal ist", dlg.isModal());
			
			test("Überprüfen, ob der Dialog angezeigt wird.", dlg.isVisible());
			
			// Button ermitteln und ihn drücken
			JButtonOperator btn = new JButtonOperator(dlg, "Ok");
			btn.push();
			
			// Überprüfen, ob der Dialog nicht mehr angezeigt wird
			test("Überprüfen, ob der Dialog nicht mehr angezeigt wird",
					!dlg.isVisible());
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		return 0;
	}

	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.dialogs.TestDlgLicense"});
	}

}
