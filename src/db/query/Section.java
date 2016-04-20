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

package db.query;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'section'.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class Section extends Query {
	/**
	 * Initalisiert den Tabellen-Namen und die Spalten-Namen.
	 */
	public Section() {
		super("section");
		_columnNames.add("id");
		_columnNames.add("name");
	}

	/**
	 * Gibt die Datenbank-Abfrage zurück, die die Tabelle "section" erzeugt.
	 * 
	 * @return Datenbank-Abfrage, um die Tabelle "section" zu erzeugen.
	 */
	@Override
	public String createTable() {
		return "CREATE TABLE IF NOT EXISTS 'section' (" +
				"'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'name' TEXT);";
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um ein neues Geschft in die Tabelle
	 * "section" zu speichern. Diese Abfrage enthält bereits den Namen. Wird
	 * <b>null</b> als Parameter übergeben, so wird als Platzhalter für den
	 * Namen ein <b>?</b> eingefügt.
	 * 
	 * @param name Name des neuen Geschäfts.
	 * 
	 * @return Datenbank-Abfrage, um ein neues Geschäft zu erstellen.
	 */
	public String insert(String name) {
		String ret = insert();
		
		if (name != null && !name.isEmpty()) {
			ret = ret.replace("?", name);
		}

		return ret;
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um eine neue Kategorie in die Tabelle
	 * "category" zu speichern. Diese Abfrage enthält bereits den Namen. Wird
	 * <b>null</b> als Parameter übergeben, so wird als Platzhalter für den
	 * Namen ein <b>?</b> eingefügt.
	 * 
	 * @param name Name der neuen Kategorie.
	 * 
	 * @param id ID des Datensatzes
	 * 
	 * @return Datenbank-Abfrage, um eine neue Kategorie zu erstellen.
	 */
	public String insert(int id, String name) {
		StringBuilder sb = new StringBuilder(insertWithId());
		
		replaceId(id, sb, false);
		
		if (name != null && !name.isEmpty()) {
			sb.replace(sb.indexOf("?"), sb.indexOf("?") + 1, name);
		}

		return sb.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um ein Geschäft in der Tabelle
	 * "section" zu ändern. Wurde eine ID größer <b>-1</b> angegeben, so wird
	 * die ID in die Abfrage aufgebommen. Wurde als ID <b>-1</b> angegeben,
	 * wird für die ID ein <b>?</b> als Platzhalter in die Datenbank-Abfrage
	 * übernommen. Wird ein leerer String als Name angegeben, so wird für den
	 * Namen ebenfalls ein <b>?</b> als Platzhalter in der Abfrage übernommen.
	 * Wird ein Name angegeben, so wird der Name in die Abfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @param name Neuer Name des Geschäfts
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update(int id, String name) {
		// Abfrage zum ändern des angegebenen Datensatzes
		StringBuilder ret = new StringBuilder(update(id));
		
		if (name != null && !name.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, name);
		
		// abfrage zurück geben
		return ret.toString();
	}
	
}
