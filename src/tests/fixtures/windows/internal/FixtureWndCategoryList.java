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

package tests.fixtures.windows.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JMenuItem;

import fit.ActionFixture;
import haushaltsbuch.db.DbController;
import tests.tests.windows.internal.TestWndCategoryList;

/**
 * Stellt die Funktionen für den Fit-Test des Unterfensters "Kategorien" bereit.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndCategoryList extends ActionFixture {

	/**
	 * Speichert die Test-Klasse
	 */
	private TestWndCategoryList _test;
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @throws Exception Wird geworfen, wenn ein Fehler aufgetreten ist. 
	 */
	public FixtureWndCategoryList() throws Exception {
		_test = new TestWndCategoryList();
	}
	
	/**
	 * Ermittelt von der angegebenen Spalte die Überschrift.
	 * 
	 * @param column Spalte, von der die Überschrift ermittelt werden soll.
	 * 
	 * @return Überschrift der angegebenen Spalte
	 */
	private String headerFromColumn(int column) {
		return _test.getColumnHeader(column);
	}
	
	/**
	 * Ermittelt die Überschrift der Spalte 0
	 * 
	 * @return Überschrift der Spalte 0
	 */
	public String columnHeader0() {
		return headerFromColumn(0);
	}
	
	/**
	 * Ermittelt die Überschrift der Spalte 1
	 * 
	 * @return Überschrift der Spalte 1
	 */
	public String columnHeader1() {
		return headerFromColumn(1);
	}
	
	/**
	 * Ermittelt die Anzahl der Zeilen
	 * 
	 * Anzahl der Zeilen
	 */
	public String getTableRowCount() {
		return String.valueOf(_test.getTableRowCount());
	}
	
	/**
	 * Ermittelt die Anzahl der Spalten
	 */
	public String getTableColumnCount() {
		return String.valueOf(_test.getTableColumnCount());
	}
	
	/**
	 * Ermittelt ob eine Tabelle vorhanden ist oder nicht.
	 */
	public String haveTable() {
		return String.valueOf(_test.existsTable());
	}
	
	/**
	 * Überprüft, ob das Popup-Menü einen Eintrag "Neu" hat
	 */
	public String popupHaveInsertItem() {
		return String.valueOf(_test.getPopupItem(0).getText().equals("Neu"));
	}
	
	/**
	 * Überprüft, ob das Popup-Menü einen Eintrag "Ändern" hat.
	 */
	public String popupHaveChangeItem() {
		return String.valueOf(_test.getPopupItem(1).getText().equals("Ändern"));
	}
	
	/**
	 * Überprüft, ob das Popup-Menü einen Eintrag "Löschen" hat.
	 */
	public String popupHaveDeleteItem() {
		return String.valueOf(_test.getPopupItem(2).getText().equals("Löschen"));
	}
	
	/**
	 * Öffnet das Popup-Menü
	 */
	public void openPopup() {
		_test.callPopup();
	}
	
	/**
	 * Ermittelt die Anzahl der Items im Popup-Menü
	 */
	public String popupItemCount() {
		return String.valueOf(_test.getPopupItemCount());
	}
	
	/**
	 * Drückt den Popop-Menü-Eintrag "Neu"
	 */
	public void pushPopupInsert() {
		_test.pushPopup("Neu");
		_test.waitDlg("Kategorie erstellen");
	}
	
	/**
	 * Ermittelt ob der Dialog angezeigt wird.
	 */
	public String isDialogVisible() {
		return String.valueOf(_test.isDialogVisible());
	}
	
	/**
	 * Übergibt den Text-Feld im Dialog den Namen
	 * 
	 * @param name Name der Kategorie
	 */
	public void setCategory(String name) {
		_test.setDialogText(name);
	}
	
	/**
	 * Drückt im Dialog den Abbrechen-Button
	 */
	public void pushDialogCancel() {
		_test.pushDialogCancel();
	}
	
	/**
	 * Drückt im Dialog den Ok-Button
	 */
	public void pushDialogOk() {
		_test.pushDialogOk();
	}
	
	/**
	 * Drückt im Dialog den Nein-Button
	 */
	public void pushDialogNo() {
		_test.pushDialogNo();
	}
	
	/**
	 * Drückt im Dialog den Ja-Button
	 */
	public void pushDialogYes() {
		_test.pushDialogYes();
	}
	
	/**
	 * Liest aus der Datenbank die letzte Kategorie und gibt deren Namen zurück.
	 * 
	 * @return Name der letzten Kategorie
	 * 
	 * @throws SQLException 
	 */
	public String getLastCategory() throws SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery("SELECT name FROM category ORDER BY id DESC");
		return rs.getString("name");
	}
	
	/**
	 * Ermittelt, ob der Popup-Menü Eintrag "Ändern" benutzbar ist oder nicht.
	 * 
	 * @return Ist der Eintrag "Ändern" benutzbar?
	 */
	public String isPopupChangeItemEnable() {
		return String.valueOf(_test.getPopupItem(1).isEnabled());
	}
	
	/**
	 * Ermittelt, ob der Popup-Menü Eintrag "Löschen" benutzbar ist oder nicht.
	 * 
	 * @return Ist der Eintrag "Löschen" benutzbar?
	 */
	public String isPopupDeleteItemEnable() {
		return String.valueOf(_test.getPopupItem(2).isEnabled());
	}
	
	/**
	 * Selektiert in der Tabelle die erste Zeile.
	 */
	public void selectRowOne() {
		_test.tableSelectRow(0);
	}
	
	/**
	 * Drückt den Popup-Menü Eintrag "Ändern"
	 */
	public void pushPopupChange() {
		_test.pushPopup("Ändern");
		_test.waitDlg("Kategorie ändern");
	}
	
	/**
	 * Drückt den Popup-Menü Eintrag "Löschen"
	 */
	public void pushPopupDelete() {
		_test.pushPopup("Löschen");
		_test.waitDlg("Datensatz löschen");
	}
	
	/**
	 * Ermittelt den Namen der Kategorie aus der selektieren Zeile.
	 * 
	 * @return Name der Kategorie der selektieren Zeile
	 */
	public String getSelectedCategory() {
		return _test.getTableSelectName();
	}
	
	/**
	 * Ermittelt welche Zeile in der Tabelle ausgewählt ist.
	 * 
	 * @return Ausgewählte Zeile in der Tabelle
	 */
	public String getTableSelectedRow() {
		return String.valueOf(_test.getTableSelectedRow());
	}
}
