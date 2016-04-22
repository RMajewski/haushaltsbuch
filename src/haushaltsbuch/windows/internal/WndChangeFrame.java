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

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import haushaltsbuch.datas.Data;

/**
 * Zeigt die Elemente an, damit der Benutzer Daten ändern kann. In diesem
 * Fenster wird der Ok- und Abbrechen-Button initalisiert und angezeigt.
 * Fenster, die von diesem Fenster abgeleitet werden, müssen die anderen
 * Steuerelemnte initalisieren.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public abstract class WndChangeFrame extends WndInternalFrame implements ActionListener{

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3746578076495646263L;

	/**
	 * Speichert die Datensatz-Daten
	 */
	protected Data _data;
	
	/**
	 * Speichert das Fenster, welches dieses Fenster aufgerufen hat
	 */
	protected WndTableFrame _frame;
	
	/**
	 * Speichert das Steuerelement für die Beschreibung
	 */
	protected JTextArea _txtComment;
	
	/**
	 * ActionCommand für den Speichern-Button
	 */
	protected static final String SAVE = "Save";
	
	/**
	 * ActionCommand für den Abbrechen-Button
	 */
	protected static final String CANCEL = "Cancel";
	
	/**
	 * Speichert das GridBagLayout
	 */
	protected GridBagLayout _gbl; 
	
	/**
	 * 
	 * @param data
	 * @param frame
	 */
	public WndChangeFrame(Data data, WndTableFrame frame) {
		// Klasse initalisieren
		super();
		
		// Daten speichern
		_frame = frame;
		_data = data;
		
		// Fenster-Titel
		if (_data == null)
			setTitle("Neuen Datensatz erstellen");
		else
			setTitle("Datensätz ändern");
	
		// GridBag-Layout initalisieren und setzen
		_gbl = new GridBagLayout();
		setLayout(_gbl);
		
		// Mehrzeiliger Text für die Beschreibung
		_txtComment = new JTextArea();
		addComponent(_gbl, new JScrollPane(_txtComment), 2, 5, 2, 4, 0.8, 0.5);
		
		// Speichern-Button
		JButton btn = new JButton("Speichern");
		btn.setActionCommand(SAVE);
		btn.setMnemonic('S');
		btn.setSelected(true);
		btn.addActionListener(this);
		addComponent(_gbl, btn, 2, 10, 1, 1, 0, 0);
		
		// Abbrechen-Button
		btn = new JButton("Abbrechen");
		btn.setMnemonic('A');
		btn.setActionCommand(CANCEL);
		btn.addActionListener(this);
		addComponent(_gbl, btn, 3, 10, 1, 1, 0, 0);
	}
	
	/**
	 * Reagiert auf den Abbrechen-Button und beendet das Fenster.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Beenden
		if (ae.getActionCommand().compareTo(CANCEL) == 0)
			try {
			 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
