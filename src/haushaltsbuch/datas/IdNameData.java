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
 * Speichert ID und Name.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class IdNameData extends Data {
	
	/**
	 * Speichert den Namen
	 */
	private String _name;
	
	/**
	 * Initalisiert die ID mit -1 und einen leeren Namen
	 */
	public IdNameData() {
		super(-1);
		setName(new String());
	}
	
	/**
	 * Initalisiert den Namen mit einen leeren Zeichenkette. Übernimmt die
	 * übergebene ID.
	 * 
	 * @param id Neue ID
	 */
	public IdNameData(int id) {
		super(id);
		setName(new String());
	}
	
	/**
	 * Initalisiert die Daten mit der Übergebenen ID und den übergenen Namen.
	 * 
	 * @param id Neue ID
	 * 
	 * @param name Neuer Name
	 */
	public IdNameData(int id, String name) {
		super(id);
		setName(name);
	}

	/**
	 * Gibt den Namen zurück
	 * 
	 * @return Gespeicherter Name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Speichert den übergebenen Namen.
	 * 
	 * @param name Neuer Name, der gespeichert werden soll
	 */
	public void setName(String name) {
		if (name == null)
			_name = new String();
		else
			_name = name;
	}
}
