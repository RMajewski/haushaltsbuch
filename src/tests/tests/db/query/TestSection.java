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

package tests.tests.db.query;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.db.query.Section;
import tests.testcase.TestHelper;

/**
 * Enthält die Tests, um die Klasse {@link haushaltsbuch.db.query.Section} zu testen.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestSection extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle "section"
	 */
	private static Section _section;
	
	/**
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("section");

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_section = new Section();
	}
	
	/**
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 * 
	 * @see haushaltsbuch.db.query.Section#Section()
	 */
	@Test
	public void testRightTableName() {
		assertEquals("section", _section.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 * 
	 * @see haushaltsbuch.db.query.Section#Section()
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _section.getColumnNames().get(0));
		assertEquals("name", _section.getColumnNames().get(1));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#createTable()} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testCreateTableReturnIsRight() {
		StringBuilder test = new StringBuilder("CREATE TABLE IF NOT EXISTS " +
				"'section' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'name' TEXT);");
		assertEquals(test.toString(), _section.createTable());
	}

	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_section.insert());
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#insert()} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testInsertReturnHaveSection() {
		assertEquals(1, frequency(_section.insert(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#insert(String)} keine
	 * leere Zeichenkette liefert, auch wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterReturnNotNull() {
		assertStringIsNotNull(_section.insert(null));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#insert(String)} ein
	 * Fragezeichen enhält, wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_section.insert(null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#insert(String)} kein
	 * Fragezeichen zurück gibt.
	 */
	@Test
	public void testInsertStringHasNoQuery() {
		assertEquals(0, frequency(_section.insert("test"), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#insert(String)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testInsertStringReturnHaveSection() {
		assertEquals(1, frequency(_section.insert("Dies ist ein Test"),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#delete(int)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testDeleteReturnHaveSection() {
		assertEquals(1, frequency(_section.delete(100),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#update(int)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testUpdateReturnHaveSection() {
		assertEquals(1, frequency(_section.update(100),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasNoQuery() {
		assertEquals(0, frequency(_section.update(100, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int String)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasId() {
		int id = 100;
		assertEquals(1, frequency(_section.update(id, "test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)} den
	 * übergebenen Namen enthält.
	 */
	@Test
	public void testUpdateStringHasName() {
		String test = new String("test");
		assertEquals(1, frequency(_section.update(100, test), test));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)}
	 * keine leere Zeichenkette liefert, der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_section.update(-1, "name"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält, der Parameter für die ID ist
	 * -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterHasOneQuery() {
		assertEquals(1, frequency(_section.update(-1, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int String)} die
	 * übergebene ID in der Rückgabe nicht enthält. Der Parameter ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParamterHasNoId() {
		int id = -1;
		assertEquals(0, frequency(_section.update(id, "test"), new String("\"" + id + "\"")));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>
	 */
	@Test
	public void testUpdateStringNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_section.update(100, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>. Der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAndNullAsParametersHasTwoQueries() {
		assertEquals(2, frequency(_section.update(-1, null), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#update(int, String)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testUpdateStringReturnHaveSection() {
		assertEquals(1, frequency(_section.update(100, "Dies ist ein Test"),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Section#select()} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testSelectReturnHaveSection() {
		assertEquals(1, frequency(_section.select(),_table));
	}
}
