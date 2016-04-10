package test.db.query;

import db.query.Query;

/**
 * Beinhaltet eine Implementation der {@link db.query.Query}-Klasse zum
 * testen der implementierten Methoden.
 * 
 * @author René Majewski
 *
 */
public class TestQueryImplementation extends Query {
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @param table Name der Tabelle
	 * 
	 * @param col1 Name der 1. Spalte
	 * 
	 * @param col2 Name der 2. Spalte
	 */
	public TestQueryImplementation(String table, String col1, String col2) {
		super(table);
		
		_columnNames.add(col1);
		_columnNames.add(col2);
	}
	
	/**
	 * Ruft zum testen die Methode {@link db.query.Query#replaceId(int, StringBuilder, boolean)}
	 * auf
	 * 
	 * @param id ID, die in die Zeichenkette geschrieben werden soll.
	 * 
	 * @param builder Zeichenkette, in der das Fragezeichen überschrieben
	 * werden soll.
	 * 
	 * @param last Soll das Fragezeichen an letzter Position oder an erster
	 * Position überschrieben werden?
	 */
	public void testReplayQuery(int id, StringBuilder builder, boolean last) {
		replaceId(id, builder, last);
	}
	

	/**
	 * Dieses Methode wird für den Test der Query-Klasse nicht gebraucht.
	 * 
	 * @return Es wird lediglich <b>null</b> zurück gegeben.
	 */
	@Override
	public String createTable() {
		return null;
	}

	/**
	 * Dieses Methode wird für den Test der Query-Klasse nicht gebraucht.
	 * 
	 * @return Es wird lediglich <b>null</b> zurück gegeben.
	 */
	@Override
	public String insert() {
		return null;
	}

	/**
	 * Dieses Methode wird für den Test der Query-Klasse nicht gebraucht.
	 * 
	 * @return Es wird lediglich <b>null</b> zurück gegeben.
	 */
	@Override
	public String delete(int id) {
		return null;
	}

	/**
	 * Dieses Methode wird für den Test der Query-Klasse nicht gebraucht.
	 * 
	 * @return Es wird lediglich <b>null</b> zurück gegeben.
	 */
	@Override
	public String update(int id) {
		return null;
	}

}
