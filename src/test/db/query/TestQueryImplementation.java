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

}
