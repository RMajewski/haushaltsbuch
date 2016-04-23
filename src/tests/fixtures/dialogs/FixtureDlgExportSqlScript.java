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

package tests.fixtures.dialogs;

import haushaltsbuch.dialogs.DlgExportSqlScript;
import tests.tests.dialogs.TestDlgExportSqlScript;

/**
 * Beinhaltet die einzelnen Test-Schritte, die im FIT-Dokument angegeben sind.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureDlgExportSqlScript extends FixtureDialogs {
	/**
	 * Initalisiert diese Klasse
	 * 
	 * @throws Exception 
	 */
	public FixtureDlgExportSqlScript() throws Exception {
		_test = new TestDlgExportSqlScript();
	}
	
	/**
	 * Klickt auf den Button zum Abbrechen
	 */
	public void pushCancel() {
		((TestDlgExportSqlScript)_test).pushCancel();
	}
	
	/**
	 * Klickt auf den Button zum exportieren
	 */
	public void pushExport() {
		pushOk();
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle der Kategorien exportiert
	 * werden soll, oder nicht.
	 * 
	 * @return Soll der Tabellen-Kopf der Tabelle 'category' export werden?
	 */
	public String isExportTableCategoryChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportTableCategoryChecked());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle der Geschäfte exportiert
	 * werden soll, oder nicht.
	 * 
	 * @return Soll der Tabellen-Kopf der Tabelle 'section' export werden?
	 */
	public String isExportTableSectionChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportTableSectionChecked());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle 'money' exportiert
	 * werden soll, oder nicht.
	 * 
	 * @return Soll der Tabellen-Kopf der Tabelle 'money' export werden?
	 */
	public String isExportTableMoneyChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportTableMoneyChecked());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle 'money_details' exportiert
	 * werden soll, oder nicht.
	 * 
	 * @return Soll der Tabellen-Kopf der Tabelle 'money_detail' export werden?
	 */
	public String isExportTableMoneyDetailsChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportTableMoneyDetailsChecked());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Daten für die Tabelle der Kategorien exportiert
	 * werden soll, oder nicht.
	 * 
	 * @return Sollen die Daten der Tabelle 'category' export werden?
	 */
	public String isExportDataCategoryChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportDataCategoryChecked());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Daten für die Tabelle der Geschäfte exportiert
	 * werden soll, oder nicht.
	 * 
	 * @return Sollen die Daten der Tabelle 'section' export werden?
	 */
	public String isExportDataSectionChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportDataSectionChecked());
	}
	
	/**
	 * Gibt aus, ob die Tabellen-Daten für die Tabelle 'money' exportiert
	 * werden sollen, oder nicht.
	 * 
	 * @return Sollen die Daten der Tabelle 'money' export werden?
	 */
	public String isExportDataMoneyChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportDataMoneyChecked());
	}
	
	/**
	 * Gibt aus, ob die Tabellen-Daten für die Tabelle 'money_details' exportiert
	 * werden sollen, oder nicht.
	 * 
	 * @return Sollen die Daten der Tabelle 'money_detail' export werden?
	 */
	public String isExportDataMoneyDetailsChecked() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).isExportDataMoneyDetailsChecked());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle der Kategorien existiert
	 * und angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Tabelle 'category'
	 */
	public String haveExportTableCategory() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportTableCategory());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle der Geschäfte existiert
	 * und angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Tabelle 'section'
	 */
	public String haveExportTableSection() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportTableSection());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle 'money' existiert und
	 * angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Tabelle 'money'
	 */
	public String haveExportTableMoney() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportTableMoney());
	}
	
	/**
	 * Gibt aus, ob der Tabellen-Kopf für die Tabelle 'money_details' existiert
	 * und angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Tabelle 'money_details'
	 */
	public String haveExportTableMoneyDetails() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportTableMoneyDetails());
	}
	
	/**
	 * Gibt aus, ob Daten für die Tabelle der Kategorien existiert
	 * und angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export der Daten für die
	 * Tabelle 'category'
	 */
	public String haveExportDataCategory() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportDataCategory());
	}
	
	/**
	 * Gibt aus, ob Daten für die Tabelle der Geschäfte existiert
	 * und angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export der Daten für die
	 * Tabelle 'section'
	 */
	public String haveExportDataSection() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportDataSection());
	}
	
	/**
	 * Gibt aus, ob Daten für die Tabelle 'money' existiert und angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export der Daten für die
	 * Tabelle 'money'
	 */
	public String haveExportDataMoney() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportDataMoney());
	}
	
	/**
	 * Gibt aus, ob Daten für die Tabelle 'money_details' existiert und
	 * angezeigt wird.
	 * 
	 * @return Existiert die Auswahl-Box für den Export der Daten für die
	 * Tabelle 'money_details'
	 */
	public String haveExportDataMoneyDetails() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).haveExportDataMoneyDetails());
	}
	
	/**
	 * Ermittelt, wie viele Einträge in den Einstellungen gespeichert sind.
	 * 
	 * @return Einträge in den Einstellungen
	 */
	public String preferencesCount() {
		return String.valueOf(
				((TestDlgExportSqlScript)_test).getPreferencesCount());
	}
	
	/**
	 * Ermittel, ob der Dialog mit dem Button Abbrechen beendet wurde oder
	 * nicht.
	 * 
	 * @return Wurde der Dialog Abgebrochen?
	 */
	public String isCancel() {
		return String.valueOf(((TestDlgExportSqlScript)_test).istCancel());
	}
	
	/**
	 * Ermittelt, ob der Tabellen-Kopf der Tabelle 'category' exportiert werden
	 * soll.
	 * 
	 * @return Soll der Tabellen-Kopf für die Tabelle 'category' exportiert
	 * werden?
	 */
	public String getPreferenceExportTableCategory() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.TABLE_CATEGORY);
	}
	
	/**
	 * Ermittelt, ob der Tabellen-Kopf der Tabelle 'section' exportiert werden
	 * soll.
	 * 
	 * @return Soll der Tabellen-Kopf für die Tabelle 'section' exportiert
	 * werden?
	 */
	public String getPreferenceExportTableSection() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.TABLE_SECTION);
	}
	
	/**
	 * Ermittelt, ob der Tabellen-Kopf der Tabelle 'money' exportiert werden
	 * soll.
	 * 
	 * @return Soll der Tabellen-Kopf für die Tabelle 'money' exportiert
	 * werden?
	 */
	public String getPreferenceExportTableMoney() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.TABLE_MONEY);
	}
	
	/**
	 * Ermittelt, ob der Tabellen-Kopf der Tabelle 'money_details' exportiert 
	 * werden soll.
	 * 
	 * @return Soll der Tabellen-Kopf für die Tabelle 'money_details' exportiert
	 * werden?
	 */
	public String getPreferenceExportTableMoneyDetails() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.TABLE_MONEY_DETAILS);
	}
	
	/**
	 * Ermittelt, ob die Daten der Tabelle 'category' exportiert werden sollen.
	 * 
	 * @return Sollen die Daten für die Tabelle 'category' exportiert werden?
	 */
	public String getPreferenceExportDataCategory() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.DATA_CATEGORY);
	}
	
	/**
	 * Ermittelt, ob die Daten der Tabelle 'section' exportiert werden sollen.
	 * 
	 * @return Sollen die Daten für die Tabelle 'section' exportiert werden?
	 */
	public String getPreferenceExportDataSection() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.DATA_SECTION);
	}
	
	/**
	 * Ermittelt, ob die Daten der Tabelle 'money' exportiert werden sollen.
	 * 
	 * @return Sollen die Datten für die Tabelle 'money' exportiert werden?
	 */
	public String getPreferenceExportDataMoney() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.DATA_MONEY);
	}
	
	/**
	 * Ermittelt, ob die Daten der Tabelle 'money_details' exportiert werden
	 * sollen.
	 * 
	 * @return Sollen die Daten für die Tabelle 'money_details' exportiert
	 * werden?
	 */
	public String getPreferenceExportDataMoneyDetails() {
		return ((TestDlgExportSqlScript)_test).getPreference(
				DlgExportSqlScript.DATA_MONEY_DETAILS);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob der Tabellen-Kopf für
	 * die Kategorien exportiert werden soll oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportTableCategory(boolean select) {
		((TestDlgExportSqlScript)_test).setExportTableCategory(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob der Tabellen-Kopf für
	 * die Geschäfte exportiert werden soll oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportTableSection(boolean select) {
		((TestDlgExportSqlScript)_test).setExportTableSection(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob der Tabellen-Kopf für
	 * die Tabelle 'money' exportiert werden soll oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportTableMoney(boolean select) {
		((TestDlgExportSqlScript)_test).setExportTableMoney(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob der Tabellen-Kopf für
	 * die Tabelle 'money_details' exportiert werden soll oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportTableMoneyDetails(boolean select) {
		((TestDlgExportSqlScript)_test).setExportTableMoneyDetails(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob Daten für die
	 * Kategorien exportiert werden sollen oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportDataCategory(boolean select) {
		((TestDlgExportSqlScript)_test).setExportDataCategory(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob die Daten für die
	 * Geschäfte exportiert werden sollen oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportDataSection(boolean select) {
		((TestDlgExportSqlScript)_test).setExportDataSection(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob die Daten für die
	 * Tabelle 'money' exportiert werden sollen oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportDataMoney(boolean select) {
		((TestDlgExportSqlScript)_test).setExportDataMoney(select);
	}
	
	/**
	 * Wählt die Auswahl-Box aus oder ab, die angibt, ob die Daten für die
	 * Tabelle 'money_details' exportiert werden sollen oder nicht.
	 * 
	 * @param select Soll aus- oder abgewählt werden?
	 */
	public void setExportDataMoneyDetails(boolean select) {
		((TestDlgExportSqlScript)_test).setExportDataMoneyDetails(select);
	}
}
