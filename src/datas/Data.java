package datas;

/**
 * Grundlagen-Klasse für die Daten. Hier werden Methoden implementiert, die
 * in alle Datensatz-Klassen vorhanden sind.
 * 
 * @author René Majewski
 */
public class Data {
	/**
	 * Speichert die ID
	 */
	protected int _id;
	
	/**
	 * Initalisiert die Klasse.
	 * 
	 * @param id ID, die diesem Datensatz zugewiesen wurde.
	 */
	public Data(int id) {
		_id = id;
	}
	
	/**
	 * Gibt die ID zurück
	 * 
	 * @return ID, die diesem Datensatz zugewiesen wurde.
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Speichert die neue ID.
	 * 
	 * @param id Neue ID, die diesem Datensatz zugewiesen werden soll.
	 */
	public void setId(int id) {
		_id = id;
	}
}
