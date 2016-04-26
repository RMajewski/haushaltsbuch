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

package haushaltsbuch.events;

import java.util.Vector;

import haushaltsbuch.listener.ToolBarDbElementListener;

/**
 * Implementiert die Verwaltung der Listener und führt an allen eingetragenen
 * Listener die Methode setDbElementsEnable aus.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class ToolBarDbElementEventMulticaster
	implements ToolBarDbElementListener {
	
	/**
	 * Speichert die einzelnen Listener
	 */
	private Vector<ToolBarDbElementListener> _listener;
	
	/**
	 * Initalisiert die Klasse
	 */
	public ToolBarDbElementEventMulticaster() {
		_listener = new Vector<ToolBarDbElementListener>();
	}

	/**
	 * Übermittelt die Ereignis-Daten an elle eingetragenen Listener.
	 * 
	 * @param e Ereignis-Daten
	 */
	@Override
	public void setDbElementsEnable(ToolBarDbElementEvent e) {
		for(int i = 0; i < _listener.size(); i++)
			_listener.elementAt(i).setDbElementsEnable(e);
	}

	/**
	 * Löscht den angegebenen Listener
	 * 
	 * @param listener Listener, der gelöscht werden soll.
	 */
	public void remove(ToolBarDbElementListener listener) {
		_listener.remove(listener);
	}
	
	/**
	 * Fügt einen neuen Listener der Liste hinzu.
	 * 
	 * Es wird überprüft, ob der Listener schon in der Liste vorhanden ist. Ist
	 * dies nicht der Fall, so wird er an die Liste angehängt.
	 * 
	 * @param listener Listener, der in die Liste hinzu gefügt werden soll.
	 */
	public void add(ToolBarDbElementListener listener) {
		if (!_listener.contains(listener))
			_listener.addElement(listener);
	}
	
	/**
	 * Gibt die Liste mit Listenern zurück.
	 */
	public Vector<ToolBarDbElementListener> getListener() {
		return _listener;
	}
}
