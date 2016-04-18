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

import datas.ReportData;
import dialogs.DlgReport;
import elements.ReportGraphic;

import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;

/**
 * Zeigt die einzelnen Reports an.
 * 
 * @author René Majewski
 */
public class WndReports extends WndInternalFrame {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8914036564878348162L;
	
	/**
	 * Speichert die Tabelle
	 */
	private JTable _table;
	
	/**
	 * Speichert die Grafische Darstellung
	 */
	private ReportGraphic _report;
	
	/**
	 * Initalisiert das Fenster
	 * 
	 * @param report Welcher Report soll erstellt werden?
	 */
	public WndReports(int report) {
		// Klasse initalisieren
		super();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tabellenansicht", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		_table = new JTable();
		scrollPane.add(_table);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Grafische Ansicht", null, panel_1, null);
		
		_report = new ReportGraphic();
		panel_1.add(_report);
		
		// Einstellungen aufrufen
		DlgReport dlg = new DlgReport(report);
		ReportData data = dlg.getData();
		
		// Überprüfen ob ob der Report angezeigt werden soll
		if (data.getFinished() == DlgReport.CREATE) {
			// Namen einstellen
			switch (data.getType()) {
				case ReportData.TYPE_MONTH:
					setTitle("Monatsübersicht");
					break;
					
				case ReportData.TYPE_WEEK:
					setTitle("Wocheübersicht");
					break;
					
				case ReportData.TYPE_YEAR:
					setTitle("Jahresübersicht");
			}
			
			// Dialog anzeigen
			setVisible(true);
		}
	}
}
