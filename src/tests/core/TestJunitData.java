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
 * Speichert die Daten für einen junite-Test
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestJunitData extends TestData {
	/**
	 * Speichert die erfolgreichen Tests
	 */
	private int _ok;
	
	/**
	 * Speichert der fehlerhaften Tests.
	 */
	private int _fail;
	
	/**
	 * Initalisiert die Daten der Klasse
	 * 
	 * @see TestData#TestData()
	 */
	public TestJunitData() {
		super();
		
		_ok = 0;
		_fail = 0;
	}
	
	/**
	 * Gibt die Anzahl der erfolgreich ausgeführten Tests zurück
	 * 
	 * @return Anzahl der erfolgreichen Tests
	 */
	public int getOk() {
		return _ok;
	}
	
	/**
	 * Speichert die Anzahl der erfolgreich ausgeführten Tests
	 * 
	 * @param ok Azahl der erfolgreichen Tests
	 */
	public void setOk(int ok) {
		_ok = ok;
	}
	
	/**
	 * Gibt die Anzahl der fehlerhaften Tests zurück.
	 * 
	 * @return Anzahl der fehlerhaften Tests
	 */
	public int getFail() {
		return _fail;
	}
	
	/**
	 * Speichert die Anzahl der fehlerhaften Tests.
	 * 
	 * @param fail Anzahl der fehlerhaften Tests
	 */
	public void setFail(int fail) {
		_fail = fail;
	}
}
