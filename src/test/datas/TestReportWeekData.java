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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.GregorianCalendar;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.MoneyData;
import datas.ReportPreferencesData;
import datas.ReportWeekData;
import db.DbController;
import db.query.Money;

/**
 * Testet die Daten-Klasse {@link datas.ReportWeekData}
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportWeekData {
	/**
	 * Speichert die Daten des Reports
	 */
	private ReportWeekData _data;
	
	/**
	 * Speichert das ausgewählte Jahr
	 */
	private int _year;
	
	/**
	 * Speichert die Einstellungen
	 */
	private ReportPreferencesData _preferences;
	
	/**
	 * Speichert die Anzahl von Kategorien
	 */
	private int _categoryCount;
	
	/**
	 * Speichert die Anzahl von Geschäften
	 */
	private int _sectionCount;
	
	/**
	 * Speichert die Einnahmen für die 1. Woche
	 */
	private double _in;
	
	/**
	 * Speichert die Ausgaben für die 1. Woche
	 */
	private double _out;
	
	
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
		DbController.getInstance().prepaireDatabase();
		
		// Kalender vorbereiten
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 2);
		
		Statement stm = DbController.getInstance().createStatement();
		
		// 1. Eintrag für Einnahmen und Ausgaben
		long date = gc.getTimeInMillis();
		stm.executeUpdate(DbController.queries().money().insert(date, MoneyData.INCOMING, ""));
		stm.executeUpdate(DbController.queries().moneyDetails().insert(1, 1, 1, 1.88, ""));
		stm.executeUpdate(DbController.queries().money().insert(date, MoneyData.OUTGOING, ""));
		stm.executeUpdate(DbController.queries().moneyDetails().insert(2, 1, 1, 1.76, ""));
		
		// 2. Eintrag für Einnahmen und Ausgaben
		gc.set(GregorianCalendar.DAY_OF_WEEK, 4);
		date = gc.getTimeInMillis();
		stm.executeUpdate(DbController.queries().money().insert(date, MoneyData.INCOMING, ""));
		stm.executeUpdate(DbController.queries().moneyDetails().insert(3, 1, 1, 4, ""));
		stm.executeUpdate(DbController.queries().money().insert(date, MoneyData.OUTGOING, ""));
		stm.executeUpdate(DbController.queries().moneyDetails().insert(4, 1, 1, 11, ""));
		
		// 3. Eintrag für Einnahmen und Ausgaben
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		date = gc.getTimeInMillis();
		stm.executeUpdate(DbController.queries().money().insert(date, MoneyData.INCOMING, ""));
		stm.executeUpdate(DbController.queries().moneyDetails().insert(5, 1, 1, 6, ""));
		stm.executeUpdate(DbController.queries().money().insert(date, MoneyData.OUTGOING, ""));
		stm.executeUpdate(DbController.queries().moneyDetails().insert(6, 1, 1, 13, ""));

		_year = 2016;
		_preferences = new ReportPreferencesData(
				ReportPreferencesData.TYPE_MONTH, 1, 0, _year);
		_data = new ReportWeekData(_preferences);
		_categoryCount = 10;
		_sectionCount = 4;
		_in = 11.88;
		_out = 25.76;
	}
	
	/**
	 * Nach den Tests aufräumen
	 */
	@After
	public void tearDown() {
		DbController.getInstance().close();
	}
	
	/**
	 * Überprüft, ob die Konstante {@link datas.ReportWeekData#DRAW_CATEGORIES}
	 * richtig gesetzt ist.
	 */
	@Test
	public void testDrawCategories() {
		assertEquals("Week.drawCategories", ReportWeekData.DRAW_CATEGORIES);
	}
	
	/**
	 * Überprüft, ob die Konstante {@link datas.ReportWeekData#DRAW_SECTIONS}
	 * richtig gesetzt ist.
	 */
	@Test
	public void testDrawSections() {
		assertEquals("Week.drawSections", ReportWeekData.DRAW_SECTIONS);
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
	 * @import java.sql.Statement;
see datas.ReportWeekData#setPreferences(ReportPreferences)
	 */
	@Test
	public void testSetPreferences() {
		int year = 2000;
		_preferences.setYear(year);
		_data.setPreferences(_preferences);
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
				_data.getWeekCount());
	}
	
	/**
	 * Überprüft, ob die Anzahl der Wochennummern für 2000 korrekt zurück
	 * gegeben wird.
	 * 
	 * @see datas.ReportWeekData#getWeekCount()
	 */
	@Test
	public void testGetWeekNumbersFor2000() {
		_year = 2000;
		_preferences.setYear(_year);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(GregorianCalendar.YEAR, _year);
		assertEquals(calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR) + 1,
				_data.getWeekCount());
	}
	
	/**
	 * Überprüft ob die Wochennummern richtig zurück gegeben werden
	 * 
	 * @see datas.ReportWeekData#getWeekNumber(int)
	 */
	@Test
	public void testGetWeekNumber() {
		for (int i = 0; i <= _data.getWeekCount(); i++)
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
	 * einzelnen Kategorien mit angegeben werden
	 * @throws SQLException 
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithCategories() {
		// Einstellungen speichern
		_preferences.setPreference(ReportWeekData.DRAW_CATEGORIES, 1);
		
		try {
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs = stm.executeQuery(DbController.queries().category().count());
			_categoryCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Ermittelte Spalten überprüfen
		assertEquals(4 + _categoryCount, _data.getColumnCount());
	}
	
	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * einzelnen Geschäfte mit angegeben werden
	 * @throws SQLException 
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithSections() {
		// Einstellungen speichern
		_preferences.setPreference(ReportWeekData.DRAW_SECTIONS, 1);
		
		try {
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs = stm.executeQuery(DbController.queries().category().count());
			_sectionCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Ermittelte Spalten überprüfen
		assertEquals(4 + _sectionCount, _data.getColumnCount());
	}
	
	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * einzelnen Kategorien und die einzelnen Geschäfte mit angegeben werden
	 * @throws SQLException 
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithCategoriesAndSections() {
		// Einstellungen speichern
		_preferences.setPreference(ReportWeekData.DRAW_CATEGORIES, 1);
		_preferences.setPreference(ReportWeekData.DRAW_SECTIONS, 1);
		
		try {
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs = stm.executeQuery(DbController.queries().category().count());
			_categoryCount = rs.getInt(1);
			
			rs = stm.executeQuery(DbController.queries().section().count());
			_sectionCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Ermittelte Spalten überprüfen
		assertEquals(4 + _categoryCount + _sectionCount, _data.getColumnCount());
	}

	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * Spalte "bis" mit angegeben wird.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithDateTo() {
		// Einstellungen speichern
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
		
		assertEquals(5, _data.getColumnCount());
	}

	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * Spalte "von" mit angegeben wird.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithDateFrom() {
		// Einstellungen speichern
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
		
		assertEquals(5, _data.getColumnCount());
	}

	/**
	 * Überprüft, ob die Anzahl der Spalten stimmen, wenn zusätzlich noch die
	 * Spalte "von" und die Spalte "bis" mit angegeben werden.
	 * 
	 * @see datas.ReportWeekData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountWithDateFromAndDateTo() {
		// Einstellungen speichern
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
		
		assertEquals(6, _data.getColumnCount());
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
	 * Standard-Spalten werden noch die Kategorien angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderWithCategory() {
		// Einstellungen
		_preferences.setPreference(ReportWeekData.DRAW_CATEGORIES, 1);
		
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
		verify(tc).setHeaderValue("Einnahmen");
		verify(tcm).getColumn(2);
		verify(tc).setHeaderValue("Ausgaben");
		verify(tcm).getColumn(3);
		verify(tc).setHeaderValue("Differenz");
		
		for (int i = 4; i < _data.getColumnCount(); i++) {
			verify(tcm).getColumn(i);
		}
	}
	
	/**
	 * Überprüft, ob die Spaltenüberschriften gesetzt werden können. Neben den
	 * Standard-Spalten werden noch die Geschäfte angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderWithSection() {
		// Einstellungen
		_preferences.setPreference(ReportWeekData.DRAW_SECTIONS, 1);
		
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
		verify(tc).setHeaderValue("Einnahmen");
		verify(tcm).getColumn(2);
		verify(tc).setHeaderValue("Ausgaben");
		verify(tcm).getColumn(3);
		verify(tc).setHeaderValue("Differenz");
		
		for (int i = 4; i < _data.getColumnCount(); i++) {
			verify(tcm).getColumn(i);
		}
	}
	
	/**
	 * Überprüft, ob die Spaltenüberschriften gesetzt werden können. Neben den
	 * Standard-Spalten werden noch die Kategorien, die Geschäfte, die Spalte
	 * 'von' und die Spalte 'bis' angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderAllExtraColumns() {
		// Einstellungen
		_preferences.setPreference(ReportWeekData.DRAW_CATEGORIES, 1);
		_preferences.setPreference(ReportWeekData.DRAW_SECTIONS, 1);
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
		
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
		
		for (int i = 6; i < _data.getColumnCount(); i++) {
			verify(tcm).getColumn(i);
		}
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
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
		
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
		// Einstellungen
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
		
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
		// Einstellungen
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
		
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
		for (int i = 1; i < _data.getWeekCount(); i++)
			assertEquals(0, _data.incoming(i), 0);
	}
	
	/**
	 * Überprüft, ob die Einnahmen für die 1. Woche richtig ermittelt wurden.
	 * 
	 * @see datas.ReportWeekData#incoming(int)
	 */
	@Test
	public void testIncomingWeekOne() {
		assertEquals(_in, _data.incoming(1), 0.01);
	}
	
	/**
	 * Überprüft, ob die Ausgaben für die angegebene Woche richtig zurück
	 * gegeben werden.
	 * 
	 * @see datas.ReportWeekData#outgoing(int)
	 */
	@Test
	public void testOutgoing() {
		for (int i = 1; i < _data.getWeekCount(); i++)
			assertEquals(0, _data.outgoing(i), 0);
	}
	
	/**
	 * Überprüft, ob die Ausgaben für die 1. Woche richtig ermittelt wurden.
	 * 
	 * @see datas.ReportWeekData#incoming(int)
	 */
	@Test
	public void testOutgoingWeekOne() {
		assertEquals(_out, _data.outgoing(1), 0.01);
	}
	
	/**
	 * Überprüft, ob die Differenz für die angegebene Woche richtig zurück
	 * gegeben werden.
	 * 
	 * @see datas.ReportWeekData#deviation(int)
	 */
	@Test
	public void testDeviation() {
		for (int i = 1; i < _data.getWeekCount(); i++)
			assertEquals(0, _data.deviation(i), 0.01);
	}
	
	/**
	 * Überprüft, ob die Differenz für die 1. Woche richtig ermittelt wurden.
	 * 
	 * @see datas.ReportWeekData#deviation(int)
	 */
	@Test
	public void testDeviationWeekOne() {
		assertEquals(_in - _out, _data.deviation(1), 0.01);
	}
	
	/**
	 * Überprüft, dass die Spalte 'von' angezeigt werden soll, wenn dies in den
	 * Einstellungen angegeben ist.
	 * 
	 * @see datas.ReportWeekData#drawDateFrom()
	 */
	@Test
	public void testDrawDateFromReturnIsTrueWithOneInPreferences() {
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
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
		_preferences.setPreference(ReportWeekData.DRAW_DATE_FROM, 0);
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
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
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
		_preferences.setPreference(ReportWeekData.DRAW_DATE_TO, 0);
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
		gc.set(GregorianCalendar.MONTH, GregorianCalendar.JANUARY);
		gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _year);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		assertEquals(gc.getTimeInMillis(), _data.getDateToAsLong(1));
	}
}
