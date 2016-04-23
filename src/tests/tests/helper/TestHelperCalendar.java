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

package tests.tests.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.helper.HelperCalendar;

/**
 * Implementiert Tests für die Klasse {@link helper.HelperCalendar}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestHelperCalendar {
	/**
	 * Speichert die Instanz des Kalenders
	 */
	private GregorianCalendar _cal;
	
	/**
	 * Speichert das Jahr
	 */
	private int _year;

	/**
	 * Initalisiert die Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_year = 2016;
		_cal = HelperCalendar.createCalendar(_year);
	}

	/**
	 * Testet, ob die Kalender-Instanz nicht null ist.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreatecalendarReturnNotNull() {
		assertNotNull(_cal);
	}
	
	/**
	 * Testet, ob das Jahr in der Kalender-Instanz richtig gesetzt wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightYear() {
		assertEquals(_year, _cal.get(GregorianCalendar.YEAR));
	}

	
	/**
	 * Testet, ob der Monat in der Kalender-Instanz richtig gesetzt wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightMonth() {
		assertEquals(GregorianCalendar.JANUARY, 
				_cal.get(GregorianCalendar.MONTH));
	}
	
	/**
	 * Testet, ob der Tag in der Kalender-Instanz richtig gesetzt wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightDay() {
		assertEquals(1, _cal.get(GregorianCalendar.DAY_OF_MONTH));
	}
	
	/**
	 * Testet, ob die Stunde in der Kalender-Instanz richtig gesetzt wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightHour() {
		assertEquals(0, _cal.get(GregorianCalendar.HOUR));
	}
	
	/**
	 * Testet, ob die Minute in der Kalender-Instanz richtig gesetzt wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightMinute() {
		assertEquals(0, _cal.get(GregorianCalendar.MINUTE));
	}
	
	/**
	 * Testet, ob die Sekunde in der Kalender-Instanz richtig gesetzt wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightSecond() {
		assertEquals(0, _cal.get(GregorianCalendar.SECOND));
	}
	
	/**
	 * Testet, ob die Millisekunde in der Kalender-Instanz richtig gesetzt
	 * wurde.
	 * 
	 * @see helper.HelperCalendar#createCalendar(int)
	 */
	@Test
	public void testCreateCalendarReturnHasRightHourMilliSecond() {
		assertEquals(0, _cal.get(GregorianCalendar.MILLISECOND));
	}
	
	/**
	 * Testet, ob der angegeben long-Wert richtig in eine Lesbare Zeichenkette
	 * umgewandelt wird.
	 * 
	 * @see helper.HelperCalendar#dateToString(long)
	 */
	@Test
	public void testDateToString() {
		assertEquals("01.01.2016", 
				HelperCalendar.dateToString(_cal.getTimeInMillis()));
	}
	
	/**
	 * Testet, ob der zurück gegbene Wert richtig ist. Es werden die 12 
	 * verschiedenen Möglichkeiten getestet.
	 * 
	 * @see helper.HelperCalendar#enStringToMonth(String)
	 */
	@Test
	public void testEnStringToMonthReturnRigth() {
		assertEquals(0, HelperCalendar.enStringToMonth("January"));
		assertEquals(1, HelperCalendar.enStringToMonth("February"));
		assertEquals(2, HelperCalendar.enStringToMonth("March"));
		assertEquals(3, HelperCalendar.enStringToMonth("April"));
		assertEquals(4, HelperCalendar.enStringToMonth("May"));
		assertEquals(5, HelperCalendar.enStringToMonth("June"));
		assertEquals(6, HelperCalendar.enStringToMonth("July"));
		assertEquals(7, HelperCalendar.enStringToMonth("August"));
		assertEquals(8, HelperCalendar.enStringToMonth("September"));
		assertEquals(9, HelperCalendar.enStringToMonth("October"));
		assertEquals(10, HelperCalendar.enStringToMonth("November"));
		assertEquals(11, HelperCalendar.enStringToMonth("December"));
	}
	
	/**
	 * Testet, ob bei einem falschen Manatsnamen -1 zurück gegeben wird.
	 * 
	 * @see helper.HelperCalendar#enStringToMonth(String)
	 */
	@Test
	public void testEnStringToMonthWrongParameterReturnMinusOne() {
		assertEquals(-1, HelperCalendar.enStringToMonth("test"));
	}
	
	/**
	 * Testet, ob bei einer leeren Zeichenkette als Paramter -1 zurück gegeben
	 * wird.
	 * 
	 * @see helper.HelperCalendar#enStringToMonth(String)
	 */
	@Test
	public void testEnStringToMonthEmptyAsParameterReturnMinusOne() {
		assertEquals(-1, HelperCalendar.enStringToMonth(new String()));
	}
	
	/**
	 * Testet, ob bei einer <b>null</b> als Paramter -1 zurück gegeben wird.
	 * 
	 * @see helper.HelperCalendar#enStringToMonth(String)
	 */
	@Test
	public void testEnStringToMonthNullAsParameterReturnMinusOne() {
		assertEquals(-1, HelperCalendar.enStringToMonth(null));
	}
	
	/**
	 * Testet, ob die richtigen Monatsnamen zurück gegeben werden.
	 * 
	 * @see haushaltsbuch.helper.HelperCalendar#enMonthToString(int)
	 */
	@Test
	public void testEnMonthToStringReturnRight() {
		assertEquals("January", HelperCalendar.enMonthToString(0));
		assertEquals("February", HelperCalendar.enMonthToString(1));
		assertEquals("March", HelperCalendar.enMonthToString(2));
		assertEquals("April", HelperCalendar.enMonthToString(3));
		assertEquals("May", HelperCalendar.enMonthToString(4));
		assertEquals("June", HelperCalendar.enMonthToString(5));
		assertEquals("July", HelperCalendar.enMonthToString(6));
		assertEquals("August", HelperCalendar.enMonthToString(7));
		assertEquals("September", HelperCalendar.enMonthToString(8));
		assertEquals("October", HelperCalendar.enMonthToString(9));
		assertEquals("November", HelperCalendar.enMonthToString(10));
		assertEquals("December", HelperCalendar.enMonthToString(11));
	}
	
	/**
	 * Testet, ob eine leere Zeichenkette zurück gegeben wird, wenn der Monat
	 * außerhalb des Bereiches liegt.
	 */
	@Test
	public void testEnMonthToStringWithWrongParameterReturnIsEmptyString() {
		assertTrue(HelperCalendar.enMonthToString(-1).isEmpty());
		assertTrue(HelperCalendar.enMonthToString(12).isEmpty());
	}
}
