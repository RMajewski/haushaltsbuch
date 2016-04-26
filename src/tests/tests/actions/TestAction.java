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

package tests.tests.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.actions.Action;

/**
 * Testet die Klasse {@link haushaltsbuch.actions.Action}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestAction {

	/**
	 * Speichert die Instanz von Action
	 */
	private TestActionImplementation _action;
	
	/**
	 * Initalisiert die Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_action = new TestActionImplementation("insert_small.png",
				"insert_big.png");
	}

	/**
	 * Überprüft, ob die Klasse {@link haushaltsbuch.action.Action} von der
	 * Klasse AbstractAction abgeleitet wurde.
	 */
	@Test
	public void testActionInheritedAbstractAction() {
		assertEquals("javax.swing.AbstractAction", 
				Action.class.getSuperclass().getName());
	}
	
	/**
	 * Überprüft, ob das Icon für die Menüs gesetzt wurde.
	 * 
	 * @see haushaltsbuch.actions.Action#Action(String, String)
	 */
	@Test
	public void testIsSetSmallIcon() {
		assertNotNull(_action.getValue(Action.SMALL_ICON));
	}
	
	/**
	 * Überprüft, ob das Icon für die Toolbar gesetzt wurde.
	 * 
	 * @see haushaltsbuch.actions.Action#Action(String, String)
	 */
	@Test
	public void testIsSetBigIcon() {
		assertNotNull(_action.getValue(Action.LARGE_ICON_KEY));
	}

}
