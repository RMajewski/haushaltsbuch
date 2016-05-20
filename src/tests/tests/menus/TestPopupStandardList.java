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
import static org.mockito.Mockito.*;

import javax.swing.JMenuItem;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.actions.DbChange;
import haushaltsbuch.actions.DbDelete;
import haushaltsbuch.actions.DbInsert;
import haushaltsbuch.elements.ToolBarMain;
import haushaltsbuch.menus.PopupStandardList;

/**
 * Testet das standart Popup-Menü für Listen
 * 
 * In der Version 0.2 wurde das Mockobjekt der Toolbar hinzugefügt, um die
 * Aktionen richtig zu testen.
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.1
 */
public class TestPopupStandardList {
	/**
	 * Speichert das Popup-Menü
	 */
	private PopupStandardList _popup;
	
	/**
	 * Speichert das Mockobjekt der Toolbar
	 */
	private ToolBarMain _toolbar;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_toolbar = mock(ToolBarMain.class);
		when(_toolbar.getDbInsert()).thenReturn(mock(DbInsert.class));
		when(_toolbar.getDbChange()).thenReturn(mock(DbChange.class));
		when(_toolbar.getDbDelete()).thenReturn(mock(DbDelete.class));
		
		_popup = new PopupStandardList(_toolbar);
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
	 * Testet, ob der Menü-Eintrag "Neu" eine Aktion ist.
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testInsertItemIsAction() {
		assertEquals(_toolbar.getDbInsert(),
				((JMenuItem)_popup.getComponent(0)).getAction());
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
	 * Testet, ob der Menü-Eintrag "Ändern" eine Aktion ist.
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testChangeItemIsAction() {
		assertEquals(_toolbar.getDbChange(),
				((JMenuItem)_popup.getComponent(1)).getAction());
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
	 * Testet, ob der Menü-Eintrag "Löschen" eine Aktion ist.
	 * 
	 * @see haushaltsbuch.menus.PopupStandardList#PopupStandardList(java.awt.event.ActionListener)
	 */
	@Test
	public void testDeleteItemIsAction() {
		assertEquals(_toolbar.getDbDelete(),
				((JMenuItem)_popup.getComponent(2)).getAction());
	}
}
