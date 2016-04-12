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

import db.query.QueryInterface;
import test.TestHelper;

/**
 * Testet ob das {@link db.query.QueryInterface}-Interface alle Methoden
 * deklariert hat.
 * 
 * @author René Majewski
 *
 */
public class TestQueryInterface extends TestHelper {
	
	/**
	 * Initalisiert die einzelnen Test
	 */
	@Before
	public void before() {
		_methods = QueryInterface.class.getMethods();
	}

	/**
	 * Testet ob die Methode {@link db.query.QueryInterface#createTable()}
	 * deklariert wurde.
	 */
	@Test
	public void testCreateTableDeclaration() {
		assertTrue(getMethod("createTable") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#createTable()}
	 * keine Parameter hat.
	 */
	@Test
	public void testCreateTableNoParameter() {
		assertEquals(0, getMethod("createTable").getParameterTypes().length);
	}
	
	/**
	 * Testetm, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#createTable()} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testCreateTableReturnString() {
		assertEquals("java.lang.String", getMethod("createTable").getReturnType().getName());
	}

	/**
	 * Testet ob die Methode {@link db.query.QueryInterface#insert()}
	 * deklariert wurde.
	 */
	@Test
	public void testInsertDeclaration() {
		assertTrue(getMethod("insert") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#insert()}
	 * keine Parameter hat.
	 */
	@Test
	public void testInsertNoParameter() {
		assertEquals(0, getMethod("insert").getParameterTypes().length);
	}
	
	/**
	 * Testetm, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#insert()} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testInsertReturnString() {
		assertEquals("java.lang.String", getMethod("insert").getReturnType().getName());
	}

	/**
	 * Testet ob die Methode {@link db.query.QueryInterface#update(int)}
	 * deklariert wurde.
	 */
	@Test
	public void testUpdateDeclaration() {
		assertTrue(getMethod("update") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#update(int)}
	 * ein Parameter von Typ int besitzt.
	 */
	@Test
	public void testUpdateOneParameter() {
		assertEquals(1, getMethod("update").getParameterTypes().length);
		assertEquals("int", getMethod("update").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testetm, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#update(int id)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testUpdateReturnString() {
		assertEquals("java.lang.String", getMethod("update").getReturnType().getName());
	}

	/**
	 * Testet ob die Methode {@link db.query.QueryInterface#delete(int)}
	 * deklariert wurde.
	 */
	@Test
	public void testDeleteDeclaration() {
		assertTrue(getMethod("delete") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#delete(int)}
	 * ein Parameter von Typ int besitzt.
	 */
	@Test
	public void testDeleteOneParameter() {
		assertEquals(1, getMethod("delete").getParameterTypes().length);
		assertEquals("int", getMethod("delete").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#delete(int)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testDeleteReturnString() {
		assertEquals("java.lang.String", getMethod("delete").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#select()}
	 * deklariert wurde.
	 */
	@Test
	public void testSelectDeclaration() {
		assertTrue(getMethod("select") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#select()}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testSelectNoParameter() {
		assertEquals(0, getMethod("select").getParameterTypes().length);
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#select()} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testSelectReturnString() {
		assertEquals("java.lang.String", getMethod("select").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusInsertOk()}
	 * deklariert wurde.
	 */
	@Test
	public void testStatusInsertOkDeclaration() {
		assertTrue(getMethod("statusInsertOk") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusInsertOk()}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testStatusInsertOkNoParameter() {
		assertEquals(0, getMethod("statusInsertOk").getParameterTypes().length);
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#statusInsertOk()} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testStatusInsertOkReturnString() {
		assertEquals("java.lang.String", getMethod("statusInsertOk").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusInsertError()}
	 * deklariert wurde.
	 */
	@Test
	public void testStatusInsertErrorDeclaration() {
		assertTrue(getMethod("statusInsertError") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusInsertError()}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testStatusInsertErrorNoParameter() {
		assertEquals(0, getMethod("statusInsertError").getParameterTypes().length);
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#statusInsertError()} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testStatusInsertErrorReturnString() {
		assertEquals("java.lang.String", getMethod("statusInsertError").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusUpdateOk(int)}
	 * deklariert wurde.
	 */
	@Test
	public void testStatusUpdateOkDeclaration() {
		assertTrue(getMethod("statusUpdateOk") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusUpdateOk(int)}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testStatusUpdateOkHasOneParameter() {
		assertEquals(1, getMethod("statusUpdateOk").getParameterTypes().length);
		assertEquals("int", getMethod("statusUpdateOk").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#statusUpdateOk(int)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testStatusUpdatetOkReturnString() {
		assertEquals("java.lang.String", getMethod("statusUpdateOk").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusUpdateError(int)}
	 * deklariert wurde.
	 */
	@Test
	public void testStatusUpdateErrorDeclaration() {
		assertTrue(getMethod("statusUpdateError") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusUpdateError(int)}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testStatusUpdateErrorHaseOneParameter() {
		assertEquals(1, getMethod("statusUpdateError").getParameterTypes().length);
		assertEquals("int", getMethod("statusUpdateError").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#statusUpdateError(int)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testStatusUpdatetErrorReturnString() {
		assertEquals("java.lang.String", getMethod("statusUpdateError").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusDeleteOk(int)}
	 * deklariert wurde.
	 */
	@Test
	public void testDeleteInsertOkDeclaration() {
		assertTrue(getMethod("statusDeleteOk") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusDeleteOk(int)}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testDeleteOkHasOneParameter() {
		assertEquals(1, getMethod("statusDeleteOk").getParameterTypes().length);
		assertEquals("int", getMethod("statusDeleteOk").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#statusDeleteOk(int)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testDeleteInsertOkReturnString() {
		assertEquals("java.lang.String", getMethod("statusDeleteOk").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusDeleteError(int)}
	 * deklariert wurde.
	 */
	@Test
	public void testDeleteInsertErrorDeclaration() {
		assertTrue(getMethod("statusDeleteError") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#statusDeleteError(int)}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testDeleteInsertErrorHasOneParameter() {
		assertEquals(1, getMethod("statusDeleteOk").getParameterTypes().length);
		assertEquals("int", getMethod("statusDeleteOk").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#statusDeleteError(int)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testDeleteInsertErrorReturnString() {
		assertEquals("java.lang.String", getMethod("statusDeleteError").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#count()}
	 * deklariert wurde.
	 */
	@Test
	public void testCountDeclaration() {
		assertTrue(getMethod("count") != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#count()}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testCountNoParameter() {
		assertEquals(0, getMethod("count").getParameterTypes().length);
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#count()} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testCountReturnString() {
		assertEquals("java.lang.String", getMethod("count").getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#search(String, String)}
	 * deklariert wurde.
	 */
	@Test
	public void testSearchDeclaration() {
		assertTrue(getMethod("search", 2) != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#search(String, String)}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testSearchHaveTwoParameter() {
		assertEquals(2, getMethod("search", 2).getParameterTypes().length);
		assertEquals("java.lang.String", getMethod("search", 2).getParameterTypes()[0].getName());
		assertEquals("java.lang.String", getMethod("search", 2).getParameterTypes()[1].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#search(String, String)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testSearchReturnString() {
		assertEquals("java.lang.String", getMethod("search", 2).getReturnType().getName());
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#search(String, int)}
	 * deklariert wurde.
	 */
	@Test
	public void testSearchStringIntDeclaration() {
		assertTrue(getMethod("search", 1) != null);
	}
	
	/**
	 * Testet, ob die Methode {@link db.query.QueryInterface#search(String, int)}
	 * keinen Parameter besitzt.
	 */
	@Test
	public void testSearchStringIntHaveTwoParameter() {
		assertEquals(2, getMethod("search", 2).getParameterTypes().length);
		assertEquals("java.lang.String", getMethod("search", 1).getParameterTypes()[0].getName());
		assertEquals("int", getMethod("search", 1).getParameterTypes()[1].getName());
	}
	
	/**
	 * Testet, ob der Rückgabewert der Methode
	 * {@link db.query.QueryInterface#search(String, int)} vom Typ
	 * {@link java.lang.String} ist.
	 */
	@Test
	public void testSearchStringIntReturnString() {
		assertEquals("java.lang.String", getMethod("search", 1).getReturnType().getName());
	}
}
