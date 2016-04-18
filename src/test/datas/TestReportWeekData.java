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
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.ReportPreferencesData;
import datas.ReportWeekData;
import db.DbController;

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
		_year = 2016;
		_preferences = new ReportPreferencesData(
				ReportPreferencesData.TYPE_MONTH, 1, 0, _year);
		_data = new ReportWeekData(_preferences);
		_categoryCount = 10;
		_sectionCount = 4;
		
		DbController.getInstance().prepaireDatabase();
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
		assertEquals(calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR),
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
		assertEquals(calendar.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR),
				_data.getWeekCount());
	}
	
	/**
	 * Überprüft ob die Wochennummern richtig zurück gegeben werden
	 * 
	 * @see datas.ReportWeekData#getWeekNumber(int)
	 */
	@Test
	public void testGetWeekNumber() {
		for (int i = 0; i < _data.getWeekCount(); i++)
			assertEquals(String.valueOf(i + 1), _data.getWeekNumber(i));
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
	 * Standard-Spalten werden noch die Kategorien und die Geschäfte angezeigt.
	 * 
	 * @see datas.ReportWeekData#setHeader(TableColumnModel)
	 */
	@Test
	public void testSetColumnHeaderWithCategoryAndSections() {
		// Einstellungen
		_preferences.setPreference(ReportWeekData.DRAW_CATEGORIES, 1);
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
}
