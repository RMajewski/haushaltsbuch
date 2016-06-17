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
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateSectionMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 1, false), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateSectionHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 1, false), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateSectionHasTableName() {
		assertEquals(1, frequency(_outstanding.update(1, -1, false), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateSectionMinusOneAsSectionId() {
		assertEquals(1, frequency(_outstanding.update(1, -1, false), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateSectionHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 1, false), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * die ID des Geschäftes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateSectionHasSectionId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(1, id, false), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateSection() {
		String ret = "UPDATE 'outstanding' SET sectionid = 1 WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 1, false));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateMonthsMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 1, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateMonthsHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 1, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateMonthsHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 1, true), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * die Anzahl der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateMonthsHasSectionId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(1, id, true), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateMonthHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, 1, true), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, boolean)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateMonths() {
		String ret = "UPDATE 'outstanding' SET months = 1 WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 1, true));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateMoneyMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 0.00, false), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateMoneyHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 0.00, false), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateMoneyHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 0.00, false), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * die Anzahl der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateMoneyHasMoney() {
		double money = 99.99;
		assertEquals(1, frequency(_outstanding.update(1, money, false), 
				String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateMoneyHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, 0.00, false), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateMoney() {
		String ret = "UPDATE 'outstanding' SET money = 99.99 WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 99.99, false));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateMonthMoneyMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 0.00, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateMonthMoneyHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 0.00, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateMonthMoneyHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 0.00, true), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * die Anzahl der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateMonthMoneyHasMoney() {
		double money = 99.99;
		assertEquals(1, frequency(_outstanding.update(1, money, true), 
				String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateMonthMoneyHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, 0.00, true), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, double, boolean)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateMonthMoney() {
		String ret = "UPDATE 'outstanding' SET monthmoney = 99.99 WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 99.99, true));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, long)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateStartMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 0l), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, long)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateStartHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 0l), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, long)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateStartHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 0l), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, long)}
	 * das Datum für die 1. Rate enthält.
	 */
	@Test
	public void testUpdateStartHasDate() {
		long date = 710750871807510l;
		assertEquals(1, frequency(_outstanding.update(1, date), 
				String.valueOf(date)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, long)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateStartHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, 0l), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, long)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateStart() {
		String ret = "UPDATE 'outstanding' SET start = 14681 WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 14681l));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateCommentMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, "Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * einen Platzhalter für den Kommentar enthält, wenn als Kommentar
	 * <b>null</b> übergeben wird.
	 */
	@Test
	public void testUpdateCommentNullAsComment() {
		assertEquals(1, frequency(_outstanding.update(1, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * einen Platzhalter für den Kommentar enthält, wenn als Kommentar
	 * eine leere Zeichenkette übergeben wird.
	 */
	@Test
	public void testUpdateCommentEmptyStringAsComment() {
		assertEquals(1, frequency(_outstanding.update(1, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateCommentHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, "Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateCommentHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, "Test"), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * den Kommentar enthält.
	 */
	@Test
	public void testUpdateCommentHasComment() {
		String comment = "Dies ist ein Test";
		assertEquals(1, frequency(_outstanding.update(1, comment), 
				String.valueOf(comment)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateCommentHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, "Test"), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, String)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateComment() {
		String ret = "UPDATE 'outstanding' SET comment = \"Test\" WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, "Test"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateWithoutCommentMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 1, 1.00, 1, 1l, 2.00),
				"?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateWithoutCommentHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, 2.00),
				"?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateWithoutCommentHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 1, 1.00, 1, 1l, 2.00), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * die ID des Geschäftes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateWithoutCommentHasSectionId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(1, id, 1.00, 1, 1l, 2.00), 
				String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * den Betrag für die Höhe der Schuld in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateWithoutCommentHasMoney() {
		double money = 99.99;
		assertEquals(1, frequency(_outstanding.update(1, 1, money, 1, 1l, 2.00), 
				String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * den Betrag für die Höhe der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateWithoutCommentHasMonthMoney() {
		double money = 99.99;
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, money), 
				String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * den Anzahl der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateWithoutCommentHasMonths() {
		int months = 19;
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, months, 1l, 1.00), 
				String.valueOf(months)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * das Datum für die 1. Rate in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateWithoutCommentHasStart() {
		long start = 19;
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, start, 1.00), 
				String.valueOf(start)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateWithoutCommentHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, 1.00),
				_table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdateWithoutComment() {
		String ret = "UPDATE 'outstanding' SET sectionid = 1, money = 1.15, " + 
				"months = 10, start = 180987, monthmoney = 2.87 WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 1, 1.15, 10, 180987, 2.87));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * einen Platzhalter für die ID enthält, wenn als ID -1 übergeben wird.
	 */
	@Test
	public void testUpdateMinusOneAsId() {
		assertEquals(1, frequency(_outstanding.update(-1, 1, 1.00, 1, 1l, 2.00,
				"Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * einen Platzhalter für den Kommentar enthält, wenn als Kommentar
	 * <b>null</b> übergeben wird.
	 */
	@Test
	public void testUpdateNullAsComment() {
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, 2.00,
				null), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * einen Platzhalter für den Kommentar enthält, wenn als Kommentar
	 * eine leere Zeichenkette übergeben wird.
	 */
	@Test
	public void testUpdateEmptyStringAsComment() {
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, 2.00,
				new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * keinen Platzhalter enthält, wenn alles angegeben wurde.
	 */
	@Test
	public void testUpdateHasNoQuery() {
		assertEquals(0, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, 2.00,
				"Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(id, 1, 1.00, 1, 1l, 2.00,
				"Test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * die ID des Geschäftes in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasSectionId() {
		int id = 100;
		assertEquals(1, frequency(_outstanding.update(1, id, 1.00, 1, 1l, 2.00,
				"Test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * den Betrag für die Höhe der Schuld in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasMoney() {
		double money = 99.99;
		assertEquals(1, frequency(_outstanding.update(1, 1, money, 1, 1l, 2.00,
				"Test"), String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * den Betrag für die Höhe der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasMonthMoney() {
		double money = 99.99;
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, money,
				"Test"), String.valueOf(money)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * den Anzahl der monatlichen Raten in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasMonths() {
		int months = 19;
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, months, 1l, 
				1.00, "Test"), String.valueOf(months)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * das Datum für die 1. Rate in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasStart() {
		long start = 19;
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, start, 
				1.00, "Test"), String.valueOf(start)));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * 'outstanding' in der Rückgabe enthält;
	 */
	@Test
	public void testUpdateHasTable() {
		assertEquals(1, frequency(_outstanding.update(1, 1, 1.00, 1, 1l, 1.00,
				"Test"), _table));
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.db.query.Outstanding#update(int, int, double, int, long, double, String)}
	 * die richtige Rückgabe erzeugt.
	 */
	@Test
	public void testUpdate() {
		String ret = "UPDATE 'outstanding' SET sectionid = 1, money = 1.15, " + 
				"months = 10, start = 180987, monthmoney = 2.87, comment = " +
				"\"Test\" WHERE id = 2";
		assertEquals(ret, _outstanding.update(2, 1, 1.15, 10, 180987, 2.87,
				"Test"));
	}
}
