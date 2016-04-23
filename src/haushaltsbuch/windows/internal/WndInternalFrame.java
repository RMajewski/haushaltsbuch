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
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndInternalFrame extends JInternalFrame {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 5666026630627138150L;
	
	/**
	 * Initalisieren der Eigenschaften
	 */
	public WndInternalFrame() {
		// Klasse initalisieren
		super();
		
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
}