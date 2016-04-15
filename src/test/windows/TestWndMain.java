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

package test.windows;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Scenario;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import test.GuiTest;
import windows.WndMain;

/**
 * Testet das Hauptfenster
 * 
 * @author René Majewski
 */
public class TestWndMain extends GuiTest {

	/**
	 * Führt die einzelnen Tests aus.
	 */
	@Override
	public int runIt(Object arg0) {
		try {
			// Start des Haupt-Programms
			new ClassReference("Main").startApplication();
			
			// Fenster des Hauptprogrammes
			JFrameOperator wnd = new JFrameOperator(WndMain.TITLE);
			
			test("Gibt es ein Menü?", new JMenuBarOperator(wnd).isEnabled());
			
			test("Wurde die StatusBar eingefügt?", 
					new JLabelOperator(wnd).getText().equals("Ready"));
			
			test("Desktop für die Unterfenster eingefügt?",
					wnd.getContentPane().getComponent(0).getClass().getName().equals("javax.swing.JDesktopPane"));
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		
		// Da bisher nicht beendet mit 0 beenden (kein Fehler)
		return 0;
	}

	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"test.windows.TestWndMain"});
	}

}
