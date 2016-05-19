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

package haushaltsbuch.windows.internal;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JInternalFrame;

import haushaltsbuch.elements.Desktop;

/**
 * Diese Klasse stellt für das GridBag-Layout Helfer zur Verfügung.
 * 
 * In der Version 0.2 wird ein Attribut implementiert, welches angibt, ob das
 * Fenster ein Datenbank-Fenster ist und die Buttons für die Datenbank in der
 * ToolBar benötigt.
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.1
 */
public class WndInternalFrame extends JInternalFrame {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 5666026630627138150L;
	
	/**
	 * Gibt an, ob das Fenster ein Datenbank-Fenster ist. Die
	 * Standard-Einstellung ist <b>false</b>
	 */
	private boolean _enableDbElements;
	
	/**
	 * Gibt an, ob das Fenster ein Report-Fenster ist. Die Standard-Einstellung
	 * ist <b>false</b>
	 */
	private boolean _enablePdfReport;
	
	/**
	 * Gibt an, ob das Fenster ausgedruckt werden kann. Die Standard-Einstellung
	 * ist <b>false</b>. Sollte das Fenster ausgedruckt werden können, so muss
	 * es das Interface <b>Printable</b> implementieren.
	 */
	private boolean _enablePrint;
	
	/**
	 * Speichert den Desktop
	 */
	protected Desktop _desktop;
	
	/**
	 * Initalisieren der Eigenschaften
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public WndInternalFrame(Desktop desktop) {
		// Klasse initalisieren
		super();
		
		// Desktop speichern
		_desktop = desktop;
		
		// Standard-Einstellungen für die Toolbar
		setEnableDbElements(false);
		setEnablePdfReport(false);
		setEnablePrint(false);
		
		// Fenstergröße
		setSize(600,400);
		
		// Fenstereigenschaften
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(false);
	}
	
	/**
	 * Fügt neues Fenster auf dem Desktop ein und gibt ihn den Focus.
	 * 
	 * @param wnd Fenster, das den Focus erhalten soll
	 */
	protected void newWindow(WndInternalFrame wnd) {
		_desktop.addInternalFrame(wnd);
	}

	/**
	 * Fügt eine Komponente in das GridBag-Layout ein und setzt die
	 * entsprechenden Eigenschaften.
	 * 
	 * @param gbl GridBag-Layout
	 * 
	 * @param c Komponente, die eingefügt werden soll
	 * 
	 * @param x Position auf der X-Achse, an die die Komponente eingefügt
	 * werden soll.
	 * 
	 * @param y Position auf der Y-Achse, an die die Komponenten eingefügt
	 * werden soll.
	 * 
	 * @param width Breite der Komponente
	 * 
	 * @param height Höhe der Komponente
	 * 
	 * @param weightx Gibt an, ob die Komponente in X-Richtung vergrößert
	 * werden soll.
	 * 
	 * @param weighty GIbt an, ob die Komponente in Y-Richtung vergrößert
	 * werden soll.
	 */
	protected void addComponent(GridBagLayout gbl, 
								Component c, int x, int y, int width,
								int height, double weightx, double weighty) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	/**
	 * Gibt zurück, ob dieses Fenster die Datenbank-Elemente der ToolBar
	 * benötigt.
	 * 
	 * @return Werden die Datenbank-Elemente der ToolBar benötigt?
	 */
	public boolean isEnableDbElements() {
		return _enableDbElements;
	}

	/**
	 * Es wird gespeichert, ob die Datenbank-Elemente der ToolBar benötigt
	 * werden.
	 * 
	 * @param enableDbElements Gibt an, ob die Datenbank-Elemente der ToolBar
	 * benötigt werden.
	 */
	public void setEnableDbElements(boolean enableDbElements) {
		this._enableDbElements = enableDbElements;
	}

	/**
	 * Gibt zurück, ob dieses Fenster das PDF-Report-Element der ToolBar
	 * benötigt.
	 * 
	 * @return Wird das PDF-Report-Element der ToolBar benötigt?
	 */
	public boolean isEnablePdfReport() {
		return _enablePdfReport;
	}
	
	/**
	 * Gibt zurück, ob dieses Fenster ausgedruckt werden kann oder nicht.
	 * 
	 * @return Kann das Fenster ausgedruckt werden?
	 */
	public boolean isEnablePrint() {
		return _enablePrint;
	}

	/**
	 * Es wird gespeichert, ob das PDF-Report-Element der ToolBar benötigt wird.
	 * 
	 * @param enablePdfReport Gibt an, ob das PDF-Report-Element der ToolBar
	 * benötigt wird.
	 */
	public void setEnablePdfReport(boolean enablePdfReport) {
		_enablePdfReport = enablePdfReport;
	}
	
	/**
	 * Es wird gespeichert, ob das Print-Element der Toolbar enabled sein soll.
	 * 
	 * @param enablePrint Gibt an, ob das Print-Element der Toolbar enabled ist
	 * oder nicht.
	 */
	public void setEnablePrint(boolean enablePrint) {
		_enablePrint = enablePrint;
	}
}
