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

package menus;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Hauptmenü, welches am oberen Bildschirm angezeigt wird.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class MainTop extends JMenuBar {
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -6550171562622643291L;
	
	/**
	 * ActionCommand für Datei -> Beenden
	 */
	public static final String FILE_END = "Beenden";
	
	/**
	 * ActionCommand für Hilfe -> Über ...
	 */
	public static final String HELP_ABOUT = "HelpAbout";
	
	/**
	 * ActionCommand füt Hilfe -> Lizenz ...
	 */
	public static final String HELP_LICENSE = "HelpLicense";
	
	/**
	 * ActionCommand für Log -> Anzeigen ...
	 */
	public static final String LOG_VIEW = "LogAnzeigen";
	
	/**
	 * ActionCommand für Datenbank -> Kategory
	 */
	public static final String DB_CATEGORY = "DBCategoryList";
	
	/**
	 * ActionCOmmand für Datenbank -> Geschäft
	 */
	public static final String DB_SECTION = "DBSectionList";
	
	/**
	 * ActionCommand für Datenbank -> Money
	 */
	public static final String DB_MONEY = "DBMoneyList";
	
	/**
	 * Speichert den Namen des Menus
	 */
	public static final String MENU_NAME = new String("MainMenu");

	/**
	 * Initalisiert das Menü
	 * 
	 * @param listener Reagiert auf das Drücken eines Menü-Eintrages
	 */
	public MainTop(ActionListener listener) {
		// Initalisieren
		super();
		
		// Name des Menüs setzen
		setName(MENU_NAME);
		
		// Menü erstellen
		add(createFileMenu(listener));
		add(createDbMenu(listener));
		add(createLogMenu(listener));
		add(createHelpMenu(listener));
	}
	
	/**
	 * Initalisiert das Menü: Datei
	 * 
	 * @param listener Reagiert auf das Drücken eines Menü-Eintrages
	 */
	private JMenu createFileMenu(ActionListener listener) {
		// Datei-Menü initalisieren
		JMenu ret = new JMenu("Datei");
		ret.setMnemonic('D');
		
		// Beenden
		JMenuItem item = new JMenuItem(FILE_END);
		item.setMnemonic('B');
		item.setActionCommand(FILE_END);
		item.addActionListener(listener);
		ret.add(item);
		
		// Datei-Menü zurück geben
		return ret;
	}
	
	/**
	 * Initalisiert das Menü: Hilfe
	 * 
	 * @param listener Reagiert auf das Drücken eines Menü-Eintrages
	 * 
	 * @return Hilfe-Menü
	 */
	private JMenu createHelpMenu(ActionListener listener) {
		// Hilfe-Menü initalisieren
		JMenu ret = new JMenu("Hilfe");
		ret.setMnemonic('H');
		
		// Lizenz ...
		JMenuItem item = new JMenuItem("Lizenz...");
		item.setMnemonic('L');
		item.setActionCommand(HELP_LICENSE);
		item.addActionListener(listener);
		ret.add(item);
		
		// Trennlinie
		ret.addSeparator();
		
		// Über ...
		item = new JMenuItem("Über...");
		item.setMnemonic('Ü');
		item.setActionCommand(HELP_ABOUT);
		item.addActionListener(listener);
		ret.add(item);
		
		// Hilfe-Menü zurück geben
		return ret;
	}
	
	/**
	 * Erzeugt das Menü für das Logbuch.
	 * 
	 * @param listener Listener, auf das Drücken eines Menü-Elementes reagieren
	 * soll.
	 * 
	 * @return Erzeugtes Log-Menü
	 */
	private JMenu createLogMenu(ActionListener listener) {
		// Log-Menü initalisieren
		JMenu ret = new JMenu("Log");
		ret.setMnemonic('L');
		
		// Anzeigen ...
		JMenuItem item = new JMenuItem("Anzeigen...");
		item.setMnemonic('A');
		item.setActionCommand(LOG_VIEW);
		item.addActionListener(listener);
		ret.add(item);
		
		// Log-Menü zurück geben
		return ret;
	}
	
	/**
	 * Initalisiert das Menü: Datenbank
	 * 
	 * @param listener Klasse die auf das Drücken eines Menü-Elementes
	 * reagiert.
	 * 
	 * @return Datenbank-Menü
	 */
	private JMenu createDbMenu(ActionListener listener) {
		// Datenbank-Menp initalisieren
		JMenu ret = new JMenu("Datenbank");
		ret.setMnemonic('D');
		
		// Eintrag für die Kategor
		JMenuItem item = new JMenuItem("Kategorien");
		item.setMnemonic('K');
		item.setActionCommand(DB_CATEGORY);
		item.addActionListener(listener);
		ret.add(item);
		
		// Eintrag für die Geschäfte
		item = new JMenuItem("Geschäfte");
		item.setMnemonic('G');
		item.setActionCommand(DB_SECTION);
		item.addActionListener(listener);
		ret.add(item);
		
		// Eintrag für das Geld
		item = new JMenuItem("Einnahmen und Ausgaben");
		item.setMnemonic('A');
		item.setActionCommand(DB_MONEY);
		item.addActionListener(listener);
		ret.add(item);
		
		// Datenbank-Menü zurück geben
		return ret;
	}
}
