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

import java.util.GregorianCalendar;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'money'.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class Money extends Query {

	/**
	 * Initalisiert den Tabellen-Namen und die Spalten-Namen
	 */
	public Money() {
		super("money");
		_columnNames.add("id");
		_columnNames.add("date");
		_columnNames.add("inout");
		_columnNames.add("comment");
	}
	
	/**
	 * Ermittelt aus dem übergebenen boolschen-Wert, ob in die
	 * Datenbank-Abfrage eine 1 oder eine 0 eingefügt werden muss.
	 * 
	 * @param inout Handelt es sich um eine Einnahme oder eine Ausgabe?
	 * 
	 * @return Entsprechender Zahlenwert zu inout.
	 */
	private String inoutToString(boolean inout) {
		if (inout)
			return new String("1");
		else
			return new String("0");
	}

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
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, inoutToString(inout));
		
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
	public String insert(int id, long date, boolean inout, String comment) {
		// Rückgabe voerbereiten
		StringBuilder ret = new StringBuilder(insertWithId());
		
		// Id einfügen
		replaceId(id, ret, false);
		
		// Datum einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(date));
		
		// Einfügen, ob es eine Einname oder eine Ausgabe ist
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, inoutToString(inout));
		
		// Kommentar einfügen
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		else
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, new String());
		
		// Datenbank-Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um das Datum eines Datensatzes in der
	 * Tabelle "money" zu ändern. Wurde eine ID größer <b>-1</b> angegeben,
	 * so wird die ID in die Abfrage aufgenommen. Wurde als ID <b>-1</b>
	 * angegeben, word für die ID ein <b>?</b> als Platzhalter in die
	 * Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @param date Datum, das in den Datensatz eingefügt werden soll.
	 * 
	 * @param inout Handelt es sich um eine Einnahme oder eine Ausgabe?
	 * 
	 * @param comment Beschreibung des Datensatzes
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update(int id, long date, boolean inout, String comment) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder(update(id));
		
		// Datum einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(date));
		
		// Einfügen ob es sich um eine Einnahme oder eine Ausgabe handelt
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, inoutToString(inout));
		
		// Soll der Kommentar eingefügt werden?
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um das Datum eines Datensatzes in der
	 * Tabelle "money" zu ändern, ohne Kommentar. Wurde eine ID größer
	 * <b>-1</b> angegeben, so wird die ID in die Abfrage aufgenommen. Wurde
	 * als ID <b>-1</b> angegeben, word für die ID ein <b>?</b> als Platzhalter
	 * in die Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @param date Datum, das in den Datensatz eingefügt werden soll.
	 * 
	 * @param inout Handelt es sich um eine Einnahme oder eine Ausgabe?
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update(int id, long date, boolean inout) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("UPDATE 'money' SET date = ?, inout = ?, comment = '' WHERE id = ?");
		
		// ID einfügen?
		replaceId(id, ret, true);
		
		// Datum einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(date));
		
		// Einfügen ob es sich um eine Einnahme oder eine Ausgabe handelt
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, inoutToString(inout));
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um das Datum eines Datensatzes in der
	 * Tabelle "money" zu ändern. Wurde eine ID größer <b>-1</b> angegbeen,
	 * so wird die ID in die Abfrage aufgenommen. Wurde als ID <b>-1</b>
	 * angegeben, wird für die ID ein <b>?</b> als Platzhalter in die
	 * Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @param date Neues Datum, das in den Datensatz gespeichert werde soll.
	 * 
	 * @return Datenbank-Abfrage, um das Datum im angegeben Datensatz zu
	 * ändern.
	 */
	public String update(int id, long date) {
		// Abfrage zum ändern des Datums im angegeben Datensatz
		StringBuilder ret = new StringBuilder("UPDATE 'money' SET date = ? WHERE id = ?");

		// ID einfügen
		replaceId(id, ret, true);
		
		// Datum einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1,	String.valueOf(date));
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um in der Tabelle "money" zu ändern, ob
	 * es sich um eine Einnahme oder Ausgabe handelt. Wurde eine ID größer 
	 * <b>-1</b> angegbeen, so wird die ID in die Abfrage aufgenommen. Wurde
	 * als ID <b>-1</b> angegeben, wird für die ID ein <b>?</b> als Platzhalte
	 * in die Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @param inout Handelt es sich um eine Einnahme oder eine Ausgabe?
	 * 
	 * @return Datenbank-Abfrage, um zu ändern, ob es sich um eine Einnahme
	 * oder eine Ausgabe handelt.
	 */
	public String update(int id, boolean inout) {
		// Abfrage zum ändern, ob es sich um eine Einnahme oder Ausgabe handelt
		StringBuilder ret = new StringBuilder("UPDATE 'money' SET inout = ? WHERE id = ?");

		// ID einfügen
		replaceId(id, ret, true);
		
		// Einfügen, ob es sich um eine Einnahme oder eine Ausgabe handelt
		if (inout)
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, "1");
		else
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, "0");
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um die Beschreibung eines Datensatzes
	 * in der Tabelle "money" zu ändern. Wurde eine ID größer <b>-1</b>
	 * angegeben, so wird die ID in die Abfrage aufgenommen. Wurde als ID
	 * <b>-1</b> angegeben, wird für die ID ein <b>?</b> als Platzhalter in die
	 * Datenbankabfrage übernommen.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @param comment Neue Beschreibung, die in den Datensatz gespeichert werde
	 * soll.
	 * 
	 * @return Datenbank-Abfrage, um die Beschreibung im angegeben Datensatz zu
	 * ändern.
	 */
	public String update(int id, String comment) {
		// Abfrage zum ändern der Beschreibung im angegeben Datensatz
		StringBuilder ret = new StringBuilder("UPDATE 'money' SET comment ='?' WHERE id = ?");

		// ID einfügen
		replaceId(id, ret, true);
		
		// Beschreibung einfügen
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um alle Datensätze der angegebenen Woche
	 * zu selektieren. Zudem wird noch angegeben, ob Einnahmen oder Ausgaben
	 * selektiert werden soll.
	 * 
	 * @param from Start-Datum als long-Wert
	 * 
	 * @param to End-Datum als long-Wert
	 * 
	 * @param inout Einnahmen oder Ausgaben selektieren?
	 * 
	 * @return Datenbank-Abfrage, um Datensätze der angegebenen Woche zu
	 * selektieren.
	 */
	public String selectWeek(long from, long to, int inout) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT id FROM ");
		ret.append(_tableName);
		ret.append(" WHERE date BETWEEN ");		
		ret.append(from);
		ret.append(" AND ");
		ret.append(to);
		ret.append(" AND inout = ");
		ret.append(inout);
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um alle Datensätze des angegebenen Tages
	 * zu selektieren. Zudem wird noch angegeben, ob Einnahmen oder Augaben
	 * selektiert werden sollen.
	 * 
	 * @param day Tag, von dem die Datensätze selektiert werden sollen.
	 * 
	 * @param inout Einnahmen oder Ausgaben selektieren?
	 * 
	 * @return Datenbank-Abfrage , um Datenätze des angegebenen Tages zu
	 * selektieren.
	 */
	public String selectDay(long day, int inout) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(day);
		gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
		gc.set(GregorianCalendar.MINUTE, 59);
		gc.set(GregorianCalendar.SECOND, 59);
		gc.set(GregorianCalendar.MILLISECOND, 999);
		return selectWeek(day, gc.getTimeInMillis(), inout);
	}
}
