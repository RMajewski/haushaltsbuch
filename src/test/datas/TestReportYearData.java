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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import datas.ReportPreferencesData;
import datas.ReportYearData;
import test.TestReports;

/**
 * Testet die Klasse {@link datas.ReportYearData}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportYearData extends TestReports {
	/**
	 * Speichert die Instanz für die Daten
	 */
	private ReportYearData _data;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_year = 2016;
		_month = GregorianCalendar.JANUARY;
		_type = ReportPreferencesData.TYPE_YEAR;
		initPreferences();
		
		_data = new ReportYearData(_rpd);
	}
	
	/**
	 * Testet, ob die Klase {@link datas.ReportYearData} von der Klasse
	 * {@link datas.ReportData} abgeleitet wurde.
	 */
	@Test
	public void testReportMonthDataExtendsReportData() {
		assertEquals("datas.ReportData", 
				_data.getClass().getSuperclass().getName());
	}

	/**
	 * Überprüft, ob die Methode {@link datas.ReportYearData#getColumnCount()}
	 * die Richtig Anzahl an Spalten zurück gibt.
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(4, _data.getColumnCount());
	}

	/**
	 * Überprüft, ob die Methode {@link datas.ReportYearData#getRowCount()} die
	 * richtige Anzahl an Zeilen zurück gibt.
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(12, _data.getRowCount());
	}

	/**
	 * Überprüft, ob die Spalten-Überschriften der Tabelle richtig gesetzt
	 * wurden.
	 * 
	 * @see datas.ReportYearData#setColumnHeader(TableColumnModel)
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
		order.verify(tc, times(1)).setHeaderValue("Monat");
		order.verify(tc, times(1)).setHeaderValue("Einnahmen");
		order.verify(tc, times(1)).setHeaderValue("Ausgaben");
		order.verify(tc, times(1)).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob die Namen der Monate richtig wiedergegeben werden.
	 * 
	 * @see datas.ReportYearData#getMonthName(int)
	 */
	@Test
	public void testGetMonthNameReturnRightNames() {
		assertEquals("Januar", _data.getMonthName(0));
		assertEquals("Februar", _data.getMonthName(1));
		assertEquals("März", _data.getMonthName(2));
		assertEquals("April", _data.getMonthName(3));
		assertEquals("Mai", _data.getMonthName(4));
		assertEquals("Juni", _data.getMonthName(5));
		assertEquals("Juli", _data.getMonthName(6));
		assertEquals("August", _data.getMonthName(7));
		assertEquals("September", _data.getMonthName(8));
		assertEquals("Oktober", _data.getMonthName(9));
		assertEquals("November", _data.getMonthName(10));
		assertEquals("Dezember", _data.getMonthName(11));
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn ein
	 * Monats-Index aus dem Minus-Bereich angegeben wurde.
	 * 
	 * @see datas.ReportYearData#getMonthName(int)
	 */
	@Test
	public void TestGetMonthNameWithMinusOneAsParameterReturnEmptyString() {
		assertTrue(_data.getMonthName(-1).isEmpty());
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn ein
	 * Monats-Index größer als 11 angegeben wurde.
	 * 
	 * @see datas.ReportYearData#getMonthName(int)
	 */
	@Test
	public void testGetMonthNameWithTwelveAsParameterReturnEmptyString() {
		assertTrue(_data.getMonthName(12).isEmpty());
	}

}
