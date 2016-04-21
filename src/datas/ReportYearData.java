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

import javax.swing.table.TableColumnModel;

/**
 * Speichert die Daten, die im Report für die Jahresübersicht angezeigt werden
 * soll.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ReportYearData extends ReportData {

	/**
	 * Initalisiert die Daten.
	 * 
	 * @param preferences Einstellungen, die vorgenommen wurden.
	 */
	public ReportYearData(ReportPreferencesData preferences) {
		// Klasse initalisieren
		super(preferences);
		
		// Daten ermitteln
		setPreferences(preferences);
	}

	/**
	 * Gibt die Anzahl der Spalten zurück.
	 * 
	 * @return Anzahl der Spalten
	 */
	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Gibt die Anzahl der Zeilen zurück.
	 * 
	 * @return Anzahl der Zeilen
	 */
	@Override
	public int getRowCount() {
		return 12;
	}

	/**
	 * Setzt die Spalten-Überschriften.
	 * 
	 * @param tcm Spalten-Model der Tabelle
	 */
	@Override
	public void setColumnHeader(TableColumnModel tcm) {
		tcm.getColumn(0).setHeaderValue("Monat");
		tcm.getColumn(1).setHeaderValue("Einnahmen");
		tcm.getColumn(2).setHeaderValue("Ausgaben");
		tcm.getColumn(3).setHeaderValue("Differenz");
	}

	/**
	 * Gibt den angegeben Monats-Namen zurück.
	 * 
	 * @param month Monat, dessen Name ermittelt werden soll.
	 * 
	 * @return Wenn der Monats-Index außerhalb des Bereiches ist, so wird eine
	 * leere Zeichenkette zurück gegeben. Ist der Index innerhalb des Bereiches,
	 * so wird der Name des Monats zurück gegeben.
	 */
	public String getMonthName(int month) {
		switch(month) {
			case 0:
				return "Januar";
				
			case 1:
				return "Februar";
				
			case 2:
				return "März";
				
			case 3:
				return "April";
				
			case 4:
				return "Mai";
				
			case 5:
				return "Juni";
						
			case 6:
				return "Juli";
				
			case 7:
				return "August";
				
			case 8:
				return "September";
				
			case 9:
				return "Oktober";
				
			case 10:
				return "November";
				
			case 11:
				return "Dezember";
		}
		
		// month außerhalb des Bereiches
		return new String();
	}
	
	@Override
	public void setPreferences(ReportPreferencesData preferences) {
		// Einstellungen speichern
		super.setPreferences(preferences);
	}
}
