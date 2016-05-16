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

import org.junit.Test;

import haushaltsbuch.comparators.CompSum;

/**
 * Testet den Comparator {@link haushaltsbuch.comparators.CompSum}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.3
 */
public class TestCompSum {
	/**
	 * Testet ob -1 raus kommt, wenn sum1 kleiner als sum2 ist.
	 * 
	 * @see haushaltsbuch.comparators.CompSum#compare(String, String)
	 */
	@Test
	public void testCompareLesser() {
		String sum1 = "9.88", sum2 = "10.00";
		assertEquals(-1, new CompSum().compare(sum1, sum2));
	}

	/**
	 * Testet ob 0 raus kommt, wenn sum1 gleich sum2 is.
	 * 
	 * @see haushaltsbuch.comparators.CompSum#compare(String, String)
	 */
	@Test
	public void testCompareEqual() {
		String sum1 = "19.00", sum2 = "19.00";
		assertEquals(0, new CompSum().compare(sum1, sum2));
	}

	/**
	 * Testet ob 1 raus kommt, wenn die sum1 größer als sum2 is.
	 * 
	 * @see haushaltsbuch.comparators.CompSum#compare(String, String)
	 */
	@Test
	public void testCompareBigger() {
		String sum1 = "19.88", sum2 = "2.00";
		assertEquals(1, new CompSum().compare(sum1, sum2));
	}
	/**
	 * Testet ob -1 raus kommt, wenn sum1 kleiner als sum2 ist.
	 * 
	 * @see haushaltsbuch.comparators.CompSum#compare(String, String)
	 */
	@Test
	public void testCompareLesserWithComma() {
		String sum1 = "9,88", sum2 = "10,00";
		assertEquals(-1, new CompSum().compare(sum1, sum2));
	}

	/**
	 * Testet ob 0 raus kommt, wenn sum1 gleich sum2 is.
	 * 
	 * @see haushaltsbuch.comparators.CompSum#compare(String, String)
	 */
	@Test
	public void testCompareEqualWithComma() {
		String sum1 = "19,00", sum2 = "19,00";
		assertEquals(0, new CompSum().compare(sum1, sum2));
	}

	/**
	 * Testet ob 1 raus kommt, wenn die sum1 größer als sum2 is.
	 * 
	 * @see haushaltsbuch.comparators.CompSum#compare(String, String)
	 */
	@Test
	public void testCompareBiggerWithComma() {
		String sum1 = "19,88", sum2 = "2,00";
		assertEquals(1, new CompSum().compare(sum1, sum2));
	}
}
