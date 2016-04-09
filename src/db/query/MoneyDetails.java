package db.query;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'money_details'.
 * 
 * @author René Majewski
 *
 */
public class MoneyDetails implements QueryInterface {


	/**
	 * Gibt die Datenbank-Abfrage zurück, die die Tabelle "money_details"
	 * erzeugt.
	 * 
	 * @return Datenbank-Abfrage, um die Tabelle "money_details" zu erzeugen.
	 */
	@Override
	public String createTable() {
		return "CREATE TABLE IF NOT EXISTS 'money' (" +
				"'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'moneyid' INTEGER NOT NULL, " +
				"'categoryid' INTEGER NOT NULL, " +
				"'sectionid' INTEGER NOT NULL, " +
				"'money' DOUBLE, " +
				"'comment' TEXT)";
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Eintrag in der Tabelle
	 * "money_details" zu erzeugen. Für die 'moneyid', 'categoryid',
	 * 'sectionid', das Geld und die Beschreiung wird jeweils ein "?" als
	 * Platzhalter genutzt.
	 *
	 * 
	 * @return Datenbank-Abfrage, um einen neuen Eintrag in "money_details" zu
	 * erzeugen
	 */
	@Override
	public String insert() {
		return "INSERT INTO 'money_details' ('moneyid', 'categoryid', 'sectionid', 'money', 'comment') VALUES (?, ?, ?, ?, ?);";
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um einen Datensatz aus der Tabelle
	 * "money_details" zu löschen. Wurde eine ID größer <b>-1</> angegeben, so
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
		String ret = "DELETE FROM 'money_details' WHERE id = ?";
		
		// Platzhalter mit einer ID ersetzen?
		if (id > -1)
			ret = ret.replace("?", new String("\"" + id + "\""));
		
		// Abfrage zrück geben
		return ret;
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um einen Datensatz in der Tabelle
	 * "money_details" zu ändern. Wurde ein ID größer <b>-1</b> angegeben, so
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
		
		// TODO Diese Abfrage-Bedingung als Methode implementieren
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
		return "SELECT id, moneyid, categoryid, sectionid, money, comment FROM money_details ORDER BY id ASC";
	}

}
