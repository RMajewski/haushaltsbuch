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

import fit.ColumnFixture;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.helper.HelperCalendar;
import tests.tests.dialogs.TestDlgReport;

/**
 * Implementiert die Schnittstelle damit das Spalten-Fixture von DlgReport.fit
 * ausgeführt werden kann.
 * 
 * @author René Majewski
 *
 * @version 0,1
 * @since 0.2
 */
public class FixtureDlgReportColumn extends ColumnFixture {
	/**
	 * Eingabe des Monates
	 */
	public String month;
	
	/**
	 * Eingabe des Jahres
	 */
	public String year;
	
	/**
	 * Eingabe des Types
	 */
	public String type;
	
	/**
	 * Speichert die Test-Klasse
	 */
	private TestDlgReport _test;
	
	/**
	 * Initalisiert die Klasse
	 */
	public FixtureDlgReportColumn() {
		// Klasse initalisieren
		super();
		
		// Test-Klasse initalisieren
		try {
			_test = new TestDlgReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Übergibt die eingegebenen Daten an die Test-Applikation und öffnet einen
	 * neuen Dialog. Der Dialog wird beendet und die gespeichertenen
	 * Einstellungen ausgelesen. Es wird hier der Monat zurück gegeben.
	 */
	public String getMonth() {
		// Monat ermitteln
		int m = HelperCalendar.enStringToMonth(month);
		
		// Jahr ermitteln
		int y = Integer.valueOf(year);
		
		// Typ ermitteln
		int t = 0;
		switch(type) {
			case "Week":
				t = ReportPreferencesData.TYPE_WEEK;
				break;
				
			case "Month":
				t = ReportPreferencesData.TYPE_MONTH;
				break;
				
			case "Year":
				t = ReportPreferencesData.TYPE_YEAR;
				break;
		}
		
		// Einstellungen erstellen
		ReportPreferencesData rpd = new ReportPreferencesData(t, 0, m, y);
		
		// Dialog aufrufen
		_test.createReportDialog(rpd);
		
		// "Report erstellen"-Button drücken
		_test.pushOk();
		
		// Aus den Einstellungen den Monat ermitteln
		return HelperCalendar.enMonthToString(_test.getPreferencesMonth());
	}
	
	/**
	 * Ermittelt aus den Einstellungen das Jahr
	 */
	public String getYear() {
		return String.valueOf(_test.getPreferencesYear());
	}
	
	/**
	 * Ermittelt aus den Einstellungen den Type
	 */
	public String getType() {
		switch(_test.getPreferencesType()) {
			case ReportPreferencesData.TYPE_MONTH:
				return "Month";
				
			case ReportPreferencesData.TYPE_WEEK:
				return "Week";
				
			case ReportPreferencesData.TYPE_YEAR:
				return "Year";
		}
		return null;
	}
	
	/**
	 * Ermittelt ob der Radio-Button für die Wochenübersicht ausgewählt ist oder
	 * nicht.
	 * 
	 * @return Radio-Button der Wochenübersicht ausgewählt?
	 */
	public String isWeekChecked() {
		return String.valueOf(_test.isWeekChecked());
	}
	
	/**
	 * Ermittelt ob der Radio-Button für die Monatsübersicht ausgewählt ist oder
	 * nicht.
	 * 
	 *  @return Radio-Button der Monatsübersicht ausgewählt?
	 */
	public String isMonthChecked() {
		return String.valueOf(_test.isMonthChecked());
	}
	
	/**
	 * Ermittelt ob der Radio-Button für die Jahresübersicht ausgewählt ist oder
	 * nicht.
	 * 
	 * @return Radio-Button für die Jahresübersicht ausgewählt?
	 */
	public String isYearChecked() {
		return String.valueOf(_test.isYearChecked());
	}
}
