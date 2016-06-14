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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import haushaltsbuch.db.DbController;

/**
 * Enthält alle Datenbank-Abfragen für die Tabelle 'money_details'.
 * 
 * In Version 0.2 wird die Tabelle um die Spalte "paymentid" erweitert. In
 * dieser Spalte wird gespeichert, welches Zahlungsmittel benutzt wurde.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
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
		_columnNames.add("paymentid");
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
				"'comment' TEXT, " +
				"'paymentid' INTEGER NOT NULL DEFAULT 1)";
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
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Eintrag in die Tabelle
	 * "money_details" zu erzeugen. Für die 'moneyid', 'category' und 'section'
	 * werden die IDs übernommen. Sollte ein eine <b>-1</b übergeben werden, so
	 * wird als Platzhalter ein <b>?</b> genommen. Das Geld wird so übernommen,
	 * wie es angegeben wurde. Solle eine <b>null</b> oder eine leere
	 * Zeichenkette als Kommentar übergeben werden, so wird als Platzhalter das
	 * <b>?</b> benutzt. Sollte eine Zeichenkette übergeben werden, so wird
	 * diese Zeichenkette in die Datenbank-Abfrage übernommen.
	 * 
	 * @param id ID des Datensatzes
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
	public String insert(int id, int moneyid, int categoryid, int sectionid, double money, String comment) {
		// Vorberung der Abfrage
		StringBuilder ret = new StringBuilder("INSERT INTO 'money_details' ('id', 'moneyid', 'categoryid', 'sectionid', 'money', 'comment') VALUES(");
		
		// ID einfügen
		ret.append(String.valueOf(id));
		ret.append(", ");
		
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
		StringBuilder ret = new StringBuilder("SELECT id, moneyid, categoryid, sectionid, money, comment, paymentid FROM money_details WHERE moneyid = ? ORDER BY id ASC");
		
		// Money-ID einfügen
		replaceId(id, ret, false);
		
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
	 * @param moneyid ID des zugehörigen Money-Datensatzes
	 * 
	 * @param categoryid ID der Kategorie
	 * 
	 * @param sectionid ID des Geschäftes
	 * 
	 * @param money Betrag, der eingezahlt oder ausgegeben wurde.
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 * 
	 * @deprecated use {@link #update(int, int, int, int, double, int)}
	 */
	public String update(int id, int moneyid, int categoryid, int sectionid, double money) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("UPDATE 'money_details' SET moneyid = ?, categoryid = ?, sectionid = ?, money = ?, comment = \"\" WHERE id = ?");
		
		// ID einfügen?
		replaceId(id, ret, true);
		// Money-ID, ID der Kategrie, ID des Geschäfts und Betrag einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(moneyid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(categoryid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(sectionid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(money));
		
		// Abfrage zurück geben
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
	 * @param moneyid ID des zugehörigen Money-Datensatzes
	 * 
	 * @param categoryid ID der Kategorie
	 * 
	 * @param sectionid ID des Geschäftes
	 * 
	 * @param money Betrag, der eingezahlt oder ausgegeben wurde.
	 * 
	 * @param paymentId ID des Zahlungsmittels
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update(int id, int moneyid, int categoryid, int sectionid,
			double money, int paymentId) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("UPDATE 'money_details' SET moneyid = ?, categoryid = ?, sectionid = ?, money = ?, comment = \"\", paymentid = ? WHERE id = ?");
		
		// ID einfügen?
		replaceId(id, ret, true);
		// Money-ID, ID der Kategrie, ID des Geschäfts und Betrag einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(moneyid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(categoryid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(sectionid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(money));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(paymentId));
		
		// Abfrage zurück geben
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
	 * @param moneyid ID des zugehörigen Money-Datensatzes
	 * 
	 * @param categoryid ID der Kategorie
	 * 
	 * @param sectionid ID des Geschäftes
	 * 
	 * @param money Betrag, der eingezahlt oder ausgegeben wurde.
	 * 
	 * @param comment Beschreibung des Datensatzes
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 * 
	 * @deprecated
	 */
	public String update(int id, int moneyid, int categoryid, int sectionid, double money, String comment) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder(update(id));
		
		// Money-ID, ID der Kategrie, ID des Geschäfts und Betrag einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(moneyid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(categoryid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(sectionid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(money));
		
		// Soll der Kommentar eingefügt werden?
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		// Abfrage zurück geben
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
	 * @param moneyid ID des zugehörigen Money-Datensatzes
	 * 
	 * @param categoryid ID der Kategorie
	 * 
	 * @param sectionid ID des Geschäftes
	 * 
	 * @param money Betrag, der eingezahlt oder ausgegeben wurde.
	 * 
	 * @param comment Beschreibung des Datensatzes
	 * 
	 * @param paymentId ID des Zahlungsmittels
	 * 
	 * @return Datenbank-Abfrage, um den angegeben Datensatz zu ändern.
	 */
	public String update(int id, int moneyid, int categoryid, int sectionid, 
			double money, String comment, int paymentId) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder(update(id));
		
		// Money-ID, ID der Kategrie, ID des Geschäfts und Betrag einfügen
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(moneyid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(categoryid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(sectionid));
		ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, String.valueOf(money));
		
		// Soll der Kommentar eingefügt werden?
		if (comment != null && !comment.isEmpty())
			ret.replace(ret.indexOf("?"), ret.indexOf("?") + 1, comment);
		
		ret.replace(ret.lastIndexOf("?"), ret.lastIndexOf("?") + 1, String.valueOf(paymentId));
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbankabfrage, um für die angegben Money-ID alle Beträge
	 * zusammen zu addieren.
	 * 
	 * @param moneyid ID des Datensatzes von 'money', für den die Summe der
	 * Beträge ermittelt werden soll.
	 * 
	 * @return Summe der Beträge für den ausgewählen Money-Datensatz
	 */
	public String sumMoneyId(int moneyid) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT sum(money) FROM ");
		ret.append(_tableName);
		ret.append(" WHERE moneyid = ");
		ret.append(moneyid);
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbankabfrage, um für die angegebene Kategorie-ID alle
	 * Beträge ermittelt werden soll.
	 * 
	 * @param category Name der Kategorie
	 * 
	 * @param inout Legt fest, ob die Einnahmen oder die Ausgaben der Kategorie
	 * ermittelt werden soll.
	 * 
	 * @return Summe der Beträge für die ausgewählte Kategorie
	 */
	public String sumCategoryId(String category, int inout) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT sum(money) FROM ");
		ret.append(_tableName);
		ret.append(" INNER JOIN money ON money.id = money_details.moneyid");
		ret.append(" INNER JOIN category ON category.id = ");
		ret.append(" money_details.categoryid WHERE category.name = '");
		ret.append(category);
		ret.append("' AND inout = ");
		ret.append(inout);
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbankabfrage, um für das angegebene Geschäft alle Beträge
	 * zu ermitteln.
	 * 
	 * @param section Name des Geschäftes
	 * 
	 * @param inout Legt fest, ob die Einnahmen oder die Ausgaben der Kategorie
	 * ermittelt werden soll.
	 * 
	 * @return Summe der Beträge für das ausgewählte Geschäft
	 */
	public String sumSectionId(String section, int inout) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT sum(money) FROM ");
		ret.append(_tableName);
		ret.append(" INNER JOIN money ON money.id = money_details.moneyid");
		ret.append(" INNER JOIN section ON section.id = ");
		ret.append(" money_details.sectionid WHERE section.name = '");
		ret.append(section);
		ret.append("' AND inout = ");
		ret.append(inout);
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Spalte "paymentid", wenn sie noch nicht exitiert.
	 * 
	 * @return Gibt true zurück, wenn kein Fehler aufgetreten ist. Ist ein
	 * Fehler aufgetreten, so wird false zurück gegeben.
	 */
	public boolean upgrade1() throws SQLException {
		Statement stm = DbController.getInstance().createStatement();
		
		ResultSet rs = stm.executeQuery("SELECT * FROM sqlite_master WHERE " +
				"tbl_name = '" + _tableName + "' AND type = 'table'");
		
		if (rs.getString(5).indexOf("paymentid") == -1) {
			DbController.getInstance().createStatement().executeUpdate(
					"ALTER TABLE '" + _tableName + "' ADD COLUMN 'paymentid' " +
					"INTEGER DEFAULT 1");
		}
		
		rs.close();
		return true;
	}
}
