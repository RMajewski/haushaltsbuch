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

/**
 * Von diesem Fixture werden alle Fixture abgeleitet, die Unterfenster mit
 * Tabellen testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class FixtureWndTables extends FixtureWnd {
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
	 * Ermittelt die Überschrift der Spalte 2
	 * 
	 * @return Überschrift der Spalte 2
	 */
	public String columnHeader2() {
		return _test.getColumnHeader(2);
	}
	
	/**
	 * Ermittelt die Überschrift der Spalte 3
	 * 
	 * @return Überschrift der Spalte 3
	 */
	public String columnHeader3() {
		return _test.getColumnHeader(3);
	}
	
	/**
	 * Ermittelt die Überschrift der Spalte 4
	 * 
	 * @return Überschrift der Spalte 4
	 */
	public String columnHeader4() {
		return _test.getColumnHeader(4);
	}
	
	/**
	 * Ermittelt die Überschrift der Spalte 5
	 * 
	 * @return Überschrift der Spalte 5
	 */
	public String columnHeader5() {
		return _test.getColumnHeader(5);
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
	 * Selektiert in der Tabelle die erste Zeile.
	 */
	public void selectRowOne() {
		_test.tableSelectRow(0);
	}
	
	/**
	 * Ermittelt, ob ein Popup-Menü-Eintrag "Details" existiert
	 * 
	 * @return Existiert der Popup-Menü-Eintrag "Details"?
	 */
	public String popupHaveDetailsItem() {
		return String.valueOf(_test.getPopupItem(4).getText().equals(
				"Details"));
	}
	
}
