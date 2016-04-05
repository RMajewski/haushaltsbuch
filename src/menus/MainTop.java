package menus;

import javax.swing.JMenuBar;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Hauptmenü, welches am oberen Bildschirm angezeigt wird.
 * 
 * @author René Majewski
 *
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
	public static final String HELP_ABOUT = "Über...";
	
	/**
	 * ActionCommand für Log -> Anzeigen ...
	 */
	public static final String LOG_VIEW = "LogAnzeigen";
	
	/**
	 * ActionCommand für Datenbank -> Kategory -> Anzeigen
	 */
	public static final String DB_CATEGORY_LIST = "DBCategoryList";
	
	/**
	 * ActionCommand für Datenbank -> Kategory -> Hinzufügen
	 */
	public static final String DB_CATEGORY_NEW = "Neu...";

	/**
	 * Initalisiert das Menü
	 * 
	 * @param listener Reagiert auf das Drücken eines Menü-Eintrages
	 */
	public MainTop(ActionListener listener) {
		// Initalisieren
		super();
		
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
		
		// Über ...
		JMenuItem item = new JMenuItem(HELP_ABOUT);
		item.setMnemonic('Ü');
		item.setActionCommand(HELP_ABOUT);
		item.addActionListener(listener);
		ret.add(item);
		
		// Hilfe-Menü zurück geben
		return ret;
	}
	
	/**
	 * 
	 * @param listener
	 * @return
	 */
	private JMenu createLogMenu(ActionListener listener) {
		// Log-Menü initalisieren
		JMenu ret = new JMenu("Log");
		ret.setMnemonic('L');
		
		// Anzeigen ...
		JMenuItem item = new JMenuItem("Anzeigen");
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
		
		// Untermenü für die Kategorien
		JMenu menu = new JMenu("Kategorie");
		menu.setMnemonic('K');
		
		// Anzeigen
		JMenuItem item = new JMenuItem("Anzeigen");
		item.setMnemonic('A');
		item.setActionCommand(DB_CATEGORY_LIST);
		item.addActionListener(listener);
		menu.add(item);
		
		// Neu
		item = new JMenuItem(DB_CATEGORY_NEW);
		item.setMnemonic('N');
		item.setActionCommand(DB_CATEGORY_NEW);
		item.addActionListener(listener);
		menu.add(item);
		
		// Untermenü einfügen
		ret.add(menu);
		
		// Datenbank-Menü zurück geben
		return ret;
	}
}
