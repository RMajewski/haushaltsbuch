package datas;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

// TODO Aus übergebenen Daum (String) long-Wert erzeugen

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
	 * Erstellt aus dem gespeicherten long-Wert des Datum eine Zeichenkette,
	 * in der das Datum in Klartext geschrieben ist.
	 * 
	 * @return Datum als Zeichenkette
	 */
	public String getDateAsString() {
		return DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date(_date));
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
	 * Es wird aus der übergebenen Zeichenkette der long-Wert des Datums
	 * ermittelt und gespeichert. Wird eine leere Zeichenkette oder <b>null</b>
	 * übergeben, so wird der long-Wert des aktuellen Datums ermittelt und
	 * gespeichert.
	 * 
	 * @param date Zeichenkette, aus der der long-Wert des Datums ermittelt
	 * werden soll.
	 * 
	 * @return Gibt das ermittelte Datum zurück.
	 */
	public long setDate(String date) {
		if (date == null || date.isEmpty()) {
			// Aktuelles Datum ermitteln und long-Wert speichern
			_date = new Date().getTime();
		} else {
			String[]tmp  = date.split(Pattern.quote("."));
			_date = new GregorianCalendar(Integer.valueOf(tmp[2]).intValue(),
										  Integer.valueOf(tmp[1]).intValue() - 1,
										  Integer.valueOf(tmp[0]).intValue()
							  ).getTimeInMillis();
		}
		
		// Datum zurück geben
		return _date;
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
	 * Gibt die Zeichenkette "Ausgabe" zurück, wenn in _inout OUTGOING
	 * gespeichert wurde. Wurde dagegen INCOMING gespeichert, so wird die
	 * Zeichenkette "Einnahm" zurück gegeben.
	 *   
	 * @return Gibt entweder "Einnahme" oder "Ausgabe" zurück.
	 */
	public String getInOutAsString() {
		if (_inout == INCOMING)
			return new String("Einnahme");
		else
			return new String("Ausgabe");
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
