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

import org.junit.Before;
import org.junit.Test;

import db.query.Category;
import test.TestHelper;

/**
 * Enthält die Tests, um die Klasse {@link db.query.Category} zu testen.
 * 
 * @author René Majewski
 */
public class TestCategory extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfrage der Tabelle 'category'.
	 */
	private Category _category;
	
	/**
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("category");

	/**
	 * Initalisiert die einzelnen Tests.
	 */
	@Before
	public void setUp() {
		_category = new Category();
	}
	
	/**
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 */
	@Test
	public void testRightTableName() {
		assertEquals("category", _category.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _category.getColumnNames().get(0));
		assertEquals("name", _category.getColumnNames().get(1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#createTable()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testCreateTableReturnNotNull() {
		assertStringIsNotNull(_category.createTable());
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#createTable()} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testCreateTableReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.createTable(),_table));
	}

	/**
	 * Testet, ob die Methode {@link db.query.Category#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_category.insert());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#insert()} ein
	 * Fragezeichen enthält.
	 */
	@Test
	public void testInsertHasAQuery() {
		assertEquals(1, frequency(_category.insert(), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#insert()} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testInsertReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.insert(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#insert(String)} keine
	 * leere Zeichenkette liefert, auch wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterReturnNotNull() {
		assertStringIsNotNull(_category.insert(null));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#insert(String)} ein
	 * Fragezeichen enhält, wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_category.insert(null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#insert(String)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testInsertStringReturnNotNull() {
		assertStringIsNotNull(_category.insert("test"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#insert(String)} kein
	 * Fragezeichen zurück gibt.
	 */
	@Test
	public void testInsertStringHasNoQuery() {
		assertEquals(0, frequency(_category.insert("test"), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#insert(String)} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testInsertStringReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.insert("Dies ist ein Test"),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#delete(int)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testDeleteReturnNotNull() {
		assertStringIsNotNull(_category.delete(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#delete(int)} kein
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasNoQuery() {
		assertEquals(0, frequency(_category.delete(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#delete(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasId() {
		assertEquals(1, frequency(_category.delete(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#delete(int)} eine
	 * leere Zeichenkette liefert, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterReturnNull() {
		assertStringIsNotNull(_category.delete(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#delete(int)} ein
	 * Fragezeichen in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasNoQuery() {
		assertEquals(1, frequency(_category.delete(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#delete(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasId() {
		assertEquals(0, frequency(_category.delete(-1), "100"));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#delete(int)} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testDeleteReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.delete(100),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int)} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testUpdateReturnNotNull() {
		assertStringIsNotNull(_category.update(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int)} ein
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasOneQuery() {
		assertEquals(1, frequency(_category.update(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasId() {
		assertEquals(1, frequency(_category.update(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int)} keine leere
	 * Zeichenkette liefert, auch wenn als Parameter -1 übergeben wird
	 */
	@Test
	public void testUpdateMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_category.update(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int)} zwei
	 * Fragezeichen in der Rückgabe enthält, wenn der PArameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasOneQuery() {
		assertEquals(2, frequency(_category.update(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasNoId() {
		assertEquals(0, frequency(_category.update(-1), "-1"));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#update(int)} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testUpdateReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.update(100),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)}
	 * keine leere Zeichenkette liefert.
	 */
	@Test
	public void testUpdateStringReturnNotNull() {
		assertStringIsNotNull(_category.update(100, "name"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasNoQuery() {
		assertEquals(0, frequency(_category.update(100, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int String)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasId() {
		int id = 100;
		assertEquals(1, frequency(_category.update(id, "test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)} den
	 * übergebenen Namen enthält.
	 */
	@Test
	public void testUpdateStringHasName() {
		String test = new String("test");
		assertEquals(1, frequency(_category.update(100, test), test));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)}
	 * keine leere Zeichenkette liefert, der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_category.update(-1, "name"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält, der Parameter für die ID ist
	 * -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterHasOneQuery() {
		assertEquals(1, frequency(_category.update(-1, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int String)} die
	 * übergebene ID in der Rückgabe nicht enthält. Der Parameter ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParamterHasNoId() {
		int id = -1;
		assertEquals(0, frequency(_category.update(id, "test"), new String("\"" + id + "\"")));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>
	 */
	@Test
	public void testUpdateStringNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_category.update(100, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>. Der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAndNullAsParametersHasTwoQueries() {
		assertEquals(2, frequency(_category.update(-1, null), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#update(int, String)} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testUpdateStringReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.update(100, "Dies ist ein Test"),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Category#select()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testSelectReturnNotNull() {
		assertStringIsNotNull(_category.select());
	}
	
	/**
	 * Testest, ob die Methode {@link db.query.Category#select()} in
	 * der Rückgabe 'category' enthält.
	 */
	@Test
	public void testSelectReturnHaveMoneyDetails() {
		assertEquals(1, frequency(_category.select(),_table));
	}
}
