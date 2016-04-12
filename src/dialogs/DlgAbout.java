package dialogs;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * In diesen Dialog wird etwas über das Programm gesagt.
 * 
 * @author René Majewski
 *
 */
public class DlgAbout extends JDialog {

	/**
	 * Serilisation ID
	 * 
	 * @param owner Instanz, das diesen Dialog aufgerufen hat.
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
		setSize(400, 200);
		
		// Dialog-Titel
		setTitle("Log");
		
		setLayout(new BorderLayout(5, 5));
		
		// Box für den Text initalisieren
		JPanel pan = new JPanel();
		pan.add(new JLabel("Haushaltsbuch"));
		pan.add(new JLabel("Version 0.1"));
		pan.add(new JLabel("Copyright © by René Majewski"));
		pan.add(new JLabel("http://github.com/RMajewski/haushaltsbuch"));
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
