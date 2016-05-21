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

package haushaltsbuch.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

/**
 * Stellt den Fehlerbericht farbig dar.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class ErrorSyntax extends DefaultStyledDocument {

	/**
	 * Speichert den Namen für die standard Schrift-Art
	 */
	public static final String DEFAULT_FONT_FAMILY = "Curier New";
	
	/**
	 * Speichert die standard Schriftgröße
	 */
	public static final int DEFAULT_FONT_SIZE = 12;
	
	/**
	 * Konstante Schrift-Attribute für normale Ausgabe
	 */
	public static final SimpleAttributeSet DEFAULT_NORMAL;
	
	/**
	 * Konstante Schrift-Attribute für Fehler-Klassen
	 */
	public static final SimpleAttributeSet DEFAULT_ERROR;
	
	/**
	 * Konstante Schrift-Attribute für eigene Klassen
	 */
	public static final SimpleAttributeSet DEFAULT_CLASSES;
	
	/**
	 * Konstante Schrift-Attribute für Datei-namen
	 */
	public static final SimpleAttributeSet DEFAULT_FILE;

	static {
		DEFAULT_NORMAL = new SimpleAttributeSet();
		StyleConstants.setForeground(DEFAULT_NORMAL, Color.white);
		StyleConstants.setBackground(DEFAULT_NORMAL, Color.black);
		StyleConstants.setFontFamily(DEFAULT_NORMAL, DEFAULT_FONT_FAMILY);
		StyleConstants.setFontSize(DEFAULT_NORMAL, DEFAULT_FONT_SIZE);
		
		DEFAULT_ERROR = new SimpleAttributeSet();
		StyleConstants.setForeground(DEFAULT_ERROR, Color.red);
		StyleConstants.setBackground(DEFAULT_ERROR, Color.black);
		StyleConstants.setFontFamily(DEFAULT_ERROR, DEFAULT_FONT_FAMILY);
		StyleConstants.setFontSize(DEFAULT_ERROR, DEFAULT_FONT_SIZE);
		
		DEFAULT_CLASSES = new SimpleAttributeSet();
		StyleConstants.setForeground(DEFAULT_CLASSES, Color.green);
		StyleConstants.setBackground(DEFAULT_CLASSES, Color.black);
		StyleConstants.setFontFamily(DEFAULT_CLASSES, DEFAULT_FONT_FAMILY);
		StyleConstants.setFontSize(DEFAULT_CLASSES, DEFAULT_FONT_SIZE);
		
		DEFAULT_FILE = new SimpleAttributeSet();
		StyleConstants.setForeground(DEFAULT_FILE, Color.blue);
		StyleConstants.setBackground(DEFAULT_FILE, Color.black);
		StyleConstants.setFontFamily(DEFAULT_FILE, DEFAULT_FONT_FAMILY);
		StyleConstants.setFontSize(DEFAULT_FILE, DEFAULT_FONT_SIZE);
	}
	
	/**
	 * Speichert das Dokument, in welchem der Text dargestellt wird.
	 */
	private DefaultStyledDocument _doc;

	/**
	 * Speichert das Element, in dem alle anderen Elemente eingefügt werden.
	 */
	private Element _rootElement;
	
	/**
	 * Speichert die Attribute für normale Ausgaben
	 */
	private MutableAttributeSet _normal = DEFAULT_NORMAL;
	
	/**
	 * Speichert die Attribute für Fehler-Klassen
	 */
	private MutableAttributeSet _error = DEFAULT_ERROR;
	
	/**
	 * Speichert die Attribute für eigene Klassen
	 */
	private MutableAttributeSet _class = DEFAULT_CLASSES;
	
	/**
	 * Speichert die Attribute für Datei-Namen
	 */
	private MutableAttributeSet _file = DEFAULT_FILE;
	
	/**
	 * Zuordnung der Schrift-Attribute zu den Schlüsselwörtern.
	 */
	private HashMap<String, MutableAttributeSet> _keywords;
	
	/**
	 * Zuordnung der Schrift-Attribute zu den Exceptions
	 */
	private HashMap<String, MutableAttributeSet> _classes;
	
	/**
	 * Speichert die Schriftgröße
	 */
	private int _fontSize;
	
	/**
	 * Speichert die Schriftart
	 */
	private String _fontName;
	
	/**
	 * Initialisierung der Klasse
	 */
	public ErrorSyntax() {
		_doc = this;
		_rootElement = _doc.getDefaultRootElement();
		putProperty(DefaultEditorKit.EndOfLineStringProperty, 
				System.lineSeparator());
		_fontSize = DEFAULT_FONT_SIZE;
		_fontName = DEFAULT_FONT_FAMILY;
		_keywords = new HashMap<String, MutableAttributeSet>();
		_classes = new HashMap<String, MutableAttributeSet>();
	}
	
	/**
	 * Speichert ein Schlüsselwort in Verbindung mit den Schrift-Formatierungen
	 * 
	 * @param key Schlüsselwort oder Token
	 * 
	 * @param attr Atrribute für die zu verwendete Schrift
	 */
	public void addKeyword(String key, MutableAttributeSet attr) {
		_keywords.put(key, attr);
	}
	
	/**
	 * Fügt den Namen einer Fehler-Klasse der Liste hinzu
	 * 
	 * @param exception Name der Fehler-Klasse
	 */
	public void addException(String exception) {
		_classes.put(exception, _error);
	}
	
	/**
	 * Fügt den Namen einer Klasse der Liste hinzu
	 * 
	 * @param name Name der Klasse
	 * 
	 * @param attr Attribute für die zu verwendete Schrift
	 */
	public void addClassName(String name, MutableAttributeSet attr) {
		_classes.put(name, attr);
	}
	
	/**
	 * Ermittelt die Attribute für die Schrift des ausewählten Schlüsselwortes.
	 * 
	 * @param key Schlüsswort, dess Schrift-Attribute ermittel werden sollen
	 * 
	 * @return Schrift-Attribute des Schlüsselwortes
	 */
	public MutableAttributeSet getKeyWordFormatting(String key) {
		return _keywords.get(key);
	}
	
	/**
	 * Ermittelt die Schrift-Attribute für eine Fehler-Klasse
	 * 
	 * @return Schrift-Attribute der Fehler-Klasse
	 */
	public MutableAttributeSet getExceptionFormatting() {
		return _error;
	}
	
	/**
	 * Löscht ein Schlüsselwort aus der Liste.
	 * 
	 * @param key Schlüsselwort, dass aus der Liste gelöscht werden soll.
	 */
	public void removeKeyword(String key) {
		_keywords.remove(key);
	}
	
	/**
	 * Löscht eine Fehler-Klasse aus der Liste.
	 * 
	 * @param exception Fehler-Klasse, die aus der Liste gelöscht werden soll
	 */
	public void removeException(String exception) {
		_classes.remove(exception);
	}
	
	/**
	 * Setzt die Anzahl der Zeichen für einen Tabulator
	 * 
	 * @param count Anzahl der Zeichen für einen Tabulator
	 */
	public void setTabs(int count) {
		Font font = new Font(_fontName, Font.PLAIN, _fontSize);
		FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int charWidth = metrics.charWidth(' ');
		int tabWidth = charWidth * count;
		
		TabStop[] tabs = new TabStop[35];
		for (int i = 0; i < tabs.length; i++)
			tabs[i] = new TabStop((i * 1) * tabWidth);
		
		TabSet ts = new TabSet(tabs);
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setTabSet(attributes, ts);
		
		setParagraphAttributes(0, getLength(), attributes, false);
	}
	
	/**
	 * Gibt die benutze Schriftgröße zurück
	 * 
	 * @return Verwendete Schriftgröße
	 */
	public int getFontSize() {
		return _fontSize;
	}
	
	/**
	 * Speichert die verwendete Schriftgröße
	 * 
	 * @param size Neue Schriftgröße
	 */
	public void setFontSize(int size) {
		_fontSize = size;
		
		StyleConstants.setFontSize(_error, size);
		StyleConstants.setFontSize(_normal, size);
		StyleConstants.setFontSize(_class, size);
	}
	
	/**
	 * Gibt die benutzte Schriftart zurück
	 * 
	 * @return Verwendete Schriftart
	 */
	public String getFontName() {
		return _fontName;
	}
	
	/**
	 * Speichert die zu verwendete Schriftart
	 * 
	 * @param name Name der neuen Schriftart
	 */
	public void setFontName(String name) {
		_fontName = name;
		
		StyleConstants.setFontFamily(_error, name);
		StyleConstants.setFontFamily(_class, name);
		StyleConstants.setFontFamily(_normal, name);
	}

	/**
	 * Fügt neuen Text in das Dokument ein
	 * 
	 * @param offset Stelle, an die der Text eingefügt werden soll
	 * 
	 * @param str Zeichenkette, die eingefügt werden soll
	 * 
	 * @param a Schrift-Attribute
	 */
	@Override
	public void insertString(int offset, String str, AttributeSet a) 
			throws BadLocationException {
		super.insertString(offset, str, a);
		processChangedLines(offset, str.length());
	}
	
	/**
	 * Löscht die angegeben Zeichenkette.
	 * 
	 * @param offset Stelle, von der an gelöscht werden soll
	 * 
	 * @param offset Anzahl Zeichen, die gelöscht werden sollen
	 */
	@Override
	public void remove(int offset, int length) throws BadLocationException {
		super.remove(offset, length);
		processChangedLines(offset, 0);
	}
	
	/**
	 * Liest die veränderten Zeichen ein.
	 * 
	 * @param offset Stelle, an der die Veränderung beginnt
	 * 
	 * @param length Anzahl Zeichen, die verändert wurden
	 */
	public void processChangedLines(int offset, int length) 
			throws BadLocationException {
		String content = _doc.getText(0, _doc.getLength());
		
		int startLine = _rootElement.getElementIndex(offset);
		int endLine = _rootElement.getElementIndex(offset + length);
		
		for (int i = startLine; i <= endLine; i++) {
			applyHighlighting(content, i);
		}
	}
	
	/**
	 * Durchsucht die übergebene Zeichenkette, nach Zeichen die mit einem
	 * Highlight versehen werden sollen
	 *  
	 * @param content Zeichenkette, die verarbeitet werden soll.
	 * 
	 * @param line Nummer der Zeile, die durchlaufen wird.
	 */
	private void applyHighlighting(String content, int line) {
		int startOffset = _rootElement.getElement(line).getStartOffset();
		int endOffset = _rootElement.getElement(line).getEndOffset() - 1;		
		int lineLength = endOffset - startOffset;
		int contentLength = content.length();
		
		if (endOffset >= contentLength)
			endOffset = contentLength - 1;
		
		// Ersten Zeichenbeschränkungen
		int test1 = content.indexOf(':', startOffset);
		int test2 = content.indexOf('(', startOffset);

		int length = 0;
		// Überprüfen, ob eine Klasse existiert
		if ((test1 > -1) && (test1 <= endOffset) && (test2 > endOffset)) {
			length = test1 - startOffset;
		} else if ((test2 > -1) && (test2 <= endOffset) && (test2 < test1)) {
			length = test2 - startOffset;
		} else if ((startOffset < endOffset) && (test2 > endOffset)) {
			length = endOffset - startOffset;
		}
		
		// Klasse gefunden?
		if (length > 0) {
			String name = content.substring(startOffset, 
					startOffset + length);
			for (Map.Entry<String, MutableAttributeSet> e : _classes.entrySet()) {
				if (isInString(name, e.getKey())) {
					_doc.setCharacterAttributes(startOffset, length, 
							e.getValue(), true);
					startOffset += length;
				}
			}
		}
		
		// Nach Datei-Namen suchen
		if ((test2 >= startOffset) && (test2 < endOffset)) {
			// Sind Zeichen zeichen zwischen Dateinamen und Klasse vorhanden?
			if (startOffset <= test2)
				_doc.setCharacterAttributes(startOffset, test2 - startOffset  + 1, 
						_normal, true);

			int test3 = content.indexOf(')', test2);
			if ((test3 > startOffset) && (test3 <= endOffset)) {
				length = test3 - test2;
				_doc.setCharacterAttributes(test2 + 1, length, _file, true);
				startOffset = test2 + length;
			}
		}
		
		_doc.setCharacterAttributes(startOffset, lineLength, _normal, true);
	}
	
	/**
	 * Überprüft, ob in der Zeichenkette <b>source</b> die Zeichenkette
	 * <b>part</b> enthalten ist. Ist dies der Fall, so wird true zurück gegeben.
	 * Wenn nicht, dann false.
	 * 
	 * @param source Zeichenkette, die durchsucht werden soll.
	 * 
	 * @param part Zeichenkette, die existieren soll
	 * 
	 * @return Wurde part in source gefunden?
	 */
	private boolean isInString(String source, String part) {
		if (source == null || source.isEmpty() || part == null || part.isEmpty())
			return false;
		
		if (source.indexOf(part) > -1)
				return true;
		
		return false;
	}
}
