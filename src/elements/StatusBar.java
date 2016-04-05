package elements;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import datas.LogData;

/**
 * Zeigt die StatusBar an.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 *
 */
public class StatusBar extends JLabel {
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 3659562286526820393L;
	
	/**
	 * Speichert die Instanz der StatusBar
	 */
	private static final StatusBar _status = new StatusBar();
	
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
	 * Gibt die Instanz der StatusBar zurück
	 * 
	 * @return Instanz der Statusbar
	 */
	public static StatusBar getInstance() {
		return _status;
	}
	
	/**
	 * Text anzeigen
	 * 
	 * @param message Text, der angezeigt werden soll.
	 */
	public void setMessage(String message)
	{
		setText(message);
		_list.add(new LogData(message));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie so, dass sie nicht
	 * ausgegeben werden soll
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsNoOut(String message) {
		setText(message);
		_list.add(new LogData(message, LogData.NO_OUT));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markiert sie als Fehler.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsError(String message) {
		setText(message);
		_list.add(new LogData(message, LogData.ERROR));
	}
	
	/**
	 * Speichert die übergebene Nachricht und markeirt sie als Warnung.
	 * 
	 * @param message Nachricht, die gespeichert werden soll
	 */
	public void setMessageAsWarning(String message) {
		setText(message);
		_list.add(new LogData(message, LogData.WARNING));
	}
	
	/**
	 * Speichert die Übergebene Nachricht als Erfolgreich.
	 * 
	 * @param message Nachricht, die gespeichert werden soll.
	 */
	public void setMessageAsOk(String message) {
		setText(message);
		_list.add(new LogData(message, LogData.OK));
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
