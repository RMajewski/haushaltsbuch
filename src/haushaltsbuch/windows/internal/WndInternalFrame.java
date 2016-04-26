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
	 * Initalisieren der Eigenschaften
	 */
	public WndInternalFrame() {
		// Klasse initalisieren
		super();
		
		// Standardmäßig kein Datenbank-Fenster
		setEnableDbElements(false);
		
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
	protected void newWindow(JInternalFrame wnd) {
		getDesktopPane().add(wnd);
		wnd.moveToFront();
		try {
			wnd.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	 * @param Gibt an, ob die Datenbank-Elemente der ToolBar benötigt werden.
	 */
	public void setEnableDbElements(boolean _enableDbElements) {
		this._enableDbElements = _enableDbElements;
	}
}
