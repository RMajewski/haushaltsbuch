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

/**
 * Grundlagen-Klasse für die Daten. Hier werden Methoden implementiert, die
 * in alle Datensatz-Klassen vorhanden sind.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class Data {
	/**
	 * Speichert die ID
	 */
	protected int _id;
	
	/**
	 * Initalisiert die Klasse.
	 * 
	 * @param id ID, die diesem Datensatz zugewiesen wurde.
	 */
	public Data(int id) {
		_id = id;
	}
	
	/**
	 * Gibt die ID zurück
	 * 
	 * @return ID, die diesem Datensatz zugewiesen wurde.
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Speichert die neue ID.
	 * 
	 * @param id Neue ID, die diesem Datensatz zugewiesen werden soll.
	 */
	public void setId(int id) {
		_id = id;
	}
}
