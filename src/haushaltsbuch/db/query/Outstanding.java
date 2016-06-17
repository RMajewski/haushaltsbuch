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

package haushaltsbuch.db.query;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'outstanding'.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.4
 */
public class Outstanding extends Query {

	/**
	 * Initalisiert den Tabellen-Namen und die Spalten-Namen
	 */
	public Outstanding() {
		super("outstanding");
		_columnNames.add("id");
		_columnNames.add("sectionid");
		_columnNames.add("money");
		_columnNames.add("months");
		_columnNames.add("start");
		_columnNames.add("monthMoney");
		_columnNames.add("comment");
	}

	/**
	 * Gibt die Datenbank-Abfrage zurück, die die Tabelle "outstanding" erzeugt.
	 * 
	 * @return Datenbank-Abfrage, um die Tabelle "outstanding" zu erzeugen.
	 */
	@Override
	public String createTable() {
		return "CREATE TABLE IF NOT EXISTS 'outstanding' (" +
				"'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
				"'sectionid' INTEGER NOT NULL," +
				"'money' DOUBLE NOT NULL," +
				"'months' INTEGER NOT NULL," +
				"'start' LONG NOT NULL," +
				"'monthMoney' DOUBLE NOT NULL," +
				"'comment' TEXT);";
	}
	
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen Eintrag in der Tabelle
	 * "outstanding" zu erzeugen. Es werden die übergebenen Daten in die
	 * Datenbank-Abfrage geschrieben.
	 * 
	 * @return Datenbank-Abfrage, um einen neuen Eintrag in "outstanding" zu
	 * erzeugen
	 */
	public String insert(int sectionId, double money, int months, long start,
			double monthMoney, String comment) {
		// Rückgabe voerbereiten
		StringBuilder ret = new StringBuilder(insert());
		
		// ID des Geschäftes einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(sectionId));
		
		// Schlulden einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(money));
		
		// Anzahl monatlicher Raten einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(months));
		
		// Datum der 1. Rate einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(start));
		
		// Höhe der monatlichen Rate einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(monthMoney));
		
		// Kommentar einfügen
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		else
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, new String());
		
		// Datenbank-Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen Eintrag in der Tabelle "money"
	 * zu erzeugen. Es werden die übergebenen Daten in die Datenbank-Abfrage
	 * geschrieben.
	 * 
	 * @return Datenbank-Abfrage, um einen neuen Eintrag in "money" zu erzeugen
	 */
	public String insert(int id, int sectionId, double money, int months, long start,
			double monthMoney, String comment) {
		// Rückgabe voerbereiten
		StringBuilder ret = new StringBuilder(insertWithId());
		
		// Id einfügen
		replaceId(id, ret, false);
		
		// ID des Geschäftes einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(sectionId));
		
		// Schlulden einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(money));
		
		// Anzahl monatlicher Raten einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(months));
		
		// Datum der 1. Rate einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(start));
		
		// Höhe der monatlichen Rate einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(monthMoney));
		
		// Kommentar einfügen
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		else
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, new String());
		
