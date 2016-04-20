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

package test.datas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datas.ReportPreferencesData;

/**
 * Tests für die Klasse {@link datas.ReportPreferencesData}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
public class TestReportPreferenceData {
	/**
	 * Speichert die Instanz der Klasse
	 */
	private ReportPreferencesData _data;
	
	/**
	 * Speichert das Jahr
	 */
	private int _year;
	
	/**
	 * Speichert den Monat
	 */
	private int _month;
	
	/**
	 * Speichert wie beendet wurde
	 */
	private int _finished;
	
	/**
	 * Speichert welcher Report erstellt werden soll
	 */
	private int _type;
	
	/**
	 * Speichert den Schlüssel
	 */
	private final String _key = "Test";

	/**
	 * Initalisiert die Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_finished = 0;
		_month = 1;
		_year = 2016;
		_type = 2;
		_data = new ReportPreferencesData(_type, _finished, _month, _year);
	}
	
	/**
	 * Überprüft ob richtig initalisiert wird
	 * 
	 * @see datas.ReportPreferencesData#ReportData(int, int, int)
	 */
	@Test
	public void testReportData() {
		assertEquals(_type, _data.getType());
		assertEquals(_finished, _data.getFinished());
		assertEquals(_month, _data.getMonth());
		assertEquals(_year, _data.getYear());
	}
	
	/**
	 * Überprüft, ob der Monat richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportPreferencesData#getMonth()
	 */
	@Test
	public void testGetMonth() {
		assertEquals(_month, _data.getMonth());
	}
	
	/**
	 * Überprüft, ob das Jahr richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportPreferencesData#getYear()
	 */
	@Test
	public void testGetYear() {
		assertEquals(_year, _data.getYear());
	}
	
	/**
	 * Überprüft, ob das Ende richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportPreferencesData#getFinished()
	 */
	@Test
	public void testGetFinished() {
		assertEquals(_finished, _data.getFinished());
	}

	/**
	 * Überprüft, ob der Monat richtig gesetzt wird.
	 * 
	 * @see datas.ReportPreferencesData#setMonth(int)
	 */
	@Test
	public void testSetMonth() {
		_month = 5;
		_data.setMonth(_month);
		assertEquals(_month, _data.getMonth());
	}
	
	/**
	 * Überprüft, ob das Jahr richtig gesetzt wird.
	 * 
	 * @see datas.ReportPreferencesData#setYear(int)
	 */
	@Test
	public void testSetYear() {
		_year = 2020;
		_data.setYear(_year);
		assertEquals(_year, _data.getYear());
	}
	
	/**
	 * Überprüft, ob das Ende richtig gesetzt wird.
	 * 
	 * @see datas.ReportPreferencesData#setFinished(int)
	 */
	@Test
	public void testSetFinished() {
		_finished = 1;
		_data.setFinished(_finished);
		assertEquals(_finished, _data.getFinished());
	}
	
	/**
	 * Überprüft, ob der Report-Type richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportPreferencesData#setType()
	 */
	@Test
	public void testGetType() {
		assertEquals(_type, _data.getType());
	}
	
	/**
	 * Überprüft, ob der Report-Type richtig gesetzt wird.
	 * 
	 * @see datas.ReportPreferencesData#setType(int)
	 */
	@Test
	public void testSetType() {
		_type = 100;
		_data.setType(_type);
		assertEquals(_type, _data.getType());
	}
	
	/**
	 * Überprüft, ob der Wert für die Wochenübersicht richtig gesetzt ist.
	 * 
	 * @see datas.ReportPreferencesData#TYPE_WEEK
	 */
	@Test
	public void testTypeWeek() {
		assertEquals(1, ReportPreferencesData.TYPE_WEEK);
	}
	
	/**
	 * Überprüft, ob der Wert für die Monatsübersicht richtig gesetzt ist.
	 * 
	 * @see datas.ReportPreferencesData#TYPE_MONTH
	 */
	@Test
	public void testTypeMonth() {
		assertEquals(2, ReportPreferencesData.TYPE_MONTH);
	}
	
	/**
	 * Überprüft, ob der Wert für die jahrenübersicht richtig gesetzt ist.
	 * 
	 * @see datas.ReportPreferencesData#TYPE_YEAR
	 */
	@Test
	public void testTypeYEAR() {
		assertEquals(3, ReportPreferencesData.TYPE_YEAR);
	}
	
	/**
	 * Überprüft, ob die Anzahl der Einstellungen korrekt ermittelt wird.
	 * 
	 * @see datas.ReportPreferencesData#getPrefenceCount()
	 */
	@Test
	public void testGetPreferenceCount() {
		assertEquals(0, _data.getPreferenceCount());
	}
	
	/**
	 * Überprüft, ob die Einstellung korrekt zurück gegeben wird.
	 * 
	 * @see datas.ReportPreferencesData#getPrefence(String)
	 */
	@Test
	public void testGetPreference() {
		_data.setPreference(_key, 100);
		assertEquals(100, _data.getPreference(_key));
	}
	
	/**
	 * Überprüft, ob <b>null</b> zurück geliefert wird, wenn der Schlüssel
	 * <b>null</b> ist.
	 * 
	 * @see datas.ReportPreferencesData#getPrefence(String)
	 */
	@Test
	public void testGetPreferenceWithNullAsKey() {
		assertNull(_data.getPreference(null));
	}
	
	/**
	 * Überprüft, ob <b>null</b> zurück geliefert wird, wenn der Schlüssel
	 * eine leere Zeichenkette ist.
	 * 
	 * @see datas.ReportPreferencesData#getPrefence(String)
	 */
	@Test
	public void testGetPreferenceWithEmptyAsKey() {
		assertNull(_data.getPreference(new String()));
	}
	
	/**
	 * Fügt eine Einstellung hinzu.
	 * 
	 * @see datas.ReportPreferencesData#setPreference(String, Object)
	 */
	@Test
	public void testSetPreferenceWithValidKey() {
		_data.setPreference(_key, 100);
		assertEquals(1, _data.getPreferenceCount());
	}
	
	/**
	 * Überprüft, ob die Einstellung nicht gespeichert wird, wenn als Schlüssel
	 * <b>null</b> übergeben wird.
	 * 
	 * @see datas.ReportPreferencesData#setPreference(String, Object)
	 */
	@Test
	public void testSetPreferenceWithNullAsKeyNotInsert() {
		_data.setPreference(null, 100);
		assertEquals(0, _data.getPreferenceCount());
	}
	
	/**
	 * Überprüft, ob die Einstellung nicht gespeichert wird, wenn als Schlüssel
	 * eine leere Zeichenkette übergeben wird.
	 * 
	 * @see datas.ReportPreferencesData#setPreference(String, Object)
	 */
	@Test
	public void testSetPreferenceWithEmptyAsKeyNotInsert() {
		_data.setPreference(new String(), 100);
		assertEquals(0, _data.getPreferenceCount());
	}
	
	/**
	 * Überprüft, ob die Einstellung geändert wird, wenn sie schon existiert,
	 * also nicht doppelt angelegt wird.
	 * 
	 * @see datas.ReportPreferencesData#setPreference(String, Object)
	 */
	@Test
	public void testSetPreferenceChangePreference() {
		_data.setPreference(_key, 100);
		assertEquals(1, _data.getPreferenceCount());
		assertEquals(100, _data.getPreference(_key));
		
		_data.setPreference(_key, 200);
		assertEquals(1, _data.getPreferenceCount());
		assertEquals(200, _data.getPreference(_key));
	}
	
	/**
	 * Überprüft, ob die Einstellung nicht gespeichert wird, wenn als
	 * Einstellung <b>null</b> übergeben wird.
	 * 
	 * @see datas.ReportPreferencesData#setPreference(String, Object)
	 */
	@Test
	public void testSetPreferenceWithNullAsPreferenceNotInsert() {
		_data.setPreference(_key, null);
		assertEquals(0, _data.getPreferenceCount());
	}
	
	/**
	 * Überprüft, ob die angegebene Einstellung gelöscht wurde.
	 * 
	 * @see datas.ReportPreferencesData#removePreference(String)
	 */
	@Test
	public void testRemovePreference() {
		_data.setPreference(_key, "Test");
		assertEquals(1, _data.getPreferenceCount());
		
		_data.removePreference(_key);
		assertEquals(0, _data.getPreferenceCount());
	}
}
