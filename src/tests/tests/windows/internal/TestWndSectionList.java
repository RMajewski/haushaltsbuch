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

import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import haushaltsbuch.tables.models.IdNameListModel;
import haushaltsbuch.windows.internal.WndSectionList;
import tests.testcase.GuiWndIdNameTest;

/**
 * Enthält die GUI-Tests für das Unterfenster 
 * {@link haushaltsbuch.windows.internal.WndSectionList}.
 * 
 * @author René Majewski
 */
public class TestWndSectionList extends GuiWndIdNameTest {
	/**
	 * Speichert wie das Fenster geöffnet werden soll.
	 */
	private static final String _menuPath = "Datenbank|Geschäfte";
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndSectionList() throws Exception{
		testInit(WndSectionList.WND_TITLE, _menuPath, false);
	}
	
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Testet, ob das Fenster richtig initalisiert wurde
			testInit(WndSectionList.WND_TITLE, _menuPath, true);
			
			// Spaltenüberschriften
			test("Ist der Name der Spalte 0 'ID'?",
					_table.getColumnModel().getColumn(0).getHeaderValue().equals("ID"));

			test("Ist der Name der Spalte 1 'Geschäft'?", 
					_table.getColumnModel().getColumn(1).getHeaderValue().equals("Geschäft"));

			// Popup-Menü testen
			testPopupMenu(3);
			
			// Auf Eintrag "Neu" klicken
			String name = "Geschäft erstellen";
			newCancel(name);
			newOkWithoutName(name);
			newOkWithName(name);
			
			// Auf Eintrag "Ändern" klicken
			name = "Geschäft ändern";
			changeCancel(name);
			changeOkWithoutName(name);
			changeOkWithName(name);
			changeOkWithSameName(name);
			
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
	 * Ermittelt den Namen des Geschäftes aus der selektierten Zeile.
	 * 
	 * @return Name des Geschäftes aus der selektierten Zeile
	 */
	public String getTableSelectName() {
		return ((IdNameListModel)_table.getModel()).getRowDataAt(
				_table.getSelectedRow()).getName();
	}
	
	/**
	 * Ermittelt den Eintrag des Text-Feldes im Dialog.
	 * 
	 * @return Eintrag des Text-Felden im Dialog.
	 */
	public String getDialogTextFieldText() {
		return new JTextFieldOperator(_dlg).getText();
	}

	/**
	 * Ruft den Test aus
	 * 
	 * @param args Kommandozeilen-Paramter
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.windows.internal.TestWndSectionList"});
	}
}
