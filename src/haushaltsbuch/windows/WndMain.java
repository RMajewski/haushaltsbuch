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

package haushaltsbuch.windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.dialogs.DlgAbout;
import haushaltsbuch.dialogs.DlgLicense;
import haushaltsbuch.dialogs.DlgLogView;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.elements.ToolBarMain;
import haushaltsbuch.export.SqlScript;
import haushaltsbuch.menus.MainTop;
import haushaltsbuch.windows.internal.WndCategoryList;
import haushaltsbuch.windows.internal.WndMoneyList;
import haushaltsbuch.windows.internal.WndReports;
import haushaltsbuch.windows.internal.WndSectionList;

/**
 * Klasse für das Hauptfenster.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndMain extends JFrame implements ActionListener {
	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 2595951786930353918L;
	
	/**
	 * StatusBar
	 */
	private StatusBar _status;
	
	/**
	 * Speichert die ToolBar
	 */
	private ToolBarMain _toolbar;
	
	/**
	 * Speichert den Desktop
	 */
	private Desktop _desktop;
	
	/**
	 * Speichert den Namen des Fensters
	 */
	public static final String TITLE = new String("René's Haushaltsbuch");

	/**
	 * Hauptfenster initalisieren
	 */
	public WndMain() {
		// Name des Hauptfensters
		super(TITLE);
		
		// Übeprüfen ob im Debug-Modus gestartet wurde
		if (System.getProperty("debugging") != null) {
			setTitle(TITLE + " (Debug)");
		}
		
		// Fenster beim Beenden schließen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Größe
		setSize(250,100);
		setExtendedState(MAXIMIZED_BOTH);
		
		// Menü setzen
		setJMenuBar(new MainTop(this));
		
		// Dekstop initalisieren
		_desktop = new Desktop();
		add(_desktop);
		
		// ToolBar initalisieren und anzeigen
		_toolbar = new ToolBarMain(_desktop, this);
		_desktop.setToolBar(_toolbar);
		add(_toolbar, BorderLayout.NORTH);
		
		// StatusBar initalisieren und anzeigen
		_status = StatusBar.getInstance();
		getContentPane().add(_status, java.awt.BorderLayout.SOUTH);
		
		// Datenbank vorbereiten
		prepaireDatabase();
		
		// anzeigen
		_status.setMessageAsOk("Ready");
		setVisible(true);
	}

	/**
	 * Überprüft ob die Tabellen angelegt wurden. Ist dies nicht der Fall, so
	 * werden die Tabellen angelegt.
	 */
	private void prepaireDatabase() {
		try {
			DbController.getInstance().prepaireDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Es wird auf die einzelnen Drücke von Buttons oder Menü-Einträgen
	 * reagiert.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand())
		{
			// Programm beenden
			case MainTop.FILE_END:
				dispose();
				break;
				
			// Linzez-Dialog
			case MainTop.HELP_LICENSE:
				new DlgLicense(this);
				break;
				
			// Über-Dialog
			case MainTop.HELP_ABOUT:
				new DlgAbout(this);
				break;
			
			// Kategorien anzeigen
			case MainTop.DB_CATEGORY:
				_desktop.addInternalFrame(new WndCategoryList(_desktop));
				break;
				
			// Geschäfte anzeigen
			case MainTop.DB_SECTION:
				_desktop.addInternalFrame(new WndSectionList(_desktop));
				break;
				
			// Money-Datensätze anzeigen
			case MainTop.DB_MONEY:
				_desktop.addInternalFrame(new WndMoneyList(_desktop));
				break;
				
			// Log anzeigen
			case MainTop.LOG_VIEW:
				new DlgLogView(this);
				break;
				
			// Wochenübersicht anzeigen
			case MainTop.REPORT_WEEK:
				_desktop.addInternalFrame(new WndReports(_desktop, 
						ReportPreferencesData.TYPE_WEEK, this));
				break;
				
			// Monatsübersicht anzeigen
			case MainTop.REPORT_MONTH:
				_desktop.addInternalFrame(new WndReports(_desktop, 
						ReportPreferencesData.TYPE_MONTH, this));
				break;
				
			// Jahresübersicht anzeigen
			case MainTop.REPORT_YEAR:
				_desktop.addInternalFrame(new WndReports(_desktop, 
						ReportPreferencesData.TYPE_YEAR, this));
				break;
				
			// Export der Daten als SQL-Script
			case MainTop.EXPORT_SQL_SCRIPT:
				new SqlScript().execute();
				break;
		}
		
	}

}
