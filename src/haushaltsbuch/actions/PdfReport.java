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

package haushaltsbuch.actions;

import java.awt.event.ActionEvent;

import javax.swing.filechooser.FileNameExtensionFilter;

import haushaltsbuch.elements.Desktop;
import haushaltsbuch.export.Pdf;
import haushaltsbuch.windows.internal.WndReports;

/**
 * Implementiert die Aktion, die ein PDF-Report erstellt.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class PdfReport extends Action {
	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -1900846348420549686L;
	
	/**
	 * Speichert das Kommando, um das Report-Fenster zu öffnen.
	 */
	public static final String COMMAND = "PdfReport";

	/**
	 * Initalisiert die Aktion.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public PdfReport(Desktop desktop) {
		super("pdf_small.png", "pdf_big.png", desktop);

		putValue(Action.NAME, "PDF-Report");
		putValue(Action.MNEMONIC_KEY, 2);
		putValue(Action.ACTION_COMMAND_KEY, COMMAND);
		putValue(Action.SHORT_DESCRIPTION,
				"Ruft Dialog zum Exportieren als PDF auf.");
	}
	
	/**
	 * Erzeugt das PDF für den Report.
	 * 
	 * @param e Daten des Eventes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Pdf pdf = new Pdf(_desktop.getMainWindow());
		pdf.setReportData(((WndReports)_frame).getReportData());
		pdf.execute("PDF speichern", 
				new FileNameExtensionFilter("PDF-Dateien pdf", "pdf"));
	}


}
