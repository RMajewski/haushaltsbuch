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

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JMenuItem;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
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
 * Von dieser Klasse werden die Test-Klassen abgeleitet, die die Unterfenster
 * mit Tabellen testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public abstract class GuiWndTest extends GuiTest {
	/**
	 * Speichert das Popup-Menü
	 */
	protected JPopupMenuOperator _popup;
	
	/**
	 * Speichert die Tabelle
	 */
	protected JTableOperator _table;
	
	/**
	 * Speichert das Hauptfenster
	 */
	protected JFrameOperator _wnd;
	
	/**
	 * Speichert das Unterfenster
	 */
	protected JInternalFrameOperator _frame;
	
	/**
	 * Testet, ob die Menü-Einträge "Ändern" und "Löschen" richtig gesetzt
	 * sind.
	 * 
	 * @param enabled <b>true</b>, wenn die Einträge benutzbar sein sollen.
	 * <b>false</b>, wenn die Einträge nicht benutzbar sein sollen.
	 * 
	 * @throws GuiTestException
	 */
	protected void checkPopupItemEnabled(boolean enabled) 
			throws GuiTestException {
		// Ändern
		test(null, _popup.getComponent(1).isEnabled() == enabled);
		
		// Löschen
		test(null, _popup.getComponent(2).isEnabled() == enabled);
	}
	
	/**
	 * Startet das Programm und fängt das Hauptfenster ab. Danach wird
	 * das Fenster gestartet, welches angeben ist. Es wird überprüft, ob es
	 * eine Tabelle enthält und diese aktiviert ist.
	 * 
	 * @param name Name des Fensters, welches gestartet werden soll
	 * 
	 * @param menu Name des Menü-Eintrages, welches das Fenster startet.
	 * 
	 * @throws GuiTestException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 */
	protected void testInit(String name, String menu) 
			throws GuiTestException, SQLException, InvocationTargetException,
			NoSuchMethodException, ClassNotFoundException {
		// Datenbank vorbereiten
		DbController.getInstance().prepaireDatabase();
		
		// Start des Haupt-Programms
		new ClassReference("Main").startApplication();
		
		// Fenster des Hauptprogrammes
		_wnd = new JFrameOperator(WndMain.TITLE);
		
		// MainMenu-Bar laden und angegebenen Menü-Eintrag aufrufen
		JMenuBarOperator m = new JMenuBarOperator(_wnd);
		m.pushMenu(menu);

		// Unterfenster abfangen
		_frame = new JInternalFrameOperator(_wnd, name);
		
		// Übeprüfen ob es eine Tabelle besitz
		_table = new JTableOperator(_frame);
		test("Wurde eine Tabelle eingefügt?", _table.isEnabled());
	}
	
	/**
	 * Testet, ob das Popup-Menü die Einträge "Neu", "Ändern" und "Löschen"
	 * beinhaltet. Der Eintrag "Neu" soll von Anfang an benutzbar sein. Da noch
	 * keine Zelle Selektiert ist, sollten die Eintäge "Ändern" und "Löschen"
	 * noch nicht benutzbar sein.
	 * 
	 * Als nächster Schritt wird eine Zeile in der Tabelle markiert und danach
	 * geprüft, ob die Einträge "Ändern" und "Löschen" benutzbar sind.
	 * 
	 * @throws GuiTestException
	 */
	protected void testPopupMenu(int itemCount) throws GuiTestException {
		// Popup-Menü öffnen
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup = new JPopupMenuOperator();
		
		test("Hat das Popup-Menü " + itemCount + " Einträge?", 
				_popup.getComponentCount() == itemCount);
		
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
		_table.clickOnCell(0, 0);
		
		// Überprüfen ob "Löschen" und "Ändern" benutzbar sind
		checkPopupItemEnabled(true);
	}

	/**
	 * Ruft den Dialog für "Löschen" auf, und bricht ihn mit Nein ab. Danach
	 * sollte in der Datenbank sich nichts geändert haben.
	 * 
	 * @throws GuiTestException
	 */
	protected void deleteNo() throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Löschen");
		
		int row = _table.getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Datensatz löschen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "Nein");
		btn.push();
		
		test("Löschen->Nein sollte sich in der Tabelle nichts geändert haben",
				row == _table.getRowCount());
	}

	/**
	 * Ruft den Dialog für "Löschen" auf, und drückt auf Ja. Nun sollte es einen
	 * Datensatz weniger geben.
	 * 
	 * @throws GuiTestException
	 */
	protected void deleteYes() throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Löschen");
		
		int row = _table.getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Datensatz löschen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "Ja");
		btn.push();
		
		test("Löschen->Ja sollte es ein Datensatz weniger geben",
				(row -1) == _table.getRowCount());
		
		// Keine Selektion in der Tabelle
		checkPopupItemEnabled(false);
	}
}
