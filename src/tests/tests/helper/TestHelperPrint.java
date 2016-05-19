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

package tests.tests.helper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.helper.HelperPrint;

/**
 * Testet die Helfer-Klasse {@link haushaltsbuch.helper.HelperPrint}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class TestHelperPrint {
	/**
	 * Speichert das Mockobject von Graphics.
	 */
	private Graphics _g;
	
	@Before
	public void setUp() {
		_g = mock(Graphics.class);
	}

	@Test
	public void testCalcPageCountIntFontIntGraphics() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalcPageCountIntIntGraphics() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalcRecordPerPageFontIntGraphics() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalcRecordPerPageIntGraphics() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Testet, ob die Höhe der Zeile richtig berechnet wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#calcRowHeight(Font, Graphics)
	 */
	@Test
	public void testCalcRowHeightFontGraphics() {
		int height = 118;
		
		FontMetrics metrics = mock(FontMetrics.class);
		when(metrics.getHeight()).thenReturn(height);
		
		Font font = mock(Font.class);
		
		when(_g.getFontMetrics(font)).thenReturn(metrics);
		
		assertEquals(height + 10, HelperPrint.calcRowHeight(font, _g));
		
		verify(metrics).getHeight();
		verify(_g).getFontMetrics(font);
	}

	/**
	 * Testet, ob die Höhe der Zeile richtig berechnet wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#calcRowHeight(Graphics)
	 */
	@Test
	public void testCalcRowHeightGraphics() {
		int height = 10;
		FontMetrics metrics = mock(FontMetrics.class);
		when(metrics.getHeight()).thenReturn(height);
		
		when(_g.getFontMetrics(HelperPrint.standardFont())).thenReturn(metrics);
		
		assertEquals(height + 10, HelperPrint.calcRowHeight(_g));
		
		verify(metrics).getHeight();
		verify(_g).getFontMetrics(HelperPrint.standardFont());
	}

	@Test
	public void testCalcColumnWidthFontIntGraphics() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCalcColumnWidthIntGraphics() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Testet, ob die Höhe richtig berechnet wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#calcFontHeight(Graphics)
	 */
	@Test
	public void testCalcFontHeightFontGraphics() {
		int height = 12;
		FontMetrics metrics = mock(FontMetrics.class);
		when(metrics.getHeight()).thenReturn(height);
		
		when(_g.getFontMetrics(HelperPrint.standardFont())).thenReturn(metrics);
		
		assertEquals(height, HelperPrint.calcFontHeight(_g));
		
		verify(metrics).getHeight();
		verify(_g).getFontMetrics(HelperPrint.standardFont());
	}

	/**
	 * Testet, ob die Höhe richtig berechnet wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#calcFontHeight(java.awt.Graphics)
	 */
	@Test
	public void testCalcFontHeightGraphics() {
		int height = 12;
		FontMetrics metrics = mock(FontMetrics.class);
		when(metrics.getHeight()).thenReturn(height);
		
		when(_g.getFontMetrics(HelperPrint.standardFont())).thenReturn(metrics);
		
		assertEquals(height, HelperPrint.calcFontHeight(_g));
		
		verify(metrics).getHeight();
		verify(_g).getFontMetrics(HelperPrint.standardFont());
	}

	/**
	 * Testet, ob die Zelle richtig ausgegeben wird.
	 */
	@Test
	public void testDrawCell() {
		int x = 10;
		int y = 15;
		int width = 100;
		int height = 150;
		int fontHeight = 12;
		String value = "Test";
		
		FontMetrics metrics = mock(FontMetrics.class);
		when(metrics.getHeight()).thenReturn(fontHeight);
		
		Font font = mock(Font.class);
		
		when(_g.getFontMetrics(font)).thenReturn(metrics);
		when(_g.getFont()).thenReturn(font);
		
		HelperPrint.drawCell(x, y, width, height, value, _g);
		
		verify(metrics).getHeight();
		verify(_g).getFont();
		verify(_g).getFontMetrics(font);
		verify(_g).drawRect(x, y, width, height);
		verify(_g).drawString(value, x + 5, y + 5 + fontHeight);
	}

	/**
	 * Testet, ob die fette Standard-Druck-Schrift richtig initialisiert wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#standardFont()
	 */
	@Test
	public void testStandardFont() {
		Font font = new Font(Font.SERIF, Font.PLAIN, 12);
		assertEquals(font, HelperPrint.standardFont());
	}

	/**
	 * Testet, ob die fette Standard-Druck-Schrift richtig initialisiert wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#standardBoldFont()
	 */
	@Test
	public void testStandardBoldFont() {
		Font font = new Font(Font.SERIF, Font.PLAIN + Font.BOLD, 12);
		assertEquals(font, HelperPrint.standardBoldFont());
	}

	/**
	 * Testet, ob die kursive Standard-Druck-Schrift richtig initialisiert wird.
	 * 
	 * @see haushaltsbuch.helper.HelperPrint#standardItalicFont()
	 */
	@Test
	public void testStandardItalicFont() {
		Font font = new Font(Font.SERIF, Font.PLAIN + Font.ITALIC, 12);
		assertEquals(font, HelperPrint.standardItalicFont());
	}

}
