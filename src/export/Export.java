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

package export;

import java.io.File;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import dialogs.DlgExport;

/**
 * Von dieser Klasse werde alle Export-Klasse abgeleitet. Sie stellt eine
 * Public-Methode bereit, in der zuerst ein Speichern-Dialog aufgerufen wird.
 * Wird in diesen eine Datei angegeben, so wird die proteced Methode export
 * aufgerufen, Diese muss in den abgeleiteten Klassen implementiert werden.
 * In dieser Methode sollen die Befehle stehen, um die Datei zu füllen. 
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public abstract class Export {
	/**
	 * Dialog mit speziellen Einstellungen für den Export.
	 */
	protected DlgExport _dlg;
	
	/**
	 * Speichert die Einstellungen aus dem Dialog
	 */
	protected Map<String, Object> _preference;
	
	/**
	 * Wird aufgerufen, wenn eine Datei ausgewählt wurde.
	 * 
	 * @param file Date, in die der Export gespeichert werden soll.
	 */
	protected abstract void export(File file);
	
	/**
	 * Es wird ein Speichern-Dialog aufgerufen. Wird eine Datei ausgewählt, so
	 * so wird die Export-Methode aufgerufen, in der die Datei gefüllt wird.
	 */
	public void execute() {
		// Speichern-Dialog aufrufen
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("SQL-Script", ".sql"));
		int state = fc.showOpenDialog(null);
		
		// Überprüfen ob der Dialog abgebrochen wurde
		if (state == JFileChooser.CANCEL_OPTION)
			return;
		
		// Dialog für Einstellungen aufrufen
		if (_dlg != null) {
			_dlg.setVisible(true);
			
			// Wurde nicht abgebrochen
			if (!_dlg.isCancel())
				_preference = _dlg.getPreferences();
		}
		
		// Export aufrufen
		export(fc.getSelectedFile());
	}
}
