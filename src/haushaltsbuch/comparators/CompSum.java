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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Comparator;

/**
 * Vergleicht die beiden Beträge miteinander. Die Beträge sollten das Format
 * "0.00" aufweisen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class CompSum implements Comparator<String> {

	/**
	 * Vergleicht die beiden Beträge miteinander. Es wird -1 zurück
	 * gegeben, wenn sum1 kleiner als is als sum2. Sind beide Beträge
	 * gleich groß, so wird 0 zurück geben. Ist die sum1 größer als sum2
	 * so wird 1 zurück gegeben.
	 */
	@Override
	public int compare(String sum1, String sum2) {
		DecimalFormat df = new DecimalFormat("0.00");
		double d1 = 0, d2 = 0;
		try {
			d1 = df.parse(sum1).doubleValue();
			d2 = df.parse(sum2).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (d1 < d2)
			return -1;
		
		if (d1 == d2)
			return 0;
		
		return 1;
	}

}
