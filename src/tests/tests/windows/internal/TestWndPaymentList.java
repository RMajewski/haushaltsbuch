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
import haushaltsbuch.windows.internal.WndPaymentList;
import tests.testcase.GuiWndIdNameTest;

/**
 * Enthält die GUI-Tests für das Unterfenster 
 * {@link haushaltsbuch.windows.internal.WndPaymentList}.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @serial 0.4
 */
public class TestWndPaymentList extends GuiWndIdNameTest {
	/**
	 * Speichert wie das Fenster geöffnet werden soll.
	 */
	private static final String _menuPath = "Datenbank|Zahlungsmethoden";
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndPaymentList() throws Exception{
		testInit(WndPaymentList.WND_TITLE, _menuPath, false);
	}
	
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
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
