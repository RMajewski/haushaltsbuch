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
	 * {@link db.query.Query#getCloumnCount()}
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(2, _query.getCloumnCount());
	}
	
	/**
	 * Testet, ob kein Fragezeichen von der Methode {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 * überschrieben wird, wenn eine -1 als id übergeben wird.
	 */
	@Test
	public void testreplaceIdWithMinusOneAsIDReturnHaveTwoQueries() {
		_query.testReplayQuery(-1, _builder, false);
		assertEquals(2, frequency(_builder.toString(), "?"));
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testreplaceIdWithFalseAsLastReturnHasOneQuery() {
		_query.testReplayQuery(100, _builder, false);
		assertEquals(1, frequency(_builder.toString(), "?"));
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testreplaceIdWithFalseAsLastReturnIsRight() {
		_query.testReplayQuery(100, _builder, false);
		assertEquals("Dies 100 ist ein ? Test.", _builder.toString());
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testreplaceIdWithTrueAsLastReturnHasOneQuery() {
		_query.testReplayQuery(100, _builder, true);
		assertEquals(1, frequency(_builder.toString(), "?"));
	}
	
	/**
	 * Testet, ob es nur noch ein Fragezeichen gibt, wenn eine richtige ID
	 * angegeben wird.
	 * {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 */
	@Test
	public void testreplaceIdWithTrueAsLastReturnIsRight() {
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
		ret.append(") VALUES (\"?\", \"?\");");
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
	
	/**
	 * Teste, ob die Nachricht für die StatusBar keine <b>null</b> zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#statusInsertOk()}
	 */
	@Test
	public void testStatusInsertOkReturnIsNotNull() {
		assertFalse(_query.statusInsertOk() == null);
	}
	
	/**
	 * Testet, ob die nAchricht für die StatusBar keine leere Zeichenkette
	 * enthält.
	 * 
	 * {@link db.query.Query#statusInsertOk()}
	 */
	@Test
	public void testStatusInsertOkReturnIsNotEmpty() {
		assertFalse(_query.statusInsertOk().isEmpty());
	}
	
	/**
	 * Testet, ob die Nachricht für die StatusBar den richtigen Rückgabe-Wert
	 * hat. 
	 * 
	 * {@link db.query.Query#statusInsertOk()}
	 */
	@Test
	public void testStatusInsertOkReturnIsRight() {
		StringBuilder ret = new StringBuilder("Datenbank: In die Tabelle '");
		ret.append(_table);
		ret.append("' wurde ein Datensatz eingefügt.");
		assertEquals(ret.toString(), _query.statusInsertOk());
	}
	
	/**
	 * Teste, ob die Nachricht für die StatusBar keine <b>null</b> zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#statusInsertError()}
	 */
	@Test
	public void testStatusInsertErrorReturnIsNotNull() {
		assertFalse(_query.statusInsertError() == null);
	}
	
	/**
	 * Testet, ob die nAchricht für die StatusBar keine leere Zeichenkette
	 * enthält.
	 * 
	 * {@link db.query.Query#statusInsertError()}
	 */
	@Test
	public void testStatusInsertErrorReturnIsNotEmpty() {
		assertFalse(_query.statusInsertError().isEmpty());
	}
	
	/**
	 * Testet, ob die Nachricht für die StatusBar den richtigen Rückgabe-Wert
	 * hat. 
	 * 
	 * {@link db.query.Query#statusInsertError()}
	 */
	@Test
	public void testStatusInsertErrorReturnIsRight() {
		StringBuilder ret = new StringBuilder("Datenbank: In die Tabelle '");
		ret.append(_table);
		ret.append("' konnte kein Datensatz eingefügt werden.");
		assertEquals(ret.toString(), _query.statusInsertError());
	}
	
	/**
	 * Teste, ob die Nachricht für die StatusBar keine <b>null</b> zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#statusUpdateOk(int)}
	 */
	@Test
	public void testStatusUpdateOkReturnIsNotNull() {
		assertFalse(_query.statusUpdateOk(100) == null);
	}
	
	/**
	 * Testet, ob die nAchricht für die StatusBar keine leere Zeichenkette
	 * enthält.
	 * 
	 * {@link db.query.Query#statusUpdateOk(int)}
	 */
	@Test
	public void testStatusUpdateOkReturnIsNotEmpty() {
		assertFalse(_query.statusUpdateOk(100).isEmpty());
	}
	
	/**
	 * Testet, ob die Nachricht für die StatusBar den richtigen Rückgabe-Wert
	 * hat. 
	 * 
	 * {@link db.query.Query#statusUpdateOk(int)}
	 */
	@Test
	public void testStatusUpdateOkReturnIsRight() {
		int id = 100;
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" aus der Tabelle '");
		ret.append(_table);
		ret.append("' wurde geändert.");
		assertEquals(ret.toString(), _query.statusUpdateOk(id));
	}
	
	/**
	 * Teste, ob die Nachricht für die StatusBar keine <b>null</b> zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#statusUpdateError(int)}
	 */
	@Test
	public void testStatusUpdateErrorReturnIsNotNull() {
		assertFalse(_query.statusUpdateError(100) == null);
	}
	
	/**
	 * Testet, ob die nAchricht für die StatusBar keine leere Zeichenkette
	 * enthält.
	 * 
	 * {@link db.query.Query#statusUpdateError(int)}
	 */
	@Test
	public void testStatusUpdateErrorReturnIsNotEmpty() {
		assertFalse(_query.statusUpdateError(100).isEmpty());
	}
	
	/**
	 * Testet, ob die Nachricht für die StatusBar den richtigen Rückgabe-Wert
	 * hat. 
	 * 
	 * {@link db.query.Query#statusUpdateError(int)}
	 */
	@Test
	public void testStatusUpdateErrorReturnIsRight() {
		int id = 100;
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" aus der Tabelle '");
		ret.append(_table);
		ret.append("' konnte nicht geändert werden.");
		assertEquals(ret.toString(), _query.statusUpdateError(id));
	}
	
	/**
	 * Teste, ob die Nachricht für die StatusBar keine <b>null</b> zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#statusDeleteOk(int)}
	 */
	@Test
	public void testStatusDeleteOkReturnIsNotNull() {
		assertFalse(_query.statusDeleteOk(100) == null);
	}
	
	/**
	 * Testet, ob die nAchricht für die StatusBar keine leere Zeichenkette
	 * enthält.
	 * 
	 * {@link db.query.Query#statusDeleteOk(int)}
	 */
	@Test
	public void testStatusDeleteOkReturnIsNotEmpty() {
		assertFalse(_query.statusDeleteOk(100).isEmpty());
	}
	
	/**
	 * Testet, ob die Nachricht für die StatusBar den richtigen Rückgabe-Wert
	 * hat. 
	 * 
	 * {@link db.query.Query#statusDeleteOk(int)}
	 */
	@Test
	public void testStatusDeleteOkReturnIsRight() {
		int id = 100;
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" wurde aus der Tabelle '");
		ret.append(_table);
		ret.append("' gelöscht.");
		assertEquals(ret.toString(), _query.statusDeleteOk(id));
	}
	
	/**
	 * Teste, ob die Nachricht für die StatusBar keine <b>null</b> zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#statusDeleteError(int)}
	 */
	@Test
	public void testStatusDeleteErrorReturnIsNotNull() {
		assertFalse(_query.statusDeleteError(100) == null);
	}
	
	/**
	 * Testet, ob die nAchricht für die StatusBar keine leere Zeichenkette
	 * enthält.
	 * 
	 * {@link db.query.Query#statusDeleteError(int)}
	 */
	@Test
	public void testStatusDeleteErrorReturnIsNotEmpty() {
		assertFalse(_query.statusDeleteError(100).isEmpty());
	}
	
	/**
	 * Testet, ob die Nachricht für die StatusBar den richtigen Rückgabe-Wert
	 * hat. 
	 * 
	 * {@link db.query.Query#statusDeleteError(int)}
	 */
	@Test
	public void testStatusDeleteErrorReturnIsRight() {
		int id = 100;
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" konnte nicht aus der Tabelle '");
		ret.append(_table);
		ret.append("' gelöscht werden.");
		assertEquals(ret.toString(), _query.statusDeleteError(id));
	}
	
	/**
	 * Teste, ob die Anzahl an Datensätzen nicht <b>null</b> zurück gibt. 
	 * 
	 * {@link db.query.Query#count()}
	 */
	@Test
	public void testCountReturnIsNotNull() {
		assertFalse(_query.count() == null);
	}
	
	/**
	 * Testet, ob die Anzahl an Datensätzen keine leere Zeichenkette zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#count()}
	 */
	@Test
	public void testCountReturnIsNotEmpty() {
		assertFalse(_query.count().isEmpty());
	}
	
	/**
	 * Testet, die Anzahl an Datensätzen die richtige Abfrage zurück
	 * gibt.
	 * 
	 * {@link db.query.Query#count()}
	 */
	@Test
	public void testCountReturnIsRight() {
		StringBuilder ret = new StringBuilder("SELECT count(*) FROM '");
		ret.append(_table);
		ret.append("'");
		assertEquals(ret.toString(), _query.count());
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, nicht
	 *  <b>null</b> zurück gibt. 
	 * 
	 * {@link db.query.Query#search(String, String)}
	 */
	@Test
	public void testSearchReturnIsNotNull() {
		assertFalse(_query.search(_col1, "test") == null);
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, keine
	 * leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#search(String, String)}
	 */
	@Test
	public void testSearchReturnIsNotEmpty() {
		assertFalse(_query.search(_col1, "test").isEmpty());
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, die
	 * richtige Abfrage zurück gibt.
	 * 
	 * {@link db.query.Query#search(String, String)}
	 */
	@Test
	public void testSearchReturnIsRight() {
		StringBuilder ret = new StringBuilder("SELECT ");
		ret.append(_col1);
		ret.append(", ");
		ret.append(_col2);
		ret.append(" FROM ");
		ret.append(_table);
		ret.append(" WHERE ");
		ret.append(_col1);
		ret.append(" = \"test\" ORDER BY id ASC");
		assertEquals(ret.toString(), _query.search(_col1, "test"));
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, nicht
	 *  <b>null</b> zurück gibt. 
	 * 
	 * {@link db.query.Query#search(String, int)}
	 */
	@Test
	public void testSearchStringIntReturnIsNotNull() {
		assertFalse(_query.search(_col1, 100) == null);
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, keine
	 * leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#search(String, int)}
	 */
	@Test
	public void testSearchStringIntReturnIsNotEmpty() {
		assertFalse(_query.search(_col1, 100).isEmpty());
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, die
	 * richtige Abfrage zurück gibt.
	 * 
	 * {@link db.query.Query#search(String, int)}
	 */
	@Test
	public void testSearchStringIntReturnIsRight() {
		StringBuilder ret = new StringBuilder("SELECT ");
		ret.append(_col1);
		ret.append(", ");
		ret.append(_col2);
		ret.append(" FROM ");
		ret.append(_table);
		ret.append(" WHERE ");
		ret.append(_col1);
		ret.append(" = 100 ORDER BY id ASC");
		assertEquals(ret.toString(), _query.search(_col1, 100));
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, nicht
	 *  <b>null</b> zurück gibt. 
	 * 
	 * {@link db.query.Query#sort(String)}
	 */
	@Test
	public void testSortStringReturnIsNotNull() {
		assertFalse(_query.sort(_col1) == null);
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, keine
	 * leere Zeichenkette zurück gibt.
	 * 
	 * {@link db.query.Query#sort(String)}
	 */
	@Test
	public void testSortStringReturnIsNotEmpty() {
		assertFalse(_query.sort(_col1).isEmpty());
	}
	
	/**
	 * Testet, ob die Abfrage, in der ein Datensatz ausgewählt wird, die
	 * richtige Abfrage zurück gibt.
	 * 
	 * {@link db.query.Query#sort(String)}
	 */
	@Test
	public void testSortStringReturnIsRight() {
		StringBuilder ret = new StringBuilder("SELECT ");
		ret.append(_col1);
		ret.append(", ");
		ret.append(_col2);
		ret.append(" FROM ");
		ret.append(_table);
		ret.append(" ORDER BY ");
		ret.append(_col1);
		ret.append(" ASC");
		assertEquals(ret.toString(), _query.sort(_col1));
	}
}
