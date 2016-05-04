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

package tests.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Implementiert die Verwaltung der Tests.
 * 
 * Es wird die Konfigurations-Datei eingelesen. Wenn dabei kein Fehler
 * aufgetreten ist, so werden die einzelnen Tests ausgeführt. Zum Schluss
 * wird eine HTML-Seite geniert, in dem die Ergebnisse der einzelnen Tests
 * zusammen gefasst sind.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestCore {
	/**
	 * Speichert die GUI-Tests
	 */
	private List<TestSuiteData> _gui;
	
	/**
	 * Speichert die junit-Tests
	 */
	private List<TestJunitSuiteData> _junit;
	
	/**
	 * Speichert die Fit-Tests
	 */
	private List<TestFitSuiteData> _fit;
	
	/**
	 * Speichert das Result-Verzeichnis für die Fit-Tests
	 */
	private String _fitResult;
	
	/**
	 * Speichert das Verzeichnis für die Ergebnisse
	 */
	private String _resultPath;
	
	/**
	 * Speichert das Verzeichnis für den Source-Code
	 */
	private String _srcPath;
	
	/**
	 * Speichert das Verzeichnis zu den Bibliotheken
	 */
	private String _bibPath;

	/**
	 * Initalisiert die Test-Verwalung
	 */
	public TestCore() {
		// Listen initalisieren
		_gui = new ArrayList<TestSuiteData>();
		_junit = new ArrayList<TestJunitSuiteData>();
		_fit = new ArrayList<TestFitSuiteData>();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		DecimalFormat df = new DecimalFormat("00");
		_fitResult = df.format(gc.get(GregorianCalendar.YEAR)) +
				df.format(gc.get(GregorianCalendar.MONTH) + 1) +
				df.format(gc.get(GregorianCalendar.DAY_OF_MONTH)) +
				df.format(gc.get(GregorianCalendar.HOUR_OF_DAY)) +
				df.format(gc.get(GregorianCalendar.MINUTE)) +
				df.format(gc.get(GregorianCalendar.SECOND));
	}

	/**
	 * Liest die angegeben Konfiguration-Datei ein.
	 * 
	 * @param config Name der Konfigurations-Datei
	 * 
	 * @return Konnte die Konfigurations-Datei richtig eingelesen werden?
	 */
	public boolean readConfig(String config) throws XMLStreamException{
		// Überprüfen, ob die config-Datei existiert
		InputStream stream = getClass().getClassLoader().getResourceAsStream(config);
		if (stream == null) {
			System.out.println("Konfigurations-Datei (" + config + 
					") existiert nicht.");
			return false;
		}
		
		// XMl-Factory
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser = factory.createXMLStreamReader(stream);
		
		// Daten vorbereiten
		TestData test = new TestData();
		TestSuiteData suite = new TestSuiteData();
		int type = -1;
		String str = new String();
		
		// XML-Dokument einlesen
		while (parser.hasNext()) {
			switch (parser.getEventType()) {
				// Dokument ist zu Ende
				case XMLStreamConstants.END_DOCUMENT:
					parser.close();
					break;
					
				// Start eines neuen Elementes
				case XMLStreamConstants.START_ELEMENT:
					// Welches Element?
					switch (parser.getLocalName()) {
						// Konfiguration
						case "config":
							type = 0;
							break;
							
						// GUI-Tests
						case "guiTests":
							type = 1;
							break;
							
						// junit-Tests
						case "junitTests":
							type = 2;
							break;
							
						// Fit-Tests
						case "fitTests":
							type = 3;
							break;
							
						// Neue Test-Suite
						case "testSuite":
							if (type == 1)
								suite = new TestSuiteData();
							else if (type == 2)
								suite = new TestJunitSuiteData();
							else if (type == 3)
								suite = new TestFitSuiteData();
							break;
							
						// Name
						case "name":
							break;
							
						// Package
						case "package":
							break;
							
						// Neuer Test
						case "test":
							if (type == 1)
								test = new TestData();
							else if (type == 2)
								test = new TestJunitData();
							else if (type == 3)
								test = new TestFitData();
							break;
					}
					break;
					
				// Ende eines Elementes
				case XMLStreamConstants.END_ELEMENT:
					switch(parser.getLocalName()) {
						// Suite
						case "testSuite":
							if (type == 1)
								_gui.add(suite);
							else if (type == 2)
								_junit.add((TestJunitSuiteData)suite);
							else if (type == 3)
								_fit.add((TestFitSuiteData)suite);
							break;
							
						// Name
						case "name":
							suite.setName(str);
							break;
							
						// Package
						case "package":
							suite.setPackage(str);
							break;
							
						// Test
						case "test":
							test.setName(str);
							suite.addTest(test);
							break;
							
						// Result-Pfad
						case "resultPath":
							_resultPath = str;
							break;
							
						// Source-Pfad
						case "srcPath":
							_srcPath = str;
							break;
							
						// Bibliotheks-Pfad
						case "bibPath":
							_bibPath = str;
							break;
					}
					break;
					
				// Zeichen
				case XMLStreamConstants.CHARACTERS:
					str = parser.getText();
					break;
			}
			
			// Nextes Element
			parser.next();
		}
		
		// Wurde die Konfigurations-Datei richtig gelesen?
		return true;
	}
	
	/**
	 * Überprüft, ob die Dateien existieren
	 */
	public void checkFileExists() {
		listCheckFiles(_gui);
		listCheckJunitFiles();
		listCheckFitFiles();
	}
	
	/**
	 * Durchläuft die angegebene Liste und prüft, ob die Dateien existieren.
	 */
	private void listCheckFitFiles() {
		for (int i = 0; i < _fit.size(); i++)
			suiteCheckFiles(_fit.get(i), "fit");
	}
	
	/**
	 * Durchläuft die junit-Suuite-Liste und prüft, ob die Dateien existieren.
	 */
	private void listCheckJunitFiles() {
		for (int i = 0; i < _junit.size(); i++)
			suiteCheckFiles(_junit.get(i), "java");
		
	}

	/**
	 * Durchläuft die angegebene Liste und prüft, ob die Dateien existieren
	 * 
	 * @param list Liste, die durchlaufen werden soll.
	 */
	private void listCheckFiles(List<TestSuiteData> list) {
		for (int i = 0; i < list.size(); i++)
			suiteCheckFiles(list.get(i), "java");
	}
	
	/**
	 * Derchläuft die angegebene Test-Suite und prüft, ob die Dateien existieren.
	 * 
	 * @param suite Test-Suite, die durchlaufen werden soll.
	 * 
	 * @param extension Datei-Endung der Datei
	 */
	private void suiteCheckFiles(TestSuiteData suite, String extension) {
		suite.setExists(fileExists(suite.getPackage().replaceAll("\\.", "/")));
		String path = suite.getPackage();
		for (int i = 0; i < suite.testCount(); i++)
				suite.getTest(i).setExists(fileExists(composeFileName(path,
						suite.getTest(i).getName(), extension)));
	}
	
	/**
	 * Setzt den vollständigen Datei-Namen mit Path zusammen.
	 * 
	 * @param path Verzeichnis, in dem sich die Datei befindet
	 * 
	 * @param file Datei-Name
	 * 
	 * @param extension Datei-Endung
	 * 
	 * @return Verzeichnis und Datei als eine Zeichenkette
	 */
	private String composeFileName(String path, String name, String extension) {
		return new String(path + "." + name).replaceAll("\\.", "/") + 
				"." + extension;
	}
	
	/**
	 * Überprüft, ob die angegebene Datei existiert.
	 * 
	 * @param file Name der Datei
	 * 
	 * @return Existiert die Datei?
	 */
	private boolean fileExists(String file) {
		File f = new File(file);
		if (f.exists())
			return true;
		return false;
	}
	
	/**
	 * Führt die einzelnen Tests aus
	 */
	public void run() {
		runGui();
		runJunit();
		runFit();
	}
	
	/**
	 * Führt die einzelnen GUI-Tests aus
	 */
	private void runGui() {
		for (int suite = 0; suite < _gui.size(); suite++) {
			// Test-Suite Name
			System.out.println(_gui.get(suite).getName());
			
			// gui-Tests ausführen
			for (int test = 0; test < _gui.get(suite).testCount(); test++) {
				String name = _gui.get(suite).getPackage() + "." +
						_gui.get(suite).getTest(test).getName();
				try {
					_gui.get(suite).getTest(test).setStart(
							new Date().getTime());

					System.out.print(name + ": ");
					Process p = Runtime.getRuntime().exec("java -cp " +
							System.getProperty("java.class.path")+
							" -Dtesting=true " + name);
					int exit = p.waitFor();
					
					// Ausgabe wie der Test verlaufen ist
					_gui.get(suite).getTest(test).setEnd(new Date().getTime());
					_gui.get(suite).getTest(test).setExitStatus(exit);
					if (exit == 0)
						System.out.print("wurde erfolgreich ausgeführt");
					else
						System.out.print("weißt Fehler auf");
					
					System.out.print(" (Dauer des Tests: ");
					System.out.print(String.valueOf(
							_gui.get(suite).getTest(test).getDurationTime()));
					System.out.println(" ms)");
					
					// Console-Ausgabe und Error-Ausgabe speichern
					_gui.get(suite).getTest(test).setError(p.getErrorStream());
					_gui.get(suite).getTest(test).setIn(p.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
					_gui.get(suite).getTest(test).setExitStatus(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					_gui.get(suite).getTest(test).setExitStatus(100);
				}
				
			}
		}
	}
	
	/**
	 * Führt die einzelnen junit-Tests aus
	 */
	private void runJunit() {
		for (int suite = 0; suite < _junit.size(); suite++) {
			// Test-Suite Name
			System.out.println(_junit.get(suite).getName());
			
			// juni-Tests ausführen
			for (int test = 0; test < _junit.get(suite).testCount(); test++) {
				String name = _junit.get(suite).getPackage() + "." +
						_junit.get(suite).getTest(test).getName();
				
				try {
					_junit.get(suite).getTest(test).setStart(
							new Date().getTime());

					System.out.print(name + ": ");
					Process p = Runtime.getRuntime().exec("java -cp " +
							System.getProperty("java.class.path")+
							" -Dtesting=true org.junit.runner.JUnitCore " +
							name);
					
					int exit = p.waitFor();
					_junit.get(suite).getTest(test).setExitStatus(exit);
					
					// Ausgabe wie der Test verlaufen ist
					_junit.get(suite).getTest(test).setEnd(
							new Date().getTime());

					if (exit == 0)
						System.out.print("wurde erfolgreich ausgeführt");
					else
						System.out.print("weißt Fehler auf");

					System.out.print(" (Dauer des Tests: ");
					System.out.print(String.valueOf(
							_junit.get(suite).getTest(test).getDurationTime()));
					System.out.println(" ms)");
					
					// Console-Ausgabe und Error-Ausgabe speichern
					_junit.get(suite).getTest(test).setError(p.getErrorStream());
					_junit.get(suite).getTest(test).setIn(p.getInputStream());

					InputStream is = p.getInputStream();
					is.mark(is.available());
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String line;
					while ((line = br.readLine()) != null) {
						if (line.indexOf("OK (") > -1) {
							String ok = new String("OK (");
							
							String tmp = line.substring(ok.length(),
									line.indexOf(" tests)"));
							_junit.get(suite).getTest(test).setOk(
									Integer.valueOf(tmp));
						} else if (line.indexOf("Tests run") > -1) {
							String ok = new String("Tests run: ");
							String fail = new String(",  Failures: ");
							int indexOk = ok.length();
							int indexFail = line.indexOf(fail);

							_junit.get(suite).getTest(test).setOk(
									Integer.valueOf(line.substring(indexOk, 
											indexFail)));
							
							indexFail += fail.length();
							_junit.get(suite).getTest(test).setFail(
									Integer.valueOf(line.substring(indexFail)));
						}
					}
					is.reset();
				} catch (IOException e) {
					e.printStackTrace();
					_junit.get(suite).getTest(test).setExitStatus(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					_junit.get(suite).getTest(test).setExitStatus(100);
				}
			} // for über alle Tests
			
			System.out.println();
		} // for über alle Test-Suites
	}
	
	/**
	 * Für einzelnen Fit-Tests aus
	 */
	private void runFit() {
		for (int suite = 0; suite < _fit.size(); suite++) {
			// Test-Suite Name
			System.out.println(_junit.get(suite).getName());
			
			for (int test = 0; test < _fit.get(suite).testCount(); test++) {
				// Name der Fit-Datei
				String fit = composeFileName(_srcPath + "." + 
						_fit.get(suite).getPackage(), 
						_fit.get(suite).getTest(test).getName(), "fit");
				
				// Überprüfen, ob die Datei existiert
				File f = new File(fit);
				if (!f.exists() || f.isDirectory()) {
					_fit.get(suite).getTest(test).setExists(false);
					_fit.get(suite).getTest(test).setExitStatus(100);
					System.out.println("Die Fit-Datei: '" + fit +
							"' existiert nicht oder ist ein Verzeichnis");
					continue;
				}
				_fit.get(suite).getTest(test).setExists(true);
				
				// Überprüfen ob das Result-Verzeichnis existiert
				String resultPath = _resultPath + File.separator + _fitResult + 
						File.separator + 
						_fit.get(suite).getPackage().replaceAll("\\.", 
								File.separator);
				File r = new File(resultPath);
				if (!r.exists()) {
					// Verzeichnis anlegen
					r.mkdirs();
				}
				
				// Name der Result-Datei ermitteln und als Endung html
				String resultFileName = resultPath + File.separator + 
						_fit.get(suite).getTest(test).getName() + ".html";
				
				// Ausführen
				try {
					_fit.get(suite).getTest(test).setStart(new Date().getTime());

					System.out.print(fit + ": ");
					String exec = "java -cp " +
							"bin:" + _bibPath + "/fit.jar:" + _bibPath + "/jemmy.jar:" +
							_bibPath + "/sqlite-jdbc-3.8.11.2.jar -Dtesting=true "
							+ "fit.FileRunner " + fit + " " + resultFileName;
					Process p = Runtime.getRuntime().exec(exec);
					int exit = p.waitFor();
					
					// Endzeit ermitteln
					_fit.get(suite).getTest(test).setEnd(new Date().getTime());
					
					// Überpüfen ob Exit-Status 0 ist
					_fit.get(suite).getTest(test).setExitStatus(exit);
					if (exit == 0)
						System.out.print("wurde erfolgreich ausgeführt.");
					else
						System.out.print("weißt einen Fehler auf.");
					
					
					// Dauer ausgeben
					System.out.println(" (Dauer des Tests: " + 
							_fit.get(suite).getTest(test).getDurationTime() 
							+ " ms)");
					
					// Console in Datei speichern
					_fit.get(suite).getTest(test).setIn(p.getInputStream());

					// Error auslesen
					InputStream is = p.getErrorStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String line;
					while ((line = br.readLine()) != null) {
						if ((line.indexOf("right") > -1) &&
								(line.indexOf("wrong") > -1)) {
							String[] tmp = line.split(" ");
							_fit.get(suite).getTest(test).setRight(
									Integer.valueOf(tmp[0]));
							_fit.get(suite).getTest(test).setWrong(
									Integer.valueOf(tmp[2]));
							_fit.get(suite).getTest(test).setIgnore(
									Integer.valueOf(tmp[4]));
							_fit.get(suite).getTest(test).setException(
									Integer.valueOf(tmp[6]));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					_fit.get(suite).getTest(test).setExitStatus(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					_fit.get(suite).getTest(test).setExitStatus(100);
				}
				
			} // for über die Fit-Tests
			
		} // for über die Fit-Suites
	}
	
	/**
	 * Erstellt die HTML-Datei mit den Resultaten
	 */
	public void createResultHtml()  {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		
		
		
		String htmlFile = _resultPath + File.separator + "Ergebnisse_" +
				_fitResult + ".html";
		try {
			HtmlOut html = new HtmlOut(htmlFile);
			html.htmlHead();
			
			html.guiHead();
			htmlGui(_gui, html);
			
			html.junitHead();
			htmlJunit(html);
			
			html.fitHead();
			htmlFit(html);
			
			html.htmlEnd();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt die HTML-Ausgabe für die Fit-Tests.
	 * 
	 * @param html Klasse, die die HTML-Ausgabe erzeugt.
	 * 
	 * @throws IOException
	 */
	private void htmlJunit(HtmlOut html) throws IOException {
		for (int suite = 0; suite < _junit.size(); suite++) {
			int ok = 0;
			int fail = 0;
			long time = 0;
			
			for (int test = 0; test < _junit.get(suite).testCount(); test++) {
				int okTest = _junit.get(suite).getTest(test).getOk();
				int failTest = _junit.get(suite).getTest(test).getFail();
				long timeTest = _junit.get(suite).getTest(test).getDurationTime();
				
				// Ausgabe des Tests
				html.test( _junit.get(suite).getTest(test).getName(),
						okTest, failTest, timeTest, 
						_junit.get(suite).getTest(test).getIn(),
						_junit.get(suite).getTest(test).getError());
				
				// Fehler bzw. Richtig für Test-Suite erhöhen
				ok += okTest;
				fail += failTest;
				time += timeTest;
			} // for über alle Tests
			
			// Ausgabe für die Test-Suite
			html.suiteHtml(_junit.get(suite).getName(),
					_junit.get(suite).getPackage(), ok, fail, time);
		} // for über alle Test-Suits
	}

	/**
	 * Erstellt die HTML-Ausgabe für die Fit-Tests.
	 * 
	 * @param html Klasse, die die HTML-Ausgabe erzeugt.
	 * 
	 * @throws IOException
	 */
	private void htmlFit(HtmlOut html) throws IOException {
		for (int suite = 0; suite < _fit.size(); suite++) {
			int right = 0;
			int wrong = 0;
			int ignore = 0;
			int exception = 0;
			long time = 0;
			
			for (int test = 0; test < _fit.get(suite).testCount(); test++) {
				int rightTest = _fit.get(suite).getTest(test).getRight();
				int wrongTest = _fit.get(suite).getTest(test).getWrong();
				int ignoreTest = _fit.get(suite).getTest(test).getIgnore();
				int exceptionTest = _fit.get(suite).getTest(test).getException();
				long timeTest = _fit.get(suite).getTest(test).getDurationTime();
				
				// Ausgabe des Tests
				html.test( _fit.get(suite).getTest(test).getName(),
						rightTest, wrongTest, ignoreTest, exceptionTest,
						timeTest, _fit.get(suite).getTest(test).getIn(),
						_fit.get(suite).getTest(test).getError(), true,
						_fitResult + File.separator + 
						_fit.get(suite).getPackage().replaceAll("\\.", 
								File.separator));
				
				// Fehler bzw. Richtig für Test-Suite erhöhen
				right += rightTest;
				wrong += wrongTest;
				ignore += ignoreTest;
				exception += exceptionTest;
				time += timeTest;
			} // for über alle Tests
			
			// Ausgabe für die Test-Suite
			html.suiteHtml(_fit.get(suite).getName(),
					_fit.get(suite).getPackage(), right, wrong, ignore, 
						exception, time);
		} // for über alle Test-Suits
	}

	/**
	 * Erstellt die HTML-Ausgabe für die GUI-Tests und die junit-Tests.
	 * 
	 * @param list Liste der Test-Suites, wo die HTML-Ausgabe erstellt werden
	 * soll.
	 * 
	 * @param html Klasse, die die HTML-Ausgabe erzeugt.
	 * 
	 * @throws IOException
	 */
	private void htmlGui(List<TestSuiteData> list, HtmlOut html) 
			throws IOException {
		for (int suite = 0; suite < list.size(); suite++) {
			int right = 0;
			int exception = 0;
			long time = 0;
			
			for (int test = 0; test < list.get(suite).testCount(); test++) {
				int rightTest = 0;
				int exceptionTest = 0;
				long timeTest = list.get(suite).getTest(test).getDurationTime();
				
				// Überprüfen, ob der Test positiv abgelaufen ist.
				if (list.get(suite).getTest(test).getExitStatus() == 0)
					rightTest++;
				else 
					exceptionTest++;
				
				// Ausgabe des Tests
				html.test( list.get(suite).getTest(test).getName(),
						rightTest, exceptionTest, time,
						list.get(suite).getTest(test).getIn(),
						list.get(suite).getTest(test).getError());
				
				// Fehler bzw. Richtig für Test-Suite erhöhen
				right += rightTest;
				exception += exceptionTest;
				time += timeTest;
			} // for über alle Tests
			
			// Ausgabe für die Test-Suite
			html.suiteHtml(list.get(suite).getName(),
					list.get(suite).getPackage(), right, exception, time);
		} // for über alle Test-Suits
		
	}
}
