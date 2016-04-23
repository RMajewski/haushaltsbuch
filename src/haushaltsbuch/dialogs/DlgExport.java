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

package haushaltsbuch.dialogs;

import java.awt.Window;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;

/**
 * Von diesen Dialog werden alle Dialoge abgeleitet, die Einstellungen für einen
 * Export-Vorgang bereitstellen.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
public class DlgExport extends JDialog {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -8031712451794602775L;
	
	/**
	 * Speichert die einzelnen Einstellungen
	 */
	protected Map<String, Object> _preference;
	
	/**
	 * Speichert, ob abgebrochen wurde
	 */
	protected boolean _cancel;
	
	/**
	 * Initalisiert den Dialog
	 * 
	 * @param owner Fenster, das den Dialog aufgerufen hat.
	 */
	public DlgExport(Window owner) {
		// Dialog initalisieren
		super(owner);
		
		// Einstellungen initalisieren
		_preference = new HashMap<String, Object>();
		
		// Bis jetzt nicht abgebrochen
		_cancel = false;
	}
	
	/**
	 * Gibt die gespeicherten Einstellungen zurück
	 * 
	 * @return Gespeicherte Einstellungen
	 */
	public Map<String, Object> getPreferences() {
		return _preference;
	}
	
	/**
	 * Gibt <b>true</b> zurück, wenn abgebrochen wurde. Wurde gespeichert, so 
	 * wird <b>false</b> zurück gegebn.
	 * 
	 * @return Ermittelt, ob abgerbrochen wurde oder nicht.
	 */
	public boolean isCancel() {
		return _cancel;
	}
}
