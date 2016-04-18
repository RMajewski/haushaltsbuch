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

import datas.ReportData;

/**
 * Tests für die Klasse {@link datas.ReportData}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
public class TestReportData {
	/**
	 * Speichert die Instanz der Klasse
	 */
	private ReportData _data;
	
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
		_data = new ReportData(_type, _finished, _month, _year);
	}
	
	/**
	 * Überprüft ob richtig initalisiert wird
	 * 
	 * @see datas.ReportData#ReportData(int, int, int)
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
	 * @see datas.ReportData#getMonth()
	 */
	@Test
	public void testGetMonth() {
		assertEquals(_month, _data.getMonth());
	}
	
	/**
	 * Überprüft, ob das Jahr richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportData#getYear()
	 */
	@Test
	public void testGetYear() {
		assertEquals(_year, _data.getYear());
	}
	
	/**
	 * Überprüft, ob das Ende richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportData#getFinished()
	 */
	@Test
	public void testGetFinished() {
		assertEquals(_finished, _data.getFinished());
	}

	/**
	 * Überprüft, ob der Monat richtig gesetzt wird.
	 * 
	 * @see datas.ReportData#setMonth(int)
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
	 * @see datas.ReportData#setYear(int)
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
	 * @see datas.ReportData#setFinished(int)
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
	 * @see datas.ReportData#setType()
	 */
	@Test
	public void testGetType() {
		assertEquals(_type, _data.getType());
	}
	
	/**
	 * Überprüft, ob der Report-Type richtig gesetzt wird.
	 * 
	 * @see datas.ReportData#setType(int)
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
	 * @see datas.ReportData#TYPE_WEEK
	 */
	@Test
	public void testTypeWeek() {
		assertEquals(1, ReportData.TYPE_WEEK);
	}
	
	/**
	 * Überprüft, ob der Wert für die Monatsübersicht richtig gesetzt ist.
	 * 
	 * @see datas.ReportData#TYPE_MONTH
	 */
	@Test
	public void testTypeMonth() {
		assertEquals(2, ReportData.TYPE_MONTH);
	}
	
	/**
	 * Überprüft, ob der Wert für die jahrenübersicht richtig gesetzt ist.
	 * 
	 * @see datas.ReportData#TYPE_YEAR
	 */
	@Test
	public void testTypeYEAR() {
		assertEquals(3, ReportData.TYPE_YEAR);
	}
	
	
}
