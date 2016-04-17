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
public class TestWndCategoryList extends GuiWndIdNameTest {
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Testet, ob das Fenster richtig initalisiert wurde
			testInit("Kategorien", "Datenbank|Kategorien");
			
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
	 * Ruft den Test aus
	 * 
	 * @param args Kommandozeilen-Paramter
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.windows.internal.TestWndCategoryList"});
	}
}
