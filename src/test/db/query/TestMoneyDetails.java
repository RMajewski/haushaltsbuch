package test.db.query;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import db.query.MoneyDetails;

/**
 * Testet die Klasse {@link db.query.MoneyDetails}
 * 
 * @author René Majewski
 */
public class TestMoneyDetails {
	/**
	 * Speichert die Instanz der Datenbank-Abfragen der Tabelle 'money_details'
	 */
	private MoneyDetails _moneyDetails;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_moneyDetails = new MoneyDetails();
	}

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}

}
