package dialogs;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import db.DbController;
import listener.PopupMenuMouseListener;
import menus.PopupCategoryList;
import tables.models.IdNameListModel;

/**
 * In diesen Dialog werden die einzelnen Geschäfte angezeigt.
 * 
 * @author René Majewski
 */
public class DlgSectionList extends JDialog implements ActionListener {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = -6848374575961659809L;
	
	/**
	 * Speichert das Popup-Menü
	 */
	private PopupCategoryList _popup;
	
	/**
	 * Speichert die Tabelle mit den Geschäften
	 */
	private JTable _table;
	
	/**
	 * Initalisiert den Dialog und die Tabelle. Anschließend wird der Dialog
	 * angezeigt.
	 * 
	 * @param owner Vater-Fenster
	 */
	public DlgSectionList(Window owner) {
		// Dialog initalisieren
		super(owner);
		
		// Nicht Modal
		setModal(false);
		
		// Größe
		setSize(600, 400);
		
		// Title
		setTitle("Geschäfte");
		
		// Tabelle initalisieren
		_table = new JTable(new IdNameListModel(DbController.queries().section().select()));
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Geschäft");
		_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_table.setRowSelectionAllowed(true);
		add(new JScrollPane(_table));
		
		// Popup-Menü initalisieren
		_popup = new PopupCategoryList(this);
		_table.addMouseListener(new PopupMenuMouseListener(_popup));
		
		// Anzeigen
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
