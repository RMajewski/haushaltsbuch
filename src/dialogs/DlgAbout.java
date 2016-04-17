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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	 * Initalisiert den Dialog und sein Elemente
	 */
	public DlgAbout(Window owner) {
		// Dialog initalisieren
		super(owner);
		
		// Modaler Dialog
		setModal(true);
		
		// Größe
		setSize(325, 200);
		
		// Dialog-Titel
		setTitle("Über ...");
		setName("DlgAbout");
		
		setLayout(new BorderLayout(5, 5));
		
		// Box für den Text initalisieren
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(new JLabel("Haushaltsbuch", JLabel.CENTER));
		pan.add(new JLabel(" "));
		pan.add(new JLabel("Version 0.1"));
		pan.add(new JLabel("Copyright René Majewski"));
		pan.add(new JLabel(" "));
		pan.add(new JLabel("http://github.com/RMajewski/haushaltsbuch"));
		pan.add(new JLabel(" "));
		pan.add(new JLabel(" "));
		pan.add(new JLabel("Diese Software steht unter der EUPL"));
		add(pan, BorderLayout.CENTER);
		
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
		add(pan, BorderLayout.PAGE_END);
		
		// Dialog anzeigen
		setVisible(true);
	}
}
