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
import haushaltsbuch.db.DbController;
import tests.fixtures.FixtureWnd;
import tests.tests.windows.internal.TestWndOutstandingChange;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndOutstandingChange und dem Test-Programm TestWndOutstandingChange dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class FixtureWndOutstandingChange extends FixtureWnd {

	/**
	 * Initalisiert die Klasse
	 * @throws Exception 
	 */
	public FixtureWndOutstandingChange() throws Exception {
		_test = new TestWndOutstandingChange();
	}
	
	/**
	 * Liest die Anzahl der Datensätze in der Datenbank-Tabelle "outstanding"
	 * aus.
	 * @throws SQLException 
	 */
	public String getDbTableRowCount() throws SQLException {
		ResultSet rs = DbController.getInstance().createStatement().executeQuery(
				DbController.queries().outstanding().count());
		return rs.getString(1);
	}
	
	/**
	 * Öffnet das Fenster zum Einfügen
	 */
	public void pushInsert() {
		((TestWndOutstandingChange)_test).pushInsert();
	}
	
	/**
	 * Öffnet das Fenster zum Ändern
	 */
	public void pushChange() {
		((TestWndOutstandingChange)_test).pushChange();
	}
	
	/**
	 * Ermittelt, ob das Unterfenster angezeigt wird.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	@Override
	public String isWindowVisible() {
		return String.valueOf(
				((TestWndOutstandingChange)_test).isWindowVisible());
	}
	
	/**
	 * Ermittelt, ob es im Fenster einen Button "Abbrechen" gibt
	 * 
	 * @return Exisitert ein Button "Abbrechen"?
	 */
	public boolean haveCancelButton() {
		return ((TestWndOutstandingChange)_test).existsCancelButton();
	}
	
	/**
	 * Ermittelt, ob es im Fenster einen Button "Speichern" gibt
	 * 
	 * @return Exisitert ein Button "Speichern"?
	 */
	public boolean haveSaveButton() {
		return ((TestWndOutstandingChange)_test).existsSaveButton();
	}
	
	/**
	 * Ermittelt, ob es im Fenster einen Button "Zahlungen suchen" gibt
	 * 
	 * @return Exisitert ein Button "Zahlungen suchen"?
	 */
	public boolean haveSearchRepayButton() {
		return ((TestWndOutstandingChange)_test).existsSearchRepayButton();
	}
	
	/**
	 * Ermittelt, ob es im Fenster einen Button "Neue Zahlung" gibt
	 * 
	 * @return Exisitert ein Button "Neue Zahlung"?
	 */
	public boolean haveInsertRepayButton() {
		return ((TestWndOutstandingChange)_test).existsNewRepayButton();
	}
	
	/**
	 * Ermittelt, ob es im Fenster eine Combo-Box für die Geschäfte gibt.
	 * 
	 * @return Existiert eine Combo-Box für die Geschäfte
	 */
	public boolean haveSectionComboBox() {
		return ((TestWndOutstandingChange)_test).existsSectionComboBox();
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Text-Feld für die die Höhe der Schuld
	 * gibt.
	 * 
	 * @return Existiert ein Text-Feld für die Höhe der Schuld?
	 */
	public boolean haveMoneyFormattedTextField() {
		return ((TestWndOutstandingChange)_test).existsMoneyTextField();
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Text-Feld für die Anzahl der monatlichen
	 * Raten gibt.
	 * 
	 * @return Existiert ein Text-Feld für die Anzahl der monatlichen Raten?
	 */
	public boolean haveMonthsFormattedTextField() {
		return ((TestWndOutstandingChange)_test).existsMonthsTextField();
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Text-Feld für die Höhe der monatlichen
	 * Raten gibt.
	 * 
	 * @return Existiert ein Text-Feld für die Höhe der monatlichen Raten?
	 */
	public boolean haveMonthMoneyFormattedTextField() {
		return ((TestWndOutstandingChange)_test).existsMonthMoneyTextField();
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Text-Feld für das Datum der 1. Rate gibt.
	 * 
	 * @return Existiert ein Text-Feld für das Datum der 1. Rate?
	 */
	public boolean haveStartFormattedTextField() {
		return ((TestWndOutstandingChange)_test).existsStartTextField();
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Text-Bereich für den Kommentar gibt.
	 * 
	 * @return Existiert ein Text-Bereich für Kommentar?
	 */
	public boolean haveCommentTextArea() {
		return ((TestWndOutstandingChange)_test).existsCommentTextArea();
	}

	/**
	 * Ermittelt, ob es im Fenster eine Tabelle für die Rückzahlungen gibt.
	 * 
	 * @return Existiert eine Tabelle für die Rückzahlungen?
	 */
	public boolean haveTable() {
		return ((TestWndOutstandingChange)_test).existsRepayTable();
	}
	
	/**
	 * Ermittelt, ob es im Fenster die Höhe der restlichen Schuld angezeigt
	 * wird.
	 * 
	 * @return Existiert die Ausgabe der Höhe der restlichen Schuld?
	 */
	public boolean haveLabelForRestMoney() {
		return ((TestWndOutstandingChange)_test).existsRestMoney();
	}
	
	/**
	 * Ermittelt, ob es im Fenster die Anzahl der restlichen monatlichen Raten
	 * angezeigt wird.
	 * 
	 * @return Existiert die Ausgabe der Anzahl der restlichen monatlichen
	 * Raten?
	 */
	public boolean haveLabelForRestMonths() {
		return ((TestWndOutstandingChange)_test).existsRestMonths();
	}
	
	/**
	 * Es wird ein Druck auf den Speichern-Button simuliert.
	 */
	public void pushNoBlockSave() {
		((TestWndOutstandingChange)_test).pushNoBlockSave();
	}
	
	/**
	 * Es wird ein Druck auf den Speichern-Button simuliert.
	 */
	public void pushSave()  {
		((TestWndOutstandingChange)_test).pushSave();
	}
	
	/**
	 * Es wird ein Klick auf den Abbrechen-Button simuliert.
	 */
	public void pushCancel() {
		((TestWndOutstandingChange)_test).pushCancel();
	}
	
	/**
	 * Es wird auf den angegebenen Dialog gewartet.
	 * 
	 * @param title Titel des Dialoges
	 */
	public void waitForDialog(String title) {
		_test.waitDlg(title);
	}
	
	/**
	 * Setzt den Text für die Höhe der Schuld
	 * 
	 * @param value Höhe der Schuld
	 */
	public void setMoneyTextField(String value) {
		((TestWndOutstandingChange)_test).setMoneyText(value);
	}
	
	/**
	 * Setzt den Text für die Höhe der Schuld
	 * 
	 * @param value Höhe der Schuld
	 */
	public void setMoneyTextFieldValue(String value) {
		((TestWndOutstandingChange)_test).setMoneyText(value);
	}
	
	/**
	 * Setzt den das Text-Feld für das Datum der 1. Rate
	 */
	public void focusStartField() {
		((TestWndOutstandingChange)_test).focusStartField();
	}
	
	/**
	 * Ermittelt den Text des Eingabe-Feldes für die Höhe der Schuld.
	 * 
	 * @return Höhe der Schuld im Eingabe-Feld.
	 */
	public String getMoney() {
		return ((TestWndOutstandingChange)_test).getMoneyText();
	}
	
	/**
	 * Setzt den Text für die Höhe der monatlichen Rate
	 * 
	 * @param value Höhe der monatlichen Rate
	 */
	public void setMonthMoneyTextField(String value) {
		((TestWndOutstandingChange)_test).setMonthMoneyText(value);
	}
	
	/**
	 * Ermittelt den Text des Eingabe-Feldes für die Höhe der monatlichen Rate.
	 * 
	 * @return Höhe der monatlichen Rate im Eingabe-Feld.
	 */
	public String getMonthMoney() {
		return ((TestWndOutstandingChange)_test).getMonthMoneyText();
	}
	
	/**
	 * Ermittelt den Text des Eingabe-Feldes für die Anzahl der monatlichen
	 * Raten.
	 * 
	 * @return Anzahl der monatlichen Raten im Eingabe-Feld.
	 */
	public String getMonths() {
		return ((TestWndOutstandingChange)_test).getMonthsText();
	}
	
	/**
	 * Gibt den Text aus dem Label für die Höhe der restlichen Schuld zurück.
	 * 
	 * @return Text, der in dem label für die Höhe der restichen Schuld steht.
	 */
	public String getLabelRestMoney() {
		return ((TestWndOutstandingChange)_test).getMoneyLabel();
	}
	
	/**
	 * Gibt den Text aus dem Label für die Höhe der restlichen Raten zurück.
	 * 
	 * @return Text, der in dem label für die Höhe der restichen Raten steht.
	 */
	public String getLabelRestMonths() {
		return ((TestWndOutstandingChange)_test).getMonthsLabel();
	}
	
	/**
	 * Setzt den Text für die Anzahl der monatlichen Raten
	 * 
	 * @param value Anzahl der monatlichen Raten
	 */
	public void setMonthsTextField(String value) {
		((TestWndOutstandingChange)_test).setMonthsText(value);
	}
	
	/**
	 * Setzt den Text für die das Datum der 1. Rate
	 * 
	 * @param value Datum der 1. Rate
	 */
	public void setStartTextField(String value) {
		((TestWndOutstandingChange)_test).setStartText(value);
	}
	
	/**
	 * Ermittelt den Text des Eingabe-Feldes für das Datum der 1. Rate.
	 * 
	 * @return Datum der 1. Rate.
	 */
	public String getStart() {
		return ((TestWndOutstandingChange)_test).getStartText();
	}
	
	/**
	 * Setzt den Text für den Kommentar
	 * 
	 * @param value Text des Kommentars
	 */
	public void setCommentTextArea(String value) {
		((TestWndOutstandingChange)_test).setCommentText(value);
	}
	
	/**
	 * Ermittel den Text des Eingabe-Bereiches für den Kommentar.
	 * 
	 * @return Kommentar
	 */
	public String getComment() {
		return ((TestWndOutstandingChange)_test).getCommentText();
	}
	
	/**
	 * Selektiert die 1. Zeile in der Tabelle
	 */
	public void selectTableRow0() {
		((TestWndOutstandingChange)_test).selectTableRow(0);
	}
	
	/**
	 * Ermittelt den Text, der in der Combo-Box für das Geschäft steht.
	 * 
	 * @return Text aus der Combo-Box für das Geschäft.
	 */
	public String getSection() {
		return ((TestWndOutstandingChange)_test).getSectionText();
	}
	
	/**
	 * Ermittelt den Betrag für die Höhe der Schulden aus der Datenbank
	 * 
	 * @return Betrag für die Höhe der Schulden
	 */
	public double getDbMoney() throws SQLException {
		ResultSet rs = DbController.getInstance().createStatement()
				.executeQuery(DbController.queries().outstanding().search(
						"id", 1));
		return rs.getDouble("money");
	}
}
