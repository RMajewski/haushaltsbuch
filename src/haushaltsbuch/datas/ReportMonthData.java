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

package haushaltsbuch.datas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.table.TableColumnModel;

import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.helper.HelperCalendar;

/**
 * Speichert die Daten, die im Report Monatsübersicht angezeigt werden sollen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ReportMonthData extends ReportData {

	/**
	 * Initalisiert die einzelnen Daten.
	 * 
	 * @param preferences Einstellungen für den Report
	 */
	public ReportMonthData(ReportPreferencesData preferences) {
		super(preferences);
		
		setPreferences(preferences);
	}

	/**
	 * Gibt die Anzahl der Spalten zurück
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}
	
	/**
	 * Gibt die Anzahl der Zeilen zurück. Es werden für den ausgewählten Monat
	 * die Anzahl der Tage ermittelt und diese zurück gegeben.
	 */
	@Override
	public int getRowCount() {
		GregorianCalendar gc = HelperCalendar.createCalendar(
				_preferences.getYear());
		gc.set(GregorianCalendar.MONTH, _preferences.getMonth());
		return gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * Setzt die einzelnen Spalten-Überschriften.
	 * 
	 * @param tcm Model für die Kopf-Zeilen
	 */
	@Override
	public void setColumnHeader(TableColumnModel tcm) {
		tcm.getColumn(0).setHeaderValue("Tag");
		tcm.getColumn(1).setHeaderValue("Einnahmen");
		tcm.getColumn(2).setHeaderValue("Ausgaben");
		tcm.getColumn(3).setHeaderValue("Differenz");
	}

	/**
	 * Ermittelt aus den angegeben Index den Tag und gibt diesen als lesbare 
	 * Zeichenkette zurück. Sollte der Index außerhalb des Bereiches sein, in
	 * den die Tage liegen, wird <b>null</b> zurück gegeben.
	 * 
	 * @param index Index, von dem der Tag ermittelt werden soll.
	 * 
	 * @return Ermittelter Tag als zeichenkette
	 */
	public Object getDay(int index) {
		GregorianCalendar gc = 
				HelperCalendar.createCalendar(_preferences.getYear());
		gc.set(GregorianCalendar.MONTH, _preferences.getMonth());
		if ((0 <= index) && (index < 
				gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH))) {
			gc.set(GregorianCalendar.DAY_OF_MONTH, index + 1);
			return HelperCalendar.dateToString(gc.getTimeInMillis());
		}
		
		// Standard Rückgabe
		return null;
	}

	/**
	 * Speichert die neuen Einstellungen. Außerdem werden die Daten eingelesen.
	 * 
	 * @param preferences Neue Einstellungen
	 */
	@Override
	public void setPreferences(ReportPreferencesData preferences) {
		// Neue Einstellungen speichern
		super.setPreferences(preferences);
		
		// Monat ermitteln, für den die Übersicht erstellt werden soll
		GregorianCalendar gc = HelperCalendar.createCalendar(
				_preferences.getYear());
		gc.set(GregorianCalendar.MONTH, _preferences.getMonth());
		int days = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		// Listen für Einnahmen und Ausgaben initalisieren
		_in = initDoubleList(days);
		_out = initDoubleList(days);
		
		// Schleife über alle Tage
		for (int i = 0; i < days; i++) {
			try {
				// Long-Wert ermitteln
				gc.set(GregorianCalendar.DAY_OF_MONTH, i + 1);
				long day = gc.getTimeInMillis();
				
				// Einnahmen ermitteln
				Statement stm = DbController.getInstance().createStatement();
				ResultSet rsw = stm.executeQuery(DbController.queries().money().selectDay(day, MoneyData.INT_INCOMING));
				double d = 0;
				while(rsw.next()) {
					Statement stm2 = DbController.getInstance().createStatement();
					ResultSet rs = stm2.executeQuery(DbController.queries().moneyDetails().sumMoneyId(rsw.getInt("id")));
					d += rs.getDouble(1);
					rs.close();
				}
				if (d > 0)
					_in.set(i, d);
				rsw.close();
				
				// Ausgaben ermitteln
				stm = DbController.getInstance().createStatement();
				rsw = stm.executeQuery(DbController.queries().money().selectDay(day, MoneyData.INT_OUTGOING));
				d = 0;
				while(rsw.next()) {
					Statement stm2 = DbController.getInstance().createStatement();
					ResultSet rs = stm2.executeQuery(DbController.queries().moneyDetails().sumMoneyId(rsw.getInt("id")));
					d += rs.getDouble(1);
					rs.close();
				}
				if (d > 0)
					_out.set(i, d);
				rsw.close();
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(DbController.statusDbError(), e);
			}
		}
	}
}
