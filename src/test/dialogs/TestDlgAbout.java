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
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import test.GuiTest;
import windows.WndMain;

/**
 * Testet, ob der "Über ..."-Dialog mit Help->Über... aufgerufen werden kann
 * und ob er einen Button hat, der den Dialog beendet.
 * 
 * @author René Majewski
 */
public class TestDlgAbout extends GuiTest {
	/**
	 * Ruft die einzelnen Tests auf.
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Start des Haupt-Programms
			new ClassReference("Main").startApplication();
			
			// Fenster des Hauptprogrammes
			JFrameOperator wnd = new JFrameOperator(WndMain.TITLE);
			
			// MainMenu-Bar laden und Help -> "Über ..." aufrufen
			JMenuBarOperator menu = new JMenuBarOperator(wnd);
			menu.getTimeouts().setTimeout("JMenuOperator.PushMenuTimeout", 600000);
			menu.pushMenuNoBlock("Hilfe|Über...");
			
			// Dialog-Fenster abfangen
			JDialogOperator dlg = new JDialogOperator(wnd, "Über ...");
			
			test("Überprüfen, ob der Dialog angezeigt wird", dlg.isVisible());
			
			// Button ermitteln und ihn drücken
			JButtonOperator btn = new JButtonOperator(dlg, "Ok");
			btn.push();
			
			test("Überprüfen, ob der Dialog nicht mehr angezeigt wird", !dlg.isVisible());
			
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}
	
	public static void main(String[] argv) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.dialogs.TestDlgAbout"});
	}

}
