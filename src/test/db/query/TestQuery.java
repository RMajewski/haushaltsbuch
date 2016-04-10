package test.db.query;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import test.TestHelper;

/**
 * Testet die Klasse {@link db.query.Query}
 * 
 * @author René Majewski
 */
public class TestQuery extends TestHelper {
	/**
	 * Speichert die Instanz der TestQueryImplementation
	 */
	private TestQueryImplementation _query;
	
	/**
	 * Name der Tabelle
	 */
	private final String _table = new String("test");
	
	/**
	 * Name der 1. Spalte
	 */
	private final String _col1 = new String("col1");
	
	/**
	 * Name der 2. Spalte
	 */
	private final String _col2 = new String("col2");
	
	/**
	 * Speichert den String-Builder
	 */
	private StringBuilder _builder;
	
	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_query = new TestQueryImplementation(_table, _col1, _col2);
		_builder = new StringBuilder("Dies ? ist ein ? Test.");
	}
	
	/**
	 * Testet, ob der Tabellen-Name richtig zurück gegeben wird.
	 * {@link db.query.Query#getTableName()}
	 */
	@Test
	public void testGetTableName() {
		assertEquals(_table, _query.getTableName());
	}
	
	/**
	 * Testet, ob die Liste mit den Spalten-Namen richtig zurück gegeben wird.
	 * {@link db.query.Query#getColumnNames()}
	 */
	@Test
	public void testGetColoumnNames() {
		assertEquals(_col1, _query.getColumnNames().get(0));
		assertEquals(_col2, _query.getColumnNames().get(1));
	}
	
	/**
	 * Testet, ob die die richtige Anzahl an Spalten zurück gegeben wird.
	 * {@link db.query.Query#getColumnCount()}
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(2, _query.getCloumnCount());
	}
	
	/**
	 * Testet, ob kein Fragezeichen von der Methode {@link db.query.Query#replaceQuery(int, StringBuilder, boolean}
	 * überschrieben wird, wenn eine -1 als id übergeben wird.
	 */
	@Test
	public void testReplaceQueryWithMinusOneAsIDReturnHaveTwoQueries() {
		_query.testReplayQuery(-1, _builder, false);
		assertEquals(2, frequency(_builder.toString(), "?"));
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testReplaceQueryWithFalseAsLastReturnHasOneQuery() {
		_query.testReplayQuery(100, _builder, false);
		assertEquals(1, frequency(_builder.toString(), "?"));
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testReplaceQueryWithFalseAsLastReturnIsRight() {
		_query.testReplayQuery(100, _builder, false);
		assertEquals("Dies 100 ist ein ? Test.", _builder.toString());
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testReplaceQueryWithTrueAsLastReturnHasOneQuery() {
		_query.testReplayQuery(100, _builder, true);
		assertEquals(1, frequency(_builder.toString(), "?"));
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testReplaceQueryWithTrueAsLastReturnIsRight() {
		_query.testReplayQuery(100, _builder, true);
		assertEquals("Dies ? ist ein 100 Test.", _builder.toString());
	}
	
	/**
	 * Testem ob die Datenbank-Abfrage, um alle Datensätze aufzulisten,
	 * kein <b>null</b> als Rückgabe hat.
	 * 
	 * {@link db.query.Query#select()}
	 */
	@Test
	public void testSelectReturnIsNotNull() {
		assertFalse(_query.select() == null);
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um alle Datensätze aufzulisten,
	 * keine leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#select()}
	 */
	@Test
	public void testSelectReturnIsNotEmpty() {
		assertFalse(_query.select().isEmpty());
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um alle Datensätze aufzulisten,
	 * richtig erzeugt wurde.
	 * 
	 * {@link db.query.Query#select()}
	 */
	@Test
	public void testSelectReturnIsRight() {
		StringBuilder ret = new StringBuilder("SELECT ");
		ret.append(_col1);
		ret.append(", ");
		ret.append(_col2);
		ret.append(" FROM ");
		ret.append(_table);
		ret.append(" ORDER BY id ASC");
		assertEquals(ret.toString(), _query.select());
	}
	
	/**
	 * Teste, ob die Datenbank-Abfrage, um einen Datensatz zu löschen,
	 * kein <b>null</b> als Rückgabe hat.
	 * 
	 * {@link db.query.Query#delete(int)}
	 */
	@Test
	public void testDeleteReturnIsNotNull() {
		assertFalse(_query.delete(100) == null);
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu löschen,
	 * keine leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#delete(int)}
	 */
	@Test
	public void testDeleteReturnIsNotEmpty() {
		assertFalse(_query.delete(100).isEmpty());
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu löschen,
	 * richtig erzeugt wurde.
	 * 
	 * {@link db.query.Query#delete(int)}
	 */
	@Test
	public void testDeleteReturnIsRight() {
		int id = 100;
		StringBuilder ret = new StringBuilder("DELETE FROM ");
		ret.append(_table);
		ret.append(" WHERE id = ");
		ret.append(String.valueOf(id));
		assertEquals(ret.toString(), _query.delete(id));
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu löschen,
	 * keine Fragezeichen in der Rückgabe enthält.
	 * 
	 * {@link db.query.Query#delete(int)}
	 */
	@Test
	public void testDeleteReturnHaveZeroQuery() {
		assertEquals(0, frequency(_query.delete(100), "?"));
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu löschen,
	 * die ID in der Rückgabe enthält.
	 * 
	 * {@link db.query.Query#delete(int)}
	 */
	@Test
	public void testDeleteReturnHaveId() {
		int id = 100;
		assertEquals(1, frequency(_query.delete(id), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu löschen,
	 * ein Fragezeichen in der Rückgabe enthält, wenn <b>-1</b> als ID
	 * übergeben wird.
	 * 
	 * {@link db.query.Query#delete(int)}
	 */
	@Test
	public void testDeleteWithMinusOneReturnHasOneQuery() {
		assertEquals(1, frequency(_query.delete(-1), "?"));
	}
	
	/**
	 * Testem ob die Datenbank-Abfrage, um einen Datensatz zu ändern,
	 * kein <b>null</b> als Rückgabe hat.
	 * 
	 * {@link db.query.Query#update(int)}
	 */
	@Test
	public void testUpdateReturnIsNotNull() {
		assertFalse(_query.update(100) == null);
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu ändern,
	 * keine leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#update(int)}
	 */
	@Test
	public void testUpdateReturnIsNotEmpty() {
		assertFalse(_query.update(100).isEmpty());
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu ändern,
	 * richtig erzeugt wurde.
	 * 
	 * {@link db.query.Query#update(int)}
	 */
	@Test
	public void testUpdateReturnIsRight() {
		int id = 100;
		StringBuilder ret = new StringBuilder("UPDATE ");
		ret.append(_table);
		ret.append(" SET ");
		ret.append(_col1);
		ret.append(" = '?', ");
		ret.append(_col2);
		ret.append(" = '?' WHERE id = ");
		ret.append(String.valueOf(id));
		assertEquals(ret.toString(), _query.update(id));
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu ändern,
	 * zwei Fragezeichen in der Rückgabe enthält.
	 * 
	 * {@link db.query.Query#update(int)}
	 */
	@Test
	public void testUpdateReturnHaveTwoQuery() {
		assertEquals(2, frequency(_query.update(100), "?"));
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu ändern,
	 * die ID in der Rückgabe enthält.
	 * 
	 * {@link db.query.Query#update(int)}
	 */
	@Test
	public void testUpdateReturnHaveId() {
		int id = 100;
		assertEquals(1, frequency(_query.update(id), String.valueOf(id)));
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz zu ändern,
	 * drei Fragezeichen in der Rückgabe enthält, wenn <b>-1</b> als ID
	 * übergeben wird.
	 * 
	 * {@link db.query.Query#update(int)}
	 */
	@Test
	public void testUpdateWithMinusOneReturnHasThreeQueries() {
		assertEquals(3, frequency(_query.update(-1), "?"));
	}
	
	/**
	 * Teste, ob die Datenbank-Abfrage, um einen Datensatz einzufügen,
	 * kein <b>null</b> als Rückgabe hat.
	 * 
	 * {@link db.query.Query#insert()}
	 */
	@Test
	public void testInsertReturnIsNotNull() {
		assertFalse(_query.insert() == null);
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz einzufügen,
	 * keine leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#insert()}
	 */
	@Test
	public void testInsertReturnIsNotEmpty() {
		assertFalse(_query.insert().isEmpty());
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz einzufügen,
	 * richtig erzeugt wurde.
	 * 
	 * {@link db.query.Query#insert()}
	 */
	@Test
	public void testInsertReturnIsRight() {
		StringBuilder ret = new StringBuilder("INSERT INTO ");
		ret.append(_table);
		ret.append(" (");
		ret.append(_col1);
		ret.append(", ");
		ret.append(_col2);
		ret.append(") VALUES (?, ?);");
		assertEquals(ret.toString(), _query.insert());
	}
	
	/**
	 * Testet, ob die Datenbank-Abfrage, um einen Datensatz einzufügen,
	 * zwei Fragezeichen in der Rückgabe enthält.
	 * 
	 * {@link db.query.Query#insert()}
	 */
	@Test
	public void testUpdateReturnHaveTwoQueries() {
		assertEquals(2, frequency(_query.insert(), "?"));
	}
}
