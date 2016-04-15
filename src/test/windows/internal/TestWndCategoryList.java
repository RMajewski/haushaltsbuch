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

package test.windows.internal;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.Operator;

import db.DbController;
import test.GuiTest;
import test.GuiTestException;
import windows.WndMain;

/**
 * Testet das Unterfenster {@link windows.internal.WndCategoryList}.
 * 
 * @author René Majewski
 */
public class TestWndCategoryList extends GuiTest {
	/**
	 * Speichert das Popup-Menü
	 */
	private JPopupMenuOperator _popup;
	
	/**
	 * Testet, ob die Menü-Einträge "Ändern" und "Löschen" richtig gesetzt
	 * sind.
	 * 
	 * @param enabled <b>true</b>, wenn die Einträge benutzbar sein sollen.
	 * <b>false</b>, wenn die Einträge nicht benutzbar sein sollen.
	 * 
	 * @throws GuiTestException
	 */
	private void checkPopupItemEnabled(boolean enabled) 
			throws GuiTestException {
		// Ändern
		test(null, _popup.getComponent(1).isEnabled() == enabled);
		
		// Löschen
		test(null, _popup.getComponent(2).isEnabled() != enabled);
	}

	@Override
	public int runIt(Object arg0) {
		try {
			// Datenbank vorbereiten
			DbController.getInstance().prepaireDatabase();
			
			// Start des Haupt-Programms
			new ClassReference("Main").startApplication();
			
			// Fenster des Hauptprogrammes
			JFrameOperator wnd = new JFrameOperator(WndMain.TITLE);
			
			// MainMenu-Bar laden und Datenbank -> "Kategorien" aufrufen
			JMenuBarOperator menu = new JMenuBarOperator(wnd);
			menu.pushMenu("Datenbank|Kategorien");

			// Unterfenster abfangen
			JInternalFrameOperator internal = new JInternalFrameOperator(wnd, "Kategorien");
			
			// Übeprüfen ob es eine Tabelle besitz
			JTableOperator table = new JTableOperator(internal);
			test("Wurde eine Tabelle eingefügt?", table.isEnabled());
			
			// Spaltenüberschriften
			test("Ist der Name der Spalte 0 'ID'?",
					table.getColumnModel().getColumn(0).getHeaderValue().equals("ID"));
			
			test("Ist der Name der Spalte 1 'Kategorie'?", 
					table.getColumnModel().getColumn(1).getHeaderValue().equals("Kategorie"));
			
			// Popup-Menü öffnen
			table.clickMouse(1, Operator.getPopupMouseButton());			
			_popup = new JPopupMenuOperator();
			_popup.getTimeouts().setTimeout("JPopupMenuOperator.PushMenuTimeout", 600000);
			
			
			test("Hat das Popup-Menü 3 Einträge?", 
					_popup.getComponentCount() == 3);
			
			// 1. Eintrag "Neu" und ist benutzbar?
			test("1. Popup-Menü-Eintrag 'Neu'?", 
					((JMenuItem)_popup.getComponent(0)).getText().equals("Neu"));
			
			test("Ist 'Neu' anklickbar?", _popup.getComponent(0).isEnabled());

			// 2. Eintrag "Ändern" und 3. Eintrag "Löschen"?
			test("2. Popup-Menü-Eintrag 'Ändern'",
					((JMenuItem)_popup.getComponent(1)).getText().equals("Ändern"));
			
			test("3. Popup-Menü-Eintrag 'Löschen'",
					((JMenuItem)_popup.getComponent(2)).getText().equals("Löschen"));
			
			// Überprüfen ob "Löschen" und "Ändern" nicht anklickbar sind
			checkPopupItemEnabled(false);
			
			// Zeile makieren
			table.clickOnCell(0, 0);
			
			// Überprüfen ob "Löschen" und "Ändern" benutzbar sind
			checkPopupItemEnabled(true);
			
			// Auf neuen Eintrag klicken
			table.clickMouse(1, Operator.getPopupMouseButton());			
			_popup.pushMenuNoBlock("Neu");
			
			// Dialog abfangen
			JDialogOperator dlg = new JDialogOperator(wnd, "Kategorie erstellen");
			
			// Eingabe-Fehld in Dialog vorhanden?
			// FIXME "Neu", "Ändern" und Löschen jeweils in einer neuen Test-Klasse
			
		} catch (Exception e) {
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	/**
	 * Ruft den Test aus
	 * 
	 * @param args Kommandozeilen-Paramter
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.windows.internal.TestWndCategoryList"});
	}

}
