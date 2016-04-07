package test;

import static org.junit.Assert.*;

/**
 * Hier werden einige Helfer-Methoden deklariert.
 * 
 * @author René Majewski
 *
 */
public class TestHelper {
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
