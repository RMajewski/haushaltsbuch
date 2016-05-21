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
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import haushaltsbuch.datas.LogData;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.renderer.LogViewListRenderer;
import haushaltsbuch.text.ErrorSyntax;

import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.Component;
import javax.swing.Box;

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
public class WndLogView extends WndInternalFrame 
		implements ActionListener, ListSelectionListener, ClipboardOwner {
	
	/**
	 * Speichert den Titel des Dialogs
	 */
	public static final String WND_TITLE = "Log";
	
	/**
	 * ActionCommand für den Ok-Button
	 */
	private static final String OK = "WndLogViewOk";
	
	/**
	 * ActionCommand zum Einfügen in die ZWischenablage
	 */
	private static final String CLIPBOARD = "WndLogViewClipboard";
	
	/**
	 * ActionCommand zum Speichern der Log
	 */
	private static final String SAVE = "WndLogViewSave";

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die Ausgabe für die Fehlermeldungen
	 */
	private JEditorPane _txtError;
	
	/**
	 * Speichert die Liste der Meldungen
	 */
	private JList<LogData> _listLog;


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
		status.setMessageAsError(new Exception());
		status.setMessageAsError(new Exception("Dies ist ein Test"));
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
		
		Component horizontalStrut = Box.createHorizontalStrut(75);
		panel.add(horizontalStrut);
		
		JButton btnClipboard = new JButton("In die Zwischenablage");
		btnClipboard.setMnemonic('Z');
		btnClipboard.setActionCommand(CLIPBOARD);
		btnClipboard.addActionListener(this);
		panel.add(btnClipboard);
		
		JButton btnSave = new JButton("Speichern");
		btnSave.setMnemonic('S');
		btnSave.setActionCommand(SAVE);
		btnSave.addActionListener(this);
		panel.add(btnSave);
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		_txtError = new JEditorPane();
		_txtError.setEditable(false);
		_txtError.setEditorKitForContentType("text", editorkit());
		_txtError.setContentType("text");
		_txtError.setBackground(Color.black);
		splitPane.setRightComponent(new JScrollPane(_txtError));
		
		_listLog = new JList<LogData>();
		_listLog.setModel(model);
		_listLog.setCellRenderer(new LogViewListRenderer());
		_listLog.addListSelectionListener(this);
		
		// Scroll-Pane
		JScrollPane pane = new JScrollPane(_listLog);
		splitPane.setLeftComponent(pane);
		
		// Anzeigen
		setVisible(true);
	}
	
	private StyledEditorKit editorkit() {
		return new StyledEditorKit() {
			private static final long serialVersionUID = 1L;

			@Override
			public Document createDefaultDocument() {
				ErrorSyntax syntax = new ErrorSyntax();
				syntax.addKeyword("java", 
						ErrorSyntax.DEFAULT_ERROR);
				syntax.addException("java.lang.Exception");
				syntax.addClassName("haushaltsbuch", 
						ErrorSyntax.DEFAULT_CLASSES);
				return syntax;
			}
		};
	}

	/**
	 * Reagiert auf den Klick eines der Buttons
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(OK))
			setVisible(false);
		
		else if (ae.getActionCommand().equals(CLIPBOARD)) {
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection sc = new StringSelection(_txtError.getText());
			cb.setContents(sc, this);
		}
		
		else if (ae.getActionCommand().equals(SAVE)) {
			// Speichern-Dialog aufrufen
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("Log-Datei log", "log"));
			fc.setDialogTitle("Log speichern");
			int state = fc.showSaveDialog(null);
			
			if (state == JFileChooser.APPROVE_OPTION) {
				try {
					File file = fc.getSelectedFile();
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					
					for (int i = 0; i < _listLog.getModel().getSize(); i++) {
						if (i > 0)
							bw.write(System.lineSeparator());
						
						LogData data = _listLog.getModel().getElementAt(i);
						
						bw.write(data.getMessage());
						bw.write(System.lineSeparator());
						
						if (!data.getError().isEmpty()) {
							bw.write(data.getError());
							bw.write(System.lineSeparator());
						}
					}
					
					bw.close();
				} catch (IOException e) {
					StatusBar.getInstance().setMessageAsError(e);
				}
			}
		}
	}

	/**
	 * Reagiert darauf, wenn in der Liste eine Zeile selektiert wird.
	 * 
	 * @param e Event-Daten
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getFirstIndex() > -1) {
			if (!_listLog.getSelectedValue().getError().isEmpty())
				_txtError.setText(_listLog.getSelectedValue().getError());
			else
				_txtError.setText("Kein Fehlerbericht vorhanden");
		}
	}

	/**
	 * Wird nicht benutzt. Muss aber durch den Zugriff auf die Zwischenablage
	 * implementiert sein.
	 */
	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
	}
}
