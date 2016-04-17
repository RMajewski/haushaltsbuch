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

package test.comparators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import comparators.CompId;
import datas.Data;

/**
 * Testet den Comparator {@link comparators.CompId}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class TestCompId {

	/**
	 * Testet ob -1 raus kommt, wenn die 1. ID kleiner als die 2. ID is.
	 * 
	 * @see comparators.CompId#compare(datas.Data, datas.Data)
	 */
	@Test
	public void testCompareLesser() {
		Data data1 = new Data(1), data2 = new Data(2);
		assertEquals(-1, new CompId().compare(data1, data2));
	}

	/**
	 * Testet ob 0 raus kommt, wenn die 1. ID gleich der 2. ID is.
	 * 
	 * @see comparators.CompId#compare(datas.Data, datas.Data)
	 */
	@Test
	public void testCompareEqaul() {
		Data data1 = new Data(1), data2 = new Data(1);
		assertEquals(0, new CompId().compare(data1, data2));
	}

	/**
	 * Testet ob 1 raus kommt, wenn die 1 ID größer als die 2. ID is.
	 * 
	 * @see comparators.CompId#compare(datas.Data, datas.Data)
	 */
	@Test
	public void testCompareBigger() {
		Data data1 = new Data(2), data2 = new Data(1);
		assertEquals(1, new CompId().compare(data1, data2));
	}
}
