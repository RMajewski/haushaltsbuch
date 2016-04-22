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
}
