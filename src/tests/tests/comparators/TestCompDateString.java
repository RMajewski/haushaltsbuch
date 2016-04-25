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

package tests.tests.comparators;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.comparators.CompDateString;
import haushaltsbuch.helper.HelperCalendar;

/**
 * Testet die Klasse {@link haushaltsbuch.comparators.CompStringDate}
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestCompDateString {
	
	/**
	 * Speichert das 1. Datum.
	 */
	private String _date1;
	
	/**
	 * Speichert das 2. Datum
	 */
	private String _date2;
	
	/**
	 * Speichert die Instanz vom Comparator
	 */
	private CompDateString _comp;

	/**
	 * Initalisieren der Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		GregorianCalendar gc = HelperCalendar.createCalendar(2016);
		gc.set(GregorianCalendar.MONTH, 3);
		gc.set(GregorianCalendar.DAY_OF_MONTH, 10);
		_date1 = HelperCalendar.dateToString(gc.getTimeInMillis());
		gc.set(GregorianCalendar.MONTH, 4);
		_date2 = HelperCalendar.dateToString(gc.getTimeInMillis());
		_comp = new CompDateString();
	}

	/**
	 * Testet, ob das 1. Datum kleiner als das 2. Datum ist.
	 */
	@Test
	public void testDateOneLowerAsDateTwo() {
		assertEquals(-1, _comp.compare(_date1, _date2));
	}

	/**
	 * Testet, ob das 2. Datum größer als das 1. Datum ist.
	 */
	@Test
	public void testDateOneBiggerAsDateTwo() {
		assertEquals(1, _comp.compare(_date2, _date1));
	}
	
	/**
	 * Testet, ob 1. Datum genaus groß ist, wie das 1. Datum.
	 */
	@Test
	public void testDateOneEqualsDateOne() {
		assertEquals(0, _comp.compare(_date1, _date1));
	}
	
	/**
	 * Überprüft, ob ein Fehler auftritt, wenn als 1. Datum keine Zeichenkette
	 * mit einen Datums-Wert übergeben wird.
	 */
	@Test(expected = ClassCastException.class)
	public void testThrowsClassCastExceptionWhenDate1NotDate() {
		_comp.compare("Test", _date2);
	}
	
	/**
	 * Überprüft, ob ein Fehler auftritt, wenn als 2. Datum keine Zeichenkette
	 * mit einen Datums-Wert übergeben wird.
	 */
	@Test(expected = ClassCastException.class)
	public void testThrowsClassCastExceptionWhenDate2NotDate() {
		_comp.compare(_date1, "Test");
	}
}
