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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import haushaltsbuch.helper.HelperCalendar;

/**
 * Diese Klasse wird benutzt, um alle GUI-Tests auszuführen.
 * 
 * In der Version 0.2 wurde die Klasse für Fit-Tests erweitert. Außerdem werden
 * die Konsolen-Ausgaben und die Fehler-Ausgaben in Dateien gespeichert.
 * 
 * In der Version 0.3 wurde die Klasse für junit-Tests erweitert.
 * 
 * @author René Majewski
 * 
 * @version 0.3
 * @since 0.1
 */
public class TestsHaushaltsbuch {
	/**
	 * Speichert die Anzahl fehlgeschlagenen Tests
	 */
	private int _failCount;
	
	/**
	 * Speichert die Anzahl der erfolgreichen Tests
	 */
	private int _passCount;
	
	/**
	 * Speichert die Zeit, die die GUI-Tests gebraucht haben
	 */
	private long _timeGui;
	
	/**
	 * Speichert die Zeit, die die Fit-Tests gebraucht haben
	 */
	private long _timeFit;
	
	/**
	 * Speichert die Zeit, die die junit-Tests gebraucht haben
	 */
	private long _timeJunit;
	
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
	public TestsHaushaltsbuch() {
		// Initalisierungen für Tests
		_failCount = 0;
		_passCount = 0;
		_timeGui = 0;
		
		// Initalisierungen für Fit-Tests
		_fitRight = 0;
		_fitWrong = 0;
		_fitIgnore = 0;
		_fitExceptions = 0;
		_timeFit = 0;
		
		// Initalisierungen für junit-Test
		_timeJunit = 0;
	}
	
	/**
	 * Schreibt den angegeben InputStream in die angegebene Datei.
	 * 
	 * @param file Name der Datei
	 * 
	 * @param is InputStream der in die Datei geschrieben werden soll.
	 * 
	 * @param test Name des Tests
	 * 
	 * @param start Start-Zeitpunkt des Tests
	 * @throws IOException 
	 */
	private void appendFile(String file, InputStream is, String test,
			long start) throws IOException {
		// Datei öffnen
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		// 2 Zeilen abstand zum vorherigen Eintrag
		bw.newLine();
		bw.newLine();
		
		// Test-Name und Start schreiben
		bw.write(test);
		bw.write(" (Start: ");
		bw.write(HelperCalendar.datetimeToString(start));
		bw.write(")");
		bw.newLine();
		
		// Daten aus InputStream schreiben
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		while((line = br.readLine()) != null) {
			bw.write(line);
			bw.newLine();
		}
		
		// Datei schließen
		br.close();
		bw.close();
		fw.close();
	}
	
