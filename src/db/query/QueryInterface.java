package db.query;

/**
 * In diesem Interface werden die Methoden deklariert, die in einer
 * Abfrage-Klasse mindestens enthalten sein sollen. So etwa, createTable,
 * insert, delete und update.
 * 
 * @author René Majewski
 *
 */
public interface QueryInterface {
	/**
	 * Gibt eine Zeichenkette zurück, in der die Tabelle angelegt wird.
	 */
	public String createTable();
	
	/**
	 * Gibt eine Zeichenkette zurück, in der ein Datensatz neu angelegt wird.
	 */
	public String insert();
	
	/**
	 * Gibt eine Zeichenkette zurück, in der der angegebene Datensatz gelöscht
	 * wird.
	 * 
	 * @param id ID des Datensatzes
	 */
	public String delete(int id);
	
	/**
	 * Gibt eine Zeichenkette zurück, in der der angegebene Datensatz geändert
	 * wird.
	 * 
	 * @param id ID des Datensatzes
	 */
	public String update(int id);
}
