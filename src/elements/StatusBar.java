package elements;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * Zeigt die StatusBar an.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 *
 */
public class StatusBar extends JLabel {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = 3659562286526820393L;
	
	/**
	 * Liste, der in der Statuszeile angezeigten Nachrichten
	 */
	private List<String> _list;

	/**
	 * Initalisieren der StatusBar
	 */
	public StatusBar()
	{
		super();
		setPreferredSize(new Dimension(100, 20));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		// Liste initalisieren
		_list = new ArrayList<String>();
	}
	
	/**
	 * Text anzeigen
	 * 
	 * @param message Text, der angezeigt werden soll.
	 */
	public void setMessage(String message)
	{
		setText(message);
		_list.add(message);
	}
	
	/**
	 * List mit den Log zurück geben
	 * 
	 * @return Liste mit den Log-Einträgen
	 */
	public List<String> getLog() {
		return _list;
	}
}
