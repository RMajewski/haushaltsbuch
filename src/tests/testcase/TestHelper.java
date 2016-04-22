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

package tests.testcase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

/**
 * Hier werden einige Helfer-Methoden deklariert.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestHelper {
	/**
	 * Speichert die einzelnen Methoden
	 */
	protected Method _methods[];
	
	/**
	 * Sucht die angegebene Methode aus dem Methoden-Array.
	 * 
	 * @param name Name der Methode
	 * 
	 * @param index Angabe, die wievielte Methode zurück gegeben werden soll
	 * 
	 * @return Gibt <b>null</b> zurück, wenn die Methode nicht gefunden wurde.
	 * Wurde die Methode gefunden, so wird diese zurück gegeben.
	 */
	protected Method getMethod(String name, int index) {
		// Array durchlaufen
		int count = 1;
		for (Method method : _methods) {
			if (method.getName().equals(name)) {
				if (count == index)
					return method;
				count++;
			}
		}
	
		// Da bisher nicht beendet wurde, null zurück geben
		return null;
	}
	
	/**
	 * Sucht die angegebene Methode aus dem Methoden-Array.
	 * 
	 * @param name Name der Methode
	 * 
	 * @return Gibt <b>null</b> zurück, wenn die Methode nicht gefunden wurde.
	 * Wurde die Methode gefunden, so wird diese zurück gegeben.
	 */
	protected Method getMethod(String name) {
		return getMethod(name, 1);
	}

	/**
	 * Überprüft ob die übergebene Zeichenkette nicht <b>null</b> ist und b
	 * sie nicht leer ist. Trifft eins von beiden nicht zu, so wird
	 * AssertionError ausgelöst.
	 * 
	 * @param test Die zu überprüfende Zeichenkette.
	 * 
	 * @throws AssertionError
	 */
	protected void assertStringIsNotNull(String test) throws AssertionError {
		assertNotNull(test);
		assertFalse(test.isEmpty());
	}
	
	/**
	 * Es wird ermittelt, wie oft part in source vorkommt. Ist part oder source
	 * leer, so wird -1 zurück geben.
	 * 
	 * @param source Die Zeichenkette, die durchsucht werden soll.
	 * 
	 * @param part Zeichenkette, die gesucht werden soll.
	 * 
	 * @return Anzahl, wie oft part in source vorkommt. Oder -1, wenn eine von
	 * beiden Zeichenketten leer sind.
	 */
	protected int frequency(String source, String part) {
		if (source == null || source.isEmpty() || part == null || part.isEmpty())
			return -1;
		
		int ret = 0;
		for (int pos = 0; (pos = source.indexOf(part, pos)) != -1; ret++)
			pos += part.length();
		
		return ret;
	}
}