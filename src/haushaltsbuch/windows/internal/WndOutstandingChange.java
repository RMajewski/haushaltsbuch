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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.dialogs.DlgInfo;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.tables.models.MoneyListModel;
import haushaltsbuch.tables.models.OutstandingListModel;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

/**
 * Zeigt das Fenster an, um einen neuen Datensatz anzulegen oder einen
 * Datensa zu ändern in der Datenbank-Tabelle 'outstanding'.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class WndOutstandingChange extends WndChangeFrame  {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ActionCommand für neue Zahlung
	 */
	public static final String REPAY_NEW = "WndOutstandingChange.repayNew";
	
	/**
	 * ActionCommand für Zahlungen suchen
	 */
	public static final String REPAY_SEARCH = 
			"WndOutstandingChange.repaySearch";
	
	/**
	 * Speichert die Tabelle für Rückzahlungen
	 */
	private JTable _table;
	
	/**
	 * Speichert das Label für die Anzahl der restlichen Monate
	 */
	private JLabel _restMonths;
	
	/**
	 * Speichert das Label für die rechtliche Schuld
	 */
	private JLabel _restMoney;
	
	/**
	 * Speichert die Combo-Box für die Auswahl der Geschäfte.
	 */
	private JComboBox<String> _cbSection;
	
	/**
	 * Speichert das Text-Feld für die Höhe der Schulden
	 */
	private JFormattedTextField _txtMoney;
	
	/**
	 * Speichert das Text-Feld für die Anzahl der monatlichen Raten.
	 */
	private JFormattedTextField _txtMonths;
	
	/**
	 * Speichert das Text-Feld für das Datum der 1. Rate
	 */
	private JFormattedTextField _txtStart;
	
	/**
	 * Speichert das Text-Feld für die Höhe der monatlichen Raten
	 */
	private JFormattedTextField _txtMonthMoney;
	
	/**
	 * Speichert die Höhe der Schuld
	 */
	private double _money;

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
	public WndOutstandingChange(Desktop desktop, Data data, WndTableFrame frame) {
		super(desktop, data, frame, false);
		
		_money = 0.00;
		
		// Größe des Fensters festlegen
		setSize(desktop.getWidth(), desktop.getHeight());
		
		// Layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(desktop.getWidth() / 3);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panElements = new JPanel();
		splitPane.setLeftComponent(panElements);
		GridBagLayout gbl_panElements = new GridBagLayout();
		gbl_panElements.columnWidths = new int[]{0, 0, 0};
		gbl_panElements.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panElements.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panElements.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panElements.setLayout(gbl_panElements);
		
		JLabel label = new JLabel("Bei wem?");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(5, 5, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panElements.add(label, gbc_label);
		
		_cbSection = new JComboBox<String>();
		GridBagConstraints gbc__cbSection = new GridBagConstraints();
		gbc__cbSection.insets = new Insets(5, 5, 5, 0);
		gbc__cbSection.fill = GridBagConstraints.HORIZONTAL;
		gbc__cbSection.gridx = 1;
		gbc__cbSection.gridy = 0;
		panElements.add(_cbSection, gbc__cbSection);
		
		label = new JLabel("Schulden");
		gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panElements.add(label, gbc_label);
		
		_txtMoney = new JFormattedTextField(new DecimalFormat("0.00"));
		_txtMoney.setText("0,00");
		_txtMoney.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if (_txtMoney.getValue() != null)
					_money = ((Number)_txtMoney.getValue()).doubleValue();
				else
					_money = 0.00;
			}
		});
		GridBagConstraints gbc__txtMoney = new GridBagConstraints();
		gbc__txtMoney.insets = new Insets(0, 0, 5, 0);
		gbc__txtMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc__txtMoney.gridx = 1;
		gbc__txtMoney.gridy = 1;
		panElements.add(_txtMoney, gbc__txtMoney);
		
		label = new JLabel("Anzahl Monate");
		gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		panElements.add(label, gbc_label);
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(0);
		nf.setMinimumIntegerDigits(1);
		nf.setMaximumIntegerDigits(2);
		_txtMonths = new JFormattedTextField(nf);
		_txtMonths.setText("0");
		_txtMonths.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ((_txtMonths.getValue() != null) && (_money > 0.00)) {
					_txtMonthMoney.setValue(new Double(_money / 
							((Number)_txtMonths.getValue()).intValue()));
				}
			}
		});
		GridBagConstraints gbc__months = new GridBagConstraints();
		gbc__months.insets = new Insets(0, 0, 5, 0);
		gbc__months.fill = GridBagConstraints.HORIZONTAL;
		gbc__months.gridx = 1;
		gbc__months.gridy = 2;
		panElements.add(_txtMonths, gbc__months);
		
		label = new JLabel("Datum 1. Rate");
		gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 3;
		panElements.add(label, gbc_label);
		
		_txtStart = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		GridBagConstraints gbc__txtStart = new GridBagConstraints();
		gbc__txtStart.insets = new Insets(0, 0, 5, 0);
		gbc__txtStart.fill = GridBagConstraints.HORIZONTAL;
		gbc__txtStart.gridx = 1;
		gbc__txtStart.gridy = 3;
		panElements.add(_txtStart, gbc__txtStart);
		
		label = new JLabel("Monatliche Rate");
		gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 4;
		panElements.add(label, gbc_label);
		
		_txtMonthMoney = new JFormattedTextField(new DecimalFormat("0.00"));
		_txtMonthMoney.setText("0,00");
		GridBagConstraints gbc__txtMonthMoney = new GridBagConstraints();
		gbc__txtMonthMoney.insets = new Insets(0, 0, 5, 0);
		gbc__txtMonthMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc__txtMonthMoney.gridx = 1;
		gbc__txtMonthMoney.gridy = 4;
		panElements.add(_txtMonthMoney, gbc__txtMonthMoney);
		
		label = new JLabel("Beschreibung");
		gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(5, 5, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 5;
		panElements.add(label, gbc_label);
		
		_txtComment = new JTextArea(13, 55);
		_txtComment.setLineWrap(true);
		_txtComment.setWrapStyleWord(true);
		GridBagConstraints gbc__txtComment = new GridBagConstraints();
		gbc__txtComment.fill = GridBagConstraints.BOTH;
		gbc__txtComment.gridx = 1;
		gbc__txtComment.gridy = 5;
		gbc__txtComment.gridheight = 2;
		gbc_label.weighty = 0.75;
		JScrollPane scroll = new JScrollPane(_txtComment);
		panElements.add(scroll, gbc__txtComment);
		
		JPanel panRepay = new JPanel();
		panRepay.setLayout(new BorderLayout(0, 0));
	
		_table = new JTable();
		panRepay.add(_table);
		splitPane.setRightComponent(panRepay);
		
		JPanel panRest = new JPanel();
		panRepay.add(panRest, BorderLayout.WEST);
		panRest.setLayout(new GridLayout(4, 1, 0, 0));
		
		label = new JLabel("Restliche Schuld");
		panRest.add(label);
		
		_restMoney = new JLabel("0,00");
		panRest.add(_restMoney);
		
		label = new JLabel("Resliche Monate");
		panRest.add(label);
		
		_restMonths = new JLabel("0");
		panRest.add(_restMonths);
		
		JPanel panRepayButtons = new JPanel();
		panRepay.add(panRepayButtons, BorderLayout.SOUTH);
		panRepayButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewRepay = new JButton("Neue Zahlung");
		btnNewRepay.setMnemonic('Z');
		btnNewRepay.addActionListener(this);
		btnNewRepay.setActionCommand(REPAY_NEW);
		panRepayButtons.add(btnNewRepay);
		
		JButton btnSearch = new JButton("Zahlungen suchen");
		btnSearch.setMnemonic('s');
		btnSearch.addActionListener(this);
		btnSearch.setActionCommand(REPAY_SEARCH);
		panRepayButtons.add(btnSearch);
		
		JPanel panButtons = new JPanel();
		getContentPane().add(panButtons, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Speichern");
		btnSave.setMnemonic('S');
		btnSave.addActionListener(this);
		btnSave.setActionCommand(SAVE);
		panButtons.add(btnSave);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setMnemonic('A');
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand(CANCEL);
		panButtons.add(btnCancel);
		
		// Wenn kein Datensatz angegeben wurde, dann leere Datensatz erstellen
		if (_data == null)
			_data = new OutstandingData();
		else {
			_txtMoney.setValue(((OutstandingData)_data).getMoney());
			_txtMonthMoney.setValue(((OutstandingData)_data).getMonthMoney());
			_txtMonths.setValue(((OutstandingData)_data).getMonths());
			_txtStart.setText(((OutstandingData)_data).getStartAsString());
			_txtComment.setText(((OutstandingData)_data).getComment());
		}
		
		// Geschäfte füllen
		queriesAddComboBox(DbController.queries().section().sort("name"),
				((OutstandingData)_data).getSectionId(), _cbSection);
		
		// Fenster anzeigen und Fokus holen
		setVisible(true);
	}
	
	/**
	 * Reagiert auf das Drücken der Buttons
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().compareTo(SAVE) == 0) {
			// Überprüfen ob die Höhe der Schuld eingegeben wurde.
			if (_txtMoney.getText().isEmpty()) {
				// Benutzer darauf hinweisen
				new DlgInfo("Höhe der Schuld", "Sie haben vergessen den " +
						"Betrag einzugeben.", _desktop.getMainWindow());
				
				// Focus setzen
				_txtMoney.requestFocus();
				
				// Methode verlassen
				return;
			}
			
			// Überprüfen, ob die Höhe der monatlichen Rate eingegeben wurde
			if (_txtMonthMoney.getText().isEmpty()) {
				// Benutzer darauf hinweisen
				new DlgInfo("Höhe der monatlichen Rate", 
						"Sie haben vergessen den Betrag einzugeben.", 
						_desktop.getMainWindow());
				
				// Focus setzen
				_txtMonthMoney.requestFocus();
				
				// Methode verlassen
				return;
			}
			
			// Überprüfen, ob die Anzahl der monatlichen Raten eingegeben wurden
			if (_txtMonths.getText().isEmpty() || 
					_txtMonths.getText().equals("0")) {
				// Benutzer darauf hinweisen
				new DlgInfo("Anzahl der monatlichen Raten", 
						"Sie haben vergessen die Anzahl der monatlichen "+
						"Raten einzugeben.", _desktop.getMainWindow());
				
				// Focus setzen
				_txtMonths.requestFocus();
				
				// Methode verlassen
				return;
				
			}
			
			// Überprüfen, ob das Datum für die 1. Rate eingegeben wurde
			if (_txtStart.getText().isEmpty()) {
				// Benutzer darauf hinweisen
				new DlgInfo("Datum der 1. Rate", 
						"Sie haben vergessen das Datum der 1. Rate einzugeben.", 
						_desktop.getMainWindow());
				
				// Focus setzen
				_txtStart.requestFocus();
				
				// Methode verlassen
				return;
			}
			
			// ID des Geschäftes ermitteln
			try {
				Statement stm = DbController.getInstance().createStatement();
				ResultSet rs = stm.executeQuery(
						DbController.queries().section().search("name",
								String.valueOf(_cbSection.getSelectedItem())));
				int section = rs.getInt("id");
				double money = 0.0;
				if (_txtMoney.getValue() != null)
					money = ((Number)_txtMoney.getValue()).doubleValue();
				
				int months = 0;
				if (_txtMoney.getValue() != null)
					months = ((Number)_txtMonths.getValue()).intValue();
				
				double monthMoney = 0.0;
				if (_txtMonthMoney.getValue() != null)
					monthMoney = 
						((Number)_txtMonthMoney.getValue()).doubleValue();
				
				long start = new MoneyData().setDate(_txtStart.getText());
				String comment = _txtComment.getText();
				
				// Neuer Datensatz oder Datensatz ändern?
				if (_data.getId() == -1) {
					// Neuer Datensatz
					if (stm.executeUpdate(
							DbController.queries().outstanding().insert(
									section, money, months, start, monthMoney,
									comment)) > 0)
						StatusBar.getInstance().setMessageAsOk(
								DbController.queries().outstanding()
								.statusInsertOk());
					else
						StatusBar.getInstance().setMessageAsError(
								DbController.queries().outstanding()
								.statusInsertError(), new String());
				} else {
					// Datensatz ändern
					String sql = new String();
					
					// Überprüfen ob ein Kommentar angegeben wurde
					if (comment.isEmpty())
						sql = DbController.queries().outstanding().update(
								_data.getId(), section, money, months, start,
								monthMoney);
					else
						sql = DbController.queries().outstanding().update(
								_data.getId(), section, money, months, start,
								monthMoney, comment);
					
					// Abfrage an Datenbank stellen
					if (stm.executeUpdate(sql) > 0)
						StatusBar.getInstance().setMessageAsOk(
								DbController.queries().outstanding()
									.statusUpdateOk(_data.getId()));
					else
						StatusBar.getInstance().setMessageAsError(
								DbController.queries().outstanding()
									.statusUpdateError(_data.getId()),
									new String());
				}
					
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(
						DbController.statusDbError(), e);
			}
			
			// Beenden
			try {
				 setClosed(true);
			} catch (Exception e) {
				StatusBar.getInstance().setMessageAsError(e);
			}
			
			// Tabelle updaten
			if (_frame != null) {
				((OutstandingListModel)_frame.getTable().getModel())
					.dataRefresh(true);
				
				if (_data.getId() == -1) {
					_frame.moveToFront();
					try {
						_frame.setSelected(true);
					} catch (PropertyVetoException e) {
						StatusBar.getInstance().setMessageAsError(e);
					}
					_frame.getTable().setRowSelectionInterval(
							_frame.getTable().getRowCount() -1, 
							_frame.getTable().getRowCount() -1);
				}
			}
			
			// Methode beenden
			return;
		}
		
		// Da nich beendet, diese Methode in der Vater-Klasse aufrufen
		super.actionPerformed(ae);
	}

}
