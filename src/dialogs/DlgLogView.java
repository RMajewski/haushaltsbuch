package dialogs;

import java.awt.Window;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import datas.LogData;
import elements.StatusBar;
import renderer.LogViewListRenderer;

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
