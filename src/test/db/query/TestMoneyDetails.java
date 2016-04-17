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

package test.db.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 */
	@Test
	public void testRightTableName() {
		assertEquals("money_details", _moneyDetails.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _moneyDetails.getColumnNames().get(0));
		assertEquals("moneyid", _moneyDetails.getColumnNames().get(1));
		assertEquals("categoryid", _moneyDetails.getColumnNames().get(2));
		assertEquals("sectionid", _moneyDetails.getColumnNames().get(3));
		assertEquals("money", _moneyDetails.getColumnNames().get(4));
		assertEquals("comment", _moneyDetails.getColumnNames().get(5));
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
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertIntIntIntDoubleStringWithNull() {
		assertEquals(1, frequency(_moneyDetails.insert(100, 200, 300, 10.34, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#insert(int, int, int, double, String)}
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
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * keine <b>null</b> zurück gibt.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnNotNull() {
		assertFalse(_moneyDetails.update(100, 200, 300, 400, 10.89, "Dies ist ein Test") == null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * keine leere Zeichenkette zurück gibt.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnNotEmpty() {
		assertFalse(_moneyDetails.update(100, 200, 300, 400, 10.89, "Dies ist ein Test").isEmpty());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * keine <b>?</b> in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnNoQuery() {
		assertEquals(0, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveId() {
		int id = 100;
		assertEquals(1, frequency(_moneyDetails.update(id, 200, 300, 400, 10.89, "Dies ist ein Test"), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * die ID des zugehörigen Money-Datensatzes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveMoneyId() {
		int id = 200;
		assertEquals(1, frequency(_moneyDetails.update(100, id, 300, 400, 10.89, "Dies ist ein Test"), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * die ID der Kategorie in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveCategoryId() {
		int id = 300;
		assertEquals(1, frequency(_moneyDetails.update(100, 200, id, 400, 10.89, "Dies ist ein Test"), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * die ID des Geschäftes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveSectionId() {
		int id = 400;
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, id, 10.89, "Dies ist ein Test"), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * den Betrag in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveMoney() {
		double money = 18.76;
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, money, "Dies ist ein Test"), String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * den Kommentar in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveComment() {
		String comment = "Dies ist ein Test!";
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89, comment), comment));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * den Tabellen-Namen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringReturnHaveTableName() {
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89, "Dies ist ein Test"), _table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * ein <b>?</b> enthält, wenn -1 als ID übergeben wird.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringMinusOneAsIdReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.update(-1, 200, 300, 400, 10.89, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * ein <b>?</b> enthält, wenn null als Kommentar übergeben wird.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringNullAsCommentReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double, String)}
	 * ein <b>?</b> enthält, wenn eine leere Zeichenkette als Kommentar
	 * übergeben wird.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleStringEmptyAsCommentReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * keine <b>null</b> zurück gibt.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnNotNull() {
		assertFalse(_moneyDetails.update(100, 200, 300, 400, 10.89) == null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * keine leere Zeichenkette zurück gibt.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnNotEmpty() {
		assertFalse(_moneyDetails.update(100, 200, 300, 400, 10.89).isEmpty());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * keine <b>?</b> in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnNoQuery() {
		assertEquals(0, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89), "?"));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnHaveId() {
		int id = 100;
		assertEquals(1, frequency(_moneyDetails.update(id, 200, 300, 400, 10.89), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * die ID des zugehörigen Money-Datensatzes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnHaveMoneyId() {
		int id = 200;
		assertEquals(1, frequency(_moneyDetails.update(100, id, 300, 400, 10.89), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * die ID der Kategorie in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnHaveCategoryId() {
		int id = 300;
		assertEquals(1, frequency(_moneyDetails.update(100, 200, id, 400, 10.89), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * die ID des Geschäftes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnHaveSectionId() {
		int id = 400;
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, id, 10.89), String.valueOf(id)));
	}
	
	/**
	 * Test, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * den Betrag in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnHaveMoney() {
		double money = 18.76;
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, money), String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * den Tabellen-Namen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleReturnHaveTableName() {
		assertEquals(1, frequency(_moneyDetails.update(100, 200, 300, 400, 10.89), _table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.MoneyDetails#update(int, int, int, int, double)}
	 * ein <b>?</b> enthält, wenn -1 als ID übergeben wird.
	 */
	@Test
	public void testUpdateIntIntIntIntDoubleMinusOneAsIdReturnHasOneQuery() {
		assertEquals(1, frequency(_moneyDetails.update(-1, 200, 300, 400, 10.89), "?"));
	}
}
