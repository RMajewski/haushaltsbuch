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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import haushaltsbuch.dialogs.DlgAbout;
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
	public static String DIALOG_ABOUT = "tests.dialogs.DlgAbout";

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
		JButton btn = new JButton(DIALOG_ABOUT);
		btn.addActionListener(this);
		btn.setActionCommand(DIALOG_ABOUT);
		add(btn);
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
