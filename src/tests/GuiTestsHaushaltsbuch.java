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

package tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Diese Klasse wird benutzt, um alle GUI-Tests auszuführen.
 * 
 * In der Version 0.2 wurde die Klasse für Fit-Tests erweitert. Außerdem werden
 * die Konsolen-Ausgaben und die Fehler-Ausgaben in Dateien gespeichert.
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.1
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
	 * Speichert die Start-Zeit für die Fit-Tests
	 */
	private long _fitStartTime;
	
	/**
	 * Speichert die Anzahl richtiger Behauptungen bei Fit-Tests
	 */
	private int _fitRight;
	
	/**
	 * Speichert die Anzahl falscher Behauptungen bei Fit-Tests
	 */
	private int _fitWrong;
	
	/**
	 * Speichert die Anzahl ignorierter Behauptungen bei Fit-Tests
	 */
	private int _fitIgnore;
	
	/**
	 * Speichert die Anzahl Fehler bei Fit-Tests
	 */
	private int _fitExceptions;
	
	/**
	 * Speichert das Verzeichnis für die Resultate
	 */
	private static final String RESULT_PATH = "result/fit";
	
	/**
	 * Speichert das Verzeichnis für die Bibliotheken
	 */
	private static final String PATH_BIB = "../";
	
	/**
	 * Speichert den Namen der Datei, in die die Ausgaben der Konsole
	 * gespeichert werden sollen.
	 */
	private static final String FILE_NAME_CONSOLE =  "result/gui_console.txt";
	
	/**
	 * Speichert den Namen der Datei, in die die Fehler gespeichert werden
	 * sollen.
	 */
	private static final String FILE_NAME_ERROR = "result/gui_error.txt";
	
	/**
	 * Initalisiert die Klasse
	 */
	public GuiTestsHaushaltsbuch() {
		// Initalisierungen für Tests
		_failCount = 0;
		_passCount = 0;
		_startTime = new Date().getTime();
		
		// Initalisierungen für Fit-Tests
		_fitRight = 0;
		_fitWrong = 0;
		_fitIgnore = 0;
		_fitExceptions = 0;
	}
	
	/**
	 * Ruft die übergebene Test-Klasse auf.
	 * 
	 * @param test Test-Klasse die aufgerufen werden soll
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
	 * Führt den angegebenen Test mit aus.
	 * 
	 * @param fit Datei, der den Fit-Test beinhaltet
	 */
	public void runFit(String fit) {
		// Überprüfen, ob die Datei existiert
		File f = new File(fit);
		if (!f.exists() || f.isDirectory()) {
			_failCount++;
			System.out.println("Die Fit-Datei: '" + fit +
					"' existiert nicht oder ist ein Verzeichnis");
			return;
		}
		
		// Überprüfen ob das Result-Verzeichnis existiert
		File r = new File(RESULT_PATH);
		if (!r.exists()) {
			// Verzeichnis anlegen
			r.mkdirs();
		}
		
		// Name der Result-Datei ermitteln und als Endung html
		StringBuilder resultFileName = new StringBuilder(f.getName());
		resultFileName.replace(resultFileName.indexOf("."), 
				resultFileName.length(), ".html");
		
		// Name der Unterverzeichnisse ermitteln
		StringBuilder resultPath = new StringBuilder();
		String[] paths = f.getParent().split("/");
		boolean append = false;
		for (int i = 0; i < paths.length; i++) {
			if (append) {
				resultPath.append(System.getProperty("file.separator"));
				resultPath.append(paths[i]);
			} else if (paths[i].equals("fit")) {
				append = true;
			}
		}
		
		// Result-Datei zusammensetzen
		StringBuilder result = new StringBuilder(RESULT_PATH);
		result.append(resultPath);
		result.append(System.getProperty("file.separator"));
		result.append(resultFileName);
		
		// Überprüfen, ob die Unterverzeichnisse existieren
		r = new File(new File(result.toString()).getParent());
		if (!r.exists())
			// Unterverzeichnisse anlegen
			r.mkdirs();
		
		// Ausführen
		try {
			long start = new Date().getTime();
			System.out.print(fit + ": ");
			Process p = Runtime.getRuntime().exec("java -cp " +
					"bin:" + PATH_BIB + "/fit.jar:" + PATH_BIB + "/jemmy.jar" +
					" -Dtesting=true fit.FileRunner " + fit + " " +
					result.toString());
			int exit = p.waitFor();
			long ms = new Date().getTime() - start;
			
			// Überpüfen ob Exit-Status 0 ist
			if (exit == 0) {
				_passCount++;
				System.out.print("wurde erfolgreich ausgeführt.");
			} else {
				_failCount++;
				System.out.print("weißt einen Fehler auf.");
			}
			
			// Dauer ausgeben
			System.out.println(" (Dauer des Tests: " + ms + " ms)");
			
			// Console in Datei speichern
			FileOutputStream out = new FileOutputStream(FILE_NAME_CONSOLE,
					true);
			byte[] buffer = new byte[0xFFFF];
			
			for (int len; (len = p.getInputStream().read(buffer)) != -1;)
				out.write(buffer, 0, len);
			out.close();

			// Console in Datei speichern
			out = new FileOutputStream(FILE_NAME_ERROR, true);
			buffer = new byte[0xFFFF];
			
			for (int len; (len = p.getErrorStream().read(buffer)) != -1;)
				out.write(buffer, 0, len);
			out.close();
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
		
//		// Menüs
//		tests.runTest(tests.tests.menus.TestTopMainMenu.class.getName());
//		
//		// Hauptfenster
//		tests.runTest(tests.tests.windows.TestWndMain.class.getName());
//		
//		// Unterfenster
//		tests.runTest(tests.tests.windows.internal.TestWndCategoryList.class.getName());
//		tests.runTest(tests.tests.windows.internal.TestWndSectionList.class.getName());
//		tests.runTest(tests.tests.windows.internal.TestWndMoneyList.class.getName());
		
		// Fit-Test der Dialoge
		tests.runFit("src/tests/fit/dialogs/DlgAbout.fit");
//		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScript.fit");
		tests.runFit("src/tests/fit/dialogs/DlgLicense.fit");
		tests.runFit("src/tests/fit/dialogs/DlgLogView.fit");
		tests.runFit("src/tests/fit/dialogs/DlgReport.fit");
		
		
		// Statistik ausgeben
		tests.statistics();
	}

}
