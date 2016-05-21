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
import haushaltsbuch.actions.Print;

/**
 * Implementiert die Tests für {@link haushaltsbuch.actions.Print}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class TestPrint {
	/**
	 * Speichert die Instanz von Print
	 */
	private Print _test;

	@Before
	public void setUp() throws Exception {
		_test = new Print(null);
	}
	
	/**
	 * Es wird überprüft, ob die Klasse {@link haushaltsbuch.actions.Print}
	 * von der Klasse {@link haushaltsbuch.actions.Action} abgeleitet wurde.
	 */
	@Test
	public void testDbNewInheritedAction() {
		assertEquals("haushaltsbuch.actions.Action",
				Print.class.getSuperclass().getName());
	}

	/**
	 * Überprüft, ob das Kommando richtig gesetzt wurde.
	 */
	@Test
	public void testCommandIsRight() {
		assertEquals(Print.COMMAND, _test.getValue(Action.ACTION_COMMAND_KEY));
	}

	/**
	 * Überprüft, ob der Name richtig gesetzt wurde.
	 */
	@Test
	public void testNameIsRight() {
		assertEquals("Drucken", _test.getValue(Action.NAME));
	}

	/**
	 * Überprüft, ob die Mnemonic richtig gesetzt ist.
	 */
	@Test
	public void testMnemonicIsRight() {
		assertEquals(1, _test.getValue(Action.MNEMONIC_KEY));
	}
	
	/**
	 * Überprüft, ob das Kommando richtig gesetzt wurde.
	 * 
	 * @see haushaltsbuch.actions.DbChange#COMMAND
	 */
	@Test
	public void testCommandStringIsRight() {
		assertEquals("Print", Print.COMMAND);
	}

}
