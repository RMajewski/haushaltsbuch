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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 * Zeigt die Einstellungen für den Export des SQL-Scripten.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class DlgExportSqlScript extends DlgExport implements ActionListener {
	/**
	 * ActionCommand zum Abbrechen
	 */
	public static final String BTN_CANCEL = "DlgExportSqlScriptCancel";
	
	/**
	 * ActionCommand zum exportieren
	 */
	public static final String BTN_EXPORT = "DlgExportSqlScriptExport";
	
	/**
	 * Einstellung für den Export der Tabelle für die Kategorien
	 */
	public static final String TABLE_CATEGORY = "table.category";
	
	/**
	 * Einstellung für den Export der Tabelle für die Geschäfte
	 */
	public static final String TABLE_SECTION = "table.section";
	
	/**
	 * Einstellung für den Export der Tabelle für die Geld-Übersicht
	 */
	public static final String TABLE_MONEY = "table.money";
	
	/**
	 * Einstellung für den Export der Tabelle für die Geld-Details
	 */
	public static final String TABLE_MONEY_DETAILS = "table.money_details";
	
	/**
	 * Einstellung für den Export der Daten der Kategorien
	 */
	public static final String DATA_CATEGORY = "data.category";
	
	/**
	 * Einstellungen für den Export der Datan der Geschäfte
	 */
	public static final String DATA_SECTION = "data.section";
	
	/**
	 * Einstellungen für den Export der Daten der Geld-Übersicht
	 */
	public static final String DATA_MONEY = "data.money";
	
	/**
	 * Einstellungen für den Export der Dataen der Geld-Details
	 */
	public static final String DATA_MONEY_DETAILS = "data.money_details";
	
	/**
	 * Speichert die Auswahl-Box, ob der Tabellen-Kopf für die Kategorien
	 * exportiert werden soll oder nicht.
	 */
	private JCheckBox _cbTableCategory;
	
	/**
	 * Speichert die Auswahl-Box, ob der Tabellen-Kopf für die Geschäfte
	 * exportiert soll oder nicht.
	 */
	private JCheckBox _cbTableSection;
	
	/**
	 * Speichert die Auswahl-Box, ob der Tabellen-Kopf für die Geld-Übersicht
	 * exportiert werden soll.
	 */
	private JCheckBox _cbTableMoney;
	
	/**
	 * Speichert die Auswahl-Box, ob der Tabellen-Kopf für die Geld-Details
	 * export werden soll.
	 */
	private JCheckBox _cbTableMoneyDetails;
	
	/**
	 * Speichert die Auswahl-Box, ob die Tabellen-Daten für die Kategorien
	 * exportiert werden soll oder nicht.
	 */
	private JCheckBox _cbDataCategory;
	
	/**
	 * Speichert die Auswahl-Box, ob die Tabellen-Daten für die Geschäfte
	 * exportiert soll oder nicht.
	 */
	private JCheckBox _cbDataSection;
	
	/**
	 * Speichert die Auswahl-Box, ob die Tabellen-Daten für die Geld-Übersicht
	 * exportiert werden soll.
	 */
	private JCheckBox _cbDataMoney;
	
	/**
	 * Speichert die Auswahl-Box, ob die Tabellen-Daten für die Geld-Details
	 * export werden soll.
	 */
	private JCheckBox _cbDataMoneyDetails;

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 7761588158976101899L;

	/**
	 * Speichert den Titel des Dialogs
	 */
	public static final String DIALOG_TITLE = "Export: SQL-Script";
	
	/**
	 * Initalisiert diesen Dialog
	 * 
	 * @param owner Fenster, das den Dialog aufgerufen hat.
	 */
	public DlgExportSqlScript(Window owner) {
		// Klasse initalisieren
		super(owner);
		setModal(true);
		
		// Größe
		setSize(450, 300);
		
		// Titel
		setTitle(DIALOG_TITLE);
		
		// Modal
		setModal(true);
		
		// Desion
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblWelcheDatenExportieren = new JLabel("Welche Daten exportieren?");
		GridBagConstraints gbc_lblWelcheDatenExportieren = new GridBagConstraints();
		gbc_lblWelcheDatenExportieren.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcheDatenExportieren.gridx = 1;
		gbc_lblWelcheDatenExportieren.gridy = 1;
		getContentPane().add(lblWelcheDatenExportieren, gbc_lblWelcheDatenExportieren);
		
		JLabel lblWelchenTabellenExportieren = new JLabel("Welche Tabellen exportieren?");
		GridBagConstraints gbc_lblWelchenTabellenExportieren = new GridBagConstraints();
		gbc_lblWelchenTabellenExportieren.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelchenTabellenExportieren.gridx = 0;
		gbc_lblWelchenTabellenExportieren.gridy = 1;
		getContentPane().add(lblWelchenTabellenExportieren, gbc_lblWelchenTabellenExportieren);
		
		_cbTableCategory = new JCheckBox("Kategorien");
		_cbTableCategory.setSelected(true);
		GridBagConstraints gbc_cbTableCategories = new GridBagConstraints();
		gbc_cbTableCategories.anchor = GridBagConstraints.WEST;
		gbc_cbTableCategories.insets = new Insets(0, 0, 5, 5);
		gbc_cbTableCategories.gridx = 0;
		gbc_cbTableCategories.gridy = 2;
		getContentPane().add(_cbTableCategory, gbc_cbTableCategories);
		
		_cbDataCategory = new JCheckBox("Kategorien");
		_cbDataCategory.setSelected(true);
		GridBagConstraints gbc_cbDataCategories = new GridBagConstraints();
		gbc_cbDataCategories.anchor = GridBagConstraints.WEST;
		gbc_cbDataCategories.insets = new Insets(0, 0, 5, 0);
		gbc_cbDataCategories.gridx = 1;
		gbc_cbDataCategories.gridy = 2;
		getContentPane().add(_cbDataCategory, gbc_cbDataCategories);
		
		_cbTableSection = new JCheckBox("Geschäfte");
		_cbTableSection.setSelected(true);
		GridBagConstraints gbc_cbTableSections = new GridBagConstraints();
		gbc_cbTableSections.insets = new Insets(0, 0, 5, 5);
		gbc_cbTableSections.anchor = GridBagConstraints.WEST;
		gbc_cbTableSections.gridx = 0;
		gbc_cbTableSections.gridy = 3;
		getContentPane().add(_cbTableSection, gbc_cbTableSections);
		
		_cbDataSection = new JCheckBox("Geschäfte");
		_cbDataSection.setSelected(true);
		GridBagConstraints gbc_cbDataSections = new GridBagConstraints();
		gbc_cbDataSections.anchor = GridBagConstraints.WEST;
		gbc_cbDataSections.insets = new Insets(0, 0, 5, 0);
		gbc_cbDataSections.gridx = 1;
		gbc_cbDataSections.gridy = 3;
		getContentPane().add(_cbDataSection, gbc_cbDataSections);
		
		_cbDataMoney = new JCheckBox("Geld-Übersicht");
		_cbDataMoney.setSelected(true);
		GridBagConstraints gbc_cbDataMoney = new GridBagConstraints();
		gbc_cbDataMoney.anchor = GridBagConstraints.WEST;
		gbc_cbDataMoney.insets = new Insets(0, 0, 5, 0);
		gbc_cbDataMoney.gridx = 1;
		gbc_cbDataMoney.gridy = 4;
		getContentPane().add(_cbDataMoney, gbc_cbDataMoney);
		
		_cbTableMoney = new JCheckBox("Geld-Übersicht");
		_cbTableMoney.setSelected(true);
		GridBagConstraints gbc_cbTableMoney = new GridBagConstraints();
		gbc_cbTableMoney.insets = new Insets(0, 0, 5, 5);
		gbc_cbTableMoney.anchor = GridBagConstraints.WEST;
		gbc_cbTableMoney.gridx = 0;
		gbc_cbTableMoney.gridy = 4;
		getContentPane().add(_cbTableMoney, gbc_cbTableMoney);
		
		_cbDataMoneyDetails = new JCheckBox("Geld-Details");
		_cbDataMoneyDetails.setSelected(true);
		GridBagConstraints gbc_cbDataMoneyDetails = new GridBagConstraints();
		gbc_cbDataMoneyDetails.anchor = GridBagConstraints.WEST;
		gbc_cbDataMoneyDetails.insets = new Insets(0, 0, 5, 0);
		gbc_cbDataMoneyDetails.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDataMoneyDetails.gridx = 1;
		gbc_cbDataMoneyDetails.gridy = 5;
		getContentPane().add(_cbDataMoneyDetails, gbc_cbDataMoneyDetails);
		
		_cbTableMoneyDetails = new JCheckBox("Geld-Details");
		_cbTableMoneyDetails.setSelected(true);
		GridBagConstraints gbc_cbTableMoneyDetails = new GridBagConstraints();
		gbc_cbTableMoneyDetails.anchor = GridBagConstraints.WEST;
		gbc_cbTableMoneyDetails.insets = new Insets(0, 0, 5, 5);
		gbc_cbTableMoneyDetails.gridx = 0;
		gbc_cbTableMoneyDetails.gridy = 5;
		GridBagConstraints gbc__cbTableMoneyDetails = new GridBagConstraints();
		gbc__cbTableMoneyDetails.anchor = GridBagConstraints.WEST;
		gbc__cbTableMoneyDetails.gridy = 5;
		gbc__cbTableMoneyDetails.gridx = 0;
		getContentPane().add(_cbTableMoneyDetails, gbc__cbTableMoneyDetails);
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(this);
		btnExport.setActionCommand(BTN_EXPORT);
		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.insets = new Insets(0, 0, 5, 5);
		gbc_btnExport.gridx = 0;
		gbc_btnExport.gridy = 8;
		getContentPane().add(btnExport, gbc_btnExport);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(this);
		btnAbbrechen.setActionCommand(BTN_CANCEL);
		GridBagConstraints gbc_btnAbbrechen = new GridBagConstraints();
		gbc_btnAbbrechen.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbbrechen.gridx = 1;
		gbc_btnAbbrechen.gridy = 8;
		getContentPane().add(btnAbbrechen, gbc_btnAbbrechen);
		
		
	}

	/**
	 * Reagiert auf die Clicks der Buttons.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Abbrechen
		if (ae.getActionCommand().equals(BTN_CANCEL)) {
			setVisible(false);
			_cancel = true;
		}
		
		// Export
		else if (ae.getActionCommand().equals(BTN_EXPORT)) {
			// Einstellungen speichern
			if (_cbTableCategory.isSelected())
				_preference.put(TABLE_CATEGORY, 1);
			
			if (_cbTableSection.isSelected())
				_preference.put(TABLE_SECTION, 1);
			
			if (_cbTableMoney.isSelected())
				_preference.put(TABLE_MONEY, 1);
			
			if (_cbTableMoneyDetails.isSelected())
				_preference.put(TABLE_MONEY_DETAILS, 1);
			
			if (_cbDataCategory.isSelected())
				_preference.put(DATA_CATEGORY, 1);
			
			if (_cbDataSection.isSelected())
				_preference.put(DATA_SECTION, 1);
			
			if (_cbDataMoney.isSelected())
				_preference.put(DATA_MONEY, 1);
			
			if (_cbDataMoneyDetails.isSelected())
				_preference.put(DATA_MONEY_DETAILS, 1);
			
			// Dialog beenden
			setVisible(false);
		}
	}
}
