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

import tests.fixtures.FixtureWndTables;
import tests.tests.windows.internal.TestWndMoneyDetailsList;

/**
 * Implementiert die Schnittstelle zwischen den WndMoneyDetailsList-Test und dem
 * TestWndMoneyDetailsList-Testprogramm.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndMoneyDetailsList extends FixtureWndTables {

	/**
	 * Initalisiert die Klasse
	 */
	public FixtureWndMoneyDetailsList() throws Exception {
		_test = new TestWndMoneyDetailsList();
	}
	
	/**
	 * Öffnet das Einfügen-Fenster
	 */
	public void pushPopupInsert() {
		_test.pushPopup("Neu");
		((TestWndMoneyDetailsList)_test).openWindowInsert();
	}
	
	/**
	 * Öffnet das Ändern-Fenster
	 */
	public void pushPopupChange() {
		_test.pushPopup("Ändern");
		((TestWndMoneyDetailsList)_test).openWindowChange();
	}
	
	/**
	 * Ermittelt ob das Unterfenster angezeigt wird oder nicht.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	@Override
	public String isWindowVisible() {
		return String.valueOf(
				((TestWndMoneyDetailsList)_test).isWindowVisible());
	}
	
	/**
	 * Drückt im Unterfenster den Abbrechen-Button.
	 */
	public void pushWindowCancel() {
		((TestWndMoneyDetailsList)_test).pushWindowCancel();
	}
	
	/**
	 * Ermittelt die ID der selektierten Tabellen-Zeile.
	 */
	public String getSelectedId() {
		return String.valueOf(
				((TestWndMoneyDetailsList)_test).getSelectedData().getId());
	}
	
	/**
	 * Ermittelt die Kategorie der selektierten Tabellen-Zeile.
	 */
	public String getSelectedCategory() {
		return String.valueOf(
				((TestWndMoneyDetailsList)_test).getSelectedData()
				.getCategoryId());
	}
	
	/**
	 * Ermittelt das Geschäft der selektierten Tabellen-Zeile.
	 */
	public String getSelectedSection() {
		return String.valueOf(
				((TestWndMoneyDetailsList)_test).getSelectedData()
				.getSectionId());
	}
	
	/**
	 * Ermittelt den Betrag der selektierten Tabellen-Zeile.
	 */
	public String getSelectedMoney() {
		return String.valueOf(
				((TestWndMoneyDetailsList)_test).getSelectedData()
				.getMoney());
	}
	
	/**
	 * Ermittelt den Kommentar der selektierten Tabellen-Zeile.
	 */
	public String getSelectedComment() {
		return String.valueOf(
				((TestWndMoneyDetailsList)_test).getSelectedData()
				.getComment());
	}
}
