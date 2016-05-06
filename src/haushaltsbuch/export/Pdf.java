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

package haushaltsbuch.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;

import haushaltsbuch.datas.ReportData;
import haushaltsbuch.datas.ReportMonthData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportWeekData;
import haushaltsbuch.datas.ReportYearData;
import haushaltsbuch.dialogs.DlgExportPdf;
import haushaltsbuch.windows.WndMain;

/**
 * Exportiert die Daten aus dem aktuellen Report in eine PDF-Datei.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class Pdf extends Export {
	/**
	 * Speichert die Daten des Reports
	 */
	private ReportData _data;

	/**
	 * Speichert die Standard Schriftart
	 */
	private Font _font;
	
	/**
	 * Speichert die Schriftart für eine positive Differenz.
	 */
	private Font _fontDeviationPlus;
	
	/**
	 * Speichert die Schriftart für eine negative Differenz.
	 */
	private Font _fontDeviationMinus;
	
	/**
	 * Speichert fette standard Schriftart
	 */
	private Font _fontBold;
	
	/**
	 * Speichert die Schriftart für die Überschrift.
	 */
	private Font _fontHeader;
	
	/**
	 * Initalisiert diese Klasse
	 */
	public Pdf(JFrame owner) {
		// Dialog speichern
		super(new DlgExportPdf(owner));
	}
	
	/**
	 * Speichert die Daten des Reports.
	 * 
	 * @param report Daten des Reports
	 */
	public void setReportData(ReportData data) {
		_data = data;
	}
	
	/**
	 * Gibt die Daten des Reportes zurück
	 * 
	 * @return Daten des Reportes
	 */
	public ReportData getReportData() {
		return _data;
	}

	/**
	 * Erstellt die PDF-Datei
	 */
	@Override
	protected void export(File file) {
		// Initalisiert die Schriftarten
		_font = new Font(FontFamily.HELVETICA, 12, Font.NORMAL,
				BaseColor.BLACK);
		
		_fontBold = new Font(FontFamily.HELVETICA, 12, Font.BOLD,
				BaseColor.BLACK);
		
		_fontHeader = new Font(FontFamily.HELVETICA, 20, Font.BOLD,
				BaseColor.BLACK);
		
		_fontDeviationPlus = new Font(FontFamily.HELVETICA, 12, Font.NORMAL,
				BaseColor.GREEN);
		
		_fontDeviationMinus = new Font(FontFamily.HELVETICA, 12, Font.NORMAL,
				BaseColor.RED);
		
		try {
			// PDF-Datei erzeugen und öffnen
			Document doc = new Document();
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();
			
			// Meta-Daten erzeugen
			createMetaData(doc);
			
			// Überprüfen, ob Tabelle eingefügt werden soll
			int chapterCount = 1;
			if (_preference.get(DlgExportPdf.EXPORT_TABLE) != null) {
				// Überschrift für Tabelle
				Chunk chTitle = new Chunk(title(false), _fontHeader);
				Paragraph title = new Paragraph(chTitle);
				Chapter chapter = new Chapter(title, chapterCount++);
				
				// Tabelle einfügen
				insertTable(chapter);
				
				// ins Dokument einfügen
				doc.add(chapter);
			}
			
			// Überprüftn, ob das Balekndiagramm eingefügt werden soll
			if (_preference.get(DlgExportPdf.EXPORT_BAR_CHART) != null) {
				// Überprüfen, ob eine neue Seite eingefügt werden soll.
				if (_preference.get(DlgExportPdf.EXPORT_TABLE) != null)
					doc.newPage();
				
				// Seiten-Einstellungen
				doc.setPageSize(new Rectangle(doc.getPageSize().getHeight(),
						doc.getPageSize().getWidth()));
				
				// Überschrift für das Diagramm
				Chunk chTitle = new Chunk(title(true), _fontHeader);
				Paragraph title = new Paragraph(chTitle);
				Chapter chapter = new Chapter(title, chapterCount++);
				
				// Diagramm einfügen
				insertChart(chapter);
				
				// Ins Dokument einfügen
				doc.add(chapter);
			}
			
			// PDF-Datei schließen
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Erzeugt ein Diagramm
	 * 
	 * @param chap Kapitel, in das das Diagramm eingefügt werden soll.
	 */
	private void insertChart(Chapter chap) {
	}

	/**
	 * Erzeugt die Tabelle mit den Daten
	 * 
	 * @param chap Kapitel, in die die Tabelle eingefügt werden soll.
	 */
	private void insertTable(Chapter chap) {
		// Erzeugt die Tabelle
		PdfPTable table = new PdfPTable(4);
		
		// Erzeugt die Überschriften
		table.addCell(new Phrase(new Chunk(firstColumnHeader(), _fontBold)));
		table.addCell(new Phrase(new Chunk("Einnahmen", _fontBold)));
		table.addCell(new Phrase(new Chunk("Ausgaben", _fontBold)));
		table.addCell(new Phrase(new Chunk("Differenz", _fontBold)));
		table.setHeaderRows(1);
		
		// Zeilen
		for (int row = 0; row < _data.getRowCount(); row++) {
			table.addCell(new Phrase(new Chunk(firstColumn(row), _font)));
			table.addCell(new Phrase(new Chunk(
					new DecimalFormat("0.00").format(_data.incoming(row)),
					_font)));
			table.addCell(new Phrase(new Chunk(
					new DecimalFormat("0.00").format(_data.outgoing(row)),
					_font)));
			
			Font font = _font;
			if (_data.deviation(row) > 0.0)
				font = _fontDeviationPlus;
			else if(_data.deviation(row) < 0.0)
				font = _fontDeviationMinus;
			
			table.addCell(new Phrase(new Chunk(
					new DecimalFormat("0.00").format(_data.deviation(row)),
					font)));
		}
		
		// Tabelle in das Kapitel einfügen
		chap.add(table);
	}

	/**
	 * Ermittelt den Wert der ersten Spalte für die übergebene Zeile.
	 * 
	 * @param row Zeile, für die der Wert ermittelt werden soll.
	 * 
	 * @return Wert der Zelle.
	 */
	private String firstColumn(int row) {
		switch (_data.getPreferences().getType()) {
			case ReportPreferencesData.TYPE_WEEK:
				return ((ReportWeekData)_data).getWeekNumber(row);
				
			case ReportPreferencesData.TYPE_MONTH:
				return String.valueOf(((ReportMonthData)_data).getDay(row));
				
			case ReportPreferencesData.TYPE_YEAR:
				return ((ReportYearData)_data).getMonthName(row);
		}
		return new String();
	}

	/**
	 * Ermittelt die Überschrift für die 1. Spalte.
	 * 
	 * @return Überschrift für die 1. Spalte
	 */
	private String firstColumnHeader() {
		switch (_data.getPreferences().getType()) {
			case ReportPreferencesData.TYPE_WEEK:
				return "Woche";
				
			case ReportPreferencesData.TYPE_MONTH:
				return "Tag";
				
			case ReportPreferencesData.TYPE_YEAR:
				return "Monat";
		}
		
		return new String();
	}

	/**
	 * Generiert den Titel
	 * 
	 * @param chart Gibt an, ob "Diagramm" mit ausgegeben werden soll.
	 */
	private String title(boolean chart) {
		StringBuilder ret = new StringBuilder();
		
		switch (_data.getPreferences().getType()) {
			case ReportPreferencesData.TYPE_WEEK:
				ret.append("Wochenübersicht für das Jahr ");
				break;
				
			case ReportPreferencesData.TYPE_MONTH:
				ret.append("Übersicht über den Monat ");
				ret.append(String.valueOf(_data.getMonth()));
				ret.append(" aus dem Jahr ");
				break;
				
			case ReportPreferencesData.TYPE_YEAR:
				ret.append("Übersicht über das Jahr ");
				break;
		}
		
		ret.append(String.valueOf(_data.getYear()));
		
		if (chart)
			ret.append(" (Diagramm)");
		
		return ret.toString();
	}
	
	/**
	 * Erzeugt die Meta-Daten
	 * 
	 * @param doc PDF-Datei, in die die Meta-Daten geschrieben werden sollen.
	 */
	private void createMetaData(Document doc) {
		doc.addTitle(title(false));
		doc.addAuthor(WndMain.TITLE);
		doc.addCreator(WndMain.TITLE);
	}

}
