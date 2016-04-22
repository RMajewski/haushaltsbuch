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

import haushaltsbuch.datas.Data;

/**
 * Vergleicht die beiden Daten-Pakte mit einander.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
public class CompId implements Comparator<Data> {

	/**
	 * Vergleicht die beiden Daten-Pakete mit einander. Es wird -1 zurück
	 * gegeben, wenn die ID von d1 kleiner als die des d2. Sind beide IDs gleich
	 * groß, so wird 0 zurück geben. Ist die ID größer von d1 als die des von
	 * d2, so wird 1 zurück gegeben.
	 */
	@Override
	public int compare(Data d1, Data d2) {
		if (d1.getId() < d2.getId())
			return -1;
		
		if (d1.getId() == d2.getId())
			return 0;

		return 1;
	}

}
