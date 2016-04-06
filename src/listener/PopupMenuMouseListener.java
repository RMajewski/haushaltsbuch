package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 * Wenn die rechte Maustaste gedrückt wird, wird das übergebene Popup-Menü
 * angezeigt.
 * 
 * @author René Majewski
 *
 */
public class PopupMenuMouseListener extends MouseAdapter {
	/**
	 * Speichert das Poup-Menü, das angezeigt werden soll
	 */
	private JPopupMenu _popupmenu;
	
	/**
	 * Initalisiert den Listener und speichert das Popup-Menü, welches
	 * angzeigt werden soll, wenn die rechte Maustaste gedrückt wird.
	 * 
	 * @param popup Popup-Menü, welches angezeigt werden soll.
	 */
	public PopupMenuMouseListener(JPopupMenu popup) {
		super();
		_popupmenu = popup;
	}

	/**
	 * Reagiert auf Tastendrücke der Maus.
	 * 
	 * @param m Event-Daten
	 */
	@Override
	public void mouseClicked(MouseEvent m) {
		if (SwingUtilities.isRightMouseButton(m)) {
			_popupmenu.show(m.getComponent(), m.getX(), m.getY());
		}
	}
}
