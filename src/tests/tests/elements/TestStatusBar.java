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

package tests.tests.elements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.LogData;
import haushaltsbuch.elements.StatusBar;

/**
 * Testet die Klasse {@link elements.StatusBar}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestStatusBar {

	/**
	 * Initalisiert die einezlnen Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * Räumt nach den einzelnen Tests auf
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		StatusBar.getInstance().close();
	}
	
	/**
	 * Testet, ob die StatusBar von JLabel abgeleitet wurde.
	 * 
	 * @see elements.StatusBar
	 */
	@Test
	public void testStatusBarDerivedJLabel() {
		assertEquals("javax.swing.JLabel", StatusBar.class.getSuperclass().getName());
	}
	
	/**
	 * Testet, ob die zurück gegebene Instanz nicht null ist.
	 * 
	 * @see elements.StatusBar#getInstance()
	 */
	@Test
	public void testGetInstanceReturnNotNull() {
		assertNotNull(StatusBar.getInstance());
	}
	
	/**
	 * Testet, ob {@link elements.StatusBar#getInstance()} eine Instanz von
	 * StatusBar zurück gibt.
	 */
	@Test
	public void testGetInstanceReturnRightClass() {
		assertEquals("haushaltsbuch.elements.StatusBar", StatusBar.getInstance().getClass().getName());
	}
	
	/**
	 * Testet, ob die StatusBar initalisiert wurde, auch wenn vorher
	 * {@link elements.StatusBar#close()} aufgerufen wurde.
	 * 
	 * @see elements.StatusBar#getInstance()
	 */
	@Test
	public void testGetInstanceAfterCloser() {
		StatusBar.getInstance().close();
		assertNotNull(StatusBar.getInstance());
	}
	
	/**
	 * Testet, ob eine initalisierte Liste (ohne Einträge) zurück gebenen wird.
	 * 
	 * @see elements.StatusBar#getLog()
	 */
	@Test
	public void testGetLog() {
		List<LogData> list = new ArrayList<LogData>();
		assertEquals(list, StatusBar.getInstance().getLog());
		assertEquals(0, StatusBar.getInstance().getLog().size());
	}
	
	/**
	 * Testet, ob eine "normale" Nachricht in die Liste eingefügt werden wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageInsertList() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessage(message);
		List<LogData> list = StatusBar.getInstance().getLog();
		assertEquals(1, list.size());
		assertEquals(LogData.NONE, list.get(0).getOut());
		assertEquals(message, list.get(0).getMessage());
	}
	
	/**
	 * Testet, ob eine "normale" Nachricht angezeigt wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageDrawMessage() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessage(message);
		assertEquals(message, StatusBar.getInstance().getText());
		assertEquals(LogData.COLOR_NONE, StatusBar.getInstance().getBackground());
	}
	
	/**
	 * Testet, ob eine Warnung in die Liste eingefügt werden wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsWarningInsertList() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsWarning(message);
		List<LogData> list = StatusBar.getInstance().getLog();
		assertEquals(1, list.size());
		assertEquals(LogData.WARNING, list.get(0).getOut());
		assertEquals(message, list.get(0).getMessage());
	}
	
	/**
	 * Testet, ob eine Warnung angezeigt wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsWarningDrawMessage() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsWarning(message);
		assertEquals(message, StatusBar.getInstance().getText());
		assertEquals(LogData.COLOR_WARNING, StatusBar.getInstance().getBackground());
	}
	
	/**
	 * Testet, ob ein Fehler in die Liste eingefügt werden wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsErrorInsertList() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsError(message);
		List<LogData> list = StatusBar.getInstance().getLog();
		assertEquals(1, list.size());
		assertEquals(LogData.ERROR, list.get(0).getOut());
		assertEquals(message, list.get(0).getMessage());
	}
	
	/**
	 * Testet, ob ein Fehler angezeigt wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsErrorDrawMessage() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsError(message);
		assertEquals(message, StatusBar.getInstance().getText());
		assertEquals(LogData.COLOR_ERROR, StatusBar.getInstance().getBackground());
	}
	
	/**
	 * Testet, ob eine Nachricht, die nicht angezeigt werden soll, in die
	 * Liste eingefügt wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsNoOutInsertList() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsNoOut(message);
		List<LogData> list = StatusBar.getInstance().getLog();
		assertEquals(1, list.size());
		assertEquals(LogData.NO_OUT, list.get(0).getOut());
		assertEquals(message, list.get(0).getMessage());
	}
	
	/**
	 * Testet, ob eine Nachricht, die nicht angezeigt werden soll, auch nicht
	 * angezeigt wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsNoOutDrawMessage() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsNoOut(message);
		assertEquals("", StatusBar.getInstance().getText());
	}
	
	/**
	 * Testet, ob eine alles "In Ordnung"-Nachricht in die Liste eingefügt
	 * werden wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsOkInsertList() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsOk(message);
		List<LogData> list = StatusBar.getInstance().getLog();
		assertEquals(1, list.size());
		assertEquals(LogData.OK, list.get(0).getOut());
		assertEquals(message, list.get(0).getMessage());
	}
	
	/**
	 * Testet, ob eine alles "In Ordnung"-Nachricht angezeigt wird.
	 * 
	 * @see elements.StatusBar#setMessage(String)
	 */
	@Test
	public void testSetMessageAsOkDrawMessage() {
		String message = "Dies ist ein Test";
		StatusBar.getInstance().setMessageAsOk(message);
		assertEquals(message, StatusBar.getInstance().getText());
		assertEquals(LogData.COLOR_OK, StatusBar.getInstance().getBackground());
	}
}
