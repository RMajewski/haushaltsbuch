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

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JCheckBoxOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JRadioButtonOperator;
import org.netbeans.jemmy.operators.JTabbedPaneOperator;

import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.dialogs.DlgExportSqlScript;
import haushaltsbuch.dialogs.DlgReport;
import haushaltsbuch.helper.HelperCalendar;
import tests.apps.TestDialogApplication;
import tests.testcase.TestDialogs;

/**
 * Implementiert die GUI-Tests für den Report-Dialog.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestDlgReport extends TestDialogs {
	/**
	 * Speichert den Button zum Abbrechen
	 */
	private JButtonOperator _btnCancel;
	
	/**
	 * Speichert den Radio-Button zur Auswahl der Wochenübersicht
	 */
	private JRadioButtonOperator _rbWeek;
	
	/**
	 * Speichert den Radio-Button zur Auswahl der Monatsübersicht
	 */
	private JRadioButtonOperator _rbMonth;
	
	/**
	 * Speichert den Radio-Button zur Auswahl der Jahresübersicht
	 */
	private JRadioButtonOperator _rbYear;
	
	/**
	 * Speichert die Einstellungen für den Report-Dialog.
	 */
	private ReportPreferencesData _rpd;
	
	/**
	 * Speichert die Auswahl-Box für das Jahr
	 */
	private JComboBoxOperator _cbYear;
	
	/**
	 * Speichert die Auswahl-Box für den Monat
	 */
	private JComboBoxOperator _cbMonth;
	
	/**
	 * Speichert die Check-Box für die Spalte "von"
	 */
	private JCheckBoxOperator _cbDateFrom;
	
	/**
	 * Speichert die Check-Box für die Spalte "bis"
	 */
	private JCheckBoxOperator _cbDateTo;
	
	/**
	 * Speichert die Tab-Pane
	 */
	private JTabbedPaneOperator _tabPane;
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @throws Exception 
	 */
	public TestDlgReport() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		// Test-Applikation aufrufen
		(new ClassReference("tests.apps.TestDialogApplication")).startApplication();
		
		// Fenster ermitteln
		_frame = new JFrameOperator(TestDialogApplication.TITLE);
	}
	
	/**
	 * Öffnet der Report-Dialog.
	 */
	public void createReportDialog(ReportPreferencesData rpd) {
		// Einstellungen an Applikation übergeben
		((TestDialogApplication)_frame.getSource()).setReportPreferences(rpd);
		
		// Dialog aufrufen
		new JButtonOperator(_frame,
				TestDialogApplication.DIALOG_REPORT).pushNoBlock();
		
		// Dialog ermitteln
		_dlg = new JDialogOperator(_frame, DlgReport.DIALOG_TITLE);
		
		// Button ermitteln
		_btnOk = new JButtonOperator(_dlg, "Report erstellen");
		_btnCancel = new JButtonOperator(_dlg, "Abbrechen");

		// Tab-Pane ermitteln
		_tabPane = new JTabbedPaneOperator(_dlg);
		_tabPane.selectPage("Kalender");
		
		// Radio-Buttons ermitteln
		_rbWeek = new JRadioButtonOperator(_dlg, "Wochenübersicht");
		_rbMonth = new JRadioButtonOperator(_dlg, "Monatsübersicht");
		_rbYear = new JRadioButtonOperator(_dlg, "Jahresübersicht");
		
		// Auswahl-Boxden ermitteln
		_cbYear = new JComboBoxOperator(_dlg, 1);
		_cbMonth = new JComboBoxOperator(_dlg, 0);
		
		// Zusätzliche Spalten auswählen
		_tabPane.selectPage("Monatsübersicht");
		_cbDateFrom = new JCheckBoxOperator(_dlg, "von");
		_cbDateTo = new JCheckBoxOperator(_dlg, "bis");
	}

	/**
	 * Führt den Test aus
	 */
	@Override
	public int runIt(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Ermittel aus den Einstellungen das Jahr.
	 * 
	 * @return Ermitteltes Jahr
	 */
	public int getPreferencesYear() {
		return ((DlgReport)_dlg.getSource()).getData().getYear();
	}

	/**
	 * Ermittelt aus den Einstellungen den Monat.
	 * 
	 * @return Ermittelter Monat
	 */
	public int getPreferencesMonth() {
		return ((DlgReport)_dlg.getSource()).getData().getMonth();
	}
	
	/**
	 * Gibt die angegebene gespeicherte Einstellunge zurück
	 * 
	 * @param key Welche Einstellung soll zurück gegeben werden?
	 * 
	 * @return Ausgewählte Einstellung
	 */
	public String getPreferences(String key) {
		return String.valueOf(
				((DlgReport)_dlg.getSource()).getData().getPreference(key));
	}
	
	/**
	 * Drückt auf den Buttln zum Abbrechen
	 */
	public void pushCancel() {
		_btnCancel.push();
	}
	
	/**
	 * Ermittelt aus den Einstellungen den Typ des Reports.
	 * 
	 * @return Ermittelter Typ
	 */
	public int getPreferencesType() {
		return ((DlgReport)_dlg.getSource()).getData().getType();
	}
	
	/**
	 * Ermittelt ob der Radio-Button für die Wochenübersicht ausgewählt ist oder
	 * nicht.
	 * 
	 * @return Radio-Button ausgewählt?
	 */
	public boolean isWeekChecked() {
		return _rbWeek.isSelected();
	}
	
	/**
	 * Ermittelt ob der Radio-Button für die Monatsübersicht ausgewählt ist oder
	 * nicht.
	 * 
	 * @return Radio-Button ausgewählt?
	 */
	public boolean isMonthChecked() {
		return _rbMonth.isSelected();
	}
	
	/**
	 * Ermittelt den Monat, der im Dialog ausgewählt wurde.
	 * 
	 * @return Monat, der ausgewählt wurde
	 */
	public int getMonth() {
		return _cbMonth.getSelectedIndex();
	}
	
	/**
	 * Ermittelt ob der Radio-Button für die Jahresübersicht ausgewählt ist oder
	 * nicht.
	 * 
	 * @return Radio-Button ausgewählt?
	 */
	public boolean isYearChecked() {
		return _rbYear.isSelected();
	}
	
	/**
	 * Ermittelt das Jahr, dass im Dialog ausgewählt wurde.
	 * 
	 * @return Jahr, das ausgewählt wurde
	 */
	public int getYear() {
		return (int)_cbYear.getSelectedItem();
	}
	
	/**
	 * Ermittelt die Anzahl der Einstellungen, die der Dialog gespeichert hat.
	 * 
	 * @return Anzahl gespeicherter Einstellungen
	 */
	public int getPreferencesCount() {
		return ((DlgReport)_dlg.getSource()).getData().getPreferenceCount();
	}
	
	/**
	 * Wählt den Monat im Auswahl-Feld aus.
	 * 
	 * @param month Monat, der aussgewählt werden soll
	 */
	public void setMonth(int month) {
		_cbMonth.setSelectedIndex(month);
	}
	
	/**
	 * Wählt das Jahr im Auswahl-Feld aus.
	 * 
	 * @param year Jahr, das ausgewählt werden soll
	 */
	public void setYear(int year) {
		_cbYear.setSelectedItem(Integer.valueOf(year));
	}

	/**
	 * Wählt den Radio-Button Monatsübersicht aus.
	 * 
	 * @param select Ausgewählt oder nicht.
	 */
	public void setTypeWeek(boolean select) {
		_rbWeek.setSelected(select);
	}
	
	/**
	 * Ermittelt ob die Check-Bos für die Spalte "von" ausgewählt ist.
	 * 
	 * @return Spalte "von" ausgewählt?
	 */
	public boolean isDateFromChecked() {
		return _cbDateFrom.isSelected();
	}
	
	/**
	 * Ermittelt ob die Check-Bos für die Spalte "bis" ausgewählt ist.
	 * 
	 * @return Spalte "bis" ausgewählt?
	 */
	public boolean isDateToChecked() {
		return _cbDateTo.isSelected();
	}
	
	/**
	 * Wählt die Spalte "von" aus.
	 * 
	 * @param select Soll die Spalte ausgewählt werden oder nicht?
	 */
	public void selectDateFrom(boolean select) {
		_cbDateFrom.setSelected(select);
	}
	

	public static void main(String[] args) {
		Test.main(new String[] {"tests.tests.dialogs.TestDlgReport"});
	}

}
