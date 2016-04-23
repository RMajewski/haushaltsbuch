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

package tests.tests.menus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JMenuItem;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.menus.PopupMoneyList;

/**
 * Testet, ob das Popup-Menü für die Tabelle Einnahmen und Ausgaben richtig
 * initalisiert wird.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 *
 */
public class TestPopupMoneyList {
	/**
	 * Speichert das Popup-Menü
	 */
	private PopupMoneyList _popup;
	
	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_popup = new PopupMoneyList(null);
	}
	
	/**
	 * Testet, ob Klase {@link menus.PopupMoneyList} von der Klasse
	 * {@link menus.PopupStandardList} abgeleitet ist.
	 */
	@Test
	public void testInheridedPopupStandardList() {
		assertEquals("haushaltsbuch.menus.PopupStandardList", PopupMoneyList.class.getSuperclass().getName());
	}
	
	/**
	 * Testet, ob es 5 Menü-Elemente gibt
	 * 
	 * @see menus.PopupMoneyList#PopupMoneyList(java.awt.event.ActionListener)
	 */
	@Test
	public void testHaveFifeItems() {
		assertEquals(5, _popup.getComponentCount());
	}
	
	/**
	 * Testet, ob es einen Eintrag "Details" gibt
	 * 
	 * @see menus.PopupMoneyList#PopupMoneyList(java.awt.event.ActionListener)
	 */
	@Test
	public void testHaveDetailsItem() {
		assertTrue(((JMenuItem)_popup.getComponent(4)).getText().equals("Details"));
	}
	
	
	/**
	 * Überprüft, den ActionCommand für "Details" auf Richtigkeit.
	 * 
	 * @see menus.PopupMoneyList#DETAILS
	 */
	@Test
	public void testActionCommandChangeIsRight() {
		assertEquals("PopupMoneyListDetails", PopupMoneyList.DETAILS);
	}
}