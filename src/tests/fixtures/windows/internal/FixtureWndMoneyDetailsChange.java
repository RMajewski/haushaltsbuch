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

package tests.fixtures.windows.internal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import haushaltsbuch.db.DbController;
import tests.fixtures.FixtureWnd;
import tests.tests.windows.internal.TestWndMoneyDetailsChange;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndMoneyDetailsChange und dem Test-Programm TestWndMoneyDetailsChange dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndMoneyDetailsChange extends FixtureWnd {

	/**
	 * Initalisiert die Klasse
	 * @throws Exception 
	 */
	public FixtureWndMoneyDetailsChange() throws Exception {
		_test = new TestWndMoneyDetailsChange();
	}
	
	/**
	 * Liest die Anzahl der Datensätze in der Datenbank-Tabelle "money_details"
	 * aus.
	 * @throws SQLException 
	 */
	public String getDbTableRowCount() throws SQLException {
		ResultSet rs = DbController.getInstance().createStatement().executeQuery(
				DbController.queries().moneyDetails().count());
		return rs.getString(1);
	}
	
	/**
	 * Ermittelt ob die Combo-Box für die Kategorien existiert.
	 * 
	 * @return Existiert die Combo-Box der Kategorien?
	 */
	public String haveCategoryComboBox() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).existsCategoryComboBox());
	}
	
	/**
	 * Ermittelt ob die Combo-Box für die Geschäfte existiert.
	 * 
	 * @return Existiert die Combo-Box der Geschäfte?
	 */
	public String haveSectionComboBox() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).existsSectionComboBox());
	}
	
	/**
	 * Ermittelt ob das Eingabe-Feld für den Betrag existiert.
	 * 
	 * @return Existiert die Betrag-Eingabe?
	 */
	public String haveMoneyTextField() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).existsMoneyTextField());
	}
	
	/**
	 * Ermittelt, ob der Eingabe-Bereich für den Kommentar existiert.
	 * 
	 * @return Existiert die Kommentar-Eingabe?
	 */
	public String haveCommentTextArea() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).existsCommentTextArea());
	}
	
	/**
	 * Ermittelt, ob der Abbrechen-Button existiert.
	 * 
	 * @return Existiert der Button "Abbrechen"?
	 */
	public String haveCancelButton() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).existsCancelButton());
	}
	
	/**
	 * Ermittelt, ob der Speichern-Button existiert.
	 * 
	 * @return Existiert der Button "Speichern"?
	 */
	public String haveSaveButton() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).existsSavelButton());
	}
	
	/**
	 * Setzt den Text in den Eingabefeld für den Betrag.
	 * 
	 * @param str Text, der in das Eingabefeld übertragen werden soll.
	 * @throws ParseException 
	 */
	public void setMoneyText(String str) throws ParseException {
		((TestWndMoneyDetailsChange)_test).setMoneyText(str);
	}
	
	/**
	 * Drückt auf den Speicher-Button
	 */
	public void pushNoBlockSave() {
		((TestWndMoneyDetailsChange)_test).pushNoBlockSave();
		_test.waitDlg("Betrag");
	}
	
	/**
	 * Setzt den Focus auf den Eingabebereich des Kommentars
	 */
	public void focusCommentArea() {
		((TestWndMoneyDetailsChange)_test).setFocusToCommentTextArea();
	}
	
	/**
	 * Ermittelt den Text vom Eingabefeld des Betrages
	 */
	public String getMoney() {
		return ((TestWndMoneyDetailsChange)_test).getTextFromMoneyTextField();
	}
	
	/**
	 * Setzt den Kommentar in den EIngabebereich.
	 * 
	 * @param str Neuer Kommentar.
	 */
	public void setCommentTextArea(String str) {
		((TestWndMoneyDetailsChange)_test).setCommentText(str);
	}
	
	/**
	 * Drückt den Speichern-Button
	 */
	public void pushSave() {
		((TestWndMoneyDetailsChange)_test).pushSave();
	}
	
	/**
	 * Drückt den Abbrechen-Button
	 */
	public void pushCancel() {
		((TestWndMoneyDetailsChange)_test).pushCancel();
	}
	
	/**
	 * Öffnet das Fenster zum Einfügen
	 */
	public void pushInsert() {
		((TestWndMoneyDetailsChange)_test).pushInsert();
	}
	
	/**
	 * Öffnet das Fenster zum Ändern
	 */
	public void pushChange() {
		((TestWndMoneyDetailsChange)_test).pushChange();
	}
	
	/**
	 * Selektiert die 1. Zeile der Tabelle
	 */
	public void selectTableRow0() {
		_test.tableSelectRow(0);
	}
	
	/**
	 * Ermittelt den Text der Kombobox für die Kategorie
	 * 
	 * @return Name der ausgwählten Kategorie
	 */
	public String getCategoryComboBoxText() {
		return ((TestWndMoneyDetailsChange)_test).getCategoryComboBoxText();
	}
	
	/**
	 * Ermittelt den Text der Kombobox für die Geschäfte
	 * 
	 * @return Name des ausgewählten Geschäftes
	 */
	public String getSectionComboBoxText() {
		return ((TestWndMoneyDetailsChange)_test).getSectionComboBoxText();
	}
	
	/**
	 * Ermittelt den Text der Eingabe des Betrages.
	 * 
	 * @return Text des Betrag-Eingabefeldes
	 */
	public String getMoneyTextFieldText() {
		return ((TestWndMoneyDetailsChange)_test).getTextFromMoneyTextField();
	}
	
	/**
	 * Ermittelt den Text der Eingabe des Kommentares
	 * 
	 * @return Text des Kommentares
	 */
	public String getCommentTextAreaText() {
		return ((TestWndMoneyDetailsChange)_test).getCommentTextAreaText();
	}
	
	/**
	 * Ermittelt den Betrag des geänderten Datensatzes
	 * 
	 * @return Betrag des Datensatzes
	 */
	public String getDbMoney() throws SQLException {
		ResultSet rs = DbController.getInstance().createStatement()
				.executeQuery(DbController.queries().moneyDetails()
						.search("id", 1));
		
		return String.valueOf(rs.getDouble("money"));
	}
	
	/**
	 * Ermittelt ob das Fenster angezeigt wird.
	 */
	@Override
	public String isWindowVisible() {
		return String.valueOf(
				((TestWndMoneyDetailsChange)_test).isWindowVisible());
	}
}
