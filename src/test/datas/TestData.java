package test.datas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datas.Data;

/**
 * Testet die Klasse {@link datas.Data}
 * 
 * @author René Majewski
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
