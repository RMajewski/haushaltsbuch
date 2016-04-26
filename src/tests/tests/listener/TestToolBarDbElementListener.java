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

package tests.tests.listener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.listener.ToolBarDbElementListener;
import tests.testcase.TestHelper;

/**
 * Testet das Interface {@link haushaltsbuch.listener.ToolBarDbElementListener}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarDbElementListener extends TestHelper {

	/**
	 * Initalisiert die Tests.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_methods = ToolBarDbElementListener.class.getMethods();
	}

	/**
	 * Testet ob {@link haushaltsbuch.listener.ToolBarDbElementListener} von
	 * EventListener abgeleitet wurde.
	 */
	@Test
	public void testToolBarDbElementListenerInheritedEventListener() {
		assertEquals("interface java.util.EventListener", 
				ToolBarDbElementListener.class.getGenericInterfaces()[0].toString());
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.listener.ToolBarDbElementListener#setDbElementsEnable(haushaltsbuch.events.ToolBarDbElementEvent)}
	 * deklariert wurde.
	 */
	@Test
	public void testSetDbElementsEnableDeclaration() {
		assertNotNull(getMethod("setDbElementsEnable"));
	}

	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.listener.ToolBarDbElementListener#setDbElementsEnable(haushaltsbuch.events.ToolBarDbElementEvent)}
	 * einen Parameter hat.
	 */
	@Test
	public void testSetDbElementsEnableHasOneParameter() {
		assertEquals(1,
				getMethod("setDbElementsEnable").getParameterTypes().length);
		assertEquals("haushaltsbuch.events.ToolBarDbElementEvent", 
				getMethod("setDbElementsEnable").getParameterTypes()[0].getName());
	}
	
	/**
	 * Testet, ob die Methode
	 * {@link haushaltsbuch.listener.ToolBarDbElementListener#setDbElementsEnable(haushaltsbuch.events.ToolBarDbElementEvent)}
	 * keinen Rückgabe-Wert hat.
	 */
	@Test
	public void testSetDbElementsEnableNoReturnType() {
		assertEquals("void", getMethod("setDbElementsEnable").getReturnType().getName());
	}
}
