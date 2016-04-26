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

package tests.tests.elements;

import javax.swing.JButton;

import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.Test;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JInternalFrameOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;

import haushaltsbuch.db.DbController;
import haushaltsbuch.dialogs.DlgReport;
import haushaltsbuch.windows.WndMain;
import tests.apps.TestToolBarApplication;
import tests.testcase.GuiTest;

/**
 * Implementiert das Testprogramm, um jemmy- und Fit-Tests auszufügen, um die
 * {@link haushaltsbuch.elementes.ToolBarMain} zu testen.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestToolBarMainFit extends GuiTest {
	
	/**
	 * Speichert das Hauptfenster.
	 */
	private JFrameOperator _wnd;
	
	/**
	 * Speichert das Unterfenster
	 */
	private JInternalFrameOperator _frame;
	
	/**
	 * Speichert den Dialog für Report-Einstellungen
	 */
	private JDialogOperator _dlg;
	
	/**
	 * Speichert die ToolBar
	 */
	private JComponentOperator _toolbar;
	
	/**
	 * Initalisiert die Klasse und die Tests.
	 */
	public TestToolBarMainFit() throws Exception {
		// Start des Haupt-Programms
		new ClassReference("tests.apps.TestToolBarApplication").startApplication();
		
		// Datenabnk vorbereiten
		DbController.getInstance().prepaireDatabase();
		
		// Fenster des Hauptprogrammes
		_wnd = new JFrameOperator(TestToolBarApplication.TITLE);
		
		// ToolBar abfangen
		_toolbar = new JComponentOperator(_wnd, 4);
	}
	
	/**
	 * Gibt den Namen der ToolBar zurück
	 * 
	 * @return Name der ToolBar
	 */
	public String getToolBarName() {
		return _toolbar.getName();
	}
	
	/**
	 * Drückt den Button für Report und fängt das Fenster ab.
	 */
	public void pushReport() {
		new JButtonOperator((JButton)_toolbar.getComponent(4)).pushNoBlock();
		_dlg = new JDialogOperator(_wnd, DlgReport.DIALOG_TITLE);
	}
	
	/**
	 * Ermittelt ob das Unterfenster angezeigt wird.
	 * 
	 * @return Wird das Unterfenster angezeigt?
	 */
	public boolean isInternalFrameVisible() {
		return _frame.isVisible();
	}
	
	/**
	 * Ermittelt ob der Dialog angezeigt wird.
	 * 
	 * @return Wird der Dialog angezeigt?
	 */
	public boolean isDialogVisible() {
		return _dlg.isVisible();
	}
	
	/**
	 * Ermittelt den Titel des Dialogs
	 * 
	 * @return Titel des Dialogs
	 */
	public String getDialogTitle() {
		return _dlg.getTitle();
	}
	
	/**
	 * Ermittelt ob der Button "Neu" benutzbar ist.
	 * 
	 * @return Ist "Neu" benutzbar
	 */
	public boolean isButtonInsertEnable() {
		return new JButtonOperator((JButton)_toolbar.getComponent(0)).isEnabled();
	}
	
	/**
	 * Ermittelt ob der Button "Ändern" benutzbar ist.
	 * 
	 * @return Ist "Ändern" benutzbar
	 */
	public boolean isButtonChangeEnable() {
		return new JButtonOperator((JButton)_toolbar.getComponent(1)).isEnabled();
	}
	
	/**
	 * Ermittelt ob der Button "Löschen" benutzbar ist.
	 * 
	 * @return Ist "Löschen" benutzbar
	 */
	public boolean isButtonDeleteEnable() {
		return new JButtonOperator((JButton)_toolbar.getComponent(2)).isEnabled();
	}

	/**
	 * Führt die Tests aus.
	 */
	@Override
	public int runIt(Object arg0) {
		return 0;
	}
	
	/**
	 * Klickt auf den angegebenen Menü-Eintrag.
	 * 
	 * @param path Menü-Eintrag, der gedrückt werden soll
	 */
	public void pushMenu(String path) {
		new JMenuBarOperator(_wnd).pushMenu(path);
	}

	/**
	 * Initalisiert die Test-Umgebung
	 * 
	 * @param args Parameter von der Kommandozeile
	 */
	public static void main(String[] args) {
		System.setProperty("testing", "true");
		Test.main(new String[] {"tests.tests.elements.TestToolBarMainFit"});
	}
}
