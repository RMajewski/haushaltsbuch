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

package tests.tests.datas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.GregorianCalendar;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import haushaltsbuch.datas.ReportMonthData;
import haushaltsbuch.datas.ReportPreferencesData;
import tests.testcase.TestReports;

/**
 * Testet die Klasse {@link datas.ReportMonthData}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportMonthData extends TestReports {
	/**
	 * Instanz der Monats-Daten
	 */
	private ReportMonthData _data;

	/**
	 * Initalisiert die einzelnen Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_year = 2016;
		_month = GregorianCalendar.JANUARY;
		_type = ReportPreferencesData.TYPE_MONTH;
		initPreferences();
		
		_data = new ReportMonthData(_rpd);
	}
	
	/**
	 * Testet, ob die Klase {@link datas.ReportMonthData} von der Klasse
	 * {@link datas.ReportData} abgeleitet wurde.
	 */
	@Test
	public void testReportMonthDataExtendsReportData() {
		assertEquals("datas.ReportData", 
				_data.getClass().getSuperclass().getName());
	}
	
	/**
	 * Überprüft, ob die Richtige Anzahl an Spalten zurück gegeben werden.
	 * 
	 * @see datas.ReportMonthData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountReturnRightCount() {
		assertEquals(4, _data.getColumnCount());
	}
	
	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben werden. Es
	 * wird der Juli 2016 eingestellt.
	 * 
	 * @see datas.ReportMonthData#getRowCount()
	 */
	@Test
	public void testGetRowCountReturgnRightCountForJuliy2016() {
		when(_rpd.getMonth()).thenReturn(GregorianCalendar.JULY);
		assertEquals(31, _data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben werden. Es
	 * wird der Februar 2016 eingestellt.
	 * 
	 * @see datas.ReportMonthData#getRowCount()
	 */
	@Test
	public void testGetRowCountReturgnRightCountForFebruary2016() {
		when(_rpd.getMonth()).thenReturn(GregorianCalendar.FEBRUARY);
		assertEquals(29, _data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben werden. Es
	 * wird der Februar 2015 eingestellt.
	 * 
	 * @see datas.ReportMonthData#getRowCount()
	 */
	@Test
	public void testGetRowCountReturgnRightCountForFebruary2015() {
		when(_rpd.getMonth()).thenReturn(GregorianCalendar.FEBRUARY);
		when(_rpd.getYear()).thenReturn(2015);
		assertEquals(28, _data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben werden. Es
	 * wird der April 2016 eingestellt.
	 * 
	 * @see datas.ReportMonthData#getRowCount()
	 */
	@Test
	public void testGetRowCountReturgnRightCountForApril2016() {
		when(_rpd.getMonth()).thenReturn(GregorianCalendar.APRIL);
		assertEquals(30, _data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die richtigen Spalten-Namen gesetzt werden.
	 * 
	 * @see datas.ReportMonthData#setColumnHeader(javax.swing.table.TableColumnModel)
	 */
	@Test
	public void testSetColumnHeader() {
		// Mock-Objekte vorbereiten
		TableColumn tc = mock(TableColumn.class);
		TableColumnModel tcm = mock(TableColumnModel.class);
		when(tcm.getColumn(0)).thenReturn(tc);
		when(tcm.getColumn(1)).thenReturn(tc);
		when(tcm.getColumn(2)).thenReturn(tc);
		when(tcm.getColumn(3)).thenReturn(tc);
		InOrder order = inOrder(tc);
		
		// Spalten-Überschriften setzen
		_data.setColumnHeader(tcm);
		
		// Überprüfen, ob die entsprechenden Methoden aufgerufen wurden
		verify(tcm, times(1)).getColumn(0);
		verify(tcm, times(1)).getColumn(1);
		verify(tcm, times(1)).getColumn(2);
		verify(tcm, times(1)).getColumn(3);
		verify(tcm, never()).getColumn(4);
		order.verify(tc, times(1)).setHeaderValue("Tag");
		order.verify(tc, times(1)).setHeaderValue("Einnahmen");
		order.verify(tc, times(1)).setHeaderValue("Ausgaben");
		order.verify(tc, times(1)).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob der richtige Tag zurück gegeben wird.
	 * 
	 * @see datas.ReportMonthData#getDay(int)
	 */
	@Test
	public void testGetDayForFirstJanuary2016() {
		for (int i = 0; i < 31; i++)
			assertEquals("01.01.2016", _data.getDay(0));
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn der index
	 * im Minus-Bereich ist.
	 * 
	 * @see datas.ReportMonthData#getDay(int)
	 */
	@Test
	public void testGetDayWithMinusOneAsParameter() {
		assertNull(_data.getDay(-1));
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn eine zu
	 * hoher Index angegeben wird.
	 * 
	 * @see datas.ReportMonthData#getDay(int)
	 */
	@Test
	public void testGetDayWithThirtyOneAsParameter() {
		assertNull(_data.getDay(31));
	}
}
