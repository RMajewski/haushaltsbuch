package windows.internal;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JInternalFrame;

/**
 * Diese Klasse stellt für das GridBag-Layout Helfer zur Verfügung.
 * 
 * @author René Majewski
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
