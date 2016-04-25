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

package tests.tests.windows.internal;

import java.util.Date;

import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;

import haushaltsbuch.datas.MoneyDetailsData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.helper.HelperCalendar;
import haushaltsbuch.tables.models.MoneyDetailsListModel;
import haushaltsbuch.windows.internal.WndMoneyDetailsList;
import haushaltsbuch.windows.internal.WndMoneyList;
import tests.testcase.GuiWndTest;

public class TestWndMoneyDetailsList extends GuiWndTest {
	/**
	 * Speichert das Fenster für die Details.
	 */
	private JInternalFrameOperator _wndDetails;
	
	/**
	 * Speichert das Fenster zu Einfügen bzw. Ändern.
	 */
	private JInternalFrameOperator _wndChange;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndMoneyDetailsList() throws Exception {
		// Datensatz für Einnahmen und Ausgaben einfügen
		DbController.getInstance().prepaireDatabase();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().money().insert(new Date().getTime(), 
						true, "Dies ist ein Test"));
		
		// Fenster für Einnahmen und Ausgaben aufrufen
		String date = HelperCalendar.dateToString(new Date().getTime());
		testInit(WndMoneyList.WND_TITLE, TestWndMoneyList.MENU_PATH, false);
		
		// Datensatz für Details einfügen
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().moneyDetails().insert(1, 1, 1, 10.89,
						"Dies ist ein Test"));
		
		// Fenster für Details aufrufen
		_table.clickMouse(0, 0, 2);
		_wndDetails = new JInternalFrameOperator(_wnd, "Details zur Einnahme "
				+ "vom " + date);
		
		// Die Tabelle ermitteln
		_table = new JTableOperator(_wndDetails);
	}
	
	/**
	 * Ruft das Fenster zum Einfügen auf
	 */
	public void openWindowInsert() {
		_wndChange = new JInternalFrameOperator(_wnd,
				"Neuen Datensatz erstellen");
	}
	
	/**
	 * Ruft das Fenster zum Ändern auf
	 */
	public void openWindowChange() {
		_wndChange = new JInternalFrameOperator(_wnd,
				"Datensatz ändern");
	}
	
	/**
	 * Ermittelt, ob das Unterfenster angezeigt wird oder nicht.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	public boolean isWindowVisible() {
		return _wndChange.isVisible();
	}
	
	/**
	 * Drückt im Unterfenster den Abbrechen-Button.
	 */
	public void pushWindowCancel() {
		new JButtonOperator(_wndChange, "Abbrechen").push();
	}
	
	/**
	 * Gibt die Daten des selektierten Datensatzes zurück
	 * 
	 * @return Daten des selektierten Datensatzes
	 */
	public MoneyDetailsData getSelectedData() {
		return ((MoneyDetailsListModel)_table.getModel()).getRowDataAt(
				_table.getSelectedRow());
	}

	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Ruft das Testprogramm auf
	 * 
	 * @param args Parameter aus der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.windows.internal.TestWndMoneyDetailsList"});
	}
}
