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

import javax.xml.stream.XMLStreamException;

import tests.core.TestCore;

/**
 * Diese Klasse wird benutzt, um alle GUI-Tests auszuführen.
 * 
 * In der Version 0.2 wurde die Klasse für Fit-Tests erweitert. Außerdem werden
 * die Konsolen-Ausgaben und die Fehler-Ausgaben in Dateien gespeichert.
 * 
 * In der Version 0.3 wurde die Klasse für junit-Tests erweitert.
 * 
 * In der Version 0.4 werden die Tests aus XML-Konfigurationsdatei geladen. Dies
 * und die Ausführung der Tests und die Generierung der Ergebnisse als HTML 
 * werden in den Klassen der tests.core-Klassen implementiert.
 * 
 * @author René Majewski
 * 
 * @version 0.4
 * @since 0.1
 */
public class TestsHaushaltsbuch {
	
	/**
	 * Ruft die einzelnen Tests auf
	 * 
	 * @param args Übergebene Kommandozeilen-Parameter
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		TestCore test = new TestCore();
		try {
			if (test.readConfig("tests/tests.xml")) {
				test.checkFileExists();
				test.run();
				test.createResultHtml();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
}

}
