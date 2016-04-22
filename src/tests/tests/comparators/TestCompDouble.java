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

import haushaltsbuch.comparators.CompDouble;

/**
 * Testet den Comparator {@link comparators.CompDouble}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class TestCompDouble {

	/**
	 * Testet ob -1 raus kommt, wenn die 1. Zahl kleiner als die 2. Zahl is.
	 * 
	 * @see comparators.CompDouble#compare(datas.Data, datas.Data)
	 */
	@Test
	public void testCompareLesser() {
		double data1 = 1, data2 = 2;
		assertEquals(-1, new CompDouble().compare(data1, data2));
	}

	/**
	 * Testet ob 0 raus kommt, wenn die 1. Zahl gleich der 2. Zahl is.
	 * 
	 * @see comparators.CompDouble#compare(datas.Data, datas.Data)
	 */
	@Test
	public void testCompareEqual() {
		double data1 = 1l, data2 = 1l;
		assertEquals(0, new CompDouble().compare(data1, data2));
	}

	/**
	 * Testet ob 1 raus kommt, wenn die 1 Zahl größer als die 2. Zahl is.
	 * 
	 * @see comparators.CompDouble#compare(datas.Data, datas.Data)
	 */
	@Test
	public void testCompareBigger() {
		double data1 = 2, data2 = 1;
		assertEquals(1, new CompDouble().compare(data1, data2));
	}
}
