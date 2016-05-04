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

package haushaltsbuch.dialogs;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import haushaltsbuch.actions.Action;

/**
 * Implementiert einen Dialog, um Informationen anzuzeigen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @version 0.2
 */
public class DlgInfo extends JDialog {

	/**
	 * Serialize ID
	 */
	private static final long serialVersionUID = -6467763716281338318L;

	/**
	 * Initalisiert den Dialog
	 * 
	 * @param title Wird als Titel in der Titelleiste angezeigt.
	 * 
	 * @param message Nachricht, die angezeigt werden soll.
	 * 
	 * @param owner Fenster, dass diesen Dialog anzeigen lässt.
	 */
	public DlgInfo(String title, String message, Frame owner) {
		// Initalisiert die Klasse
		super(owner);
		
		// Modaler Dialog
		setModal(true);
		
		// Titel und Größe des Dialogs
		setTitle(title);
		setSize(400, 200);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Informations-Icon
		URL url = DlgInfo.class.getClassLoader().getResource("icons/info_big.png");
		System.out.println(url);
		if (url != null) {
			Icon icon = new ImageIcon(url);
			getContentPane().add(new JLabel(icon), BorderLayout.WEST);
		}
		
		// Nachricht anzeigen
		JLabel labMessage = new JLabel(message);
		getContentPane().add(labMessage, BorderLayout.CENTER);
		
		// Button
		JPanel panButton = new JPanel();
		getContentPane().add(panButton, BorderLayout.SOUTH);		
		JButton btnOk = new JButton("Ok");
		btnOk.setMnemonic('O');
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
			
		});
		panButton.add(btnOk);
		
		// Dialog anzeigen
		setVisible(true);
	}
}
