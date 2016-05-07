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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportWeekData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Money;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Queries;
import haushaltsbuch.helper.HelperCalendar;
import tests.testcase.TestReports;

/**
 * Testet die Daten-Klasse {@link datas.ReportWeekData}
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestReportWeekData extends TestReports {
	/**
	 * Speichert die Daten des Reports
	 */
	private ReportWeekData _data;
	
	/**
	 * Speichert die Einnahmen für die 1. Woche
	 */
	private double _in;
	
	/**
	 * Speichert die Ausgaben für die 1. Woche
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
	 * Speichert den Mock für ein leeren ResultSet
	 */
	private ResultSet _rsEmpty;
	
	/**
	 * Speichert den Mock für ein Statement, was immer ein leeres ResultSet
	 * liefert.
	 */
	private Statement _stmAnyEmpty;
	
	
	/**
	 * Diese Initalisierungen bleiben für alle Tests bestehen.
	 */
	@BeforeClass
	public static void setUpClass() {
		System.setProperty("testing", "true");
	}

	/**
	 * Initalisiert die einzelnen Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.setProperty("testing", "1");
		
		// Einstellungen mocken
		_year = 2016;
		_month = GregorianCalendar.JANUARY;
		_type = ReportPreferencesData.TYPE_YEAR;
		initPreferences();
		
		// Daten für die Mocks festlegen
		_in = 11.88;
		_out = 25.76;
		_inId = 100;
		_outId = 200;
		
		
		// Money mocken
		Money money = mock(Money.class);
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		long first = 0, last = 0;
		for (int i = 0; i <= gc.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR) + 1; i++) {
			if (i > 0) {
				gc.set(GregorianCalendar.WEEK_OF_YEAR, i);
				gc.set(GregorianCalendar.DAY_OF_WEEK, 2);
			}
			first = gc.getTimeInMillis();
			gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
			last = gc.getTimeInMillis();
			
			when(money.selectWeek(first, last, MoneyData.INT_INCOMING)).thenReturn("in" + i);
			when(money.selectWeek(first, last, MoneyData.INT_OUTGOING)).thenReturn("out" + i);
		}
		
		// MoneyDetails mocken
		MoneyDetails details = mock(MoneyDetails.class);
		when(details.sumMoneyId(_inId)).thenReturn("details_in");
		when(details.sumMoneyId(_outId)).thenReturn("details_out");
		
		// Queries mocken
		Queries queries = mock(Queries.class);
		when(queries.money()).thenReturn(money);
		when(queries.moneyDetails()).thenReturn(details);
		
		// ResultSets, Statements und DbController mocken
		try {
			// ResultSets
			_rsEmpty = mock(ResultSet.class);
			when(_rsEmpty.next()).thenReturn(false);
			
			ResultSet weekInId = mock(ResultSet.class);
			when(weekInId.next()).thenReturn(true, false);
			when(weekInId.getInt("id")).thenReturn(_inId);
			
			ResultSet weekIn = mock(ResultSet.class);
			when(weekIn.getDouble(1)).thenReturn(_in);
			
			ResultSet weekOutId = mock(ResultSet.class);
			when(weekOutId.next()).thenReturn(true, false);
			when(weekOutId.getInt("id")).thenReturn(_outId);
			
			ResultSet weekOut = mock(ResultSet.class);
			when(weekOut.getDouble(1)).thenReturn(_out);
			
			
			// Statements
			Statement stmInId = mock(Statement.class);
			when(stmInId.executeQuery("in0")).thenReturn(weekInId);
			
			Statement stmIn = mock(Statement.class);
			when(stmIn.executeQuery("details_in")).thenReturn(weekIn);
			
			Statement stmOutId = mock(Statement.class);
			when(stmOutId.executeQuery("out0")).thenReturn(weekOutId);
			
			Statement stmOut = mock(Statement.class);
			when(stmOut.executeQuery("details_out")).thenReturn(weekOut);

			for (int i = 1; i <= gc.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR) + 1; i++) {
				when(stmInId.executeQuery("in" + i)).thenReturn(_rsEmpty);
				when(stmOutId.executeQuery("out" + i)).thenReturn(_rsEmpty);
			}
			
			// Statement, was immer ein leeres ResultSet liefert
			_stmAnyEmpty = mock(Statement.class);
			when(_stmAnyEmpty.executeQuery(anyString())).thenReturn(_rsEmpty);
		
			
			// DbController mocken
			DbController dbc = mock(DbController.class);
			when(dbc.createStatement()).thenReturn(stmInId, stmIn, stmOutId, stmOut,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId, 
					stmInId, stmOutId, stmInId, stmOutId, stmInId, stmOutId,
					stmInId, stmOutId, stmInId, stmOutId);
			
			// Statische Methoden des DbController
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(dbc);
			PowerMockito.when(DbController.queries()).thenReturn(queries);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Instanz der Daten-Klasse erzeugen
		_data = new ReportWeekData(_rpd);
	}
	
	/**
	 * Überprüft, ob die Konstante {@link datas.ReportWeekData#DRAW_DATE_FROM}
	 * richtig gesetzt ist.
	 */
	@Test
	public void testDrawFrom() {
		assertEquals("Week.drawDateFrom", ReportWeekData.DRAW_DATE_FROM);
	}
	
	/**
	 * Überprüft, ob die Konstante {@link datas.ReportWeekData#DRAW_DATE_TO}
	 * richtig gesetzt ist.
	 */
	@Test
	public void testDrawTo() {
		assertEquals("Week.drawDateFrom", ReportWeekData.DRAW_DATE_FROM);
	}
	
	/**
	 * Überprüft ob das ausgewählte Jahre richtig wiedergegeben wird.
	 * 
	 * @see datas.ReportWeekData#getYear()
	 */
	@Test
	public void testGetYear() {
		assertEquals(_year, _data.getYear());
	}
	
	/**
	 * Überprüft, die Einstellungen neu gesetzt werden können
	 * 
	 * @see datas.ReportWeekData#setPreferences(ReportPreferences)
	 */
	@Test
	public void testSetPreferences() {
		DbController dbc = DbController.getInstance();
		try {
			when(dbc.createStatement()).thenReturn(_stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, 
					_stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty, _stmAnyEmpty,
					_stmAnyEmpty, _stmAnyEmpty);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int year = 2000;
		when(_rpd.getYear()).thenReturn(year);
		_data.setPreferences(_rpd);
		assertEquals(year, _data.getYear());
	}
	
	/**
	 * Überprüft, ob die Anzahl der Wochennummern für 2016 korrekt zurück
	 * gegeben wird.
	 * 
	 * @see datas.ReportWeekData#ReportWeekData(int)
	 */
	@Test
	public void testGetWeekNumbersFor2016() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.YEAR, _year);
		assertEquals(calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR) + 1,
				_data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die Anzahl der Wochennummern für 2000 korrekt zurück
	 * gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getWeekNumber(int)
	 */
	@Test
	public void testGetWeekNumbersFor2000() {
		_year = 2000;
		_rpd.setYear(_year);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.YEAR, _year);
		assertEquals(calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR) + 1,
				_data.getRowCount());
	}
	
	/**
	 * Überprüft ob die Wochennummern richtig zurück gegeben werden
	 * 
	 * @see datas.ReportWeekData#getWeekNumber(int)
	 */
	@Test
	public void testGetWeekNumber() {
		for (int i = 0; i < _data.getRowCount(); i++)
			assertEquals(String.valueOf(i), _data.getWeekNumber(i));
	}

	/**
	 * Anzahl Spalten ermitteln, wenn keine zusätzlichen Daten angezeigt werden
	 * sollen.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(4, _data.getColumnCount());
	}

	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * Spalte "bis" mit angegeben wird.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithDateTo() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_TO)).thenReturn(1);

		assertEquals(5, _data.getColumnCount());
		
		verify(_rpd, times(1)).getPreference(ReportWeekData.DRAW_DATE_FROM);
		verify(_rpd, times(1)).getPreference(ReportWeekData.DRAW_DATE_TO);
	}

	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * Spalte "von" mit angegeben wird.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithDateFrom() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_FROM)).thenReturn(1);
		
		assertEquals(5, _data.getColumnCount());
		
		verify(_rpd, times(1)).getPreference(ReportWeekData.DRAW_DATE_FROM);
		verify(_rpd, times(1)).getPreference(ReportWeekData.DRAW_DATE_TO);
	}

	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * Spalte "von" und die Spalte "bis" mit angegeben werden.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithDateFromAndDateTo() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_FROM)).thenReturn(1);
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_TO)).thenReturn(1);
		
		assertEquals(6, _data.getColumnCount());
		
		verify(_rpd, times(1)).getPreference(ReportWeekData.DRAW_DATE_FROM);
		verify(_rpd, times(1)).getPreference(ReportWeekData.DRAW_DATE_TO);
	}
	
	/**
	 * Überprüft, ob die Spaltenüberschriften gesetzt werden können.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeader() {
		// Model vorbereiten
		TableColumnModel tcm = mock(TableColumnModel.class);
		TableColumn tc = mock(TableColumn.class);
		when(tcm.getColumn(0)).thenReturn(tc);
		when(tcm.getColumn(1)).thenReturn(tc);
		when(tcm.getColumn(2)).thenReturn(tc);
		when(tcm.getColumn(3)).thenReturn(tc);
		
		// Header setzen
		_data.setColumnHeader(tcm);
		
		// Überprüfen ob die richtigen Methoden aufgerufen wurden
		verify(tcm).getColumn(0);
		verify(tc).setHeaderValue("Woche");
		verify(tcm).getColumn(1);
		verify(tc).setHeaderValue("Einnahmen");
		verify(tcm).getColumn(2);
		verify(tc).setHeaderValue("Ausgaben");
		verify(tcm).getColumn(3);
		verify(tc).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob die Spaltenüberschriften gesetzt werden können. Neben den
	 * Standard-Spalten wird noch die Spalte "von" angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderWithDateFrom() {
		// Einstellungen
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_FROM)).thenReturn(1);
		
		// Model vorbereiten
		TableColumnModel tcm = mock(TableColumnModel.class);
		TableColumn tc = mock(TableColumn.class);
		
		for (int i = 0; i < _data.getColumnCount(); i++)
			when(tcm.getColumn(i)).thenReturn(tc);
		
		// Header setzen
		_data.setColumnHeader(tcm);
		
		// Überprüfen ob die richtigen Methoden aufgerufen wurden
		verify(tcm).getColumn(0);
		verify(tc).setHeaderValue("Woche");
		verify(tcm).getColumn(1);
		verify(tc).setHeaderValue("von");
		verify(tcm).getColumn(2);
		verify(tc).setHeaderValue("Einnahmen");
		verify(tcm).getColumn(3);
		verify(tc).setHeaderValue("Ausgaben");
		verify(tcm).getColumn(4);
		verify(tc).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob die Spaltenüberschriften gesetzt werden können. Neben den
	 * Standard-Spalten wird noch die Spalte "bis" angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderWithDateTo() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_TO)).thenReturn(1);
		
		// Model vorbereiten
		TableColumnModel tcm = mock(TableColumnModel.class);
		TableColumn tc = mock(TableColumn.class);
		
		for (int i = 0; i < _data.getColumnCount(); i++)
			when(tcm.getColumn(i)).thenReturn(tc);
		
		// Header setzen
		_data.setColumnHeader(tcm);
		
		// Überprüfen ob die richtigen Methoden aufgerufen wurden
		verify(tcm).getColumn(0);
		verify(tc).setHeaderValue("Woche");
		verify(tcm).getColumn(1);
		verify(tc).setHeaderValue("bis");
		verify(tcm).getColumn(2);
		verify(tc).setHeaderValue("Einnahmen");
		verify(tcm).getColumn(3);
		verify(tc).setHeaderValue("Ausgaben");
		verify(tcm).getColumn(4);
		verify(tc).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob die Spaltenüberschriften gesetzt werden können. Neben den
	 * Standard-Spalten wird noch die Spalten "von" und "bis" angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderWithDateFromAndDateTo() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_FROM)).thenReturn(1);
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_TO)).thenReturn(1);
		
		// Model vorbereiten
		TableColumnModel tcm = mock(TableColumnModel.class);
		TableColumn tc = mock(TableColumn.class);
		
		for (int i = 0; i < _data.getColumnCount(); i++)
			when(tcm.getColumn(i)).thenReturn(tc);
		
		// Header setzen
		_data.setColumnHeader(tcm);
		
		// Überprüfen ob die richtigen Methoden aufgerufen wurden
		verify(tcm).getColumn(0);
		verify(tc).setHeaderValue("Woche");
		verify(tcm).getColumn(1);
		verify(tc).setHeaderValue("von");
		verify(tcm).getColumn(2);
		verify(tc).setHeaderValue("bis");
		verify(tcm).getColumn(3);
		verify(tc).setHeaderValue("Einnahmen");
		verify(tcm).getColumn(4);
		verify(tc).setHeaderValue("Ausgaben");
		verify(tcm).getColumn(5);
		verify(tc).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob die Einnahmen für die angegebene Woche richtig zurück
	 * gegeben werden.
	 * 
	 * @see datas.ReportWeekData#incoming(int)
	 */
	@Test
	public void testIncoming() {
		for (int i = 1; i < _data.getRowCount(); i++)
			assertEquals(0, _data.incoming(i), 0);
	}
	
	/**
	 * Überprüft, ob die Einnahmen für die 1. Woche richtig ermittelt wurden.
	 * 
	 * @see datas.ReportWeekData#incoming(int)
	 */
	@Test
	public void testIncomingWeekZero() {
		assertEquals(_in, _data.incoming(0), 0.0);
	}
	
	/**
	 * Überprüft, ob die Ausgaben für die angegebene Woche richtig zurück
	 * gegeben werden.
	 * 
	 * @see datas.ReportWeekData#outgoing(int)
	 */
	@Test
	public void testOutgoing() {
		for (int i = 1; i < _data.getRowCount(); i++)
			assertEquals(0, _data.outgoing(i), 0);
	}
	
	/**
	 * Überprüft, ob die Ausgaben für die 1. Woche richtig ermittelt wurden.
	 * 
	 * @see datas.ReportWeekData#incoming(int)
	 */
	@Test
	public void testOutgoingWeekZero() {
		assertEquals(_out, _data.outgoing(0), 0.0);
	}
	
	/**
	 * Überprüft, ob die Differenz für die angegebene Woche richtig zurück
	 * gegeben werden.
	 * 
	 * @see datas.ReportWeekData#deviation(int)
	 */
	@Test
	public void testDeviation() {
		for (int i = 1; i < _data.getRowCount(); i++)
			assertEquals(0, _data.deviation(i), 0.0);
	}
	
	/**
	 * Überprüft, ob die Differenz für die 1. Woche richtig ermittelt wurden.
	 * 
	 * @see datas.ReportWeekData#deviation(int)
	 */
	@Test
	public void testDeviationWeekZero() {
		assertEquals(_in - _out, _data.deviation(0), 0.00);
	}
	
	/**
	 * Überprüft, dass die Spalte 'von' angezeigt werden soll, wenn dies in den
	 * Einstellungen angegeben ist.
	 * 
	 * @see datas.ReportWeekData#drawDateFrom()
	 */
	@Test
	public void testDrawDateFromReturnIsTrueWithOneInPreferences() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_FROM)).thenReturn(1);
		assertTrue(_data.drawDateFrom());
	}
	
	/**
	 * Überprüft, dass die Spalte 'von' nicht angezeigt werden soll, wenn es 
	 * keine Angabe in den Einstellungen gibt.
	 * 
	 * @see datas.ReportWeekData#drawDateFrom()
	 */
	@Test
	public void TestDrawDateFromReturnIsFalse() {
		assertFalse(_data.drawDateFrom());
	}
	
	/**
	 * Überprüft, dass die Spalte 'von' nicht angezeigt werden soll, wenn in den
	 * Einstellungen eine 0 vermerkt ist.
	 * 
	 * @see datas.ReportWeekData#drawDateFrom()
	 */
	@Test
	public void TestDrawDateFromReturnIsFalseWithZeroInPreferences() {
		_rpd.setPreference(ReportWeekData.DRAW_DATE_FROM, 0);
		assertFalse(_data.drawDateFrom());
	}
	
	/**
	 * Überprüft, dass die Spalte 'bis' angezeigt werden soll, wenn dies in den
	 * Einstellungen angegeben ist.
	 * 
	 * @see datas.ReportWeekData#drawDateTo()
	 */
	@Test
	public void testDrawDateToReturnIsTrueWithOneInPreferences() {
		when(_rpd.getPreference(ReportWeekData.DRAW_DATE_TO)).thenReturn(1);
		assertTrue(_data.drawDateTo());
	}
	
	/**
	 * Überprüft, dass die Spalte 'bis' nicht angezeigt werden soll, wenn es 
	 * keine Angabe in den Einstellungen gibt.
	 * 
	 * @see datas.ReportWeekData#drawDateTo()
	 */
	@Test
	public void TestDrawDateToReturnIsFalse() {
		assertFalse(_data.drawDateTo());
	}
	
	/**
	 * Überprüft, dass die Spalte 'bis' nicht angezeigt werden soll, wenn in den
	 * Einstellungen eine 0 vermerkt ist.
	 * 
	 * @see datas.ReportWeekData#drawDateTo()
	 */
	@Test
	public void TestDrawDateToReturnIsFalseWithZeroInPreferences() {
		_rpd.setPreference(ReportWeekData.DRAW_DATE_TO, 0);
		assertFalse(_data.drawDateTo());
	}
	
	/**
	 * Überprüft, ob der 1. Wochentag nicht <b>null</b> ist.
	 * 
	 * @see datas.ReportWeekData#getDateFrom(int)
	 */
	@Test
	public void TestGetDateFromReturnNotNull() {
		assertNotNull(_data.getDateFrom(1));
	}
	
	/**
	 * Überprüft, ob der 1. Wochentag keine leere Zeichenkette ist.
	 * 
	 * @see datas.ReportWeekData#getDateFrom(int)
	 */
	@Test
	public void TestGetDateFromReturnNotEmpty() {
		assertFalse(_data.getDateFrom(1).isEmpty());
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn die Woche
	 * kleiner 0 ist.
	 * 
	 * @see datas.ReportWeekData#getDateFrom(int)
	 */
	@Test
	public void TestGetDateFromReturnIsEmptyWithMinusOneAsParameter() {
		assertTrue(_data.getDateFrom(-1).isEmpty());
	}
	
	/**
	 * Überprüft, ob der 1. Wochentag richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getDateFrom(int)
	 */
	@Test
	public void TestGetDateFromReturnIsRight() {
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 2);
		assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(gc.getTimeInMillis())),
				_data.getDateFrom(1));
	}
	
	/**
	 * Überprüftb, ob der 1. Tag in 2016 als 1. Tag in der 0. Woche zurück
	 * gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getDareFrom(int)
	 */
	@Test
	public void TestGetDateFromWithZoroAsParameterReturnTheFirstDayIn2016() {
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		gc.set(GregorianCalendar.MONTH, GregorianCalendar.JANUARY);
		gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
		assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(gc.getTimeInMillis())),
				_data.getDateFrom(0));
	}
	
	/**
	 * Überprüft, ob der letzte Wochentag nicht <b>null</b> ist.
	 * 
	 * @see datas.ReportWeekData#getDateTo(int)
	 */
	@Test
	public void TestGetDateToReturnNotNull() {
		assertNotNull(_data.getDateTo(1));
	}
	
	/**
	 * Überprüft, ob der letzte Wochentag keine leere Zeichenkette ist.
	 * 
	 * @see datas.ReportWeekData#getDateTo(int)
	 */
	@Test
	public void TestGetDateToReturnNotEmpty() {
		assertFalse(_data.getDateTo(1).isEmpty());
	}
	
	/**
	 * Überprüft, ob eine leere Zeichenkette zurück gegeben wird, wenn die Woche
	 * kleiner 0 ist.
	 * 
	 * @see datas.ReportWeekData#getDateTo(int)
	 */
	@Test
	public void TestGetDateToReturnIsEmptyWithMinusOneAsParameter() {
		assertTrue(_data.getDateFrom(-1).isEmpty());
	}
	
	/**
	 * Überprüft, ob der letzte Wochentag richtig zurück gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getDateTo(int)
	 */
	@Test
	public void TestGetDateToReturnIsRight() {
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		assertEquals(DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(gc.getTimeInMillis())),
				_data.getDateTo(1));
	}
	
	/**
	 * Überprüft, ob 0 zurück gegeben wird, wenn eine Woche kleiner 0 angegeben
	 * wird.
	 * 
	 * @see datas.ReportWeekData#getDateFromAsLong(int)
	 */
	@Test
	public void TestGetDateAsLongReturnZeroWithMinusOneAsParamter() {
		assertEquals(0, _data.getDateFromAsLong(-1));
	}
	
	/**
	 * Überprüft, ob der richtige <b>long</b>-Wert zurück gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getDateFromAsLong(int)
	 */
	@Test
	public void TestGetDateFromAsLongReturnIsRight() {
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 2);
		assertEquals(gc.getTimeInMillis(), _data.getDateFromAsLong(1));
	}
	
	/**
	 * Überprüft, ob der richtige <b>long</b>-Wert zurück gegeben wird, wenn
	 * die 0. Woche ausgewählt wird.
	 * 
	 * @see datas.ReportWeekData#getDateFromAsLong(int)
	 */
	@Test
	public void TestGetDateFromAsLongReturnTheFirstDayOf2016() {
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		assertEquals(gc.getTimeInMillis(), _data.getDateFromAsLong(0));
	}
	
	/**
	 * Überprüft, ob 0 zurück gegeben wird, wenn eine Woche kleiner 0 angegeben
	 * wird.
	 * 
	 * @see datas.ReportWeekData#getDateToAsLong(int)
	 */
	@Test
	public void TestGetDateToAsLongReturnZeroWithMinusOneAsParamter() {
		assertEquals(0, _data.getDateToAsLong(-1));
	}
	
	/**
	 * Überprüft, ob der richtige <b>long</b>-Wert zurück gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getDateToAsLong(int)
	 */
	@Test
	public void TestGetDateToAsLongReturnIsRight() {
		GregorianCalendar gc = HelperCalendar.createCalendar(_year);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		assertEquals(gc.getTimeInMillis(), _data.getDateToAsLong(1));
	}
}
