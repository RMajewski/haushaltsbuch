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

package haushaltsbuch.elements;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Stellt den Report Grafisch da.
 * 
 * @author René Majewski
 *
 * @since 0.2
 * @version 0.1
 */
public class ReportGraphic extends JComponent {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 301411065862380914L;

	/**
	 * Speichert, ob die Hilfslinien angezeigt werden sollen oder nicht.
	 */
	protected boolean _drawReferenceLines;
	
	/**
	 * Speichert die Farbe der Hilfslinien
	 */
	protected Color _colorReferenceLines;
	
	/**
	 * Speichert die Farbe für Einnahmen
	 */
	protected Color _colorIn;
	
	/**
	 * Speichert die Farbe für Ausgaben
	 */
	protected Color _colorOut;
	
	/**
	 * Speichert die Farbe für die Differenz
	 */
	protected Color _colorDeviation;
	
	/**
	 * Initalisiert die Klasse
	 */
	public ReportGraphic() {
		// Klasse initalisieren
		super();
		
		// Einstellungen für die Hilfslinien
		_drawReferenceLines = false;
		_colorReferenceLines = Color.LIGHT_GRAY;
		
		// Farben für die Einnahmen, Ausgaben und die Differenz
		_colorIn = Color.GREEN;
		_colorOut = Color.RED;
		_colorDeviation = Color.YELLOW;
		
		// Hintergrund setzen
		setBackground(Color.WHITE);
	}
	
	/**
	 * Zeichnet das Balkendiagramm
	 */
	@Override
	public void paint(Graphics g) {
		// Hintergrund zeichnen
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * Ermittelt, ob die Hilfslinien angezeigt werden sollen oder nicht.
	 * 
	 * @return Sollen die Hilfslinien angezeigt werden?
	 */
	public boolean drawReferenceLines() {
		return _drawReferenceLines;
	}
	
	/**
	 * Speichert, ob die Hilfslinien angezeigt werden sollen oder nicht.
	 * 
	 * @param draw Sollen die Hilfslineien angezeigt werden?
	 */
	public void setDrawReferenceLines(boolean draw) {
		_drawReferenceLines = draw;
	}
	
	/**
	 * Ermittelt die eingestellte Farbe der Hilfslinien.
	 * 
	 * @return Farbe der Hilfslinien
	 */
	public Color getReferenceLinesColor() {
		return _colorReferenceLines;
	}

	/**
	 * Speichert die Farbe der Hilfslinien.
	 * 
	 * @param color Neue Farbe der Hilfslinien
	 */
	public void setReferenceLineColor(Color color) {
		_colorReferenceLines = color;
		
	}
	
	/**
	 * Ermittelt die Farbe für die Einnahmen.
	 * 
	 * @return Farbe der Einnahmen
	 */
	public Color getIncomingColor() {
		return _colorIn;
	}
	
	/**
	 * Speichert die Farbe der Einnahmen.
	 * 
	 * @param color Neue Farbe der Einnahmen
	 */
	public void setIncomingColor(Color color) {
		_colorIn = color;
	}
	
	/**
	 * Ermittelt die Farbe für die Ausgaben.
	 * 
	 * @return Farbe der Ausgaben
	 */
	public Color getOutgoingColor() {
		return _colorOut;
	}
	
	/**
	 * Speichert die Farbe der Einnahmen.
	 * 
	 * @param color Neue Farbe der Einnahmen
	 */
	public void setOutgoingColor(Color color) {
		_colorOut = color;
	}
	
	/**
	 * Ermittelt die Farbe für die Differenz.
	 * 
	 * @return Farbe der Differenz
	 */
	public Color getDeviationColor() {
		return _colorDeviation;
	}
	
	/**
	 * Speichert die Farbe der Differenz.
	 * 
	 * @param color Neue Farbe der Differenz.
	 */
	public void setDeviationColor(Color color) {
		_colorDeviation = color;
	}
}
