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
import haushaltsbuch.elements.StatusBar;
import tests.fixtures.FixtureWndTables;
import tests.tests.windows.internal.TestWndPaymentList;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndPaymentList und dem Test-Programm TestWndPaymentList dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class FixtureWndPaymentList extends FixtureWndTables {

	/**
	 * Initalisiert die Klasse
	 */
	public FixtureWndPaymentList() throws Exception {
		_test = new TestWndPaymentList();
	}
	
	/**
	 * Drückt den Popup-Menü Eintrag "Ändern"
	 */
	public void pushPopupChange() {
		_test.pushNoBlockPopup("Ändern");
		_test.waitDlg("Zahlungsmethode ändern");
	}
	
	/**
	 * Drückt den Popop-Menü-Eintrag "Neu"
	 */
	public void pushPopupInsert() {
		_test.pushNoBlockPopup("Neu");
		_test.waitDlg("Neue Zahlungsmethode eingeben");
	}
	
	/**
	 * Übergibt den Text-Feld im Dialog den Namen
	 * 
	 * @param name Name der Zahlungsmethode
	 */
	public void setPayment(String name) {
		_test.setDialogText(name);
	}
	
	/**
	 * Liest aus der Datenbank die letzte Zahlungsmethode und gibt deren Namen
	 * zurück.
	 * 
	 * @return Name der letzten Zahlungsmethode
	 * 
	 * @throws SQLException 
	 */
	public String getLastPayment() throws SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery("SELECT name FROM payment ORDER BY id DESC");
		return rs.getString("name");
	}
	
	/**
	 * Ermittelt den Namen der Zahlungsmethode aus der selektieren Zeile.
	 * 
	 * @return Name der Zahlungsmethode der selektieren Zeile
	 */
	public String getSelectedPayment() {
		return ((TestWndPaymentList)_test).getTableSelectName();
	}
	
	/**
	 * Es wird auf die 1. Zeile doppelt geklickt.
	 */
	public void tableDoubleClick() {
		_test.tableDoubleClick(0);
		_test.waitDlg("Zahlungsmethode ändern");
	}
	
	/**
	 * Ermittelt die angezeigte Status-Nachricht
	 * 
	 * @return Angezeigte Status-Nachricht
	 */
	public String getStatusMessage() {
		return StatusBar.getInstance().getText();
	}
	
	/**
	 * Ermittelt, ob im Text-Feld des Dialoges etwas steht.
	 * 
	 * @return Steht etwas im Text-Feld des Dialoges?
	 */
	public boolean hasPaymentName() {
		String name = ((TestWndPaymentList)_test).getDialogTextFieldText();
		return (name != null) && !name.isEmpty();
	}

}
