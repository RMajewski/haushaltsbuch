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

import org.testsuite.helper.HelperCalendar;

/**
 * Speichert die Daten für einen Datensatz der Tabelle 'outstanding'
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class OutstandingData extends Data {
	/**
	 * Speichert die ID des Geschäftes
	 */
	private int _sectionId;
	
	/**
	 * Speichert den Betrag
	 */
	private double _money;
	
	/**
	 * Speichert die Anzahl der Raten
	 */
	private int _months;
	
	/**
	 * Speichert das Start-Datum
	 */
	private long _start;
	
	/**
	 * Speichert den Betrag der monatlichen Rate
	 */
	private double _monthMoney;
	
	/**
	 * Speichert den Kommentar.
	 */
	private String _comment;
	
	/**
	 * Initalisiert die ID mit -1, das Geld mit 0.00 und eine leere
	 * Zeichenkette als Beschreibung
	 */
	public OutstandingData() {
		super(-1);
		setComment(null);
		_money = 0.0;
		_monthMoney = 0.0;
		_months = -1;
		_sectionId = -1;
		_start = 0l;
	}
	
	/**
	 * Initalisiert die ID des Datensatzen mit der übergebenen ID, die
	 * IDs für den dazugehörigenen Money-Datensatz, der Kategorie und des
	 * Geschäftes werden auf -1 gesetzt. Das Geld wird auf 0.00 gesetzt. Als
	 * Beschreibung wird eine leere Zeichenkette gespeichert.
	 * 
	 * @param id ID des Datensatzes
	 */
	public OutstandingData(int id) {
		this();
		_id = id;
	}

	/**
	 * Initialisiert die Daten mit den übergebenen Werten.
	 * 
	 * @param id ID des Datensatzes
	 * 
	 * @param sectionId ID des Geschäftes
	 * 
	 * @param money Höhe der Schulden
	 * 
	 * @param months Anzahl der monatlichen Raten
	 * 
	 * @param start Startpunkt der ersten Rate.
	 * 
	 * @param monthMoney Höhe der monatlichen Raten.
	 * 
	 * @param comment Beschreibung
	 */
	public OutstandingData(int id, int sectionId, double money, int months,
			long start, double monthMoney, String comment) {
		super(id);
		_sectionId = sectionId;
		_money = money;
		_months = months;
		_start = start;
		_monthMoney = monthMoney;
		setComment(comment);
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
	 * Speichert die neue Beschreibung.
	 * 
	 * @param comment Neue Beschreibung
	 */
	public void setComment(String comment) {
		if (comment == null)
			_comment = new String();
		else
			_comment = comment;
	}

	/**
	 * Gibt das gespeicherte Geld zurück.
	 * 
	 * @return Gespeichertes Geld
	 */
	public double getMoney() {
		return _money;
	}

	/**
	 * Speichert den neuen Geld-Wert
	 * 
	 * @param money Neuer Geld-Wert
	 */
	public void setMoney(double money) {
		_money = money;
	}

	/**
	 * Gibt die Höhe der monatlichen Rate zurück.
	 * 
	 * @return Höhe der monatlichen Rate
	 */
	public double getMonthMoney() {
		return _monthMoney;
	}

	/**
	 * Speichert die Höhe der monatlichen Rate
	 * 
	 * @param money Höhe der monatlichen Rate
	 */
	public void setMonthMoney(double money) {
		_monthMoney = money;
	}

	/**
	 * Gibt die ID des Geschäftes zurück.
	 * 
	 * @return ID des Geschäftes
	 */
	public int getSectionId() {
		return _sectionId;
	}

	/**
	 * speichert die neue ID des Geschäftes.
	 * 
	 * @param sectionId Neue ID des Geschäftes
	 */
	public void setSectionId(int sectionId) {
		_sectionId = sectionId;
	}

	/**
	 * Es wird das gespeicherte Datum der 1. Raten zurück gegeben.
	 * 
	 * @return Gespeichertes Datum der 1. Rate
	 */
	public long getStart() {
		return _start;
	}

	/**
	 * Erstellt aus dem gespeicherten long-Wert des Start-Datum eine 
	 * Zeichenkette, in der das Datum in Klartext geschrieben ist.
	 * 
	 * @return Datum als Zeichenkette
	 */
	public String getStartAsString() {
		return HelperCalendar.dateToString(_start);
	}

	/**
	 * Es wird das Start-Datum auf das übergebene Datum gesetzt.
	 * 
	 * @param date Neues Start-Datum, was gespeichert werden soll.
	 */
	public void setStart(long date) {
			_start = date;
	}
	
	/**
	 * Gibt die Anzahl der monatlichen Raten zurück.
	 * 
	 * @return Anzahl der monatlichen Raten.
	 */
	public int getMonths() {
		return _months;
	}
	
	/**
	 * Setzt die Anzahl der monatlichen Raten.
	 * 
	 * @param months Anzahl der monatlihen Raten.
	 */
	public void setMonths(int months) {
		_months = months;
	}
}
