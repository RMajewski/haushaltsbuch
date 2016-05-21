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

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import haushaltsbuch.windows.internal.WndInternalFrame;
import haushaltsbuch.windows.internal.WndTableFrame;

/**
 * Dies ist der Desktop für das Hauptfenster.
 * 
 * In der Version 0.1 wird eine Methode hinzugefügt, damit ein Fenster zum
 * Desktop hinzugfügt werden kann. Dem Fenster wird als InternalFrameLister und
 * als ToolBarDbElementListener die ToolBar hinzugeüfgt.
 *  
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class Desktop extends JDesktopPane {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -1430411283318097567L;

	/**
	 * Speichert die ToolBar
	 */
	private ToolBarMain _toolbar;
	
	/**
	 * Speichert das Hauptfenster
	 */
	private JFrame _main;
	
	/**
	 * Initalisiert den Desktop
	 */
	public Desktop(JFrame owner) {
		super();
		_main = owner;
	}
	
	/**
	 * Setzt die Toolbar
	 * 
	 * @param toolbar ToolBar, die als Listener den Fenster hinzugefügt werden
	 * soll.
	 */
	public void setToolBar(ToolBarMain toolbar) {
		_toolbar = toolbar;
	}
	
	/**
	 * Gibt die Toolbar zurück
	 * 
	 * @return Gespeicherte Toolbar
	 */
	public ToolBarMain getToolBar() {
		return _toolbar;
	}
	
	/**
	 * Ermittelt das Hauptfenster und gibt dieses zurück.
	 * 
	 * @return Hauptfenster der Anwendung
	 */
	public JFrame getMainWindow() {
		return _main;
	}
	
	/**
	 * Fügt ein neues Fenster dem Desktop hinzu und registriert die ToolBar als
	 * InternalFrameListener. Ist das Fenster auch ein Datenbank-Fenste so wird
	 * die ToolBar auch als ToolBarDbElementListener registriert. Das Fenster
	 * erhält zudem den Fokus.
	 * 
	 * @param frame Fenster, das in den Desktop eingefügt werden soll.
	 */
	public void addInternalFrame(WndInternalFrame frame) {
		frame.addInternalFrameListener(_toolbar);
		
		if (frame instanceof WndTableFrame)
			((WndTableFrame)frame).addToolBarDbEventListener(_toolbar);
			
		add(frame);
		frame.moveToFront();
		try {
			frame.setSelected(true);
		} catch (Exception e) {
			StatusBar.getInstance().setMessageAsError(e);
		}		
	}
}
