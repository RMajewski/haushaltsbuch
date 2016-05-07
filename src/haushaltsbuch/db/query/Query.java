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

import java.util.ArrayList;
import java.util.List;

/**
 * Von dieser Klasse werden die einzelnen Klassen abgeleitet, die
 * Datenbank-Abfragen bereit stellen.
 * 
 * Der Name der Datenbank-Tabelle und die Namen der einzelnen Spalten werden
 * dann gespeichert und können über Getter-Methoden abgerufen werden. 

 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public abstract class Query implements QueryInterface {
	/**
	 * Speichert den Namen der der Datenbank-Tabelle
	 */
	protected String _tableName;
	
	/**
	 * Speichert die Liste mit den Spalten-Namen
	 */
	protected List<String> _columnNames;

	/**
	 * Initalisiert diese Klasse
	 * 
	 * @param name Name der Datenbank-Tabelle
	 */
	public Query(String name) {
		_tableName = name;
		_columnNames = new ArrayList<String>();
	}
	
	/**
	 * Überprüft die angegebene ID, ob sie größer als <b>-1</b> ist. Ist dies
	 * der Fall, so wird die angegebene Stelle mit der ID überschrieben. 
	 * 
	 * @param id ID, die in der Zeichenkette ersetzt werden soll.
	 * 
	 * @param builder Zeichenkette, in die die ID geschrieben werde soll.
	 * 
	 * @param last Wird <b>true</b> übergeben, so wird das letzte Fragezeichen
	 * mit der ID überschrieben. <b>false</b>, so wird das erste Fragenzeichen
	 * mit der ID überschrieben.
	 */
	protected void replaceId(int id, StringBuilder builder, boolean last) {
		if (id > -1)
			if (last)
				builder.replace(builder.lastIndexOf("?"), builder.lastIndexOf("?") + 1, String.valueOf(id));
			else
				builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, String.valueOf(id));
	}
	
	/**
	 * Liefert den Namen der zugehörigenen Datenbank-Tabelle
	 * 
	 * @return Name der Datenbank-Tabelle
	 */
	public String getTableName() {
		return _tableName;
	}
	
	/**
	 * Liefert eine Liste mit allen Spalten-Namen
	 * 
	 * @return Liste mit allen Spalten-Namen
	 */
	public List<String> getColumnNames() {
		return _columnNames;
	}
	
	/**
	 * Liefert die Anzahl der Spalten.
	 * 
	 * @return Anzahl der Spalten
	 */
	public int getCloumnCount() {
		return _columnNames.size();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um alle Datensätze anzuzeigen.
	 * Die Datensätze werden nach den IDs geordnet.
	 * 
	 * Diese Abfrage wird mit Hilfe der gespeicherten Spalten-Namen
	 * und des gespeicherten Tabellen-Namens erstellt.
	 * 
	 * @return Datenbank-Abfrage, um alle Datensätze aufzulisten
	 */
	@Override
	public String select() {
		return sort(null);
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Datensatz in die Tabelle
	 * einzufügen.
	 * 
	 * Diese Abfrage wird mit Hilfe der gespeicherten Spalten-Namen
	 * und des gespeicherten Tabellen-Namens erstellt.
	 * 
	 * Es werden Anführungszeichen um die Fragezeichen gesetzt.
	 * 
	 * @return Datenbank-Abfrage um neuen Datensatz einzufügen
	 */
	@Override
	public String insert() {
		return insert(true);
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Datensatz in die Tabelle
	 * einzufügen.
	 * 
	 * Diese Abfrage wird mit Hilfe der gespeicherten Spalten-Namen
	 * und des gespeicherten Tabellen-Namens erstellt.
	 * 
	 * @return Datenbank-Abfrage um neuen Datensatz einzufügen
	 * 
	 * @param quotes Bei <b>true</b> werden die Anführungszeichen gesetzt.
	 * Bei <b>false</b> werden keine Anführungszeichen gesetzt.
	 */
	public String insert(boolean quotes) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("INSERT INTO ");
		
		// Anführungszeichen
		String quote = new String("");
		if (quotes)
			quote = new String("\"");
		
		// Tabellen-Namen einfügen
		ret.append(_tableName);
		ret.append(" (");
		
		// Spalten-Namen einfügen (id auslassen)
		boolean first = true;
		int queries = 0;
		for (int i = 0; i < _columnNames.size(); i++) {
			if (!_columnNames.get(i).equals("id")) {
				if (first)
					first = false;
				else
					ret.append(", ");
				ret.append(_columnNames.get(i));
				queries++;
			}
		}
		
		// Anzahl Fragezeichen einfügen
		ret.append(") VALUES (");
		for (int i = 0; i < queries; i++) {
			if (i > 0)
				ret.append(", ");
			
			ret.append(quote);
			ret.append("?");
			ret.append(quote);
		}
		ret.append(");");
		
		// Abfrage zurück geben
		return ret.toString();
	}

	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Datensatz in die Tabelle
	 * einzufügen. Es wird auch die ID mit in die Abfrage eingebaut.
	 * 
	 * Diese Abfrage wird mit Hilfe der gespeicherten Spalten-Namen
	 * und des gespeicherten Tabellen-Namens erstellt.
	 * 
	 * @return Datenbank-Abfrage um neuen Datensatz einzufügen
	 * 
	 * @param quotes Bei <b>true</b> werden die Anführungszeichen gesetzt.
	 * Bei <b>false</b> werden keine Anführungszeichen gesetzt.
	 */
	public String insertWithId() {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("INSERT INTO ");
		
		// Anführungszeichen
		String quote = new String("\"");
		
		// Tabellen-Namen einfügen
		ret.append(_tableName);
		ret.append(" (");
		
		// Spalten-Namen einfügen (id auslassen)
		boolean first = true;
		int queries = 0;
		for (int i = 0; i < _columnNames.size(); i++) {
			if (first)
				first = false;
			else
				ret.append(", ");
			ret.append(_columnNames.get(i));
			queries++;
		}
		
		// Anzahl Fragezeichen einfügen
		ret.append(") VALUES (");
		for (int i = 0; i < queries; i++) {
			if (i > 0)
				ret.append(", ");
			
			ret.append(quote);
			ret.append("?");
			ret.append(quote);
		}
		ret.append(");");
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Datensatz zu ändern.
	 * Wird als ID <b>-1</b> übergeben, so wird als Platzhalter ein
	 * Fragenzeichen eingesetzt. Wenn nicht, so wird die ID mit in der
	 * Abfrage erstellt.
	 * 
	 * Diese Abfrage wird mit Hilfe der gespeicherten Spalten-Namen
	 * und des gespeicherten Tabellen-Namens erstellt.
	 * 
	 * @param id ID des Datensatzes, der geändert werden soll.
	 * 
	 * @return Datenbank-Abfrage um einen Datensatz zu ändern
	 */
	@Override
	public String update(int id) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("UPDATE ");
		
		// Tabellen-Namen einfügen
		ret.append(_tableName);
		ret.append(" SET ");
		
		// Spalten mit Fragenzeichen einfügen ( id auslassen)
		boolean first = true;
		for (int i = 0; i < _columnNames.size(); i++) {
			if (!_columnNames.get(i).equals("id")) {
				if (first)
					first = false;
				else
					ret.append(", ");
				
				ret.append(_columnNames.get(i));
				ret.append(" = '?'");
			}
		}
		
		// Abfrage abschließen
		ret.append(" WHERE id = ?");
		
		// ID einfügen
		replaceId(id, ret, true);
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, um einen neuen Datensatz löschen.
	 * Wird als ID <b>-1</b> übergeben, so wird als Platzhalter ein
	 * Fragenzeichen eingesetzt. Wenn nicht, so wird die ID mit in der
	 * Abfrage erstellt.
	 * 
	 * Diese Abfrage wird mit Hilfe der gespeicherten Spalten-Namen
	 * und des gespeicherten Tabellen-Namens erstellt.
	 * 
	 * @param id ID des Datensatzes, der gelöscht werden soll.
	 * 
	 * @return Datenbank-Abfrage um einen Datensatz zu löschen
	 */
	@Override
	public String delete(int id) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("DELETE FROM ");
		
		// Tabellen-Namen einfügen und Abfrage abschließen
		ret.append(_tableName);
		ret.append(" WHERE id = ?");
		
		// ID ersetzen
		replaceId(id, ret, true);
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, in der die Anzahl der Datensätze
	 * ermittelt wird.
	 * 
	 * @return Datenbank-Abfrage, in der Anzah der Datensätze ermittelt wird.
	 */
	@Override
	public String count() {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT count(*) FROM '");
		ret.append(_tableName);
		ret.append("'");
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, in der ein bestimmter Datensatz
	 * ausgewählt werden soll.
	 * 
	 * @param col Spalte, in der gesucht werden soll.
	 * 
	 * @param str Zeichenkette nach der gesucht werden soll.
	 * 
	 * @return Datenbank-Abfrage, in der ein bestimmter Datensatz ausgewählt
	 * werden soll.
	 */
	@Override
	public String search(String col, String str) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT ");
		
		// Spalten-Namen einfügen
		for (int i = 0; i < _columnNames.size(); i++) {
			if (i == 0)
				ret.append(_columnNames.get(i));
			else {
				ret.append(", ");
				ret.append(_columnNames.get(i));
			}
		}
		
		// Tabellen-Name einfügen
		ret.append(" FROM ");
		ret.append(_tableName);
		
		// Suche einfügen
		ret.append(" WHERE ");
		ret.append(col);
		ret.append(" = \"");
		ret.append(str);
		ret.append("\"");
		
		// Nach was wird geordnet?
		ret.append(" ORDER BY id ASC");
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, in der ein bestimmter Datensatz
	 * ausgewählt werden soll.
	 * 
	 * @param col Spalte, in der gesucht werden soll.
	 * 
	 * @param str Inter-Wert, nach dem gesucht werden soll.
	 * 
	 * @return Datenbank-Abfrage, in der ein bestimmter Datensatz ausgewählt
	 * werden soll.
	 */
	@Override
	public String search(String col, int str) {
		// Abfrage vorbereiten
		StringBuilder ret = new StringBuilder("SELECT ");
		
		// Spalten-Namen einfügen
		for (int i = 0; i < _columnNames.size(); i++) {
			if (i == 0)
				ret.append(_columnNames.get(i));
			else {
				ret.append(", ");
				ret.append(_columnNames.get(i));
			}
		}
		
		// Tabellen-Name einfügen
		ret.append(" FROM ");
		ret.append(_tableName);
		
		// Suche einfügen
		ret.append(" WHERE ");
		ret.append(col);
		ret.append(" = ");
		ret.append(str);
		
		// Nach was wird geordnet?
		ret.append(" ORDER BY id ASC");
		
		// Abfrage zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Datenbank-Abfrage, in der nach einer bestimmten Spalte
	 * sortiert werden soll.
	 * 
	 * @param col Spalte, nach der sortiert werden soll.
	 * 
	 * @return Datenbank-Abfrage, wo nach einer bestimmten Spalte sortiert
	 * wird.
	 */
	@Override
	public String sort(String col) {
		// Rückgabe vorbereiten
		StringBuilder ret = new StringBuilder("SELECT ");
		
		// Spalten-Namen einfügen
		for (int i = 0; i < _columnNames.size(); i++) {
			if (i == 0)
				ret.append(_columnNames.get(i));
			else {
				ret.append(", ");
				ret.append(_columnNames.get(i));
			}
		}
		
		// Tabellen-Name einfügen
		ret.append(" FROM ");
		ret.append(_tableName);
		
		// Nach was wird geordnet?
		ret.append(" ORDER BY ");
		if (col == null || col.isEmpty())
			ret.append("id");
		else
			ret.append(col);
		ret.append(" ASC");
		
		// Rückgabe der Datenbank-Abfrage
		return ret.toString();
	}
	
	/**
	 * Gibt die Status-Nachricht fürs Einfügen eines Datensatzes zurück.
	 * 
	 * @return Status-Nachricht fürs Einfügen eines Datensatzes
	 */
	@Override
	public String statusInsertOk() {
		// Nachricht vorbereiten
		StringBuilder ret = new StringBuilder("Datenbank: In die Tabelle '");
		ret.append(_tableName);
		ret.append("' wurde ein Datensatz eingefügt.");
		
		// Nachricht zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Status-Nachricht, wenn der Datensatz nicht eingefügt werden
	 * kann.
	 * 
	 * @return Status-Nachricht, wenn Datensatz nicht eingefügt werden konnte.
	 */
	@Override
	public String statusInsertError() {
		// Nachricht vorbereiten
		StringBuilder ret = new StringBuilder("Datenbank: In die Tabelle '");
		ret.append(_tableName);
		ret.append("' konnte kein Datensatz eingefügt werden.");
		
		// Nachricht zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Status-Nachricht, fürs ändern einen Datensatzes.
	 * 
	 * @return Status-Nachricht, wenn Datensatz geändert wurde.
	 */
	@Override
	public String statusUpdateOk(int id) {
		// Nachricht vorbereiten
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" aus der Tabelle '");
		ret.append(_tableName);
		ret.append("' wurde geändert.");
		
		// nachrihct zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Status-Nachricht, wenn der Datensatz nicht geändert werden
	 * kann.
	 * 
	 * @return Status-Nachricht, wenn Datensatz nicht geändert werden konnte
	 */
	@Override
	public String statusUpdateError(int id) {
		// Nachricht vorbereiten
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" aus der Tabelle '");
		ret.append(_tableName);
		ret.append("' konnte nicht geändert werden.");
		
		// Nachricht zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Status-Nachrciht, wenn ein Datensatz gelöscht wurde.
	 * 
	 * @return Status-Nachricht, wenn Datensatz gelöscht wurde.
	 */
	@Override
	public String statusDeleteOk(int id) {
		// Nachricht vorbereiten
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" wurde aus der Tabelle '");
		ret.append(_tableName);
		ret.append("' gelöscht.");
		
		// Nachricht zurück geben
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Status-Nachricht, wenn ein Datensatz nicht gelöscht werden
	 * konnte.
	 * 
	 * @return Status-Nachricht, wenn Datensatz nicht gelöscht werden konnte.
	 */
	@Override
	public String statusDeleteError(int id) {
		// Nachricht vorbereiten
		StringBuilder ret = new StringBuilder("Datenbank: Der Datensatz mit der ID ");
		ret.append(id);
		ret.append(" konnte nicht aus der Tabelle '");
		ret.append(_tableName);
		ret.append("' gelöscht werden.");
		
		// Nachricht zurück geben
		return ret.toString();
	}
}
