package windows.internal;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import datas.MoneyDetailsData;

/**
 * Fenster zum Eingaben der Daten für einen Details-Datensatz von Money.
 * 
 * @author René Majewski
 */
public class WndMoneyDetailsChange extends WndInternalFrame implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 3225451593739557575L;
	
	/**
	 * Speichert den Datensatz.
	 */
	private MoneyDetailsData _data;
	
	/**
	 * Speichert das Fenster, welches dieses Fenster aufgerufen hat.
	 */
	private JInternalFrame _frame;
	
	/**
	 * Speichert das ActionCommand für OK-Button
	 */
	private static final String SAVE = "Save";
	
	/**
	 * Speichert das ActionCommand für den Abbrechen-Button
	 */
	private static final String CANCEL = "Cancel";

	/**
	 * Initalisiert das Fenster
	 * 
	 * @param data Datensatz, dessen Daten geändert werden sollen
	 * 
	 * @param frame Fenster, welches dieses Fenster aufgereufen hat
	 */
	public WndMoneyDetailsChange(MoneyDetailsData data, JInternalFrame frame) {
		super();
		
		// Datensatz speichern
		_data = data;
		_frame = frame;
		
		// Titel des Fensters
		if (data == null)
			setTitle("Neuen Datensatz erstellen");
		else
			setTitle("Datensatz ändern");
		
		// GridBag-Layout initalisieren und setzen
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		// Speichern-Button
		JButton btn = new JButton("Speichern");
		btn.setActionCommand(SAVE);
		btn.setMnemonic('S');
		btn.setSelected(true);
		btn.addActionListener(this);
		addComponent(gbl, btn, 2, 10, 1, 1, 0, 0);
		
		// Abbrechen-Button
		btn = new JButton("Abbrechen");
		btn.setMnemonic('A');
		btn.setActionCommand(CANCEL);
		btn.addActionListener(this);
		addComponent(gbl, btn, 3, 10, 1, 1, 0, 0);
		
		// Fenster anzeigen
		pack();
		setVisible(true);
		
	}

	/**
	 * Reagiert auf das Drücken der Buttons
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Beenden
		if (ae.getActionCommand().compareTo(CANCEL) == 0)
			try {
			 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}
