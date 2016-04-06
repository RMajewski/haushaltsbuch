package test.datas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import datas.CategoryData;

/**
 * Testet die CategoryData-Klasse
 * 
 * @author René Majewski
 *
 */
public class TestCategoryData {
	/**
	 * Speichert die Klasse für die Kategorie-Daten
	 */
	private static CategoryData _data;
	
	/**
	 * Speichert den Namen der Kategorie
	 */
	private static String _name;
	
	/**
	 * Speichert die ID der Kategorie
	 */
	private static int _id;
	
	/**
	 * Initalisiert jeden einezelnen Test
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_id = 100;
		_name = "Test";
		_data = new CategoryData(_id, _name);
	}
	
	/**
	 * Testet, ob der Konstruktor ohne Argemente richtig initalisiert.
	 * ({@link datas.CategoryData#CategoryData()}) 
	 *
	 */
	@Test
	public void testCategoryData() {
		_data = new CategoryData();
		assertEquals(-1, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID richtig initalisiert.
	 * ({@link datas.CategoryData#CategoryData(int)})
	 */
	@Test
	public void testCategoryDataInt() {
		_data = new CategoryData(2);
		assertEquals(2, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und den übergebenen
	 * Namen richtig arbeitet.
	 * ({@link datas.CategoryData#CategoryData(int, java.String)})
	 */
	@Test
	public void testCategoryDataIntString() {
		assertEquals(_id, _data.getId());
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und einen null-Objekt
	 * als Name richtig arbeitet (leeren Namen erzeugt)
	 */
	@Test
	public void testCategoryDataIntStringWithNull() {
		_data = new CategoryData(_id, null);
		assertEquals(_id, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob die ID richtig zurück gegeben wird.
	 * {@link datas.CategoryData#getId()}
	 */
	@Test
	public void testGetId() {
		assertEquals(_id, _data.getId());
	}
	
	/**
	 * Testet, ob die ID richtig übernommen wird.
	 * {@link datas.CategoryData#setId(int)}
	 */
	@Test
	public void testSetId() {
		_data.setId(5400);
		assertEquals(5400, _data.getId());
	}
	
	/**
	 * Testet, ob der Namen richtig zurück gegeben wird.
	 * {@link datas.CategoryData#getName()}
	 */
	@Test
	public void testGetName() {
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Name richtig übernommen wird.
	 * {@link datas.CategoryData#setName(java.String)}
	 */
	@Test
	public void testSetName() {
		String name = new String("Neuer Name");
		_data.setName(name);
		assertEquals(name, _data.getName());
	}
	
	/**
	 * Testet, ob ein leerer Name erzeugt wird, wenn null übergeben wird.
	 * {@link datas.CategoryData#setName(java.String)}
	 */
	@Test
	public void testSetNameWithNull() {
		_data.setName(null);
		assertEquals(new String(), _data.getName());
	}
}
