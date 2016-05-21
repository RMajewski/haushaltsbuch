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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import haushaltsbuch.datas.ReportCategoryData;
import haushaltsbuch.datas.ReportData;
import haushaltsbuch.datas.ReportMonthData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportSectionData;
import haushaltsbuch.datas.ReportWeekData;
import haushaltsbuch.datas.ReportYearData;
import haushaltsbuch.dialogs.DlgReport;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.ReportGraphic;
import haushaltsbuch.helper.HelperPrint;
import haushaltsbuch.tables.models.ReportCategoryModel;
import haushaltsbuch.tables.models.ReportModel;
import haushaltsbuch.tables.models.ReportMonthModel;
import haushaltsbuch.tables.models.ReportSectionModel;
import haushaltsbuch.tables.models.ReportWeekModel;
import haushaltsbuch.tables.models.ReportYearModel;

/**
 * Zeigt die einzelnen Reports an.
 * 
 * In der Version 0.2 wird das Drucken der Reports unterstützt.
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.2
 */
public class WndReports extends WndInternalFrame
	implements ActionListener, ChangeListener, Printable {

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
	 * Speichert das Fenster, dass dieses Fenster aufgeruefen hat.
	 */
	private Window _owner;
	
	/**
	 * Speichert die TabbedPane
	 */
	private JTabbedPane _tabbedPane;
	
	/**
	 * Initalisiert das Fenster
	 * 
	 * @param desktop Desktop des Hauptfensters
	 * 
	 * @param report Welcher Report soll erstellt werden?
	 * 
	 * @param owner Fenster, dass dieses Unterfenster erzeugt hat.
	 */
	public WndReports(Desktop desktop, int report, Window owner) {
		// Klasse initalisieren
		super(desktop);
		_owner = owner;
		setEnablePdfReport(true);
		
		// Größe einstellen
		setSize(1000, 700);
		
		// Einstellungen erzeugen
		_preference = new ReportPreferencesData(report, 0, -1, 0);
		
		// Design
		_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		_tabbedPane.addChangeListener(this);
		getContentPane().add(_tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		_tabbedPane.addTab("Tabellenansicht", null, panel, null);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		_table = new JTable();
		panel.add(new JScrollPane(_table));
		
		JPanel panel_1 = new JPanel();
		_tabbedPane.addTab("Grafische Ansicht", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
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
		
		// Drucken wird unterstützt
		setEnablePrint(true);
		
		// Einstellungen aufrufen
		createDlgReport();
	}
	
	/**
	 * Ermittelt die Einstellungen
	 */
	public void createDlgReport() {
		// Einstellungen aufrufen
		DlgReport dlg = new DlgReport(_preference, _owner);
		_preference = dlg.getData();
		
		// Überprüfen ob ob der Report angezeigt werden soll
		if (_preference.getFinished() == DlgReport.CREATE) {
			// Namen einstellen
			switch (_preference.getType()) {
				case ReportPreferencesData.TYPE_WEEK:
					setTitle("Wochenübersicht für das Jahr " + 
							_preference.getYear());
					
					// Tabellen-Model einstellen
					ReportWeekData dataWeek = new ReportWeekData(_preference);
					_table.setModel(new ReportWeekModel(dataWeek));
					
					// Spalten-Beschreibungen
					dataWeek.setColumnHeader(_table.getColumnModel());
					break;
					
				case ReportPreferencesData.TYPE_MONTH:
					ReportYearData ryd = new ReportYearData(_preference);
					setTitle("Monatsübersicht für den Monat " + 
							ryd.getMonthName(_preference.getMonth()) + 
							" im Jahr " + _preference.getYear());
					
					// Tabellen-Model einstellen
					ReportMonthData dataMonth = 
							new ReportMonthData(_preference);
					_table.setModel(new ReportMonthModel(dataMonth));
					
					// Spalten-Beschreibungen
					dataMonth.setColumnHeader(_table.getColumnModel());
					break;
					
				case ReportPreferencesData.TYPE_YEAR:
					setTitle("Übersicht für das Jahr " + _preference.getYear());
					
					// Tabellen-Modell initalisieren
					ReportYearData dataYear = new ReportYearData(_preference);
					_table.setModel(new ReportYearModel(dataYear));
					
					// Spalten-Beschreibungen
					dataYear.setColumnHeader(_table.getColumnModel());
					break;
					
				case ReportPreferencesData.TYPE_CATEGORY:
					setTitle("Übersicht über die Kategorien");
					
					// Tabellen-Modell initalisieren
					ReportCategoryData dataCategory = new ReportCategoryData(
							_preference);
					_table.setModel(new ReportCategoryModel(dataCategory));
					
					// Spalten-Beschreibungen
					dataCategory.setColumnHeader(_table.getColumnModel());
					break;
					
				case ReportPreferencesData.TYPE_SECTION:
					setTitle("Übersicht über die Geschäfte");
					
					// Tabellen-Modell initalisieren
					ReportSectionData dataSection = new ReportSectionData(
							_preference);
					_table.setModel(new ReportSectionModel(dataSection));
					
					// Spalten-Beschreibungen
					dataSection.setColumnHeader(_table.getColumnModel());
					break;
			}
			
			// Muss das Diagramm neugezeichnet werden?
			if (_tabbedPane.getSelectedIndex() == 1) {
				stateChanged(new ChangeEvent(_tabbedPane));
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

	/**
	 * Reagiert darauf, wenn eine neue Registerkarte ausgewählt wird.
	 * 
	 * @param ce Event-Daten
	 */
	@Override
	public void stateChanged(ChangeEvent ce) {
		switch (((JTabbedPane)ce.getSource()).getSelectedIndex()) {
			// Informationen in der Tabelle anzeigen
		
			// Diagramm anzeigen
			case 1:
				// Diagramm vorbereiten
				_report.setData(((ReportModel)_table.getModel()).getData());
				_report.setDrawXLegend(true);
				_report.setYLegend("Euro");
				_report.setDrawYLegend(true);
				_report.setDrawReferenceLines(true);
				
				// Beschriftung X-Achse
				for (int i = 0; i < _table.getModel().getRowCount(); i++)
					_report.addXHeader(String.valueOf(
							_table.getModel().getValueAt(i, 0)));
				
				// Überschrift der X-Achse setzen
				switch (_preference.getType()) {
					// Wochenübersicht
					case ReportPreferencesData.TYPE_WEEK:
						_report.setXLegend("Wochen");
						break;
						
					// Monatsübersicht
					case ReportPreferencesData.TYPE_MONTH:
						_report.setXLegend("Tage");
						break;
						
					// Jahresübersicht
					case ReportPreferencesData.TYPE_YEAR:
						_report.setXLegend("Monate");
						break;
						
					// Übersicht Kategorien
					case ReportPreferencesData.TYPE_CATEGORY:
						_report.setXLegend("Kategorien");
						break;
						
					// Übersicht Geschäfte
					case ReportPreferencesData.TYPE_SECTION:
						_report.setXLegend("Geschäfte");
				}
				
				// Diagramm neu zeichnen
				_report.repaint();
				break;
		}
	}
	
	/**
	 * Gibt die gespeicherten Daten zurück.
	 * 
	 * @return Gespeicherte Daten
	 */
	public ReportData getReportData() {
		return ((ReportModel)_table.getModel()).getData();
	}

	/**
	 * Druck die Tabelle aus.
	 * 
	 * @param g Grafik-Kontekt des Druckers
	 * 
	 * @param pf Seiten-Einstellungen
	 * 
	 * @param page Index der Seite
	 * 
	 * @return Gibt an, ob die Seite existiert oder nicht.
	 */
	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		int pageCount = HelperPrint.calcPageCount(_table.getRowCount(), 
				(int)pf.getImageableHeight(), g);
		int count = HelperPrint.calcRecordPerPage((int)pf.getImageableHeight(),
				g);
		
		// Unterstützt in Version 0.2 nur Wochenübersicht, Monatsübersicht und
		// Jahresübersicht
		if (_preference.getType() > ReportPreferencesData.TYPE_YEAR)
			return Printable.NO_SUCH_PAGE;
		
		// Überprüft, ob die Seite noch gedruckt werden kann oder nicht.
		if (page > (pageCount - 1))
			return Printable.NO_SUCH_PAGE;
		
		// Überschriften
		int widthColumn1 = HelperPrint.calcColumnWidth(10, g);
		int widthColumn2 = HelperPrint.calcColumnWidth(10, g);
		int widthColumn3 = HelperPrint.calcColumnWidth(10, g);
		int widthColumn4 = HelperPrint.calcColumnWidth(10, g);
		int x1 = (int)pf.getImageableX();
		int x2 = x1 + widthColumn1;
		int x3 = x2 + widthColumn2;
		int x4 = x3 + widthColumn3;
		int id1 = 0;
		int id2 = 1;
		int id3 = 2;
		int id4 = 3;
		int height = HelperPrint.calcRowHeight(g);
		g.setFont(HelperPrint.standardBoldFont());
		HelperPrint.drawCell(x1, (int)pf.getImageableY(), widthColumn1, height,
				String.valueOf(_table.getColumnModel().getColumn(0)
						.getHeaderValue()), g);
		HelperPrint.drawCell(x2, (int)pf.getImageableY(), widthColumn2, height,
				"Einnahmen", g);
		HelperPrint.drawCell(x3, (int)pf.getImageableY(), widthColumn3, height,
				"Ausgaben", g);
		HelperPrint.drawCell(x4, (int)pf.getImageableY(), widthColumn4, height,
				"Differenz", g);
		
		// Spalten in der Tabelle einstellen
		if (_preference.getType() == ReportPreferencesData.TYPE_WEEK) {
			String from = String.valueOf(_preference.getPreference(
					ReportWeekData.DRAW_DATE_FROM));
			String to = String.valueOf(_preference.getPreference(
					ReportWeekData.DRAW_DATE_TO));
			
			if ((from != null) && !from.isEmpty() && from.equals("1")) {
				id2++;
				id3++;
				id4++;
			}
			
			if ((to != null) && !to.isEmpty() && to.equals("1")) {
				id2++;
				id3++;
				id4++;
			}
		}
		
		// Daten ausgeben
		g.setFont(HelperPrint.standardFont());
		int max = 0;
		if ((count * (page + 1)) < _table.getRowCount())
			max = (count * (page + 1));
		else
			max = _table.getRowCount();
		
		for (int index = (count * page); index < max; index++)
		{
			int y = (int)pf.getImageableY() + 
					((index + 1 - (count * page)) * height);
			
			HelperPrint.drawCell(x1, y, widthColumn1, height, 
					String.valueOf(_table.getValueAt(index, id1)), g);
			HelperPrint.drawCell(x2, y, widthColumn2, height, 
					String.valueOf(_table.getValueAt(index, id2)), g);
			HelperPrint.drawCell(x3, y, widthColumn3, height, 
					String.valueOf(_table.getValueAt(index, id3)), g);
			HelperPrint.drawCell(x4, y, widthColumn4, height, 
					String.valueOf(_table.getValueAt(index, id4)), g);
		}
		
		// Seite kann gedruckt werden
		return Printable.PAGE_EXISTS;
	}
}
