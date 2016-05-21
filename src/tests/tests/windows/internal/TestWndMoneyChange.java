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

import java.util.Date;

import javax.swing.JFormattedTextField;

import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JRadioButtonOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import haushaltsbuch.db.DbController;
import haushaltsbuch.windows.internal.WndMoneyList;
import tests.testcase.GuiWndTest;

/**
 * Implementiert das Test-Programm für das Fenster WndMoneyChange.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestWndMoneyChange extends GuiWndTest {
	
	/**
	 * Speichert das Fenster, das getestet werden soll.
	 */
	private JInternalFrameOperator _wndChange;
	
	/**
	 * Speichert das Text-Feld für das Datum
	 */
	private JTextFieldOperator _date;
	
	/**
	 * Speichert den RadioButton "Einnahme"
	 */
	private JRadioButtonOperator _in;
	
	/**
	 * Speichert den RadioButton "Ausgabe"
	 */
	private JRadioButtonOperator _out;
	
	/**
	 * Speichert den RadioButton "Ausgabe"
	 */
	private JTextAreaOperator _comment;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndMoneyChange() throws Exception {
		// Datensatz für Einnahmen und Ausgaben einfügen
		DbController.getInstance().prepaireDatabase();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().money().insert(new Date().getTime(), 
						true, "Dies ist ein Test"));
		
		// Fenster für Einnahmen und Ausgaben aufrufen
		testInit(WndMoneyList.WND_TITLE, TestWndMoneyList.MENU_PATH, false);
		
		// Fenster fürs Einfügen aufrufen
		callPopup();
		pushPopup("Neu");
		_wndChange = new JInternalFrameOperator(_wnd, "Datensatz erstellen");
		
		// Komponenten ermitteln
		_date = new JTextFieldOperator(_wndChange);
		_in = new JRadioButtonOperator(_wndChange, "Einnahme");
		_out = new JRadioButtonOperator(_wndChange, "Ausgabe");
		_comment = new JTextAreaOperator(_wndChange);
	}
	
	/**
	 * Ermittelt ob das Eingabe-Feld für das Datum vorhanden ist und angezeigt
	 * wird.
	 * 
	 * @return Existiert das Eingabe-Feld für das Datum?
	 */
	public boolean existsDateTextField() {
		return _date.isVisible();
	}
	
	/**
	 * Ermittelt ob der Radio-Button "Einnahme" vorhanden ist und angezeigt
	 * wird.
	 * 
	 * @return Existiert der Radio-Button "Einnahme"?
	 */
	public boolean existsIncomingRadioButton() {
		return _in.isVisible();
	}
	
	/**
	 * Ermittelt ob der Radio-Button "Ausgabe" vorhanden ist und angezeigt
	 * wird.
	 * 
	 * @return Existiert der Radio-Button "Ausgabe"?
	 */
	public boolean existsOutgoingRadioButton() {
		return _out.isVisible();
	}

	@Override
	public int runIt(Object arg0) {
		return 0;
	}
	
	/**
	 * Ermittelt ob der Eingabebereich den Kommentar vorhanden ist und angezeigt
	 * wird.
	 * 
	 * @return Existiert das Eingabe-Feld für das Datum?
	 */
	public boolean existsCommentTextArea() {
		return _comment.isVisible();
	}
	
	/**
	 * Ermittelt, ob der Button zum Abrechen vorhanden ist und angezeigt wird.
	 * 
	 * @return Existiert der Button "Abbrechen"?
	 */
	public boolean existsCancelButton() {
		return new JButtonOperator(_wndChange, "Abbrechen").isVisible();
	}
	
	/**
	 * Ermittelt, ob der Button zum Speichern vorhanden ist und angezeigt wird.
	 * 
	 * @return Existiert der Button "Speichern"?
	 */
	public boolean existsSaveButton() {
		return new JButtonOperator(_wndChange, "Speichern").isVisible();
	}
	
	/**
	 * Der Text im Datums-Feld wird neu gesetzt.
	 * 
	 * @param str Text, der in das Datums-Feld geschrieben werden soll.
	 */
	public void setDateText(String str) {
		_date.setText(str);
	}
	
	/**
	 * Der Text im Kommentar-bereich wird neu gesetzt.
	 * 
	 * @param str Text, der in den Kommentar-Bereich geschrieben werden soll.
	 */
	public void setCommentText(String str) {
		_comment.setText(str);
	}
	
	/**
	 * Klickt auf Speichern, aber so dass ein Dialog abgefangen werden kann.
	 */
	public void pushNoBlockSave() {
		new JButtonOperator(_wndChange, "Speichern").pushNoBlock();
	}
	
	/**
	 * Klickt auf Speichern.
	 */
	public void pushSave() {
		new JButtonOperator(_wndChange, "Speichern").push();
	}

	/**
	 * Klickt auf Abbrechen
	 */
	public void pushCancel() {
		new JButtonOperator(_wndChange, "Abbrechen").push();
	}
	
	/**
	 * Den Fokus bekommt der Radio-Button "Einnahme"
	 */
	public void setFocusToIn() {
		_in.requestFocus();
		_in.waitHasFocus();
	}
	
	/**
	 * Den Fokus bekommt der Radio-Button "Einnahme"
	 */
	public void setFocusToOut() {
		_out.requestFocus();
		_out.waitHasFocus();
	}
	
	/**
	 * Ermittelt den Text vom Datums-Feld
	 * 
	 * @return Text vom Datums-Feld
	 */
	public String getTextFromDate() {
		return String.valueOf(((JFormattedTextField)_date.getSource()).getText());
	}
	
	/**
	 * Ermittelt, ob das Fenster angezeigt wird oder nicht.
	 * 
	 * @return Wird das Fenster angezeigt?
	 */
	public boolean isWindowVisible() {
		return _wndChange.isVisible();
	}

	/**
	 * Ruft das Testprogramm auf
	 * 
	 * @param args Parameter aus der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.windows.internal.TestWndMoneyChange"});
	}

}
