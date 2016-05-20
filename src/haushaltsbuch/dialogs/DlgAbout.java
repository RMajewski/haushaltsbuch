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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * In diesen Dialog wird etwas über das Programm gesagt.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class DlgAbout extends JDialog {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -1375741277581661L;
	
	/**
	 * Speichert den Titel dieses Dialoges
	 */
	public static final String DIALOG_TITLE = new String("Über ...");
	
	/**
	 * Speichert den Namen dieses Dialoges
	 */
	public static final String DIALOG_NAME = new String("dialogs.DlgAbout");

	/**
	 * Initalisiert den Dialog und sein Elemente
	 */
	public DlgAbout(Window owner) {
		// Dialog initalisieren
		super(owner);
		setResizable(false);
		
		// Modaler Dialog
		setModal(true);
		
		// Größe
		setSize(340, 200);
		
		// Dialog-Titel
		setTitle(DIALOG_TITLE);
		setName(DIALOG_NAME);
		getContentPane().setLayout(new BorderLayout(0, 10));
		
		// Box für den Text initalisieren
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(0, 10));
		JLabel label = new JLabel("Haushaltsbuch");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(325, 20));
		pan.add(label, BorderLayout.NORTH);
		getContentPane().add(pan);
		
		JPanel panel_1 = new JPanel();
		pan.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		JLabel label_1 = new JLabel("http://github.com/RMajewski/haushaltsbuch");
		panel_1.add(label_1, BorderLayout.SOUTH);
		JLabel label_3 = new JLabel("Version 0.3");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3, BorderLayout.CENTER);
		JLabel label_2 = new JLabel("Diese Software steht unter der EUPL");
		pan.add(label_2, BorderLayout.SOUTH);
		
		// Button
		pan = new JPanel();
		JButton btn = new JButton("Ok");
		btn.setMnemonic('O');
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});
		pan.add(btn);
		getContentPane().add(pan, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(panel_2, BorderLayout.WEST);
		
		// Dialog anzeigen
		setVisible(true);
	}
}
