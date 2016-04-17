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
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import test.GuiTest;
import windows.WndMain;

/**
 * Testet, ob der Log-Dialog mit Log->Ansehen aufgerufen werden kann.
 * 
 * @see dialogs.DlgLog
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestDlgLog extends GuiTest {

	/**
	 * Führt den Test aus
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
			menu.pushMenuNoBlock("Log|Anzeigen");
			
			// Dialog-Fenster abfangen
			JDialogOperator dlg = new JDialogOperator(wnd, "Log");
			
			test("Überprüfen, ob der Dialog Modal ist", dlg.isModal());
			test("Überprüfen, ob der Dialog angezeigt wird.", dlg.isVisible());
		} catch (Exception e) {
			return 1;
		}
		
		return 0;
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Parameter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.dialogs.TestDlgLog"});
	}

}
