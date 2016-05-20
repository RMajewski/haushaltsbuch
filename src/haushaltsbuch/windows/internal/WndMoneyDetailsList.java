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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.comparators.CompSum;
import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.MoneyDetailsData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.helper.HelperPrint;
import haushaltsbuch.tables.models.MoneyDetailsListModel;

/**
 * In diesem Fenster wird zum angegeben Datensatz aus der Tabelle 'money' die
 * Detail-Datensätze als Liste angezeigt.
 * 
 * In Version 0.2 wurde Methode <b>actionPerformed</b> gelöscht, da sie nicht
 * mehr gebraucht wird. Die Elemente vom Popup-Menü benutzen die Methoden 
 * {@link change}, {@link delete} und {@link insert}.
 * 
 * In Version 0.3 wird das Drucken der Tabelle unterstützt.
 * 
 * @author René Majewski
 * 
 * @version 0.3
 * @since 0.1
 */
public class WndMoneyDetailsList extends WndTableFrame implements Printable {

	/**
	 * Serilisation ID
	 */
	private static final long serialVersionUID = 8514536537238375532L;
	
	/**
	 * Speichert den Datensatz aus der Tabelle 'money'
	 */
	private MoneyData _money;

	/**
	 * Initalisiert das Fenster und speichert die Daten des angegebenen
	 * Money-Datensatzes.
	 * 
	 * @param desktop Dekstop des Hauptfensters.
	 * 
	 * @param money Zugehöriger Money-Datensatz
	 */
	public WndMoneyDetailsList(Desktop desktop, MoneyData money) {
		// Klasse initalisiern
		super(desktop);
		
		// Datsatz aus 'money' speichern
		_money = money;
		
		// Titel des Fensters
		setTitle("Details zur " + _money.getInOutAsString() + " vom " +
				_money.getDateAsString());
		
		// Größe
		setSize(1000, 400);
		setMinimumSize(new Dimension(1000, 400));
		
		// Tabelle initalisieren
		MoneyDetailsListModel model = new MoneyDetailsListModel(_money.getId());
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");
		_table.getColumnModel().getColumn(2).setHeaderValue("Geschäft");
		_table.getColumnModel().getColumn(3).setHeaderValue("Betrag");
		_table.getColumnModel().getColumn(4).setHeaderValue("Beschreibung");

		// Sorter initalisieren
		TableRowSorter<MoneyDetailsListModel> sorter = 
				new TableRowSorter<MoneyDetailsListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		sorter.setComparator(3, new CompSum());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);
		
		// Fenster anzeigen
		//pack();
		setVisible(true);
		
		// Drucken unterstützen
		setEnablePrint(true);
	}
	
	/**
	 * Gibt die Tabelle zurück.
	 * 
	 * @return Tabelle
	 */
	@Override
	public JTable getTable() {
		return _table;
	}

	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() > -1)
			newWindow(new WndMoneyDetailsChange(_desktop, 
					((MoneyDetailsListModel)_table.getModel()).getRowDataAt(
							_table.getRowSorter().convertRowIndexToModel(
									_table.getSelectedRow())), this));
	}

	/**
	 * Wird auf gerufen, wenn ein neuer Datensatz eingefügt werden soll.
	 */
	@Override
	public void insert() {
		MoneyDetailsData data = new MoneyDetailsData(-1);
		data.setMoneyId(_money.getId());
		newWindow(new WndMoneyDetailsChange(_desktop, data, this));
	}

	/**
	 * Wird aufgerufen, wenn ein selektierter Datensatz geändert werden soll.
	 */
	@Override
	public void change() {
		tableRowDoubleClick();
	}

	/**
	 * Wird aufgerufen, wenn ein selektierter Datensatz gelöscht werden soll.
	 */
	@Override
	public void delete() {
		// Wurde ein Datensatz ausgewählt?
		if (_table.getSelectedRow() > -1)
			delete(((MoneyDetailsListModel)_table.getModel()).getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow())).getId(), 
					DbController.queries().moneyDetails());
	}

	/**
	 * Druck die Tabelle aus.
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
		int widthColumn3 = HelperPrint.calcColumnWidth(12, g);
		int widthColumn4 = HelperPrint.calcColumnWidth(12, g);
		int widthColumn5 = (int)pf.getImageableWidth() - widthColumn1 - 
				widthColumn2 -widthColumn3 - widthColumn4;
		int x1 = (int)pf.getImageableX();
		int x2 = x1 + widthColumn1;
		int x3 = x2 + widthColumn2;
		int x4 = x3 + widthColumn3;
		int x5 = x4 + widthColumn4;
		int height = HelperPrint.calcRowHeight(g);
		g.setFont(HelperPrint.standardBoldFont());
		HelperPrint.drawCell(x1, (int)pf.getImageableY(), widthColumn1, height,
				"ID", g);
		HelperPrint.drawCell(x2, (int)pf.getImageableY(), widthColumn2, height,
				"Datum", g);
		HelperPrint.drawCell(x3, (int)pf.getImageableY(), widthColumn3, height,
				"Kategorie", g);
		HelperPrint.drawCell(x4, (int)pf.getImageableY(), widthColumn4, height,
				"Geschäft", g);
		HelperPrint.drawCell(x5, (int)pf.getImageableY(), widthColumn5, height,
				"Betrag", g);
		
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
					String.valueOf(_money.getDateAsString()), g);
			HelperPrint.drawCell(x3, y, widthColumn3, height, 
					String.valueOf(_table.getValueAt(index, 1)), g);
			HelperPrint.drawCell(x4, y, widthColumn4, height, 
					String.valueOf(_table.getValueAt(index, 2)), g);
			HelperPrint.drawCell(x5, y, widthColumn5, height, 
					String.valueOf(_table.getValueAt(index, 3)), g);
		}
		
		// Seite kann gedruckt werden
		return Printable.PAGE_EXISTS;
	}
}
