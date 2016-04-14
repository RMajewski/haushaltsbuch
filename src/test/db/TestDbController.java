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

package test.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import db.DbController;
import test.TestHelper;

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
	 */
	@Test
	public void testGetInstanceRightClass()  {
		assertEquals("db.DbController", DbController.getInstance().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die zurück gegebene Instanz nicht null ist.
	 */
	@Test
	public void testGetInstanceReturnNotNull() {
		assertNotNull(DbController.getInstance());
	}
	
	/**
	 * Übeprüft, ob der Datenbank-Name nicht <b>null</b> oder eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testGetDatabaseNameReturnIsNotNullAndNotEmpty() {
		assertStringIsNotNull(DbController.getInstance().getDatabaseName());
	}
	
	/**
	 * Überprüft, ob zum testen die Datenbank im Speichert angelegt wird.
	 */
	@Test
	public void testGetDatabaseNameReturnIsMemoryWithTesting() {
		assertEquals(":memory:", DbController.getInstance().getDatabaseName());
	}
}
