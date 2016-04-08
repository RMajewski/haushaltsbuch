package db.query;

import java.util.Date;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'money'.
 * 
 * @author René Majewski
 *
 */
public class Money implements QueryInterface {

	/**
	 * Gibt die Datenbank-Abfrage zurück, die die Tabelle "money" erzeugt.
	 * 
	 * @return Datenbank-Abfrage, um die Tabelle "money" zu erzeugen.
	 */
	@Override
	public String createTable() {
		return "CREATE TABLE IF NOT EXISTS 'money' (" +
				"'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'date' INTEGER NOT NULL, " +
				"'inout' INTEGER, " +
				"'comment' TEXT)";
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Eintrag in der Tabelle
	 * "money" zu erzeugen. FÜr das Datum, ob es eine Ausgabe oder Einnahme
	 * und die Beschreibung werden "?" als Platzhalter genutzt.
	 * 
	 * @return Datenbank-Abfrage, um einen neuen Eintrag in "money" zu erzeugen
	 */
	@Override
	public String insert() {
		return "INSERT INTO 'money' ('date', 'inout', 'comment') VALUES (?, ?, ?)";
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen Eintrag in der Tabelle "money"
	 * zu erzeugen. Es werden die übergebenen Daten in die Datenbank-Abfrage
	 * geschrieben.
	 * 
	 * @return Datenbank-Abfrage, um einen neuen Eintrag in "money" zu erzeugen
	 */
	public String insert(long date, boolean inout, String comment) {
		// Rückgabe voerbereiten
		StringBuilder ret = new StringBuilder(insert());
		
		// Datum einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(date));
		
		// Einfügen, ob es eine Einname oder eine Ausgabe ist
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(inout));
		
		// Kommentar einfügen
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		// Datenbank-Abfrage zurück geben
		return ret.toString();
	}


	/**
	 * Erzeugt die Datenbank-Abfrage, um eine Kategorie aus der Tabelle
	 * "money" zu löschen. Wurde eine ID größer <b>-1</> angegeben, so
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
		String ret = "DELETE FROM 'money' WHERE id = ?";
		
		// Platzhalter mit einer ID ersetzen?
		if (id > -1)
			ret = ret.replace("?", new String("\"" + id + "\""));
		
		// Abfrage zrück geben
		return ret;
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um eine Kategorie in der Tabelle
	 * "money" zu ändern. Wurde ein ID größer <b>-1</b> angegeben, so
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
		StringBuilder ret = new StringBuilder("UPDATE 'money' SET date = ?, inout = ?, comment = '?' WHERE id = ?");
		
		if (id > -1)
			ret.replace(ret.lastIndexOf("?"), ret.lastIndexOf("?") + 1, String.valueOf(id));
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um alle Datensätze der Tabelle "money"
	 * aufzulisten. Die Datensätze werden noch ihren IDs aufsteigend sortiert.
	 * 
	 * @return Datenbank-Abfrage, um alle Datensätze zurück zu geben.
	 */
	@Override
	public String select() {
		return "SELECT id, date, inout, comment FROM money ORDER BY id ASC;";
	}

}
