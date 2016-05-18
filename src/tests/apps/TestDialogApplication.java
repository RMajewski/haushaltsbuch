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

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.dialogs.DlgAbout;
import haushaltsbuch.dialogs.DlgExportPdf;
import haushaltsbuch.dialogs.DlgExportSqlScript;
import haushaltsbuch.dialogs.DlgInfo;
import haushaltsbuch.dialogs.DlgInputChange;
import haushaltsbuch.dialogs.DlgLicense;
import haushaltsbuch.dialogs.DlgLogView;
import haushaltsbuch.dialogs.DlgReport;

/**
 * Ruft zum testen den angegebenen Dialog auf, damit er mit Jemmy und Fit
 * getestet werden kann.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestDialogApplication extends JFrame implements ActionListener {
	/**
	 * Speichert den Titel des Fensters
	 */
	public static String TITLE = "TestDialogApplication";
	
	/**
	 * Gibt an, dass der "Über..."-Dialog angezeigt werden soll.
	 */
	public static final String DIALOG_ABOUT = "tests.dialogs.DlgAbout";
	
	/**
	 * Gibt an, dass der Lizenz-Dialog angezeigt werden soll.
	 */
	public static final String DIALOG_LICENSE = "tests.dialogs.DlgLicense";
	
	/**
	 * Gibt an, dass der Log angezeigt werden soll.
	 */
	public static final String DIALOG_LOG = "tests.dialogs.DlgLogView";
	
	/**
	 * Gibt an, dass der Export-Dialog für das SQL-Script angezeigt werden soll.
	 */
	public static final String DIALOG_EXPORT_SQL = "tests.dialogs.DlgExportSqlScript";
	
	/**
	 * Gibt an, dass der Export-Dialog für die PDF angezeigt werden soll.
	 */
	public static final String DIALOG_EXPORT_PDF = "tests.dialogs.DlgExportPdf";
	
	/**
	 * Gibt an, dass der Report-Dialog angezeigt werden soll.
	 */
	public static final String DIALOG_REPORT = "test.dialogs.DlgReport";
	
	/**
	 * Gibt an, dass der Informations-Dialog angezeigt werden soll.
	 */
	public static final String DIALOG_INFO = "test.dialogs.DlgInfo";
	
	/**
	 * Gibt an, dass eine neue Kategorie erstellt werden soll.
	 */
	public static final String DIALOG_INPUTCHANGE_CATEGORY_INSERT = 
				"test.dialogs.DlgInputChange.Category.Insert";
	
	/**
	 * Gibt an, dass eine Kategorie geändert werden soll.
	 */
	public static final String DIALOG_INPUTCHANGE_CATEGORY_CHANGE = 
				"test.dialogs.DlgInputChange.Category.Change";
	
	/**
	 * Gibt an, dass ein neues Geschäft erstellt werden soll.
	 */
	public static final String DIALOG_INPUTCHANGE_SECTION_INSERT = 
				"test.dialogs.DlgInputChange.Section.Insert";
	
	/**
	 * Gibt an, dass ein Geschäft geändert werden soll.
	 */
	public static final String DIALOG_INPUTCHANGE_SECTION_CHANGE = 
				"test.dialogs.DlgInputChange.Section.Change";

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -3655292066616840554L;
	
	/**
	 * Speichert die Einstellungen für den Report-Dialog
	 */
	private ReportPreferencesData _rpd;

	/**
	 * Initalisiert das Fenster.
	 */
	public TestDialogApplication() {
		// Klasse initalisieren
		super();
		
		// Titel
		setTitle(TITLE);
		
		// Größe
		setSize(600, 400);
		
		// Button für "Über..."-Dialog anzeigen
		getContentPane().setLayout(new FlowLayout());
		JButton btn = new JButton(DIALOG_ABOUT);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_ABOUT);
		getContentPane().add(btn);
		
		// Button für Lizenez-Dialog anzeigen
		btn = new JButton(DIALOG_LICENSE);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_LICENSE);
		getContentPane().add(btn);
		
		// Button zum Anzeigen des Logs
		btn = new JButton(DIALOG_LOG);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_LOG);
		getContentPane().add(btn);
		
		// Button zum Anzeigen des Dialogs für Export des SQL-Script
		btn = new JButton(DIALOG_EXPORT_SQL);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_EXPORT_SQL);
		getContentPane().add(btn);
		
		// Button zum Anzeigen des Dialogs für Export der PDF-Datei
		btn = new JButton(DIALOG_EXPORT_PDF);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_EXPORT_PDF);
		getContentPane().add(btn);
		
		// Button zum Anzeigen des Report-Dialoges
		btn = new JButton(DIALOG_REPORT);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_REPORT);
		getContentPane().add(btn);
		
		// Button zum Anzeigen des Info-Dialoges
		btn = new JButton(DIALOG_INFO);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_INFO);
		getContentPane().add(btn);
		
		// Button zum Dialog zur Eingabe einer neuen Kategorie
		btn = new JButton(DIALOG_INPUTCHANGE_CATEGORY_INSERT);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_INPUTCHANGE_CATEGORY_INSERT);
		getContentPane().add(btn);
		
		// Button zum Dialog zur Änderung einer Kategorie
		btn = new JButton(DIALOG_INPUTCHANGE_CATEGORY_CHANGE);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_INPUTCHANGE_CATEGORY_CHANGE);
		getContentPane().add(btn);
		
		// Button zum Dialog zur Eingabe eines neuen Geschäftes
		btn = new JButton(DIALOG_INPUTCHANGE_SECTION_INSERT);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_INPUTCHANGE_SECTION_INSERT);
		getContentPane().add(btn);
		
		// Button zum Dialog zur Änderung eines Geschäftes
		btn = new JButton(DIALOG_INPUTCHANGE_SECTION_CHANGE);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_INPUTCHANGE_SECTION_CHANGE);
		getContentPane().add(btn);

		// Fenster Anzeigen
		setVisible(true);
	}
	
	/**
	 * Speichert die übergenen Einstellungen für den Report-Dialog.
	 * 
	 * @param pref Einstellungen für den Report-Dialog
	 */
	public void setReportPreferences(ReportPreferencesData pref) {
		_rpd = pref;
	}
	
	/**
	 * Öffnet das Fenster der Test-Applikation
	 */
	public static void main(String[] args) {
		new TestDialogApplication();
	}

	/**
	 * Reagier auf die Klicks der Buttons
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			switch (ae.getActionCommand()) {
				// "Über..."-Dialog
				case DIALOG_ABOUT:
					new DlgAbout(this);
					break;
				
				// Lizenz-Dialog
				case DIALOG_LICENSE:
					new DlgLicense(this);
					break;
				
				// Log-Dialog
				case DIALOG_LOG:
					new DlgLogView(this);
					break;
				
				// Export SQL-Script
				case DIALOG_EXPORT_SQL:
					DlgExportSqlScript dlg = new DlgExportSqlScript(this);
					dlg.setVisible(true);
					break;
				
				// Export PDF
				case DIALOG_EXPORT_PDF:
					DlgExportPdf dlg2 = new DlgExportPdf(this);
					dlg2.setVisible(true);
					break;
				
				// Report-Dialog
				case DIALOG_REPORT:
					new DlgReport(_rpd, this);
					break;
				
				// Info-Dialog
				case DIALOG_INFO:
					new DlgInfo("Test", "Dies ist ein Test!", this);
					break;
					
				// Neue Kategorie
				case DIALOG_INPUTCHANGE_CATEGORY_INSERT:
					new DlgInputChange(DlgInputChange.WND_CATEGORY, 
							DlgInputChange.TYPE_INSERT, null, this);
					break;
					
				// Kategorie ändern
				case DIALOG_INPUTCHANGE_CATEGORY_CHANGE:
					new DlgInputChange(DlgInputChange.WND_CATEGORY,
							DlgInputChange.TYPE_CHANGE, "Test", this);
					break;
					
				// Neues Geschäft
				case DIALOG_INPUTCHANGE_SECTION_INSERT:
					new DlgInputChange(DlgInputChange.WND_SECTION,
							DlgInputChange.TYPE_INSERT, null, this);
					break;
					
				// Geschäft ändern
				case DIALOG_INPUTCHANGE_SECTION_CHANGE:
					new DlgInputChange(DlgInputChange.WND_SECTION,
							DlgInputChange.TYPE_CHANGE, "Test", this);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
