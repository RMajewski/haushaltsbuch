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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import haushaltsbuch.datas.Data;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;

/**
 * Zeigt die Elemente an, damit der Benutzer Daten ändern kann. In diesem
 * Fenster wird der Ok- und Abbrechen-Button initalisiert und angezeigt.
 * Fenster, die von diesem Fenster abgeleitet werden, müssen die anderen
 * Steuerelemnte initalisieren.
 * 
 * In der Version 0.2 werden die automatisch erzeugten Elemente nur erzeugt,
 * wenn dies gewünscht wird.
 * 
 * @author René Majewski
 * 
 * @version 0.2
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
	 * @param desktop
	 * @param data
	 * @param frame
	 * 
	 * @deprecated Bitte neuen Konstruktor benutzen:
	 * {@link #WndChangeFrame(Desktop, Data, WndTableFrame, boolean)}
	 */
	public WndChangeFrame(Desktop desktop, Data data, WndTableFrame frame) {
		this(desktop, data, frame, true);
	}
	
	/**
	 * @param desktop
	 * @param data
	 * @param frame
	 * @param create
	 */
	public WndChangeFrame(Desktop desktop, Data data, WndTableFrame frame,
			boolean create) {
		// Klasse initalisieren
		super(desktop);
		
		// Daten speichern
		_frame = frame;
		_data = data;
		
		// Fenster-Titel
		if ((_data == null) || (_data.getId() == -1))
			setTitle("Neuen Datensatz erstellen");
		else
			setTitle("Datensatz ändern");
		
		if (create) {
			// GridBag-Layout initalisieren und setzen
			_gbl = new GridBagLayout();
			setLayout(_gbl);
			
			// Label für die Beschreibung
			JLabel label = new JLabel("Beschreibung");
			addComponent(_gbl, label, 0, 8, 1, 1, 0.2, 0);
			
			// Mehrzeiliger Text für die Beschreibung
			_txtComment = new JTextArea(13, 55);
			_txtComment.setLineWrap(true);
			_txtComment.setWrapStyleWord(true);
			addComponent(_gbl, new JScrollPane(_txtComment), 2, 8, 2, 4, 0.8, 0.5);
			
			// Speichern-Button
			JButton btn = new JButton("Speichern");
			btn.setActionCommand(SAVE);
			btn.setMnemonic('S');
			btn.setSelected(true);
			btn.addActionListener(this);
			addComponent(_gbl, btn, 2, 13, 1, 1, 0, 0);
			
			// Abbrechen-Button
			btn = new JButton("Abbrechen");
			btn.setMnemonic('A');
			btn.setActionCommand(CANCEL);
			btn.addActionListener(this);
			addComponent(_gbl, btn, 3, 13, 1, 1, 0, 0);
		}
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
				StatusBar.getInstance().setMessageAsError(e);
			}
	}
	
	/**
	 * Führt die angegebene SQL-Abfrage aus und fügt der angegebenen ComboBox
	 * den Namen als Eintrag hinzu. Sollte die ID des Namens und die übergebene
	 * ID übereinstimmten, so wird der Namen in der ComboBox ausgewählt.
	 * 
	 * @param sql SQL-Abfrage, mit der die Daten aus der Datenbank ermittelt
	 * werden können.
	 * 
	 * @param id ID, des Namens, der in der ComboBox ausgewählt werden soll
	 * 
	 * @param combo ComboBox, die gefüllt werden soll.
	 */
	protected void queriesAddComboBox(String sql, int id, JComboBox<String> combo) {
		try {
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				combo.addItem(rs.getString("name"));
				
				// Datensatz auswählen?
				if (_data.getId() > -1)
					if (rs.getInt("id") == id)
						combo.setSelectedItem(rs.getString("name"));
			}
			rs.close();
			
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(
					DbController.statusDbError(), e);
		}
	}
}
