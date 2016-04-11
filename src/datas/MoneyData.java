package datas;

// TODO Aus übergebenen Daum (String) long-Wert erzeugen
// TODO Aus long-Wert ein Datum erzeugen (String)

/**
 * Speichert die Daten für einen Datensatz der Tabelle 'money'
 * 
 * @author René Majewski
 */
public class MoneyData extends Data {
	/**
	 * Speichert den Wert für eine Ausgabe
	 */
	public static final boolean OUTGOING = false;
	
	/**
	 * Speichert den Wert für eine Einnahme
	 */
	public static final boolean INCOMING = true;

	/**
	 * Speichert den Wert für die Einnahme als Integer
	 */
	public static final int INT_INCOMING = 1;

	/**
	 * Speichert den für Ausgabe als Integer
	 */
	public static final int INT_OUTGOING = 0;
	
	/**
	 * Speichert das Datum
	 */
	private long _date;
	
	/**
	 * Speichert ob es eine Ein- oder Auszahlung ist
	 */
	private boolean _inout;
	
	/**
	 * Speichert die Beschreibung
	 */
	private String _comment;
	
	/**
	 * Initalisiert die Daten. Es wird -1 als ID angenommen. Das Datum wird
	 * das aktuelle Datum ermittelt und gespeichert. Es wird eine Auszahlung
	 * angenommen. 
	 */
	public MoneyData() {
		super(-1);
		_date = -1;
		_inout = false;
		setComment(null);
	}
	
	/**
	 * Initalisiert die Daten. Es wird die übergebene ID übernommen. Es wir
	 * das aktuelle Datum ermittelt und gespeichert. Es wird eine Auszahlung
	 * angenommen. Es wird keine Beschreibung gespeichert.
	 * 
	 * @param id Neue ID
	 */
	public MoneyData(int id) {
		this();
		_id = id;
	}

	/**
	 * Initalisiert die Daten. Es wird die übergebene ID, das übergebene Datum,
	 * die übergebene Ausgabe oder Einzahlung und die Beschreibung übernommen.
	 * 
	 * @param id ID, die gespeichert werden soll.
	 * 
	 * @param date Datum, das gespeichert werden soll.
	 * 
	 * @param inout Handelt es sich um ein Ausgabe oder eine Einnahme?
	 * 
	 * @param comment Beschreibung, die gespeichert werden soll.
	 */
	public MoneyData(int id, long date, boolean inout, String comment) {
		super(id);
		setDate(date);
		_inout = inout;
		setComment(comment);
	}

	/**
	 * Es wird das gespeicherte Datum zurück gegeben.
	 * 
	 * @return Gespeichertes Datum
	 */
	public long getDate() {
		return _date;
	}

	/**
	 * Es wird das Datum auf das übergebene Datum gesetzt.
	 * 
	 * @param date Neues Datum, was gespeichert werden soll.
	 */
	public void setDate(long date) {
			_date = date;
	}

	/**
	 * Gibt zurück, ob dies eine Ausgabe oder eine Einzahlung ist.
	 * 
	 * @return Ausgabe oder Einzahlung
	 */
	public boolean getInOut() {
		return _inout;
	}

	/**
	 * Setzt den Wert für die Ausgabe oder Einzahlung auf den übergebenen Wert.
	 * 
	 * @param inout Ist es eine Ausgabe oder eine Einzahlung?
	 */
	public void setInOut(boolean inout) {
		_inout = inout;
	}

	/**
	 * Gibt die gespeicherte Beschreibung zurück.
	 * 
	 * @return Gespeicherte Beschreibung
	 */
	public String getComment() {
		return _comment;
	}

	/**
	 * Setzt die Beschreibung auf den übergebenen Wert. Wird <b>null</b>
	 * übergeben, so wird eine leere Zeichenkette erzeugt.
	 * 
	 * @param comment Neue Beschreibung
	 */
	public void setComment(String comment) {
		if (comment == null)
			_comment = new String();
		else
			_comment = comment;
	}

}
