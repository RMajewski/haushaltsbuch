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

import haushaltsbuch.db.query.Payment;
import tests.testcase.TestHelper;

/**
 * Enthält die Tests, um die Klasse {@link haushaltsbuch.db.query.Payment} 
 * zu testen.
 * 
 * @author René Majewski
 * 
 * @version 0.4
 * @since 0.1
 */
public class TestPayment extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle "section"
	 */
	private static Payment _payment;
	
	/**
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("payment");

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_payment = new Payment();
	}
	
	/**
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 * 
	 * @see haushaltsbuch.db.query.Payment#Section()
	 */
	@Test
	public void testRightTableName() {
		assertEquals(_table, _payment.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 * 
	 * @see haushaltsbuch.db.query.Payment#Section()
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _payment.getColumnNames().get(0));
		assertEquals("name", _payment.getColumnNames().get(1));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#createTable()} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testCreateTableReturnIsRight() {
		StringBuilder test = new StringBuilder("CREATE TABLE IF NOT EXISTS " +
				"'payment' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'name' TEXT);");
		assertEquals(test.toString(), _payment.createTable());
	}

	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_payment.insert());
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#insert()} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testInsertReturnHaveSection() {
		assertEquals(1, frequency(_payment.insert(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#insert(String)} keine
	 * leere Zeichenkette liefert, auch wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterReturnNotNull() {
		assertStringIsNotNull(_payment.insert(null));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#insert(String)} ein
	 * Fragezeichen enhält, wenn der Parameter <b>null</b> ist.
	 */
	@Test
	public void testInsertStringWithNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_payment.insert(null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#insert(String)} kein
	 * Fragezeichen zurück gibt.
	 */
	@Test
	public void testInsertStringHasNoQuery() {
		assertEquals(0, frequency(_payment.insert("test"), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#insert(String)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testInsertStringReturnHaveSection() {
		assertEquals(1, frequency(_payment.insert("Dies ist ein Test"),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#delete(int)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testDeleteReturnHaveSection() {
		assertEquals(1, frequency(_payment.delete(100),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testUpdateReturnHaveSection() {
		assertEquals(1, frequency(_payment.update(100),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasNoQuery() {
		assertEquals(0, frequency(_payment.update(100, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int String)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStringHasId() {
		int id = 100;
		assertEquals(1, frequency(_payment.update(id, "test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)} den
	 * übergebenen Namen enthält.
	 */
	@Test
	public void testUpdateStringHasName() {
		String test = new String("test");
		assertEquals(1, frequency(_payment.update(100, test), test));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)}
	 * keine leere Zeichenkette liefert, der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_payment.update(-1, "name"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält, der Parameter für die ID ist
	 * -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParameterHasOneQuery() {
		assertEquals(1, frequency(_payment.update(-1, "test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int String)} die
	 * übergebene ID in der Rückgabe nicht enthält. Der Parameter ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAsParamterHasNoId() {
		int id = -1;
		assertEquals(0, frequency(_payment.update(id, "test"), new String("\"" + id + "\"")));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>
	 */
	@Test
	public void testUpdateStringNullAsParameterHasOneQuery() {
		assertEquals(1, frequency(_payment.update(100, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)}
	 * kein Fragezeichen in der Rückgabe enthält. Der Parameter für den Namen
	 * is <b>null</b>. Der Parameter für die ID ist -1.
	 */
	@Test
	public void testUpdateStringMinusOneAndNullAsParametersHasTwoQueries() {
		assertEquals(2, frequency(_payment.update(-1, null), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#update(int, String)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testUpdateStringReturnHaveSection() {
		assertEquals(1, frequency(_payment.update(100, "Dies ist ein Test"),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#select()} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testSelectReturnHaveSection() {
		assertEquals(1, frequency(_payment.select(),_table));
	}
}
