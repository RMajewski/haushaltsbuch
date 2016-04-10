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
}
