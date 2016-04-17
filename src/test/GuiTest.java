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

package test;

import org.netbeans.jemmy.Scenario;

/**
 * Von dieser Klasse werden alle GUI-Tests abgeleitet werden.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 *
 */
public abstract class GuiTest implements Scenario {
	/**
	 * Testet, ob die Behauptung richtig ist. Ist sie nicht richtig, so wird mit
	 * dem Fehler {@link test.GuiTestException} abgebrochen.
	 * 
	 * @param message Nachricht, die auf der Console ausgegeben werden soll
	 * 
	 * @param assertion Behauptung, die überprüft werden soll.
	 * 
	 * @throws GuiTestException Wenn ein Test fehlerhaft ist
	 */
	protected void test(String message, boolean assertion) throws GuiTestException {
		System.out.println();
		System.out.println();
		System.out.print("(");
		System.out.print(Thread.currentThread().getStackTrace()[2].getFileName());
		System.out.print(":");
		System.out.print(Thread.currentThread().getStackTrace()[2].getLineNumber());
		System.out.println(")");
		
		if (message != null && !message.isEmpty())
			System.out.println("\t\"" + message + "\"");

		if (!assertion) {
			System.out.println("\t-> Fehler");
			System.out.println();
			System.out.println();
			throw new GuiTestException();
		} else {
			System.out.println("\t-> Erfolgreich");
		}
		
		System.out.println();
		System.out.println();
	}
	


}
