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

package test.menus;

import javax.swing.JMenu;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import test.GuiTest;
import test.GuiTestException;
import windows.WndMain;

/**
 * Überprüft, ob alle Menü-Einträge vorhanden sind.
 * 
 * @author René Majewski
 */
public class TestTopMainMenu extends GuiTest {
	/**
	 * Überprüft ob das Datei-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 * 
	 * @throws GuiTestException
	 */
	private void testFileMenu(JMenu menu) throws GuiTestException{
		test("Ist der Menü-Name Datei?", menu.getText().equals("Datei"));
		test("Überprüfen ob der Beenden-Eintrag vorhanden ist",
				menu.getItem(0).getText().equals("Beenden"));
	}
	
	/**
	 * Überprüft ob das Datenbank-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 * 
	 * @throws GuiTestException
	 */
	private void testDbMenu(JMenu menu) throws GuiTestException {
		test("Ist der Menü-Name Datenbank?", 
				menu.getText().equals("Datenbank"));
		
		test("Überprüfen, ob der Eintrag 'Kategorien' vorhanden ist",
				menu.getItem(0).getText().equals("Kategorien"));
		
		test("Überprüfen, ob der Eintrag 'Geschäfte' vorhanden ist",
				menu.getItem(1).getText().equals("Geschäfte"));
		
		test("Überprüfen, ob der Eintrag 'Einnahmen und Ausgaben' vorhanden ist",
				menu.getItem(2).getText().equals("Einnahmen und Ausgaben"));
	}
	
	/**
	 * Überprüft ob das Log-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 * 
	 * @throws GuitestsException
	 */
	private void testLogMenu(JMenu menu) throws GuiTestException {
		test("Ist der Name des Menü Log?", menu.getText().equals("Log"));
		test("Überprüfen, ob der Eintrag 'Anzeigen...' vorhanden ist",
				menu.getItem(0).getText().equals("Anzeigen..."));
	}
	
	/**
	 * Überprüft ob das Hilfe-Menü richtig ist.
	 * 
	 * @throws GuiTestException
	 */
	private void testHelpMenu(JMenu menu) throws GuiTestException{
		test("Ist der Name des Menü Hilfer?", menu.getText().equals("Hilfe"));

		test("Überprüfen, ob der Eintrag 'Lizenz...' vorhanden ist",
				menu.getItem(0).getText().equals("Lizenz..."));
		
		test("Überprüfen, ob der Eintrag 'Über...' vorhanden ist",
				menu.getItem(2).getText().equals("Über..."));
	}
	

	@Override
	public int runIt(Object arg0) {
		try {
			// Start des Haupt-Programms
			new ClassReference("Main").startApplication();
			
			// Fenster des Hauptprogrammes
			JFrameOperator wnd = new JFrameOperator(WndMain.TITLE);
			
			// MainMenu-Bar laden
			JMenuBarOperator menu = new JMenuBarOperator(wnd);
			
			// Menü-Struktur überprüfen
			testFileMenu(menu.getMenu(0));
			testDbMenu(menu.getMenu(1));
			testLogMenu(menu.getMenu(2));
			testHelpMenu(menu.getMenu(3));
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.menus.TestTopMainMenu"});
	}

}
