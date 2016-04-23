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

package tests.tests.dialogs;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JCheckBoxOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

import haushaltsbuch.dialogs.DlgExportSqlScript;
import tests.apps.TestDialogApplication;
import tests.testcase.TestDialogs;

/**
 * Testet den Dialog "Export SQL-Script"
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestDlgExportSqlScript extends TestDialogs {
	/**
	 * Speichert den Button zum Abbrechen
	 */
	private JButtonOperator _btnCancel;
	
	/**
	 * Speichert die Auswahl-Box für den Export des Tabellen-Kopfes für die
	 * Kategorien.
	 */
	private JCheckBoxOperator _cbTableCategory;
	
	/**
	 * Speichert die Auswahl-Box für den Export des Tabellen-Kopfes für die
	 * Geschäfte.
	 */
	private JCheckBoxOperator _cbTableSection;
	
	/**
	 * Speichert die Auswahl-Box für den Export des Tabellen-Kopfes für die
	 * Tabelle 'money'.
	 */
	private JCheckBoxOperator _cbTableMoney;
	
	/**
	 * Speichert die Auswahl-Box für den Export des Tabellen-Kopfes für die
	 * Tabelle 'money_details'.
	 */
	private JCheckBoxOperator _cbTableMoneyDetails;
	
	/**
	 * Speichert die Auswahl-Box für den Export der Tabellen-Daten für die
	 * Kategorien.
	 */
	private JCheckBoxOperator _cbDataCategory;
	
	/**
	 * Speichert die Auswahl-Box für den Export der Tabellen-Daten für die
	 * Geschäfte.
	 */
	private JCheckBoxOperator _cbDataSection;
	
	/**
	 * Speichert die Auswahl-Box für den Export der Tabellen-Daten für die
	 * Tabelle 'money'.
	 */
	private JCheckBoxOperator _cbDataMoney;
	
	/**
	 * Speichert die Auswahl-Box für den Export der Tabellen-Daten für die
	 * Tabelle 'money_details'.
	 */
	private JCheckBoxOperator _cbDataMoneyDetails;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestDlgExportSqlScript() throws Exception {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
		
		// Dialog aufrufen
		new JButtonOperator(_frame,
				TestDialogApplication.DIALOG_EXPORT_SQL).pushNoBlock();
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame, DlgExportSqlScript.DIALOG_TITLE);
		
		// Button ermitteln
		_btnOk = new JButtonOperator(_dlg, "Export");
		_btnCancel = new JButtonOperator(_dlg, "Abbrechen");
		
		// Auswahl-Boxen für die Tabellen-Köpfe
		_cbTableCategory = new JCheckBoxOperator(_dlg, 0);
		_cbTableSection = new JCheckBoxOperator(_dlg, 2);
		_cbTableMoney = new JCheckBoxOperator(_dlg, 5);
		_cbTableMoneyDetails = new JCheckBoxOperator(_dlg, 7);
		
		// Auswahl-Boxen für die Tabellen-Daten
		_cbDataCategory = new JCheckBoxOperator(_dlg, 1);
		_cbDataSection = new JCheckBoxOperator(_dlg, 3);
		_cbDataMoney = new JCheckBoxOperator(_dlg, 4);
		_cbDataMoneyDetails = new JCheckBoxOperator(_dlg, 6);
	}
	
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object o) {
		return 0;
	}
	
	/**
	 * Klickt den Button zum Abbrechen an.
	 */
	public void pushCancel() {
		_btnCancel.push();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Kategorien ausgewählt ist.
	 */
	public boolean isExportTableCategoryChecked() {
		return _cbTableCategory.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Geschäfte ausgewählt ist.
	 */
	public boolean isExportTableSectionChecked() {
		return _cbTableSection.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Tabelle 'money' ausgewählt ist.
	 */
	public boolean isExportTableMoneyChecked() {
		return _cbTableMoney.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export des Tabellen-Kopfes für
	 * die Tabelle 'money_details' ausgewählt ist.
	 */
	public boolean isExportTableMoneyDetailsChecked() {
		return _cbTableMoneyDetails.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export der Tabellen-Daten für
	 * die Kategorien ausgewählt ist.
	 */
	public boolean isExportDataCategoryChecked() {
		return _cbDataCategory.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export der Tabellen-Daten für
	 * die Geschäfte ausgewählt ist.
	 */
	public boolean isExportDataSectionChecked() {
		return _cbDataSection.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export der Tabellen-Daten für
	 * die Tabelle 'money' ausgewählt ist.
	 */
	public boolean isExportDataMoneyChecked() {
		return _cbDataMoney.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Export der Tabellen-Daten für
	 * die Tabelle 'money_details' ausgewählt ist.
	 */
	public boolean isExportDataMoneyDetailsChecked() {
		return _cbDataMoneyDetails.isSelected();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Tabellen-Kopf der Kategorien
	 * existiert und angezeigt wird.
	 */
	public boolean haveExportTableCategory() {
		return _cbTableCategory.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Tabellen-Kopf der Geschäfte
	 * existiert und angezeigt wird.
	 */
	public boolean haveExportTableSection() {
		return _cbTableSection.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Tabellen-Kopf der Tabelle 'money'
	 * existiert und angezeigt wird.
	 */
	public boolean haveExportTableMoney() {
		return _cbTableMoney.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für den Tabellen-Kopf der Tabelle
	 * 'money_details' existiert und angezeigt wird.
	 */
	public boolean haveExportTableMoneyDetails() {
		return _cbTableMoneyDetails.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für die Daten der Kategorien existiert
	 * und angezeigt wird.
	 */
	public boolean haveExportDataCategory() {
		return _cbTableCategory.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für die Daten der Geschäfte existiert
	 * und angezeigt wird.
	 */
	public boolean haveExportDataSection() {
		return _cbTableSection.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für die Daten der Tabelle 'money'
	 * existiert und angezeigt wird.
	 */
	public boolean haveExportDataMoney() {
		return _cbTableMoney.isVisible();
	}
	
	/**
	 * Gibt zurück, ob die Auswahl-Box für die Daten der Kategorien existiert
	 * und angezeigt wird.
	 */
	public boolean haveExportDataMoneyDetails() {
		return _cbTableMoneyDetails.isVisible();
	}
	
	/**
	 * Ermittelt wie viele Eintäge in den Einstellungen stehen.
	 * 
	 * @return Anzahl Einträge in den Einstellungen
	 */
	public int getPreferencesCount() {
		return ((DlgExportSqlScript)_dlg.getSource()).getPreferences().size();
	}
	
	/**
	 * Ermittelt die angegebene Einstellung und gibt sie zurück.
	 * 
	 * @param key Einstellung, die ermittelt werden soll.
	 * 
	 * @return Ermittelte Einstellung
	 */
	public String getPreference(String key) {
		return String.valueOf(
				((DlgExportSqlScript)_dlg.getSource()).getPreferences()
				.get(key));
	}
	
	/**
	 * Ermittelt ob der Dialog abgegebrochen wurde oder nicht.
	 * 
	 * @return Wurde der Dialog abgebrochen?
	 */
	public boolean istCancel() {
		return ((DlgExportSqlScript)_dlg.getSource()).isCancel();
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export des Tabellen-Kopfes der Kategorien
	 * aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportTableCategory(boolean select) {
		_cbTableCategory.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export des Tabellen-Kopfes der Geschäfte
	 * aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportTableSection(boolean select) {
		_cbTableSection.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export des Tabellen-Kopfes für die Tabelle
	 * 'money' aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportTableMoney(boolean select) {
		_cbTableMoney.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export des Tabellen-Kopfes für die Tabelle
	 * 'money_details' aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportTableMoneyDetails(boolean select) {
		_cbTableMoneyDetails.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export der Daten der Kategorien aus oder
	 * ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportDataCategory(boolean select) {
		_cbDataCategory.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export der Daten der Geschäfte aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportDataSection(boolean select) {
		_cbDataSection.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export der Daten für die Tabelle 'money'
	 * aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportDataMoney(boolean select) {
		_cbDataMoney.setSelected(select);
	}
	
	/**
	 * Wählt die Auswahl-Box für den Export der Daten für die Tabelle
	 * 'money_details' aus oder ab.
	 * 
	 * @param select Soll ausgewähl oder abgewählt werden?
	 */
	public void setExportDataMoneyDetails(boolean select) {
		_cbDataMoneyDetails.setSelected(select);
	}
	
	/**
	 * Startet den Test
	 */
	public static void main(String[] args) {
		Test.main(new String[] {"tests.tests.dialogs.TestDlgExportSqlScript"});
	}

}
