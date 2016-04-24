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
	
	/**
	 * Wandelt ein long-Wert in Datum und Zeit in einer lesbaren Zeichenkette
	 * um.
	 * 
	 * @param datetime long-Wert, der umgewandelt werden soll
	 * 
	 * @return Lesbare Zeichenkette
	 */
	public static String datetimeToString(long datetime) {
		return DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM).format(new Date(datetime));
	}
	
	/**
	 * Ermittelt aus der angegebenen Monats-Nummer der englischen Monats-Namen.
	 * Ist die Nummer außerhalb des gültigen Bereiches wird eine leere
	 * Zeichenkette zurück gegeben.
	 * 
	 * @param month Monats-Nummer, die in einen Monats-Namen umgewandelt werden
	 * soll.
	 * 
	 * @return Englischer Monats-Name
	 */
	public static String enMonthToString(int month) {
		switch (month) {
		case 0:
			return "January";
			
		case 1:
			return "February";
			
		case 2:
			return "March";
			
		case 3:
			return "April";
			
		case 4:
			return "May";
			
		case 5:
			return "June";
			
		case 6:
			return "July";
			
		case 7:
			return "August";
			
		case 8:
			return "September";
			
		case 9:
			return "October";
			
		case 10:
			return "November";
			
		case 11:
			return "December";
		
		}
		
		// Standard Rückgabe
		return new String();
	}
	
	/**
	 * Ermittelt aus den englischen Monats-Namen die Nummer des Monats. Wird ein
	 * falscher Monatsname angegeben, so wird -1 zurück gegeben.
	 * 
	 * @param name Monatsname, der in eine Monats-Nummer umgewandelt werden
	 * soll.
	 * 
	 * @return Monats-Nummer, die ermittelt wurde.
	 */
	public static int enStringToMonth(String name) {
		if (name == null || name.isEmpty())
			return -1;
		
		switch(name) {
			case "January": 
				return 0;
				
			case "February": 
				return 1;
				
			case "March": 
				return 2;
				
			case "April":
				return 3;
					
			case "May":
				return 4;
				
			case "June":
				return 5;
			case "July":
				return 6;
				
			case "August":
				return 7;
				
			case "September":
				return 8;
				
			case "October":
				return 9;
				
			case "November":
				return 10;
				
			case "December":
				return 11;
		}
		
		// Standard Rückgabe
		return -1;
	}
}
