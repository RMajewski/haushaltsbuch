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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Zeigt die Einstellungen für den Export des Reports als PDF.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class DlgExportPdf extends DlgExport implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 6956848516720437406L;

	/**
	 * Speichert den Titel des Dialogs
	 */
	public static final String DIALOG_TITLE = "Export: PDF";
	
	/**
	 * Speichert den Namen der Einstellung für den Export der Tabelle.
	 */
	public static final String EXPORT_TABLE = "table";
	
	/**
	 * Speichert den Namen der Einstellung für den Export des Balkendiagrammes.
	 */
	public static final String EXPORT_BAR_CHART = "bar_chart";
	
	/**
	 * Speichert den Button zum Exportieren.
	 */
	private JButton _btnExport;

	/**
	 * Speichert den Button zum Abbrechen.
	 */
	private JButton _btnCancel;
	
	/**
	 * Speichert die Auswahl zum exportieren der Tabelle
	 */
	private JCheckBox _cbTable;
	
	/**
	 * Speichert die Auswahl zum exportieren des Balken-Diagramms.
	 */
	private JCheckBox _cbBarChart;
	
	/**
	 * ActionCommand zum Abbrechen
	 */
	private static final String CANCEL = new String("DlgExportPdfCancel");
	
	/**
	 * ActionCommand zum Exportieren der PDF-Datei
	 */
	private static final String EXPORT = new String("DlgExportPdfExport");
	
	/**
	 * Initalisiert diesen Dialog.
	 * 
	 * @param owner Fenster, dass diesen Dialog aufgerufen hat.
	 */
	public DlgExportPdf(Window owner) {
		// Klasse initalisieren.
		super(owner);
		
		// Größe
		setSize(450, 300);
		
		// Titel
		setTitle(DIALOG_TITLE);
		
		// Modal
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panButtons = new JPanel();
		getContentPane().add(panButtons, BorderLayout.SOUTH);
		panButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		_btnExport = new JButton("Export");
		_btnExport.setMnemonic('1');
		_btnExport.addActionListener(this);
		_btnExport.setActionCommand(EXPORT);
		panButtons.add(_btnExport);
		
		_btnCancel = new JButton("Abbrechen");
		_btnCancel.setMnemonic('1');
		_btnCancel.addActionListener(this);
		_btnCancel.setActionCommand(CANCEL);
		panButtons.add(_btnCancel);
		
		JPanel panExport = new JPanel();
		getContentPane().add(panExport, BorderLayout.CENTER);
		GridBagLayout gbl_panExport = new GridBagLayout();
		gbl_panExport.columnWidths = new int[]{0, 0};
		gbl_panExport.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panExport.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panExport.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panExport.setLayout(gbl_panExport);
		
		_cbTable = new JCheckBox("Tabelle");
		_cbTable.setSelected(true);
		GridBagConstraints gbc_chckbxTabelle = new GridBagConstraints();
		gbc_chckbxTabelle.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxTabelle.gridx = 0;
		gbc_chckbxTabelle.gridy = 0;
		panExport.add(_cbTable, gbc_chckbxTabelle);
		
		_cbBarChart = new JCheckBox("Balken-Diagramm");
		GridBagConstraints gbc_chckbxBalkendiagramm = new GridBagConstraints();
		gbc_chckbxBalkendiagramm.gridx = 0;
		gbc_chckbxBalkendiagramm.gridy = 2;
		panExport.add(_cbBarChart, gbc_chckbxBalkendiagramm);
	}

	/**
	 * Reagiert auf das Drücken eines Buttons
	 * 
	 * @param ae Daten dieses Events
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Abbrechen?
		if (ae.getActionCommand().equals(CANCEL)) {
			setVisible(false);
			_cancel = true;
		}
		
		// Exportieren
		else if (ae.getActionCommand().equals(EXPORT)) {
			setVisible(false);
			if (_cbTable.isSelected())
				_preference.put(EXPORT_TABLE, 1);
			
			if (_cbBarChart.isSelected())
				_preference.put(EXPORT_BAR_CHART, 1);
		}
	}

}
