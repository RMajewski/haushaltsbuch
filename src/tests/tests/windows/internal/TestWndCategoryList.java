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

import haushaltsbuch.tables.models.IdNameListModel;
import haushaltsbuch.windows.internal.WndCategoryList;
import tests.testcase.GuiWndIdNameTest;

/**
 * Testet das Unterfenster {@link haushaltsbuch.windows.internal.WndCategoryList}.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestWndCategoryList extends GuiWndIdNameTest {
	/**
	 * Speichert wie das Fenster geöffnet werden soll.
	 */
	private static final String _menuPath = "Datenbank|Kategorien";
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @throws Exception 
	 */
	public TestWndCategoryList() throws Exception {
		testInit(WndCategoryList.WND_TITLE, _menuPath, false);
	}
	
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Initalisiert das Fenster
			testInit(WndCategoryList.WND_TITLE, _menuPath, true);
			
			// Spaltenüberschriften
			test("Ist der Name der Spalte 0 'ID'?",
					_table.getColumnModel().getColumn(0).getHeaderValue().equals("ID"));
			
			test("Ist der Name der Spalte 1 'Kategorie'?", 
					_table.getColumnModel().getColumn(1).getHeaderValue().equals("Kategorie"));

			// Popup-Menü testen
			testPopupMenu(3);
			
			// Auf Eintrag "Neu" klicken
			String name = "Kategorie erstellen";
			newCancel(name);
			newOkWithoutName(name);
			newOkWithName(name);
			
			// Auf Eintrag "Ändern" klicken
			name = "Kategorie ändern";
			changeCancel(name);
			changeOkWithoutName(name);
			changeOkWithName(name);
			changeOkWithSameName(name);
			
			// Auf Eintrag "Löschen" klicken
			deleteNo();
			deleteYes();
		} catch (Exception e) {
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}
	
	/**
	 * Ermittelt den Namen der Kategorie aus der selektierten Zeile.
	 * 
	 * @return Name der Kategorie aus der selektierten Zeile
	 */
	public String getTableSelectName() {
		return ((IdNameListModel)_table.getModel()).getRowDataAt(
				_table.getSelectedRow()).getName();
	}

	/**
	 * Ruft den Test aus
	 * 
	 * @param args Kommandozeilen-Paramter
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.windows.internal.TestWndCategoryList"});
	}
}
