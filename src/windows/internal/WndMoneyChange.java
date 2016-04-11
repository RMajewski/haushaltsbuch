package windows.internal;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import datas.MoneyData;
import db.DbController;
import elements.StatusBar;
import tables.models.MoneyListModel;

// FIXME Überprüfen vor Speichern, ob Datum angegeben ist. Wenn nicht -> Darauf hinweisen
// TODO Ausgaben für Datenbank-Abfragen in der StatusBar vereinheitlichen.

/**
 * Zeigt das Fenster an, um einen neuen Datensatz anzulegen oder einen
 * Datensazu zu ändern in der Datenbank-Tabelle 'money'.
 * 
 * @author René Majewski
 */
public class WndMoneyChange extends WndInternalFrame implements ActionListener {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 3911001002528630785L;
	
	/**
	 * Speichert das ActionCommand für OK-Button
	 */
	private static final String SAVE = "Save";
	
	/**
	 * Speichert das ActionCommand für den Abbrechen-Button
	 */
	private static final String CANCEL = "Cancel";
	
	/**
	 * Speichert den Datensatz
	 */
	private MoneyData _data;
	
	/**
	 * Speichert das Fenster, aus dem dieses Fenster aufgerufen wurde.
	 */
	private JInternalFrame _frame;
	
	/**
	 * Speichert das Textfeld für das Datum.
	 */
	private JFormattedTextField _txtDate; 
	
	/**
	 * Speichert den Radio-Button für die Einnahme
	 */
	private JRadioButton _rbIn;
	
	/**
	 * Speichert den Radio-Button für die Ausgabe
	 */
	private JRadioButton _rbOut;
	
	/**
	 * Speichert die TextArea für die Beschreibung.
	 */
	private JTextArea _txtComment;

	/**
	 * Initalisiert das Fenster.
	 * 
	 * @param data Datensatz, der geändert werden soll. Wird <b>null</b>
	 * angegeben, so wird ein neuer Datensatz erstellt.
	 * 
	 * @param frame Fenster, aus dem dieses Fenster aufgerufen wurde.
	 */
	public WndMoneyChange(MoneyData data, JInternalFrame frame) {
		// Fenster initalisieren
		super();
		
		// ID und Fenster speichern
		_data = data;
		_frame = frame;
		
		// Fenster-Titel
		if (_data == null)
			setTitle("Neuen Datensatz erstellen");
		else
			setTitle("Datensätz ändern");
		
		// Fenster-Größe
		setSize(600, 400);
		
		// Fenster-Eigenschaften
		setResizable(false);
		setClosable(true);
		setMaximizable(false);
		setIconifiable(false);
		
		// GridBag-Layout initalisieren und setzen
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		// Label für das Datum
		JLabel label = new JLabel("Datum");
		addComponent(gbl, label, 0, 0, 1, 1, 0.2, 0);
		
		// Textfeld für das Datum
		_txtDate = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		addComponent(gbl, _txtDate, 2, 0, 2, 1, 0.8, 0);
		
		// ButtonGroup initalisieren
		ButtonGroup bg = new ButtonGroup();
		
		// Radion-Button für die Einnahme
		_rbIn = new JRadioButton("Einname");
		addComponent(gbl, _rbIn, 2, 2, 2, 1, 0, 0);
		_rbIn.setSelected(true);
		bg.add(_rbIn);
		
		// Radio-Button für die Ausgabe
		_rbOut = new JRadioButton("Ausgabe");
		addComponent(gbl, _rbOut, 2, 3, 2, 1, 0, 0);
		bg.add(_rbOut);
		
		// Label für die Beschreibung
		label = new JLabel("Beschreibung");
		addComponent(gbl, label, 0, 5, 1, 1, 0.2, 0);
		
		// Mehrzeiliger Text für die Beschreibung
		_txtComment = new JTextArea();
		addComponent(gbl, new JScrollPane(_txtComment), 2, 5, 2, 4, 0.8, 0.5);
		
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
		
		// Daten einfügen?
		if (_data != null) {
			// Datum einfügen
			_txtDate.setText(_data.getDateAsString());
			
			// Einnahme oder Ausgabe
			if (_data.getInOut() == MoneyData.INCOMING)
				_rbIn.setSelected(true);
			else
				_rbOut.setSelected(true);
			
			// Beschreibung einfügen
			_txtComment.setText(_data.getComment());
		}
		
		// Fenster anzeigen und Fokus holen
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// Beenden
		if (ae.getActionCommand().compareTo(CANCEL) == 0)
			try {
			 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// Speichern
		else if (ae.getActionCommand().compareTo(SAVE) == 0) {
			// Überprüfen ob kein Datum eingegeben wurde
			if (_txtDate.getText().isEmpty()) {
				// Benutzer darauf hinweisen
				JOptionPane.showConfirmDialog(this, "Sie haben vergessen ein Datum einzugeben.", "Datum", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				// Focus setzen
				_txtDate.requestFocus();
				
				// Methode verlassen
				return;
			}
			
			// Datum umwandeln
			String[]tmp  =_txtDate.getText().split(Pattern.quote("."));
			long date = new GregorianCalendar(
					Integer.valueOf(tmp[2]).intValue(),
					Integer.valueOf(tmp[1]).intValue() - 1,
					Integer.valueOf(tmp[0]).intValue()).getTimeInMillis();
			
			// InOut ermitteln
			boolean inout = false;
			if (_rbOut.isSelected()) {
				inout = MoneyData.OUTGOING;
			} else if (_rbIn.isSelected()) {
				inout = MoneyData.INCOMING;
			}
			
			// Beschreibung ermitteln
			String comment = _txtComment.getText();
			
			try {
				// Datenabnk-Abfrage vorbereiten
				Statement stm = DbController.getInstance().createStatement();
				
				// Neuer Datensatz oder Datensatz ändern?
				if (_data == null) {
					// Neuer Datensatz
					if (stm.executeUpdate(DbController.queries().money().insert(date, inout, comment)) > 0)
						StatusBar.getInstance().setMessageAsOk("Neuer Datensatz in die Tabelle 'money' eingefügt.");
					else
						StatusBar.getInstance().setMessageAsError("Neuer Datensatz konnte nicht in die Tabelle 'money' eingefügt werden.");
				} else {
					// Datensatz ändern
					String sql;
					
					// Überprüfen ob ein Kommentar angegeben wurde
					if (comment.isEmpty())
						sql = DbController.queries().money().update(_data.getId(), date, inout);
					else
						sql = DbController.queries().money().update(_data.getId(), date, inout, comment);
					
					// Datenbank-Abfrage stellen
					if (stm.executeUpdate(sql) > 0)
						StatusBar.getInstance().setMessageAsOk("Der Datensatz mit der ID " + String.valueOf(_data.getId()) + " wurde in der Datenbank-Tabelle 'money' geändert.");
					else
						StatusBar.getInstance().setMessageAsError("Der Datensatz mit der ID " + String.valueOf(_data.getId()) + " konnte nit in der Datenbank-Tabelle 'money' geändert werden.");
					
				}
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError("Datenbank-Abfrage war nicht erfolgreich.");
				e.printStackTrace();
			}
			
			// Tabelle updaten
			if (_frame != null) {
				((MoneyListModel)((WndMoneyList)_frame).getTable().getModel()).dataRefresh(true);;
			}
			
			// Beenden
			try {
				 setClosed(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
