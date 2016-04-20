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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.swing.table.TableColumnModel;

import db.DbController;
import elements.StatusBar;
import helper.HelperCalendar;

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
	 * Gibt den Namen für die Einstellung zum anzeigen der Spalte 'von' an.
	 */
	public static final String DRAW_DATE_FROM = new String("Week.drawDateFrom");
	
	/**
	 * Gibt den Namen für die Einstellung zum anzeigen der Spalte 'bis' an.
	 */
	public static final String DRAW_DATE_TO = new String("Week.drawDateTo");
	
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
	 * Speichert die einzelnen Einnahmen
	 */
	private List<Double> _in;
	
	/**
	 * Speichert die einzelnen Ausgaben
	 */
	private List<Double> _out;
	
	/**
	 * Initalisiert die Daten.
	 * 
	 * @param preferences Einstellungen für den Report.
	 */
	public ReportWeekData(ReportPreferencesData preferences) {
		// Liste für die Wochennummern initalisieren
		_weeks = new ArrayList<String>();
		_weekCount = 0;
		
		// Liste für die Einnahmen und die Ausgaben vorbereiten
		_in = null;
		_out = null;

		// Einstellungen speichern und Daten ermitteln
		setPreferences(preferences);
	}
	
	/**
	 * Initalisiert die angegebene Liste und füllt sie mit 0.0 für die Anzahl
	 * der Wochen.
	 */
	private List<Double> initDoubleList() {
		// Liste initalisieren?
		List<Double> ret = new ArrayList<Double>();
		
		// Liste neu aufbauen
		for (int i = 0; i <= _weekCount; i++) {
			ret.add(Double.valueOf(0));
		}
		
		// Liste zurück geben
		return ret;
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
		GregorianCalendar gc = HelperCalendar.createCalendar(
				_preferences.getYear());

		_weekCount = gc.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR) + 1;
		
		// Listen für die Einnahmen und die Ausgaben initalisieren
		_in = initDoubleList();
		_out = initDoubleList();
		
		// Schleife über alle Wochen
		for (int i = 0; i <= _weekCount; i++) {
			// Wochennummern
			_weeks.add(String.valueOf(i));
			
			// Einnahmen für die Woche
			long from = getDateFromAsLong(i);
			long to = getDateToAsLong(i);
			
			try {
				//FIXME In eine private Methode packen
				// Einnahmen
				Statement stm = DbController.getInstance().createStatement();
				ResultSet rsw = stm.executeQuery(DbController.queries().money().selectWeek(from, to, MoneyData.INT_INCOMING));
				double d = 0;
				while(rsw.next()) {
					Statement stm2 = DbController.getInstance().createStatement();
					ResultSet rs = stm2.executeQuery(DbController.queries().moneyDetails().sum(rsw.getInt("id")));
					d += rs.getDouble(1);
					rs.close();
				}
				if (d > 0)
					_in.set(i, d);
				rsw.close();
				
				// Ausgaben
				d = 0;
				rsw = stm.executeQuery(DbController.queries().money().selectWeek(from, to, MoneyData.INT_OUTGOING));
				while(rsw.next()) {
					Statement stm2 = DbController.getInstance().createStatement();
					ResultSet rs = stm2.executeQuery(DbController.queries().moneyDetails().sum(rsw.getInt("id")));
					d += rs.getDouble(1);
					rs.close();
				}
				if (d > 0)
					_out.set(i, d);
				rsw.close();
			} catch(SQLException e) {
				StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
				e.printStackTrace();
			}
			
		}
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
		
		// Soll die Spalte "von" mit ausgegeben werden?
		if (_preferences.getPreference(DRAW_DATE_FROM) != null)
			ret++;
		
		// Soll die Spalte "bis" mit ausgegeben werden?
		if (_preferences.getPreference(DRAW_DATE_TO) != null)
			ret++;
		
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
	
	/**
	 * Setzt die Spalten-Überschriften.
	 * 
	 * @param tcm Spalten-Modell der Tabelle
	 */
	public void setColumnHeader(TableColumnModel tcm) {
		// Spalten-Nummer
		int column = 0;
		
		// Spalte für die Wochennummern
		tcm.getColumn(column++).setHeaderValue("Woche");
		
		// Soll von mit ausgegeben werden?
		if (_preferences.getPreference(DRAW_DATE_FROM) != null)
			tcm.getColumn(column++).setHeaderValue("von");
		
		// Soll bis mit ausgegeben werden?
		if (_preferences.getPreference(DRAW_DATE_TO) != null)
			tcm.getColumn(column++).setHeaderValue("bis");
		
		// Spalten für die Einnahmen, die Ausgaben und die Differenz
		tcm.getColumn(column++).setHeaderValue("Einnahmen");
		tcm.getColumn(column++).setHeaderValue("Ausgaben");
		tcm.getColumn(column++).setHeaderValue("Differenz");
		
		// Überprüfen ob die Kategorien ausgegeben werden sollen
		try {
			// Datenbankabfrage vorbereiten
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs;
			
			// FIXME in seperate Methode packen und jeweils aufrufen
			
			// Sollen die Kategorien mit eingefügt werden?
			if (_preferences.getPreference(DRAW_CATEGORIES) != null) {
				rs = stm.executeQuery(
						DbController.queries().category().sort("name"));
				while (rs.next()) {
					tcm.getColumn(column++).setHeaderValue(rs.getString("name"));
				}
			}
			
			// Sollen die Geschäfte mit eingefügt werden?
			if (_preferences.getPreference(DRAW_SECTIONS) != null) {
				rs = stm.executeQuery(
						DbController.queries().section().sort("name"));
				while (rs.next()) {
					tcm.getColumn(column).setHeaderValue(rs.getString("name"));
					column++;
				}
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.getStackTrace();
		}
	}
	
	/**
	 * Gibt die Einnahmen für die angegeben Woche zurück.
	 * 
	 * @param week Woche, für die die Einnahmen ermittelt werden sollen
	 * 
	 * @return Einnahmen der angebenen Woche
	 */
	public double incoming(int week) {
		return _in.get(week);
	}
	
	/**
	 * Gibt die Ausgaben für die angegeben Woche zurück.
	 * 
	 * @param week Woche, für die die Ausgaben ermittelt werden sollen
	 * 
	 * @return Ausgaben der angebenen Woche
	 */
	public double outgoing(int week) {
		return _out.get(week);
	}
	
	/**
	 * Berechnet die Differenz von Einnahmen und Ausgaben für die angegebene
	 * Woche.
	 * 
	 * @param week Woche, für die die Differenz berechnet werden soll.
	 * 
	 * @return Gibt die Differenz von Einnahmen und Ausgaben zurück.
	 */
	public double deviation(int week) {
		return incoming(week) - outgoing(week);
	}
	
	/**
	 * Gibt Zurück, ob Spalte 'von' angezeigt werden soll oder nicht.
	 * 
	 * @return Soll Spalte 'von' angezeigt werden?
	 */
	public boolean drawDateFrom() {
		if ((_preferences.getPreference(DRAW_DATE_FROM) == null) ||
				((int)_preferences.getPreference(DRAW_DATE_FROM) == 0))
			return false;
		
		return true;
	}
	
	/**
	 * Gibt zurück, ob die Spalte 'bis' angezeigt werden soll oder nicht.
	 * 
	 * @return Soll Spalte 'bis' angezeigt werden?
	 */
	public boolean drawDateTo() {
		if ((_preferences.getPreference(DRAW_DATE_TO) == null) ||
				((int)_preferences.getPreference(DRAW_DATE_TO) == 0))
			return false;
		
		return true;
	}
	
	/**
	 * Ermittel für die angegebene Woche das Datum des ersten Wochentages und
	 * gibt die als <b>long</b> zurück.
	 * 
	 * @param week Woche, von der der 1. Wochentag ermittelt werden soll.
	 * 
	 * @return 1. Wochentag der angegebenen Woche als <b>long</b>.
	 */
	public long getDateFromAsLong(int week) {
		if (week > -1) {
			GregorianCalendar gc = HelperCalendar.createCalendar(
					_preferences.getYear());

			if (week > 0) {
				gc.set(GregorianCalendar.WEEK_OF_YEAR, week);
				gc.set(GregorianCalendar.DAY_OF_WEEK, 2);
			}

			return gc.getTimeInMillis();
		}
		
		return 0;
	}
	
	/**
	 * Ermittelt für die angegebene Woche das Datum des ersten Wochentages und
	 * gibt dies als lesbare Zeichenkette zurück.
	 * 
	 * @param week Woche, von der der 1. Wochentag ermittelt werden soll.
	 * 
	 * @return 1. Wochentag der angegeben Woche als Zeichenkette.
	 */
	public String getDateFrom(int week) {
		if (week > -1)
			return DateFormat.getDateInstance(DateFormat.MEDIUM).format(
					new Date(getDateFromAsLong(week)));
		
		return new String();
	}
	
	/**
	 * Ermittel für die angegebene Woche das Datum des letzten Wochentages und
	 * gibt die als <b>long</b> zurück.
	 * 
	 * @param week Woche, von der der letzter Wochentag ermittelt werden soll.
	 * 
	 * @return Letzter Wochentag der angegebenen Woche als <b>long</b>.
	 */
	public long getDateToAsLong(int week) {
		if (week > -1) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, _preferences.getYear());
			gc.set(GregorianCalendar.WEEK_OF_YEAR, week);
			gc.set(GregorianCalendar.DAY_OF_WEEK, 1);
			return gc.getTimeInMillis();
		}
		
		return 0;
	}
	
	/**
	 * Ermittelt für die angegebene Woche das Datum des letzten Wochentages und
	 * gibt dies als lesbare Zeichenkette zurück.
	 * 
	 * @param week Woche, von der der letzte Wochentag ermittelt werden soll.
	 * 
	 * @return Letzer Wochentag der angegeben Woche.
	 */
	public String getDateTo(int week) {
		if (week > -1)
			return DateFormat.getDateInstance(DateFormat.MEDIUM).format(
					new Date(getDateToAsLong(week)));
		
		return new String();
	}
}
