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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.IdNameData;

/**
 * Teste die Klasse {@link haushaltsbuch.datas.IdNameData}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
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
	 * Testet ob die Klasse {@link haushaltsbuch.datas.IdNameData} eine Instanz von der
	 * Klasse {@link haushaltsbuch.datas.Data} ist.
	 */
	@Test
	public void testIdNameDataInstanceOfData() {
		assertTrue(_data instanceof Data);
	}
	
	/**
	 * Testet, ob der Konstruktor ohne Argemente richtig initalisiert.
	 * ({@link haushaltsbuch.datas.IdNameData#IdNameData()}) 
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
	 * ({@link haushaltsbuch.datas.IdNameData#IdNameData(int)})
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
	 * ({@link haushaltsbuch.datas.IdNameData#IdNameData(int, String)})
	 */
	@Test
	public void testIdNameDataIntString() {
		assertEquals(_id, _data.getId());
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID und einen null-Objekt
	 * als Name richtig arbeitet (leeren Namen erzeugt)
	 * 
	 * @see haushaltsbuch.datas.IdNameData#IdNameData(int, String)
	 */
	@Test
	public void testIdNameDataIntStringWithNull() {
		_data = new IdNameData(_id, null);
		assertEquals(_id, _data.getId());
		assertEquals(new String(), _data.getName());
	}
	
	/**
	 * Testet, ob der Namen richtig zurück gegeben wird.
	 * {@link haushaltsbuch.datas.IdNameData#getName()}
	 */
	@Test
	public void testGetName() {
		assertEquals(_name, _data.getName());
	}
	
	/**
	 * Testet, ob der Name richtig übernommen wird.
	 * {@link haushaltsbuch.datas.IdNameData#setName(String)}
	 */
	@Test
	public void testSetName() {
		String name = new String("Neuer Name");
		_data.setName(name);
		assertEquals(name, _data.getName());
	}
	
	/**
	 * Testet, ob ein leerer Name erzeugt wird, wenn null übergeben wird.
	 * {@link haushaltsbuch.datas.IdNameData#setName(String)}
	 */
	@Test
	public void testSetNameWithNull() {
		_data.setName(null);
		assertEquals(new String(), _data.getName());
	}
}
