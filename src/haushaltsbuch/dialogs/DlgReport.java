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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportWeekData;

/**
 * Zeigt den Dialog an, um die Einstellungen für die Reports vorzunehmen.
 * 
 * @author René Majewski
 */
public class DlgReport extends JDialog implements ActionListener, ItemListener {
	
	/**
	 * ActionCommand zum Abbrechen
	 */
	public final static String BTN_CANCEL = new String("DlgReportCancel");
	
	/**
	 * ActionCommand zum Report Erstellen
	 */
	public final static String BTN_CREATE = new String("DlgReportCreate");
	
	/**
	 * Speichert den Titel des Dialogs.
	 */
	public final static String DIALOG_TITLE = new String("Einstellungen um Report zu erzeugen");

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
	 * Speichert den RadioButton zur Auswahl der Wochenübersicht
	 */
	private JRadioButton _rbWeek;
	
	/**
	 * Speichert den RadioButton zur Auswahl der Monatsübersicht
	 */
	private JRadioButton _rbMonth;
	
	/**
	 * Speichert den RadioButton zur Auswahl der Jahresübersicht
	 */
	private JRadioButton _rbYear;
	
	/**
	 * Speichert den RadioButton zur Auswahl der Übersicht der Geschäfte
	 */
	private JRadioButton _rbSection;
	
	/**
	 * Speichert den RadioButton zur Auswahl der Übersicht der Kategorien
	 */
	private JRadioButton _rbCategory;
	
	/**
	 * Speichert die CheckBox für Wochenübersicht -> von
	 */
	private JCheckBox _cbWeekDateFrom;
	
	/**
	 * Speichert die Checkbox für Wochenübersicht -> bis
	 */
	private JCheckBox _cbWeekDateTo;
	
	/**
	 * Speichert die Einstellungen
	 */
	private ReportPreferencesData _data;
	
	/**
	 * Initalisiert das Report-Fenster
	 * 
	 * @param preferences Einstellungen für die Reports
	 * 
	 * @param owner Fenster, das den Dialog aufgerufen hat.
	 */
	public DlgReport(ReportPreferencesData preferences, Window owner) {
		// Klasse initalisieren
		super(owner);
		
		// Daten initalisieren
		_data = preferences;
		
		// setTitle
		setTitle(DIALOG_TITLE);
		
		// Größe
		setSize(600, 400);
		
		// modal
		setModal(true);
		
		// Design
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panMain = new JPanel();
		tabbedPane.addTab("Kalender", null, panMain, null);
		tabbedPane.setEnabledAt(0, true);
		GridBagLayout gbl_panMain = new GridBagLayout();
		gbl_panMain.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panMain.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panMain.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panMain.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panMain.setLayout(gbl_panMain);
		
		JLabel lblReport = new JLabel("Report");
		GridBagConstraints gbc_lblReport = new GridBagConstraints();
		gbc_lblReport.insets = new Insets(0, 0, 5, 5);
		gbc_lblReport.gridx = 1;
		gbc_lblReport.gridy = 1;
		panMain.add(lblReport, gbc_lblReport);
		
		JPanel panReport = new JPanel();
		GridBagConstraints gbc_panReport = new GridBagConstraints();
		gbc_panReport.gridheight = 2;
		gbc_panReport.insets = new Insets(0, 0, 5, 0);
		gbc_panReport.fill = GridBagConstraints.BOTH;
		gbc_panReport.gridx = 3;
		gbc_panReport.gridy = 1;
		panMain.add(panReport, gbc_panReport);
		panReport.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		_rbWeek = new JRadioButton("Wochenübersicht");
		panReport.add(_rbWeek);
		
		_rbMonth = new JRadioButton("Monatsübersicht");
		panReport.add(_rbMonth);
		
		_rbYear = new JRadioButton("Jahresübersicht");
		panReport.add(_rbYear);
		
		_rbCategory = new JRadioButton("Übersicht Kategorien");
		panReport.add(_rbCategory);
		
		_rbSection = new JRadioButton("Übersicht Geschäfte");
		panReport.add(_rbSection);
		
		ButtonGroup g = new ButtonGroup();
		g.add(_rbWeek);
		g.add(_rbMonth);
		g.add(_rbYear);
		g.add(_rbCategory);
		g.add(_rbSection);
		
		JLabel lblMonth = new JLabel("Monat");
		GridBagConstraints gbc_lblMonth = new GridBagConstraints();
		gbc_lblMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonth.gridx = 1;
		gbc_lblMonth.gridy = 3;
		panMain.add(lblMonth, gbc_lblMonth);
		
		_cbMonth = new JComboBox<String>();
		GridBagConstraints gbc_month = new GridBagConstraints();
		gbc_month.insets = new Insets(0, 0, 5, 0);
		gbc_month.fill = GridBagConstraints.HORIZONTAL;
		gbc_month.gridx = 3;
		gbc_month.gridy = 3;
		panMain.add(_cbMonth, gbc_month);
		
		JLabel lblYear = new JLabel("Jahr");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(0, 0, 0, 5);
		gbc_lblYear.gridx = 1;
		gbc_lblYear.gridy = 5;
		panMain.add(lblYear, gbc_lblYear);
		
		_cbYear = new JComboBox<Integer>();
		_cbYear.setEditable(true);
		GridBagConstraints gbc_cbYear = new GridBagConstraints();
		gbc_cbYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbYear.gridx = 3;
		gbc_cbYear.gridy = 5;
		panMain.add(_cbYear, gbc_cbYear);
		
		JPanel panButtons = new JPanel();
		getContentPane().add(panButtons, BorderLayout.SOUTH);
		panButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreate = new JButton("Report erstellen");
		btnCreate.setMnemonic('R');
		btnCreate.setActionCommand(BTN_CREATE);
		btnCreate.addActionListener(this);
		panButtons.add(btnCreate);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setMnemonic('A');
		btnCancel.setActionCommand(BTN_CANCEL);
		btnCancel.addActionListener(this);
		panButtons.add(btnCancel);
		
		JPanel panMonth = new JPanel();
		tabbedPane.addTab("Monatsübersicht", null, panMonth, null);
		tabbedPane.setEnabledAt(1, true);
		GridBagLayout gbl_panMonth = new GridBagLayout();
		gbl_panMonth.columnWidths = new int[] {4, 0, 4, 0, 3};
		gbl_panMonth.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 10};
		gbl_panMonth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panMonth.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panMonth.setLayout(gbl_panMonth);
		
