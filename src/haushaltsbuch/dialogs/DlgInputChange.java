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

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;

/**
 * Dialog zum Eingeben bzw. Ändern einer Kategorie.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.3
 */
public class DlgInputChange extends JDialog implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -3287158860689278026L;
	
	/**
	 * Gibt an, dass es sich um den Eingabe-Dialog für eine Kategorie handelt
	 */
	public static final int WND_CATEGORY = 1;
	
	/**
	 * Gibt an, dass es sich um den Eingabe-Dialog für ein Geschäft handelt.
	 */
	public static final int WND_SECTION = 2;
	
	/**
	 * Gibt an, dass ein Neuer Datensatz erstellt werden soll
	 */
	public static final boolean TYPE_INSERT = false;
	
	/**
	 * GIbt an, dass ein Datensatz geändert werden soll
	 */
	public static final boolean TYPE_CHANGE = true;
	
	/**
	 * Gibt an, dass der Dialog abgebrochen wurde
	 */
	public static final int EXIT_CANCEL = -1;
	
	/**
	 * Gibt an, dass der Dialog beendet wurde.
	 */
	public static final int EXIT_OK = 1;
	
	/**
	 * Speichert das Eingabe-Feld
	 */
	JTextField _txtName;
	
	/**
	 * Speichert das Label 
	 */
	JLabel _label;
	
	/**
	 * Speichert den Button zum übernehmen der Daten
	 */
	JButton _btnOk;
	
	/**
	 * Speichert den Button zum Abbrechen.
	 */
	JButton _btnCancel;
	
	/**
	 * Speichert, wie der Dialog beendet wurde.
	 */
	private int _exit;

	/**
	 * Initialisiert den Dialog
	 */
	public DlgInputChange(int window, boolean type, String value, 
			Window owner) {
		super(owner);
		_exit = 0;
		
		setSize(400, 85);
		setResizable(false);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		_txtName = new JTextField();
		getContentPane().add(_txtName, BorderLayout.CENTER);
		
		if ((value != null) && !value.isEmpty())
			_txtName.setText(value);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		_btnOk = new JButton("Ok");
		_btnOk.addActionListener(this);
		panel.add(_btnOk);
		
		Component horizontalStrut = Box.createHorizontalStrut(100);
		panel.add(horizontalStrut);
		
		_btnCancel = new JButton("Abbrechen");
		_btnCancel.addActionListener(this);
		panel.add(_btnCancel);
		
		_label = new JLabel("");
		getContentPane().add(_label, BorderLayout.WEST);
		
		if (window == WND_CATEGORY) {
			_label.setText("Kategorie");
			
			if (type == TYPE_INSERT)
				setTitle("Neue Kategorie eingeben");
			else
				setTitle("Kategorie ändern");
		} else if (window == WND_SECTION) {
			_label.setText("Geschäft");
			
			if (type == TYPE_INSERT)
				setTitle("Neues Geschäft eingeben");
			else
				setTitle("Geschäft ändern");
		}
		setModal(true);
		
		setVisible(true);
	}
	
	/**
	 * Gibt den eingegebenen Wert zurück.
	 * 
	 * @return Eingegebener Wert 
	 */
	public String getInput() {
		return _txtName.getText();
	}
	
	/**
	 * Gibt zurück, wie der Dialog beendet wurde.
	 * 
	 * @return Wie wurde der Dialog beendet?
	 */
	public int getExit() {
		return _exit;
	}

	/**
	 * Reagiert auf die Klicks auf die Buttons
	 * 
	 * @param ae Daten des Events
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == _btnCancel) {
			setVisible(false);
			_exit = EXIT_CANCEL;
		}
		
		else if (ae.getSource() == _btnOk) {
			setVisible(false);
			_exit = EXIT_OK;
		}
	}
	
}
