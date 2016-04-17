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

package dialogs;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Zeigt die Lizent-Bestimmungen an.
 * 
 * Dies Lizenz-Bestimmungen werden aus der Daten 
 * 
 * @author René Majewski
 */
public class DlgLicense extends JDialog {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -4447016229235653195L;

	/**
	 * Initalisiert das Fenster
	 * 
	 * @param owner Fenster, das den Dialog aufgerufen hat.
	 */
	public DlgLicense(Window owner) {
		// Dialog initalisieren
		super(owner);
		
		// Modal
		setModal(true);
		
		// Größe
		setSize(800, 400);
		
		// Titel
		setTitle("Lizenz");
		
		// TextArea initaliseren
		JTextArea txt = new JTextArea();
		txt.setColumns(80);
		add(new JScrollPane(txt));
		
		// Datei öffnen
		File f = new File("Lizenz.md");

		// Existiert die Lizenz-Datei?
		if (f.exists()) {
			try {
				// Vorbereitungen, um die Datei zu lesen
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				
				// Datei einlesen
				String line = new String();
				do {
					line = br.readLine();
					
					if (line != null) {
						// Zeile in die Text-Ausgabe einfügen
						txt.append(line);
						txt.append("\n");
					}
				} while (line != null);
				
				// Zugriff auf Datei beenden
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// TextArea auf die 1. Zeile setzen und Benutzer kann nicht editieren
		txt.setCaretPosition(0);
		txt.setEditable(false);
		
		// Button
		JPanel pan = new JPanel();
		JButton btn = new JButton("Ok");
		btn.setMnemonic('O');
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});
		pan.add(btn);
		add(pan, BorderLayout.PAGE_END);
		
		// Dialog anzeigen
		setVisible(true);
	}
}
