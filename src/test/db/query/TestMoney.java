package test.db.query;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.query.Money;
import test.TestHelper;

/**
 * Testet die Klasse {@link db.query.Money}
 * 
 * @author René Majewski
 *
 */
public class TestMoney extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle 'money'.
	 */
	private Money _money;

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_money = new Money();
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#createTable()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testCreateTableReturnNotNull() {
		assertStringIsNotNull(_money.createTable());
	}

	/**
	 * Testet, ob die Methode {@link db.query.Money#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_money.insert());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert()} drei
	 * Fragezeichen enthält.
	 */
	@Test
	public void testInsertHasThreeQuery() {
		assertEquals(3, frequency(_money.insert(), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(long, boolean, String)
	 * keine leere Zeichenkette liefert.
	 */
	@Test
	public void testInsertDateBooleanStringReturnNotNull() {
		assertStringIsNotNull(_money.insert(7919156, true, "Dies ist ein Test"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(long, boolean, String)
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertDataBooleanStringWithNull() {
		assertEquals(1, frequency(_money.insert(768786L,  true, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(long, boolean, String)
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testInsertDataBooleanStringWithEmpty() {
		assertEquals(1, frequency(_money.insert(768786L,  true, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testDeleteReturnNotNull() {
		assertStringIsNotNull(_money.delete(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} kein
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasNoQuery() {
		assertEquals(0, frequency(_money.delete(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasId() {
		assertEquals(1, frequency(_money.delete(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} eine
	 * leere Zeichenkette liefert, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterReturnNull() {
		assertStringIsNotNull(_money.delete(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} ein
	 * Fragezeichen in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasNoQuery() {
		assertEquals(1, frequency(_money.delete(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasId() {
		assertEquals(0, frequency(_money.delete(-1), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testUpdateReturnNotNull() {
		assertStringIsNotNull(_money.update(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} drei
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasOneQuery() {
		assertEquals(3, frequency(_money.update(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasId() {
		assertEquals(1, frequency(_money.update(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} keine leere
	 * Zeichenkette liefert, auch wenn als Parameter -1 übergeben wird
	 */
	@Test
	public void testUpdateMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_money.update(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} vier
	 * Fragezeichen in der Rückgabe enthält, wenn der PArameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasOneQuery() {
		assertEquals(4, frequency(_money.update(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasNoId() {
		assertEquals(0, frequency(_money.update(-1), "-1"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#select()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testSelectReturnNotNull() {
		assertStringIsNotNull(_money.select());
	}
}
