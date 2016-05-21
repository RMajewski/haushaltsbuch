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
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;

import haushaltsbuch.datas.MoneyDetailsData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.dialogs.DlgInfo;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.tables.models.MoneyDetailsListModel;

/**
 * Fenster zum Eingaben der Daten für einen Details-Datensatz von Money.
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.1
 */
public class WndMoneyDetailsChange extends WndChangeFrame
			implements ActionListener, FocusListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 3225451593739557575L;
	
	/**
	 * Speichert die Combo-Box für die Kategorie
	 */
	private JComboBox<String> _cbCategory;
	
	/**
	 * Speichert die Combo-Box für das Geschäft
	 */
	private JComboBox<String> _cbSection;
	
	/**
	 * Speichert die Eingabe für den Betrag
	 */
	private JFormattedTextField _txtMoney;
	
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
	private void queriesAddComboBox(String sql, int id, JComboBox<String> combo) {
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

	/**
	 * Initalisiert das Fenster
	 * 
	 * @param desktop Desktop des Hauptfensters
	 * 
	 * @param data Datensatz, dessen Daten geändert werden sollen
	 * 
	 * @param frame Fenster, welches dieses Fenster aufgereufen hat
	 */
	public WndMoneyDetailsChange(Desktop desktop, MoneyDetailsData data, WndTableFrame frame) {
		super(desktop, data, frame);
		
		// Label für die Kategorie
		JLabel label = new JLabel("Kategorie");
		addComponent(_gbl, label, 0, 0, 1, 1, 0, 0);
		
		// Label für das Geschäft
		label = new JLabel("Geschäft");
		addComponent(_gbl, label, 0, 2, 1, 1, 0, 0);
		
		// Label für den Betrag
		label = new JLabel("Betrag");
		addComponent(_gbl, label, 0, 4, 1, 1, 0, 0);
		
		// Label für die Beschreibung
		label = new JLabel("Beschreibung");
		addComponent(_gbl, label, 0, 6, 1, 1, 0, 0);
		
		// Combo-Box für die Kategorien
		_cbCategory = new JComboBox<String>();
		_cbCategory.setEditable(false);
		addComponent(_gbl, _cbCategory, 2, 0, 2, 1, 0, 0);
		
		// Combo-Box für die Geschäfte
		_cbSection = new JComboBox<String>();
		_cbSection.setEditable(false);		
		addComponent(_gbl, _cbSection, 2, 2, 2, 1, 0, 0);
		
		// Textfeld für den Betrag
		_txtMoney = new JFormattedTextField(new DecimalFormat("0.00"));
		_txtMoney.setText("0,00");
		_txtMoney.addFocusListener(this);
		addComponent(_gbl, _txtMoney, 2, 4, 2, 1, 0, 0);
		
		// Kategorien füllen
		queriesAddComboBox(DbController.queries().category().sort("name"),
				((MoneyDetailsData)_data).getCategoryId(),
				_cbCategory);
		
		// Geschäfte füllen
		queriesAddComboBox(DbController.queries().section().sort("name"),
				((MoneyDetailsData)_data).getSectionId(),
				_cbSection);
		
		// Daten einfügen, wenn Daten übergeben wurden
		if (_data.getId() > -1) {
			// Betrag anzeigen
			_txtMoney.setValue(((MoneyDetailsData)_data).getMoney());
			
			// Beschreibung anzeigen
			_txtComment.setText(((MoneyDetailsData)_data).getComment());
		}
		
		// Fenster anzeigen
		setVisible(true);
		
	}

	/**
	 * Reagiert auf das Drücken der Buttons
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Speichern
		if(ae.getActionCommand().compareTo(SAVE) == 0) {
			try {
				// ID der Kategorie ermitteln
				Statement stm = DbController.getInstance().createStatement();
				ResultSet rs = stm.executeQuery(DbController.queries().category().search("name", String.valueOf(_cbCategory.getSelectedItem())));
				int category = rs.getInt("id");
				rs.close();
				
				// ID des Geschäftes ermitteln
				rs = stm.executeQuery(DbController.queries().section().search("name", String.valueOf(_cbSection.getSelectedItem())));
				int section = rs.getInt("id");
				rs.close();
				
				// Überprüfen ob Betrag eingegeben wurde.
				if (_txtMoney.getText().isEmpty()) {
					// Benutzer darauf hinweisen
					new DlgInfo("Betrag", "Sie haben vergessen den Betrag einzugeben.", null);
					
					// Focus setzen
					_txtMoney.requestFocus();
					
					// Methode verlassen
					return;
				}
				
				// Betrag ermitteln
				double money = 0;
				if (_txtMoney.getValue() != null)
					money = ((Number)_txtMoney.getValue()).doubleValue();
				
				// Beschreibung ermitteln
				_txtComment.selectAll();
				String comment = _txtComment.getSelectedText();
				System.out.println(comment);
				
				// Neuer Datensatz oder Datensatz ändern?
				if (_data.getId() == -1) {
					// Neuer Datensatz
					if (stm.executeUpdate(DbController.queries().moneyDetails().insert(((MoneyDetailsData)_data).getMoneyId(), category, section, money, comment)) > 0)
						StatusBar.getInstance().setMessageAsOk(DbController.queries().moneyDetails().statusInsertOk());
					else
						StatusBar.getInstance().setMessageAsError(
								DbController.queries().moneyDetails()
									.statusInsertError(), new String());
				} else {
					// Datensatz ändern
					String sql;
					
					// Überprüfen ob ein Kommentar angegeben wurde
					if (comment.isEmpty())
						sql = DbController.queries().moneyDetails().update(_data.getId(), ((MoneyDetailsData)_data).getMoneyId(), category, section, money);
					else
						sql = DbController.queries().moneyDetails().update(_data.getId(), ((MoneyDetailsData)_data).getMoneyId(), category, section, money, comment);
					
					// Datenbank-Abfrage stellen
					if (stm.executeUpdate(sql) > 0)
						StatusBar.getInstance().setMessageAsOk(DbController.queries().moneyDetails().statusUpdateOk(_data.getId()));
					else
						StatusBar.getInstance().setMessageAsError(
								DbController.queries().moneyDetails()
									.statusUpdateError(_data.getId()),
								new String());
					
				}
			} catch(SQLException e) {
				StatusBar.getInstance().setMessageAsError(
						DbController.statusDbError(), e);
			}
			
			// Tabelle updaten
			if (_frame != null) {
				((MoneyDetailsListModel)((WndMoneyDetailsList)_frame).getTable().getModel()).dataRefresh(true);;
			}
			
			// Beenden
			try {
				 setClosed(true);
			} catch (Exception e) {
				StatusBar.getInstance().setMessageAsError(e);
			}
			
			// Methode beenden
			return;
		}
		
		// Da bisher nicht beendet, diese Methode in der Vater-Klasse aufrufen
		super.actionPerformed(ae);
	}

	/**
	 * Wird aufgerufen, wenn der Fokus bekommt.
	 * 
	 * @param e Daten dieses Events.
	 */
	@Override
	public void focusGained(FocusEvent e) {
		_txtMoney.setSelectionStart(0);
		_txtMoney.setSelectionEnd(_txtMoney.getText().length());
	}

	/**
	 * Wird aufgerufen, wenn der Fokus verloren geht.
	 * 
	 * @param e Daten zu diesem Event
	 */
	@Override
	public void focusLost(FocusEvent e) {
	}
}
