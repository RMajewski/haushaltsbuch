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

import java.awt.Color;

/**
 * Speichert die Daten des Logbuches.
 * 
 * @author René Majewski
 */
public class LogData {
	/**
	 * Speichert die Nachricht, die ausgegeben werden soll
	 */
	private String _message;
	
	/**
	 * Speichert, wie die Nachricht dargestellt werden soll
	 */
	private short _out;
	
	/**
	 * Gibt an, dass die Nachricht nicht ausgegeben werden soll
	 */
	public static final short NO_OUT = 0;
	
	/**
	 * Gibt an, dass die Nachricht ein Fehler ist
	 */
	public static final short ERROR = 1;
	
	/**
	 * Gibt an, dass die Nachricht eine Warnung ist
	 */
	public static final short WARNING = 2;
	
	/**
	 * Gibt an, dass die Nachricht nicht spaziell markiert werden soll 
	 */
	public static final short NONE = 3;
	
	/**
	 * Gibt an, dass die Nachricht einen Erfolg markiert
	 */
	public static final short OK = 4;
	
	/**
	 * Speichert die Farbe für einen Fehler.
	 */
	public static final Color COLOR_ERROR = Color.RED;
	
	/**
	 * Speichert die Farbe für eine Warnung
	 */
	public static final Color COLOR_WARNING = Color.ORANGE;
	
	/**
	 * Speichert die Farbe für eine Normale Nachricht 
	 */
	public static final Color COLOR_NONE = Color.LIGHT_GRAY;
	
	/**
	 * Speichert die Farbe für eine OK Nachricht
	 */
	public static final Color COLOR_OK = Color.GREEN;
	
	/**
	 * Standart Konstruktor (Neue Instanz ohne Daten)
	 */
	public LogData() {
		_message = new String();
		_out = NO_OUT;
	}
	
	/**
	 * Konstruktor für eine Nachricht, die nicht speziell markiert werden soll
	 * 
	 * @param message Nachricht, die ausgegeben werden soll
	 */
	public LogData(String message) {
		setMessage(message);
		_out = NONE;
	}
	
	/**
	 * Konstruktor für eine Nachricht, die spziell markiert werden soll
	 */
	public LogData(String message, short out) {
		setMessage(message);
		_out = out;
	}
	
	/**
	 * Gibt die gespeicherte Nachricht zurück;
	 * 
	 * @return Nachricht, die gespeichert wurde
	 */
	public String getMessage() {
		return _message;
	}
	
	/**
	 * Gibt die Markierung der Nachricht zurück
	 * 
	 * @return Markierung der Nachricht
	 */
	public short getOut() {
		return _out;
	}
	
	/**
	 * Speichert die übergebene Nachricht
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessage(String message) {
		if (message != null) {
			_message = message;
		} else {
			_message = new String();
		}
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie so, dass sie nicht
	 * ausgegeben werden soll
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsNoOut(String message) {
		setMessage(message);
		_out = NO_OUT;
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie als Fehler.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsError(String message) {
		setMessage(message);
		_out = ERROR;
	}
	
	/**
	 * Speichert die übergebene Nachricht und markeirt sie als Warnung.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsWarning(String message) {
		setMessage(message);
		_out = WARNING;
	}
	
	/**
	 * Speichert die Übergebene Nachricht als Erfolgreich.
	 * 
	 * @param message Nachricht, die gespeichert werden soll.
	 */
	public void setMessageAsOk(String message) {
		setMessage(message);
		_out = OK;
	}

	/**
	 * Speichert, wie die Nachricht ausgeben werden soll.
	 * 
	 * 
	 * @param out Markierung der Nachricht
	 */
	public void setOut(short out) {
		_out = out;
	}
}
