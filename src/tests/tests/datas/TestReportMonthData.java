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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.ReportMonthData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Money;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Queries;
import haushaltsbuch.helper.HelperCalendar;
import tests.testcase.TestReports;

/**
 * Testet die Klasse {@link haushaltsbuch.datas.ReportMonthData}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestReportMonthData extends TestReports {
	/**
	 * Instanz der Monats-Daten
	 */
	private ReportMonthData _data;
	
	/**
	 * Speichert die Einnahmen
	 */
	private double _in;
	
	/**
	 * Speichert die Ausgaben
	 */
	private double _out;
	
	/**
	 * Speichert die Id für die Einnahme
	 */
	private int _inId;
	
	/**
	 * Speichert die Id für die Ausgabe
	 */
	private int _outId;

	/**
	 * Initalisiert die einzelnen Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Einstellungen mocken
		_year = 2016;
		_month = GregorianCalendar.JANUARY;
		_type = ReportPreferencesData.TYPE_MONTH;
		initPreferences();
		
		// Daten für die Mocks festlegen
		_in = 9.93;
		_out = 7.66;
		_inId = 100;
		_outId = 200;

		// Money mocken
		Money money = mock(Money.class);
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		gc.set(GregorianCalendar.MONTH, _month);
		for(int i = 1; i <= gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {
			gc.set(GregorianCalendar.DAY_OF_MONTH, i);
			long day = gc.getTimeInMillis();
			when(money.selectDay(day, MoneyData.INT_INCOMING)).thenReturn("in" + i);
			when(money.selectDay(day, MoneyData.INT_OUTGOING)).thenReturn("out" + i);
		}
		
		// MoneyDetails mocken
		MoneyDetails details = mock(MoneyDetails.class);
		when(details.sumMoneyId(_inId)).thenReturn("details_in");
		when(details.sumMoneyId(_outId)).thenReturn("details_out");
		
		// Queries mocken
		Queries queries = mock(Queries.class);
		when(queries.money()).thenReturn(money);
		when(queries.moneyDetails()).thenReturn(details);
		
		// Statements, ResultSets und DbController-Mock
		try {
			// ResultSets mocken
			ResultSet rsEmpty = mock(ResultSet.class);
			when(rsEmpty.next()).thenReturn(false);
			
			ResultSet dayInId = mock(ResultSet.class);
			when(dayInId.next()).thenReturn(true, false);
			when(dayInId.getInt("id")).thenReturn(_inId);
			
			ResultSet dayIn = mock(ResultSet.class);
			when(dayIn.getDouble(1)).thenReturn(_in);
			
			ResultSet dayOutId = mock(ResultSet.class);
			when(dayOutId.next()).thenReturn(true, false);
			when(dayOutId.getInt("id")).thenReturn(_outId);
			
			ResultSet dayOut = mock(ResultSet.class);
			when(dayOut.getDouble(1)).thenReturn(_out);
			
			
			// Statements mocken
			Statement stmInId = mock(Statement.class);
			when(stmInId.executeQuery("in1")).thenReturn(dayInId);
			
			Statement stmIn = mock(Statement.class);
			when(stmIn.executeQuery("details_in")).thenReturn(dayIn);
			
			Statement stmOutId = mock(Statement.class);
			when(stmOutId.executeQuery("out1")).thenReturn(dayOutId);
			
			Statement stmOut = mock(Statement.class);
			when(stmOut.executeQuery("details_out")).thenReturn(dayOut);
			
			for (int i = 2; i <= gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {
				when(stmInId.executeQuery("in" + i)).thenReturn(rsEmpty);
				when(stmOutId.executeQuery("out" + i)).thenReturn(rsEmpty);
			}
			
			
			// DbController mocken
			DbController dbc = mock(DbController.class);
			when(dbc.createStatement()).thenReturn(stmInId, stmIn, stmOutId, 
					stmOut, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId, 
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId, stmInId, stmOutId, stmInId, stmOutId, stmInId,
					stmOutId);
			
			// Statische Methoden von DbController mocken
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(dbc);
			PowerMockito.when(DbController.queries()).thenReturn(queries);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Instanz der Daten-Klassen erzeugen
		_data = new ReportMonthData(_rpd);
	}
	
	/**
	 * Testet, ob die Klase {@link haushaltsbuch.datas.ReportMonthData} von der Klasse
	 * {@link haushaltsbuch.datas.ReportData} abgeleitet wurde.
	 */
	@Test
	public void testReportMonthDataExtendsReportData() {
		assertEquals("haushaltsbuch.datas.ReportData", 
				_data.getClass().getSuperclass().getName());
	}
	
	/**
	 * Überprüft, ob die Richtige Anzahl an Spalten zurück gegeben werden.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountReturnRightCount() {
		assertEquals(4, _data.getColumnCount());
	}
	
	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben werden. Es
	 * wird der Juli 2016 eingestellt.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#getRowCount()
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
	 * @see haushaltsbuch.datas.ReportMonthData#getRowCount()
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
	 * @see haushaltsbuch.datas.ReportMonthData#getRowCount()
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
	 * @see haushaltsbuch.datas.ReportMonthData#getRowCount()
	 */
	@Test
	public void testGetRowCountReturgnRightCountForApril2016() {
		when(_rpd.getMonth()).thenReturn(GregorianCalendar.APRIL);
		assertEquals(30, _data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die richtigen Spalten-Namen gesetzt werden.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#setColumnHeader(javax.swing.table.TableColumnModel)
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
	 * @see haushaltsbuch.datas.ReportMonthData#getDay(int)
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
	 * @see haushaltsbuch.datas.ReportMonthData#getDay(int)
	 */
	@Test
	public void testGetDayWithMinusOneAsParameter() {
		assertNull(_data.getDay(-1));
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn eine zu
	 * hoher Index angegeben wird.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#getDay(int)
	 */
	@Test
	public void testGetDayWithThirtyOneAsParameter() {
		assertNull(_data.getDay(31));
	}
	
	/**
	 * Überprüft, ob für den 1. Tag Einnahmen bestehen.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#incoming(int)
	 */
	@Test
	public void testIncomingWithMonthOneReturnRight() {
		assertEquals(_in, _data.incoming(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Tage 2 bis 31 keine Einnahmen bestehen.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#incoming(int)
	 */
	@Test
	public void testIncomingWithMonthsTwoToTwelveReturnZero() {
		for (int i = 1; i < 12; i++)
			assertEquals(0.0, _data.incoming(i), 0.0);
	}
	
	/**
	 * Überprüft, ob für den 1. Tag Ausgaben bestehen.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#outgoing(int)
	 */
	@Test
	public void testOutgoingWithMonthOneReturnRight() {
		assertEquals(_out, _data.outgoing(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Tage 2 bis 31 keine Ausgaben bestehen.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#outgoing(int)
	 */
	@Test
	public void testOutgoingWithMonthsTwoToTwelveReturnZero() {
		for (int i = 1; i < 12; i++)
			assertEquals(0.0, _data.outgoing(i), 0.0);
	}
	
	/**
	 * Überprüft, ob für den 1. Tag die Differenz richtig ist.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#deviation(int)
	 */
	@Test
	public void testDeviationWithMonthOneReturnRight() {
		assertEquals(_in - _out, _data.deviation(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Tage 2 bis 31 die Differenz 0.00 ist.
	 * 
	 * @see haushaltsbuch.datas.ReportMonthData#deviation(int)
	 */
	@Test
	public void testDeviationWithMonthsTwoToTwelveReturnZero() {
		for (int i = 1; i < 12; i++)
			assertEquals(0.0, _data.deviation(i), 0.0);
	}
}
