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

import java.text.ParseException;
import java.util.Date;

import javax.swing.JFormattedTextField;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import haushaltsbuch.db.DbController;
import haushaltsbuch.helper.HelperCalendar;
import haushaltsbuch.windows.internal.WndMoneyList;
import tests.testcase.GuiWndTest;

/**
 * Implementiert das Test-Programm für das Fenster WndMoneyDetailsChange.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestWndMoneyDetailsChange extends GuiWndTest {
	
	/**
	 * Speichert das Fenster zum Einfügen bzw. Ändern der Details-Datensätze
	 */
	private JInternalFrameOperator _wndChange;
	
	/**
	 * Speichert das Fenster WndMoneyDetails
	 */
	private JInternalFrameOperator _wndDetails;
	
	/**
	 * Speichert die Combo-Box für die Kategorien
	 */
	private JComboBoxOperator _category;
	
	/**
	 * Speichert die Combo-Box für die Geschäfte
	 */
	private JComboBoxOperator _section;
	
	/**
	 * Speichert das Text-Feld für den Betrag
	 */
	private JTextFieldOperator _money;
	
	/**
	 * Speichert den Eingabe-Bereich für den kommentar
	 */
	private JTextAreaOperator _comment;
	
	/**
	 * Speichert das Datum
	 */
	private String _date;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndMoneyDetailsChange() throws Exception {
		// Datensatz für Einnahmen und Ausgaben einfügen
		DbController.getInstance().prepaireDatabase();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().money().insert(new Date().getTime(), 
						true, "Dies ist ein Test"));
	
		// Fenster für Einnahmen und Ausgaben aufrufen
		_date = HelperCalendar.dateToString(new Date().getTime());
		testInit(WndMoneyList.WND_TITLE, TestWndMoneyList.MENU_PATH, false);
		
		
		// Datensatz für Details einfügen
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().moneyDetails().insert(1, 1, 1, 10.89,
						"Dies ist ein Test"));
		
		// Fenster für Details aufrufen
		_table.clickMouse(0, 0, 2);
		_wndDetails = new JInternalFrameOperator(_wnd, "Details zur Einnahme "
				+ "vom " + _date);
		_table = new JTableOperator(_wndDetails);
	}
	
	/**
	 * Öffnet das Fenster zum Datensatz Einfügen.
	 */
	public void pushInsert() {
		// Fenster fürs Einfügen aufrufen
		callPopup();
		pushPopup("Neu");
		_wndChange = new JInternalFrameOperator(_wnd, "Datensatz erstellen");
		
		// Komponenten ermitteln
		_category = new JComboBoxOperator(_wndChange, 0);
		_section = new JComboBoxOperator(_wndChange, 1);
		_money = new JTextFieldOperator(_wndChange);
		_comment = new JTextAreaOperator(_wndChange);
	}
	
	/**
	 * Öffnet das Fenster zum Datensatz Ändern.
	 */
	public void pushChange() {
		// Fenster fürs Einfügen aufrufen
		callPopup();
		pushPopup("Ändern");
		_wndChange = new JInternalFrameOperator(_wnd, "Datensatz ändern");
		
		// Komponenten ermitteln
		_category = new JComboBoxOperator(_wndChange, 0);
		_section = new JComboBoxOperator(_wndChange, 1);
		_money = new JTextFieldOperator(_wndChange);
		_comment = new JTextAreaOperator(_wndChange);
	}

	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		return 0;
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
	 * Ermittelt ob die Combo-Box für die Kategorien existiert und angezeigt
	 * wird.
	 * 
	 * @return Existiert die Combo-Box für die Kategorien?
	 */
	public boolean existsCategoryComboBox() {
		return _category.isVisible();
	}
	
	/**
	 * Ermittelt ob die Combo-Box für die Geschäfte existiert und angezeigt
	 * wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsSectionComboBox() {
		return _section.isVisible();
	}
	
	/**
	 * Ermittelt ob die Text-Eingabe für den Betrag existiert und angezeigt
	 * wird.
	 * 
	 * @return Existiert die Text-Eingabe für den Betrag?
	 */
	public boolean existsMoneyTextField() {
		return _money.isVisible();
	}
	
	/**
	 * Ermittelt ob der Eingabebereich für den Kommentar existiert und angezeigt
	 * wird.
	 * 
	 * @return Existiert die Eingabebereich für den Kommentar?
	 */
	public boolean existsCommentTextArea() {
		return _comment.isVisible();
	}
	
	/**
	 * Ermittelt, ob der Button "Abbrechen" existiert und angezeigt wird.
	 * 
	 * @return Existiert der Button "Abbrechen"?
	 */
	public boolean existsCancelButton() {
		return new JButtonOperator(_wndChange, "Abbrechen").isVisible();
	}
	
	/**
	 * Ermittelt, ob der Button "Abbrechen" existiert und angezeigt wird.
	 * 
	 * @return Existiert der Button "Abbrechen"?
	 */
	public boolean existsSavelButton() {
		return new JButtonOperator(_wndChange, "Speichern").isVisible();
	}
	
	/**
	 * Setzt im Eingabefeld für den Betrag den Text neu.
	 * 
	 * @param str Neuer Text für das Eingabefeld
	 * @throws ParseException 
	 */
	public void setMoneyText(String str) throws ParseException {
		_money.setText(str);
	}
	
	/**
	 * Drückt auf den Speicher-Button.
	 */
	public void pushNoBlockSave() {
		new JButtonOperator(_wndChange, "Speichern").pushNoBlock();
	}
	
	/**
	 * Der Eingabebereich des Kommentars bekommt den Fokus
	 */
	public void setFocusToCommentTextArea() {
		_comment.requestFocus();
		_comment.waitHasFocus();
	}
	
	/**
	 * Es wird der Text vom Textfeld des Betrages ermittelt.
	 * 
	 * @return Eingegebener Betrag
	 */
	public String getTextFromMoneyTextField() {
		return _money.getText();
	}
	
	/**
	 * Es wird der Text für den Eingabebereich des Kommentars gesetzt.
	 * 
	 * @param str Neuer Kommentar
	 */
	public void setCommentText(String str) {
		_comment.setText(str);
	}
	
	/**
	 * Es wird der Speichern-Button gedrückt
	 */
	public void pushSave() {
		new JButtonOperator(_wndChange, "Speichern").push();
	}
	
	/**
	 * Es wird der Abbrechen-Button gedruckt
	 */
	public void pushCancel() {
		new JButtonOperator(_wndChange, "Abbrechen").push();
	}
	
	/**
	 * Ermittelt den Namen der ausgewählten Kategorie
	 * 
	 * @return Name der ausgewählten Kategorie
	 */
	public String getCategoryComboBoxText() {
		return _category.getSelectedItem().toString();
	}
	
	/**
	 * Ermittelt den Namen des ausgewählten Geschäfts
	 * 
	 * @return Name des ausgewählten Geschäfts
	 */
	public String getSectionComboBoxText() {
		return _section.getSelectedItem().toString();
	}

	/**
	 * Ermittelt den Text des Eingabefeldes für den Kommentar
	 * 
	 * @return Text des Eingabefeldes für den Kommentar
	 */
	public String getCommentTextAreaText() {
		return _comment.getDisplayedText();
	}
	
	/**
	 * Führt die Validierung beim Textfeld für den Betrag durch.
	 * @throws ParseException 
	 */
	public void moneyTextCommit() throws ParseException {
		((JFormattedTextField)_money.getSource()).commitEdit();
	}
}
