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
 * Vergleicht zwei Integer miteinander.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class CompInt implements Comparator<Integer> {

	/**
	 * Vergleicht die beiden Zahlen miteinander. Es wird -1 zurück
	 * gegeben, wenn die Zahl i1 kleiner als die Zahl i2. Sind beide Zahlen
	 * gleich groß, so wird 0 zurück geben. Ist die Zahl größer von i1 als die
	 * Zahl i2, so wird 1 zurück gegeben.
	 */
	@Override
	public int compare(Integer i1, Integer i2) {
		if (i1 < i2)
			return -1;
		if (i1 == i2)
			return 0;
		return 1;
	}

}
