package test.db.query;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.query.MoneyDetails;
import test.TestHelper;

/**
 * Testet die Klasse {@link db.query.MoneyDetails}
 * 
 * @author René Majewski
 */
public class TestMoneyDetails extends TestHelper{
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle 'money_details'
	 */
	private MoneyDetails _moneyDetails;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_moneyDetails = new MoneyDetails();
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#createTable()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testCreateTableReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.createTable());
	}

	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.insert());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert()} fünf
	 * Fragezeichen enthält.
	 */
	@Test
	public void testInsertHasFifeQuery() {
		assertEquals(5, frequency(_moneyDetails.insert(), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#delete(int)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testDeleteReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.delete(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#delete(int)} kein
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasNoQuery() {
		assertEquals(0, frequency(_moneyDetails.delete(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#delete(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasId() {
		assertEquals(1, frequency(_moneyDetails.delete(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#delete(int)} eine
	 * leere Zeichenkette liefert, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterReturnNull() {
		assertStringIsNotNull(_moneyDetails.delete(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#delete(int)} ein
	 * Fragezeichen in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasNoQuery() {
		assertEquals(1, frequency(_moneyDetails.delete(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#delete(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasId() {
		assertEquals(0, frequency(_moneyDetails.delete(-1), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testUpdateReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.update(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} drei
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasThreeQuery() {
		assertEquals(3, frequency(_moneyDetails.update(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasId() {
		assertEquals(1, frequency(_moneyDetails.update(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} keine leere
	 * Zeichenkette liefert, auch wenn als Parameter -1 übergeben wird
	 */
	@Test
	public void testUpdateMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.update(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} vier
	 * Fragezeichen in der Rückgabe enthält, wenn der PArameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasForeQuery() {
		assertEquals(4, frequency(_moneyDetails.update(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasNoId() {
		assertEquals(0, frequency(_moneyDetails.update(-1), "-1"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#select()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testSelectReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.select());
	}
}
