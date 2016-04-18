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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import datas.ReportPreferencesData;
import datas.ReportWeekData;
import dialogs.DlgReport;
import elements.ReportGraphic;
import tables.models.ReportWeekModel;

/**
 * Zeigt die einzelnen Reports an.
 * 
 * @author René Majewski
 */
public class WndReports extends WndInternalFrame implements ActionListener {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8914036564878348162L;
	
	/**
	 * ActionCommand für die Einstellungen
	 */
	public static final String SETS = "WndReportsSets";
	
	/**
	 * ActionCommand zum Beenden des Fensters
	 */
	public static final String CANCEL = "WndReportsCancel";
	
	/**
	 * Speichert die Tabelle
	 */
	private JTable _table;
	
	/**
	 * Speichert die Grafische Darstellung
	 */
	private ReportGraphic _report;
	
	/**
	 * Einstellungen für die Erzeugung des Reports
	 */
	private ReportPreferencesData _preference;
	
	/**
	 * Initalisiert das Fenster
	 * 
	 * @param report Welcher Report soll erstellt werden?
	 */
	public WndReports(int report) {
		// Klasse initalisieren
		super();
		
		// Größe einstellen
		setSize(1000, 700);
		
		// Einstellungen erzeugen
		_preference = new ReportPreferencesData(report, 0, 0, 0);
		
		// Design
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tabellenansicht", null, panel, null);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		_table = new JTable();
		panel.add(new JScrollPane(_table));
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Grafische Ansicht", null, panel_1, null);
		
		_report = new ReportGraphic();
		panel_1.add(_report);
		
		JPanel buttons = new JPanel();
		getContentPane().add(buttons, BorderLayout.SOUTH);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		
		JButton btnSet = new JButton("Einstellungen");
		btnSet.setMnemonic('E');
		btnSet.addActionListener(this);
		btnSet.setActionCommand(SETS);
		buttons.add(btnSet);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		buttons.add(horizontalGlue);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.setMnemonic('A');
		btnCancel.addActionListener(this);
		btnCancel.setActionCommand(CANCEL);
		buttons.add(btnCancel);
		
		// Einstellungen aufrufen
		createDlgReport();
	}
	
	/**
	 * Ermittelt die Einstellungen
	 */
	public void createDlgReport() {
		// Einstellungen aufrufen
		DlgReport dlg = new DlgReport(_preference.getType());
		_preference = dlg.getData();
		
		// Überprüfen ob ob der Report angezeigt werden soll
		if (_preference.getFinished() == DlgReport.CREATE) {
			// Namen einstellen
			switch (_preference.getType()) {
				case ReportPreferencesData.TYPE_WEEK:
					setTitle("Monatsübersicht");
					
					// Tabellen-Model einstellen
					_table.setModel(
							new ReportWeekModel(
									new ReportWeekData(_preference)));
					
					break;
					
				case ReportPreferencesData.TYPE_MONTH:
					setTitle("Wocheübersicht");
					break;
					
				case ReportPreferencesData.TYPE_YEAR:
					setTitle("Jahresübersicht");
			}
			
			// Dialog anzeigen
			setVisible(true);
		}
	}

	/**
	 * Reagiert auf die einzelnen Buttons.
	 * 
	 * @param ae Daten dieses Events
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Welcher Button wurde geddrückt?
		if (ae.getActionCommand().equals(CANCEL)) {
			setVisible(false);
		} else if (ae.getActionCommand().equals(SETS))
			createDlgReport();
	}
}
