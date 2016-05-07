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

package tests.core;

import java.io.InputStream;

/**
 * In dieser Klasse werden die Daten zu einem Test gespeichert.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestData {
	/**
	 * Speichert den Namen des Tests
	 */
	private String _name;
	
	/**
	 * Speichert die Startzeit
	 */
	private long _start;
	
	/**
	 * Speichert die Endzeit
	 */
	private long _end;
	
	/**
	 * Speichert, wie der Test abgeschlossen wurd.
	 */
	private int _exitStatus;
	
	/**
	 * Speichert die Ausgabe der Konsole
	 */
	private InputStream _in;
	
	/**
	 * Speichert die Ausgabe der Fehler
	 */
	private InputStream _error;
	
	/**
	 * Speichert, ob die Datei existiert.
	 */
	private boolean _exists;
	
	/**
	 * Initalisiert die Daten dieser Klasse
	 */
	public TestData() {
		_name = new String();
		_end = 0;
		_start = 0;
		_error = null;
		_exitStatus = -1;
		_in = null;
		_start = 0;
		_exists = false;
	}
	
	/**
	 * Legt die Startzeit
	 * 
	 * @param time Startzeit des Tests
	 */
	public void setStart(long time) {
		_start = time;
	}
	
	/**
	 * Gibt die Startzeit zurück
	 * 
	 * @return Startzeit des Tests
	 */
	public long getStart() {
		return _start;
	}
	
	/**
	 * Legt die Endzeit fest.
	 * 
	 * @param time Zeit, an dem der Test beendet wurde.
	 */
	public void setEnd(long time) {
		_end = time;
	}
	
	/**
	 * Gibt die Endzeit zurück.
	 * 
	 * @return Zeit, an dem der Test beendet wurde
	 */
	public long getEnd() {
		return _end;
	}
	
	/**
	 * Gibt die Dauer zurück, die der Test gelaufen ist.
	 * 
	 * @return Dauer des Tests
	 */
	public long getDurationTime() {
		return _end - _start;
	}
	
	/**
	 * Legt den InputStream für die Fehler fest.
	 * 
	 * @param stream InputStream, in die die Fehler geschrieben wurden
	 */
	public void setError(InputStream stream) {
		_error = stream;
	}
	
	/**
	 * Gibt den Stream zurück, in dem die Fehler geschrieben wurden.
	 * 
	 * @return InputStream für die Fehler
	 */
	public InputStream getError() {
		return _error;
	}
	
	/**
	 * Legt den Stream fest, in dem die Ausgaben der Konsole geschrieben wurden.
	 * 
	 * @param stream InputStream mit den Ausgaben der Konsole.
	 */
	public void setIn(InputStream stream) {
		_in = stream;
	}
	
	/**
	 * Gibt den Stream zurück, in dem die Ausgaben der Konsole geschrieben
	 * wurden.
	 * 
	 * @return InputStream mit den Ausgaben der Konsole.
	 */
	public InputStream getIn() {
		return _in;
	}
	
	/**
	 * Legt fest, ob die Datei existiert.
	 * 
	 * @param exists Existiert die Test-Datei?
	 */
	public void setExists(boolean exists) {
		_exists = exists;
	}
	
	/**
	 * Gibt zurück, ob die Test-Datei existiert.
	 * 
	 * @return Existiert die Test-Datei?
	 */
	public boolean isExists() {
		return _exists;
	}
	
	/**
	 * Legt den Exit-Status der Test-Ausführung fest.
	 * 
	 * @param status Exit-Status des Tests
	 */
	public void setExitStatus(int status) {
		_exitStatus = status;
	}
	
	/**
	 * Gibt den Exit-Status zurück, der vom Test zurück gegeben wurde.
	 * 
	 * @return Status, mit dem der Test beendet wurde.
	 */
	public int getExitStatus() {
		return _exitStatus;
	}
	
	/**
	 * Legt den Namen des Tests fest.
	 * 
	 * @param name Name des Tests
	 */
	public void setName(String name) {
		_name = name;
	}
	
	/**
	 * Gibt den Namen des Tests zurück.
	 * 
	 * @return Name des Tests.
	 */
	public String getName() {
		return _name;
	}
}
