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
 * Wird nur gebraucht, um die Test-Umgung zu testen
 * 
 * @author René Majewski
 *
 */
public class Tests {

	/**
	 * Führt die Tests aus
	 * 
	 * @param args Parameter von der Kommandozeile
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
