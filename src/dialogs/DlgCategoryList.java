package dialogs;

import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tables.models.CategoryListModel;

public class DlgCategoryList extends JDialog {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3602076466416544711L;
	
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
		JTable table = new JTable(new CategoryListModel());
		table.getColumnModel().getColumn(0).setHeaderValue("ID");
		table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");
		add(new JScrollPane(table));
		
		// Anzeigen
		pack();
		setVisible(true);
	}
}
