package dialogs;

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
		
		// Box für den Text initalisieren
		Box b = Box.createVerticalBox();
		b.add(Box.createGlue());
		b.add(new JLabel("Haushaltsbuch"));
		b.add(new JLabel("Version 0.1"));
		b.add(new JLabel("Copyright © by René Majewski"));
		b.add(Box.createGlue());
		b.add(new JLabel("http://github.com/RMajewski/haushaltsbuch"));
		b.add(Box.createGlue());
		getContentPane().add(b, "Center");
		
		// Button
		JButton btn = new JButton("Ok");
		btn.setMnemonic('O');
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});
		getContentPane().add(new JPanel().add(btn), "South");
		
		
		// Dialog anzeigen
		setVisible(true);
	}
}
