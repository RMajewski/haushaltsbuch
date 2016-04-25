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

package tests.fixtures;

import fit.ActionFixture;
import tests.testcase.GuiWndTest;

/**
 * Von diesem Fixture werden alle Fixture abgeleitet, die Unterfenster mit
 * Tabellen testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class FixtureWndTables extends ActionFixture {
	/**
	 * Speichert den Namen der Datenbank-Tabelle
	 */
	protected String _tableName;

	/**
	 * Speichert die Test-Klasse
	 */
	protected GuiWndTest _test;
	
	/**
	 * Ermittelt die Überschrift der Spalte 0
	 * 
	 * @return Überschrift der Spalte 0
	 */
	public String columnHeader0() {
		return _test.getColumnHeader(0);
	}
	
	/**
	 * Ermittelt die Überschrift der Spalte 1
	 * 
	 * @return Überschrift der Spalte 1
	 */
	public String columnHeader1() {
		return _test.getColumnHeader(1);
	}
	
	/**
	 * Ermittelt die Anzahl der Spalten
	 */
	public String getTableColumnCount() {
		return String.valueOf(_test.getTableColumnCount());
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
	 * Ermittelt welche Zeile in der Tabelle ausgewählt ist.
	 * 
	 * @return Ausgewählte Zeile in der Tabelle
	 */
	public String getTableSelectedRow() {
		return String.valueOf(_test.getTableSelectedRow());
	}
	
	/**
	 * Ermittelt ob eine Tabelle vorhanden ist oder nicht.
	 */
	public String haveTable() {
		return String.valueOf(_test.existsTable());
	}
	
	/**
	 * Ermittelt ob der Dialog angezeigt wird.
	 */
	public String isDialogVisible() {
		return String.valueOf(_test.isDialogVisible());
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
	 * Öffnet das Popup-Menü
	 */
	public void openPopup() {
		_test.callPopup();
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
	 * Überprüft, ob das Popup-Menü einen Eintrag "Neu" hat
	 */
	public String popupHaveInsertItem() {
		return String.valueOf(_test.getPopupItem(0).getText().equals("Neu"));
	}
	
	/**
	 * Ermittelt die Anzahl der Items im Popup-Menü
	 */
	public String popupItemCount() {
		return String.valueOf(_test.getPopupItemCount());
	}
	
	/**
	 * Drückt im Dialog den Abbrechen-Button
	 */
	public void pushDialogCancel() {
		_test.pushDialogCancel();
	}
	
	/**
	 * Drückt im Dialog den Nein-Button
	 */
	public void pushDialogNo() {
		_test.pushDialogNo();
	}
	
	/**
	 * Drückt im Dialog den Ok-Button
	 */
	public void pushDialogOk() {
		_test.pushDialogOk();
	}
	
	/**
	 * Drückt im Dialog den Ja-Button
	 */
	public void pushDialogYes() {
		_test.pushDialogYes();
	}
	
	/**
	 * Drückt den Popup-Menü Eintrag "Löschen"
	 */
	public void pushPopupDelete() {
		_test.pushPopup("Löschen");
		_test.waitDlg("Datensatz löschen");
	}
	
	/**
	 * Selektiert in der Tabelle die erste Zeile.
	 */
	public void selectRowOne() {
		_test.tableSelectRow(0);
	}
	
}
