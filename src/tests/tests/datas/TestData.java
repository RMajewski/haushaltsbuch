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

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.Data;

/**
 * Testet die Klasse {@link datas.Data}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestData {
	/**
	 * Speichert die Instanz der Klasse
	 */
	private Data _data;
	
	/**
	 * Speichert die ID
	 */
	private int _id;

	/**
	 * Initalisiert jeden Test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_id = 100;
		_data = new Data(_id);
	}
	
	/**
	 * Testet, ob die Klasse richtig initalisiert wurde.
	 * 
	 * @see datas.Data#Data(int)
	 */
	@Test
	public void testDataInt() {
		assertEquals(_id, _data.getId());
	}
	
	/**
	 * Testet, ob die ID richtig zurück gegeben wird.
	 * {@link datas.IdNameData#getId()}
	 */
	@Test
	public void testGetId() {
		assertEquals(_id, _data.getId());
	}
	
	/**
	 * Testet, ob die ID richtig übernommen wird.
	 * {@link datas.IdNameData#setId(int)}
	 */
	@Test
	public void testSetId() {
		int id = 5400;
		_data.setId(id);
		assertEquals(id, _data.getId());
	}

}
