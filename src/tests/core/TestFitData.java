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
 * Speichert die Daten für einen Fit-Test
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestFitData extends TestData {
	/**
	 * Speichert die Anzahl der richtig ausgeführten Behauptungen.
	 */
	private int _right;
	
	/**
	 * Speichert die Anzahl der Behauptungen, die falsch sind.
	 */
	private int _wrong;
	
	/**
	 * Speichert die Anzahl der Behauptungen, die ignoriert wurden.
	 */
	private int _ignore;
	
	/**
	 * Speichert die Anzahl, die Fehlerhaft sind.
	 */
	private int _exception;
	
	/**
	 * Initalisiert dies Daten der Klasse
	 * 
	 * @see TestData#TestData(String)
	 */
	public TestFitData() {
		super();
		
		_exception = 0;
		_ignore = 0;
		_right = 0;
		_wrong = 0;
	}
	
	/**
	 * Legt die Anzahl der fehlerhafen Tests fest.
	 * 
	 * @param count Anzahl der fehlerhaften Tests
	 */
	public void setException(int count) {
		_exception = count;
	}
	
	/**
	 * Gibt die Anzahl der fehlerhaften Tests zurück.
	 * 
	 * @return Anzahl der fehlerhaften Tests.
	 */
	public int getException() {
		return _exception;
	}
	
	/**
	 * Legt die Anzahl der ignorierten Tests fest
	 * 
	 * @param count Anzahl der ignorierten Tests
	 */
	public void setIgnore(int count) {
		_ignore = count;
	}
	
	/**
	 * Gibt die Anzahl der ignorierten Tests zurück.
	 * 
	 * @return Anzahl der ignorierten Tests
	 */
	public int getIgnore() {
		return _ignore;
	}
	
	/**
	 * Legt die Anzahl der richtigen Tests fest.
	 * 
	 * @param count Anzahl der richtigen Tests
	 */
	public void setRight(int count) {
		_right = count;
	}
	
	/**
	 * Gibt die Anzahl der richtigen Tests zurück.
	 * 
	 * @return Anzahl der richtigen Tests
	 */
	public int getRight() {
		return _right;
	}
	
	/**
	 * Legt die Anzahl der falschen Tests fest.
	 * 
	 * @param count Anzahl der falschen Tests
	 */
	public void setWrong(int count) {
		_wrong = count;
	}
	
	/**
	 * Gibt die Anzahl der falschen Tests zurück.
	 * 
	 * @return Anzahl der falschen Tests
	 */
	public int getWrong() {
		return _wrong;
	}
}
