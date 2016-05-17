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

package tests.tests.help;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.help.Help;

public class TestHelp {
	/**
	 * Instanz der Hilfe
	 */
	private Help _help;

	/**
	 * Initialisiert die Tests.
	 */
	@Before
	public void setUp() throws Exception {
		_help = Help.getInstance();
	}

	/**
	 * Testet, ob getInstance die gleiche Instanz liefert, wie sie schon
	 * geliefert wurde.
	 * 
	 * @see haushaltsbuch.help.Help#getInstance()
	 */
	@Test
	public void testGetInstance() {
		assertEquals(_help, Help.getInstance());
	}

	/**
	 * Testet, ob ein initialsierte HelpSet zurückgegeben wird.
	 * 
	 * @see haushaltsbuch.help.Help#getHelpSet()
	 */
	@Test
	public void testGetHelpSet() {
		assertNotNull(_help.getHelpSet());
		assertEquals("main", _help.getHelpSet().getHomeID().id);
	}

	/**
	 * Testet, ob ein initialisierter HelpBroker zurückgegeben wird.
	 * 
	 * @see haushaltsbuch.help.Help#getHelpBroker()
	 */
	@Test
	public void testGetHelpBroker() {
		assertNotNull(_help.getHelpBroker());
	}

}
