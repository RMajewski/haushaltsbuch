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
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("money_details");

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
	 * Testest, ob die Methode {@link db.query.MoneyDetails#createTable()} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testCreateTableReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.createTable(),_table));
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
	 * Testest, ob die Methode {@link db.query.MoneyDetails#insert()} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testInsertTableReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.insert(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * keine leere Zeichenkette liefert.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.insert(100, 200, 300, 10.34, "Dies ist ein Test"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * kein Fragezeichen enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveNoQuery() {
		assertEquals(0, frequency(_moneyDetails.insert(100, 200, 300, 10.34, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * ein Fragezeichen in der Rückgabe enthält, wenn <b>-1</b> als 'moneyid'
	 * übergeben wird.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringMinusOneAsMoneyIdReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.insert(-1, 200, 300, 10.34, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * die 'moneyid' in der Rückgabe enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveMoneyId() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, "Dies ist ein Test"), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * ein Fragezeichen in der Rückgabe enthält, wenn <b>-1</b> als
	 * 'categoryid' übergeben wird.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringMinusOneAsCategoryIdReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.insert(100, -1, 300, 10.34, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * die 'categoryid' in der Rückgabe enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveCategoryId() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, "Dies ist ein Test"), "200"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * ein Fragezeichen in der Rückgabe enthält, wenn <b>-1</b> als
	 * 'sectionid' übergeben wird.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringMinusOneAsSectionIdReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, -1, 10.34, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * die 'sectionid' in der Rückgabe enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveSectionId() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, "Dies ist ein Test"), "300"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringWithNull() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringWithEmpty() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * das Geld in der Rückgabe enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveMoney() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, "Dies ist ein Test"), "10.34"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * den Kommentar in der Rückgabe enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveComment() {
		String test = "Dies ist ein Test";
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, test), test));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.45, "Dies ist ein Test"),_table));
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
	 * Testest, ob die Methode {@link db.query.MoneyDetails#delete(int)} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testDeleteReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.delete(100),_table));
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
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} fünf
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasFiveQuery() {
		assertEquals(5, frequency(_moneyDetails.update(100), "?"));
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
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int)} sechs
	 * Fragezeichen in der Rückgabe enthält, wenn der PArameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasSixQuery() {
		assertEquals(6, frequency(_moneyDetails.update(-1), "?"));
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
	 * Testest, ob die Methode {@link db.query.MoneyDetails#createTable()} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testUpdateReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.createTable(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#select()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testSelectReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.select());
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.MoneyDetails#select()} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testSelectReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.select(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#select(int)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testSelectIntReturnNotNull() {
		assertStringIsNotNull(_moneyDetails.select(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#select(int)} ein
	 * Fragenzeichen enthält, wenn <b>-1</b> als Parameter übergeben wird.
	 */
	@Test
	public void testSelectIntMinusOneHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.select(-1), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.MoneyDetails#select(int)} in
	 * der Rückgabe 'money_details' enthält.
	 */
	@Test
	public void testSelectIntReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_moneyDetails.select(100),_table));
	}
}
