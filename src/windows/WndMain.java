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

package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import db.DbController;
import dialogs.DlgAbout;
import dialogs.DlgLicense;
import dialogs.DlgLogView;
import dialogs.DlgReport;
import elements.StatusBar;
import menus.MainTop;
import windows.internal.WndCategoryList;
import windows.internal.WndMoneyList;
import windows.internal.WndReports;
import windows.internal.WndSectionList;

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
	private StatusBar status;
	
	/**
	 * Speichert den Desktop
	 */
	private JDesktopPane _desktop;
	
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
		
		// Fenster beim Beenden schließen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Größe
		setSize(250,100);
		setExtendedState(MAXIMIZED_BOTH);
		
		// Menü setzen
		setJMenuBar(new MainTop(this));
		
		// Dekstop initalisieren
		_desktop = new JDesktopPane();
		add(_desktop);
		
		// StatusBar initalisieren und anzeigen
		status = StatusBar.getInstance();
		getContentPane().add(status, java.awt.BorderLayout.SOUTH);
		
		// Datenbank vorbereiten
		prepaireDatabase();
		
		// anzeigen
		status.setMessageAsOk("Ready");
		setVisible(true);
	}
	
	/**
	 * Erzeugt ein das Fenster und gibt ihm dem Focus
	 * 
	 * @param wnd Fenster, das erzeugt werden soll
	 */
	private void newWindow(JInternalFrame wnd) {
		_desktop.add(wnd);
		wnd.moveToFront();
		try {
			wnd.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
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
				System.exit(0);
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
				newWindow(new WndCategoryList());
				break;
				
			// Geschäfte anzeigen
			case MainTop.DB_SECTION:
				newWindow(new WndSectionList());
				break;
				
			// Money-Datensätze anzeigen
			case MainTop.DB_MONEY:
				newWindow(new WndMoneyList());
				break;
				
			// Log anzeigen
			case MainTop.LOG_VIEW:
				new DlgLogView(this);
				break;
				
			// Wochenübersicht anzeigen
			case MainTop.REPORT_WEEK:
				newWindow(new WndReports(DlgReport.WEEK));
				break;
				
			// Monatsübersicht anzeigen
			case MainTop.REPORT_MONTH:
				newWindow(new WndReports(DlgReport.MONTH));
				break;
				
			// Jahresübersicht anzeigen
			case MainTop.REPORT_YEAR:
				newWindow(new WndReports(DlgReport.YEAR));
				break;
		}
		
	}

}
