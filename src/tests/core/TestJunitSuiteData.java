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

/**
 * Speichert die Daten für eine junit-Test-Suite.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestJunitSuiteData extends TestSuiteData {

	/**
	 * Initalisiert die Daten.
	 */
	public TestJunitSuiteData() {
		super();
	}
	
	/**
	 * Fügt den angegebenen Fit-Test der Liste der Tests hinzu.
	 * 
	 * @param test Fit-Test, der der Liste hinzugefügt werden soll.
	 */
	public void addTest(TestJunitData test) {
		super.addTest(test);
	}
	
	/**
	 * Gibt den über index angegebenen Fit-Test zurück.
	 * 
	 * @param index Stelle, an der der Fit-Test steht, der zurück gegeben werden
	 * soll.
	 * 
	 * @return Ermittelter Fit-Test
	 */
	@Override
	public TestJunitData getTest(int index) {
		return (TestJunitData) super.getTest(index);
	}

}
