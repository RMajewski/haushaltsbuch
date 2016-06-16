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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.db.query.Outstanding;
import tests.testcase.TestHelper;

/**
 * Enthält die Tests, um die Klasse {@link haushaltsbuch.db.query.Outstanding} 
 * zu testen.
 * 
 * @author René Majewski
 * 
 * @version 0.4
 * @since 0.1
 */
public class TestOutstanding extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle "outstanding"
	 */
	private static Outstanding _outstanding;
	
	/**
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("outstanding");

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_outstanding = new Outstanding();
	}
	
	/**
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 * 
	 * @see haushaltsbuch.db.query.Payment#Section()
	 */
	@Test
	public void testRightTableName() {
		assertEquals(_table, _outstanding.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 * 
	 * @see haushaltsbuch.db.query.Payment#Section()
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _outstanding.getColumnNames().get(0));
		assertEquals("sectionid", _outstanding.getColumnNames().get(1));
		assertEquals("money", _outstanding.getColumnNames().get(2));
		assertEquals("months", _outstanding.getColumnNames().get(3));
		assertEquals("start", _outstanding.getColumnNames().get(4));
		assertEquals("monthMoney", _outstanding.getColumnNames().get(5));
		assertEquals("comment", _outstanding.getColumnNames().get(6));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#createTable()} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testCreateTableReturnIsRight() {
		StringBuilder test = new StringBuilder("CREATE TABLE IF NOT EXISTS " +
				"'outstanding' ('id' INTEGER PRIMARY KEY AUTOINCREMENT," +
				"'sectionid' INTEGER NOT NULL," +
				"'money' DOUBLE NOT NULL," +
				"'months' INTEGER NOT NULL," +
				"'start' LONG NOT NULL," +
				"'monthMoney' DOUBLE NOT NULL," +
				"'comment' TEXT);");
		assertEquals(test.toString(), _outstanding.createTable());
	}

	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_outstanding.insert());
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#insert(int, double, int, long, double, String)}
	 * kein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertIntDoubleIntLongDoubleStringgWithNull() {
		assertEquals(0, frequency(_outstanding.insert(1, 19.99, 10, 768786L, 
				19.99, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#insert(int, double, int, long, double, String)}
	 * Zeichenkette ist.
	 */
	@Test
	public void testInsertIntDoubleIntLongDoubleStringWithEmpty() {
		assertEquals(0, frequency(_outstanding.insert(1, 19.99, 10, 768786L, 
				19.99, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#insert(int, double, int, long, double, String)}
	 * der Rückgabe 'outstanding' enthält.
	 */
	@Test
	public void testInsertIntDoubleIntLongDoubleStringTableReturnHaveMoney() {
		assertEquals(1, frequency(_outstanding.insert(1, 19.99, 10, 768786L, 
				19.99, "'Dies ist ein Test'"), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#insert(int, int, double, int, long, double, String)}
	 * kein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertIntIntDoubleIntLongDoubleStringgWithNull() {
		assertEquals(0, frequency(_outstanding.insert(1, 1, 19.99, 10, 768786L, 
				19.99, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#insert(int, int, double, int, long, double, String)}
	 * Zeichenkette ist.
	 */
	@Test
	public void testInsertIntIntDoubleIntLongDoubleStringWithEmpty() {
		assertEquals(0, frequency(_outstanding.insert(1, 1, 19.99, 10, 768786L, 
				19.99, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#insert(int, int, double, int, long, double, String)}
	 * der Rückgabe 'outstanding' enthält.
	 */
	@Test
	public void testInsertIntIntDoubleIntLongDoubleStringTableReturnHaveMoney() {
		assertEquals(1, frequency(_outstanding.insert(1, 1, 19.99, 10, 768786L, 
				19.99, "'Dies ist ein Test'"), _table));
	}
}
