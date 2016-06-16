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


}
