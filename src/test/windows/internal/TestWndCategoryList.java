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
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.Operator;

import datas.IdNameData;
import db.DbController;
import tables.models.IdNameListModel;
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
	 * Speichert die Tabelle
	 */
	private JTableOperator _table;
	
	/**
	 * Speichert das Hauptfenster
	 */
	private JFrameOperator _wnd;
	
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
		test(null, _popup.getComponent(2).isEnabled() == enabled);
	}

	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Datenbank vorbereiten
			DbController.getInstance().prepaireDatabase();
			
			// Start des Haupt-Programms
			new ClassReference("Main").startApplication();
			
			// Fenster des Hauptprogrammes
			_wnd = new JFrameOperator(WndMain.TITLE);
			
			// MainMenu-Bar laden und Datenbank -> "Kategorien" aufrufen
			JMenuBarOperator menu = new JMenuBarOperator(_wnd);
			menu.pushMenu("Datenbank|Kategorien");

			// Unterfenster abfangen
			JInternalFrameOperator internal = new JInternalFrameOperator(_wnd, "Kategorien");
			
			// Übeprüfen ob es eine Tabelle besitz
			_table = new JTableOperator(internal);
			test("Wurde eine Tabelle eingefügt?", _table.isEnabled());
			
			// Spaltenüberschriften
			test("Ist der Name der Spalte 0 'ID'?",
					_table.getColumnModel().getColumn(0).getHeaderValue().equals("ID"));
			
			test("Ist der Name der Spalte 1 'Kategorie'?", 
					_table.getColumnModel().getColumn(1).getHeaderValue().equals("Kategorie"));
			
			// Popup-Menü öffnen
			_table.clickMouse(1, Operator.getPopupMouseButton());			
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
			_table.clickOnCell(0, 0);
			
			// Überprüfen ob "Löschen" und "Ändern" benutzbar sind
			checkPopupItemEnabled(true);
			
			// Auf Eintrag "Neu" klicken
			_newCancel();
			_newOkWithoutName();
			_newOkWithName();
			
			// Auf Eintrag "Ändern" klicken
			_changeCancel();
			_changeOkWithoutName();
			_changeOkWithName();
			_changeOkWithSameName();
			
			// Auf Eintrag "Löschen" klicken
			_deleteNo();
			_deleteYes();
		} catch (Exception e) {
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	/**
	 * Ruft den Dialog für "Neu" auf, und bricht ihn mit Cancel ab. Danach
	 * sollte in der Datenbank sich nichts geändert haben.
	 * 
	 * @throws GuiTestException
	 */
	private void _newCancel() throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie erstellen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "Abbrechen");
		btn.push();
		
		test("Neu->Abbrechen sollte sich in der Tabelle nichts geändert haben",
				row == _table.getRowCount());
	}
	
	/**
	 * Ruft den Dialog für "Neu" auf und Drückt den Ok-Button ohne etwas
	 * in den Namen zu schreiben. Es sollte sich nichts in der Datenbank
	 * verändert haben.
	 * 
	 * @throws GuiTestException
	 */
	private void _newOkWithoutName() throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie erstellen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "OK");
		btn.push();
		
		test("Neu->Ok (ohne Name einzugeben) sollte sich in der Tabelle nichts geändert haben",
				row == _table.getRowCount());
	}
	
	/**
	 * Ruft den Dialog für "Neu" auf. Dann wird ein Name eingegeben und der
	 * OK-Button gedrückt. Nun sollte in der Datenbank ein neuer Eintrag
	 * vorhanden sein
	 * 
	 * @throws GuiTestException
	 */
	private void _newOkWithName() throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getModel().getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie erstellen");
		
		// Eingabe-Feld
		JTextFieldOperator txt = new JTextFieldOperator(dlg);
		txt.setText("Test");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "OK");
		btn.push();
		
		test("Neu->Ok sollte sich in der Tabelle es einen Datensatz mehr geben",
				(row + 1) == _table.getRowCount());
	}

	/**
	 * Ruft den Dialog für "Ändern" auf, und bricht ihn mit Cancel ab. Danach
	 * sollte in der Datenbank sich nichts geändert haben.
	 * 
	 * @throws GuiTestException
	 */
	private void _changeCancel() throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie ändern");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "Abbrechen");
		btn.push();
		
		test("Ändern->Abbrechen sollte sich in der Tabelle nichts geändert haben",
				data.getName().equals(((IdNameListModel)_table.getModel()).getRowDataAt(0).getName()));
	}
	
	/**
	 * Ruft den Dialog für "Ändern" auf und Drückt den Ok-Button ohne etwas
	 * in den Namen zu schreiben. Es sollte sich nichts in der Datenbank
	 * verändert haben.
	 * 
	 * @throws GuiTestException
	 */
	private void _changeOkWithoutName() throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie ändern");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "OK");
		btn.push();
		
		test("Ändern->Ok (ohne Name einzugeben) sollte sich in der Tabelle nichts geändert haben",
				data.getName().equals(((IdNameListModel)_table.getModel()).getRowDataAt(0).getName()));
	}
	
	/**
	 * Ruft den Dialog für "Ändern" auf. Dann wird der Name eingegeben, der
	 * vorher schon im Datensatz stand und der OK-Button gedrückt. Es sollte
	 * sich nichts verändern haben.
	 * 
	 * @throws GuiTestException
	 */
	private void _changeOkWithSameName() throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie ändern");
		
		// Eingabe-Feld
		JTextFieldOperator txt = new JTextFieldOperator(dlg);
		txt.setText(data.getName());
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "OK");
		btn.push();
		
		test("Ändern->Ok (gleicher Name) sollte sich in der Tabelle nichts geändert haben",
				data.getName().equals(((IdNameListModel)_table.getModel()).getRowDataAt(0).getName()));
	}
	
	/**
	 * Ruft den Dialog für "Ändern" auf. Dann wird ein Name eingegeben und der
	 * OK-Button gedrückt. Nun sollte in der Datenbank ein neuer Eintrag
	 * vorhanden sein
	 * 
	 * @throws GuiTestException
	 */
	private void _changeOkWithName() throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, "Kategorie ändern");
		
		// Eingabe-Feld
		JTextFieldOperator txt = new JTextFieldOperator(dlg);
		txt.setText("Testen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "OK");
		btn.push();
		
		test("Ändern->Ok sollte sich die Kategorie im 1. Datensatz geändert haben",
				!data.getName().equals(((IdNameListModel)_table.getModel()).getRowDataAt(0)));
	}

	/**
	 * Ruft den Dialog für "Löschen" auf, und bricht ihn mit Nein ab. Danach
	 * sollte in der Datenbank sich nichts geändert haben.
	 * 
	 * @throws GuiTestException
	 */
	private void _deleteNo() throws GuiTestException {
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
	private void _deleteYes() throws GuiTestException {
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
