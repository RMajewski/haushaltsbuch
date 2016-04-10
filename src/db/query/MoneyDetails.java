package db.query;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'money_details'.
 * 
 * @author René Majewski
 *
 */
public class MoneyDetails extends Query {
	/**
	 * Initalisiert den Tabellen-Namen und die Spalten-Namen. 
	 */
	public MoneyDetails() {
		super("money_details");
		_columnNames.add("id");
		_columnNames.add("moneyid");
		_columnNames.add("categoryid");
		_columnNames.add("sectionid");
		_columnNames.add("money");
		_columnNames.add("comment");
	}

	/**
	 * Gibt die Datenbank-Abfrage zurück, die die Tabelle "money_details"
	 * erzeugt.
	 * 
	 * @return Datenbank-Abfrage, um die Tabelle "money_details" zu erzeugen.
	 */
	@Override
	public String createTable() {
		return "CREATE TABLE IF NOT EXISTS 'money_details' (" +
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
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Eintrag in die Tabelle
	 * "money_details" zu erzeugen. Für die 'moneyid', 'category' und 'section'
	 * werden die IDs übernommen. Sollte ein eine <b>-1</b übergeben werden, so
	 * wird als Platzhalter ein <b>?</b> genommen. Das Geld wird so übernommen,
	 * wie es angegeben wurde. Solle eine <b>null</b> oder eine leere
	 * Zeichenkette als Kommentar übergeben werden, so wird als Platzhalter das
	 * <b>?</b> benutzt. Sollte eine Zeichenkette übergeben werden, so wird
	 * diese Zeichenkette in die Datenbank-Abfrage übernommen.
	 * 
	 * @param moneyid ID des Money-Datensatzes, zu dem dieser Datensatz gehören
	 * soll.
	 * 
	 * @param categoryid ID der Kategory
	 * 
	 * @param sectionid ID des Geschäftes
	 * 
	 * @param money Angabe des Geldes
	 * 
	 * @param comment Beschreibung für diesen Datensatz
	 * 
	 * @return Datenbank-Abfrage, um einen Eintrag in "money_details" mit den
	 * übergebenen Daten zu erstellen.
	 */
	public String insert(int moneyid, int categoryid, int sectionid, double money, String comment) {
		// Vorberung der Abfrage
		StringBuilder ret = new StringBuilder("INSERT INTO 'money_details' ('moneyid', 'categoryid', 'sectionid', 'money', 'comment) VALUES(");
		
		// ID des Money-Datensatzes
		if (moneyid > -1)
			ret.append(String.valueOf(moneyid));
		else
			ret.append("?");
		ret.append(", ");
		
		// Die Kategory ID
		if (categoryid > -1)
			ret.append(String.valueOf(categoryid));
		else
			ret.append("?");
		ret.append(", ");
		
		// Die ID des Geschäftes
		if (sectionid > -1)
			ret.append(String.valueOf(sectionid));
		else
			ret.append("?");
		ret.append(", ");
		
		// Das Geld
		ret.append(String.valueOf(money));
		ret.append(", ");
		
		// Der Kommentar
		if (comment == null || comment.isEmpty())
			ret.append("?");
		else
			ret.append(comment);
		
		// Abfrage abschließen
		ret.append(");");
		
		// Rückgabe des Datenbank-Abfrage
		return ret.toString();
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
		StringBuilder ret = new StringBuilder("DELETE FROM 'money_details' WHERE id = ?");
		
		// Platzhalter mit einer ID ersetzen?
		replaceId(id, ret, true);
		
		// Abfrage zrück geben
		return ret.toString();
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
		StringBuilder ret = new StringBuilder("UPDATE 'money' SET moneyid = ?, categoryid = ?, sectionid = ?, money = ?, comment = '?' WHERE id = ?");
		
		// ID einfügen
		replaceId(id, ret, true);
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um alle Datensätze der Tabelle "money_details"
	 * aufzulisten. Die Datensätze werden noch ihren IDs aufsteigend sortiert.
	 * 
	 * @return Datenbank-Abfrage, um alle Datensätze zurück zu geben.
	 */
	@Override
	public String select() {
		return "SELECT id, moneyid, categoryid, sectionid, money, comment FROM money_details ORDER BY id ASC";
	}

	/**
	 * Erzeugt, die Datenbank-Abfrage, um alle Datensätze der Tabelle
	 * "money_details" aufzulisten, die einen angegeben Money-Datensatz
	 * enthalten. Ist für die ID eine <b>-1</b> angegeben, so wird ein
	 * Fragenzeichen als Platzhalter eingefügt. Ist die ID größer <b>-1</b>,
	 * so wird die ID eingefügt.
	 * 
	 * @param id ID für den Money-Datensatz
	 * @return
	 */
	public String select(int id) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT id, moneyid, categoryid, section id, money, comment FROM money_details WHERE moneyid = ? ORDER BY id ASC");
		
		// Money-ID einfügen
		replaceId(id, ret, false);
		
		// Datenbank-Abfrage zurück geben
		return ret.toString();
	}

}