	/**
	 * Ruft die übergebene Test-Klasse auf.
	 * 
	 * @param test Test-Klasse die aufgerufen werden soll
	 */
	public void runTest(String test) {
		try {
			long start = new Date().getTime();
			System.out.print(test + ": ");
			Process p = Runtime.getRuntime().exec("java -cp " +
					System.getProperty("java.class.path")+
					" -Dtesting=true " + test);
			int exit = p.waitFor();
			
			// Ausgabe wie der Test verlaufen ist
			long ms = new Date().getTime() - start;
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
			
			// Vergangene Zeit neu berechnen
			_timeGui += ms;
			
			// Console-Ausgabe und Error-Ausgabe speichern
			appendFile(FILE_NAME_CONSOLE, p.getInputStream(), test, start);
			appendFile(FILE_NAME_ERROR, p.getErrorStream(), test, start);
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
					"bin:" + PATH_BIB + "/fit.jar:" + PATH_BIB + "/jemmy.jar:" +
					PATH_BIB + "sqlite-jdbc-3.8.11.2.jar -Dtesting=true "
					+ "fit.FileRunner " + fit + " " + result.toString());
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
			_timeFit += ms;
			
			// Console in Datei speichern
			appendFile(FILE_NAME_CONSOLE, p.getInputStream(), fit, start);

			// Error auslesen
			InputStream is = p.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				if ((line.indexOf("right") > -1) &&
						(line.indexOf("wrong") > -1)) {
					String[] tmp = line.split(" ");
					_fitRight += Integer.valueOf(tmp[0]);
					_fitWrong += Integer.valueOf(tmp[2]);
					_fitIgnore += Integer.valueOf(tmp[4]);
					_fitExceptions += Integer.valueOf(tmp[6]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			_failCount++;
		} catch (InterruptedException e) {
			e.printStackTrace();
			_failCount++;
		}
	}
	
	/**
	 * Führt den angegebenen junit-Test aus.
	 * 
	 * @param test Name des junit-Tests
	 */
	public void runJunit(String test) {
		try {
			long start = new Date().getTime();
			System.out.print(test + ": ");
			Process p = Runtime.getRuntime().exec("java -cp " +
					System.getProperty("java.class.path")+
					" -Dtesting=true org.junit.runner.JUnitCore " + test);
			int exit = p.waitFor();
			
			// Ausgabe wie der Test verlaufen ist
			long ms = new Date().getTime() - start;
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
			
			// Vergangene Zeit neu berechnen
			_timeJunit += ms;
			
			// Console-Ausgabe und Error-Ausgabe speichern
			appendFile(FILE_NAME_CONSOLE, p.getInputStream(), test, start);
			appendFile(FILE_NAME_ERROR, p.getErrorStream(), test, start);
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
		System.out.println();
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
		
		// Behauptungen in Fit
		System.out.println();
		System.out.print("Fit-Behauptungen\trichtig: ");
		System.out.print(String.valueOf(_fitRight));
		System.out.print("\tfehlerhaft: ");
		System.out.print(String.valueOf(_fitWrong));
		System.out.print("\tignoriert: ");
		System.out.print(String.valueOf(_fitIgnore));
		System.out.print("\tFehler: ");
		System.out.println(String.valueOf(_fitExceptions));
		
		// Zeit ausgeben
		System.out.println();
		System.out.print("Dauer aller junit-Tests (in Millisekunden): ");
		System.out.println(String.valueOf(_timeJunit));
		System.out.print("Dauer aller GUI-Tests (in Millisekunden): ");
		System.out.println(String.valueOf(_timeGui));
		System.out.print("Dauer aller Fit-Tests (in Millisekunden): ");
		System.out.println(String.valueOf(_timeFit));
		System.out.print("Dauer aller Tests (in Millisekunden): ");
		System.out.println(String.valueOf(_timeJunit + _timeGui + _timeFit));
	}
	
	/**
	 * Ruft die einzelnen Tests auf
	 * 
	 * @param args Übergebene Kommandozeilen-Parameter
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		// Test-Klasse vorbereiten
		TestsHaushaltsbuch tests = new TestsHaushaltsbuch();
		
		// junit-Tests "comparators"
		tests.runJunit("tests.tests.comparators.TestCompDouble");
		tests.runJunit("tests.tests.comparators.TestCompId");
		tests.runJunit("tests.tests.comparators.TestCompInt");
		
		// junit-Tests "datas"
		tests.runJunit("tests.tests.datas.TestData");
		tests.runJunit("tests.tests.datas.TestIdNameData");
		tests.runJunit("tests.tests.datas.TestLogData");
		tests.runJunit("tests.tests.datas.TestMoneyData");
		tests.runJunit("tests.tests.datas.TestMoneyDetailsData");
		tests.runJunit("tests.tests.datas.TestReportData");
		tests.runJunit("tests.tests.datas.TestReportMonthData");
		tests.runJunit("tests.tests.datas.TestReportWeekData");
		tests.runJunit("tests.tests.datas.TestReportYearData");

		// junit-Tests "db" und "db.query"
		tests.runJunit("tests.tests.db.TestDbController");
		tests.runJunit("tests.tests.db.query.TestCategory");
		tests.runJunit("tests.tests.db.query.TestMoney");
		tests.runJunit("tests.tests.db.query.TestMoneyDetails");
		tests.runJunit("tests.tests.db.query.TestQueries");
		tests.runJunit("tests.tests.db.query.TestQuery");
		tests.runJunit("tests.tests.db.query.TestQueryInterface");
		tests.runJunit("tests.tests.db.query.TestSection");
		
		// junit-Tests "helper"
		tests.runJunit("tests.tests.helper.TestHelperCalendar");

		// junit-Tests "menus"
		tests.runJunit("tests.tests.menus.TestPopupMoneyList");
		tests.runJunit("tests.tests.menus.TestPopupStandardList");
		tests.runJunit("tests.tests.menus.TestTopMainMenu");

		// junit-Tests "renderer"
		tests.runJunit("tests.tests.renderer.TestLogViewListRenderer");
		
		// junit-Tests "tables.models"
		tests.runJunit("tests.tests.tables.models.TestIdNameListModel");
		tests.runJunit("tests.tests.tables.models.TestMoneyDetailsListModel");
		tests.runJunit("tests.tests.tables.models.TestMoneyListModel");
		tests.runJunit("tests.tests.tables.models.TestReportMonthModel");
		tests.runJunit("tests.tests.tables.models.TestReportWeekModel");
		tests.runJunit("tests.tests.tables.models.TestReportYearModel");

		
		// Fit-Test der Dialoge
		tests.runFit("src/tests/fit/dialogs/DlgAbout.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptCancel.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptExport.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptNoPreferences.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceTableCategory.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceTableSection.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceTableMoney.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceTableMoneyDetails.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceDataCategory.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceDataSection.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceDataMoney.fit");
		tests.runFit("src/tests/fit/dialogs/DlgExportSqlScriptPreferenceDataMoneyDetails.fit");
		tests.runFit("src/tests/fit/dialogs/DlgLicense.fit");
		tests.runFit("src/tests/fit/dialogs/DlgLogView.fit");
		tests.runFit("src/tests/fit/dialogs/DlgReport.fit");
		
		// Fit-Tests der Hauptfenster
		tests.runFit("src/tests/fit/windows/WndMain.fit");
		
		// Fit-Tests der Unterfenster
		tests.runFit("src/tests/fit/windows/internal/WndCategoryListInsert.fit");
		tests.runFit("src/tests/fit/windows/internal/WndCategoryListChange.fit");
		tests.runFit("src/tests/fit/windows/internal/WndCategoryListDelete.fit");
		tests.runFit("src/tests/fit/windows/internal/WndSectionInsert.fit");
		tests.runFit("src/tests/fit/windows/internal/WndSectionListChange.fit");
		tests.runFit("src/tests/fit/windows/internal/WndSectionListDelete.fit");
		tests.runFit("src/tests/fit/windows/internal/WndMoneyListInsert.fit");
		tests.runFit("src/tests/fit/windows/internal/WndMoneyListChange.fit");
		tests.runFit("src/tests/fit/windows/internal/WndMoneyListDelete.fit");
		tests.runFit("src/tests/fit/windows/internal/WndMoneyListDetails.fit");
		
		// Statistik ausgeben
		tests.statistics();
	}

}
