/* 
* Copyright 2016 René Majewski
*  
* Lizenziert unter der EUPL, Version 1.1 oder - sobald diese von der
* Europäischen Kommission genehmigt wurden - Folgeversionen der EUPL
* ("Lizenz"); Sie dürfen dieses Werk ausschließlich gemäß dieser Lizenz
* nutzen. 
* 
* Eine Kopie der Lizenz finden Sie hier: 
* https://joinup.ec.europa.eu/software/page/eupl
*  
* Sofern nicht durch anwendbare Rechtsvorschriften gefordert oder in 
* schriftlicher Form vereinbart, wird die unter der Lizenz verbreitete 
* Software "so wie sie ist", OHNE JEGLICHE GEWÄHRLEISTUNG ODER BEDINGUNGEN -
* ausdrücklich oder stillschweigend - verbreitet.
* Die sprachspezifischen Genehmigungen und Beschränkungen unter der Lizenz
* sind dem Lizenztext zu entnehmen.
*/ 

package test.renderer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JLabel;
import javax.swing.UIManager;

import org.junit.Before;
import org.junit.Test;

import datas.LogData;
import renderer.LogViewListRenderer;

/**
 * Testet die Klasse {@link renderer.LogViewListRenderer}.
 * 
 * @author René Majewski
 */
public class TestLogViewListRenderer {
	/**
	 * Speichert den Renderer
	 */
	private LogViewListRenderer _renderer;
	
	/**
	 * Speichert die Nachricht
	 */
	private String _message;

	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_renderer = new LogViewListRenderer();
		_message = "Dies ist ein Test!";
	}
	
	/**
	 * Erzeugt den Datensatz
	 * 
	 * @param out Was soll angezeigt werden.
	 */
	private LogData createDataSet(short out) {
		return new LogData(_message, out);
	}
	
	/**
	 * Testet, ob die Nachricht angezeigt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererHaveSetMessageText() {
		assertTrue(((JLabel)_renderer.getListCellRendererComponent(null, createDataSet(LogData.NONE), 1, false, false)).getText().equals(_message));
	}
	
	/**
	 * Testet, die richtige text-Farbe gesetzt wurde.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererHaveSetRightForeground() {
		assertEquals(UIManager.getColor("List.foreground"), _renderer.getListCellRendererComponent(null, createDataSet(LogData.NONE), 1, false, false).getForeground());
	}
	
	/**
	 * Testet, ob die entsprechende Hintergrundfarbe für "normale" Nachrichten
	 * gesetzt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererSetRightBackgroundColorForNormalMessages() {
		assertEquals(LogData.COLOR_NONE, _renderer.getListCellRendererComponent(null, createDataSet(LogData.NONE), 1, false, false).getBackground());
		// UIManager.getColor("List.background")
	}
	
	/**
	 * Testet, ob die entsprechende Hintergrundfarbe für Warnungen
	 * gesetzt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererSetRightbackgroundColorForWarnings() {
		assertEquals(LogData.COLOR_WARNING, _renderer.getListCellRendererComponent(null, createDataSet(LogData.WARNING), 1, false, false).getBackground());
	}
	
	/**
	 * Testet, ob die entsprechende Hintergrundfarbe für Fehler
	 * gesetzt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererSetRightBackgroundColorForErrors() {
		assertEquals(LogData.COLOR_ERROR, _renderer.getListCellRendererComponent(null, createDataSet(LogData.ERROR), 1, false, false).getBackground());
	}
	
	/**
	 * Testet, ob die entsprechende Hintergrundfarbe für "In Ordnung"-
	 * Nachrichten gesetzt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererSetRightBackgroundColorForOk() {
		assertEquals(LogData.COLOR_OK, _renderer.getListCellRendererComponent(null, createDataSet(LogData.OK), 1, false, false).getBackground());
	}
	
	/**
	 * Testet, ob die entsprechende Hintergrundfarbe für eine Selektierung
	 * gesetzt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererSetRightBackgroundColorSelections() {
		assertEquals(UIManager.getColor("List.selectionBackground"), _renderer.getListCellRendererComponent(null, createDataSet(LogData.WARNING), 1, true, false).getBackground());
	}
	
	/**
	 * Testet, ob die entsprechende Text-Farbe für eine Selektierung
	 * gesetzt wird.
	 * 
	 * @see renderer.LogViewListRenderer#getListCellRendererComponent(javax.swing.JList, datas.LogData, int, boolean, boolean)
	 */
	@Test
	public void testGetListCellRendererSetRightForegroundColorSelections() {
		assertEquals(UIManager.getColor("List.selectionForeground"), _renderer.getListCellRendererComponent(null, createDataSet(LogData.WARNING), 1, true, false).getForeground());
	}
}
