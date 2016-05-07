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

import haushaltsbuch.dialogs.DlgExportPdf;
import tests.apps.TestDialogApplication;
import tests.testcase.TestDialogs;

/**
 * Testet den Dialog "Export PDF"
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestDlgExportPdf extends TestDialogs {
	/**
	 * Speichert den Button zum Abbrechen
	 */
	private JButtonOperator _btnCancel;
	
	/**
	 * Speichert das Auswahlfeld für die Tabelle
	 */
	private JCheckBoxOperator _cbTable;
	
	/**
	 * Speichert das Auswahlfeld für das Balken-Diagramm
	 */
	private JCheckBoxOperator _cbBarChart;
	
	/**
	 * Initalisiert die Klasse
	 */
	public TestDlgExportPdf() throws Exception {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
		
		// Dialog aufrufen
		new JButtonOperator(_frame,
				TestDialogApplication.DIALOG_EXPORT_PDF).pushNoBlock();
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame, DlgExportPdf.DIALOG_TITLE);
		
		// Button ermitteln
		_btnOk = new JButtonOperator(_dlg, "Export");
		_btnCancel = new JButtonOperator(_dlg, "Abbrechen");
		
		// Auswahlfelder ermitteln
		_cbTable = new JCheckBoxOperator(_dlg, "Tabelle");
		_cbBarChart = new JCheckBoxOperator(_dlg, "Balken-Diagramm");
	}
	
	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object o) {
		return 0;
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für die Tabelle vorhanden ist.
	 * 
	 * @return Ist das Auswahlfeld für die Tabelle vorhanden?
	 */
	public boolean isCheckBoxTableVisible() {
		return _cbTable.isVisible();
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für die Tabelle ausgewählt ist oder nicht.
	 * 
	 * @return Ist das Auswahlfeld für die Tabelle ausgewählt?
	 */
	public boolean isCheckBoxTableChecked() {
		return _cbTable.isSelected();
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für das Balkendigramm vorhanden ist.
	 * 
	 * @return Ist das Auswahlfeld für das Balkendiagramm vorhanden?
	 */
	public boolean isCheckBoxBarChartVisible() {
		return _cbBarChart.isVisible();
	}
	
	/**
	 * Ermittelt, ob das Auswahlfeld für das Balkendiagramm ausgewählt ist.
	 * 
	 * @return Ist das Auswahlfeld für das Balkendiagramm ausgewählt?
	 */
	public boolean isCheckBoxBarCharChecked() {
		return _cbBarChart.isSelected();
	}
	
	/**
	 * Klickt auf den Button zum Abbrechen.
	 */
	public void pushCancel() {
		_btnCancel.push();
	}
	
	/**
	 * Ermittelt, ob auf den Button "Abbrechen" geklickt wurde
	 * 
	 * @return WUrde auf den Button "Abbrechen" geklickt?
	 */
	public boolean isCancel() {
		return ((DlgExportPdf)_dlg.getSource()).isCancel();
	}
	
	/**
	 * Ermittelt die Anzahl der gespeicherten Einstellungen.
	 * 
	 * @return Anzahl der gespeicherten Einstellungen.
	 */
	public int getPreferencesCount() {
		return ((DlgExportPdf)_dlg.getSource()).getPreferences().size();
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
				((DlgExportPdf)_dlg.getSource()).getPreferences()
				.get(key));
	}
	
	/**
	 * Setzt die Auswahl des Auswahlfeldes für den Export der Tabelle auf den
	 * übergebenen Wert.
	 * 
	 * @param select Soll das Auswahlfeld für den Export der Tabelle ausgewählt
	 * sein?
	 */
	public void setCheckBoxTable(boolean select) {
		_cbTable.setSelected(select);
	}
	
	/**
	 * Setzt die Auswahl des Auswahlfeldes für den Export des Balkendiagramme
	 * auf den übergebenen Wert.
	 * 
	 * @param select Soll das Auswahlfeld für den Export des Balkendiagramme
	 * ausgewählt sein?
	 */
	public void setCheckBoxBarChart(boolean select) {
		_cbBarChart.setSelected(select);
	}
	
	/**
	 * Startet den Test
	 */
	public static void main(String[] args) {
		Test.main(new String[] {"tests.tests.dialogs.TestDlgExporPdf"});
	}


}
