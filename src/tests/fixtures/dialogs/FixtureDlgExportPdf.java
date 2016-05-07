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

import haushaltsbuch.dialogs.DlgExportPdf;
import tests.tests.dialogs.TestDlgExportPdf;

/**
 * Beinhaltet die einzelnen Test-Schritte, die im FIT-Dokument angegeben sind.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureDlgExportPdf extends FixtureDialogs {
	/**
	 * Initalisiert diese Klasse
	 * 
	 * @throws Exception 
	 */
	public FixtureDlgExportPdf() throws Exception {
		_test = new TestDlgExportPdf();
	}

	/**
	 * Ermittelt, ob das Auswahlfeld für die Tabelle existiert oder nicht.
	 * 
	 * @return Existiert das Auswahlfeld für die Tabelle?
	 */
	public String haveExportTable() {
		return String.valueOf(
				((TestDlgExportPdf)_test).isCheckBoxTableVisible());
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für die Tabelle ausgewählt ist oder nicht.
	 * 
	 * @return Ist das Auswahlfeld für die Tabelle ausgewählt?
	 */
	public String isExportTableChecked() {
		return String.valueOf(
				((TestDlgExportPdf)_test).isCheckBoxTableChecked());
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für das Balken-Diagramm existiert oder
	 * nicht.
	 * 
	 * @return Existiert das Auswahlfeld für das Balken-Digramm?
	 */
	public String haveExportBarChart() {
		return String.valueOf(
				((TestDlgExportPdf)_test).isCheckBoxBarChartVisible());
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für das Balken-Diagramm ausgewählt ist oder
	 * nicht.
	 * 
	 * @return Ist das Auswahlfeld für das Balken-Diagramm ausgewählt?
	 */
	public String isExportBarChartChecked() {
		return String.valueOf(
				((TestDlgExportPdf)_test).isCheckBoxBarCharChecked());
	}
	
	/**
	 * Klickt auf den Button zum Abbrechen
	 */
	public void pushCancel() {
		((TestDlgExportPdf)_test).pushCancel();
	}
	
	/**
	 * Ermittelt ob der Button "Abbrechen" gedrückt wurde
	 * 
	 * @return Wurde der Button "Abbrechen" gedrückt?
	 */
	public String isCancel() {
		return String.valueOf(((TestDlgExportPdf)_test).isCancel());
	}
	
	/**
	 * Ermittelt die Anzahl der gespeicherten Einstellungen.
	 * 
	 * @return Anzahl der gespeicherten Einstellungen.
	 */
	public String preferencesCount() {
		return String.valueOf(((TestDlgExportPdf)_test).getPreferencesCount());
	}
	
	/**
	 * Drückt auf den Export-Button
	 */
	public void pushExport() {
		_test.pushOk();
	}
	
	/**
	 * Ermittelt, ob der Export der Tabelle in den Einstellungen gespeichert
	 * wurde.
	 * 
	 * 
	 * @return Wurde der Export der Tabelle in den Einstellungen gespeichert?
	 */
	public String getPreferenceExportTable() {
		return ((TestDlgExportPdf)_test).getPreference(
				DlgExportPdf.EXPORT_TABLE);
	}
	
	/**
	 * Ermittelt, ob der Export des Balkendiagrammes in den Einstellungen
	 * speichert wurde.
	 * 
	 * @return Wurde der Export des Balkendigrammes in den Einstellungen
	 * gespeichert?
	 */
	public String getPreferenceExportBarChart() {
		return ((TestDlgExportPdf)_test).getPreference(
				DlgExportPdf.EXPORT_BAR_CHART);
	}
	
	/**
	 * Setzt das Auswahlfeld für den Export der Tabelle neu.
	 * 
	 * @param select Soll das Auswahlfeld für den Export der Tabelle ausgewählt
	 * sein?
	 */
	public void setExportTable(boolean select) {
		((TestDlgExportPdf)_test).setCheckBoxTable(select);
	}
	
	/**
	 * Setzt das Auswahlfeld für den Export des Balkendiagrammes neu.
	 * 
	 * @param select Soll das Auswahlfeld für den Export des Balkendiagrammes
	 * ausgewählt sein?
	 */
	public void setExportBarChart(boolean select) {
		((TestDlgExportPdf)_test).setCheckBoxBarChart(select);
	}
}
