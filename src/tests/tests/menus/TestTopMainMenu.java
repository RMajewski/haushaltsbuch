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

package tests.tests.menus;

import javax.swing.JMenu;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import haushaltsbuch.windows.WndMain;
import tests.exception.GuiTestException;
import tests.testcase.GuiTest;

/**
 * Überprüft, ob alle Menü-Einträge vorhanden sind.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestTopMainMenu extends GuiTest {
	/**
	 * Überprüft ob das Datei-Menü richtig ist.
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

	/**
	 * Überprüft, ob das Report-Menü richtig ist.
	 * 
	 * @param menu
	 * 
	 * @throws GuiTestException
	 */
	private void testReportMenu(JMenu menu) throws GuiTestException {
		test("Ist der Name des Menü Report?", menu.getText().equals("Report"));
		
		test("Überprüfen, ob der Eintrag 'Wochenübersicht' vorhanden ist",
				menu.getItem(0).getText().equals("Wochenübersicht"));
		
		test("Überprüfen, ob der Eintrag 'Monatsübersicht' vorhanden ist",
				menu.getItem(1).getText().equals("Monatsübersicht"));
		
		test("Überprüfen, ob der Eintrag 'Jahresübersicht' vorhanden ist",
				menu.getItem(2).getText().equals("Jahresübersicht"));
	}

	/**
	 * Überprüft, ob das Export-Menü richtig ist.
	 * 
	 * @param menu 
	 * 
	 * @throws GuiTestException Wird ausgelöst, wenn ein Test nicht korrekt
	 * ausgeführt werden konnte.
	 */
	private void testExportMenu(JMenu menu) throws GuiTestException {
		test("Ist der Name des Menü Export richtig?", menu.getText().equals("Export"));
		
		test("Überprüfen, ob der Eintrag 'SQL-Script' vorhanden ist",
				menu.getItem(0).getText().equals("SQL-Script"));
	}
	
	/**
	 * Führt die einzelnen Tests aus
	 */
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
			int count = 0;
			testFileMenu(menu.getMenu(count++));
			testDbMenu(menu.getMenu(count++));
			testExportMenu(menu.getMenu(count++));
			testReportMenu(menu.getMenu(count++));
			testLogMenu(menu.getMenu(count++));
			testHelpMenu(menu.getMenu(count++));
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Paramter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.menus.TestTopMainMenu"});
	}

}