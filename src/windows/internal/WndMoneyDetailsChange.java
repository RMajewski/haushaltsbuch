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

package windows.internal;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import datas.MoneyDetailsData;
import db.DbController;
import elements.StatusBar;
import tables.models.MoneyDetailsListModel;

/**
 * Fenster zum Eingaben der Daten für einen Details-Datensatz von Money.
 * 
 * @author René Majewski
 */
public class WndMoneyDetailsChange extends WndInternalFrame implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 3225451593739557575L;
	
	/**
	 * Speichert den Datensatz.
	 */
	private MoneyDetailsData _data;
	
	/**
	 * Speichert das Fenster, welches dieses Fenster aufgerufen hat.
	 */
	private JInternalFrame _frame;
	
	/**
	 * Speichert das ActionCommand für OK-Button
	 */
	private static final String SAVE = "Save";
	
	/**
	 * Speichert das ActionCommand für den Abbrechen-Button
	 */
	private static final String CANCEL = "Cancel";
	
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
	 * Speichert den Text-Bereich für die Beschreibung
	 */
	private JTextArea _txtComment;

	/**
	 * Initalisiert das Fenster
	 * 
	 * @param data Datensatz, dessen Daten geändert werden sollen
	 * 
	 * @param frame Fenster, welches dieses Fenster aufgereufen hat
	 */
	public WndMoneyDetailsChange(MoneyDetailsData data, JInternalFrame frame) {
		super();
		
		// Datensatz speichern
		_data = data;
		_frame = frame;
		
		// Titel des Fensters
		if (_data.getId() == -1)
			setTitle("Neuen Datensatz erstellen");
		else
			setTitle("Datensatz ändern");
		
		// GridBag-Layout initalisieren und setzen
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		// Label für die Kategorie
		JLabel label = new JLabel("Kategorie");
		addComponent(gbl, label, 0, 0, 1, 1, 0, 0);
		
		// Label für das Geschäft
		label = new JLabel("Geschäft");
		addComponent(gbl, label, 0, 2, 1, 1, 0, 0);
		
		// Label für den Betrag
		label = new JLabel("Betrag");
		addComponent(gbl, label, 0, 4, 1, 1, 0, 0);
		
		// Label für die Beschreibung
		label = new JLabel("Beschreibung");
		addComponent(gbl, label, 0, 6, 1, 1, 0, 0);
		
		// Combo-Box für die Kategorien
		_cbCategory = new JComboBox<String>();
		_cbCategory.setEditable(false);
		addComponent(gbl, _cbCategory, 2, 0, 2, 1, 0, 0);
		
		// Combo-Box für die Geschäfte
		_cbSection = new JComboBox<String>();
		_cbSection.setEditable(false);
		
		addComponent(gbl, _cbSection, 2, 2, 2, 1, 0, 0);
		
		// Textfeld für den Betrag
		_txtMoney = new JFormattedTextField(new DecimalFormat("0.00"));
		_txtMoney.setText("0,00");
		addComponent(gbl, _txtMoney, 2, 4, 2, 1, 0, 0);
		
		// Text-Bereich für die Beschreibung
		_txtComment = new JTextArea();
		addComponent(gbl, _txtComment, 2, 6, 2, 4, 0, 0.5);
		
		try {
			// FIXME In eine private Methode packen
			// Kategorien füllen
			Statement stm = DbController.getInstance().createStatement();
			ResultSet rs = stm.executeQuery(DbController.queries().category().select());
			while (rs.next()) {
				_cbCategory.addItem(rs.getString("name"));
				
				// Datensatz ändern
				if (_data.getId() > -1)
					if (rs.getInt("id") == _data.getCategoryId())
						_cbCategory.setSelectedItem(rs.getString("name"));
			}
			
			// Geschäfte füllen
			rs = stm.executeQuery(DbController.queries().section().select());
			while (rs.next()) {
				_cbSection.addItem(rs.getString("name"));
				
				// Datensatz ändern
				if (_data.getId() > -1)
					if (rs.getInt("id") == _data.getCategoryId())
						_cbSection.setSelectedItem(rs.getString("name"));
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
			e.printStackTrace();
		}
		
		// Speichern-Button
		JButton btn = new JButton("Speichern");
		btn.setActionCommand(SAVE);
		btn.setMnemonic('S');
		btn.setSelected(true);
		btn.addActionListener(this);
		addComponent(gbl, btn, 2, 10, 1, 1, 0, 0);
		
		// Abbrechen-Button
		btn = new JButton("Abbrechen");
		btn.setMnemonic('A');
		btn.setActionCommand(CANCEL);
		btn.addActionListener(this);
		addComponent(gbl, btn, 3, 10, 1, 1, 0, 0);
		
		// Daten einfügen, wenn Daten übergeben wurden
		if (_data.getId() > -1) {
			// Betrag anzeigen
			_txtMoney.setValue(_data.getMoney());
			
			// Beschreibung anzeigen
			_txtComment.setText(_data.getComment());
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
		// Beenden
		if (ae.getActionCommand().compareTo(CANCEL) == 0)
			try {
			 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// Speichern
		else if(ae.getActionCommand().compareTo(SAVE) == 0) {
			try {
				// ID der Kategorie ermitteln
				Statement stm = DbController.getInstance().createStatement();
				ResultSet rs = stm.executeQuery(DbController.queries().category().search("name", String.valueOf(_cbCategory.getSelectedItem())));
				int category = rs.getInt("id");
				
				// ID des Geschäftes ermitteln
				rs = stm.executeQuery(DbController.queries().section().search("name", String.valueOf(_cbSection.getSelectedItem())));
				int section = rs.getInt("id");
				
				// Betrag ermitteln
				double money = ((Number)_txtMoney.getValue()).doubleValue();
				
				// Beschreibung ermitteln
				String comment = _txtComment.getText();
				
				// Neuer Datensatz oder Datensatz ändern?
				if (_data.getId() == -1) {
					// Neuer Datensatz
					System.out.println(DbController.queries().moneyDetails().insert(_data.getMoneyId(), category, section, money, comment));
					if (stm.executeUpdate(DbController.queries().moneyDetails().insert(_data.getMoneyId(), category, section, money, comment)) > 0)
						StatusBar.getInstance().setMessageAsOk(DbController.queries().moneyDetails().statusInsertOk());
					else
						StatusBar.getInstance().setMessageAsError(DbController.queries().moneyDetails().statusInsertError());
				} else {
					// Datensatz ändern
					String sql;
					
					// Überprüfen ob ein Kommentar angegeben wurde
					if (comment.isEmpty())
						sql = DbController.queries().moneyDetails().update(_data.getId(), _data.getMoneyId(), category, section, money);
					else
						sql = DbController.queries().moneyDetails().update(_data.getId(), _data.getMoneyId(), category, section, money, comment);
					
					// Datenbank-Abfrage stellen
					if (stm.executeUpdate(sql) > 0)
						StatusBar.getInstance().setMessageAsOk(DbController.queries().moneyDetails().statusUpdateOk(_data.getId()));
					else
						StatusBar.getInstance().setMessageAsError(DbController.queries().moneyDetails().statusUpdateError(_data.getId()));
					
				}
			} catch(SQLException e) {
				StatusBar.getInstance().setMessageAsError(DbController.statusDbError());
				e.printStackTrace();
			}
			
			// Tabelle updaten
			if (_frame != null) {
				((MoneyDetailsListModel)((WndMoneyDetailsList)_frame).getTable().getModel()).dataRefresh(true);;
			}
			
			// Beenden
			try {
				 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
