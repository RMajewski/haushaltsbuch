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

package tests.tests.elements;

import java.util.Date;

import javax.swing.JButton;

import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;

import haushaltsbuch.db.DbController;
import haushaltsbuch.helper.HelperCalendar;
import haushaltsbuch.windows.internal.WndMoneyList;

/**
 * Implementiert das Testprogramm, um jemmy- und Fit-Tests auszufügen, um die
 * {@link haushaltsbuch.elementes.ToolBarMain} zu testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarMainFitMoneyDetails extends TestToolBarMainFit {
	/**
	 * Speichert das Details-Fenster
	 */
	private JInternalFrameOperator _details;

	/**
	 * Den Test initalisieren
	 * @throws Exception
	 */
	public TestToolBarMainFitMoneyDetails() throws Exception {
		super();
		
		// Datensatz in der Tabelle "money" erstellen
		long date = new Date().getTime();
		DbController dbc = DbController.getInstance();
		dbc.createStatement().executeUpdate(
				DbController.queries().money().insert(date, true,
						"Dies ist ein Test"));
		
		// Datensatz in der Tabelle "money_details" erstellen
		dbc.createStatement().executeUpdate(
				DbController.queries().moneyDetails().insert(1, 1, 1, 78.99,
						"Dies ist ein Test"));
		
		// Datenbank-Fenster aufrufen
		pushMenu("Datenbank|Einnahmen und Ausgaben");
		openDatabaseWindow(WndMoneyList.WND_TITLE);
		
		// Datensatz selektiert und doppelt drauf klicken
		_table.selectCell(0, 0);
		_table.clickMouse(0, 0, 2);
		
		// Das Fenster abfangen
		_details = new JInternalFrameOperator(_wnd, "Details zur Einnahme vom " +
				HelperCalendar.dateToString(date));
		_table = new JTableOperator(_details);
	}
	
	/**
	 * Drückt auf das Element "Einfügen"
	 */
	@Override
	public void pushInsert() {
		new JButtonOperator((JButton)_toolbar.getComponent(0)).push();
		_frame = new JInternalFrameOperator(_wnd,
				"Datensatz erstellen");
	}
	
	/**
	 * Druck auf das Element "Ändern"
	 */
	@Override
	public void pushChange() {
		new JButtonOperator((JButton)_toolbar.getComponent(1)).push();
		_frame = new JInternalFrameOperator(_wnd,
				"Datensatz ändern");
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Parameter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] 
				{"tests.tests.elements.TestToolBarMainFitMoneyDetails"});
	}

}
