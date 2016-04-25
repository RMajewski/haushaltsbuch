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

import haushaltsbuch.db.DbController;
import tests.fixtures.FixtureWndTables;
import tests.tests.windows.internal.TestWndSectionList;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndSectionList und dem Test-Programm TestWndSectionList dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndSectionList extends FixtureWndTables {

	/**
	 * Initalisiert die Klasse
	 */
	public FixtureWndSectionList() throws Exception {
		_test = new TestWndSectionList();
	}
	
	/**
	 * Drückt den Popup-Menü Eintrag "Ändern"
	 */
	public void pushPopupChange() {
		_test.pushNoBlockPopup("Ändern");
		_test.waitDlg("Geschäft ändern");
	}
	
	/**
	 * Drückt den Popop-Menü-Eintrag "Neu"
	 */
	public void pushPopupInsert() {
		_test.pushNoBlockPopup("Neu");
		_test.waitDlg("Geschäft erstellen");
	}
	
	/**
	 * Übergibt den Text-Feld im Dialog den Namen
	 * 
	 * @param name Name der Kategorie
	 */
	public void setSection(String name) {
		_test.setDialogText(name);
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
		ResultSet rs = stm.executeQuery("SELECT name FROM section ORDER BY id DESC");
		return rs.getString("name");
	}
	
	/**
	 * Ermittelt den Namen der Kategorie aus der selektieren Zeile.
	 * 
	 * @return Name der Kategorie der selektieren Zeile
	 */
	public String getSelectedCategory() {
		return ((TestWndSectionList)_test).getTableSelectName();
	}
	
	/**
	 * Es wird auf die 1. Zeile doppelt geklickt.
	 */
	public void tableDoubleClick() {
		_test.tableDoubleClick(0);
		_test.waitDlg("Geschäft ändern");
	}
}
