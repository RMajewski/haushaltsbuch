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
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import datas.ReportData;
import datas.ReportPreferencesData;
import db.query.QueryInterface;
import helper.HelperCalendar;
import test.TestHelper;

public class TestReportData extends TestHelper {
	/**
	 * Speichert die Instanz von ReportData
	 */
	private TestReportDataImplementation _data;
	
	/**
	 * Speichert das Jahr
	 */
	private int _year;
	
	/**
	 * Speichert den Monat
	 */
	private int _month;
	
	/**
	 * Speichert die Mocking-Instanz für {@link datas.ReportPreferencesData}
	 */
	private ReportPreferencesData _rpd;

	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_year = 2016;
		_month = 1;
		
		_rpd = mock(ReportPreferencesData.class);
		when(_rpd.getYear()).thenReturn(_year);
		when(_rpd.getMonth()).thenReturn(_month);
		
		_data = new TestReportDataImplementation(_rpd);
		
		_methods = ReportData.class.getMethods();
	}
	
	/**
	 * Testet, ob der Konstruktor richtig initalisiert.
	 * 
	 * @see datas.ReportData#ReportData(ReportPreferencesData)
	 */
	@Test
	public void testReportData() {
		assertEquals(_year, _data.getYear());
		assertEquals(_month, _data.getMonth());
	}

	/**
	 * Testet, ob aus dem übergebenen Long-Wert ein lesbares Daten erstellt
	 * werden konnte.
	 * 
	 * @see datas.ReportData#getDateAsString(long)
	 */
	@Test
	public void testGetDateAsString() {
		GregorianCalendar cal = HelperCalendar.createCalendar(_year);
		assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM).format(
				new Date(cal.getTimeInMillis())), 
				_data.getDateAsString(cal.getTimeInMillis()));
	}
	
	/**
	 * Testet, ob die Einstellungen richtig übernommen werden.
	 * 
	 * @see datas.ReportData#setPreferences(ReportPreferencesData)
	 */
	@Test
	public void testSetPreferences() {
		int year = 2010;
		int month = 10;
		when(_rpd.getYear()).thenReturn(year);
		when(_rpd.getMonth()).thenReturn(month);
		_data.setPreferences(_rpd);
		assertEquals(year, _data.getYear());
		assertEquals(month, _data.getMonth());
	}

	/**
	 * Testet, ob das Jahr richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportData#getYear()
	 */
	@Test
	public void testGetYear() {
		assertEquals(_year, _data.getYear());
		verify(_rpd, times(1)).getYear();
	}
	
	/**
	 * Testet, ob der Monat richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportData#getMonth()
	 */
	@Test
	public void testGetMonth() {
		assertEquals(_month, _data.getMonth());
		verify(_rpd, times(1)).getMonth();
	}

	/**
	 * Testet, ob 0 zurück gegeben wird, da die Listen noch nicht gefüllt sind.
	 * 
	 * @see datas.ReportData#incoming(int)
	 */
	@Test
	public void testIncoming() {
		assertEquals(0, _data.incoming(0), 0);
	}

	/**
	 * Testet, ob 0 zurück gegeben wird, da die Listen noch nicht gefüllt sind.
	 * 
	 * @see datas.ReportData#outgoing(int)
	 */
	@Test
	public void testOutgoing() {
		assertEquals(0, _data.outgoing(0), 0);
	}

	/**
	 * Testet, ob 0 zurück gegeben wird, da die Listen noch nicht gefüllt sind.
	 * 
	 * @see datas.ReportData#deviation(int)
	 */
	@Test
	public void testDeviation() {
		assertEquals(0, _data.deviation(0), 0);
	}
	
	/**
	 * Überprüft ob die abstrakte Methode
	 * {@link datas.ReportData#getColumnCount()} existiert.
	 */
	@Test
	public void testGetColumnCount() {
		assertTrue(getMethod("getColumnCount") != null);
	}
	
	/**
	 * Überprüft ob die abstrakte Methode
	 * {@link datas.ReportData#getColumnCount()} keine Parameter hat.
	 */
	@Test
	public void testGetColumnCountNoParameter() {
		assertEquals(0, getMethod("getColumnCount").getParameterTypes().length);
	}
	
	/**
	 * Überprüft ob der Rückgabe-Type der abstrakten Methode
	 * {@link datas.ReportData#getColumnCount()} int ist.
	 */
	@Test
	public void testGetColumnCountReturnInt() {
		assertEquals("int", 
				getMethod("getColumnCount").getReturnType().getName());
	}
	
	/**
	 * Überprüft ob die abstrakte Methode
	 * {@link datas.ReportData#setColumnHeader(javax.swing.table.TableColumnModel)}
	 * existiert.
	 */
	@Test
	public void testSetColumnHeader() {
		assertTrue(getMethod("setColumnHeader") != null);
	}
	
	/**
	 * Überprüft ob die abstrakte Methode
	 * {@link datas.ReportData#setColumnHeader(javax.swing.table.TableColumnModel)}
	 * einen Parameter vom Typ int hat.
	 */
	@Test
	public void testSetColumnHeaderHasOneParameter() {
		assertEquals(1, 
				getMethod("setColumnHeader").getParameterTypes().length);
		assertEquals("javax.swing.table.TableColumnModel", 
				getMethod("setColumnHeader").getParameterTypes()[0].getName());
	}
	
	/**
	 * Überprüft ob der Rückgabe-Type der abstrakten Methode
	 * {@link datas.ReportData#getColumnCount()} int ist.
	 */
	@Test
	public void testSetColumnHeaderNoReturnType() {
		assertEquals("void", 
				getMethod("setColumnHeader").getReturnType().getName());
	}
}
