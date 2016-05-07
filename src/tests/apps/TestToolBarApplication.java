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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.ToolBarMain;
import haushaltsbuch.menus.MainTop;
import haushaltsbuch.windows.internal.WndCategoryList;
import haushaltsbuch.windows.internal.WndInternalFrame;
import haushaltsbuch.windows.internal.WndMoneyList;
import haushaltsbuch.windows.internal.WndSectionList;

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
	Desktop _desktop;
	
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
		JMenuItem item = new JMenuItem("Test");
		item.addActionListener(this);
		item.setActionCommand("Test");
		JMenu menu = new JMenu("Test");
		menu.add(item);
		MainTop bar = new MainTop(this);
		bar.add(menu);
		setJMenuBar(bar);
		
		// Desktop einfügen
		_desktop = new Desktop(this);
		add(_desktop);
		
		// ToolBar initalisieren und anzeigen
		ToolBarMain toolbar = new ToolBarMain(_desktop, this);
		_desktop.setToolBar(toolbar);
		add(toolbar, BorderLayout.NORTH);
		
		// Fenster anzeigen
		setVisible(true);
	}

	
	/**
	 * Reagiert aufs Klicken der Menü-Einträge
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Kategorien anzeigen
		if (e.getActionCommand().equals(MainTop.DB_CATEGORY))
			_desktop.addInternalFrame(new WndCategoryList(_desktop));
		
		// Geschäfte anzeigen
		else if (e.getActionCommand().equals(MainTop.DB_SECTION))
			_desktop.addInternalFrame(new WndSectionList(_desktop));
		
		// Einnahmen und Ausgaben anzeigen
		else if (e.getActionCommand().equals(MainTop.DB_MONEY))
			_desktop.addInternalFrame(new WndMoneyList(_desktop));
		
		// Test-Fenster
		else if (e.getActionCommand().equals("Test")) {
			WndInternalFrame wnd = new WndInternalFrame(_desktop);
			wnd.setVisible(true);
			_desktop.addInternalFrame(wnd);;
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
