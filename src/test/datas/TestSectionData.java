package test.datas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datas.SectionData;

/**
 * Testet die Klasse {@link datas.SectionData}
 * 
 * @author René Majewski
 */
public class TestSectionData {
	/**
	 * Speichert die Klasse für die Kategorie-Daten
	 */
	private static SectionData _data;
	
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
		_data = new SectionData(_id, _name);
	}
	
	/**
	 * Testet, ob der Konstruktor ohne Argemente richtig initalisiert.
	 * ({@link datas.SectionData#SectionData()}) 
	 *
	 */
	@Test
	public void testSectionData() {
		_data = new SectionData();
		assertEquals(-1, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID richtig initalisiert.
	 * ({@link datas.SectionData#SectionData(int)})
	 */
	@Test
	public void testSectionDataInt() {
		_data = new SectionData(2);
		assertEquals(2, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und den übergebenen
	 * Namen richtig arbeitet.
	 * ({@link datas.SectionData#SectionData(int, String)})
	 */
	@Test
	public void testSectionDataIntString() {
		assertEquals(_id, _data.getId());
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und einen null-Objekt
	 * als Name richtig arbeitet (leeren Namen erzeugt)
	 */
	@Test
	public void testSectionDataIntStringWithNull() {
		_data = new SectionData(_id, null);
		assertEquals(_id, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob die ID richtig zurück gegeben wird.
	 * {@link datas.SectionData#getId()}
	 */
	@Test
	public void testGetId() {
		assertEquals(_id, _data.getId());
	}
	
	/**
	 * Testet, ob die ID richtig übernommen wird.
	 * {@link datas.SectionData#setId(int)}
	 */
	@Test
	public void testSetId() {
		_data.setId(5400);
		assertEquals(5400, _data.getId());
	}
	
	/**
	 * Testet, ob der Namen richtig zurück gegeben wird.
	 * {@link datas.SectionData#getName()}
	 */
	@Test
	public void testGetName() {
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Name richtig übernommen wird.
	 * {@link datas.SectionData#setName(String)}
	 */
	@Test
	public void testSetName() {
		String name = new String("Neuer Name");
		_data.setName(name);
		assertEquals(name, _data.getName());
	}
	
	/**
	 * Testet, ob ein leerer Name erzeugt wird, wenn null übergeben wird.
	 * {@link datas.SectionData#setName(String)}
	 */
	@Test
	public void testSetNameWithNull() {
		_data.setName(null);
		assertEquals(new String(), _data.getName());
	}
}
