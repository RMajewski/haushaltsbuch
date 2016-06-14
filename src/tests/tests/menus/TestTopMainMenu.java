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
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.actions.PdfReport;
import haushaltsbuch.actions.Print;
import haushaltsbuch.elements.ToolBarMain;
import haushaltsbuch.menus.MainTop;

/**
 * Überprüft, ob alle Menü-Einträge vorhanden sind.
 * 
 * In der Version 0.2 wurde von jemmy-Test zu junit-Test gewechselt.
 * 
 * In der Version 0.3 wurde das Mockobjekt der Toolbar hinzugefügt, um auch die
 * Elemente, die durch die Aktionen beschrieben werden zu testen.
 * 
 * @author René Majewski
 * 
 * @version 0.3
 * @since 0.1
 */
public class TestTopMainMenu {
	/**
	 * Speichert das Hauptmenü
	 */
	private MainTop _menu;
	
	/**
	 * Speichert das Mockobjekt für die Toolbar
	 */
	private ToolBarMain _toolbar;
	
	/**
	 * Gibt an, an welcher Stelle das Datei-Menü liegen soll
	 */
	private static final int MENU_FILE = 0;
	
	/**
	 * Gibt an, an welcher Stelle das Db-Menü liegen soll
	 */
	private static final int MENU_DB = 1;
	
	/**
	 * Gibt an, an welcher Stelle das Export-Menü liegen soll
	 */
	private static final int MENU_EXPORT = 2;
	
	/**
	 * Gibt an, an welcher Stelle das Report-Menü liegen soll
	 */
	private static final int MENU_REPORT = 3;
	
	/**
	 * Gibt an, an welcher Stelle das Log-Menü liegen soll.
	 */
	private static final int MENU_LOG = 4;
	
	/**
	 * Gibt an, an welcher Stelle das Hilfe-Menü liegen soll.
	 */
	private static final int MENU_HELP = 5;
	
	/**
	 * Initalisiert das Menü
	 */
	@Before
	public void setUp() {
		_toolbar = mock(ToolBarMain.class);
		when(_toolbar.getPrint()).thenReturn(mock(Print.class));
		when(_toolbar.getPdfExport()).thenReturn(mock(PdfReport.class));
		
		_menu = new MainTop(null, _toolbar);
	}
	
	/**
	 * Überprüft, die richtige Anzahl an Menüs vorhanden ist.
	 */
	@Test
	public void testHaveRightMenu() {
		assertEquals(6, _menu.getMenuCount());
	}
	
	/**
	 * Überprüft, ob das Datei-Menü vorhanden ist und den richtigen Text hat.
	 */
	@Test
	public void testHaveFileMenu() {
		assertEquals("Datei", _menu.getMenu(MENU_FILE).getText());
	}
	
	/**
	 * Überprüft, ob das Datei-Menü genau 1 Menü-Punkt hat
	 */
	@Test
	public void testFileMenuHaveThreeItem() {
		assertEquals(3, _menu.getMenu(MENU_FILE).getItemCount());
	}
	
	/**
	 * Überprüft, ob das Datei-Menu die richtigen Einträge hat.
	 */
	@Test
	public void testFileMenuHaveRightItems() {
		assertEquals("Drucken", _menu.getMenu(MENU_FILE).getItem(0).getText());
		assertEquals("Beenden", _menu.getMenu(MENU_FILE).getItem(2).getText());
	}
	
	/**
	 * Überprüft, ob das Datenbank-Menü vorhanden ist und den richtigen Text
	 * hat.
	 */
	@Test
	public void testHaveDbMenu() {
		assertEquals("Datenbank", _menu.getMenu(MENU_DB).getText());
	}
	
	/**
	 * Überprüft, ob das Datenbank-Menü genau 4 Menü-Punkte hat
	 */
	@Test
	public void testDbMenuHaveFifeItems() {
		assertEquals(5, _menu.getMenu(MENU_DB).getItemCount());
	}
	
	/**
	 * Überprüft, ob das Datenbank-Menü die richtigen Einträge hat.
	 */
	@Test
	public void testDbMenuHaveRightItems() {
		assertEquals("Kategorien", _menu.getMenu(MENU_DB).getItem(0).getText());
		assertEquals("Geschäfte", _menu.getMenu(MENU_DB).getItem(1).getText());
		assertEquals("Zahlungsmethoden", _menu.getMenu(MENU_DB).getItem(2)
				.getText());
		assertEquals("Einnahmen und Ausgaben",
				_menu.getMenu(MENU_DB).getItem(4).getText());
	}
	
