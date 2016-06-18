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

package haushaltsbuch.windows.internal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.datas.RepaySearchData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.tables.models.RepaySearchListModel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

public class WndRepaySearchList extends WndInternalFrame
	implements ActionListener {
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ActionCommand zum Übernehmen der Datensätze.
	 */
	public static final String OK = "WndRepaySearchList.Ok";
	
	/**
	 * ActionCommand zum Abbrechen
	 */
	public static final String CANCEL = "WndRepaySearchList.Cancel";
	
	/**
	 * Speichert die Tabelle
	 */
	private JTable _table;
	
	/**
	 * Initialisiert das Fenster
	 * 
	 * @param desktop Desktop, auf dem sich das Fenster befindet.
	 * 
	 * @param data Datensatz der Schulden
	 */
	public WndRepaySearchList(Desktop desktop, OutstandingData data) {
		// Fenster initialisieren
		super(desktop);
		setTitle("Rückzahlungen suchen");
		
		// Kein Datenbank-Elemente
		setEnableDbElements(false);
		
		// Größenänderung des Fenster zulassen 
		setResizable(true);
		setMinimumSize(new Dimension(600, 400));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Tabelle initialisieren
		RepaySearchListModel model = new RepaySearchListModel(data);
		_table = new JTable(model);
		_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_table.setRowSelectionAllowed(true);
		JScrollPane pane = new JScrollPane(_table);
		getContentPane().add(pane, BorderLayout.CENTER);
		
		// Spalten-Überschriften
		_table.getColumnModel().getColumn(0).setHeaderValue("Übernehmen?");
		_table.getColumnModel().getColumn(1).setHeaderValue("Details-ID");
		_table.getColumnModel().getColumn(2).setHeaderValue("Outstanding-ID");
		_table.getColumnModel().getColumn(3).setHeaderValue("Datum");
		_table.getColumnModel().getColumn(4).setHeaderValue("Betrag");
		_table.getColumnModel().getColumn(5).setHeaderValue("Kommentar");
		
		// Panel für die Buttons
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		// Übernehmen
		JButton button = new JButton("Übernehmen");
		button.setMnemonic('Ü');
		button.addActionListener(this);
		button.setActionCommand(OK);
		panel.add(button);
		
		// Abbrechen
		button = new JButton("Abbrechen");
		button.setMnemonic('A');
		button.addActionListener(this);
		button.setActionCommand(CANCEL);
		panel.add(button);
		
		// Fenster anzeigen
		pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die Eingaben
	 * 
	 * @param Daten des Eventes
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(OK)) {
			for (int i = 0; i < _table.getModel().getRowCount(); i++) {
				RepaySearchData rsd = ((RepaySearchListModel)_table.getModel())
						.getRowDataAt(i);
				try {
					if (rsd.getTake()) {
						Statement stm = DbController.getInstance()
								.createStatement();
						stm.executeUpdate(DbController.queries().repay().insert(
								rsd.getDetailsId(),
								((RepaySearchListModel)_table.getModel())
								.getOutstandingId()));
					}
				} catch (SQLException e) {
					StatusBar.getInstance().setMessageAsError(e);
				}
			}
		} else if (ae.getActionCommand().equals(CANCEL)) {
			dispose();
		}
	}
}
