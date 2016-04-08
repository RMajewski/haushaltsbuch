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
	 * Testet, ob die Methode {@link db.query.Money#insert()} ein
	 * Fragezeichen enthält.
	 */
	@Test
	public void testInsertHasAQuery() {
		assertEquals(1, frequency(_money.insert(), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(String)} keine
	 * leere Zeichenkette liefert, auch wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterReturnNotNull() {
		assertStringIsNotNull(_money.insert(null));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(String)} ein
	 * Fragezeichen enhält, wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_money.insert(null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(String)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testInsertStringReturnNotNull() {
		assertStringIsNotNull(_money.insert("test"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(String)} kein
	 * Fragezeichen zurück gibt.
	 */
	@Test
	public void testInsertStringHasNoQuery() {
		assertEquals(0, frequency(_money.insert("test"), "?"));
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
	 * Testet, ob die Methode {@link db.query.Money#update(int)} ein
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasOneQuery() {
		assertEquals(1, frequency(_money.update(100), "?"));
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
	 * Testet, ob die Methode {@link db.query.Money#update(int)} zwei
	 * Fragezeichen in der Rückgabe enthält, wenn der PArameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasOneQuery() {
		assertEquals(2, frequency(_money.update(-1), "?"));
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
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)}
	 * keine leere Zeichenkette liefert.
	 */
	@Test
	public void testUpdateStringReturnNotNull() {
		assertStringIsNotNull(_money.update(100, "name"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int String)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasId() {
		int id = 100;
		assertEquals(1, frequency(_money.update(id, "test"), new String("\"" + id + "\"")));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)} den
	 * übergebenen Namen enthält.
	 */
	@Test
	public void testUpdateStringHasName() {
		String test = new String("test");
		assertEquals(1, frequency(_money.update(100, test), test));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)}
	 * keine leere Zeichenkette liefert, der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_money.update(-1, "name"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält, der Parameter für die ID ist
	 * -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterHasOneQuery() {
		assertEquals(1, frequency(_money.update(-1, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int String)} die
	 * übergebene ID in der Rückgabe nicht enthält. Der Parameter ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParamterHasNoId() {
		int id = -1;
		assertEquals(0, frequency(_money.update(id, "test"), new String("\"" + id + "\"")));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>
	 */
	@Test
	public void testUpdateStringNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_money.update(100, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>. Der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAndNullAsParametersHasTwoQueries() {
		assertEquals(2, frequency(_money.update(-1, null), "?"));
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
