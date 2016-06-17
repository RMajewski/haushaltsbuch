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

import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFormattedTextField;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import haushaltsbuch.db.DbController;
import haushaltsbuch.windows.internal.WndOutstandingList;
import tests.testcase.GuiWndTest;

/**
 * Implementiert das Test-Programm für das Fenster WndOutstandingChange.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class TestWndOutstandingChange extends GuiWndTest {
	
	/**
	 * Speichert das Fenster zum Einfügen bzw. Ändern der Details-Datensätze
	 */
	private JInternalFrameOperator _wndChange;
	
	/**
	 * Speichert die Combo-Box für die Geschäfte.
	 */
	private JComboBoxOperator _cbSection;
	
	/**
	 * Speichert das Text-Feld für die Höhe der Schuld
	 */
	private JTextFieldOperator _txtMoney;
	
	/**
	 * Speichert das Text-Feld für die Anzahl der monatlichen Raten
	 */
	private JTextFieldOperator _txtMonths;
	
	/**
	 * Speichert das Text-Feld für das Datum der 1. Rate
	 */
	private JTextFieldOperator _txtStart;
	
	/**
	 * Speichert das Text-Feld für die Höhe der monatlichen Raten.
	 */
	private JTextFieldOperator _txtMonthMoney;
	
	/**
	 * Speichert den Text-Bereich für den Kommentar
	 */
	private JTextAreaOperator _txtComment;
	
	/**
	 * Speichert die Tabelle für die Raten
	 */
	private JTableOperator _tableRepay;
	
	/**
	 * Speichert die Ausgabe für die restliche Schuld
	 */
	private JLabelOperator _labMoney;
	
	/**
	 * Speichert die Ausgabe für die Anzahl der restlichen Monate
	 */
	private JLabelOperator _labMonths;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestWndOutstandingChange() throws Exception {
		// Datensatz für Einnahmen und Ausgaben einfügen
		DbController.getInstance().prepaireDatabase();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().section().insert("Test"));
		long date = new GregorianCalendar(2016, Calendar.JANUARY, 1)
				.getTimeInMillis();
		DbController.getInstance().createStatement().executeUpdate(
				DbController.queries().outstanding().insert(1, 1200.00, 12,
						date, 100.00, "Dies ist ein Test"));

		// Fenster für Schulden-Liste aufrufen
		testInit(WndOutstandingList.WND_TITLE, TestWndOutstandingList.MENU_PATH,
				false);
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
	 * Öffnet das Fenster zum Datensatz Einfügen.
	 */
	public void pushInsert() {
		// Fenster fürs Einfügen aufrufen
		callPopup();
		pushPopup("Neu");
		_wndChange = new JInternalFrameOperator(_wnd, "Datensatz erstellen");
		
		// Komponenten ermitteln
		_cbSection = new JComboBoxOperator(_wnd);
		_txtMoney = new JTextFieldOperator(_wnd, 0);
		_txtMonths = new JTextFieldOperator(_wnd, 1);
		_txtStart = new JTextFieldOperator(_wnd, 2);
		_txtMonthMoney = new JTextFieldOperator(_wnd, 3);
		_txtComment = new JTextAreaOperator(_wnd);
		_tableRepay = new JTableOperator(_wnd);
		_labMoney = new JLabelOperator(_wnd, 8);
		_labMonths = new JLabelOperator(_wnd, 10);
	}
	
	/**
	 * Öffnet das Fenster zum Datensatz ändern
	 */
	public void pushChange() {
		// Fenster fürs Einfügen aufrufen
		callPopup();
		pushPopup("Ändern");
		_wndChange = new JInternalFrameOperator(_wnd, "Datensatz ändern");
		
		// Komponenten ermitteln
		_cbSection = new JComboBoxOperator(_wnd);
		_txtMoney = new JTextFieldOperator(_wnd, 0);
		_txtMonths = new JTextFieldOperator(_wnd, 1);
		_txtStart = new JTextFieldOperator(_wnd, 2);
		_txtMonthMoney = new JTextFieldOperator(_wnd, 3);
		_txtComment = new JTextAreaOperator(_wnd);
		_tableRepay = new JTableOperator(_wnd);
		_labMoney = new JLabelOperator(_wnd, 8);
		_labMonths = new JLabelOperator(_wnd, 10);
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
	 * Ermittelt, ob der Button zum "Zahlungen suchen" vorhanden ist und
	 * angezeigt wird.
	 * 
	 * @return Existiert der Button "Zahlungen suchen"?
	 */
	public boolean existsSearchRepayButton() {
		return new JButtonOperator(_wndChange, "Zahlungen suchen").isVisible();
	}
	
	/**
	 * Ermittelt, ob der Button zum "Neue Zahlung" vorhanden ist und
	 * angezeigt wird.
	 * 
	 * @return Existiert der Button "Neue Zahlung"?
	 */
	public boolean existsNewRepayButton() {
		return new JButtonOperator(_wndChange, "Neue Zahlung").isVisible();
	}
	
	/**
	 * Klickt auf Speichern, aber so dass ein Dialog abgefangen werden kann.
	 */
	public void pushNoBlockSave() {
		new JButtonOperator(_wndChange, "Speichern").pushNoBlock();
	}
	
	/**
	 * Klickt auf Speichern
	 */
	public void pushSave() {
		new JButtonOperator(_wndChange, "Speichern").push();
	}
	
	/**
	 * Klickt auf den Abbrechen-Button
	 */
	public void pushCancel() {
		new JButtonOperator(_wndChange, "Abbrechen").push();
	}

	/**
	 * Ermittelt, ob die Combo-Box für die Geschäfte vorhanden ist und angezeigt
	 * wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsSectionComboBox() {
		if (_cbSection == null)
			return false;
		return _cbSection.isVisible();
	}

	/**
	 * Ermittelt, ob das Text-Feld für die Höhe der Schuld vorhanden ist und
	 * angezeigt wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsMoneyTextField() {
		if (_txtMoney == null)
			return false;
		return _txtMoney.isVisible();
	}

	/**
	 * Ermittelt, ob das Text-Feld für die Anzahl der monatlichen Raten
	 * vorhanden ist und angezeigt wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsMonthsTextField() {
		if (_txtMonths == null)
			return false;
		return _txtMonths.isVisible();
	}

	/**
	 * Ermittelt, ob das Text-Feld für die Höhe der monatlichen Raten
	 * vorhanden ist und angezeigt wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsMonthMoneyTextField() {
		if (_txtMonthMoney == null)
			return false;
		return _txtMonthMoney.isVisible();
	}

	/**
	 * Ermittelt, ob das Text-Feld für das Datum der 1. Rate vorhanden ist und
	 * angezeigt wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsStartTextField() {
		if (_txtStart == null)
			return false;
		return _txtStart.isVisible();
	}

	/**
	 * Ermittelt, ob der Text-bereich für den kommentar vorhanden ist und
	 * angezeigt wird.
	 * 
	 * @return Existiert die Combo-Box für die Geschäfte?
	 */
	public boolean existsCommentTextArea() {
		if (_txtComment == null)
			return false;
		return _txtComment.isVisible();
	}

	/**
	 * Ermittelt, ob die Tabelle für die einzelnen Raten existiert.
	 * 
	 * @return Existiert die Tabelle für die Raten?
	 */
	public boolean existsRepayTable() {
		if (_tableRepay == null)
			return false;
		return _tableRepay.isVisible();
	}
	
	/**
	 * Ermittelt, ob die Höhe der restlichen Schuld ausgegeben wird.
	 * 
	 * @return Existiert die Ausgabe für die Höhe der restlichen Schuld?
	 */
	public boolean existsRestMoney() {
		if (_labMoney == null)
			return false;
		return _labMoney.isVisible();
	}
	
	/**
	 * Ermittelt, ob die Anzahl der restlichen monatlichen Raten ausgegeben wird.
	 * 
	 * @return Existiert die Ausgabe für die Anzahl der restlichen monatlichen
	 * Raten?
	 */
	public boolean existsRestMonths() {
		if (_labMonths == null)
			return false;
		return _labMonths.isVisible();
	}
	
	/**
	 * Der Text im Text-Feld für die Höhe der Schuld wird neu gesetzt.
	 * 
	 * @param str Text, der in das Text-Feld geschrieben werden soll.
	 */
	public void setMoneyValue(String str) {
		((JFormattedTextField)_txtMoney.getSource()).setValue(
				Double.valueOf(str));
	}
	
	/**
	 * Der Text im Text-Feld für die Höhe der Schuld wird neu gesetzt.
	 * 
	 * @param str Text, der in das Text-Feld geschrieben werden soll.
	 */
	public void setMoneyText(String str) {
		_txtMoney.setText(str);
	}
	
	/**
	 * Der Text im Text-Feld für die Höhe der monatlichen Rate wird neu gesetzt.
	 * 
	 * @param str Text, der in das Text-Feld geschrieben werden soll.
	 */
	public void setMonthMoneyText(String str) {
		_txtMonthMoney.setText(str);
	}
	
	/**
	 * Setzt den das Text-Feld für das Datum der 1. Rate
	 */
	public void focusStartField() {
		_txtStart.requestFocus();
		_txtStart.waitHasFocus();
	}
	
	/**
	 * Gibt den Text aus dem Text-Feld für die Höhe der Schuld zurück.
	 * 
	 * @return Text, der in dem Text-Feld für die Höhe der Schuld steht.
	 */
	public String getMoneyText() {
		return _txtMoney.getText();
	}
	
	/**
	 * Gibt den Text aus dem Text-Feld für die Höhe der monatlichen Rate zurück.
	 * 
	 * @return Text, der in dem Text-Feld für die Höhe der monatlichen Rate steht.
	 */
	public String getMonthMoneyText() {
		return _txtMonthMoney.getText();
	}
	
	/**
	 * Gibt den Text aus dem Text-Feld für die Anzahl der monatlichen Raten
	 * zurück.
	 * 
	 * @return Text, der in dem Text-Feld für die Anzahl der monatlichen Raten
	 * steht.
	 */
	public String getMonthsText() {
		return _txtMonths.getText();
	}
	
	/**
	 * Der Text im Text-Feld für die Anzahl der monatlichen Raten wird neu
	 * gesetzt.
	 * 
	 * @param str Text, der in das Text-Feld geschrieben werden soll.
	 */
	public void setMonthsText(String str) {
		_txtMonths.setText(str);
	}
	
	/**
	 * Gibt den Text aus dem Label für die Höhe der restlichen Schuld zurück.
	 * 
	 * @return Text, der in dem label für die Höhe der restichen Schuld steht.
	 */
	public String getMoneyLabel() {
		return _labMoney.getText();
	}
	
	/**
	 * Gibt den Text aus dem Label für die Höhe der restlichen Raten zurück.
	 * 
	 * @return Text, der in dem label für die Höhe der restichen Raten steht.
	 */
	public String getMonthsLabel() {
		return _labMonths.getText();
	}
	
	/**
	 * Gibt den Text aus dem Text-Feld für das Datum der 1. Rate zurück.
	 * 
	 * @return Text, der in dem Text-Feld für das Datum der 1. Rate steht.
	 */
	public String getStartText() {
		return _txtStart.getText();
	}
	
	/**
	 * Der Text im Text-Feld für das Datum der 1. Rate wird neu gesetzt.
	 * 
	 * @param str Text, der in das Text-Feld geschrieben werden soll.
	 */
	public void setStartText(String str) {
		_txtStart.setText(str);
	}
	
	/**
	 * Der Text im Text-Bereich für den Kommentar wird neu gesetzt.
	 * 
	 * @param str Text, der in den Text-Bereich geschrieben werden soll.
	 */
	public void setCommentText(String str) {
		_txtComment.setText(str);
	}
	
	/**
	 * Gibt den Text aus dem Text-Bereich für den Kommentar zurück.
	 * 
	 * @return Text, der in dem Text-Bereich für den Kommentar steht.
	 */
	public String getCommentText() {
		return _txtComment.getText();
	}
	
	/**
	 * Selektiert die ausgewählte Zeile in der Tabelle
	 * 
	 * @param row Zeile, die selektiert werden soll.
	 */
	public void selectTableRow(int row) {
		_table.selectCell(row, 0);
	}
	
	/**
	 * Ermittelt den Text, der in der Combo-Box für das Geschäft steht.
	 * 
	 * @return Text, der in der Combo-Box für das Geschäft steht.
	 */
	public String getSectionText() {
		return String.valueOf(_cbSection.getSelectedItem());
	}

	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
