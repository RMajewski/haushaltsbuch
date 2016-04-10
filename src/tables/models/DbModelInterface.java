package tables.models;

/**
 * In diesem Interface werden Methoden deklariert, die auf den Zugriff auf
 * die Datenbank gewährleisten.
 * 
 * @author René Majewski
 */
public interface DbModelInterface {
	/**
	 * Liest alle Datensätze ein und Speichert diese Daten in einer Liste.
	 * 
	 * @param repaint Soll die Tabelle neu gezeichnet werden, so muss
	 * <b>true</b> übergeben werden. Wenn nicht, dann <b>false</b>
	 */
	public void dataRefresh(boolean repaint);

}
