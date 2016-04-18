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

package datas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.BeforeClass;

import db.DbController;
import elements.StatusBar;

/**
 * Speichert die Daten, die im Report angezeigt werden sollen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ReportWeekData {
	/**
	 * Gibt den Namen für die Einstellung zum anzeigen der einzelnen Kategorien
	 * an.
	 */
	public static final String DRAW_CATEGORIES = 
			new String("Week.drawCategories");
	
	/**
	 * Gibt den Namen für die Einstellung zum anzeigen der einzelnen Geschäfte
	 * an.
	 */
	public static final String DRAW_SECTIONS = new String("Week.drawSections");
	/**
	 * Speichert die Einstellungen
	 */
	private ReportPreferencesData _preferences;
	
	/**
	 * Speichert die Anzahl der Wochen für das ausgewählte Jahr
	 */
	private int _weekCount;
	
	/**
	 * Speichert die einzelnen Wochennummern
	 */
	private List<String> _weeks;
	
	/**
	 * Initalisiert die Daten.
	 * 
	 * @param preferences Einstellungen für den Report.
	 */
	public ReportWeekData(ReportPreferencesData preferences) {
		// Liste für die Wochennummern initalisieren
		_weeks = new ArrayList<String>();
		_weekCount = 0;

		// Einstellungen speichern und Daten ermitteln
		setPreferences(preferences);
}

	/**
	 * Gibt das ausgewählte Jahr wieder
	 * 
	 * @return Ausgewählte Jahr
	 */
	public int getYear() {
		return _preferences.getYear();
	}
	
	/**
	 * Gibt die Anzahl der Wochen für das ausgewählte Jahr zurück.
	 */
	public int getWeekCount() {
		return _weekCount;
	}
	
	/**
	 * Gibt die Wochen für die angegebene Zeile wieder.
	 * 
	 * @param row Zeile, für die die Wochennummer ermittelt werden soll
	 * 
	 * @return Wochennummer der angegeben Zeile
	 */
	public String getWeekNumber(int row) {
		return _weeks.get(row);
	}
	
	/**
	 * Speichert die neuen Einstellungen und weist an, dass die Daten neu
	 * eingelesen werden.
	 */
	public void setPreferences(ReportPreferencesData preferences) {
		// Neue Einstellungen speichern
		_preferences = preferences;
		
		// Anzahl der Wochen ermitteln
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.YEAR, _preferences.getYear());
		_weekCount = gc.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);
		
		// Wochennummern speichern
		for (int i = 1; i <= _weekCount; i++)
			_weeks.add(String.valueOf(i));
	}

	/**
	 * Ermittelt aus den Einstellungen, wie viele Spalten ausgegeben werden
	 * müssen.
	 * 
	 * @return Anzahl an Spalten
	 */
	public int getColumnCount() {
		// 4 Spalten für die Wochennummern, Einnahmen und Ausgaben und Differenz
		int ret = 4;
		
		try {
			// Datenbankabfrage vorbereiten
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs;
			
			// Sollen die Kategorien mit eingefügt werden?
			if (_preferences.getPreference(DRAW_CATEGORIES) != null) {
				rs = stm.executeQuery(DbController.queries().category().count());
				ret += rs.getInt(1);
			}
			
			// Sollen die Geschäfte mit eingefügt werden?
			if (_preferences.getPreference(DRAW_SECTIONS) != null) {
				rs = stm.executeQuery(DbController.queries().section().count());
				ret += rs.getInt(1);
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.getStackTrace();
		}
		
		// Ermittelte Spalten zurück geben
		return ret;
	}
}
