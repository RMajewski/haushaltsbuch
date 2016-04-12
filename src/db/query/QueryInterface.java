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
	
	/**
	 * Gibt eine Zeichenkette zurück, in der alle Datensätze zurück gegeben
	 * werden.
	 */
	public String select();
	
	/**
	 * Gibt eine Zeichenkette zurück, in der die Anzahl der Datensätze zurück
	 * gegeben werden.
	 */
	public String count();
	
	/**
	 * Gibt eine Zeichenkette zurück, in dem nach einen Datensatz gesucht wird.
	 * 
	 * @param col Name der Spalte, in der gesucht werden soll
	 * 
	 * @param str Zeichenfolge, nach der gesucht werden soll
	 */
	public String search(String col, String str);
	
	/**
	 * Meldung: Datensatz wurde eingefügt
	 */
	public String statusInsertOk();
	
	/**
	 * Meldung: Datensatz konnte nicht eingefügt werden
	 */
	public String statusInsertError();
	
	/**
	 * Meldung: Datensatz wurde geändert
	 */
	public String statusUpdateOk(int id);
	
	/**
	 * Meldung: Datensatz konnte nicht geändert werden
	 */
	public String statusUpdateError(int id);
	
	/**
	 * Meldung: Datensatz wurde gelöscht
	 */
	public String statusDeleteOk(int id);
	
	/**
	 * Meldung: Datensatz konnt nicht gelöscht werden.
	 */
	public String statusDeleteError(int id);
}
