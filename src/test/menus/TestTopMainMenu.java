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

import windows.WndMain;

/**
 * Überprüft, ob alle Menü-Einträge vorhanden sind.
 * 
 * @author René Majewski
 */
public class TestTopMainMenu implements Scenario {
	/**
	 * Überprüft ob das Datei-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 */
	private int testFileMenu(JMenu menu) {
		// Name des Menü
		if (!menu.getText().equals("Datei"))
			return 1;
		
		// Überprüfen ob Beenden-Eintrag vorhanden ist
		if (!menu.getItem(0).getText().equals("Beenden"))
			return 1;
		
		// Standard-Rückgabe-Wert
		return 0;
	}
	
	/**
	 * Überprüft ob das Datenbank-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 */
	private int testDbMenu(JMenu menu) {
		// Name des Menüs
		if (!menu.getText().equals("Datenbank"))
			return 1;
		
		// Kategorie
		if (!menu.getItem(0).getText().equals("Kategorien"))
			return 1;
		
		// Geschäfte
		if (!menu.getItem(1).getText().equals("Geschäfte"))
			return 1;
		
		// Einnahmen und Ausgaben
		if (!menu.getItem(2).getText().equals("Einnahmen und Ausgaben"))
			return 1;
		
		// Standard-Rückgabe-Wert
		return 0;
	}
	
	/**
	 * Überprüft ob das Log-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 */
	private int testLogMenu(JMenu menu) {
		// Name des Menüs
		if (!menu.getText().equals("Log"))
			return 1;
		
		// Kategorie
		if (!menu.getItem(0).getText().equals("Anzeigen..."))
			return 1;
		
		// Standard-Rückgabe-Wert
		return 0;
	}
	
	/**
	 * Überprüft ob das Hilfe-Menü richtig ist.
	 * 
	 * @return 1, wenn ein Fehler aufgetreten ist. 0, wenn kein Fehler
	 * aufgetreten ist.
	 */
	private int testHelpMenu(JMenu menu) {
		// Name des Menüs
		if (!menu.getText().equals("Hilfe"))
			return 1;
		
		// Kategorie
		if (!menu.getItem(0).getText().equals("Lizenz..."))
			return 1;
		
		// Kategorie
		if (!menu.getItem(2).getText().equals("Über..."))
			return 1;
		
		// Standard-Rückgabe-Wert
		return 0;
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
			
			// Datei-Menü
			if (testFileMenu(menu.getMenu(0)) != 0)
				return 1;
			
			// Datenbank-Menü
			if (testDbMenu(menu.getMenu(1)) != 0)
				return 1;
			
			// Log-Menü
			if (testLogMenu(menu.getMenu(2)) != 0)
				return 1;
			
			// Hilfe-Menü
			if (testHelpMenu(menu.getMenu(3)) != 0)
				return 1;
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
