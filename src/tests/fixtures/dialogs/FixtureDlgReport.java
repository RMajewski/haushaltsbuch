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

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.GregorianCalendar;

import fit.ActionFixture;
import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportWeekData;
import haushaltsbuch.helper.HelperCalendar;
import tests.tests.dialogs.TestDlgReport;

/**
 * Implementiert die Aktionen für DlgReport.fit.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class FixtureDlgReport extends FixtureDialogs {
	/**
	 * Speichert die Report-Einstellungen
	 */
	private ReportPreferencesData _rpd;
	
	/**
	 * Initalisiert die Klasse
	 */
	public FixtureDlgReport() {
		try {
			_test = new TestDlgReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Einstellungen für den Report-Dialog
		_rpd = new ReportPreferencesData(ReportPreferencesData.TYPE_YEAR, 0, -1,
				-1);
		
		// Dialog aufrufen
		((TestDlgReport)_test).createReportDialog(_rpd);
	}
	
	/**
	 * Ermittelt, ob die Wochenübersicht ausgewählt ist.
	 * 
	 * @return Ist die Wochenübersicht ausgewählt?
	 */
	public String isWeekChecked() {
		return String.valueOf(((TestDlgReport)_test).isWeekChecked());
	}
	
	/**
	 * Ermittelt, ob die Monatsübersicht ausgewählt ist.
	 * 
	 * @return Ist die Monatsübersicht ausgewählt?
	 */
	public String isMonthChecked() {
		return String.valueOf(((TestDlgReport)_test).isMonthChecked());
	}
	
	/**
	 * Ermittelt, ob die Jahresübersicht ausgewählt ist.
	 * 
	 * @return Ist die Jahresübersicht ausgewählt?
	 */
	public String isYearChecked() {
		return String.valueOf(((TestDlgReport)_test).isYearChecked());
	}
	
	/**
	 * Bricht den Dialog mit beenden ab.
	 */
	public void pushCancel() {
		((TestDlgReport)_test).pushCancel();
	}
	
	/**
	 * Überprüft, ob im Dialog das aktuelle Jahr ausgewählt wurde. Wurde es
	 * ausgewählt, so wird <b>true</b> zurück gegeben. Wurde es nicht
	 * ausgewählt, so wird <b>false</b> zurück gegeben.
	 * 
	 * @return Wurde das aktuelle Jahr ausgewählt?
	 */
	public String getYearActual() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		
		if (gc.get(GregorianCalendar.YEAR) == ((TestDlgReport)_test).getYear())
			return "true";
		else
			return "false";
	}
	
	/**
	 * Überprüft, ob im Dialog der aktuelle Monat ausgewählt wurde. Wurde er
	 * ausgewählt, so wird <b>true</b> zurück gegeben. Wurde er nicht
	 * ausgewählt, so wird <b>false</b> zurück gegeben.
	 * 
	 * @return Wurde das aktuelle Jahr ausgewählt?
	 */
	public String getMonthActual() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		
		if (gc.get(GregorianCalendar.MONTH) == ((TestDlgReport)_test).getMonth())
			return "true";
		else
			return "false";
	}
	
	/**
	 * Ermittelt die Anzahl der gespeicherten Einstellungen.
	 * 
	 * @return Anzahl der Einstellungen
	 */
	public String getPreferencesCount() {
		return String.valueOf(((TestDlgReport)_test).getPreferencesCount());
	}
	
	/**
	 * Wählt das Jahr aus.
	 * 
	 * @param year Jahr, was ausgewählt werden soll.
	 */
	public void setYear(String year) {
		((TestDlgReport)_test).setYear(Integer.valueOf(year));
	}
	
	/**
	 * Wählt den Monat aus
	 * 
	 * @param month Monat, der ausgewählt werden soll.
	 */
	public void setMonth(String month) {
		((TestDlgReport)_test).setMonth(HelperCalendar.enStringToMonth(month));
	}
	
	/**
	 * Wählt die Wochenübersicht aus.
	 */
	public void setWeek(boolean select) {
		((TestDlgReport)_test).setTypeWeek(select);
	}
	
	/**
	 * Wählt die Spalte "von" aus.
	 * 
	 * @param select
	 */
	public void setDateFrom(boolean select) {
		((TestDlgReport)_test).selectDateFrom(select);
	}
	
	/**
	 * Ermittelt ob die Spalte "von" ausgewählt ist.
	 * 
	 * @return Ist "von" ausgewählt?
	 */
	public String isDateFromChecked() {
		return String.valueOf(((TestDlgReport)_test).isDateFromChecked());
	}
	
	/**
	 * Ermittelt obe die Spalte "bis" ausgewählt ist.
	 * 
	 * @return Ist "bis" ausgewählt?
	 */
	public String isDateToChecked() {
		return String.valueOf(((TestDlgReport)_test).isDateToChecked());
	}
	
	/**
	 * Drückt den Button "Report erstellen"
	 */
	public void pushCreateReport() {
		pushOk();
	}
	
	/**
	 * Ermittelt welches Jahr in den Einstellungen gespeichert wurde.
	 * 
	 * @return Jahr, das in den Einstellungen gespeichert ist.
	 */
	public String preferenceYear() {
		return String.valueOf(((TestDlgReport)_test).getPreferencesYear());
	}
	
	/**
	 * Ermittelt, welcher Monat in den Einstellungen gespeichert wurde.
	 * 
	 * @return Monat, der in den Einstellungen gespeichert ist.
	 */
	public String preferenceMonth() {
		return HelperCalendar.enMonthToString(
				((TestDlgReport)_test).getPreferencesMonth());
	}
	
	/**
	 * Ermittelt, welcher Report-Typ in den Einstellungen gespeichert wurde.
	 * 
	 * @return Report-Typ, der in den Einstellungen gespeichert ist.
	 */
	public String preferenceType() {
		switch (((TestDlgReport)_test).getPreferencesType()) {
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
	 * Ermittelt, ob die Spalte "von" in dein Einstellungen als ausgewählt
	 * gespeichert wurde.
	 * 
	 * @return Gespeicherter Wert der Einstellung Spalte "von"
	 */
	public String preferenceDateFrom() {
		return ((TestDlgReport)_test).getPreferences(
				ReportWeekData.DRAW_DATE_FROM);
	}
	
	/**
	 * Ermittelt, ob die Spalte "bis" in dein Einstellungen als ausgewählt
	 * gespeichert wurde.
	 * 
	 * @return Gespeicherter Wert der Einstellung Spalte "bis"
	 */
	public String preferenceDateTo() {
		return ((TestDlgReport)_test).getPreferences(
				ReportWeekData.DRAW_DATE_TO);
	}
}
