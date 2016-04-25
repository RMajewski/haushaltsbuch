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
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;

import haushaltsbuch.db.DbController;
import tests.fixtures.FixtureWnd;
import tests.tests.windows.internal.TestWndMoneyChange;
import tests.tests.windows.internal.TestWndMoneyList;

/**
 * Stellt die Schnittstelle zwischen den Fit-Tests für das Fenster
 * WndMoneyChange und dem Test-Programm TestWndMoneyChange dar.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureWndMoneyChange extends FixtureWnd {
	/**
	 * Initalisiert die Klasse
	 * 
	 * @throws Exception 
	 */
	public FixtureWndMoneyChange() throws Exception {
		_test = new TestWndMoneyChange();
	}
	
	/**
	 * Ermittelt die Anzahl der Einträge in der Tabelle "money"
	 * 
	 * @throws SQLException 
	 */
	public String getDbTableRowCount() throws SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery(DbController.queries().money().count());
		return String.valueOf(rs.getString(1));
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein TestFeld für das Datum gibt.
	 * 
	 * @return Exisitert das TextFeld?
	 */
	public String haveDateTextField() {
		return String.valueOf(
				((TestWndMoneyChange)_test).existsDateTextField());
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Radio-Button "Einnahme" gibt.
	 * 
	 * @return Exisitert der Radio-Buttun "Einnahme"
	 */
	public String haveIncomingRadioButton() {
		return String.valueOf(
				((TestWndMoneyChange)_test).existsIncomingRadioButton());
	}
	
	/**
	 * Ermittelt, ob es im Fenster ein Radio-Button "Ausgabe" gibt.
	 * 
	 * @return Exisitert der Radio-Buttun "Ausgabe"
	 */
	public String haveOutgoingRadioButton() {
		return String.valueOf(
				((TestWndMoneyChange)_test).existsOutgoingRadioButton());
	}
	
	/**
	 * Ermittelt, ob es im Fenster eine Eingabe-Area für den Kommentar gibt.
	 * 
	 * @return Exisitert eine Eingabe-Area für den Kommentar?
	 */
	public String haveCommentTextArea() {
		return String.valueOf(
				((TestWndMoneyChange)_test).existsCommentTextArea());
	}
	
	/**
	 * Ermittelt, ob es im Fenster einen Button "Abbrechen" gibt
	 * 
	 * @return Exisitert ein Button "Abbrechen"?
	 */
	public String haveCancelButton() {
		return String.valueOf(
				((TestWndMoneyChange)_test).existsCancelButton());
	}
	
	/**
	 * Ermittelt, ob es im Fenster einen Button "Speichern" gibt
	 * 
	 * @return Exisitert ein Button "Speichern"?
	 */
	public String haveSaveButton() {
		return String.valueOf(
				((TestWndMoneyChange)_test).existsSaveButton());
	}
	
	/**
	 * Gibt in den Datum-Feld etwas ein.
	 * 
	 * @param str Das, was in das Datums-Feld eingegeben werden soll
	 */
	public void setDateTextField(String str) {
		((TestWndMoneyChange)_test).setDateText(str);
	}
	
	/**
	 * Auf Speichern klicken
	 */
	public void pushNoBlockSave() {
		((TestWndMoneyChange)_test).pushNoBlockSave();
		_test.waitDlg("Datum");
	}
	
	/**
	 * Übergibt den Fokus an den Radio-Button "Einnahme"
	 */
	public void focusRadioButtonIn() {
		((TestWndMoneyChange)_test).setFocusToIn();
	}
	
	/**
	 * Es wird der Text im Datums-Feld ermittelt.
	 * 
	 * @return Text vom Datums-Feld
	 */
	public String getDate() {
		return ((TestWndMoneyChange)_test).getTextFromDate();
	}
	
	/**
	 * Es wird ermittelt, ob das Datum mit den aktuellen Monat und dem aktuellen
	 * Jahr vervollständigt wurde.
	 * 
	 * @return <b>true</b>, wenn das Datum vervollständigt wurde. <b>false</b>,
	 * wenn nicht.
	 */
	public String haveDateActualMonthAnYear() {
		// Vorbereitungen
		boolean ret = false;
		String[] tmp = ((TestWndMoneyChange)_test).getTextFromDate().split(".");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int month = gc.get(GregorianCalendar.MONTH);
		int year = gc.get(GregorianCalendar.YEAR);
		
		// Überprüfen ob der Monat und das Jahr hinzugefügt wurden.
		if ((tmp.length == 3) && tmp[1].equals(String.valueOf(month)) &&
				tmp[2].equals(String.valueOf(year)))
			ret = true;
		
		return String.valueOf(ret);
	}
	
	/**
	 * Es wird ermittelt, ob das Datum mit dem aktuellen Jahr vervollständigt
	 * wurde.
	 * 
	 * @return <b>true</b>, wenn das Datum vervollständigt wurde. <b>false</b>,
	 * wenn nicht.
	 */
	public String haveDateActualYear() {
		// Vorbereitungen
		boolean ret = false;
		String[] tmp = ((TestWndMoneyChange)_test).getTextFromDate().split(".");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int year = gc.get(GregorianCalendar.YEAR);
		
		// Überprüfen ob der Monat und das Jahr hinzugefügt wurden.
		if ((tmp.length == 3) && tmp[2].equals(String.valueOf(year)))
			ret = true;
		
		return String.valueOf(ret);
	}
	
	/**
	 * Übergibt den Text in den Text-Bereich des Kommentars ein.
	 * 
	 * @param str Text, der in den Text-Bereich gesetzt werden soll
	 */
	public void setCommentTextArea(String str) {
		((TestWndMoneyChange)_test).setCommentText(str);
	}
	
	/**
	 * Drückt den Speichern-Button
	 */
	public void pushSave() {
		((TestWndMoneyChange)_test).pushSave();
	}
	
	/**
	 * Ermittelt, ob das Unterfenster angezeigt wird.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	@Override
	public String isWindowVisible() {
		return String.valueOf(((TestWndMoneyChange)_test).isWindowVisible());
	}
	
}
