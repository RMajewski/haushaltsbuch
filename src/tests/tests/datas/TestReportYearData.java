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
import static org.junit.Assert.assertTrue;
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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.MockRepository;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportYearData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Money;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Queries;
import haushaltsbuch.helper.HelperCalendar;
import tests.testcase.TestReports;

/**
 * Testet die Klasse {@link datas.ReportYearData}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestReportYearData extends TestReports {
	/**
	 * Speichert die Instanz für die Daten
	 */
	private ReportYearData _data;
	
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
	 * Initalisiert die einzelnen Tests.
	 * @throws SQLException 
	 */
	@Before
	public void setUp() {
		System.setProperty("testing", "1");
		
		// Einstellungen mocken
		_year = 2016;
		_month = GregorianCalendar.JANUARY;
		_type = ReportPreferencesData.TYPE_YEAR;
		initPreferences();
		
		// Daten für die Mocks festlegen
		_in = 29.88;
		_out = 5.98;
		_inId = 100;
		_outId = 200;
		
		// Money mocken
		Money money = mock(Money.class);
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		for (int i = 0; i < 12; i++) {
			gc.set(GregorianCalendar.MONTH, i);
			gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
			long first = gc.getTimeInMillis();
			
			gc.set(GregorianCalendar.DAY_OF_MONTH,
					gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			long last = gc.getTimeInMillis();
			when(money.selectWeek(first, last, MoneyData.INT_INCOMING)).thenReturn("in" + i);
			when(money.selectWeek(first, last, MoneyData.INT_OUTGOING)).thenReturn("out" + i);
		}
		
		// MoneyDetails mocken
		MoneyDetails details = mock(MoneyDetails.class);
		when(details.sum(_inId)).thenReturn("in_details");
		when(details.sum(_outId)).thenReturn("out_details");

		// Queries mocken
		Queries qs = mock(Queries.class);
		when(qs.money()).thenReturn(money);
		when(qs.moneyDetails()).thenReturn(details);
		
		
		try {
			// ResultSets mocken
			ResultSet rsEmpty = mock(ResultSet.class);
			when(rsEmpty.next()).thenReturn(false);
			
			ResultSet monthInId = mock(ResultSet.class);
			when(monthInId.next()).thenReturn(true, false);
			when(monthInId.getInt("id")).thenReturn(_inId);
			
			ResultSet monthIn = mock(ResultSet.class);
			when(monthIn.getDouble(1)).thenReturn(_in);
			
			ResultSet monthOutId = mock(ResultSet.class);
			when(monthOutId.next()).thenReturn(true, false);
			when(monthOutId.getInt("id")).thenReturn(_outId);
			
			ResultSet monthOut = mock(ResultSet.class);
			when(monthOut.getDouble(1)).thenReturn(_out);
			
			
			// Statements mocken
			Statement stmInId = mock(Statement.class);
			when(stmInId.executeQuery("in0")).thenReturn(monthInId);
			
			Statement stmIn = mock(Statement.class);
			when(stmIn.executeQuery("in_details")).thenReturn(monthIn);

			Statement stmOutId = mock(Statement.class);
			when(stmOutId.executeQuery("out0")).thenReturn(monthOutId);
			
			Statement stmOut = mock(Statement.class);
			when(stmOut.executeQuery("out_details")).thenReturn(monthOut);

			for (int i = 1; i < 12; i++) {
				when(stmInId.executeQuery("in" + i)).thenReturn(rsEmpty);
				when(stmOutId.executeQuery("out" + i)).thenReturn(rsEmpty);
			}

			
			// DbController mocken
			DbController dbc = mock(DbController.class);
			when(dbc.createStatement()).thenReturn(stmInId, stmIn, stmOutId, stmOut,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId);
			
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(dbc);
			PowerMockito.when(DbController.queries()).thenReturn(qs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Instanz der Daten-Klasse erzeugen
		_data = new ReportYearData(_rpd);
	}
	
	@After
	public void tearDown() {
		MockRepository.clear();
		DbController.getInstance().close();
	}
	
	/**
	 * Testet, ob die Klase {@link datas.ReportYearData} von der Klasse
	 * {@link datas.ReportData} abgeleitet wurde.
	 */
	@Test
	public void testReportMonthDataExtendsReportData() {
		assertEquals("haushaltsbuch.datas.ReportData", 
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
	
	/**
	 * Überprüft, ob für den 1. Monat Einnahmen bestehen.
	 * 
	 * @see datas.ReportYearData#incoming(int)
	 */
	@Test
	public void testIncomingWithMonthOneReturnRight() {
		assertEquals(_in, _data.incoming(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Monate 2 bis 12 keine Einnahmen bestehen.
	 * 
	 * @see datas.ReportYearData#incoming(int)
	 */
	@Test
	public void testIncomingWithMonthsTwoToTwelveReturnZero() {
		for (int i = 1; i < 12; i++)
			assertEquals(0.0, _data.incoming(i), 0.0);
	}
	
	/**
	 * Überprüft, ob für den 1. Monat Ausgaben bestehen.
	 * 
	 * @see datas.ReportYearData#outgoing(int)
	 */
	@Test
	public void testOutgoingWithMonthOneReturnRight() {
		assertEquals(_out, _data.outgoing(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Monate 2 bis 12 keine Ausgaben bestehen.
	 * 
	 * @see datas.ReportYearData#outgoing(int)
	 */
	@Test
	public void testOutgoingWithMonthsTwoToTwelveReturnZero() {
		for (int i = 1; i < 12; i++)
			assertEquals(0.0, _data.outgoing(i), 0.0);
	}
	
	/**
	 * Überprüft, ob für den 1. Monat die Differenz richtig ist.
	 * 
	 * @see datas.ReportYearData#deviation(int)
	 */
	@Test
	public void testDeviationWithMonthOneReturnRight() {
		assertEquals(_in - _out, _data.deviation(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Monate 2 bis 12 die Differenz 0.00 ist.
	 * 
	 * @see datas.ReportYearData#deviation(int)
	 */
	@Test
	public void testDeviationWithMonthsTwoToTwelveReturnZero() {
		for (int i = 1; i < 12; i++)
			assertEquals(0.0, _data.deviation(i), 0.0);
	}
}
