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

package tests.tests.elements;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.ReportData;
import haushaltsbuch.elements.ReportGraphic;

/**
 * Testet die Klasse {@link haushaltsbuch.elements.ReportGraphic}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportGraphic {
	/**
	 * Speichert die Grafik
	 */
	private ReportGraphic _test;

	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_test = new ReportGraphic();
	}

	/**
	 * Überprüft, ob die Hintegrundfarbe richtig gesetzt wurde.
	 */
	@Test
	public void testBackgroundColor() {
		assertEquals(Color.WHITE, _test.getBackground());
	}
	
	/**
	 * Überprüft, ob die Vordergrundfarbe richtig gesetzt wurde.
	 */
	@Test
	public void testForegroundColor() {
		assertEquals(Color.BLACK, _test.getForeground());
	}
	
	/**
	 * Überprüft, ob die Hilfslinien angezeigt werden sollen
	 */
	@Test
	public void testDrawReferenceLines() {
		assertFalse(_test.drawReferenceLines());
	}
	
	/**
	 * Überprüft, ob die Einstellung zum anzeigen der Hilfslinien richtig
	 * geändert wird.
	 */
	@Test
	public void testSetDrawReferenceLines() {
		_test.setDrawReferenceLines(true);
		assertTrue(_test.drawReferenceLines());
	}
	
	/**
	 * Überprüft, die Farbe der Hilfslinien
	 */
	@Test
	public void testGetColorReferenceLines() {
		assertEquals(Color.LIGHT_GRAY, _test.getReferenceLinesColor());
	}

	/**
	 * Überprüft, ob die Farbe der Hilfslinien richtig geändert wird.
	 */
	@Test
	public void testSetReferenceLinesColor() {
		_test.setReferenceLineColor(Color.BLACK);
		assertEquals(Color.BLACK, _test.getReferenceLinesColor());
	}
	
	/**
	 * Überprüft, die Farbe der Einnahmen
	 */
	@Test
	public void testGetIncomingColor() {
		assertEquals(Color.GREEN, _test.getIncomingColor());
	}

	/**
	 * Überprüft, ob die Farbe der Einnahmen richtig geändert wird.
	 */
	@Test
	public void testSetIncomingColor() {
		_test.setIncomingColor(Color.BLACK);
		assertEquals(Color.BLACK, _test.getIncomingColor());
	}
	
	/**
	 * Überprüft, die Farbe der Ausgaben
	 */
	@Test
	public void testGetOutgoingColor() {
		assertEquals(Color.RED, _test.getOutgoingColor());
	}

	/**
	 * Überprüft, ob die Farbe der Ausgaben richtig geändert wird.
	 */
	@Test
	public void testSetOutgoingColor() {
		_test.setOutgoingColor(Color.BLACK);
		assertEquals(Color.BLACK, _test.getOutgoingColor());
	}
	
	/**
	 * Überprüft, die Farbe der Differenz
	 */
	@Test
	public void testGetDeviationColor() {
		assertEquals(Color.YELLOW, _test.getDeviationColor());
	}

	/**
	 * Überprüft, ob die Farbe der Differenz richtig geändert wird.
	 */
	@Test
	public void testSetDeviationColor() {
		_test.setDeviationColor(Color.BLACK);
		assertEquals(Color.BLACK, _test.getDeviationColor());
	}
	
	/**
	 * Überprüft, ob nach der Initalisation keine Daten gespeichert sind.
	 */
	@Test
	public void testNoDataAfterInit() {
		assertNull(_test.getData());
	}
	
	/**
	 * Überprüft, ob die Daten richtig geändert werden.
	 */
	@Test
	public void testSetData() {
		ReportData rd = mock(ReportData.class);
		_test.setData(rd);
		assertEquals(rd, _test.getData());
	}
	
	/**
	 * Überprüft, ob die X-Achse nicht ausgegeben werden soll, nachdem
	 * initalisiert wurde ist.
	 */
	@Test
	public void testDrawXLegend() {
		assertFalse(_test.drawXLegend());
	}
	
	/**
	 * Überprüft, ob die Ausgabe der X-Achse richtig geändert werden kann.
	 */
	@Test
	public void testSetDrawXLegend() {
		_test.setDrawXLegend(true);
		assertTrue(_test.drawXLegend());
	}
	
	/**
	 * Überprüft, ob die Y-Achse nicht ausgegeben werden soll, nachdem
	 * initalisiert wurde ist.
	 */
	@Test
	public void testDrawYLegend() {
		assertFalse(_test.drawYLegend());
	}
	
	/**
	 * Überprüft, ob die Ausgabe der Y-Achse richtig geändert werden kann.
	 */
	@Test
	public void testSetDrawYLegend() {
		_test.setDrawYLegend(true);
		assertTrue(_test.drawYLegend());
	}
	
	/**
	 * Überprüft, ob die Legende der X-Achse nach der initalisation eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testGetXLegend() {
		assertTrue(_test.getXLegend().isEmpty());
	}
	
	/**
	 * Überprüft, ob die Legende der X-Achse neu gesetzt werden kann.
	 */
	@Test
	public void testSetXLegend() {
		String test = new String("Dies ist ein Test");
		_test.setXLegend(test);
		assertEquals(test, _test.getXLegend());
	}
	
	/**
	 * Überprüft, ob die Legende der Y-Achse nach der initalisation eine leere
	 * Zeichenkette ist.
	 */
	@Test
	public void testGetYLegend() {
		assertTrue(_test.getYLegend().isEmpty());
	}
	
	/**
	 * Überprüft, ob die Legende der Y-Achse neu gesetzt werden kann.
	 */
	@Test
	public void testSetYLegend() {
		String test = new String("Dies ist ein Test");
		_test.setYLegend(test);
		assertEquals(test, _test.getYLegend());
	}
	
	/**
	 * Überprüft, ob keine Überschrift der X-Achse nach der Initalisierung
	 * vorhanden ist.
	 */
	@Test
	public void testGetXHeader() {
		assertEquals(0, _test.getXHeader().size());
	}
	
	/**
	 * Überprüft, ob eine neue Überschrift der X-Achse zur Liste hinzu gefügt
	 * werden kann.
	 */
	@Test
	public void testAddXHeader() {
		_test.addXHeader("Test");
		assertEquals(1, _test.getXHeader().size());
		assertEquals("Test", _test.getXHeader().get(0));
	}
	
	/**
	 * Überprüft, ob der minimale Wert der Y-Achse nach der Initalisierung 0.0
	 * ist.
	 */
	@Test
	public void testGetMinYAfterInit() {
		assertEquals(0.0, _test.getMinY(), 0.0);
	}
	
	/**
	 * Überprüft, ob der maximale Wert der Y-Achse nach der Initalisierung 0.0
	 * ist.
	 */
	@Test
	public void testGetMaxYAfterInit() {
		assertEquals(0.0, _test.getMaxY(), 0.0);
	}
}
