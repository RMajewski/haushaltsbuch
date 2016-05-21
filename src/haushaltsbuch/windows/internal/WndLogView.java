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

package haushaltsbuch.windows.internal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import haushaltsbuch.datas.LogData;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.renderer.LogViewListRenderer;
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;

/**
 * In diesen Dialog wird das Log angezeigt.
 * 
 * In der Version 0.2 wird vom JDialog zum JInternalFrame gewechselt. Bei
 * Fehlern wird noch die Fehlermeldung angezeigt. Die Fehlermeldungen können
 * auch in die Zwischenablage kopiert werden.
 * 
 * @author René Majewski
 *
 * @version 0.2
 * @since 0.1
 */
public class WndLogView extends WndInternalFrame implements ActionListener {
	
	/**
	 * Speichert den Titel des Dialogs
	 */
	public static final String WND_TITLE = "Log";
	
	/**
	 * ActionCommand für den Ok-Button
	 */
	private static final String OK = "DlgLogViewOk";

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die Ausgabe für die Fehlermeldungen
	 */
	private JEditorPane _txtError;


	/**
	 * Initalisiert und zeigt das Fenster an.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public WndLogView(Desktop desktop) {
		// Dialog initalisieren
		super(desktop);
		
		// Dialog-Titel
		setTitle(WND_TITLE);
		
		// Größe
		setSize(1000, 400);
		
		// Liste anzeigen
		final DefaultListModel<LogData> model = new DefaultListModel<LogData>();
		StatusBar status = StatusBar.getInstance();
		for (int i = 0; i < status.getLog().size(); i++) {
			LogData data = status.getLog().get(i);
			if (data.getOut() != LogData.NO_OUT) {
				model.addElement(data);
			}
		}
		
		// Layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Panel für den Button
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		// Button einfügen
		JButton btnOk = new JButton("Ok");
		btnOk.setMnemonic('O');
		btnOk.addActionListener(this);
		btnOk.setActionCommand(OK);
		panel.add(btnOk);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		_txtError = new JEditorPane();
		splitPane.setRightComponent(new JScrollPane(_txtError));
		
		JList<LogData> l = new JList<LogData>();
		l.setModel(model);
		l.setCellRenderer(new LogViewListRenderer());
		
		// Scroll-Pane
		JScrollPane pane = new JScrollPane(l);
		splitPane.setLeftComponent(pane);
		
		// Anzeigen
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
