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

package tests.tests.events;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.events.ToolBarDbElementEvent;

/**
 * Testet das Ereignis {@link haushaltsbuch.events.ToolBarDbElementEvent}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarDbElementEvent {

	/**
	 * Speichert die Instanz vom Ereignis
	 */
	private ToolBarDbElementEvent _event;
	
	/**
	 * Initalisiert die Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_event = new ToolBarDbElementEvent(new Object(), true);
	}

	/**
	 * Überprüft, ob ein Fehler ausgelöst wird, wenn null als "source" übergeben
	 * wird.
	 * 
	 * @see haushaltsbuch.events.ToolBarDbElementEvent
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testToolBarDbElementEventWithNullAsSource() {
		_event = new ToolBarDbElementEvent(null, false);
	}
	
	/**
	 * Testet, ob der Konstruktor richtig initalisiert hat.
	 * 
	 * @see haushaltsbuch.events.ToolBarDbElementEvent#ToolBarDbElementEvent(Object, boolean)
	 */
	@Test
	public void testToolBarDbElementEvent() {
		assertNotNull(_event.getSource());
	}

	/**
	 * Testet, ob richtig zurück gegeben wird, ob die Elemente "Ändern" und
	 * "Löschen" angezeigt werden sollen.
	 * 
	 * @see haushaltsbuch.events.ToolBarDbElementEvent#isEnable()
	 */
	@Test
	public void testIstEnable() {
		assertTrue(_event.isEnable());
	}

}
