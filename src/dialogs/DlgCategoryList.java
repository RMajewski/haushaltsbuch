package dialogs;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import listener.PopupMenuMouseListener;
import menus.PopupCategoryList;
import tables.models.CategoryListModel;

public class DlgCategoryList extends JDialog implements ActionListener {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3602076466416544711L;
	
	/**
	 * Speichert das Popup-Menü
	 */
	PopupCategoryList _popup;
	
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
		
		// Popup-Menü initalisieren
		_popup = new PopupCategoryList(this);
		table.addMouseListener(new PopupMenuMouseListener(_popup));
		
		// Anzeigen
		pack();
		setVisible(true);
	}

	/**
	 * Reagiert auf die einzelnen Einträge im PopupMenu.
	 * 
	 * @param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
	}
}
