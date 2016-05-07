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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import haushaltsbuch.db.query.Queries;
import haushaltsbuch.db.query.QueryInterface;

/**
 * Es wird die Klasse {@link haushaltsbuch.db.query.Queries} getestet.

 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestQueries {
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#category} ein Objekt der
	 * Klasse {@link haushaltsbuch.db.query.Category} ist.
	 */
	@Test
	public void testCategoryRightClassName() {
		assertEquals("haushaltsbuch.db.query.Category", Queries.getInstance().category().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#category} das Interface
	 * {@link haushaltsbuch.db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testCategoryInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().category() instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#section} ein Objekt der
	 * Klasse {@link haushaltsbuch.db.query.Section} ist.
	 */
	@Test
	public void testSectionRightClassName() {
		assertEquals("haushaltsbuch.db.query.Section", Queries.getInstance().section().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#section} das Interface
	 * {@link haushaltsbuch.db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testSectionInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().section() instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#moneyDetails} ein Objekt
	 * der Klasse {@link haushaltsbuch.db.query.MoneyDetails} ist.
	 */
	@Test
	public void testMoneyDetailsRightClassName() {
		assertEquals("haushaltsbuch.db.query.MoneyDetails", Queries.getInstance().moneyDetails().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#moneyDetails} das Interface
	 * {@link haushaltsbuch.db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testMoneyDetailsInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().moneyDetails() instanceof QueryInterface);
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#money} ein Objekt der
	 * Klasse {@link haushaltsbuch.db.query.Money} ist.
	 */
	@Test
	public void testMoneyRightClassName() {
		assertEquals("haushaltsbuch.db.query.Money", Queries.getInstance().money().getClass().getName());
	}
	
	/**
	 * Testet ob das Attribut {@link haushaltsbuch.db.query.Queries#money} das Interface
	 * {@link haushaltsbuch.db.query.QueryInterface} implementiert.
	 */
	@Test
	public void testMoneyInstanceofQueryInterface() {
		assertTrue(Queries.getInstance().money() instanceof QueryInterface);
	}

}