		// Datenbank-Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um in der Tabelle 'outstanding' das
	 * Geschäft oder die Höhe der monatlichen Raten zu ändern.
	 * 
	 * Wurde eine ID größer <b>-1</b> angegeben, so wird die ID in die Abfrage
	 * aufgenommen. Wurde als ID <b>-1</b> angegeben, wird für die ID ein 
	 * <b>?</b> als Platzhalter in die Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes.
	 * 
	 * @param sectionId Neue ID des Geschäftes.
	 * 
	 * @param months <b>True</b>, wenn es sich um die Anzahl der monatlichen
	 * Raten handelt. <b>False</b>, wenn es sich um die ID des Geschäftes
	 * handelt.
	 * 
	 * @return Datenbank-Abfrage, um in der Tabelle 'outstanding' das Geschäft
	 * zu ändern
	 */
	public String update(int id, int integer, boolean months) {
		StringBuilder ret;
		if (months)
			ret = new StringBuilder("UPDATE 'outstanding' SET months = ? " +
					"WHERE id = ?");
		else
			ret = new StringBuilder("UPDATE 'outstanding' SET sectionid = ? " +
					"WHERE id = ?");
		
		// ID ersetzen
		replaceId(id, ret, true);
		
		// ID des Geschäftes einfügen
		if (!months && (integer > -1))
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
					String.valueOf(integer));
		else if (months)
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
					String.valueOf(integer));
		
		// Abfrage zurückgeben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um in der Tabelle 'outstanding' die Höhe
	 * der Schulden oder die Höhe der monatlichen Raten zu ändern.
	 * 
	 * @param id ID des Datensatzes.
	 * 
	 * @param money Neuer Betrag für die Höhe der Schlulden
	 * 
	 * @param month <b>True</b>, wenn es sich um den Betrag für die monatlichen
	 * Raten handelt. <b>False</b>, wenn es sich um den Betrag für die Höhe
	 * der Schuld handelt.
	 * 
	 * @return Datenbank-Abfrage, um in der Tabelle 'outstanding' die Höhe der
	 * Schuld oder die Höhe der monatlichen Raten zu ändern.
	 */
	public String update(int id, double money, boolean month) {
		StringBuilder ret;
		if (month)
			ret = new StringBuilder("UPDATE 'outstanding' SET monthmoney = ? " +
					"WHERE id = ?");
		else
			ret = new StringBuilder("UPDATE 'outstanding' SET money = ? WHERE " +
					"id = ?");
		
		// ID ersetzen
		replaceId(id, ret, true);
		
		// Betrag
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
					String.valueOf(money));
		
		// Abfrage zurückgeben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um in der Tabelle 'outstanding' das Datum
	 * für die 1. Rate zu ändern.
	 * 
	 * @param id ID des Datensatzes.
	 * 
	 * @param start Neues Datum, für die 1. Rate
	 * 
	 * @return Datenbank-Abfrage, um in der Tabelle 'outstanding' das Datum der
	 * 1. Rate zu ändern.
	 */
	public String update(int id, long date) {
		StringBuilder ret = new StringBuilder("UPDATE 'outstanding' SET " +
				"start = ? WHERE id = ?");
		
		// ID ersetzen
		replaceId(id, ret, true);
		
		// Datum der 1. Rate
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
					String.valueOf(date));
		
		// Abfrage zurückgeben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um in der Tabelle 'outstanding' den
	 * Kommentart zu ändern.
	 * 
	 * @param id ID des Datensatzes.
	 * 
	 * @param comment Neuer Kommentar
	 * 
	 * @return Datenbank-Abfrage, um in der Tabelle 'outstanding' den Kommentar 
	 * zu ändern.
	 */
	public String update(int id, String comment) {
		StringBuilder ret = new StringBuilder("UPDATE 'outstanding' SET " +
				"comment = \"?\" WHERE id = ?");
		
		// ID ersetzen
		replaceId(id, ret, true);
		
		// Datum der 1. Rate
		if ((comment != null) && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		// Abfrage zurückgeben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um das Datum eines Datensatzes in der
	 * Tabelle "outstanding" zu ändern, ohne Kommentar. Wurde eine ID größer
	 * <b>-1</b> angegeben, so wird die ID in die Abfrage aufgenommen. Wurde
	 * als ID <b>-1</b> angegeben, word für die ID ein <b>?</b> als Platzhalter
	 * in die Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Daatensatzes, der geändert werden soll
	 * 
	 * @param sectionId ID des Geschäftes
	 * 
	 * @param money Betrag für die Höhe der Schuld
	 * 
	 * @param months Anzahl der monatlichen Raten
	 * 
	 * @param start Datum für die 1. Rate
	 * 
	 * @param monthMoney Betrag für die monatlichen Raten
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update (int id, int sectionId, double money, int months,
			long start, double monthMoney) {
		StringBuilder ret = new StringBuilder("UPDATE 'outstanding' SET " +
			"sectionid = ?, money = ?, months = ?, start = ?, monthmoney = ? " +
			"WHERE id = ?");
		
		// ID des Datensatzes
		replaceId(id, ret, true);
		
		// ID des Geschäftes
		// OPT In eine Methode stecken
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(sectionId));
		
		// Betrag für die Höhe der Schuld
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(money));
		
		// Anzahl der Monate
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(months));
		
		// Datum der 1. Rate
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(start));
		
		// Betrag für die monatlichen Raten
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1,
				String.valueOf(monthMoney));
		
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um das Datum eines Datensatzes in der
	 * Tabelle "outstanding" zu ändern, ohne Kommentar. Wurde eine ID größer
	 * <b>-1</b> angegeben, so wird die ID in die Abfrage aufgenommen. Wurde
	 * als ID <b>-1</b> angegeben, word für die ID ein <b>?</b> als Platzhalter
	 * in die Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Daatensatzes, der geändert werden soll
	 * 
	 * @param sectionId ID des Geschäftes
	 * 
	 * @param money Betrag für die Höhe der Schuld
	 * 
	 * @param months Anzahl der monatlichen Raten
	 * 
	 * @param start Datum für die 1. Rate
	 * 
	 * @param monthMoney Betrag für die monatlichen Raten
	 * 
	 * @param comment Neuer Kommentar
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update (int id, int sectionId, double money, int months,
			long start, double monthMoney, String comment) {
		StringBuilder ret = new StringBuilder("UPDATE 'outstanding' SET " +
			"sectionid = ?, money = ?, months = ?, start = ?, monthmoney = ?, " +
			"comment = \"?\" WHERE id = ?");
		
		// ID des Datensatzes
		replaceId(id, ret, true);
		
		// ID des Geschäftes
		// OPT In eine Methode stecken
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(sectionId));
		
		// Betrag für die Höhe der Schuld
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(money));
		
		// Anzahl der Monate
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(months));
		
		// Datum der 1. Rate
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, 
				String.valueOf(start));
		
		// Betrag für die monatlichen Raten
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1,
				String.valueOf(monthMoney));
		
		// Kommentar
		if ((comment != null) && !comment.isEmpty())
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		return ret.toString();
	}
}
