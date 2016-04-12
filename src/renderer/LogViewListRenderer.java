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

package renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import datas.LogData;

/**
 * Zeigt die Einträge der Log-Liste an.
 * 
 * @author René Majewski
 */
public class LogViewListRenderer extends JLabel implements ListCellRenderer<LogData> {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 8920119708582918294L;

	@Override
	public Component getListCellRendererComponent(JList<? extends LogData> list, LogData value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// Nachricht anzeigen
		setText(value.getMessage());
		
		// Muss aufgerufen werden, um den Hintergrund zu setzen
		setOpaque(true);
		
		// Wurde das Element ausgewählt?
		if (isSelected) {
			// Standart Schriftfarbe
			setForeground(UIManager.getColor("List.selectionForeground"));
			
			// Standart Hintergrundfarbe
			setBackground(UIManager.getColor("List.selectionBackground"));
		} else {
			// Standart Schriftfarbe
			setForeground(UIManager.getColor("List.foreground"));
			
			// Hintergrundfarbe setzen
			switch (value.getOut()) {
				// Warnung
				case LogData.WARNING:
					setBackground(Color.ORANGE);
					break;
					
				// Fehler
				case LogData.ERROR:
					setBackground(Color.RED);
					break;
					
				// OK
				case LogData.OK:
					setBackground(Color.GREEN);
					break;
					
				// Keine besondere Markierung
				case LogData.NONE:
					setBackground(UIManager.getColor("List.background"));
					break;
			}
		}
		return this;
	}

}
