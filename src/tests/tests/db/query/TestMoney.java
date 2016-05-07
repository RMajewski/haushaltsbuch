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

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.db.query.Money;
import haushaltsbuch.helper.HelperCalendar;
import tests.testcase.TestHelper;

/**
 * Testet die Klasse {@link haushaltsbuch.db.query.Money}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestMoney extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle 'money'.
	 */
	private Money _money;
	
	/**
	 * Speichert den Namen der Tabelle
	 */
	private final String _table = new String("money");

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_money = new Money();
	}
	
	/**
	 * Testet, ob der richtige Tabellen-Name gesetzt wurde.
	 * 
	 * @see haushaltsbuch.db.query.Money#Money()
	 */
	@Test
	public void testRightTableName() {
		assertEquals("money", _money.getTableName());
	}
	
	/**
	 * Testet, ob die richtigen Spalten-Namen gesetzt wurden.
	 * 
	 * @see haushaltsbuch.db.query.Money#Money()
	 */
	@Test
	public void testRightColumnNames() {
		assertEquals("id", _money.getColumnNames().get(0));
		assertEquals("date", _money.getColumnNames().get(1));
		assertEquals("inout", _money.getColumnNames().get(2));
		assertEquals("comment", _money.getColumnNames().get(3));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#createTable()} die richtige
	 * Rückgabe liefert.
	 */
	@Test
	public void testCreateTableReturnIsRight() {
		String test = "CREATE TABLE IF NOT EXISTS 'money' (" +
				"'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'date' INTEGER NOT NULL, " +
				"'inout' INTEGER, " +
				"'comment' TEXT)";
		assertEquals(test, _money.createTable());
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#insert()} drei
	 * Fragezeichen enthält.
	 */
	@Test
	public void testInsertHasThreeQuery() {
		assertEquals(3, frequency(_money.insert(), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#insert()} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testInsertTableReturnHaveMoney() {
		assertEquals(1, frequency(_money.insert(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#insert(long, boolean, String)}
	 * kein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertDataBooleanStringWithNull() {
		assertEquals(0, frequency(_money.insert(768786L,  true, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#insert(long, boolean, String)}
	 * kein ? in der Rückgabe enthält, wenn die Beschreibung eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testInsertDataBooleanStringWithEmpty() {
		assertEquals(0, frequency(_money.insert(768786L,  true, new String()), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#insert(long, boolean, String)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testInsertLongBooleanStringTableReturnHaveMoney() {
		assertEquals(1, frequency(_money.insert(100l, true, "'Dies ist ein Test'"),_table));
	}
	
	/**
	 * Testet, ob die Mehode {@link haushaltsbuch.db.query.Money#insert(long, boolean, String)}
	 * eine 1 in der Rückgabe enthält, wenn der Datensatz als Eingabe markiert
	 * wurde.
	 */
	@Test
	public void testInsertLongBooleanStringWithIncomingReturnHasOne() {
		assertEquals(1, frequency(_money.insert(7777l, true, "Test"), "1"));
	}
	
	/**
	 * Testet, ob die Mehode {@link haushaltsbuch.db.query.Money#insert(long, boolean, String)}
	 * eine 0 in der Rückgabe enthält, wenn der Datensatz als Ausgabe markiert
	 * wurde.
	 */
	@Test
	public void testInsertLongBooleanStringWithOutgoingReturnHasZero() {
		assertEquals(1, frequency(_money.insert(7777l, false, "Test"), "0"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#delete(int)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testDeleteReturnHaveMoney() {
		assertEquals(1, frequency(_money.delete(100),_table));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#update(int)} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testUpdateReturnHaveMoney() {
		assertEquals(1, frequency(_money.update(100),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long)}
	 * kein Fragenzeichen enthält.
	 */
	@Test
	public void testUpdateDateHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, 11487l), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long)}
	 * die ID enthält.
	 */
	@Test
	public void testUpdateDateHasId() {
		int id = 9090;
		assertEquals(1, frequency(_money.update(id, 7979l), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long)} das
	 * Datum enthält.
	 */
	@Test
	public void testUpdateDateHasDate() {
		long date = 190438108370l;
		assertEquals(1, frequency(_money.update(477, date), String.valueOf(date)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long)} ein
	 * Fragezeichen enthält, wenn keine ID angegeben wurde.
	 */
	@Test
	public void testUpdateDateMinusOneReturnOneQuery() {
		assertEquals(1, frequency(_money.update(-1, 4717951739l), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long)} die
	 * richtig Rückgabe liefert.
	 */
	@Test
	public void testUpdateDateReturnIsRight() {
		String test = "UPDATE 'money' SET date = 5175837 WHERE id = 100";
		assertEquals(test, _money.update(100, 5175837l));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)}
	 * kein Fragenzeichen enthält.
	 */
	@Test
	public void testUpdateInOutHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)}
	 * die ID enthält.
	 */
	@Test
	public void testUpdateInoutHasId() {
		int id = 9090;
		assertEquals(1, frequency(_money.update(id, true), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)}
	 * eine 1 in der Rückgabe enthält, wenn es sich um eine Einnahme handelt.
	 */
	@Test
	public void testUpdateInOutWithIncomingReturnHasOne() {
		boolean inout = true;
		assertEquals(1, frequency(_money.update(477, inout), "1"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)}
	 * eine 0 in der Rückgabe enthält, wenn es sich um eine Ausgabe handelt.
	 */
	@Test
	public void testUpdateInOutWithIncomingReturnHasZero() {
		boolean inout = false;
		assertEquals(1, frequency(_money.update(477, inout), "0"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)} ein
	 * Fragezeichen enthält, wenn keine ID angegeben wurde.
	 */
	@Test
	public void testUpdateInOutMinusOneReturnOneQuery() {
		assertEquals(1, frequency(_money.update(-1, true), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testUpdateInOutWithTrueAsParameterReturnIsRight() {
		String test = "UPDATE 'money' SET inout = 1 WHERE id = 100";
		assertEquals(test, _money.update(100, true));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, boolean)} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testUpdateInOutWithFalseAsParameterReturnIsRight() {
		String test = "UPDATE 'money' SET inout = 0 WHERE id = 100";
		assertEquals(test, _money.update(100, false));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)}
	 * kein Fragenzeichen enthält.
	 */
	@Test
	public void testUpdateCommentHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, "Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)}
	 * die ID enthält.
	 */
	@Test
	public void testUpdateCommentHasId() {
		int id = 9090;
		assertEquals(1, frequency(_money.update(id, "Test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)} die
	 * Beschreibung enthält.
	 */
	@Test
	public void testUpdateCommentHasComment() {
		String comment = "Dies ist ein Test";
		assertEquals(1, frequency(_money.update(477, comment), comment));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)} ein
	 * Fragezeichen enthält, wenn keine ID angegeben wurde.
	 */
	@Test
	public void testUpdateCommentMinusOneReturnOneQuery() {
		assertEquals(1, frequency(_money.update(-1, "Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)} ein
	 * Fragezeichen enthält, wenn als Beschreibung <b>null</b> übergeben wird.
	 */
	@Test
	public void testUpdateCommentWithNullReturnOneQuery() {
		assertEquals(1, frequency(_money.update(100, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)} ein
	 * Fragezeichen enthält, wenn als Beschreibung eine leere Zeichenkette
	 * übergeben wird.
	 */
	@Test
	public void testUpdateCommentWithEmptyReturnOneQuery() {
		assertEquals(1, frequency(_money.update(100, new String()), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, String)} die
	 * richtige Rückgabe liefert.
	 */
	@Test
	public void testUpdateCommentReturnIsRight() {
		String test = "UPDATE 'money' SET comment = 'Dies ist ein Test' "
				+ "WHERE id = 100";
		assertEquals(test, _money.update(100, "Dies ist ein Test"));
	}
	
	/**
	 * Test, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanStringReturnHaveId() {
		int id = 100;
		assertEquals(1, frequency(_money.update(id, 110857l, false, "Dies ist ein Test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * das Datum in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanStringReturnHaveDate() {
		long date = 217805710837l;
		assertEquals(1, frequency(_money.update(100, date, false, "Dies ist ein Test"), String.valueOf(date)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * in der Rückgabe enthält, ob es sich um eine Einnahme oder eine Ausgabe
	 * handelt.
	 */
	@Test
	public void testUpdateIntBooleanStringReturnHaveInout() {
		assertEquals(1, frequency(_money.update(999, 708705l, true, "Dies ist ein Test"), "1"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * den Kommentar in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanStringReturnHaveComment() {
		String comment = "Dies ist ein Test!";
		assertEquals(1, frequency(_money.update(100, 17508170l, false, comment), comment));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * den Tabellen-Namen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanStringReturnHaveTableName() {
		assertEquals(1, frequency(_money.update(100, 17508170l, false, "Dies ist ein Test"), _table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * ein <b>?</b> enthält, wenn -1 als ID übergeben wird.
	 */
	@Test
	public void testUpdateIntBooleanStringMinusOneAsIdReturnHasOneQuery() {
		assertEquals(1, frequency(_money.update(-1, 1740704l, false, "Dies ist ein Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * ein <b>?</b> enthält, wenn null als Kommentar übergeben wird.
	 */
	@Test
	public void testUpdateIntBooleanStringNullAsCommentReturnHasOneQuery() {
		assertEquals(1, frequency(_money.update(100, 1407450l, false, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean, String)}
	 * ein <b>?</b> enthält, wenn eine leere Zeichenkette als Kommentar
	 * übergeben wird.
	 */
	@Test
	public void testUpdateIntBooleanStringEmptyAsCommentReturnHasOneQuery() {
		assertEquals(1, frequency(_money.update(100, 17501l, false, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * keine <b>?</b> in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanReturnNoQuery() {
		assertEquals(0, frequency(_money.update(100, 157081750l, false), "?"));
	}
	
	
	/**
	 * Test, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * die ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanReturnHaveId() {
		int id = 100;
		assertEquals(1, frequency(_money.update(id, 110857l, false, "Dies ist ein Test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * das Datum in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateIntBooleanReturnHaveDate() {
		long date = 217805710837l;
		assertEquals(1, frequency(_money.update(100, date, false), String.valueOf(date)));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * in der Rückgabe enthält, ob es sich um eine Einnahme oder eine Ausgabe
	 * handelt.
	 */
	@Test
	public void testUpdateIntBooleanReturnHaveInout() {
		assertEquals(1, frequency(_money.update(999, 708705l, true), "1"));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * dir richtig Rückgabe liefert
	 */
	@Test
	public void testUpdateIntBooleanWithFalseAsParameterReturnIsRight() {
		String test = "UPDATE 'money' SET date = 17508170, inout = 0, "
				+ "comment = '' WHERE id = 100";
		assertEquals(test, _money.update(100, 17508170l, false));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * dir richtig Rückgabe liefert
	 */
	@Test
	public void testUpdateIntBooleanWithTrueAsParameterReturnIsRight() {
		String test = "UPDATE 'money' SET date = 17508170, inout = 1, "
				+ "comment = '' WHERE id = 100";
		assertEquals(test, _money.update(100, 17508170l, true));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#update(int, long, boolean)}
	 * ein <b>?</b> enthält, wenn -1 als ID übergeben wird.
	 */
	@Test
	public void testUpdateIntBooleanMinusOneAsIdReturnHasOneQuery() {
		assertEquals(1, frequency(_money.update(-1, 1740704l, false), "?"));
	}
	
	/**
	 * Testest, ob die Methode {@link haushaltsbuch.db.query.Money#select()} in
	 * der Rückgabe 'money' enthält.
	 */
	@Test
	public void testSelectReturnHaveMoney() {
		assertEquals(1, frequency(_money.select(),_table));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#selectWeek(long, long, int)}
	 * die Datenbank-Abfrage richtig erstellt hat. Es wurden die Datensätze
	 * für die Einnahmen ausgewählt. 
	 */
	@Test
	public void testSelectOfWeekWithIncoming() {
		GregorianCalendar gc = HelperCalendar.createCalendar(2016);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		long from = gc.getTimeInMillis();
		gc.set(GregorianCalendar.DAY_OF_WEEK, 7);
		long to = gc.getTimeInMillis();
		
		
		StringBuilder query = new StringBuilder("SELECT id FROM ");
		query.append(_table);
		query.append(" WHERE date BETWEEN ");		
		query.append(from);
		query.append(" AND ");
		query.append(to);
		query.append(" AND inout = ");
		query.append(MoneyData.INT_INCOMING);
		
		assertEquals(query.toString(), 
				_money.selectWeek(from, to, MoneyData.INT_INCOMING));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#selectWeek(long, long, int)}
	 * die Datenbank-Abfrage richtig erstellt hat. Es wurden die Datensätze
	 * für die Einnahmen ausgewählt. 
	 */
	@Test
	public void testSelectOfWeekWithOutgoing() {
		GregorianCalendar gc = HelperCalendar.createCalendar(2016);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		long from = gc.getTimeInMillis();
		gc.set(GregorianCalendar.DAY_OF_WEEK, 7);
		long to = gc.getTimeInMillis();
		
		
		StringBuilder query = new StringBuilder("SELECT id FROM ");
		query.append(_table);
		query.append(" WHERE date BETWEEN ");		
		query.append(from);
		query.append(" AND ");
		query.append(to);
		query.append(" AND inout = ");
		query.append(MoneyData.INT_OUTGOING);
		
		assertEquals(query.toString(), 
				_money.selectWeek(from, to, MoneyData.INT_OUTGOING));
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#selectDay(long, int)} die
	 * Datenbank-Abfrage richtig erstellt hat. Es wurden die Datensätze für die
	 * Einnahmen ausgewählt.
	 */
	@Test
	public void testSelectDayWithIncoming() {
		GregorianCalendar gc = HelperCalendar.createCalendar(2016);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		long from = gc.getTimeInMillis();
		gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
		gc.set(GregorianCalendar.MINUTE, 59);
		gc.set(GregorianCalendar.SECOND, 59);
		gc.set(GregorianCalendar.MILLISECOND, 999);
		long to = gc.getTimeInMillis();
		
		
		StringBuilder query = new StringBuilder("SELECT id FROM ");
		query.append(_table);
		query.append(" WHERE date BETWEEN ");		
		query.append(from);
		query.append(" AND ");
		query.append(to);
		query.append(" AND inout = ");
		query.append(MoneyData.INT_INCOMING);
		
		assertEquals(query.toString(), 
				_money.selectWeek(from, to, MoneyData.INT_INCOMING));
		
	}
	
	/**
	 * Testet, ob die Methode {@link haushaltsbuch.db.query.Money#selectDay(long, int)} die
	 * Datenbank-Abfrage richtig erstellt hat. Es wurden die Datensätze für die
	 * Ausgaben ausgewählt.
	 */
	@Test
	public void testSelectDayWithOutgoing() {
		GregorianCalendar gc = HelperCalendar.createCalendar(2016);
		gc.set(GregorianCalendar.WEEK_OF_YEAR, 1);
		gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
		long from = gc.getTimeInMillis();
		gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
		gc.set(GregorianCalendar.MINUTE, 59);
		gc.set(GregorianCalendar.SECOND, 59);
		gc.set(GregorianCalendar.MILLISECOND, 999);
		long to = gc.getTimeInMillis();
		
		
		StringBuilder query = new StringBuilder("SELECT id FROM ");
		query.append(_table);
		query.append(" WHERE date BETWEEN ");		
		query.append(from);
		query.append(" AND ");
		query.append(to);
		query.append(" AND inout = ");
		query.append(MoneyData.INT_OUTGOING);
		
		assertEquals(query.toString(), 
				_money.selectWeek(from, to, MoneyData.INT_OUTGOING));
		
	}
}
