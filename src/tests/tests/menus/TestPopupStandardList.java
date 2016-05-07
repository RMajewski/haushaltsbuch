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

import haushaltsbuch.menus.PopupStandardList;

/**
 * Testet das standart Popup-Menü für Listen
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestPopupStandardList {
	/**
	 * Speichert das Popup-Menü
	 */
	private PopupStandardList _popup;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_popup = new PopupStandardList(null);
	}
	
	/**
	 * Testet, ob das Popup-Menü 3 Einträge hat
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testHaveThreeItems() {
		assertEquals(3, _popup.getComponentCount());
	}
	
	/**
	 * Testet, ob der Menü-Eintrag für "Neu" vorhanden ist
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testHaveInsertItem() {
		assertTrue(((JMenuItem)_popup.getComponent(0)).getText().equals("Neu"));
	}
	
	/**
	 * Testet, ob der Menü-Eintrag für "Ändern" vorhanden ist
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testHaveChangeItem() {
		assertTrue(((JMenuItem)_popup.getComponent(1)).getText().equals("Ändern"));
	}
	
	/**
	 * Testet, ob der Menü-Eintrag für "Löschen" vorhanden ist
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testHaveDeleteItem() {
		assertTrue(((JMenuItem)_popup.getComponent(2)).getText().equals("Löschen"));
	}
	
	/**
	 * Überprüft, den ActionCommand für "Neu" auf Richtigkeit.
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#NEW
	 */
	@Test
	public void testActionCommandNewIsRight() {
		assertEquals("PopupStandardListNew", PopupStandardList.NEW);
	}
	
	/**
	 * Überprüft, den ActionCommand für "Ändern" auf Richtigkeit.
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#CHANGE
	 */
	@Test
	public void testActionCommandChangeIsRight() {
		assertEquals("PopupStandardListChange", PopupStandardList.CHANGE);
	}
	
	/**
	 * Überprüft, den ActionCommand für "Löschen" auf Richtigkeit.
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#DELETE
	 */
	@Test
	public void testActionCommandDELETEIsRight() {
		assertEquals("PopupStandardListDelete", PopupStandardList.DELETE);
	}
}
