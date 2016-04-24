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

package tests.testcase;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.Operator;

import haushaltsbuch.datas.IdNameData;
import haushaltsbuch.tables.models.IdNameListModel;
import tests.exception.GuiTestException;

/**
 * Von dieser Klasse werden die Test-Klassen abgeleitet, die die Unterfenster
 * WndCategoryList und WndSectionList testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public abstract class GuiWndIdNameTest extends GuiWndTest {

	/**
	 * Ruft den Dialog für "Neu" auf, und bricht ihn mit Cancel ab. Danach
	 * sollte in der Datenbank sich nichts geändert haben.
	 * 
	 * @param name Name des Dialogs
	 * 
	 * @throws GuiTestException
	 */
	protected void newCancel(String name) throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
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
	protected void newOkWithoutName(String name) throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
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
	protected void newOkWithName(String name) throws GuiTestException {
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Neu");
		
		int row = _table.getModel().getRowCount();
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
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
	protected void changeCancel(String name) throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
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
	protected void changeOkWithoutName(String name) throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
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
	protected void changeOkWithSameName(String name) throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
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
	protected void changeOkWithName(String name) throws GuiTestException {
		// Zeile auswählen
		_table.selectCell(0, 0);
		
		// Daten sichern
		IdNameData data = ((IdNameListModel)_table.getModel()).getRowDataAt(0);
		
		// Popup-Menü "Neu" drücken
		_table.clickMouse(1, Operator.getPopupMouseButton());			
		_popup.pushMenuNoBlock("Ändern");
		
		// Dialog abfangen
		JDialogOperator dlg = new JDialogOperator(_wnd, name);
		
		// Eingabe-Feld
		JTextFieldOperator txt = new JTextFieldOperator(dlg);
		txt.setText("Testen");
		
		// Abbrechen-Button
		JButtonOperator btn = new JButtonOperator(dlg, "OK");
		btn.push();
		
		test("Ändern->Ok sollte sich die Kategorie im 1. Datensatz geändert haben",
				!data.getName().equals(((IdNameListModel)_table.getModel()).getRowDataAt(0)));
	}
}
