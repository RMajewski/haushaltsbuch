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

package haushaltsbuch.helper;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Implementiert Methoden, die beim Umgang mit den Kaler-Objekten behilflich
 * sind.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
public class HelperCalendar {

	/**
	 * Initalisiert den Kalender. Das Datum wird auf den 1. Januar des
	 * angegeben Jahres gesetzt. Die Stunden, Minuten, Sekunden und
	 * Millisekunden werde auf 0 gesetzt.
	 * 
	 * @param year Jahr, welches der Kalender-Instanz zugeordnet werden soll.
	 * 
	 * @return Erzeugte und initalisierte Instanz des Kalenders
	 */
	public static GregorianCalendar createCalendar(int year) {
		// Kalender Instanz erzeugen
		GregorianCalendar ret = new GregorianCalendar(year, 
				GregorianCalendar.JANUARY, 1);
		
		// Kalender zurück geben
		return ret;
	}
	
	/**
	 * Wandelt ein Datum (long-Wert) in eine lesbare Zeichenkette um.
	 * 
	 * @param date long-Wert, der umgewandelt werden soll
	 * 
	 * @return Lesbare Zeichenkette.
	 */
	public static String dateToString(long date) {
		return DateFormat.getDateInstance(DateFormat.MEDIUM).format(
				new Date(date));
	}
}
