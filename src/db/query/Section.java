package db.query;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'section'.
 * 
 * @author René Majewski
 *
 */
public class Section implements QueryInterface {
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
	 * Erzeugt die Datenbank-Abfrage, um ein neues Geschäft in die Tabelle
	 * "section" zu speichern. Den Namen des neuen Geschäfts wird mit einen
	 * <b>?</b> markiert.
	 * 
	 * @return Datenbank-Abfrage, um ein neues Geschäft zu erstellen.
	 */
	@Override
	public String insert() {
		return "INSERT INTO 'section' ('name') VALUES (?);";
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
	 * Erzeugt die Datenbank-Abfrage, um ein Geschäft aus der Tabelle
	 * "section" zu löschen. Wurde eine ID größer <b>-1</> angegeben, so
	 * ist diese bereits in der Rückgabe enthalten. Wurde als ID
	 * <b>-1</b> angegeben, so ist für die ID der Platzhalter <b>?</b>
	 * vorgesehen.
	 * 
	 * @param id ID des Datensatzes, der gelöscht werden soll.
	 * 
	 * @return Datenbank-Abfrage um den angegeben Datensatz zu löschen.
	 */
	@Override
	public String delete(int id) {
		// Abfrage enthält Platzhalter
		String ret = "DELETE FROM 'section' WHERE id = ?";
		
		// Platzhalter mit einer ID ersetzen?
		if (id > -1)
			ret = ret.replace("?", new String("\"" + id + "\""));
		
		// Abfrage zrück geben
		return ret;
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um ein Geschäft in der Tabelle
	 * "section" zu ändern. Wurde eine ID größer <b>-1</b> angegeben, so
	 * wird die ID in die Abfrage aufgenommen. Wurde als ID <b>-1</b>
	 * angegeben, wird für die ID ein <b>?</b> als Platzhalter in die
	 * Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werde soll.
	 * 
	 * @return Datenbank-Abfrage, um den angegebenen Datensatz zu ändern.
	 */
	@Override
	public String update(int id) {
		// Abfrage zum ändern des angegebenen Datensatzes
		StringBuilder ret = new StringBuilder("UPDATE 'section' SET name = '?' WHERE id = ?");
		
		if (id > -1)
			ret.replace(ret.lastIndexOf("?"), ret.lastIndexOf("?") + 1, "\"" + id + "\"");
		
		// Abfrage zurück geben
		return ret.toString();
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

	/**
	 * Erzeugt die Datenbank-Abfrage, um alle Datensätze der Tabelle "section"
	 * aufzulisten. Die Datensätze werden noch ihren IDs aufsteigend sortiert.
	 * 
	 * @return Datenbank-Abfrage, um alle Datensätze zurück zu geben.
	 */
	@Override
	public String select() {
		return "SELECT id, name FROM section ORDER BY id ASC;";
	}
	
}
