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

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Implementiert Methoden, um den Ausdruck der Tabellen zu vereinfachen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class HelperPrint {
	/**
	 * Ermittelt die Anzahl der erforderlichen Seiten.
	 * 
	 * @param drc Anzahl zu druckender Datensätze
	 * 
	 * @param font Objekt der zu verwendeten Schrift.
	 * 
	 * @param pageHeight Höhe der Seite
	 * 
	 * @param g Grafik-Kontext des Druckers.
	 *
	 * @return Anzahl der Seiten
	 */
	public static int calcPageCount(int drc, Font font, int pageHeight, 
			Graphics g) {
		return (int)Math.ceil(drc / calcRecordPerPage(font, pageHeight, g));
	}
	
	/**
	 * Ermittelt die Anzahl der erforderlichen Seiten. Es wird die
	 * Standard-Druck-Schrift der Berechnung zu Grunde gelegt.
	 * 
	 * @param drc Anzahl zu druckender Datensätze
	 * 
	 * @param pageHeight Höhe der Seite
	 * 
	 * @param g Grafik-Kontext des Druckers
	 * 
	 * @return Anzahl der Seiten
	 */
	public static int calcPageCount(int drc, int pageHeight, Graphics g) {
		return calcPageCount(drc, standardFont(), pageHeight, g);
	}
	
	/**
	 * Ermittelt die Anzahl der Datensätze, die auf einer Seite ausgedruckt
	 * werden können.
	 * 
	 * @param font Verwendete Schrift für die Einträge in den Zellen
	 * 
	 * @param pageHeight Höhe der Seite
	 * 
	 * @param g Grafik-Kontext des Druckers
	 * 
	 * @return Anzahl der Datensätze pro Seite
	 */
	public static int calcRecordPerPage(Font font, int pageHeight, Graphics g) {
		return pageHeight / calcRowHeight(font, g);
	}
	
	/**
	 * Ermittelt die Anzahl der Datensätze, die auf einer Seite ausgedruckt
	 * werden können. Es wird die Standard-Druck-Schrift zur Berechnung heran
	 * gezogen.
	 * 
	 * @param pageHeight Höhe der Seite
	 * 
	 * @param g Grafik-Kontext des Druckers
	 * 
	 * @return Anzahl der Datensätze pro Seite
	 */
	public static int calcRecordPerPage(int pageHeight, Graphics g) {
		return calcRecordPerPage(standardFont(), pageHeight, g);
	}
	
	/**
	 * Berechnet die Höhe einer Zeile
	 * 
	 * @param font Verwendete Schrift für die Einträge in den Zellen
	 * 
	 * @param g Grafik-Kontext des Druckers.
	 * 
	 * @return Zeilenhöhe
	 */
	public static int calcRowHeight(Font font, Graphics g) {
		return calcFontHeight(font, g) + 10;
	}

	/**
	 * Berechnet die Höhe einer Zeile
	 * 
	 * @param g Grafik-Konktext des Druckers.
	 * 
	 * @return Zeilenhöhe
	 */
	public static int calcRowHeight(Graphics g) {
		return calcRowHeight(standardFont(), g);
	}
	
	/**
	 * Berechnet die Breite eine Spalte.
	 * 
	 * @param font Benutzte Schrift.
	 * 
	 * @param maxChar Maximale Anzahl der Zeichen, die in der Spalte dargestellt
	 * werden sollen.
	 * 
	 * @param g Grafik-Kontext des Druckers
	 * 
	 * @return Spaltenbreite
	 */
	public static int calcColumnWidth(Font font, int maxChar, Graphics g) {
		FontMetrics metrics = g.getFontMetrics(font);
		return (metrics.charWidth('0') * maxChar) + 10;
	}
	
	/**
	 * Berechnet die Breite einer Spalte anhand der Standard-Druck-Schrift.
	 * 
	 * @param maxChar MaximaleAnzahl der Zeichen, die in der Spalte dargestellt
	 * werden sollen.
	 * 
	 * @param g Grafik-Kontext des Druckers.
	 * 
	 * @return Spaltenbreite
	 */
	public static int calcColumnWidth(int maxChar, Graphics g) {
		return calcColumnWidth(standardFont(), maxChar, g);
	}
	
	/**
	 * Berechnet die Höhe der Schrift.
	 * 
	 * @param font Betnutzte Schrift
	 * 
	 * @param g Grafik-Kontext des Druckers
	 * 
	 * @return Höhe der Schrift
	 */
	public static int calcFontHeight(Font font, Graphics g) {
		return g.getFontMetrics(font).getHeight();
	}
	
	/**
	 * Berechnet die Höhe der Schrift.
	 * 
	 * @param font Betnutzte Schrift
	 * 
	 * @param g Grafik-Kontext des Druckers
	 * 
	 * @return Höhe der Schrift
	 */
	public static int calcFontHeight(Graphics g) {
		return calcFontHeight(standardFont(), g);
	}
	
	/**
	 * Zeichnet die angegebene Zelle und schreibt den Text in diese. Der Text
	 * beginnt am linken oberen Rand der Zelle. Es wird die Schrift benutzt, die
	 * im Grafik-Kontext angegeben wurde.
	 * 
	 * @param x X-Koordinate, an der die Zelle (oben) beginnt.
	 * 
	 * @param y Y-Koordinate, an der die Zelle (links) beginnt.
	 * 
	 * @param width Breite der Zelle.
	 * 
	 * @param height Höhe der Zelle.
	 * 
	 * @param value Inhalt der Zelle.
	 * 
	 * @param g Grafik-Kontext des Druckers.
	 */
	public static void drawCell(int x, int y, int width, int height, 
			String value, Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawString(value, x + 5, y + 5 + calcFontHeight(g.getFont(), g));
	}
	
	/**
	 * Gibt die Standard-Schrift zum Drucken zurück.
	 * 
	 * @return Standard-Schrift zum Drucken.
	 */
	public static Font standardFont() {
		return new Font(Font.SERIF, Font.PLAIN, 12);
	}
	
	/**
	 * Gibt die Standard-Schrift (Fett) zum Drucken zurück.
	 * 
	 * @return Standard-Fett-Schrift zum Drucken
	 */
	public static Font standardBoldFont() {
		return standardFont().deriveFont(Font.BOLD);
	}
	
	/**
	 * Gibt die Standard-Schrift (Kursiv) zum Durcken zurück.
	 * 
	 * @return Standard-Kursiv-Schrift zum Drucken
	 */
	public static Font standardItalicFont() {
		return standardFont().deriveFont(Font.ITALIC);
	}
}