	/**
	 * Überprüft, ob das Export-Menü vorhanden ist und den richtigen Text
	 * hat.
	 */
	@Test
	public void testHaveExportMenu() {
		assertEquals("Export", _menu.getMenu(MENU_EXPORT).getText());
	}
	
	/**
	 * Überprüft, ob das Export-Menü genau 1 Menü-Punkt hat
	 */
	@Test
	public void testExportMenuHaveThreeItem() {
		assertEquals(3, _menu.getMenu(MENU_EXPORT).getItemCount());
	}
	
	/**
	 * Überprüft, ob das Export-Menü die richtigen Einträge hat.
	 */
	@Test
	public void testExportMenuHaveRightItems() {
		assertEquals("SQL-Script",
				_menu.getMenu(MENU_EXPORT).getItem(0).getText());
		assertEquals("PDF-Export",
				_menu.getMenu(MENU_EXPORT).getItem(2).getText());
	}
	
	/**
	 * Überprüft, ob das Report-Menü vorhanden ist und den richtigen Text
	 * hat.
	 */
	@Test
	public void testHaveReportMenu() {
		assertEquals("Report", _menu.getMenu(MENU_REPORT).getText());
	}
	
	/**
	 * Überprüft, ob das Report-Menü genau 3 Menü-Punkte hat
	 */
	@Test
	public void testDbMenuHaveThreeItems() {
		assertEquals(3, _menu.getMenu(MENU_REPORT).getItemCount());
	}
	
	/**
	 * Überprüft, ob das Report-Menü die richtigen Einträge hat.
	 */
	@Test
	public void testReportMenuHaveRightItems() {
		assertEquals("Wochenübersicht",
				_menu.getMenu(MENU_REPORT).getItem(0).getText());
		assertEquals("Monatsübersicht",
				_menu.getMenu(MENU_REPORT).getItem(1).getText());
		assertEquals("Jahresübersicht",
				_menu.getMenu(MENU_REPORT).getItem(2).getText());
	}
	
	/**
	 * Überprüft, ob das Log-Menü vorhanden ist und den richtigen Text
	 * hat.
	 */
	@Test
	public void testHaveLogMenu() {
		assertEquals("Log", _menu.getMenu(MENU_LOG).getText());
	}
	
	/**
	 * Überprüft, ob das Log-Menü genau 1 Menü-Punkt hat
	 */
	@Test
	public void testLogMenuHaveOneItem() {
		assertEquals(1, _menu.getMenu(MENU_LOG).getItemCount());
	}
	
	/**
	 * Überprüft, ob das Log-Menü die richtigen Einträge hat.
	 */
	@Test
	public void testLogMenuHaveRightItems() {
		assertEquals("Anzeigen...", _menu.getMenu(MENU_LOG).getItem(0).getText());
	}
	
	/**
	 * Überprüft, ob das Hilfe-Menü vorhanden ist und den richtigen Text
	 * hat.
	 */
	@Test
	public void testHaveHelpMenu() {
		assertEquals("Hilfe", _menu.getMenu(MENU_HELP).getText());
	}
	
	/**
	 * Überprüft, ob das Hilfe-Menü genau 2 Menü-Punkte hat
	 */
	@Test
	public void testHelpMenuHaveFiveItems() {
		assertEquals(5, _menu.getMenu(MENU_HELP).getItemCount());
	}
	
	/**
	 * Überprüft, ob das Hilfe-Menü die richtigen Einträge hat.
	 */
	@Test
	public void testHelpMenuHaveRightItems() {
		assertEquals("Onlinehilfe", 
				_menu.getMenu(MENU_HELP).getItem(0).getText());
		assertEquals("Lizenz...", _menu.getMenu(MENU_HELP).getItem(2).getText());
		assertEquals("Über...", _menu.getMenu(MENU_HELP).getItem(4).getText());
	}
	
	/**
	 * Überprüft, ob der Menü-Eintrag "Drucken" eine Aktion ist.
	 */
	@Test
	public void testHelpFilePrintIsAction() {
		assertEquals(_toolbar.getPrint(),
				_menu.getMenu(MENU_FILE).getItem(0).getAction());
	}
	
	/**
	 * Überprüft, ob der Menü-Eintrag "PdF-Export" eine Aktion ist.
	 */
	@Test
	public void testExportPdfIsAction() {
		assertEquals(_toolbar.getPdfExport(),
				_menu.getMenu(MENU_EXPORT).getItem(2).getAction());
	}
}
