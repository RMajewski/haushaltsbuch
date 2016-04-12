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
		StringBuilder ret = new StringBuilder("INSERT INTO 'money_details' ('moneyid', 'categoryid', 'sectionid', 'money', 'comment') VALUES(");
		
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
		else {
			ret.append("\"");
			ret.append(comment);
			ret.append("\"");
		}
		
		// Abfrage abschließen
		ret.append(");");
		
		// Rückgabe des Datenbank-Abfrage
		return ret.toString();
	}

	/**
	 * Erzeugt, die Datenbank-Abfrage, um alle Datensätze der Tabelle
	 * "money_details" aufzulisten, die einen angegeben Money-Datensatz
	 * enthalten. Ist für die ID eine <b>-1</b> angegeben, so wird ein
	 * Fragenzeichen als Platzhalter eingefügt. Ist die ID größer <b>-1</b>,
	 * so wird die ID eingefügt.
	 * 
	 * @param id ID für den Money-Datensatz
	 * 
	 * @return Datenbank-Abfrage um alle Datensätze anzuzeigen.
	 */
	public String select(int id) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT id, moneyid, categoryid, sectionid, money, comment FROM money_details WHERE moneyid = ? ORDER BY id ASC");
		
		// Money-ID einfügen
		replaceId(id, ret, false);
		
		// Datenbank-Abfrage zurück geben
		return ret.toString();
	}

}
