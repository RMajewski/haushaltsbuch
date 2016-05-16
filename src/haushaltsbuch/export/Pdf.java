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
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import haushaltsbuch.datas.ReportData;
import haushaltsbuch.datas.ReportMonthData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.datas.ReportWeekData;
import haushaltsbuch.datas.ReportYearData;
import haushaltsbuch.dialogs.DlgExportPdf;
import haushaltsbuch.helper.HelperNumbersOut;
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
	 * Speichert den Namen der Schriftdatei
	 */
	private static final String _fontName = "fonts/helvari.ttf";
	
	/**
	 * Speichert den Namen der Schriftdatei für fett geschriebenes
	 */
	private static final String _fontNameBold = "fonts/helvaribold.ttf";
	
	/**
	 * Speichert den Minimalwert für Y-Achse
	 */
	private double _minY;
	
	/**
	 * Speichert den Maximalwert für die Y-Achse
	 */
	private double _maxY;
	
	/**
	 * Initalisiert diese Klasse
	 */
	public Pdf(JFrame owner) {
		// Dialog speichern
		super(new DlgExportPdf(owner));
		_minY = 0.0;
		_maxY = 0.0;
	}
	
	/**
	 * Speichert die Daten des Reports.
	 * 
	 * @param data Daten des Reports
	 */
	public void setReportData(ReportData data) {
		_data = data;
		
		_minY = 0.0;
		_maxY = 0.0;
		
		// Maximal- und Minimal-Wert für Y-Achse bestimmen
		for (int i = 0; i < _data.getRowCount(); i++) {
			if (_data.incoming(i) < _minY)
				_minY = _data.incoming(i);
			
			else if (_data.incoming(i) > _maxY)
				_maxY = _data.incoming(i);
			
			if (_data.outgoing(i) < _minY)
				_minY = _data.outgoing(i);
			
			else if (_data.outgoing(i) > _maxY)
				_maxY = _data.outgoing(i);
			
			if (_data.deviation(i) < _minY)
				_minY = _data.deviation(i);
			
			else if (_data.deviation(i) > _maxY)
				_maxY = _data.deviation(i);
		}
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
		// Schriftart hinzufügen
		FontFactory.register(_fontName);
		FontFactory.register(_fontNameBold);
		
		// Initalisiert die Schriftarten
		_font = FontFactory.getFont(_fontName, BaseFont.CP1250, 
				BaseFont.EMBEDDED, 12, Font.NORMAL, BaseColor.BLACK);
		
		_fontBold = FontFactory.getFont(_fontNameBold, BaseFont.CP1250, 
				BaseFont.EMBEDDED, 12, Font.BOLD, BaseColor.BLACK);
		
		_fontHeader = FontFactory.getFont(_fontNameBold, BaseFont.CP1250, 
				BaseFont.EMBEDDED, 20, Font.BOLD, BaseColor.BLACK);
		
		_fontDeviationPlus = FontFactory.getFont(_fontName, BaseFont.CP1250, 
				BaseFont.EMBEDDED, 12, Font.NORMAL, BaseColor.GREEN);
		
		_fontDeviationMinus = FontFactory.getFont(_fontName,
				BaseFont.CP1250, BaseFont.EMBEDDED, 12, Font.NORMAL,
				BaseColor.RED);
		
		try {
			// PDF-Datei erzeugen und öffnen
			Document doc = new Document();
			PdfWriter writer = PdfWriter.getInstance(doc,
					new FileOutputStream(file));
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
				
				// Ins Dokument einfügen
				doc.add(chapter);
				
				// Diagramm einfügen
				insertChart(writer, doc);
			}
			
			// PDF-Datei schließen
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Erzeugt ein Diagramm
	 * 
	 * @param writer Zugriff auf den PDF-Stream.
	 * 
	 * @param doc Zugriff auf das PDF-Dokument
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	private void insertChart(PdfWriter writer, Document doc) throws DocumentException, IOException {
		float width = doc.getPageSize().getWidth() - doc.leftMargin() -
				doc.rightMargin() - 100;
		float height = doc.getPageSize().getHeight() - doc.topMargin() -
				doc.bottomMargin() - 150;
		float startX = doc.leftMargin();
		float startY = doc.bottomMargin();
		
		// Zum Zeichnen vorbereiten
		PdfContentByte canvas = writer.getDirectContent();
		
		// Farben setzen
		BaseColor repLines = BaseColor.LIGHT_GRAY;
		BaseColor normal = BaseColor.BLACK;
		BaseColor in = BaseColor.GREEN;
		BaseColor out = BaseColor.RED;
		BaseColor dev = BaseColor.YELLOW;
		
		// Hilfslinien zeichnen
		float w = width / _data.getRowCount();
		canvas.setColorStroke(repLines);
		
		// X-Achse
		for (int row = 1; row < _data.getRowCount(); row ++) {
			canvas.moveTo(startX + 100 + w * row, startY + 50);
			canvas.lineTo(startX + 100 + w * row, startY + height + 50);
		}
		
		// Y-Achse
		float h = height / 10;
		for (int col = 1; col < 10; col++) {
			canvas.moveTo(startX + 100, startY + 50 + col * h);
			canvas.lineTo(startX + width + 100, startY + 50 + col * h);
		}
		
		canvas.closePathStroke();
		
		// Legende X-Achse
		String legend = firstColumnHeader();
		canvas.beginText();
		canvas.setFontAndSize(_fontBold.getBaseFont(), 12);
		canvas.moveText(startX + 100 + width / 2, startY);
		canvas.showText(legend);
		canvas.endText();
		
		// Beschriftung Abschnitte
		boolean top = false;
		for (int row = 0; row < _data.getRowCount(); row++) {
			float y = startY + 20;
			if (top)
				y += 12;
			top = !top;
			
			canvas.beginText();
			canvas.setFontAndSize(_font.getBaseFont(), 12);
			canvas.moveText(startX + 100 + row * w, y);
			canvas.showText(firstColumn(row));
			canvas.endText();
		}
		
		// Legende Y-Achse
		canvas.beginText();
		canvas.setFontAndSize(_fontBold.getBaseFont(), 12);
		canvas.moveText(startX, startY + 50 + height / 2);
		canvas.setTextMatrix(0, 1, -1, 0, startX, startY + 50 + height / 2);
		canvas.showText("Euro");
		canvas.endText();
		
		// Zeichnen der Mittellinie und Ausgabe der Werte
		float zero = startY + 50;
		if (_minY < 0.0) {
			zero = startY + 50 + height / 2;
			canvas.setColorStroke(normal);
			canvas.moveTo(startX + 100, zero);
			canvas.lineTo(startX + 100 + width, zero);
			canvas.closePathStroke();
			
			canvas.beginText();
			canvas.setFontAndSize(_font.getBaseFont(), 12);
			canvas.moveText(startX + 20, startY + 50 + height / 2);
			canvas.showText("0,00");
			canvas.endText();
			
			canvas.beginText();
			canvas.setFontAndSize(_font.getBaseFont(), 12);
			canvas.moveText(startX + 20, startY + 50 + height);
			canvas.showText(HelperNumbersOut.sum(_maxY));
			canvas.endText();
			
			canvas.beginText();
			canvas.setFontAndSize(_font.getBaseFont(), 12);
			canvas.moveText(startX + 20, startY + 50);
			canvas.showText(HelperNumbersOut.sum(_minY));
			canvas.endText();
		}
		
		// Balken zeichnen
		float barWidth = w / 3;
		for (int row = 0; row < _data.getRowCount(); row++) {
			// Einnahmen
			if (_data.incoming(row) > 0.0) {
				float bh = (float) ((_data.incoming(row) / _maxY) *
						(height / 2));
				canvas.setColorFill(in);
				canvas.rectangle(startX + 100 + w * row,  zero, barWidth, bh);
				canvas.fill();
			}
			
			// Ausgaben
			if (_data.outgoing(row) > 0.0) {
				float bh = (float) ((_data.outgoing(row) / _maxY) *
						(height / 2));
				canvas.setColorFill(out);
				canvas.rectangle(startX + 100 + w * row + barWidth,  zero,
						barWidth, bh);
				canvas.fill();
			}

			// Differenz
			if (_data.deviation(row) > 0.0) {
				float bh = (float) ((_data.deviation(row) / _maxY) *
						(height / 2));
				canvas.setColorFill(dev);
				canvas.rectangle(startX + 100 + w * row + (2 * barWidth), zero,
						barWidth, bh);
				canvas.fill();
			} else if (_data.deviation(row) < 0.0) {
				float bh = (float) ((_data.deviation(row) / _minY) *
						(height / 2));
				canvas.setColorFill(dev);
				canvas.rectangle(startX + 100 + w * row + (2 * barWidth),
						startY + 50, barWidth, bh);
				canvas.fill();
			}
		}
		
		// Rahmen zeichnen
		canvas.setColorStroke(normal);
		canvas.rectangle(startX + 100, startY + 50, width, height);
		canvas.closePathStroke();
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
					HelperNumbersOut.sum(_data.incoming(row)),
					_font)));
			table.addCell(new Phrase(new Chunk(
					HelperNumbersOut.sum(_data.outgoing(row)),
					_font)));
			
			Font font = _font;
			if (_data.deviation(row) > 0.0)
				font = _fontDeviationPlus;
			else if(_data.deviation(row) < 0.0)
				font = _fontDeviationMinus;
			
			table.addCell(new Phrase(new Chunk(
					HelperNumbersOut.sum(_data.deviation(row)),
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
