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

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import haushaltsbuch.datas.LogData;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.renderer.LogViewListRenderer;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * In diesen Dialog wird das Log angezeigt.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.1
 */
public class DlgLogView extends JDialog implements ActionListener {
	
	/**
	 * Speichert den Titel des Dialogs
	 */
	public static final String DIALOG_TITLE = "Log";
	
	/**
	 * ActionCommand für den Ok-Button
	 */
	private static final String OK = "DlgLogViewOk";

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Initalisiert und zeigt den Dialog an.
	 * 
	 * @param owner Vater-Objekt
	 */
	public DlgLogView(Window owner) {
		// Dialog initalisieren
		super(owner);
		
		// Modaler Dialog
		setModal(true);
		
		// Größe
		setSize(600, 400);
		
		// Dialog-Titel
		setTitle(DIALOG_TITLE);
		
		// Liste anzeigen
		final DefaultListModel<LogData> model = new DefaultListModel<LogData>();
		StatusBar status = StatusBar.getInstance();
		for (int i = 0; i < status.getLog().size(); i++) {
			LogData data = status.getLog().get(i);
			if (data.getOut() != LogData.NO_OUT) {
				model.addElement(data);
			}
		}
		getContentPane().setLayout(new BorderLayout(0, 0));
		JList<LogData> l = new JList<LogData>();
		l.setModel(model);
		l.setCellRenderer(new LogViewListRenderer());
		
		// Scroll-Pane
		JScrollPane pane = new JScrollPane(l);
		getContentPane().add(pane);
		
		// Panel für den Button
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		// Button einfügen
		JButton btnOk = new JButton("Ok");
		btnOk.setMnemonic('O');
		btnOk.addActionListener(this);
		btnOk.setActionCommand(OK);
		panel.add(btnOk);

		
		// Anzeigen
		pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf den Klick des Buttons
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(OK))
			setVisible(false);
	}
}
