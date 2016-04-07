package test.db.query;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import db.query.QueryInterface;

/**
 * Testet ob das {@link db.query.QueryInterface}-Interface alle Methoden
 * deklariert hat.
 * 
 * @author René Majewski
 *
 */
public class TestQueryInterface {
	/**
	 * Speichert die einzelnen Methoden
	 */
	private Method _methods[];
	
	/**
	 * Sucht die angegebene Methode aus dem Methoden-Array.
	 * 
	 * @param name Name der Methode
	 * 
	 * @return Gibt <b>null</b> zurück, wenn die Methode nicht gefunden wurde.
	 * Wurde die Methode gefunden, so wird diese zurück gegeben.
	 */
	private Method getMethod(String name) {
		// Array durchlaufen
		for (Method method : _methods) {
			if (method.getName().equals(name)) {
				return method;
			}
		}
	
		// Da bisher nicht beendet wurde, null zurück geben
		return null;
	}
	
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
}
