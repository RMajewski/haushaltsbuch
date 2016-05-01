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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	 * Speichert den HTML-Code für die Tests
	 */
	private StringBuilder _tests;
	
	/**
	 * Speichert die Aktuelle ID des Testes.
	 */
	private int _id;
	
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
		
		// Daten initalisieren
		_tests = new StringBuilder();
		_id = 1;
		
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
			_bw.write("\t\t\t\t<li>junit 4.12</li>");
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
	 * @throws IOException
	 */
	private void stylesheets() throws IOException {
		_bw.write("\t\t<style>"); _bw.newLine();
		_bw.write(".pass {background-color: #0F0;}"); _bw.newLine();
		_bw.write(".wrong {background-color: #F00;}"); _bw.newLine();
		_bw.write(".ignore {background-color: #AAAAAA;}"); _bw.newLine();
		_bw.write(".exception {background-color: #FF0;}"); _bw.newLine();
		_bw.write(".testoutInvisible {display: none;}"); _bw.newLine();
		_bw.write(".testoutVisible {display: block;}"); _bw.newLine();
		_bw.write(".right {float: right;}"); _bw.newLine();
		_bw.write("\t\t</style>"); _bw.newLine();
	}
	
	/**
	 * Erstellt den Java-Scriot
	 * 
	 * @throws IOException
	 */
	private void javaScript() throws IOException {
		_bw.write("\t\t<script type=\"text/javascript\">"); _bw.newLine();
		_bw.write("function toogleDisplayId(id) {"); _bw.newLine();
		
		_bw.write("if (window.document.getElementById(\"id_\" + id ).className == ");
		_bw.write("\"testoutInvisible\")"); _bw.newLine();
		_bw.write("window.document.getElementById(\"id_\" + id).className = \"testoutVisible\";");
		_bw.newLine();
		_bw.write("else");
		_bw.newLine();
		_bw.write("window.document.getElementById(\"id_\" + id).className = \"testoutInvisible\";");
		_bw.write("}"); _bw.newLine();
		_bw.write("\t\t</script>"); _bw.newLine();
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
		javaScript();
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
	 * 
	 * @param time Zeit, die die Suite insgesamt gebraucht hat.
	 */
	public void suiteHtml(String name, String pack, int right, int wrong, 
			int ignore, int exception, long time) throws IOException {
		
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
		
		_bw.write("\t\t\t\t<td");
		_bw.write(testClass(right, "pass"));
		_bw.write(rightColspan);
		_bw.write(">");
		_bw.write(String.valueOf(right));
		_bw.write("</td>");
		_bw.newLine();
		
		if (wrong > -1) {		
			_bw.write("\t\t\t\t<td");
			_bw.write(testClass(wrong, "wrong"));
			_bw.write(">");
			_bw.write(String.valueOf(wrong));
			_bw.write("</td>");
			_bw.newLine();
		}
		
		if (ignore > -1) {
			_bw.write("\t\t\t\t<td");
			_bw.write(testClass(ignore, "ignore"));
			_bw.write(">");
			_bw.write(String.valueOf(ignore));
			_bw.write("</td>");
			_bw.newLine();
		}
		
		_bw.write("\t\t\t\t<td");
		_bw.write(testClass(exception, "exception"));
		_bw.write(exceptionColspan);
		_bw.write(">");
		_bw.write(String.valueOf(exception));
		_bw.write("</td>"); _bw.newLine();
		
		// Zeit
		_bw.write("\t\t\t\t<td>");
		_bw.write(String.valueOf(time));
		_bw.write("</td>"); _bw.newLine();

		_bw.write("\t\t\t</tr>"); _bw.newLine();

		_bw.write("\t\t\t<tr>"); _bw.newLine();
		
		// Ausgabe des Package-Namen
		_bw.write("\t\t\t\t<td colspan=\"6\">Package: ");
		_bw.write(pack);
		_bw.write("</td>");
		_bw.newLine();

		_bw.write("\t\t\t</tr>"); _bw.newLine();
		
		// Tests ausgeben
		_bw.write(_tests.toString());
		_bw.newLine();
		
		// Tests neu initalisieren
		_tests = new StringBuilder();
		
		// Ende der Tabelle
		_bw.write("\t\t</table>");
		_bw.newLine();
	}
	
	/**
	 * Überprüft, ob der angegebene Test größer 0 ist. Wenn dies der Fall ist,
	 * so wird die angegebene Klasse ausgegebenen.
	 * 
	 * @param test Test, der überprüft werden soll.
	 * 
	 * @param className Name der Klasse die ausgegeben werden soll.
	 * 
	 * @return Klasse, wenn test > 0 ist. Ansonnsten leere Zeichenkette.
	 */
	private String testClass(int test, String className) {
		String ret = new String();
		
		if (test > 0)
			ret = " class=\"" + className + "\"";
		
		return ret;
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
	 * @param exception Anzahl Fehlerhafter Tests
	 * 
	 * @param time Zeit, die die Test-Suite insgesamt gebraucht hat
	 * 
	 * @throws IOException
	 */
	public void suiteHtml(String name, String pack, int right, int exception,
			long time)
		throws IOException {
		suiteHtml(name, pack, right, -1, -1, exception, time);
	}
	
	// FIXME Ausgabe der Zeit!!!
	
	/**
	 * Erzeugt den HTML-Code für einen Test
	 * 
	 * @param name Name der Test-Suite
	 * 
	 * @param right Anzahl richtiger Tests
	 * 
	 * @param wrong Anzahl falscher Tests
	 * 
	 * @param ignore Anzahl ignorierter Tests
	 * 
	 * @param exception Anzahl Fehlerhafter Tests
	 * 
	 * @param console Ausgabe der Konsole
	 * 
	 * @param error Ausgabe der Fehler
	 * 
	 * @param time Zeit, die der Test gebraucht hat
	 * 
	 * @param fit Gibt an, ob die Links zu den Fit-Ergebnis-Dateien erstellt
	 * werden sollen oder nicht.
	 * 
	 * @param path Gibt das Verzeichnis an, in dem die Fit-Ausgabe-Dateien
	 * liegen.
	 * 
	 * @throws IOExcetption
	 */
	public void test(String name, int right, int wrong, int ignore,
			int exception, long time, InputStream console, InputStream error,
			boolean fit, String path) 
					throws IOException {
		String rightColspan = new String();
		if (wrong == -1)
			rightColspan = " colspan=\"2\"";
		
		String exceptionColspan = new String();
		if (ignore == -1)
			exceptionColspan = " colspan=\"2\"";
		
		String nl = System.getProperty("line.separator");
		
		_tests.append("\t\t\t<tr>");
		_tests.append(nl);
		
		_tests.append("\t\t\t\t<td>");
		
		if (fit) {
			_tests.append("<a href=\"");
			_tests.append(path);
			_tests.append(File.separator);
			_tests.append(name);
			_tests.append(".html\">");
		}
		
		_tests.append(String.valueOf(name));
		
		if (fit)
			_tests.append("</a>");
		
		_tests.append("<div class=\"right\"><a href=\"");
		_tests.append("javascript:toogleDisplayId(");
		_tests.append(_id);
		_tests.append(")\">Ausgabe</a></div>");
		_tests.append("<div class=\"testoutInvisible\" id=\"id_");
		_tests.append(_id++);
		_tests.append("\">");
		
		String tmp = streamOut(console);
		if ((tmp != null) && !tmp.isEmpty()) {
			_tests.append("<div class=\"console\">");
			_tests.append(tmp);
			_tests.append("</div>");
		}

		tmp = streamOut(error);
		if ((tmp != null) && !tmp.isEmpty()) {
			_tests.append("<div class=\"error\"");
			_tests.append(tmp);
			_tests.append("</div>");
		}
		_tests.append("</div>");
		_tests.append("</td>");
		_tests.append(nl);
		
		_tests.append("\t\t\t\t<td");
		_tests.append(testClass(right, "pass"));
		_tests.append(rightColspan);
		_tests.append(">");
		_tests.append(String.valueOf(right));
		_tests.append("</td>");
		_tests.append(nl);
		
		if (wrong > -1) {
			_tests.append("\t\t\t\t<td");
			_tests.append(testClass(wrong, "wrong"));
			_tests.append(">");
			_tests.append(String.valueOf(wrong));
			_tests.append("</td>");
			_tests.append(nl);
		}
		
		if (ignore > -1) {
			_tests.append("\t\t\t\t<td");
			_tests.append(testClass(ignore, "ignore"));
			_tests.append(">");
			_tests.append(String.valueOf(ignore));
			_tests.append("</td>");
			_tests.append(nl);
		}
		
		_tests.append("\t\t\t\t<td");
		_tests.append(testClass(exception, "exception"));
		_tests.append(exceptionColspan);
		_tests.append(">");
		_tests.append(String.valueOf(exception));
		_tests.append("</td>");
		_tests.append(nl);
		
		_tests.append("\t\t\t\t<td>");
		_tests.append(time);
		_tests.append("</td>");
		_tests.append(nl);
		
		_tests.append("\t\t\t</tr>");
		_tests.append(nl);
	}
	
	/**
	 * Liest den angegebenen Stream aus und gibt diesen als String zurück. Die
	 * Zeichen "Neue Zeile" werden durch ein "<br/>" ersetzt.
	 * 
	 * @param is Stream, der ausgelesen werden soll.
	 * 
	 * @return Ausgelesener Stream.
	 */
	private String streamOut(InputStream is) throws IOException {
		// Rückgabe vorbereiten
		StringBuilder buffer = new StringBuilder();
		
		// Stream auslesen
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			if (isr.ready()) {
				BufferedReader br = new BufferedReader(isr);
				String line;
				while ((line = br.readLine()) != null) {
					buffer.append(line);
					buffer.append("<br/>");
				}
			}
		}
		
		// Rückgabe
		return buffer.toString();
	}

	/**
	 * Erzeugt den HTML-Code für einen Test
	 * 
	 * @param name Name der Test-Suite
	 * 
	 * @param right Anzahl richtiger Tests
	 * 
	 * @param exception Anzahl Fehlerhafter Tests
	 * 
	 * @param time Zeit, die der Test gebraucht hat
	 * 
	 * @param in Ausgabe der Konsole
	 * 
	 * @param error Ausgabe der Fehler
	 * 
	 * @throws IOExcetption
	 */
	public void test(String name, int right, int exception, long time,
			InputStream console, InputStream error) throws IOException {
		test(name, right, -1, -1, exception, time, console, error, false, null);
	}
}
