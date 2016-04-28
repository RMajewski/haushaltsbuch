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

package tests.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import haushaltsbuch.helper.HelperCalendar;

public class HtmlOut {
	/**
	 * Speichert die Ausgabe-Datei
	 */
	private String _htmlFile;
	
	/**
	 * Speichert den Writer für die Datei
	 */
	private FileWriter _writer;
	
	/**
	 * Speichert den BufferedWriter
	 */
	private BufferedWriter _bw;
	
	/**
	 * Es sollen die Bibliotheken für die GUI-Tests ausgegeben werden.
	 */
	private static final short BIB_GUI = 1;
	
	/**
	 * Es sollen die Bibliotheken für die junit-Tests ausgegeben werden.
	 */
	private static final short BIB_JUNIT = 2;
	
	/**
	 * Es sollen die Bibliotheken für die Fit-Tests ausgegeben werden.
	 */
	private static final short BIB_FIT = 3;
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @param file Ausgabe-Datei
	 * 
	 * @throws IOException 
	 */
	public HtmlOut(String file) throws IOException {
		// Daten speichern
		_htmlFile = file;
		
		// Writer zum ausgeben öffnen.
		_writer = new FileWriter(new File(_htmlFile));
		_bw = new BufferedWriter(_writer);
	}
	
	/**
	 * Gibt die verwendeten Bibliotheken aus.
	 * 
	 * @param bib Welche Bibliotheken sollen ausgegeben werden?
	 * 
	 * @throws IOException
	 */
	private void bibs(short bib) throws IOException {
		_bw.write("\t\t<p>Verwendete Bibliotheken:</p>"); _bw.newLine();
		_bw.write("\t\t\t<ul>"); _bw.newLine();
		
		// Jemmy
		if ((bib == BIB_GUI) || (bib == BIB_FIT)) {
			_bw.write("\t\t\t\t<li>Jemmy Version ");
			_bw.write(org.netbeans.jemmy.JemmyProperties.getFullVersion());
			_bw.write("</li>");
			_bw.newLine();
		}
		
		// junit
		if (bib == BIB_JUNIT) {
			_bw.write("\t\t\t\t<li>junit 4</li>");
			_bw.newLine();
		}
		
		// mockito
		if (bib == BIB_JUNIT) {
			_bw.write("\t\t\t\t<li>mockito 1.10.19</li>");
			_bw.newLine();
		}
		
		// powermock
		if (bib == BIB_JUNIT) {
			_bw.write("\t\t\t\t<li>powermock 1.6.3</li>");
			_bw.newLine();
		}
		
		// Fit
		if (bib == BIB_FIT) {
			_bw.write("\t\t\t\t<li>fit 1.1</li>");
			_bw.newLine();
		}
		
		_bw.write("\t\t\t</ul>"); _bw.newLine();
	}
	
	/**
	 * Erstellt die Stylesheets
	 * 
	 * @throws IOEXception
	 */
	private void stylesheets() throws IOException {
		_bw.write("\t\t<style>"); _bw.newLine();
		_bw.write(".pass {background-color: #0F0;}");
		_bw.write(".wrong {background-color: #F00;}");
		_bw.write(".ignore {background-color: #AAAAAA}");
		_bw.write(".exception {background-color: #FF0}");
		_bw.write("\t\t</style>"); _bw.newLine();
	}
	
