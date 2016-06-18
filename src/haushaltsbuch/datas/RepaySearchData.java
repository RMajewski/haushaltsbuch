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

/**
 * Speichert die Daten für einen Datensatz zum Anzeigen der gesuchten Ausgaben.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class RepaySearchData extends RepayData {
	/**
	 * Gibt die Anzahl der Spalten für die Tabelle an.
	 */
	public static final int COLUMNS = 6;
	
	/**
	 * Speichert, ob der Datensatz in der Tabelle für die Rückzahlungen
	 * gespeichert werden soll (true) oder nicht (false);
	 */
	private boolean _take;
	
	/**
	 * Speichert das Datum, an dem die Ausgabe getätigt wurde.
	 */
	private String _date;
	
	/**
	 * Speichert den Betrag, der ausgegeben wurde.
	 */
	private double _money;
	
	/**
	 * Speichert den Kommentar.
	 */
	private String _comment;
	
	/**
	 * Initalisiert die IDs mit -1; 
	 */
	public RepaySearchData() {
		super(-1, -1, -1);
		_comment = new String();
		_date = new String();
		_money = 0.00;
	}

	/**
	 * Initalisiert die dieses Datensatzes mit der übergebenen ID. Die IDs für
	 * den Details-Datensatz und den Daten für die Schulden werden auf -1
	 * gesetzt.
	 * 
	 * @param id ID dieses Datensatzes.
	 */
	public RepaySearchData(int id) {
		this();
		_id = id;
	}
	
	/**
	 * Inititalisiert die Datensätze mit den übergebenen IDs.
	 * 
	 * @param id ID dieses Datensatzes.
	 * 
	 * @param detailsId ID des Datensatzes für die Details der Ausgaben.
	 * 
	 * @param outstandingID ID des Datensatzes für die Schulden.
	 */
	public RepaySearchData(int id, int detailsId, int outstandingId, 
			String date, double money, String comment) {
		super(id, detailsId, outstandingId);
		_comment = comment;
		_money = money;
		_date = date;
	}
	
	/**
	 * Gibt den Kommentar zurück.
	 * 
	 * @return Kommentar
	 */
	public String getComment() {
		return _comment;
	}
	
	/**
	 * Setzt den Kommentar neu.
	 * 
	 * @param comment Der neue Kommentar
	 */
	public void setComment(String comment) {
		_comment = comment;
	}
	
	/**
	 * Gibt das Datum zurück.
	 * 
	 * @return Datum
	 */
	public String getDate() {
		return _date;
	}
	
	/**
	 * Setzt das Datum neu.
	 * 
	 * @param date Neues Datum
	 */
	public void setDate(String date) {
		_date = date;
	}
	
	/**
	 * Gibt den Betrag zurück.
	 * @return 
	 * 
	 * @return Betrag
	 */
	public double getMoney() {
		return _money;
	}
	
	/**
	 * Setzt den Betrag neu
	 * 
	 * @param money Neuer Betrag
	 */
	public void setMoney(double money) {
		_money = money;
	}
	
	/**
	 * Gibt zurück, ob der Datensatz übernommen werden soll oder nicht.
	 * 
	 * @return Soll der Datensatz übernommen werden?
	 */
	public boolean getTake() {
		return _take;
	}
	
	/**
	 * Setzt, ob der Datensatz übernommen werden soll (true) oder nicht (false).
	 * 
	 * @param take Soll der Datensatz übernommen werden?
	 */
	public void setTake(boolean take) {
		_take = take;
	}
}
