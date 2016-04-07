package test.db.query;

import static org.junit.Assert.*;

import org.junit.Test;

import db.query.Query;
import db.query.QueryInterface;

/**
 * Es wird die Klasse {@link db.query.Query} getestet.

 * @author René Majewski
 *
 */
public class TestQuery {
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#category} ein Objekt der
	 * Klasse {@link db.query.Category} ist.
	 */
	@Test
	public void testCategoryRightClassName() {
		assertEquals("db.query.Category", Query.category.getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#category} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testCategoryInstanceofQueryInterface() {
		assertTrue(Query.category instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#section} ein Objekt der
	 * Klasse {@link db.query.Section} ist.
	 */
	@Test
	public void testSectionRightClassName() {
		assertEquals("db.query.Section", Query.section.getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#section} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testSectionInstanceofQueryInterface() {
		assertTrue(Query.section instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#moneyDetails} ein Objekt
	 * der Klasse {@link db.query.MoneyDetails} ist.
	 */
	@Test
	public void testMoneyDetailsRightClassName() {
		assertEquals("db.query.MoneyDetails", Query.moneyDetails.getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#moneyDetails} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testMoneyDetailsInstanceofQueryInterface() {
		assertTrue(Query.moneyDetails instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#money} ein Objekt der
	 * Klasse {@link db.query.Money} ist.
	 */
	@Test
	public void testMoneyRightClassName() {
		assertEquals("db.query.Money", Query.money.getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link db.query.Query#money} das Interface
	 * {@link db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testMoneyInstanceofQueryInterface() {
		assertTrue(Query.money instanceof QueryInterface);
	}

}
