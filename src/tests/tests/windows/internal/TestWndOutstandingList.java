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

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;

import haushaltsbuch.datas.MoneyDetailsData;
import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.tables.models.MoneyDetailsListModel;
import haushaltsbuch.tables.models.OutstandingListModel;
import haushaltsbuch.windows.internal.WndOutstandingList;
import tests.testcase.GuiWndTest;

/**
 * Enhält die GUI-Tests für das Unterfenster
 * {@link haushaltsbuch.windows.internal.WndOutstandingList}.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class TestWndOutstandingList extends GuiWndTest {
	/**
	 * Speichert wie das Fenster geöffnet werden soll.
	 */
	public static final String MENU_PATH = "Datenbank|Schulden";
	
	/**
	 * Speichert das Fenster zum Einfügen und Ändern
	 */
	private JInternalFrameOperator _wndInsert;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndOutstandingList() throws Exception {
		// Datensatz fürs Geschäft einfügen
		DbController.getInstance().prepaireDatabase();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().section().insert("Test"));
		
		// Datensatz für Schulden einfügen
		long start = new GregorianCalendar(2016, Calendar.JANUARY, 1).getTimeInMillis();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().outstanding().insert(1, 19.99, 10,
						start, 20.00, "Dies ist ein Test"));
		
		// Fenster für Schuleden aufrufen
		testInit(WndOutstandingList.WND_TITLE, MENU_PATH, false);
	}
	
	/**
	 * Ermittelt, ob das Unterfenster angezeigt wird oder nicht.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	public boolean isWindowVisible() {
		return _wndInsert.isVisible();
	}
	
	/**
	 * Drückt im Unterfenster den Abbrechen-Button.
	 */
	public void pushWindowCancel() {
		new JButtonOperator(_wndInsert, "Abbrechen").push();
	}
	
	/**
	 * Ruft das Fenster zum Einfügen auf
	 */
	public void openWindowInsert() {
		_wndInsert = new JInternalFrameOperator(_wnd,
				"Neuen Datensatz erstellen");
	}
	
	/**
	 * Ruft das Fenster zum Ändern auf
	 */
	public void openWindowChange() {
		_wndInsert = new JInternalFrameOperator(_wnd,
				"Datensatz ändern");
	}
	
	/**
	 * Gibt die Daten des selektierten Datensatzes zurück
	 * 
	 * @return Daten des selektierten Datensatzes
	 */
	public OutstandingData getSelectedData() {
		return ((OutstandingListModel)_table.getModel()).getRowDataAt(
				_table.getSelectedRow());
	}

	/**
	 * Führt den Test aus.
	 */
	@Override
	public int runIt(Object arg0) {
		return 0;
	}

}
