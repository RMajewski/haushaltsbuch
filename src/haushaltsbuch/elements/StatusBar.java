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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import haushaltsbuch.datas.LogData;

/**
 * Zeigt die StatusBar an.
 * 
 * In der Version 0.2 wird die Fehlerbeschreibung zum LogData hinzugefügt. Aus
 * diesem Grund sind einige der bisherigen Methoden zur Erzeugung der LogData
 * deprecated.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 */
public class StatusBar extends JLabel {
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 3659562286526820393L;
	
	/**
	 * Speichert die Instanz der StatusBar
	 */
	private static StatusBar _status = new StatusBar();
	
	/**
	 * Liste, der in der Statuszeile angezeigten Nachrichten
	 */
	private List<LogData> _list;

	/**
	 * Initalisieren der StatusBar
	 */
	public StatusBar()
	{
		super();
		setPreferredSize(new Dimension(100, 20));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		// Liste initalisieren
		_list = new ArrayList<LogData>();
	}
	
	/**
	 * Erzeugt den Fehlerbericht.
	 * 
	 * @param e Fehler, aus dem der Bericht erzeugt werden soll.
	 * 
	 * @return Fehlerbericht zu dem angegebenen Fehler
	 */
	private String getErrorAsString(Exception e) {
		StringBuilder ret = new StringBuilder(e.toString());
		
		StackTraceElement[] ste = e.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			ret.append(System.lineSeparator());
			ret.append("\t");
			ret.append(ste[i].toString());
		}
		
		return ret.toString();
	}
	
	/**
	 * Gibt die Instanz der StatusBar zurück. Sollte die Instanz nicht
	 * intalisiert sein, so wird sie vor der Rückgabe initalisiert.
	 * 
	 * @return Instanz der Statusbar
	 */
	public static StatusBar getInstance() {
		if (_status == null)
			_status = new StatusBar();
		return _status;
	}
	
	/**
	 * Beendet die StatusBar
	 */
	public void close() {
		if (_status != null)
			_status = null;
	}
	
	/**
	 * Text anzeigen
	 * 
	 * @param message Text, der angezeigt werden soll.
	 */
	public void setMessage(String message)
	{
		setText(message);
		setBackground(LogData.COLOR_NONE);
		_list.add(new LogData(message, null));
	}
	
	/**
	 * Text anzeigen
	 * 
	 * @param message Text, der angezeigt werden soll.
	 * 
	 * @param error Fehlerbeschreibung
	 */
	public void setMessage(String message, String error)
	{
		setText(message);
		setBackground(LogData.COLOR_NONE);
		_list.add(new LogData(message, error));
	}
	
	/**
	 * Text anzeigen
	 * 
	 * @param message Text, der angezeigt werden soll.
	 * 
	 * @param e Fehler, zu dem der Fehlerbericht erzeugt werden soll.
	 */
	public void setMessage(String message, Exception e)
	{
		setText(message);
		setBackground(LogData.COLOR_NONE);
		_list.add(new LogData(message, getErrorAsString(e)));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie so, dass sie nicht
	 * ausgegeben werden soll
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsNoOut(String message) {
		_list.add(new LogData(message, null, LogData.NO_OUT));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie so, dass sie nicht
	 * ausgegeben werden soll
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param error Fehlerbeschreibung
	 */
	public void setMessageAsNoOut(String message, String error) {
		_list.add(new LogData(message, error, LogData.NO_OUT));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie so, dass sie nicht
	 * ausgegeben werden soll
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param e Fehler, zu dem der Fehlerbericht erzeugt werden soll.
	 */
	public void setMessageAsNoOut(String message, Exception e)
	{
		_list.add(new LogData(message, getErrorAsString(e), LogData.NO_OUT));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie als Fehler.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @deprecated Neue zu verwendende Methode
	 * {@link #setMessageAsError(String, String)}
	 */
	public void setMessageAsError(String message) {
		setText(message);
		setBackground(LogData.COLOR_ERROR);
		_list.add(new LogData(message, LogData.ERROR));
	}
	
	/**
	 * Erzeugt eine Fehler-Nachricht.
	 * 
	 * @param e Fehler, der aufgetreten ist.
	 * 
	 */
	public void setMessageAsError(Exception e) {
		String message = "Folgender Fehler ist aufgetreten: " + e.toString();
		setText(message);
		setBackground(LogData.COLOR_ERROR);
		_list.add(new LogData(message, getErrorAsString(e), LogData.ERROR));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie als Fehler.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param error Fehlerbeschreibung
	 */
	public void setMessageAsError(String message, String error) {
		setText(message);
		setBackground(LogData.COLOR_ERROR);
		_list.add(new LogData(message, error, LogData.ERROR));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie als Fehler.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param e Fehler, zu dem der Fehlerbericht erzeugt werden soll.
	 */
	public void setMessageAsError(String message, Exception e)
	{
		setText(message);
		setBackground(LogData.COLOR_ERROR);
		_list.add(new LogData(message, getErrorAsString(e), LogData.ERROR));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markeirt sie als Warnung.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @deprecated Neue zu verwendende Nachricht
	 * {@link #setMessageAsWarning(String, String)}
	 */
	public void setMessageAsWarning(String message) {
		setText(message);
		setBackground(LogData.COLOR_WARNING);
		_list.add(new LogData(message, LogData.WARNING));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markeirt sie als Warnung.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param error Fehlerbeschreibung
	 */
	public void setMessageAsWarning(String message, String error) {
		setText(message);
		setBackground(LogData.COLOR_WARNING);
		_list.add(new LogData(message, error, LogData.WARNING));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markeirt sie als Warnung.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param e Fehler, zu dem der Fehlerbericht erzeugt werden soll.
	 */
	public void setMessageAsWarning(String message, Exception e)
	{
		setText(message);
		setBackground(LogData.COLOR_WARNING);
		_list.add(new LogData(message, getErrorAsString(e), LogData.WARNING));
	}
	
	/**
	 * Speichert die Übergebene Nachricht als Erfolgreich.
	 * 
	 * @param message Nachricht, die gespeichert werden soll.
	 */
	public void setMessageAsOk(String message) {
		setText(message);
		setBackground(LogData.COLOR_OK);
		_list.add(new LogData(message, null, LogData.OK));
	}
	
	/**
	 * Speichert die Übergebene Nachricht als Erfolgreich.
	 * 
	 * @param message Nachricht, die gespeichert werden soll.
	 * 
	 * @param error Fehlerbeschreibung
	 */
	public void setMessageAsOk(String message, String error) {
		setText(message);
		setBackground(LogData.COLOR_OK);
		_list.add(new LogData(message, error, LogData.OK));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markeirt sie als Warnung.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 * 
	 * @param e Fehler, zu dem der Fehlerbericht erzeugt werden soll.
	 */
	public void setMessageAsOk(String message, Exception e)
	{
		setText(message);
		setBackground(LogData.COLOR_OK);
		_list.add(new LogData(message, getErrorAsString(e), LogData.OK));
	}
	
	/**
	 * List mit den Log zurück geben
	 * 
	 * @return Liste mit den Log-Einträgen
	 */
	public List<LogData> getLog() {
		return _list;
	}
}
