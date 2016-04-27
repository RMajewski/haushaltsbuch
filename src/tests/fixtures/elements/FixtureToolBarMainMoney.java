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

package tests.fixtures.elements;

import java.sql.SQLException;
import java.util.Date;

import haushaltsbuch.db.DbController;
import haushaltsbuch.windows.internal.WndMoneyList;
import haushaltsbuch.windows.internal.WndSectionList;
import tests.tests.elements.TestToolBarMainFitMoney;

/**
 * Implementiert die Schnittstelle zwischen den Fit-Tests für die
 * {@link haushaltsbuch.elementes.ToolBarMain} und dem Testprogramm
 * {@link tests.tests.elements.TestToolbarMain}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureToolBarMainMoney extends FixtureToolBarMain {

	/**
	 * Initalisiert die Tests
	 * @throws Exception
	 */
	public FixtureToolBarMainMoney() throws Exception {
		_test = new TestToolBarMainFitMoney();
	}
	
	/**
	 * Drückt auf den Menü-Eintrag Datenbank -> Geschäft
	 */
	public void pushDatabaseMoney() {
		_test.pushMenu("Datenbank|Einnahmen und Ausgaben");
		_test.openDatabaseWindow(WndMoneyList.WND_TITLE);
	}
	
	/**
	 * Fügt einen Test-Datensatz in die Tabelle "money" ein.
	 */
	public void insertNewDatarecordMoney() throws SQLException {
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().money().insert(new Date().getTime(), 
						true, "Dies ist ein Test"));
	}

}
