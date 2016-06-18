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
 * Speichert die Daten für einen Datensatz der Tabelle 'repay'
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class RepayData extends Data {
	/**
	 * Speichert die ID vom Datensatz für die Ausgabe-Details
	 */
	private int _detailsId;
	
	/**
	 * Speichert die ID vom Datensatz für die Schulden
	 */
	private int _outstandingId;
	
	/**
	 * Initalisiert die IDs mit -1; 
	 */
	public RepayData() {
		super(-1);
		_detailsId = -1;
		_outstandingId = -1;
	}

	/**
	 * Initalisiert die dieses Datensatzes mit der übergebenen ID. Die IDs für
	 * den Details-Datensatz und den Daten für die Schulden werden auf -1
	 * gesetzt.
	 * 
	 * @param id ID dieses Datensatzes.
	 */
	public RepayData(int id) {
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
	public RepayData(int id, int detailsId, int outstandingId) {
		this(id);
		_detailsId = detailsId;
		_outstandingId = outstandingId;
	}
	
	/**
	 * Setzt die ID des Datensatzes für die Details der Ausgaben neu.
	 * 
	 * @param id ID des Datensatzes für die Ausgaben
	 */
	public void setDetailsId(int id) {
		_detailsId = id;
	}
	
	/**
	 * Gibt die ID des Datensatzes für die Details der Ausgaben zurück.
	 * 
	 * @return ID des Datensatzes für die Details der Ausgaben.
	 */
	public int getDetailsId() {
		return _detailsId;
	}
	
	/**
	 * Setzt die ID des Datensatzes für die Schulden neu.
	 * 
	 * @param id Neue ID des Datensatzes für die Schulden.
	 */
	public void setOutstandingId(int id) {
		_outstandingId = id;
	}
	
	/**
	 * Gibt die ID des Datensatzes für die Schulden zurück.
	 * 
	 * @return ID des Datensatzes für die Schulden.
	 */
	public int getOutstandingId() {
		return _outstandingId;
	}

}
