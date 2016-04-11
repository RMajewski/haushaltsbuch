package test.datas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datas.Data;
import datas.IdNameData;

/**
 * Teste die Klasse {@link datas.IdNameData}
 * 
 * @author René Majewski
 */
public class TestIdNameData {
	/**
	 * Speichert die Klasse für die Daten
	 */
	private static IdNameData _data;
	
	/**
	 * Speichert den Namen
	 */
	private static String _name;
	
	/**
	 * Speichert die ID
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
		_data = new IdNameData(_id, _name);
	}
	
	/**
	 * Testet ob die Klasse {@link datas.IdNameData} eine Instanz von der
	 * Klasse {@link datas.Data} ist.
	 */
	@Test
	public void testIdNameDataInstanceOfData() {
		assertTrue(_data instanceof Data);
	}
	
	/**
	 * Testet, ob der Konstruktor ohne Argemente richtig initalisiert.
	 * ({@link datas.IdNameData#IdNameData()}) 
	 *
	 */
	@Test
	public void testIdNameData() {
		_data = new IdNameData();
		assertEquals(-1, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID richtig initalisiert.
	 * ({@link datas.IdNameData#IdNameData(int)})
	 */
	@Test
	public void testIdNameDataInt() {
		_data = new IdNameData(2);
		assertEquals(2, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und den übergebenen
	 * Namen richtig arbeitet.
	 * ({@link datas.IdNameData#IdNameData(int, String)})
	 */
	@Test
	public void testIdNameDataIntString() {
		assertEquals(_id, _data.getId());
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und einen null-Objekt
	 * als Name richtig arbeitet (leeren Namen erzeugt)
	 */
	@Test
	public void testIdNameDataIntStringWithNull() {
		_data = new IdNameData(_id, null);
		assertEquals(_id, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Namen richtig zurück gegeben wird.
	 * {@link datas.IdNameData#getName()}
	 */
	@Test
	public void testGetName() {
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Name richtig übernommen wird.
	 * {@link datas.IdNameData#setName(String)}
	 */
	@Test
	public void testSetName() {
		String name = new String("Neuer Name");
		_data.setName(name);
		assertEquals(name, _data.getName());
	}
	
	/**
	 * Testet, ob ein leerer Name erzeugt wird, wenn null übergeben wird.
	 * {@link datas.IdNameData#setName(String)}
	 */
	@Test
	public void testSetNameWithNull() {
		_data.setName(null);
		assertEquals(new String(), _data.getName());
	}
}
