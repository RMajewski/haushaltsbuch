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
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.actions.Action;
import haushaltsbuch.windows.internal.WndInternalFrame;

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
	
	/**
	 * Überprüft, ob das gespeicherte Fenster zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.actions.Action#getFrame()
	 */
	@Test
	public void testGetFrame() {
		assertNull(_action.getFrame());
	}
	
	/**
	 * Überprüft, ob auch ein Fenster gespeichert wird.
	 * 
	 * @see haushaltsbuch.actions.Action#setFrame(haushaltsbuch.windows.internal.WndInternalFrame)
	 */
	@Test
	public void testSetFrame() {
		WndInternalFrame frame = mock(WndInternalFrame.class);
		_action.setFrame(frame);
		assertEquals(frame, _action.getFrame());
	}

	/**
	 * Überprüft, ob ein Fehler ausgelöst wird, wenn <b>null</b> statt einem
	 * Fenster übergeben wird.
	 * 
	 * @see haushaltsbuch.actions.Action#setFrame(WndInternalFrame)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetFrameWithNullAsParamterThrowsIllegalArgumentException() {
		_action.setFrame(null);
	}
	
	/**
	 * Überprüft, ob das Fenster auch wieder gelöscht wird.
	 * 
	 * @see haushaltsbuch.actions.Action#deleteFrame()
	 */
	@Test
	public void testDeleteFrame() {
		WndInternalFrame frame = mock(WndInternalFrame.class);
		_action.setFrame(frame);
		_action.deleteFrame();
		assertEquals(null, _action.getFrame());
	}
}
