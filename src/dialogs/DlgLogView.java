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

package dialogs;

import java.awt.Window;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import datas.LogData;
import elements.StatusBar;
import renderer.LogViewListRenderer;

/**
 * In diesen Dialog wird das Log angezeigt.
 * 
 * @author René Majewski
 *
 */
public class DlgLogView extends JDialog {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Initalisiert und zeigt den Dialog an.
	 * 
	 * @param owner Vater-Objekt
	 */
	public DlgLogView(Window owner) {
		// Dialog initalisieren
		super(owner);
		
		// Modaler Dialog
		setModal(true);
		
		// Größe
		setSize(600, 400);
		
		// Dialog-Titel
		setTitle("Log");
		
		// Liste anzeigen
		final DefaultListModel<LogData> model = new DefaultListModel<LogData>();
		StatusBar status = StatusBar.getInstance();
		for (int i = 0; i < status.getLog().size(); i++) {
			LogData data = status.getLog().get(i);
			if (data.getOut() != LogData.NO_OUT) {
				model.addElement(data);
			}
		}
		JList<LogData> l = new JList<LogData>();
		l.setModel(model);
		l.setCellRenderer(new LogViewListRenderer());
		JScrollPane pane = new JScrollPane(l);
		add(pane);
		
		// Anzeigen
		pack();
		setVisible(true);
	}
}
