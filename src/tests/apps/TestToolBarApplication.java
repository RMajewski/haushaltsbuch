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

package tests.apps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import haushaltsbuch.elements.ToolBarMain;
import haushaltsbuch.windows.internal.WndCategoryList;

public class TestToolBarApplication extends JFrame implements ActionListener {
	/**
	 * Speichert den Titel des Fensters
	 */
	public static String TITLE = "TestToolbarApplication";

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -5235425500673789997L;
	
	/**
	 * Speichert den Desktop
	 */
	JDesktopPane _desktop;
	
	/**
	 * Initalisiert das Fenster
	 */
	public TestToolBarApplication() {
		// Klasse initalisieren
		super();
		
		// Titel und Größe
		setTitle(TITLE);
		setSize(600,200);
		
		// Fenster beim Beenden schließen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Menü erstellen
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Datenbank");
		JMenuItem item = new JMenuItem("Kategorien");
		item.addActionListener(this);
		item.setActionCommand("Category");
		menu.add(item);
		bar.add(menu);
		
		item = new JMenuItem("Test");
		item.addActionListener(this);
		item.setActionCommand("Test");
		menu = new JMenu("Test");
		menu.add(item);
		bar.add(menu);
		setJMenuBar(bar);
		
		// Desktop einfügen
		_desktop = new JDesktopPane();
		add(_desktop);
		
		// ToolBar initalisieren und anzeigen
		add(new ToolBarMain(_desktop, this), BorderLayout.NORTH);
		
		// Fenster anzeigen
		setVisible(true);
	}

	
	/**
	 * Reagiert aufs Klicken der Menü-Einträge
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Kategorien anzeigen
		if (e.getActionCommand().equals("Category")) {
			WndCategoryList category = new WndCategoryList();
			_desktop.add(category);
			category.toFront();
			try {
				category.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Test")) {
			JInternalFrame wnd = new JInternalFrame();
			wnd.setSize(100, 100);
			wnd.setVisible(true);
			_desktop.add(wnd);
			wnd.toFront();
			try {
				wnd.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new TestToolBarApplication();
	}

}
