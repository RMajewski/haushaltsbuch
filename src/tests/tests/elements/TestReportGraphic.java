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

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

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
}
