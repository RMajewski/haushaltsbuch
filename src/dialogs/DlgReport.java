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

package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import datas.ReportData;

/**
 * Zeigt den Dialog an, um die Einstellungen für die Reports vorzunehmen.
 * 
 * @author René Majewski
 */
public class DlgReport extends JDialog implements ActionListener {
	
	/**
	 * ActionCommand zum Abbrechen
	 */
	public final static String BTN_CANCEL = new String("DlgReportCancel");
	
	/**
	 * ActionCommand zum Report Erstellen
	 */
	public final static String BTN_CREATE = new String("DlgReportCreate");

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -6290197426436360379L;
	
	/**
	 * Report-Erstellung wurde abgebrochen
	 */
	public static final int CANCEL = 1;
	
	/**
	 * Es soll der Report erstellt werden.
	 */
	public static final int CREATE = 2;
	
	/**
	 * Speichert die ComboBox für die Monate
	 */
	private JComboBox<String> _cbMonth;
	
	/**
	 * Speichert die ComboBox für die Auswahl der Jahre
	 */
	private JComboBox<Integer> _cbYear;
	
	/**
	 * Speichert die Einstellungen
	 */
	private ReportData _data;
	
	/**
	 * Initalisiert das Report-Fenster
	 * 
	 * @param report Welcher Report soll voreingestellt sein?
	 */
	public DlgReport(int report) {
		// Klasse initalisieren
		super();
		
		// Daten initalisieren
		_data = new ReportData(report, 0, 0, 0);
		
		// setTitle
		setTitle("Einstellungen um Report zu erzeugen");
		
		// Größe
		setSize(600, 400);
		
		// modal
		setModal(true);
		
		// Design
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Kalender", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblMonth = new JLabel("Monat");
		GridBagConstraints gbc_lblMonth = new GridBagConstraints();
		gbc_lblMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonth.gridx = 1;
		gbc_lblMonth.gridy = 2;
		panel.add(lblMonth, gbc_lblMonth);
		
		_cbMonth = new JComboBox<String>();
		GridBagConstraints gbc_month = new GridBagConstraints();
		gbc_month.insets = new Insets(0, 0, 5, 0);
		gbc_month.fill = GridBagConstraints.HORIZONTAL;
		gbc_month.gridx = 3;
		gbc_month.gridy = 2;
		panel.add(_cbMonth, gbc_month);
		
		JLabel lblYear = new JLabel("Jahr");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(0, 0, 0, 5);
		gbc_lblYear.gridx = 1;
		gbc_lblYear.gridy = 4;
		panel.add(lblYear, gbc_lblYear);
		
		_cbYear = new JComboBox<Integer>();
		GridBagConstraints gbc_cbYear = new GridBagConstraints();
		gbc_cbYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbYear.gridx = 3;
		gbc_cbYear.gridy = 4;
		panel.add(_cbYear, gbc_cbYear);
		
		JPanel calendar = new JPanel();
		getContentPane().add(calendar, BorderLayout.SOUTH);
		calendar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreate = new JButton("Report erstellen");
		btnCreate.setMnemonic('R');
		btnCreate.setActionCommand(BTN_CREATE);
		btnCreate.addActionListener(this);
		calendar.add(btnCreate);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setMnemonic('A');
		btnCancel.setActionCommand(BTN_CANCEL);
		btnCancel.addActionListener(this);
		calendar.add(btnCancel);
		
		// Daten für die Monate
		_cbMonth.addItem("Januar");
		_cbMonth.addItem("Februar");
		_cbMonth.addItem("März");
		_cbMonth.addItem("April");
		_cbMonth.addItem("Mai");
		_cbMonth.addItem("Juni");
		_cbMonth.addItem("Juli");
		_cbMonth.addItem("August");
		_cbMonth.addItem("September");
		_cbMonth.addItem("Oktober");
		_cbMonth.addItem("November");
		_cbMonth.addItem("Dezember");

		// Daten für die Jahre
		for (int i = 2000; i < 2101; i++ ) {
			_cbYear.addItem(Integer.valueOf(i));
		}
		
		// Aktuelles Jahr und aktuellen Monat auswählen auswählen
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(new Date().getTime());
		_cbYear.setSelectedItem(Integer.valueOf(cal.get(GregorianCalendar.YEAR)));
		_cbMonth.setSelectedIndex(cal.get(GregorianCalendar.MONTH));
		
		// Anzeigen
		setVisible(true);
	}

	/**
	 * Reagiert darauf, wenn einer der Button gedrückt wird.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Welcher Befehl?
		if (ae.getActionCommand().equals(BTN_CANCEL)) {
			// Abbrechen
			_data.setFinished(CANCEL);
			setVisible(false);
		} else if (ae.getActionCommand().equals(BTN_CREATE)) {
			_data.setFinished(CREATE);
			_data.setMonth(_cbMonth.getSelectedIndex());
			_data.setYear((Integer)_cbYear.getSelectedItem());
			setVisible(false);
		}
	}

	/**
	 * Gibt die Einstellungen zurück.
	 * 
	 * return Gemachte Einstellungen
	 */
	public ReportData getData() {
		return _data;
	}
}
