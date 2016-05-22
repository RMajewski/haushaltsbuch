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

import java.sql.Statement;
import java.util.Date;

import haushaltsbuch.db.DbController;
import haushaltsbuch.helper.HelperCalendar;
import tests.fixtures.FixtureWndTables;
import tests.tests.windows.internal.TestWndMoneyList;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndMoneyList und dem Test-Programm TestWndMoneyList dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndMoneyList extends FixtureWndTables {
	/**
	 * Initalisiert die Klasse
	 */
	public FixtureWndMoneyList() throws Exception {
		_test = new TestWndMoneyList();
	}

	/**
	 * Fügt einen Datensatz ein.
	 */
	public void databaseInsertMoney() throws Exception {
		Statement stm = DbController.getInstance().createStatement();
		stm.executeUpdate(DbController.queries().money().insert(
				new Date().getTime(), true, "Dies ist ein Test"));
		_test.tableRefreshData();
	}
	
	/**
	 * Drückt auf den Popup-Menü-Eintrag "Neu"
	 */
	public void pushPopupInsert() {
		_test.pushPopup("Neu");
		((TestWndMoneyList)_test).waitWindow("Neuen Datensatz erstellen");
	}
	
	/**
	 * Drück auf den Popup-Menü-Eintrag "Ändern"
	 */
	public void pushPopupChange() {
		_test.pushPopup("Ändern");
		((TestWndMoneyList)_test).waitWindow("Datensatz ändern");
	}
	
	/**
	 * Drück auf den Popup-Menü-Eintrag "Details"
	 */
	public void pushPopupDetails() {
		String date = HelperCalendar.dateToString(new Date().getTime());
		_test.pushPopup("Details");
		((TestWndMoneyList)_test).waitWindow("Details zur Einnahme vom " +
				date);
	}
	
	/**
	 * Drückt den Abbrechen-Button im Unterfenster
	 */
	public void pushWindowCancel() {
		((TestWndMoneyList)_test).pushWindowCancel();
	}
	
	/**
	 * Beendet das Unterfenster für die Details.
	 */
	public void pushWindowClose() {
		((TestWndMoneyList)_test).windowClose();
	}
	
	/**
	 * Ermittelt von der Selektierten Zeile die ID.
	 * 
	 * @return ID, die in der Selektierten Zeile steht.
	 */
	public String getSelectedId() {
		return String.valueOf(((TestWndMoneyList)_test).getTableSelectedId());
	}
	
	/**
	 * Ermittelt von der Selektierten Zeile das Datum.
	 * 
	 * @return Datum, das in der Selektierten Zeile steht.
	 */
	public String isSelectedDateActual() {
		String date = HelperCalendar.dateToString(new Date().getTime());
		return String.valueOf(
				((TestWndMoneyList)_test).getTableSelectedDate().equals(date));
	}
	
	/**
	 * Ermittelt von der Selektierten Zeile die Einnahme oder Ausgabe.
	 * 
	 * @return Einnahme oder Ausgabe, was in der Selektierten Zeile steht.
	 */
	public String getSelectedInOut() {
		return String.valueOf(
				((TestWndMoneyList)_test).getTableSelectedInOut());
	}
	
	/**
	 * Ermittelt von der Selektierten Zeile den Kommentar.
	 * 
	 * @return Kommentar oder Ausgabe, was in der Selektierten Zeile steht.
	 */
	public String getSelectedComment() {
		return ((TestWndMoneyList)_test).getTableSelectedComment();
	}
	
	/**
	 * Ermittelt, ob der Popup-Menü Eintrag "Details" benutzbar ist oder nicht.
	 * 
	 * @return Ist der Eintrag "Details" benutzbar?
	 */
	public String isPopupDetailsItemEnable() {
		return String.valueOf(_test.getPopupItem(4).isEnabled());
	}
	
	/**
	 * Klickt doppelt auf die 1. Zeile
	 */
	public void doubleClickOnRow0() {
		String date = HelperCalendar.dateToString(new Date().getTime());
		_test.tableDoubleClick(0);
		((TestWndMoneyList)_test).waitWindow("Details zur Einnahme vom " +
				date);
	}
}
