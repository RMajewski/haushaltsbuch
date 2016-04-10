package db.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Von dieser Klasse werden die einzelnen Klassen abgeleitet, die
 * Datenbank-Abfragen bereit stellen.
 * 
 * Der Name der Datenbank-Tabelle und die Namen der einzelnen Spalten werden
 * dann gespeichert und können über Getter-Methoden abgerufen werden. 

 * @author René Majewski
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
}
