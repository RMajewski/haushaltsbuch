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

package tests.tests.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Category;
import haushaltsbuch.db.query.Money;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Section;
import tests.testcase.TestHelper;

/**
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.1
 */
public class TestDbController extends TestHelper {
	/**
	 * Diese Initalisierungen bleiben für alle Tests bestehen.
	 */
	@BeforeClass
	public static void setUpClass() {
		System.setProperty("testing", "true");
	}

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * Räumt nach jeden Test auf
	 */
	@After
	public void tearDown() {
		// Verbindung zur Datenbank beenden
		DbController.getInstance().close();
	}
	
	/**
	 * Gibt den Fehler für Datenbank-Fehler aus.
	 */
	private void dbError(SQLException e) {
		e.printStackTrace();
		fail("Datenbank-Fehler");
	}
	
	/**
	 * Überprüft ob die angegebenene Tabelle in der Datenbank existiert.
	 * 
	 * @param table Name der Tabelle, die in der Datenbank existieren soll.
	 * 
	 * @return Gibt <b>true</b> zurück, wenn die Tabelle in der Datenbank
	 * existiert. Esistiert sie nicht, so wird <b>false</b> zurück gegeben.
	 */
	private boolean dbTableExists(String table) throws SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = '" + table + "'");
		String name = rs.getString("name");
		rs.close();
		if (name.equals(table))
			return true;
		else
			return false;
	}
	
	/**
	 * Überprüft, ob die Spalte in der angegebenen Tabelle existiert.
	 * 
	 * @param table Name der Tabelle
	 * 
	 * @param column Name der Spalte, die überprüft werden soll.
	 * 
	 * @return Gibt <b>true</b> zurück, wenn die Spalte in der Tabelle
	 * existiert. Existiert sie nicht, so wird <b>false</b> zurück gegeben.
	 */
	private boolean dbTableColumnExists(String table, String column) throws SQLException{
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM " + table);
		ResultSetMetaData meta = rs.getMetaData();
		
		// Über alle Spalten laufen
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			if (meta.getColumnName(i).equals(column))
				return true;
		}
		
		rs.close();
		
		// Standard-Rückgabe
		return false;
	}
	
	/**
	 * Status-Nachricht für Datenbank-Fehler darf nicht <b>null</b> oder eine
	 * leere Zeichenkette sein.
	 */
	@Test
	public void testStatusDbErrorNotNullAndNotEmpty() {
		assertStringIsNotNull(DbController.statusDbError());
	}
	
	/**
	 * Überprüfbt, ob die zurück gegebene Instanz auch eine Instanz von
	 * DbController-Klasse ist.
	 * 
	 * @see db.DbController#getInstance()
	 */
	@Test
	public void testGetInstanceRightClass()  {
		assertEquals("haushaltsbuch.db.DbController", DbController.getInstance().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die zurück gegebene Instanz nicht null ist.
	 * 
	 * @see db.DbController#getInstance()
	 */
	@Test
	public void testGetInstanceReturnNotNull() {
		assertNotNull(DbController.getInstance());
	}
	
	/**
	 * Übeprüft, ob der Datenbank-Name nicht <b>null</b> oder eine leere
	 * Zeichenkette ist.
	 * 
	 * @see db.DbController#getInstance()
	 */
	@Test
	public void testGetDatabaseNameReturnIsNotNullAndNotEmpty() {
		assertStringIsNotNull(DbController.getInstance().getDatabaseName());
	}
	
	/**
	 * Überprüft, ob zum testen die Datenbank im Speichert angelegt wird.
	 * 
	 * @see db.DbController#getInstance()
	 */
	@Test
	public void testGetDatabaseNameReturnIsMemoryWithTesting() {
		assertEquals(":memory:", DbController.getInstance().getDatabaseName());
	}
	
	/**
	 * Überprüft, ob die Rückgabe von queries() eine Instanz der Klasse
	 * Queries ist.
	 * 
	 * @see db.DbController#queries()
	 */
	@Test
	public void testQueriesRightClass() {
		assertEquals("haushaltsbuch.db.query.Queries", DbController.queries().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die Rückgabe von createStatement() eine Instanz der Klasse
	 * org.sqlite.jdbc4.JDBC4Statement ist.
	 * 
	 * @see db.DbController#createStatement()
	 */
	@Test
	public void testCreateStatementRightClass() {
		try {
			assertEquals("org.sqlite.jdbc4.JDBC4Statement", DbController.getInstance().createStatement().getClass().getName());
		} catch (SQLException e) {
			dbError(e);
		}
	}

	/**
	 * Überprüft, ob die Rückgabe von getConnection() eine Instanz der Klasse
	 * org.sqlite.SQLiteConnection ist.
	 * 
	 * @see db.DbController#createStatement()
	 */
	@Test
	public void testGetConnectionRightClass() {
		assertEquals("org.sqlite.SQLiteConnection", DbController.getInstance().getConnection().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die Rückgabe von isConnection() korrekt ist.
	 * 
	 * @see db.DbController#isConnection()
	 */
	@Test
	public void testIsConnectionReturnTrue() {
		try {
			assertTrue(DbController.getInstance().isConnection());
			assertEquals(!DbController.getInstance().getConnection().isClosed(), DbController.getInstance().isConnection());
		} catch(SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob nach dem Beenden der Verbindung <b>false</b> zurück
	 * gegeben wird.
	 * 
	 * @see db.DbController#isConnection()
	 */
	@Test
	public void testIsConnectionReturnFalseAfterClose() {
		try {
			DbController.getInstance().getConnection().close();
			assertFalse(DbController.getInstance().isConnection());
			assertEquals(!DbController.getInstance().getConnection().isClosed(), DbController.getInstance().isConnection());
		} catch(SQLException e) {
			dbError(e);
		}
		
	}
	
	/**
	 * Übeprüft, ob prepareStatement(String) eine Instanz der Klasse
	 * org.sqlite.jdbc4.JDBC4PreparedStatement zurück gibt.
	 * 
	 * @see db.DbController#prepareStatement(String)
	 */
	@Test
	public void testPrepareStatementReturnIsRightClass() {
		try {
			assertEquals("org.sqlite.jdbc4.JDBC4PreparedStatement", DbController.getInstance().prepareStatement("SELECT * FROM sqlite_master").getClass().getName());
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob die Methode setAutoCommit(boolean) richtig arbeitet. Das
	 * heißt, wenn <b>false</b> übergeben wurde, sollte bei
	 * getConnectio().getAutoCommit() auch <b>true</b> zurück gegeben werden.
	 * 
	 * @see db.DbController#setAutoCommit(boolean)
	 */
	@Test
	public void testSetAutoCommitWithTrueAsParameter() {
		try {
			DbController.getInstance().setAutoCommit(true);
			assertTrue(DbController.getInstance().getConnection().getAutoCommit());
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob die Methode setAutoCommit(boolean) richtig arbeitet. Das
	 * heißt, wenn <b>false</b> übergeben wurde, sollte bei
	 * getConnectio().getAutoCommit() auch <b>true</b> zurück gegeben werden.
	 * 
	 * @see db.DbController#setAutoCommit(boolean)
	 */
	@Test
	public void testSetAutoCommitWithFalseAsParameter() {
		try {
			DbController.getInstance().setAutoCommit(false);
			assertFalse(DbController.getInstance().getConnection().getAutoCommit());
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob die prepaireDatabase() die Datenbank-Tabelle 'category'
	 * erzeugt hat.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseCreateTableCategory() {
		try {
			DbController.getInstance().prepaireDatabase();

			assertTrue(dbTableExists(new String("category")));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob alle Spalten der Tabelle 'secetion' erzeugt wurden.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseCategoryHasAllColumns() {
		try {
			DbController.getInstance().prepaireDatabase();

			Category category = DbController.queries().category();
			for (int i = 0; i < category.getCloumnCount(); i++)
				assertTrue(dbTableColumnExists("category", category.getColumnNames().get(i)));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob die prepaireDatabase() die Datenbank-Tabelle 'section'
	 * erzeugt hat.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseCreateTableSection() {
		try {
			DbController.getInstance().prepaireDatabase();

			assertTrue(dbTableExists(new String("section")));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob alle Spalten der Tabelle 'secetion' erzeugt wurden.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseSectionHasAllColumns() {
		try {
			DbController.getInstance().prepaireDatabase();

			Section section = DbController.queries().section();
			for (int i = 0; i < section.getCloumnCount(); i++)
				assertTrue(dbTableColumnExists("section", section.getColumnNames().get(i)));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob die prepaireDatabase() die Datenbank-Tabelle 'money'
	 * erzeugt hat.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseCreateTableMoney() {
		try {
			DbController.getInstance().prepaireDatabase();

			assertTrue(dbTableExists(new String("money")));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob alle Spalten der Tabelle 'money' erzeugt wurden.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseMoneyHasAllColumns() {
		try {
			DbController.getInstance().prepaireDatabase();

			Money money = DbController.queries().money();
			for (int i = 0; i < money.getCloumnCount(); i++)
				assertTrue(dbTableColumnExists("money", money.getColumnNames().get(i)));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob die prepaireDatabase() die Datenbank-Tabelle
	 * 'money_details' erzeugt hat.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseCreateTableMoneyDetails() {
		try {
			DbController.getInstance().prepaireDatabase();

			assertTrue(dbTableExists(new String("money_details")));
		} catch (SQLException e) {
			dbError(e);
		}
	}
	
	/**
	 * Überprüft, ob alle Spalten der Tabelle 'money_details' erzeugt wurden.
	 * 
	 * @see db.DbController#prepaireDatabase()
	 */
	@Test
	public void testPrepaireDatabaseMoneyDetailsHasAllColumns() {
		try {
			DbController.getInstance().prepaireDatabase();

			MoneyDetails details = DbController.queries().moneyDetails();
			for (int i = 0; i < details.getCloumnCount(); i++)
				assertTrue(dbTableColumnExists("money_details", details.getColumnNames().get(i)));
		} catch (SQLException e) {
			dbError(e);
		}
	}
}
