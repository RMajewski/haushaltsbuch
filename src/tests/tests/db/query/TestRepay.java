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

import haushaltsbuch.db.query.Repay;
import tests.testcase.TestHelper;

/**
 * Enthält die Tests, um die Klasse {@link haushaltsbuch.db.query.Repay} 
 * zu testen.
 * 
 * @author René Majewski
 * 
 * @version 0.4
 * @since 0.1
 */
public class TestRepay extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle "repay"
	 */
	private static Repay _repay;
	
	/**
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("repay");

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_repay = new Repay();
	}
	
	/**
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 * 
	 * @see haushaltsbuch.db.query.Payment#Section()
	 */
	@Test
	public void testRightTableName() {
		assertEquals(_table, _repay.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 * 
	 * @see haushaltsbuch.db.query.Payment#Section()
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _repay.getColumnNames().get(0));
		assertEquals("outstandingid", _repay.getColumnNames().get(1));
		assertEquals("money_detailsid", _repay.getColumnNames().get(2));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Payment#createTable()} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testCreateTableReturnIsRight() {
		StringBuilder test = new StringBuilder("CREATE TABLE IF NOT EXISTS " +
				"'oustanding' ('id' INTEGER PRIMARY KEY AUTOINCREMENT," +
				"'outstandingid' INTEGER NOT NULL," +
				"'money_detailsid' INTEGER NOT NULL);");
		assertEquals(test.toString(), _repay.createTable());
	}

	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Payment#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_repay.insert());
	}
}
