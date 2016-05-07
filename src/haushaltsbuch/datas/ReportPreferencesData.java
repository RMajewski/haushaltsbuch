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

import java.util.HashMap;
import java.util.Map;

/**
 * In dieser Klasse werden die einzelnen Einstellungen zur Report-Erstellung
 * gespeichert.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 1.0
 */
public class ReportPreferencesData {
	/**
	 * Wochenübersicht soll erstellt werden.
	 */
	public static final int TYPE_WEEK = 1;
	
	/**
	 * Monatsübersicht soll erstellt werden.
	 */
	public static final int TYPE_MONTH = 2;
	
	/**
	 * Jahresübersicht soll erstellt werden.
	 */
	public static final int TYPE_YEAR = 3;
	
	/**
	 * Übersicht über die Kategorien soll erstellt werden
	 */
	public static final int TYPE_CATEGORY = 4;
	
	/**
	 * Übersicht über die Geschäfte soll erstellt werden
	 */
	public static final int TYPE_SECTION = 5;
	
	/**
	 * Speichert das Ende
	 */
	private int _finished;

	/**
	 * Speichert den Monat
	 */
	private int _month;
	
	/**
	 * Speichert das Jahr
	 */
	private int _year;
	
	/**
	 * Speichert den Report-Type
	 */
	private int _type;
	
	/**
	 * Speichert die einzelnen Einstellungen
	 */
	private Map<String, Object> _preferences;

	/**
	 * Initalisiert dies Daten.
	 * 
	 * @param finished Wie wurde beendet?
	 * 
	 * @param month Monat, der eingestellt wurde
	 * 
	 * @param year Jahr, dass eingestellt wurde
	 * 
	 * @param type Report-Typ
	 */
	public ReportPreferencesData(int type, int finished, int month, int year) {
		_finished = finished;
		_month = month;
		_year = year;
		_type = type;
		_preferences = new HashMap<String, Object>();
	}
	
	/**
	 * Gibt den Grund des Ende zurück.
	 * 
	 * @return Grund des Endes
	 */
	public int getFinished() {
		return _finished;
	}

	/**
	 * Setzt den Grund des Ende.
	 * 
	 * @param finished Neuer Grund des Endes
	 */
	public void setFinished(int finished) {
		_finished = finished;
	}

	/**
	 * Gibt den Monat zurück.
	 * 
	 * @return Monat, der gespeichert ist
	 */
	public int getMonth() {
		return _month;
	}

	/**
	 * Setzt den Monat neu.
	 * 
	 * @param month Neuer Monat
	 */
	public void setMonth(int month) {
		_month = month;
	}

	/**
	 * Gibt das gespeicherte Jahr zurück.
	 * 
	 * @return Jahr, das gespeichert ist
	 */
	public int getYear() {
		return _year;
	}

	/**
	 * Setzt das Jahr neu
	 * 
	 * @param year Neues Jahr
	 */
	public void setYear(int year) {
		_year = year;
	}
	
	/**
	 * Gibt den gespeicherten Report-Typ zurück.
	 * 
	 * @return Report-Typ
	 */
	public int getType() {
		return _type;
	}
	
	/**
	 * Setzt den neuen Report-Typ
	 * 
	 * @param type Neuer Report-Type
	 */
	public void setType(int type) {
		_type = type;
	}
	
	/**
	 * Fügt einen neue Einstellung ein.
	 * 
	 * @param key Name der Einstellung
	 * 
	 * @param preference Einstellung, die gespeichert werden soll.
	 */
	public void setPreference(String key, Object preference) {
		if ((key != null) && !key.isEmpty() && (preference != null))
			_preferences.put(key, preference);
	}
	
	/**
	 * Liest die angegebene Einstellung aus und gibt diese zurück.
	 * 
	 * @param key Name der Einstellung, die ermittelt werden soll
	 * 
	 * @return Ausgelesene Einstellung
	 */
	public Object getPreference(String key) {
		if (key == null || key.isEmpty())
			return null;
		return _preferences.get(key);
	}
	
	/**
	 * Löscht die angegebene Einstellung aus der Liste.
	 * 
	 * @param key Einstellung, die gelöscht werden soll
	 */
	public void removePreference(String key) {
		_preferences.remove(key);
	}
	
	/**
	 * Gibt die Anzahl gespeicherter Einstellungen zurück.
	 * 
	 * @return Anzahl gespeicherter Einstellungen
	 */
	public int getPreferenceCount() {
		return _preferences.size();
	}
}
