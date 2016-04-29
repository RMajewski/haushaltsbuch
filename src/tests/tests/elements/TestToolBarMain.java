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

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.actions.DbInsert;
import haushaltsbuch.elements.ToolBarMain;

/**
 * Implementiert die Test der Klasse {@link haushaltsbar.elements.ToolBarMain}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarMain {

	/**
	 * Speichert die Instanz der Toolbar
	 */
	private ToolBarMain _test;
	
	/**
	 * Initalisiert die Tests
	 */
	@Before
	public void setUp() throws Exception {
		_test = new ToolBarMain(null, null);
	}
	
	/**
	 * Überprüft, ob die {@link haushaltsbuch.elements.ToolbarMain} von der
	 * Klasse JToolBar abgeleitet wurde.
	 */
	@Test
	public void testToolBarMainInheritedJToolBar() {
		assertEquals("javax.swing.JToolBar",
				ToolBarMain.class.getSuperclass().getName());
	}

	/**
	 * Überprüft, ob alle Elemente hinzugefügt wurden.
	 * 
	 * @see haushaltsbuch.elements.ToolBarMain#ToolBarMain()
	 */
	@Test
	public void testToolBarMainHaveFifeElements() {
		assertEquals(5, _test.getComponentCount());
	}
	
	/**
	 * Überprüft, ob die Aktion zum Neuen Datensatz einfügen, eingefügt wurde.
	 * 
	 * @see haushaltsbuch.elements.ToolBarMain#ToolBarMain()
	 */
	@Test
	public void testToolBarMainHaveActionDbInsert() {
		assertEquals("haushaltsbuch.actions.DbInsert", 
				((JButton)_test.getComponent(0)).getAction().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die Aktion zum Datensatz Ändern, eingefügt wurde.
	 * 
	 * @see haushaltsbuch.elements.ToolBarMain#ToolBarMain()
	 */
	@Test
	public void testToolBarMainHaveActionDbChange() {
		assertEquals("haushaltsbuch.actions.DbChange", 
				((JButton)_test.getComponent(1)).getAction().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die Aktion zum Datensatz löschen, eingefügt wurde.
	 * 
	 * @see haushaltsbuch.elements.ToolBarMain#ToolBarMain()
	 */
	@Test
	public void testToolBarMainHaveActionDbDelete() {
		assertEquals("haushaltsbuch.actions.DbDelete", 
				((JButton)_test.getComponent(2)).getAction().getClass().getName());
	}
	
	/**
	 * Überprüft, ob die Aktion zum erzeugen eines Reportes, eingefügt wurde.
	 * 
	 * @see haushaltsbuch.elements.ToolBarMain#ToolBarMain()
	 */
	@Test
	public void testToolBarMainHaveActionReport() {
		assertEquals("haushaltsbuch.actions.Report", 
				((JButton)_test.getComponent(4)).getAction().getClass().getName());
	}
}