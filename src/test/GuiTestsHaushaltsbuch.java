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

package test;

import java.io.IOException;
import java.util.Date;

/**
 * Diese Klasse wird benutzt, um alle Tests auszuführen.
 * 
 * Dies ist notwendig, da verschiedene Test-Systeme benutzt werden.
 * 
 * @author René Majewski
 *
 */
public class GuiTestsHaushaltsbuch {
	/**
	 * Speichert die Anzahl fehlgeschlagenen Tests
	 */
	private int _failCount;
	
	/**
	 * Speichert die Anzahl der erfolgreichen Tests
	 */
	private int _passCount;
	
	/**
	 * Speichert die Start-Zeit
	 */
	private long _startTime;
	
	/**
	 * Initalisiert die Klasse
	 */
	public GuiTestsHaushaltsbuch() {
		_failCount = 0;
		_passCount = 0;
		_startTime = new Date().getTime();
	}
	
	/**
	 * Ruft die übergebene Test-Klasse auf.
	 * 
	 * @param test Test-Klasse die aufgerufen werden soll
	 * 
	 * @throws IOException
	 *  
	 * @throws InterruptedException 
	 */
	public void runTest(String test) {
		try {
			long start = new Date().getTime();
			Process p = Runtime.getRuntime().exec("java -cp " +
					System.getProperty("java.class.path")+
					" -Dtesting=true " + test);
			int exit = p.waitFor();
			
			// Ausgabe wie der Test verlaufen ist
			long ms = new Date().getTime() - start;
			System.out.print(test + ": ");
			if (exit == 0) {
				_passCount++;
				System.out.print("wurde erfolgreich ausgeführt");
			} else {
				_failCount++;
				System.out.print("weißt Fehler auf");
			}
			System.out.print(" (Dauer des Tests: ");
			System.out.print(String.valueOf(ms));
			System.out.println(" ms)");
		} catch (IOException e) {
			e.printStackTrace();
			_failCount++;
		} catch (InterruptedException e) {
			e.printStackTrace();
			_failCount++;
		}
	}
	
	/**
	 * Gibt die Statistik aus
	 */
	public void statistics() {
		// Zeit stoppen
		long stop = new Date().getTime();
		
		System.out.println();
		
		// Gesamt-Anzahl
		System.out.print("Anzahl Tests: ");
		System.out.print(String.valueOf(_failCount + _passCount));
		
		// Anzahl fehlerfreier Tests
		System.out.print("\t\tAnzahl Erfolge: ");
		System.out.print(String.valueOf(_passCount));
		
		// Anzahl Fehler
		System.out.print("\t\tAnzahl Fehler: ");
		System.out.println(String.valueOf(_failCount));
		
		// Zeit ausgeben
		long ms = stop - _startTime;
		System.out.print("Dauer aller Tests (in Millisekunden): ");
		System.out.println(String.valueOf(ms));
	}
	
	/**
	 * Ruft die einzelnen Tests auf
	 * 
	 * @param args Übergebene Kommandozeilen-Parameter
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		// Test-Klasse vorbereiten
		GuiTestsHaushaltsbuch tests = new GuiTestsHaushaltsbuch();
		
		// Dialoge
		tests.runTest(test.dialogs.TestDlgAbout.class.getName());
		tests.runTest(test.dialogs.TestDlgLicense.class.getName());
		tests.runTest(test.dialogs.TestDlgLog.class.getName());
		
		// Menüs
		tests.runTest(test.menus.TestTopMainMenu.class.getName());
		
		// Hauptfenster
		tests.runTest(test.windows.TestWndMain.class.getName());
		
		// Statistik ausgeben
		tests.statistics();
	}

}
