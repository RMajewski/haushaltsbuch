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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.events.ToolBarDbElementEvent;
import haushaltsbuch.events.ToolBarDbElementEventMulticaster;
import haushaltsbuch.listener.ToolBarDbElementListener;
import haushaltsbuch.windows.internal.WndInternalFrame;
import haushaltsbuch.windows.internal.WndTableFrame;

/**
 * Testet die Klasse
 * {@link haushaltsbuch.events.ToolBarDbElementEventMulticaster}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarDbElementEventMulticaster {
	class AbstractToolBarDbElementListener implements ToolBarDbElementListener {

		@Override
		public void setDbElementsEnable(ToolBarDbElementEvent e) {
		}
		
	}
	
	/**
	 * Speichert den Multicaster
	 */
	private ToolBarDbElementEventMulticaster _test;
	
	/**
	 * Speichert das Mock-Objekt eines Listeners
	 */
	private AbstractToolBarDbElementListener _listener;

	/**
	 * Initalisiert die Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_test = new ToolBarDbElementEventMulticaster();
		_listener = mock(AbstractToolBarDbElementListener.class);
	}

	/**
	 * Testet, ob ein Listener der Liste hinzugefügt werden kann.
	 */
	@Test
	public void testAdd() {
		assertEquals(0, _test.getListener().size());
		_test.add(_listener);
		assertEquals(1, _test.getListener().size());
	}
	
	/**
	 * Testet, ein Listener nicht zweimal in die Liste eingefügt werden kann.
	 */
	@Test
	public void testAddNotAddEqualsListener() {
		assertEquals(0, _test.getListener().size());
		_test.add(_listener);
		_test.add(_listener);
		assertEquals(1, _test.getListener().size());
	}

	/**
	 * Testet, ob ein Listener aus der Liste gelöscht werden kann.
	 */
	@Test
	public void testRemove() {
		_test.add(_listener);
		
		assertEquals(1, _test.getListener().size());
		_test.remove(_listener);
		assertEquals(0, _test.getListener().size());
	}
	
	/**
	 * Testet, ob das Event an alle gespeicherten Listener gesandt wird.
	 */
	@Test
	public void testSetDbElementsEnableToAllListener() {
		ToolBarDbElementEventMulticaster l2 = mock(
				ToolBarDbElementEventMulticaster.class);
		WndTableFrame frame = mock(WndTableFrame.class);
		
		ToolBarDbElementEvent e = new ToolBarDbElementEvent(new Object(), true,
				frame);
		
		_test.add(l2);
		_test.add(_listener);
		_test.setDbElementsEnable(e);
		
		verify(l2, times(1)).setDbElementsEnable(e);
		verify(_listener, times(1)).setDbElementsEnable(e);
	}
}
