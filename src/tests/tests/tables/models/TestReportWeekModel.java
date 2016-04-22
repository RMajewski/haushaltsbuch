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

package tests.tests.tables.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.ReportWeekData;
import haushaltsbuch.tables.models.ReportWeekModel;
import tests.testcase.TestReports;

/**
 * Testet das Tabellen-Model {@link tables.models.ReportWeekModel}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportWeekModel extends TestReports {
	/**
	 * Speichert die Instanz des Models
	 */
	private ReportWeekModel _model;
	
	/**
	 * Speichert das Mock-Objekt für die Daten
	 */
	private ReportWeekData _data;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Mockobjekt für die Einstellungen
		_year = 2016;
		_month = 1;
		initPreferences();
		
		// Mock-Objekt für die Daten
		_data = mock(ReportWeekData.class);
		
		// Model initalisieren
		_model = new ReportWeekModel(_data);
	}

	/**
	 * Überprüft, ob die richtige Anzahl an Spalten zurück gegeben wird.
	 * 
	 * @see tables.models.ReportWeekModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		int column = 10;
		when(_data.getColumnCount()).thenReturn(column);
		assertEquals(column, _model.getColumnCount());
	}

	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben wird.
	 * 
	 * @see tables.models.ReportWeekModel#getRowCount()
	 */
	@Test
	public void testGetRowCount() {
		int row = 10;
		when(_data.getRowCount()).thenReturn(row);
		assertEquals(row, _model.getRowCount());
	}

	/**
	 * Testet, ob die Wochennummer richtig zurück gegeben wird.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtZeroColumn() {
		String str = new String("10");
		when(_data.getWeekNumber(10)).thenReturn(str);
		
		assertEquals(str, _model.getValueAt(10, 0));
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es werden
	 * keinerlei Einstellungen gemacht.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtOneColumnWithoutPreferences() {
		int row = 10;
		double d = 10.55;
		when(_data.incoming(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(d, _model.getValueAt(row, 1));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, times(1)).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die
	 * "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtOneColumnWithDateFrom() {
		int row = 10;
		String str = new String("10.10.2010");
		when(_data.getDateFrom(row)).thenReturn(str);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(str, _model.getValueAt(row, 1));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, never()).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, times(1)).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die
	 * "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtOneColumnWithDateTo() {
		int row = 10;
		String str = new String("10.10.2010");
		when(_data.getDateTo(row)).thenReturn(str);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(str, _model.getValueAt(row, 1));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, times(1)).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es sollen die
	 * "bis"-Spalte und die "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtOneColumnWithDateFromAndDateTo() {
		int row = 10;
		String str = new String("10.10.2010");
		when(_data.getDateFrom(row)).thenReturn(str);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(str, _model.getValueAt(row, 1));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, never()).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, times(1)).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Ausgabe richtig zurück gegeben wird. Es werden
	 * keinerlei Einstellungen gemacht.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtTwoColumnWithoutPreferences() {
		int row = 10;
		double d = 10.55;
		when(_data.outgoing(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(d, _model.getValueAt(row, 2));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, times(1)).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtTwoColumnWithDateFrom() {
		int row = 10;
		double d = 10.55;
		when(_data.incoming(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(d, _model.getValueAt(row, 2));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, times(1)).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtTwoColumnWithDateTo() {
		int row = 10;
		double d = 10.55;
		when(_data.incoming(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(d, _model.getValueAt(row, 2));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, times(1)).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es sollen die
	 * "bis"-Spalte und die "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtTwoColumnWithDateFromAndDateTo() {
		int row = 10;
		String str = new String("10.10.2010");
		when(_data.getDateTo(row)).thenReturn(str);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(str, _model.getValueAt(row, 2));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, times(1)).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte Einnahme richtig zurück gegeben wird. Es werden
	 * keinerlei Einstellungen gemacht.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtThreeColumnWithoutPreferences() {
		int row = 10;
		double d = 10.55;
		when(_data.deviation(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(d, _model.getValueAt(row, 3));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, times(1)).deviation(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtThreeColumnWithDateFrom() {
		int row = 10;
		double d = 10.55;
		when(_data.outgoing(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(d, _model.getValueAt(row, 3));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, times(1)).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtThreeColumnWithDateTo() {
		int row = 10;
		double d = 10.55;
		when(_data.outgoing(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(d, _model.getValueAt(row, 3));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, times(1)).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte und "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtThreeColumnWithDateFromAndDateTo() {
		int row = 10;
		double d = 10.55;
		when(_data.incoming(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(d, _model.getValueAt(row, 3));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, times(1)).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte Einnahme richtig zurück gegeben wird. Es werden
	 * keinerlei Einstellungen gemacht.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFourColumnWithoutPreferences() {
		int row = 10;
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertNull(_model.getValueAt(row, 4));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFourColumnWithDateFrom() {
		int row = 10;
		double d = 10.55;
		when(_data.deviation(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertEquals(d, _model.getValueAt(row, 4));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, times(1)).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFourColumnWithDateTo() {
		int row = 10;
		double d = 10.55;
		when(_data.deviation(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(d, _model.getValueAt(row, 4));
		
		verify(_data, times(2)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, times(1)).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte und "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFourColumnWithDateFromAndDateTo() {
		int row = 10;
		double d = 10.55;
		when(_data.outgoing(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(d, _model.getValueAt(row, 4));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, times(1)).outgoing(row);
		verify(_data, never()).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
	
	/**
	 * Testet, ob die Spalte Einnahme richtig zurück gegeben wird. Es werden
	 * keinerlei Einstellungen gemacht.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFiveColumnWithoutPreferences() {
		int row = 10;
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertNull(_model.getValueAt(row, 5));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(0)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFiveColumnWithDateFrom() {
		int row = 10;
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(false);
		
		assertNull(_model.getValueAt(row, 5));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFiveColumnWithDateTo() {
		int row = 10;
		when(_data.drawDateFrom()).thenReturn(false);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertNull(_model.getValueAt(row, 5));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(0)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, never()).deviation(row);
	}
	
	/**
	 * Testet, ob die Spalte richtig zurück gegeben wird. Es soll die 
	 * "von"-Spalte und "bis"-Spalte mit ausgegeben werden.
	 * 
	 * @see tables.models.ReportWeekModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtFiveColumnWithDateFromAndDateTo() {
		int row = 10;
		double d = 10.55;
		when(_data.deviation(row)).thenReturn(d);
		when(_data.drawDateFrom()).thenReturn(true);
		when(_data.drawDateTo()).thenReturn(true);
		
		assertEquals(d, _model.getValueAt(row, 5));
		
		verify(_data, times(1)).drawDateFrom();
		verify(_data, times(1)).drawDateTo();
		
		verify(_data, never()).incoming(row);
		verify(_data, never()).outgoing(row);
		verify(_data, times(1)).deviation(row);
		
		verify(_data, never()).getDateFrom(row);
		verify(_data, never()).getDateTo(row);
	}
}
