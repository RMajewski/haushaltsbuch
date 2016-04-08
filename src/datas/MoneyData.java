package datas;

import java.util.Date;

/**
 * Speichert die Daten für einen Datensatz der Tabelle 'money'
 * 
 * @author René Majewski
 */
public class MoneyData {
	/**
	 * Speichert den Wert für eine Ausgabe
	 */
	public static final boolean OUTGOING = false;
	
	/**
	 * Speichert den Wert für eine Einnahme
	 */
	public static final boolean INCOMING = true;

	/**
	 * Speichert die ID
	 */
	private int _id;
	
	/**
	 * Speichert das Datum
	 */
	private Date _date;
	
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
		_id = -1;
		setDate(null);
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
	public MoneyData(int id, Date date, boolean inout, String comment) {
		_id = id;
		setDate(date);
		_inout = inout;
		setComment(comment);
	}

	/**
	 * Gibt die gespeicherte ID zurück.
	 * 
	 * @return Gespeichert ID
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Es wird die ID auf die übergebene ID gesetzt.
	 * 
	 * @param Neue ID
	 */
	public void setId(int id) {
		_id = id;
	}

	/**
	 * Es wird das gespeicherte Datum zurück gegeben.
	 * 
	 * @return Gespeichertes Datum
	 */
	public Date getDate() {
		return _date;
	}

	/**
	 * Es wird das Datum auf das übergebene Datum gesetzt. Ist das übergebene
	 * Datum <b>null</b>, so wird das aktuelle Datum ermittelt.
	 * 
	 * @param date Neues Datum, was gespeichert werden soll.
	 */
	public void setDate(Date date) {
		if (date == null)
			_date = new Date();
		else
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