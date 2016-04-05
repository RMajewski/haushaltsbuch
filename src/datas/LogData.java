package datas;

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
	 * return Nachricht, die gespeichert wurde
	 * @return 
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
