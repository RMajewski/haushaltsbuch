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

package tests.tests.windows;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import haushaltsbuch.windows.WndMain;
import tests.testcase.GuiTest;

/**
 * Testet das Hauptfenster
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestWndMain extends GuiTest {
	
	/**
	 * Speichert das Hauptfenster.
	 */
	private JFrameOperator _wnd;
	
	/**
	 * Initalisiert die Klasse
	 * 
	 * @throws Exception
	 */
	public TestWndMain() throws Exception {
		// Start des Haupt-Programms
		new ClassReference("haushaltsbuch.Main").startApplication();
		
		// Fenster des Hauptprogrammes
		_wnd = new JFrameOperator(WndMain.TITLE);
	}

	/**
	 * Führt die einzelnen Tests aus.
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			test("Gibt es ein Menü?", new JMenuBarOperator(_wnd).isEnabled());
			
			test("Wurde die StatusBar eingefügt?", 
					new JLabelOperator(_wnd).getText().equals("Ready"));
			
			test("Desktop für die Unterfenster eingefügt?",
					_wnd.getContentPane().getComponent(0).getClass().getName().equals("javax.swing.JDesktopPane"));
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	/**
	 * Gibt zurück, ob das HauptMeni initalisiert und ins Hauptfenster eingefügt
	 * wurde.
	 * 
	 * @return Wurde das Menü initalisiert?
	 */
	public boolean havMainMenu() {
		return new JMenuBarOperator(_wnd).isEnabled();
	}


	/**
	 * Gibt zurück, ob im Hauptfenster der Dekstop für die unterfenster vorbereitet
	 * wurde.
	 * 
	 * @return Wurde der Desktop initalisiert?
	 */
	public boolean isDekstopInit() {
		return _wnd.getContentPane().getComponent(1).getClass().getName().equals("haushaltsbuch.elements.Desktop");
	}

	/**
	 * Gibt zurück, ob im Hauptfenster die StatusBar initalisiert und angezeigt
	 * wird.
	 * 
	 * @return Wurde die StatusBar initalisiert?
	 */
	public boolean haveAStatusBar() {
		return new JLabelOperator(_wnd).getText().equals("Ready");
	}
	
	/**
	 * Drückt den Eintrag "Beenden" im Menü "Datei"
	 */
	public void pushFileEnde() {
		new JMenuBarOperator(_wnd).pushMenu("Datei|Beenden");
	}
	
	/**
	 * Ermittelt, ob das Fenster angezeigt wird oder nicht.
	 * 
	 * @return Wird das Fenster angezeigt?
	 */
	public boolean isWindowVisible() {
		return _wnd.isVisible();
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Parameter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.windows.TestWndMain"});
	}
}
