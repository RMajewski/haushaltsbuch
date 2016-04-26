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

package tests.tests.actions;

import java.awt.event.ActionEvent;

import haushaltsbuch.actions.Action;

/**
 * Implementiert die abstrakte Klasse {@link haushaltsbuch.actions.Action}. Dies
 * ist notwendig, damit die Tests für diese Klasse ausgeführt werden können.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestActionImplementation extends Action {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -727117647121785702L;

	/**
	 * Initalisiert diese Klasse
	 */
	public TestActionImplementation(String small, String big) {
		super(small, big);
	}

	/**
	 * Leere Implementierung
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
