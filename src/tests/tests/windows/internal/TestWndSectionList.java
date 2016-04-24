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

import tests.testcase.GuiWndIdNameTest;

/**
 * Enthält die GUI-Tests für das Unterfenster 
 * {@link windows.internal.WndSectionList}.
 * 
 * @author René Majewski
 */
public class TestWndSectionList extends GuiWndIdNameTest {
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Testet, ob das Fenster richtig initalisiert wurde
			testInit("Geschäfte", "Datenbank|Geschäfte");
			
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
	 * Ruft den Test aus
	 * 
	 * @param args Kommandozeilen-Paramter
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.windows.internal.TestWndSectionList"});
	}
}