		JLabel lblSpalten = new JLabel("Spalten");
		GridBagConstraints gbc_lblSpalten = new GridBagConstraints();
		gbc_lblSpalten.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpalten.gridx = 1;
		gbc_lblSpalten.gridy = 1;
		panMonth.add(lblSpalten, gbc_lblSpalten);
		
		_cbWeekDateFrom = new JCheckBox("von");
		_cbWeekDateFrom.addItemListener(this);
		GridBagConstraints gbc_cbFrom = new GridBagConstraints();
		gbc_cbFrom.anchor = GridBagConstraints.WEST;
		gbc_cbFrom.insets = new Insets(0, 0, 5, 0);
		gbc_cbFrom.gridx = 3;
		gbc_cbFrom.gridy = 1;
		panMonth.add(_cbWeekDateFrom, gbc_cbFrom);
		
		_cbWeekDateTo = new JCheckBox("bis");
		_cbWeekDateTo.addItemListener(this);
		GridBagConstraints gbc_cbTo = new GridBagConstraints();
		gbc_cbTo.anchor = GridBagConstraints.WEST;
		gbc_cbTo.insets = new Insets(0, 0, 5, 0);
		gbc_cbTo.gridx = 3;
		gbc_cbTo.gridy = 2;
		panMonth.add(_cbWeekDateTo, gbc_cbTo);
		
		JLabel lblWochen = new JLabel("einfügen");
		GridBagConstraints gbc_lblWochen = new GridBagConstraints();
		gbc_lblWochen.insets = new Insets(0, 0, 5, 5);
		gbc_lblWochen.gridx = 1;
		gbc_lblWochen.gridy = 2;
		panMonth.add(lblWochen, gbc_lblWochen);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		panMonth.add(panel, gbc_panel);
		
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
		
