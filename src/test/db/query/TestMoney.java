package test.db.query;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.query.Money;
import test.TestHelper;

/**
 * Testet die Klasse {@link db.query.Money}
 * 
 * @author René Majewski
 *
 */
public class TestMoney extends TestHelper {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle 'money'.
	 */
	private Money _money;

	/**
	 * Initalisiert die einzelnen Tests 
	 */
	@Before
	public void setUp() throws Exception {
		_money = new Money();
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#createTable()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testCreateTableReturnNotNull() {
		assertStringIsNotNull(_money.createTable());
	}

	/**
	 * Testet, ob die Methode {@link db.query.Money#insert()} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testInsertReturnNotNull() {
		assertStringIsNotNull(_money.insert());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert()} drei
	 * Fragezeichen enthält.
	 */
	@Test
	public void testInsertHasThreeQuery() {
		assertEquals(3, frequency(_money.insert(), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(long, boolean, String)
	 * keine leere Zeichenkette liefert.
	 */
	@Test
	public void testInsertDateBooleanStringReturnNotNull() {
		assertStringIsNotNull(_money.insert(7919156, true, "Dies ist ein Test"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(long, boolean, String)
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung <b>null</b> ist.
	 */
	@Test
	public void testInsertDataBooleanStringWithNull() {
		assertEquals(1, frequency(_money.insert(768786L,  true, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#insert(long, boolean, String)
	 * ein ? in der Rückgabe enthält, wenn die Beschreibung eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testInsertDataBooleanStringWithEmpty() {
		assertEquals(1, frequency(_money.insert(768786L,  true, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Mehode {@link db.query.Money.#insert(long, boolean, String)
	 * eine 1 in der Rückgabe enthält, wenn der Datensatz als Eingabe markiert
	 * wurde.
	 */
	@Test
	public void testInsertLongBooleanStringWithIncomingReturnHasOne() {
		assertEquals(1, frequency(_money.insert(7777l, true, "Test"), "1"));
	}
	
	/**
	 * Testet, ob die Mehode {@link db.query.Money.#insert(long, boolean, String)
	 * eine 0 in der Rückgabe enthält, wenn der Datensatz als Ausgabe markiert
	 * wurde.
	 */
	@Test
	public void testInsertLongBooleanStringWithOutgoingReturnHasZero() {
		assertEquals(1, frequency(_money.insert(7777l, false, "Test"), "0"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testDeleteReturnNotNull() {
		assertStringIsNotNull(_money.delete(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} kein
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasNoQuery() {
		assertEquals(0, frequency(_money.delete(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testDeleteHasId() {
		assertEquals(1, frequency(_money.delete(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} eine
	 * leere Zeichenkette liefert, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterReturnNull() {
		assertStringIsNotNull(_money.delete(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} ein
	 * Fragezeichen in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasNoQuery() {
		assertEquals(1, frequency(_money.delete(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#delete(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter <b>-1</b> ist.
	 */
	@Test
	public void testDeleteWithNullAsParameterHasId() {
		assertEquals(0, frequency(_money.delete(-1), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} keine leere
	 * Zeichenkette liefert.
	 */
	@Test
	public void testUpdateReturnNotNull() {
		assertStringIsNotNull(_money.update(100));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} drei
	 * Fragezeichen in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasThreeQuery() {
		assertEquals(3, frequency(_money.update(100), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} die
	 * übergebene ID in der Rückgabe enthält.
	 */
	@Test
	public void testUpdateHasId() {
		assertEquals(1, frequency(_money.update(100), "100"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} keine leere
	 * Zeichenkette liefert, auch wenn als Parameter -1 übergeben wird
	 */
	@Test
	public void testUpdateMinusOneAsParameterReturnNotNull() {
		assertStringIsNotNull(_money.update(-1));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} vier
	 * Fragezeichen in der Rückgabe enthält, wenn der PArameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasForeQuery() {
		assertEquals(4, frequency(_money.update(-1), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int)} keine
	 * ID in der Rückgabe enthält, wenn der Parameter -1 ist.
	 */
	@Test
	public void testUpdateMinusOneAsParameterHasNoId() {
		assertEquals(0, frequency(_money.update(-1), "-1"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, long)}
	 * keine leere Zeichenkette liefert
	 */
	@Test
	public void testUpdateDateReturnNotNull() {
		assertStringIsNotNull(_money.update(100, 177691l));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, long)}
	 * kein Fragenzeichen enthält.
	 */
	@Test
	public void testUpdateDateHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, 11487l), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, long)}
	 * die ID enthält.
	 */
	@Test
	public void testUpdateDateHasId() {
		int id = 9090;
		assertEquals(1, frequency(_money.update(id, 7979l), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, long)} das
	 * Datum enthält.
	 */
	@Test
	public void testUpdateDateHasDate() {
		long date = 190438108370l;
		assertEquals(1, frequency(_money.update(477, date), String.valueOf(date)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, long)} ein
	 * Fragezeichen enthält, wenn keine ID angegeben wurde.
	 */
	@Test
	public void testUpdateDateMinusOneReturnOneQuery() {
		assertEquals(1, frequency(_money.update(-1, 4717951739l), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, boolean)}
	 * keine leere Zeichenkette liefert
	 */
	@Test
	public void testUpdateInOutReturnNotNull() {
		assertStringIsNotNull(_money.update(100, true));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, boolean)}
	 * kein Fragenzeichen enthält.
	 */
	@Test
	public void testUpdateInOutHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, boolean)}
	 * die ID enthält.
	 */
	@Test
	public void testUpdateInoutHasId() {
		int id = 9090;
		assertEquals(1, frequency(_money.update(id, true), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, boolean)}
	 * eine 1 in der Rückgabe enthält, wenn es sich um eine Einnahme handelt.
	 */
	@Test
	public void testUpdateInOutWithIncomingReturnHasOne() {
		boolean inout = true;
		assertEquals(1, frequency(_money.update(477, inout), "1"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, boolean)}
	 * eine 0 in der Rückgabe enthält, wenn es sich um eine Ausgabe handelt.
	 */
	@Test
	public void testUpdateInOutWithIncomingReturnHasZero() {
		boolean inout = false;
		assertEquals(1, frequency(_money.update(477, inout), "0"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, boolean)} ein
	 * Fragezeichen enthält, wenn keine ID angegeben wurde.
	 */
	@Test
	public void testUpdateInOutMinusOneReturnOneQuery() {
		assertEquals(1, frequency(_money.update(-1, true), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)}
	 * keine leere Zeichenkette liefert
	 */
	@Test
	public void testUpdateCommentReturnNotNull() {
		assertStringIsNotNull(_money.update(100, "Test"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)}
	 * kein Fragenzeichen enthält.
	 */
	@Test
	public void testUpdateCommentHasNoQuery() {
		assertEquals(0, frequency(_money.update(100, "Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)}
	 * die ID enthält.
	 */
	@Test
	public void testUpdateCommentHasId() {
		int id = 9090;
		assertEquals(1, frequency(_money.update(id, "Test"), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)} die
	 * Beschreibung enthält.
	 */
	@Test
	public void testUpdateCommentHasComment() {
		String comment = "Dies ist ein Test";
		assertEquals(1, frequency(_money.update(477, comment), comment));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)} ein
	 * Fragezeichen enthält, wenn keine ID angegeben wurde.
	 */
	@Test
	public void testUpdateCommentMinusOneReturnOneQuery() {
		assertEquals(1, frequency(_money.update(-1, "Test"), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)} ein
	 * Fragezeichen enthält, wenn als Beschreibung <b>null</b> übergeben wird.
	 */
	@Test
	public void testUpdateCommentWithNullReturnOneQuery() {
		assertEquals(1, frequency(_money.update(100, null), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#update(int, string)} ein
	 * Fragezeichen enthält, wenn als Beschreibung eine leere Zeichenkette
	 * übergeben wird.
	 */
	@Test
	public void testUpdateCommentWithEmptyReturnOneQuery() {
		assertEquals(1, frequency(_money.update(100, new String()), "?"));
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.Money#select()} keine
	 * leere Zeichenkette liefert.
	 */
	@Test
	public void testSelectReturnNotNull() {
		assertStringIsNotNull(_money.select());
	}
}
