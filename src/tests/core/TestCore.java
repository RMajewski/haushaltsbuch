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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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
	private List<TestSuiteData> _junit;
	
	/**
	 * Speichert die Fit-Tests
	 */
	private List<TestFitSuiteData> _fit;
	
	/**
	 * Speichert, ob die Konfigurations-Datei fehlerfrei eingelesen werden
	 * konnte.
	 */
	private boolean _configParse;

	/**
	 * Initalisiert die Test-Verwalung
	 */
	public TestCore() {
		// Listen initalisieren
		_gui = new ArrayList<TestSuiteData>();
		_junit = new ArrayList<TestSuiteData>();
		_fit = new ArrayList<TestFitSuiteData>();
		_configParse = false;
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
							if ((type == 1) || (type == 2))
								suite = new TestSuiteData();
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
							if ((type == 1) || (type == 2))
								test = new TestData();
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
								_junit.add(suite);
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
		return _configParse = true;
	}
	
	/**
	 * Überprüft, ob die Dateien existieren
	 */
	public void checkFileExists() {
		listCheckFiles(_gui);
		listCheckFiles(_junit);
		listCheckFitFiles(_fit);
	}
	
	/**
	 * Durchläuft die angegebene Liste und prüft, ob die Dateien existieren.
	 * 
	 * @param list Liste, die durchlaufen werden soll.
	 */
	private void listCheckFitFiles(List<TestFitSuiteData> list) {
		for (int i = 0; i < list.size(); i++)
			suiteCheckFiles(list.get(i), "fit");
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
		return new String(path + "." + name).replaceAll("\\.", "/") + "." + 
				extension;
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
		
	}
	
	/**
	 * Erstellt die HTML-Datei mit den Resultaten
	 */
	public void createResultHtml()  {
		
	}
}
