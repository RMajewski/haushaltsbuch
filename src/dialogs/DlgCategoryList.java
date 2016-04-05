package dialogs;

import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DlgCategoryList extends JDialog {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3602076466416544711L;
	
	/**
	 * Namen der Tabellen-Spalten
	 */
	private String[] columnNames = {"ID", "Kategorie"};
	
	
	/**
	 * Initalisiert den den Dialog und die Tabelle. Anschließend wird der
	 * Dialog angezeigt.
	 * 
	 * @param owner Vater-Fenster
	 */
	public DlgCategoryList(Window owner) {
		// Dialog initalieren
		super(owner);
		
		// Nicht modal
		setModal(false);
		
		// Größe
		setSize(600, 400);
		
		// Titel
		setTitle("Kategorien");
		
		// Tabelle initalisieren
		String[][] test =  { {"0", "1"} };
		JTable table = new JTable(test, columnNames);
		add(new JScrollPane(table));
		
		// Anzeigen
		pack();
		setVisible(true);
	}
}
