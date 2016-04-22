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

import javax.swing.JMenuItem;

import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.Operator;

import haushaltsbuch.db.DbController;
import haushaltsbuch.tables.models.MoneyListModel;
import tests.exception.GuiTestException;

/**
 * Enhält die GUI-Tests für das Unterfenster
 * {@link windows.internal.WndMoneyList}.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestWndMoneyList extends GuiWndTest {
	/**
	 * Testet, ob die Menü-Einträge "Ändern", "Löschen" und "Details" richtig
	 * gesetzt sind.
	 * 
	 * @param enabled <b>true</b>, wenn die Einträge benutzbar sein sollen.
	 * <b>false</b>, wenn die Einträge nicht benutzbar sein sollen.
	 * 
	 * @throws GuiTestException
	 */
	@Override
	protected void checkPopupItemEnabled(boolean enabled) 
			throws GuiTestException {
		// Methode in der Vater-Klasse aufrufen
		super.checkPopupItemEnabled(enabled);
		
		// Details
		test(null, _popup.getComponent(4).isEnabled() == enabled);
	}
	
	/**
	 * Testet, ob das Popup-Menü die Einträge "Neu", "Ändern", "Löschen" und
	 * "Details" beinhaltet. Der Eintrag "Neu" soll von Anfang an benutzbar
	 * sein. Da noch keine Zelle Selektiert ist, sollten die Eintäge "Ändern",
	 * "Löschen" und "Details" noch nicht benutzbar sein.
	 * 
	 * Als nächster Schritt wird eine Zeile in der Tabelle markiert und danach
	 * geprüft, ob die Einträge "Ändern", "Löschen" und "Details" benutzbar
	 * sind.
	 * 
	 * @throws GuiTestException
	 */
	protected void testPopupMenu() throws GuiTestException {
		// Methode in der Vater-Klasse aufrufen
		testPopupMenu(5);

		// 5. Eintrag "Details"
		test("5. Popup-Menü-Eintrag 'Details'",
				((JMenuItem)_popup.getComponent(4)).getText().equals("Details"));
	}

	/**
	 * Führt den Test aus.
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Testet, ob das Fenster richtig initalisiert wurde
			testInit("Einnahmen und Ausgaben", "Datenbank|Einnahmen und Ausgaben");
			
			// Datensatz einfügen und Daten aktualisieren in der Tabelle
			DbController.getInstance().createStatement().executeUpdate(
					DbController.queries().money().insert(10l, false, "Dies ist ein Test"));
			((MoneyListModel)_table.getModel()).dataRefresh(true);
			
			// Spaltenüberschriften
			test("Ist der Name der Spalte 0 'ID'?",
					_table.getColumnModel().getColumn(0).getHeaderValue().equals("ID"));
			test("Ist der Name der Spalte 1 'Datum'?",
					_table.getColumnModel().getColumn(1).getHeaderValue().equals("Datum"));
			test("Ist der Name der Spalte 2 'Einnahme oder Ausgabe'?",
					_table.getColumnModel().getColumn(2).getHeaderValue().equals("Was?"));
			test("Ist der Name der Spalte 3 'Ausgaben'?",
					_table.getColumnModel().getColumn(3).getHeaderValue().equals("Gesamt Betrag"));
			test("Ist der Name der Spalte 4 'Beschreibung'?",
					_table.getColumnModel().getColumn(4).getHeaderValue().equals("Beschreibung"));

			Thread.currentThread();
			// Popup-Menü testen
			testPopupMenu();
			
			/*
			 *  FIXME Nur überprüfen, ob das das Fenster aufgerufen wird, da
			 *  die Fenster seperatat getestet werden
			 */
			
			
			// Auf Eintrag "Neu" klicken
			insertWindow();
			
			// Auf Eintrag "Ändern" klicken
			changeWindow();
			
			// Details anzeigen
			detailsWindow();
		
			// Auf Eintrag "Löschen" klicken
			deleteNo();
			deleteYes();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	/**
	 * Überprüft, ob das Details-Fenster angezeigt wird.
	 * 
	 * @throws GuiTestException
	 */
	private void detailsWindow() throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.selectCell(0, 0);
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		int row = _table.getRowCount();
		
		// Fenster abfangen
		JInternalFrameOperator sub = new JInternalFrameOperator(_wnd, "Datensätz ändern");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(sub, 1);
		btn.push();
		
		test("Neu->Abbrechen sollte sich in der Tabelle nichts geändert haben",
				row == _table.getRowCount());
	}

	/**
	 * Überprüft, ob das Ändern-Fenster angezeigt wird.
	 * @throws GuiTestException
	 */
	private void changeWindow() throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.selectCell(0, 0);
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		int row = _table.getRowCount();
		
		// Fenster abfangen
		JInternalFrameOperator sub = new JInternalFrameOperator(_wnd, "Datensätz ändern");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(sub, 1);
		btn.push();
		
		test("Neu->Abbrechen sollte sich in der Tabelle nichts geändert haben",
				row == _table.getRowCount());
	}

	/**
	 * Ruft den Dialog für "Neu" auf, und bricht ihn mit Cancel ab. Danach
	 * sollte in der Datenbank sich nichts geändert haben.
	 * 
	 * @throws GuiTestException
	 */
	private void insertWindow() throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getRowCount();
		
		// Fenster abfangen
		JInternalFrameOperator sub = new JInternalFrameOperator(_wnd, "Neuen Datensatz erstellen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(sub, 1);
		btn.push();
		
		test("Neu->Abbrechen sollte sich in der Tabelle nichts geändert haben",
				row == _table.getRowCount());
	}

	/**
	 * Ruft den Test aus
	 * 
	 * @param args Kommandozeilen-Paramter
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.windows.internal.TestWndMoneyList"});
	}
}
