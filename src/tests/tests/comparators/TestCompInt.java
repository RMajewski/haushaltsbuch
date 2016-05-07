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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import haushaltsbuch.comparators.CompInt;

/**
 * Testet den Comparator {@link haushaltsbuch.comparators.CompInt}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class TestCompInt {

	/**
	 * Testet ob -1 raus kommt, wenn die 1. Zahl kleiner als die 2. Zahl is.
	 * 
	 * @see haushaltsbuch.comparators.CompInt#compare(Integer, Integer)
	 */
	@Test
	public void testCompareLesser() {
		int data1 = 1, data2 = 2;
		assertEquals(-1, new CompInt().compare(data1, data2));
	}

	/**
	 * Testet ob 0 raus kommt, wenn die 1. Zahl gleich der 2. Zahl is.
	 * 
	 * @see haushaltsbuch.comparators.CompInt#compare(Integer, Integer)
	 */
	@Test
	public void testCompareEqual() {
		int data1 = 1, data2 = 1;
		assertEquals(0, new CompInt().compare(data1, data2));
	}

	/**
	 * Testet ob 1 raus kommt, wenn die 1 Zahl größer als die 2. Zahl is.
	 * 
	 * @see haushaltsbuch.comparators.CompInt#compare(Integer, Integer)
	 */
	@Test
	public void testCompareBigger() {
		int data1 = 2, data2 = 1;
		assertEquals(1, new CompInt().compare(data1, data2));
	}

}
