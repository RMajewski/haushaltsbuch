package datas;

// FIXME SectionData und CategoryData zusammen fassen

/**
 * Speichert Infortmationen zu einem Geschäft.
 * 
 * @author René Majewski
 * 
 * @deprecated
 */
public class SectionData {
	/**
	 * Speichert die ID des Geschäfts.
	 */
	private int _id;
	
	/**
	 * Speichert den Namen des Geschäfts.
	 */
	private String _name;
	
	/**
	 * Initalisiert die ID mit -1 und einen leeren Namen
	 */
	public SectionData() {
		setId(-1);
		setName(new String());
	}
	
	/**
	 * Initalisiert den Namen mit einer leeren Zeichenkette. Übernimmt die
	 * übergebene ID.
	 * 
	 * @param id Neue ID des Geschäftes.
	 */
	public SectionData(int id) {
		setId(id);
		setName(new String());
	}
	
	/**
	 * Initalisiert das Geschäft mit den übergebenen Namen und der übergebenen
	 * ID
	 * 
	 * @param id Neue ID des Geschäftes
	 * 
	 * @param name Neuer Name des Geschäftes.
	 */
	public SectionData(int id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * Gibt den Namen des Geschäftes zurück.
	 * 
	 * @return Name des Geschäftes
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Setzt den Namen des Geschäftes auf den übergebenen Namen
	 * 
	 * @param name Neuer Name des Geschäftes
	 */
	public void setName(String name) {
		if (name == null)
			_name = new String();
		else
			_name = name;
	}

	/**
	 * Gibt die ID des Geschäftes zurück
	 * 
	 * @return ID des Geschäftes
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Setzt die ID des Geschäftes auf den übergebenen Wert.
	 * 
	 * @param id Neue ID des Geschäftes
	 */
	public void setId(int id) {
		_id = id;
	}
	
}
