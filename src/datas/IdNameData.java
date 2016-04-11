package datas;

/**
 * Speichert ID und Name.
 * 
 * @author René Majewski
 */
public class IdNameData extends Data {
	
	/**
	 * Speichert den Namen
	 */
	private String _name;
	
	/**
	 * Initalisiert die ID mit -1 und einen leeren Namen
	 */
	public IdNameData() {
		super(-1);
		setName(new String());
	}
	
	/**
	 * Initalisiert den Namen mit einen leeren Zeichenkette. Übernimmt die
	 * übergebene ID.
	 * 
	 * @param id Neue ID
	 */
	public IdNameData(int id) {
		super(id);
		setName(new String());
	}
	
	/**
	 * Initalisiert die Daten mit der Übergebenen ID und den übergenen Namen.
	 * 
	 * @param id Neue ID
	 * 
	 * @param name Neuer Name
	 */
	public IdNameData(int id, String name) {
		super(id);
		setName(name);
	}

	/**
	 * Gibt den Namen zurück
	 * 
	 * @return Gespeicherter Name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Speichert den übergebenen Namen.
	 * 
	 * @param name Neuer Name, der gespeichert werden soll
	 */
	public void setName(String name) {
		if (name == null)
			_name = new String();
		else
			_name = name;
	}
}