		// Einstellungen für Report?
		switch(_data.getType()) {
			// Wochenübersicht
			case ReportPreferencesData.TYPE_WEEK:
				_rbWeek.setSelected(true);
				break;
				
			// Monatsübersicht
			case ReportPreferencesData.TYPE_MONTH:
				_rbMonth.setSelected(true);
				break;
				
			// Jahresübersicht
			case ReportPreferencesData.TYPE_YEAR:
				_rbYear.setSelected(true);
				break;
				
			// Übersicht Kategorien
			case ReportPreferencesData.TYPE_CATEGORY:
				_rbCategory.setSelected(true);
				break;
				
			// Übersicht Geschäfte
			case ReportPreferencesData.TYPE_SECTION:
				_rbSection.setSelected(true);
				break;
		}

		// Daten für die Jahre
		for (int i = 2000; i < 2101; i++ ) {
			_cbYear.addItem(Integer.valueOf(i));
		}
		
		// Wurde ein Datum übergeben?
		if (_data.getMonth() < 0) {
			// Aktuelles Jahr und aktuellen Monat auswählen auswählen
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(new Date().getTime());
			_cbYear.setSelectedItem(Integer.valueOf(cal.get(GregorianCalendar.YEAR)));
			_cbMonth.setSelectedIndex(cal.get(GregorianCalendar.MONTH));
		} else {
			_cbYear.setSelectedItem(Integer.valueOf(_data.getYear()));
			_cbMonth.setSelectedIndex(_data.getMonth());
		}
		
		// Wurde Wochenübersicht 'von' ausgewählt?
		if (_data.getPreference(ReportWeekData.DRAW_DATE_FROM) != null)
			_cbWeekDateFrom.setSelected(true);
		
		// Wurde Wochenübersicht 'bis' ausgewählt?
		if (_data.getPreference(ReportWeekData.DRAW_DATE_TO) != null)
			_cbWeekDateTo.setSelected(true);
		
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
			
			// Wurde Wochenübersicht ausgewählt?
			if (_rbWeek.isSelected())
				_data.setType(ReportPreferencesData.TYPE_WEEK);
			
			// Wurde Monatsübersicht ausgewählt?
			if (_rbMonth.isSelected())
				_data.setType(ReportPreferencesData.TYPE_MONTH);
			
			// Wurde Jahrsübersicht ausgewählt?
			if (_rbYear.isSelected())
				_data.setType(ReportPreferencesData.TYPE_YEAR);
			
			// Wurde Übersicht Kategorien ausgewählt?
			if (_rbCategory.isSelected())
				_data.setType(ReportPreferencesData.TYPE_CATEGORY);
			
			// Wurde Übersicht Geschäfte ausgewählt?
			if (_rbSection.isSelected())
				_data.setType(ReportPreferencesData.TYPE_SECTION);
			
			// Wurde Wochenübersicht 'von' ausgewählt?
			if (_cbWeekDateFrom.isSelected())
				_data.setPreference(ReportWeekData.DRAW_DATE_FROM, 1);
			else
				_data.removePreference(ReportWeekData.DRAW_DATE_FROM);
			
			// Wurde Wochenübersicht 'bis' ausgewählt?
			if (_cbWeekDateTo.isSelected())
				_data.setPreference(ReportWeekData.DRAW_DATE_TO, 1);
			else
				_data.removePreference(ReportWeekData.DRAW_DATE_TO);
			
			// Dialog ausblenden
			setVisible(false);
		}
	}

	/**
	 * Gibt die Einstellungen zurück.
	 * 
	 * return Gemachte Einstellungen
	 */
	public ReportPreferencesData getData() {
		return _data;
	}

	/**
	 * Reagiert darauf, wenn eine CheckBox aus- oder abgewählt wurde.
	 * 
	 * @param ie Event-Daten
	 */
	@Override
	public void itemStateChanged(ItemEvent ie) {
		// CheckBox
		if (ie.getSource() instanceof JCheckBox) {
			// Wochenübersicht 'von'
			if ((ie.getSource() == _cbWeekDateFrom) && 
					_cbWeekDateFrom.isSelected())
				_cbWeekDateTo.setSelected(true);
			
			// Wochenüberischt 'bis'
			if ((ie.getSource() == _cbWeekDateTo) && 
					_cbWeekDateTo.isSelected())
				_cbWeekDateFrom.setSelected(true);
		}
	}
}
