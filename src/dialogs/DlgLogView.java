package dialogs;

import java.awt.Window;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

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
	public DlgLogView(Window owner, List<String> list) {
		// Dialog initalisieren
		super(owner);
		
		// Modaler Dialog
		setModal(true);
		
		// Größe
		setSize(600, 400);
		
		// Dialog-Titel
		setTitle("Log");
		
		// Liste anzeigen
		final DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < list.size(); i++)
			model.addElement(list.get(i));
		JList<String> l = new JList<String>(model);
		JScrollPane pane = new JScrollPane(l);
		add(pane);
		
		// Anzeigen
		pack();
		setVisible(true);
	}
}
