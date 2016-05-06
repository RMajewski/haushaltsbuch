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

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumnModel;

/**
 * Von dieser Klasse werden die Klassen abgeleitet, die für die einzelnen
 * Reports die Daten bereitstellen. In dieser Klasse werden lediglich die
 * Methoden implementiert, die in jeder abgeleiteten Klasse gleich sind.
 * Außerdem werden Methoden definiert, die in den abgeleiteten Klassen
 * implementiert werden müssen.
 * 
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public abstract class ReportData {
	
	/**
	 * Speichert die einzelnen Einnahmen
	 */
	protected List<Double> _in;
	
	/**
	 * Speichert die einzelnen Ausgaben
	 */
	protected List<Double> _out;
	
	/**
	 * Speichert die Einstellungen
	 */
	protected ReportPreferencesData _preferences;
	
	/**
	 * Initalisiert die einzelnen Daten
	 */
	public ReportData(ReportPreferencesData preferences) {
		// Listen initalisieren
		_in = null;
		_out = null;
		
		// Einstellungen speichern
		_preferences = preferences;
	}
	
	/**
	 * Gibt das als long angegebene Datum als Zeichenketten zurück.
	 * 
	 * @param date Long-Wert, der in eine lesbare Zeichenkette umgewandelt
	 * werden soll.
	 * 
	 * @return Lesbares Datum als Zeichenkette
	 */
	protected String getDateAsString(long date) {
		return DateFormat.getDateInstance(DateFormat.MEDIUM).format(
				new Date(date));
	}
	
	/**
	 * Initalisiert eine Double-Liste. Es wird die Anzahl der Datensätze mit 0
	 * vorbelegt, wie sie im Parameter angegeben wurden.
	 * 
	 * @param count Anzahl an vorbelegten Datensätzen
	 */
	protected List<Double> initDoubleList(int count) {
		// Liste initalisieren
		List<Double> ret = new ArrayList<Double>();
		
		// Datensätze erzeugen
		for (int i= 0; i < count; i++)
			ret.add(0.0);
		
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
	 * Gibt den ausgewählten Monat wieder
	 * 
	 * @return Ausgewählter Monat
	 */
	public int getMonth() {
		return _preferences.getMonth();
	}

	/**
	 * Speichert die neuen Einstellungen und weist an, dass die Daten neu
	 * eingelesen werden.
	 */
	public void setPreferences(ReportPreferencesData preferences) {
		_preferences = preferences;
	}
	
	/**
	 * Gibt die gespeicherten Einstellungen zurück.
	 * 
	 * @return Gespeicherte Einstellungen
	 */
	public ReportPreferencesData getPreferences() {
		return _preferences;
	}

	/**
	 * Ermittelt aus den Einstellungen, wie viele Spalten ausgegeben werden
	 * müssen.
	 * 
	 * @return Anzahl an Spalten
	 */
	public abstract int getColumnCount();

	/**
	 * Ermittelt aus den Einstellungen, wie viele Zeilen ausgegeben werden
	 * müssen.
	 * 
	 * @return Anzahl an Zeilen
	 */
	public abstract int getRowCount();
	
	/**
	 * Setzt die Spalten-Überschriften.
	 * 
	 * @param tcm Spalten-Modell der Tabelle
	 */
	public abstract void setColumnHeader(TableColumnModel tcm);	
	
	/**
	 * Gibt die Einnahmen für die angegeben Index zurück.
	 * 
	 * @param index Index, für die die Einnahmen ermittelt werden sollen
	 * 
	 * @return Einnahmen des angegeben Indexes
	 */
	public double incoming(int index) {
		if (_in != null)
			return _in.get(index);
		return 0;
	}
	
	/**
	 * Gibt die Ausgaben für den angegeben Index zurück.
	 * 
	 * @param index Index, für die die Ausgaben ermittelt werden sollen
	 * 
	 * @return Ausgaben der angebenen Woche
	 */
	public double outgoing(int index) {
		if (_out != null)
			return _out.get(index);
		return 0;
	}
	
	/**
	 * Berechnet die Differenz von Einnahmen und Ausgaben für den angegebenen
	 * Index.
	 * 
	 * @param index Index, für die die Differenz berechnet werden soll.
	 * 
	 * @return Gibt die Differenz von Einnahmen und Ausgaben zurück.
	 */
	public double deviation(int index) {
		return incoming(index) - outgoing(index);
	}
}
