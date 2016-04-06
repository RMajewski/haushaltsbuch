package datas;

/**
 * Speichert Informationen zu einer Kategorie.
 * 
 * @author René Majewski
 *
 */
public class CategoryData {
	/**
	 * Speichert die ID der Kategorie
	 */
	private int _id;
	
	/**
	 * Speichert den Namen der Kategorie
	 */
	private String _name;
	
	/**
	 * Initalisiert die ID mit -1 und einen leeren Namen
	 */
	public CategoryData() {
		setId(-1);
		setName(new String());
	}
	
	/**
	 * Initalisiert den Namen mit einen leeren. Übernimmt die übergebene ID.
	 * 
	 * @param id Neue ID der Kategorie
	 */
	public CategoryData(int id) {
		setId(id);
		setName(new String());
	}
	
	/**
	 * Initalisiert die Kategorie mit den übergenen Namen und der übergebenen
	 * ID
	 * 
	 * @param id Neue ID der Kategorie
	 * 
	 * @param name Neuer Name der Kategorie.
	 */
	public CategoryData(int id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * Gibt den Namen der Kategorie zurück
	 * 
	 * @return Name der Kategorie
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Setzt den namen der Kategorie auf den übergebenen Namen
	 * 
	 * @param name Neuer Name der Kategorie
	 */
	public void setName(String name) {
		if (name == null)
			_name = new String();
		else
			_name = name;
	}

	/**
	 * Gibt die ID der Kategorie zurüc
	 * 
	 * @return ID der Kategorie
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Setzt die Kategorie der ID auf den übergebenen Wert.
	 * 
	 * @param id Neue ID der Kategorie
	 */
	public void setId(int id) {
		_id = id;
	}

}
