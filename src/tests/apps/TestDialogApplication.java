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
import javax.swing.JDialog;
import javax.swing.JFrame;

import haushaltsbuch.dialogs.DlgAbout;
import haushaltsbuch.dialogs.DlgExportSqlScript;
import haushaltsbuch.dialogs.DlgLicense;
import haushaltsbuch.dialogs.DlgLogView;
import tests.tests.dialogs.TestDlgAbout;

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
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -3655292066616840554L;

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

		// Fenster Anzeigen
		setVisible(true);
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
			// "Über..."-Dialog
			if (ae.getActionCommand().equals(DIALOG_ABOUT))
				new DlgAbout(this);
			
			// Lizenz-Dialog
			else if(ae.getActionCommand().equals(DIALOG_LICENSE))
				new DlgLicense(this);
			
			// Log-Dialog
			else if(ae.getActionCommand().equals(DIALOG_LOG))
				new DlgLogView(this);
			
			// Export SQL-Script
			else if(ae.getActionCommand().equals(DIALOG_EXPORT_SQL)) {
				DlgExportSqlScript dlg = new DlgExportSqlScript(this);
				dlg.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
