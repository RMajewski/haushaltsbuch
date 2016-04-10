package test.db.query;

import static org.junit.Assert.*;

import org.junit.Test;

import db.query.Queries;
import db.query.QueryInterface;

/**
 * Es wird die Klasse {@link db.query.Queries} getestet.

 * @author René Majewski
 *
 */
public class TestQueries {
	/**
	 * Testet ob das Attribut {@link db.query.Queries#category} ein Objekt der
	 * Klasse {@link db.query.Category} ist.
	 */
	@Test
	public void testCategoryRightClassName() {
		assertEquals("db.query.Category", Queries.getInstance().category().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#category} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testCategoryInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().category() instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#section} ein Objekt der
	 * Klasse {@link db.query.Section} ist.
	 */
	@Test
	public void testSectionRightClassName() {
		assertEquals("db.query.Section", Queries.getInstance().section().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#section} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testSectionInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().section() instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#moneyDetails} ein Objekt
	 * der Klasse {@link db.query.MoneyDetails} ist.
	 */
	@Test
	public void testMoneyDetailsRightClassName() {
		assertEquals("db.query.MoneyDetails", Queries.getInstance().moneyDetails().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#moneyDetails} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testMoneyDetailsInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().moneyDetails() instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#money} ein Objekt der
	 * Klasse {@link db.query.Money} ist.
	 */
	@Test
	public void testMoneyRightClassName() {
		assertEquals("db.query.Money", Queries.getInstance().money().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Queries#money} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testMoneyInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().money() instanceof QueryInterface);
	}

}
