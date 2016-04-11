package db.query;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'section'.
 * 
 * @author René Majewski
 *
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