	/**
	 * Gibt den HTML-Kopf aus
	 * 
	 * @throws IOException 
	 */
	public void htmlHead() throws IOException {
		String date = HelperCalendar.dateToString(new Date().getTime());
		
		_bw.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 "
				+ "Transitional//EN\" \"http://www.w3.org/TR/html4/"
				+ "transitional.dtd\">");
		_bw.newLine();
		_bw.write("<html>"); _bw.newLine();
		_bw.write("\t<head>"); _bw.newLine();
		_bw.write("\t\t<title>Ergebnisse der Tests vom ");
		_bw.write(date);
		_bw.write("</title>"); _bw.newLine();
		_bw.write("\t\t<meta http-equiv=\"content-type\" "
				+ "content=\"text/html; charset=UTF-8\">");
		_bw.newLine();
		stylesheets();
		_bw.write("\t</head>"); _bw.newLine();
		_bw.write("\t<body>"); _bw.newLine();
		_bw.write("\t\t<h1>Ergebniss der Tests vom ");
		_bw.write(date);
		_bw.write("</h1>"); _bw.newLine();
		_bw.write("\t\t<p>Dies ist eine automatisch erzeugte HTML-Datei mit ");
		_bw.write("dem Ergebnissen der ausgeführten Tests.</p>");
		_bw.newLine();
	}
	
	/**
	 * Gibt den HTML-Fuß aus
	 * 
	 * @throws IOException 
	 */
	public void htmlEnd() throws IOException {
		_bw.write("\t</body>");
		_bw.newLine();
		_bw.write("</html>");
		_bw.newLine();
		_bw.close();
	}
	
	/**
	 * Gibt die Überschrift für die GUI-Tests aus
	 * 
	 * @throws IOException 
	 */
	public void guiHead() throws IOException {
		_bw.write("\t\t<h2>GUI-Tests</h2>");
		_bw.newLine();
		_bw.write("\t\t<p>Die GUI-Tests beinhalten Test die die Oberfläche ");
		_bw.write("testen ausgeführt mit Jemmy<p>");
		_bw.newLine();
		bibs(BIB_GUI);
	}
	
	/**
	 * Gibt die Überschrift für die junit-Tests aus
	 * 
	 * @throws IOException
	 */
	public void junitHead() throws IOException {
		_bw.write("\t\t<hr>"); _bw.newLine();
		_bw.write("\t\t<h2>junit-Tests</h2>");
		_bw.newLine();
		_bw.write("\t\t<p>Die junit-Tests testen einzelne Klassen. Diese ");
		_bw.write("Klassen sind in den Tests meist isoliert.<p>");
		_bw.newLine();
		bibs(BIB_JUNIT);
	}
	
	/**
	 * Gibt die Überschrift für die Fit-Tests aus
	 * 
	 * @throws IOException
	 */
	public void fitHead() throws IOException {
		_bw.write("\t\t<hr>"); _bw.newLine();
		_bw.write("\t\t<h2>Fit-Tests</h2>");
		_bw.newLine();
		_bw.write("\t\t<p>Die Fit-Tests testen die Funktionalität der ");
		_bw.write("einzelnen Fenster. Dazu wurde die Fenster teilweise in");
		_bw.write("einer Testumgebung geöffnet.<p>");
		_bw.newLine();
		bibs(BIB_FIT);
	}

	/**
	 * Gibt den Beginn einer Tabelle aus. In der Tabelle stehen die Ergebnisse
	 * der Test der Suites.
	 * 
	 * @param name Name der Test-Suite
	 * 
	 * @param pack Name des Package
	 * 
	 * @param right Anzahl richtiger Tests
	 * 
	 * @param wrong Anzahl falscher Tests
	 * 
	 * @param ignore Anzahl ignorierter Tests
	 * 
	 * @param exception Anzahl Fehlerhafter Tests
	 */
	public void suiteStart(String name, String pack, int right, int wrong, 
			int ignore, int exception) throws IOException {
		
		String rightColspan = new String();
		if (wrong == -1)
			rightColspan = " colspan=\"2\"";
		
		String exceptionColspan = new String();
		if (ignore == -1)
			exceptionColspan = " colspan=\"2\"";
		
		_bw.write("\t\t<table width=\"100%\" border=\"1\">"); _bw.newLine();
		_bw.write("\t\t\t<tr>"); _bw.newLine();
		
		_bw.write("\t\t\t\t<td>");
		_bw.write(name);
		_bw.write("</td>");
		_bw.newLine();
		
		_bw.write("\t\t\t\t<td class=\"pass\"");_bw.write(rightColspan);
		_bw.write(">");
		_bw.write(String.valueOf(right));
		_bw.write("</td>");
		_bw.newLine();
		
		if (wrong > -1) {		
			_bw.write("\t\t\t\t<td class=\"wrong\">");
			_bw.write(String.valueOf(wrong));
			_bw.write("</td>");
			_bw.newLine();
		}
		
		if (ignore > -1) {
			_bw.write("\t\t\t\t<td class=\"ignore\">");
			_bw.write(String.valueOf(ignore));
			_bw.write("</td>");
			_bw.newLine();
		}
		
		_bw.write("\t\t\t\t<td class=\"exception\"");
		_bw.write(exceptionColspan);
		_bw.write(">");
		_bw.write(String.valueOf(exception));
		_bw.write("</td>"); _bw.newLine();

		_bw.write("\t\t\t</tr>"); _bw.newLine();

		_bw.write("\t\t\t<tr>"); _bw.newLine();
		
		_bw.write("\t\t\t\t<td colspan=\"5\">Package: ");
		_bw.write(pack);
		_bw.write("</td>");
		_bw.newLine();

		_bw.write("\t\t\t</tr>"); _bw.newLine();
	}
	
	/**
	 * Beendet die Tabelle für eine Test-Suite
	 */
	public void suiteEnd() throws IOException {
		_bw.write("\t\t</table>");
		_bw.newLine();
	}
}
