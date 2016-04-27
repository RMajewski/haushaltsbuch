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

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.tables.models.MoneyListModel;
import tests.tests.windows.internal.TestWndMoneyChange;


/**
 * Zeigt das Fenster an, um einen neuen Datensatz anzulegen oder einen
 * Datensazu zu ändern in der Datenbank-Tabelle 'money'.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class WndMoneyChange extends WndChangeFrame {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 3911001002528630785L;
	
	/**
	 * Speichert das Textfeld für das Datum.
	 */
	private JFormattedTextField _txtDate; 
	
	/**
	 * Speichert den Radio-Button für die Einnahme
	 */
	private JRadioButton _rbIn;
	
	/**
	 * Speichert den Radio-Button für die Ausgabe
	 */
	private JRadioButton _rbOut;

	/**
	 * Initalisiert das Fenster.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 * 
	 * @param data Datensatz, der geändert werden soll. Wird <b>null</b>
	 * angegeben, so wird ein neuer Datensatz erstellt.
	 * 
	 * @param frame Fenster, aus dem dieses Fenster aufgerufen wurde.
	 */
	public WndMoneyChange(Desktop desktop, MoneyData data, WndTableFrame frame) {
		
		// Fenster initalisieren
		super(desktop, data, frame);
		
		// Label für das Datum
		JLabel label = new JLabel("Datum");
		addComponent(_gbl, label, 0, 0, 1, 1, 0.2, 0);
		
		// Textfeld für das Datum
		_txtDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		addComponent(_gbl, _txtDate, 2, 0, 2, 1, 0.8, 0);
		_txtDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(new Date());
				String res = _txtDate.getText();
				
				String[] tmp = res.split("\\.");
				String date = new String();
				
				if (tmp.length == 1)
					date = res + 
							String.valueOf(gc.get(GregorianCalendar.MONTH) + 1) + 
							"." + String.valueOf(gc.get(GregorianCalendar.YEAR));
				
				if (tmp.length == 2)
					date = res + String.valueOf(gc.get(GregorianCalendar.YEAR));
				
				_txtDate.setText(date);
				System.out.println();
				System.out.println();
				System.out.println(res);
				System.out.println();
				System.out.println();
			}
		});
		
		// ButtonGroup initalisieren
		ButtonGroup bg = new ButtonGroup();
		
		// Radion-Button für die Einnahme
		_rbIn = new JRadioButton("Einnahme");
		addComponent(_gbl, _rbIn, 2, 2, 2, 1, 0, 0);
		_rbIn.setSelected(true);
		bg.add(_rbIn);
		
		// Radio-Button für die Ausgabe
		_rbOut = new JRadioButton("Ausgabe");
		addComponent(_gbl, _rbOut, 2, 3, 2, 1, 0, 0);
		bg.add(_rbOut);
		
		// Label für die Beschreibung
		label = new JLabel("Beschreibung");
		addComponent(_gbl, label, 0, 5, 1, 1, 0.2, 0);
		
		// Daten einfügen?
		if (_data != null) {
			// Datum einfügen
			_txtDate.setText(((MoneyData)_data).getDateAsString());
			
			// Einnahme oder Ausgabe
			if (((MoneyData)_data).getInOut() == MoneyData.INCOMING)
				_rbIn.setSelected(true);
			else
				_rbOut.setSelected(true);
			
			// Beschreibung einfügen
			_txtComment.setText(((MoneyData)_data).getComment());
		}
		
		// Fenster anzeigen und Fokus holen
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// Speichern
		if (ae.getActionCommand().compareTo(SAVE) == 0) {
			// Überprüfen ob kein Datum eingegeben wurde
			if (_txtDate.getText().isEmpty()) {
				// Benutzer darauf hinweisen
				JOptionPane.showConfirmDialog(this, "Sie haben vergessen ein Datum einzugeben.", "Datum",JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				// Focus setzen
				_txtDate.requestFocus();
				
				// Methode verlassen
				return;
			}
			
			// Datum umwandeln
			long date = new MoneyData().setDate(_txtDate.getText());
			
			// InOut ermitteln
			boolean inout = false;
			if (_rbOut.isSelected()) {
				inout = MoneyData.OUTGOING;
			} else if (_rbIn.isSelected()) {
				inout = MoneyData.INCOMING;
			}
			
			// Beschreibung ermitteln
			String comment = _txtComment.getText();
			
			try {
				// Datenabnk-Abfrage vorbereiten
				Statement stm = DbController.getInstance().createStatement();
				
				// Neuer Datensatz oder Datensatz ändern?
				if (_data == null) {
					// Neuer Datensatz
					if (stm.executeUpdate(DbController.queries().money().insert(date, inout, comment)) > 0)
						StatusBar.getInstance().setMessageAsOk(DbController.queries().money().statusInsertOk());
					else
						StatusBar.getInstance().setMessageAsError(DbController.queries().money().statusInsertError());
				} else {
					// Datensatz ändern
					String sql;
					
					// Überprüfen ob ein Kommentar angegeben wurde
					if (comment.isEmpty())
						sql = DbController.queries().money().update(_data.getId(), date, inout);
					else
						sql = DbController.queries().money().update(_data.getId(), date, inout, comment);
					
					// Datenbank-Abfrage stellen
					if (stm.executeUpdate(sql) > 0)
						StatusBar.getInstance().setMessageAsOk(DbController.queries().money().statusUpdateOk(_data.getId()));
					else
						StatusBar.getInstance().setMessageAsError(DbController.queries().money().statusUpdateError(_data.getId()));
					
				}
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
				e.printStackTrace();
			}
			
			// Tabelle updaten
			if (_frame != null) {
				((MoneyListModel)_frame.getTable().getModel()).dataRefresh(true);
			}
			
			// Beenden
			try {
				 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Methode beenden
			return;
		}
		
		// Da nich beendet, diese Methode in der Vater-Klasse aufrufen
		super.actionPerformed(ae);
	}
}
