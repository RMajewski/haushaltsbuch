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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.testsuite.helper.HelperCalendar;

import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;

/**
 * Zeigt das Fenster an, wo eine neue Rückzahlung eingegeben werden kann.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class WndRepayNew extends WndChangeFrame {
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert das Fenster für die Schulden
	 */
	private WndOutstandingChange _outstandingFrame;
	
	/**
	 * Speichert das Eingabe-Feld für das Datum.
	 */
	private JFormattedTextField _txtDate;
	
	/**
	 * Speichert das Eingabe-Feld für die Höhe der Rückzahlung.
	 */
	private JFormattedTextField _txtMoney;

	/**
	 * Initialisiert die Klasse
	 * 
	 * @param desktop Desktop, auf dem das Fenster angezeigt werden soll.
	 * 
	 * @param data Daten des Datensatzes.
	 * 
	 * @param frame Fenster, dass dieses Fenster aufgerufen hat.
	 */
	public WndRepayNew(Desktop desktop, Data data, WndOutstandingChange frame) {
		super(desktop, data, null, true);
		_outstandingFrame = frame;
		
		// Eingabe-Feld für das Datum
		JLabel label = new JLabel("Datum");
		addComponent(_gbl, label, 0, 0, 1, 1, 0.0, 0.0);
		
		_txtDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		_txtDate.setText(HelperCalendar.dateToString(new Date().getTime()));
		addComponent(_gbl, _txtDate, 2, 0, 2, 1, 0.0, 0.0);
		
		// Eingabe-Feld für den Betrag
		label = new JLabel("Höhe der Rückzahlung");
		addComponent(_gbl, label, 0, 2, 1, 1, 0.0, 0.0);

		_txtMoney = new JFormattedTextField(new DecimalFormat("0.00"));
		_txtMoney.setValue(((OutstandingData)_data).getMonthMoney());
		addComponent(_gbl, _txtMoney, 2, 2, 2, 1, 0.0, 0.0);
		
		// Anzeigen
		pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die Einzelnen Klicks auf die Buttons.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Abbrechen
		if (ae.getActionCommand().equals(CANCEL)) {
			dispose();
		}
		
		// Speichern
		else if (ae.getActionCommand().equals(SAVE)) {
			try {
				// Ermitteln ob das Datum schon vorhanden ist.
				long date = new MoneyData().setDate(_txtDate.getText());
				Statement stm = DbController.getInstance().createStatement();
				ResultSet rs = stm.executeQuery(DbController.queries().money()
						.selectDay(date, MoneyData.INT_OUTGOING));
				int moneyid = -1;
				
				// Datum mit Ausgabe schon vorhanden?
				if (rs.next()) {
					// ID ermitteln
					moneyid = rs.getInt("id");
				} else {
					// Neuen Datensatz anlegen
					stm.executeUpdate(DbController.queries().money().insert(date, 
							MoneyData.OUTGOING, ""));
					// ID Ermitteln
					rs = stm.executeQuery(DbController.queries().money()
							.selectDay(date, MoneyData.INT_OUTGOING));
					moneyid = rs.getInt("id");
					rs.close();
				}
				
				// Datensatz für Rückzahlung erstellen
				stm.executeUpdate(DbController.queries().moneyDetails().insert(
						moneyid, 1, ((OutstandingData)_data).getSectionId(), 
						((Number)_txtMoney.getValue()).doubleValue(),
						_txtComment.getText()));
				
				// ID des Datensatzes ermitteln
				rs = stm.executeQuery(DbController.queries().moneyDetails()
						.lastId());
				stm.executeUpdate(DbController.queries().repay().insert(
						rs.getInt("id"), _data.getId()));
				
				// Fenster für Schulden updaten und dieses Fenster schließen
				_outstandingFrame.update();
				dispose();
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(
						DbController.statusDbError(), e);
			}
		}
	}
}
