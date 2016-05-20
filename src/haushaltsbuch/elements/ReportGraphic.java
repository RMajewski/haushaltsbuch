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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import haushaltsbuch.datas.ReportData;
import haushaltsbuch.helper.HelperNumbersOut;

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
	 * Speichert die Daten für den Report
	 */
	protected ReportData _data;
	
	/**
	 * Speichert die Beschriftung für die X-Achse
	 */
	protected String _xLegend;
	
	/**
	 * Speichert die Beschriftung für die Y-Achse
	 */
	protected String _yLegend;
	
	/**
	 * Speichert, ob die Beschriftung für die X-Achse ausgegeben werden soll
	 */
	protected boolean _drawXLegend;
	
	/**
	 * Speichert, ob die Beschriftung für die Y-Achse ausgegeben werden soll
	 */
	protected boolean _drawYLegend;
	
	/**
	 * Speichert die Liste für die Überschriften der X-Achse
	 */
	protected List<String> _xHeader;
	
	/**
	 * Speichert den maximalen Wert der Y-Achse
	 */
	protected double _maxY;
	
	/**
	 * Speichert den minimalen Wert der Y-Achse
	 */
	protected double _minY;
	
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
		
		// Hintergrund- und Vordergrundfarbe setzen
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		
		// Daten vorbereiten
		_data = null;
		_minY = 0.0;
		_maxY = 0.0;
		
		// Legenden
		_xLegend = new String();
		_drawXLegend = false;
		_yLegend = new String();
		_drawYLegend = false;
		
		// Liste für Überschriften initalisieren
		_xHeader = new ArrayList<String>();
	}
	
	/**
	 * Zeichnet das Balkendiagramm
	 */
	@Override
	public void paint(Graphics g) {
		// Hintergrund zeichnen
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Schrift-Einstellungen ermitteln
		Font font = g.getFont();
		FontMetrics metrics = g.getFontMetrics();
		
		// Diagramm-Linien zeigen
		int width = getWidth() - 100;
		int height = getHeight() - 150;
		int zero = height + 25;
		g.setColor(getForeground());
		g.setFont(font.deriveFont(Font.BOLD));
		g.drawRect(50, 25, width, height);
		
		if (_minY < 0.0) {
			zero = (height / 2) + 25;
			g.drawLine(51, zero, width + 49, zero);
			g.drawString("0,00", 45 - metrics.stringWidth("0,00"),
					(height / 2) + 25 + (metrics.getHeight() / 2));
		}
		
		// Max und Min beschriften
		String tmp = HelperNumbersOut.sum(_maxY);
		g.drawString(tmp, 45 - metrics.stringWidth(tmp), 25);
		tmp = HelperNumbersOut.sum(_minY);
		g.drawString(tmp, 45 - metrics.stringWidth(tmp), height + 25);
		
		// Hilfslinien zeichnen
		if (_drawReferenceLines) {
			g.setColor(_colorReferenceLines);
			int h = height / 10;
			
			// Hilfslinien der Y-Achse
			for (int i = h + 25; i < height; i += h)
				g.drawLine(51, i, width + 49, i);
			
			// Hilfslinien der X-Achse
			int w = (width - 50) / _data.getRowCount();
			for (int i = w + 50; i < width; i += w)
				g.drawLine(i, 26, i, height + 24);
		}
		
		// Überschriften der X-Achse zeichnen
		if (_xHeader.size() > 0) {
			boolean t = false;
			g.setColor(getForeground());
			g.setFont(font.deriveFont(Font.BOLD));
			int x = width / _data.getRowCount();
			int y = 0;
			for (int i = 0; i < _data.getRowCount(); i++) {
				if (t)
					y = getHeight() - 100;
				else
					y = getHeight() - 100 + metrics.getHeight();
				t = !t;
				g.drawString(_xHeader.get(i), (i * x) + 50, y);
			}
		}
		
		// Legende der X-Achse zeichnen
		if (_drawXLegend && !_xLegend.isEmpty()) {
			g.setColor(getForeground());
			g.setFont(font.deriveFont(Font.BOLD));
			g.drawString(_xLegend, (getWidth() / 2) - 
					(metrics.stringWidth(_xLegend) / 2), getHeight() - 25);
		}
		
		// Legende der Y-Achse zeichnen
		if (_drawYLegend && !_yLegend.isEmpty()) {
			AffineTransform at  = new AffineTransform();
			at.rotate(Math.toRadians(-90));
			g.setFont(font.deriveFont(at).deriveFont(Font.BOLD));
			g.setColor(getForeground());
			g.drawString(_yLegend, 25, (getHeight() / 2) - 
					(metrics.stringWidth(_yLegend) / 2));
		}
		
		// Daten ausgeben
		int w = (width - 50) / _data.getRowCount();
		int w1 = w / 3;
		int zeroCalc = zero - 25;
		for (int i = 0; i < _data.getRowCount(); i++) {
			// Einnahmen
			if (_data.incoming(i) > 0.0) {
				g.setColor(_colorIn);
				int h = (int)((_data.incoming(i) /_maxY) * zeroCalc);
				g.fillRect((w * i) + 51, zero - h, w1, h);
			}
			
			// Ausgaben
			if (_data.outgoing(i) > 0) {
				g.setColor(_colorOut);
				int h = (int)((_data.outgoing(i) /_maxY) * zeroCalc);
				g.fillRect((w * i) + 51 + w1, zero - h, w1, h);
			}
			
			// Differenz
			if (_data.deviation(i) != 0.0) {
				g.setColor(_colorDeviation);
				if (_data.deviation(i) > 0.0) {
					int h = (int)((_data.deviation(i) /_maxY) * zeroCalc);
					g.fillRect((w * i) + 51 + (2 * w1), zero - h, w1, h);
				} else {
					int h = (int)((_data.deviation(i) / _minY) * zeroCalc);
					g.fillRect((w * i) + 51 + (2 * w1), zero, w1, h);
				}
			}
		}
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
	
	/**
	 * Gibt die gespeicherten Daten zurück.
	 * 
	 * @return Gespeicherte Daten
	 */
	public ReportData getData() {
		return _data;
	}
	
	/**
	 * Speichert die neuen Daten. Außerdem werden die Daten durchlaufen, um den
	 * maximallen und minimalen Wert der Y-Achse zu ermitteln.
	 * 
	 * @param data Neue Daten
	 */
	public void setData(ReportData data) {
		_data = data;
		_maxY = 0.0;
		_minY = 0.0;
		
		for (int i = 0; i < _data.getRowCount(); i++) {
			if (_data.incoming(i) < _minY)
				_minY = _data.incoming(i);
			
			else if (_data.incoming(i) > _maxY)
				_maxY = _data.incoming(i);
			
			if (_data.outgoing(i) < _minY)
				_minY = _data.outgoing(i);
			
			else if (_data.outgoing(i) > _maxY)
				_maxY = _data.outgoing(i);
			
			if (_data.deviation(i) < _minY)
				_minY = _data.deviation(i);
			
			else if (_data.deviation(i) > _maxY)
				_maxY = _data.deviation(i);
		}
	}
	
	/**
	 * Gibt zurück, ob die Legende für die X-Achse dargestellt werden soll oder
	 * nicht.
	 * 
	 * @return Soll die Legende der X-Achse angezeigt werden?
	 */
	public boolean drawXLegend() {
		return _drawXLegend;
	}
	
	/**
	 * Speichert, ob die Legnede für die X-Achse dargestellt werden solle oder
	 * nicht.
	 */
	public void setDrawXLegend(boolean x) {
		_drawXLegend = x;
	}
	
	/**
	 * Gibt die gespeicherte Legende der X-Achse zurück.
	 * 
	 * @return Gespeicherte Legende der X-Achse.
	 */
	public String getXLegend() {
		return _xLegend;
	}
	
	/**
	 * Legt die Legende der X-Achse neu fest.
	 * 
	 * @param legend Neue Legende der X-Achse
	 */
	public void setXLegend(String legend) {
		_xLegend = legend;
	}
	
	/**
	 * Gibt zurück, ob die Legende für die Y-Achse dargestellt werden soll oder
	 * nicht.
	 * 
	 * @return Soll die Legende der Y-Achse angezeigt werden?
	 */
	public boolean drawYLegend() {
		return _drawYLegend;
	}
	
	/**
	 * Speichert, ob die Legnede für die Y-Achse dargestellt werden solle oder
	 * nicht.
	 */
	public void setDrawYLegend(boolean y) {
		_drawYLegend = y;
	}
	
	/**
	 * Gibt die gespeicherte Legende der Y-Achse zurück.
	 * 
	 * @return Gespeicherte Legende der Y-Achse.
	 */
	public String getYLegend() {
		return _yLegend;
	}
	
	/**
	 * Legt die Legende der Y-Achse neu fest.
	 * 
	 * @param legend Neue Legende der Y-Achse
	 */
	public void setYLegend(String legend) {
		_yLegend = legend;
	}
	
	/**
	 * Gibt die Liste mit den Überschriften für die X-Achse zurück.
	 * 
	 * @return Liste mit den Überschriften der X-Achse
	 */
	public List<String> getXHeader() {
		return _xHeader;
	}
	
	/**
	 * Fügt eine Überschrift der X-Achse zur Liste hinzu
	 * 
	 * @param header Neue Überschrfit der X-Achse, die zur Liste hinzu gefügt
	 * werden soll.
	 */
	public void addXHeader(String header) {
		_xHeader.add(header);
	}
	
	/**
	 * Gibt den maximalen Wert der Y-Achse zurück.
	 * 
	 * @return Maximaler Wert der Y-Achse
	 */
	public double getMaxY() {
		return _maxY;
	}
	
	/**
	 * Gibt den minimalen Wert der Y-Achse zurück.
	 * 
	 * @return Minimaler Wert der Y-Achse
	 */
	public double getMinY() {
		return _minY;
	}
}
