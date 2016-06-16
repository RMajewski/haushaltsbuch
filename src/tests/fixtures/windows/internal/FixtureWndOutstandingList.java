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

import org.testsuite.helper.HelperCalendar;
import tests.fixtures.FixtureWndTables;
import tests.tests.windows.internal.TestWndOutstandingList;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndOutstandingList und dem Test-Programm TestWndOutstandingList dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class FixtureWndOutstandingList extends FixtureWndTables {
	/**
	 * Initialisiert die Klasse
	 */
	public FixtureWndOutstandingList() throws Exception {
		_test = new TestWndOutstandingList();
	}
	
	/**
	 * Öffnet das Einfügen-Fenster
	 */
	public void pushPopupInsert() {
		_test.pushPopup("Neu");
		((TestWndOutstandingList)_test).openWindowInsert();
	}
	
	/**
	 * Ermittelt ob das Unterfenster angezeigt wird oder nicht.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	@Override
	public String isWindowVisible() {
		return String.valueOf(
				((TestWndOutstandingList)_test).isWindowVisible());
	}
	
	/**
	 * Drückt im Unterfenster den Abbrechen-Button.
	 */
	public void pushWindowCancel() {
		((TestWndOutstandingList)_test).pushWindowCancel();
	}
	
	/**
	 * Öffnet das Ändern-Fenster
	 */
	public void pushPopupChange() {
		_test.pushPopup("Ändern");
		((TestWndOutstandingList)_test).openWindowChange();
	}
	
	/**
	 * Ermittelt die ID der selektierten Tabellen-Zeile.
	 */
	public int getSelectedId() {
		return ((TestWndOutstandingList)_test).getSelectedData().getId();
	}
	
	/**
	 * Ermittelt die ID des Geschäftes selektierten Tabellen-Zeile.
	 */
	public int getSelectedSectionId() {
		return ((TestWndOutstandingList)_test).getSelectedData().getSectionId();
	}
	
	/**
	 * Ermittelt die Schulden des selektierten Tabellen-Zeile.
	 */
	public double getSelectedMoney() {
		return ((TestWndOutstandingList)_test).getSelectedData().getMoney();
	}
	
	/**
	 * Ermittelt die Anzahl der monatlichen Raten des Geschäftes selektierten
	 * Tabellen-Zeile.
	 */
	public int getSelectedMonths() {
		return ((TestWndOutstandingList)_test).getSelectedData().getMonths();
	}
	
	/**
	 * Ermittelt das Datum der 1. Rate selektierten Tabellen-Zeile.
	 */
	public String getSelectedStartDate() {
		return HelperCalendar.dateToString(
				((TestWndOutstandingList)_test).getSelectedData().getStart());
	}
	
	/**
	 * Ermittelt die Höhe der monatlichen Raten des Geschäftes selektierten
	 * Tabellen-Zeile.
	 */
	public double getSelectedMonthMoney() {
		return ((TestWndOutstandingList)_test).getSelectedData().getMonthMoney();
	}
	
	/**
	 * Ermittelt die Beschreibung des Geschäftes selektierten Tabellen-Zeile.
	 */
	public String getSelectedComment() {
		return ((TestWndOutstandingList)_test).getSelectedData().getComment();
	}

}
