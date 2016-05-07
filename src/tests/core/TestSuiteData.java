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

import java.util.ArrayList;
import java.util.List;

/**
 * Speichert die Daten für eine Test-Suite.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class TestSuiteData {
	/**
	 * Speichert die Liste mit den dazugehörigen Tests
	 */
	private List<TestData> _tests;
	
	/**
	 * Speichert den Namen dieser Test-Suite
	 */
	private String _name;
	
	/**
	 * Speichert das Package, in dem sich die Tests befinden
	 */
	private String _package;
	
	/**
	 * Speichert ob das Package existiert oder nicht.
	 */
	private boolean _exists;
	
	/**
	 * Initalisiert die Daten 
	 */
	public TestSuiteData() {
		_name = new String();
		_tests = new ArrayList<TestData>();
		_package = new String();
		_exists = false;
	}
	
	/**
	 * Legt den Namen fest
	 * 
	 * @param name Neuer Name der Test-Suite
	 */
	public void setName(String name) {
		_name = name;
	}
	
	/**
	 * Gibt den Namen der Test-Suite zurück
	 * 
	 * @return Name der Test-Suite
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Legt den Namen des Packages fest.
	 * 
	 * @param packname Name des Packages
	 */
	public void setPackage(String packname) {
		if (packname == null)
			throw new IllegalArgumentException("Where was null as parameter.");
		_package = packname;
	}
	
	/**
	 * Gibt den Namen des Packages zurück
	 * 
	 * @return Name des Packages
	 */
	public String getPackage() {
		return _package;
	}
	
	/**
	 * Legt fest, ob das Package existiert oder nicht
	 * 
	 * @param exists Existiert das package?
	 */
	public void setExists(boolean exists) {
		_exists = exists;
	}
	
	/**
	 * Gibt zurück, ob das Package existiert oder nicht.
	 * 
	 * @return Existiert das Package?
	 */
	public boolean isExists() {
		return _exists;
	}
	
	/**
	 * Fügt den angegebenen Test der Liste hinzu
	 * 
	 * @param test Test, der der Liste der Tests hinzugefügt werden soll
	 */
	public void addTest(TestData test) {
		_tests.add(test);
	}
	
	/**
	 * Ermittelt den angegebenen Test und gibt ihn zurück
	 * 
	 * @param index Stelle, an der der Test in der Liste sich befindet
	 * 
	 * @return Angegebener Test
	 */
	public TestData getTest(int index) {
		return _tests.get(index);
	}
	
	/**
	 * Ermittelt die Anzahl der Tests in dieser Test-Suite und gibt diese
	 * zurück.
	 * 
	 * @return Anzahl der Tests
	 */
	public int testCount() {
		return _tests.size();
	}
}
