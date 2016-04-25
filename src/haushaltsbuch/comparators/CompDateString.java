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

package haushaltsbuch.comparators;

import java.util.Comparator;

/**
 * Vergleicht zwei Datums-Zeichenketten.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class CompDateString implements Comparator<String> {
	/**
	 * Vergleicht 2 Datums-Werte (Zeichenketten) miteinander.
	 * 
	 * @param date1 Zeichenkette 1, die ein Datum enthält
	 * 
	 * @param date2 Zeichenkette 2, die ein Datum enthält
	 * 
	 * @return Ist Datum 1 kleiner als Datum 2, so wird -1 zurück
	 * gegeben. Sind beide Daten gleich groß, so wird 0 zurück gegeben.
	 * Ist die Datum 1 größer als Datum 2, so wird 1 zurück gegeben.
	 */
	@Override
	public int compare(String date1, String date2) {
		// Vorbereitung
		String[] tmp1 = date1.split("\\.");
		String[] tmp2 = date2.split("\\.");
		
		if ((tmp1.length != 3) || (tmp2.length != 3))
			throw new ClassCastException();

		String comp1 = tmp1[2] + "." + tmp1[1] + "." + tmp1[0];
		String comp2 = tmp2[2] + "." + tmp2[1] + "." + tmp2[0];
		
		// Rückgabe
		return comp1.compareTo(comp2);
	}

}
