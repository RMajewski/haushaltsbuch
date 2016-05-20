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

package haushaltsbuch.windows.internal;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompDateString;
import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.comparators.CompSum;
import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.helper.HelperPrint;
import haushaltsbuch.menus.PopupMoneyList;
import haushaltsbuch.tables.models.MoneyListModel;

/**
 * In diesem Fenster werden die einzelnen Money-Datensätze angezeigt.
 * 
 * In Version 0.2 wurde Methode <b>actionPerformed</b> angepasst, da sie nur 
 * noch vom Popup-Menü-Element "Details" benutzt wird. Die restlichen Elemente
 * vom Popup-Menü benutzen die Methoden {@link change}, {@link delete} und
 * {@link insert}.
 * 
 * In Version 0.3 wird das Drucken der Tabelle unterstützt.
 * 
 * @author René Majewski
 * 
 * @version 0.3
 * @since 0.1
 */
public class WndMoneyList extends WndTableFrame 
		implements ActionListener, Printable {
	/**
	 * Speichert den Titel des Fensters.
	 */
	public static final String WND_TITLE = "Einnahmen und Ausgaben";

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Initalisiert das Fenster
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public WndMoneyList(Desktop desktop) {
		// Fenster initalisieren
		super(desktop);

		// Titel
		setTitle(WND_TITLE);
		
		// Popup-Menü initalisieren
		_popup = new PopupMoneyList(this, desktop.getToolBar());
		
		// Tabelle initalisieren
		MoneyListModel model = new MoneyListModel();
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Datum");
		_table.getColumnModel().getColumn(2).setHeaderValue("Was?");
		_table.getColumnModel().getColumn(3).setHeaderValue("Gesamt Betrag");
		_table.getColumnModel().getColumn(4).setHeaderValue("Beschreibung");

		// Sorter initalisieren
		TableRowSorter<MoneyListModel> sorter = 
				new TableRowSorter<MoneyListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		sorter.setComparator(1, new CompDateString());
		sorter.setComparator(3, new CompSum());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);
		
		// Details auf nicht Benutzbar setzen
		setPopupItemEnable(false);
		
		// Fenster anzeigen
		pack();
		setVisible(true);
		
		// Drucken wird unterstützt
		setEnablePrint(true);
	}
	
	/**
	 * Erzeugt das Fenster mit den Details und gibt ihm den Fokus.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() > -1) {
			MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow()));
			newWindow(new WndMoneyDetailsList(_desktop, data));
		}
	}
	
	/**
	 * Reagiert auf die einzelnen Einträge im Popup-Menü
	 * 
	 * param ae Event-Daten
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Welcher Menü-Eintrag wurde gedrückt?
		switch (ae.getActionCommand()) {
			// Details anzeigen
			case PopupMoneyList.DETAILS:
				tableRowDoubleClick();
				break;
		}
	}

	/**
	 * Wird aufgerufen, wenn ein Datensatz eingefügt werden soll.
	 */
	@Override
	public void insert() {
		newWindow(new WndMoneyChange(_desktop, null, this));
	}

	/**
	 * Wird aufgerufen, wenn der selektierte Datensatz geändert werden soll
	 */
	@Override
	public void change() {
		// Wurde ein Datensatz ausgewählt?
		if (_table.getSelectedRow() > -1) {
			// Datensatz ermitteln
			MoneyData data = ((MoneyListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow()));
			
			// Fenster zum ändern der Daten anzeigen
			newWindow(new WndMoneyChange(_desktop, data, this));
		}
	}

	/**
	 * Wird aufgerufen, wenn der selektierte Datensatz gelöscht werden soll.
	 */
	@Override
	public void delete() {
		// Wurde ein Datensatz ausgewählt?
		if (_table.getSelectedRow() > -1)
			delete(((MoneyListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow())).getId(), 
					DbController.queries().money());
	}
	
	/**
	 * Setzt die Einträge Ändern und Löschen des Stanard-Popup-Menü auf
	 * benutzbar (true) oder nicht benutzbar (false).
	 * 
	 * @param enable Standard-Popup-Einträge benutzbar?
	 */
	protected void setPopupItemEnable(boolean enable) {
		((PopupMoneyList)_popup).setMenuItemEnable(PopupMoneyList.VISIBLE_DETAILS, enable);
	}

	/**
	 * Druck die Kategorie-Tabelle aus.
	 * 
	 * @param g Grafik-Kontekt des Druckers
	 * 
	 * @param pf Seiten-Einstellungen
	 * 
	 * @param page Index der Seite
	 * 
	 * @return Gibt an, ob die Seite existiert oder nicht.
	 */
	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		int pageCount = HelperPrint.calcPageCount(_table.getRowCount(), 
				(int)pf.getImageableHeight(), g);
		int count = HelperPrint.calcRecordPerPage((int)pf.getImageableHeight(),
				g);
		
		// Überprüft, ob die Seite noch gedruckt werden kann oder nicht.
		if (page > (pageCount - 1))
			return Printable.NO_SUCH_PAGE;
		
		// Überschriften
		int widthColumn1 = HelperPrint.calcColumnWidth(6, g);
		int widthColumn2 = HelperPrint.calcColumnWidth(10, g);
		int widthColumn3 = HelperPrint.calcColumnWidth(9, g);
		int widthColumn4 = (int)pf.getImageableWidth() - widthColumn1 - 
				widthColumn2 -widthColumn3;
		int x1 = (int)pf.getImageableX();
		int x2 = x1 + widthColumn1;
		int x3 = x2 + widthColumn2;
		int x4 = x3 + widthColumn3;
		int height = HelperPrint.calcRowHeight(g);
		g.setFont(HelperPrint.standardBoldFont());
		HelperPrint.drawCell(x1, (int)pf.getImageableY(), widthColumn1, height,
				"ID", g);
		HelperPrint.drawCell(x2, (int)pf.getImageableY(), widthColumn2, height,
				"Datum", g);
		HelperPrint.drawCell(x3, (int)pf.getImageableY(), widthColumn3, height,
				"Was", g);
		HelperPrint.drawCell(x4, (int)pf.getImageableY(), widthColumn4, height,
				"gesamt", g);
		
		// Daten ausgeben
		g.setFont(HelperPrint.standardFont());
		int max = 0;
		if ((count * (page + 1)) < _table.getRowCount())
			max = (count * (page + 1));
		else
			max = _table.getRowCount();
		
		for (int index = (count * page); index < max; index++)
		{
			int y = (int)pf.getImageableY() + 
					((index + 1 - (count * page)) * height);
			
			HelperPrint.drawCell(x1, y, widthColumn1, height, 
					String.valueOf(_table.getValueAt(index, 0)), g);
			HelperPrint.drawCell(x2, y, widthColumn2, height, 
					String.valueOf(_table.getValueAt(index, 1)), g);
			HelperPrint.drawCell(x3, y, widthColumn3, height, 
					String.valueOf(_table.getValueAt(index, 2)), g);
			HelperPrint.drawCell(x4, y, widthColumn4, height, 
					String.valueOf(_table.getValueAt(index, 3)), g);
		}
		
		// Seite kann gedruckt werden
		return Printable.PAGE_EXISTS;
	}
}
