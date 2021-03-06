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
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

import haushaltsbuch.comparators.CompInt;
import haushaltsbuch.datas.IdNameData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.dialogs.DlgInputChange;
import haushaltsbuch.elements.Desktop;
import haushaltsbuch.elements.StatusBar;
import haushaltsbuch.helper.HelperPrint;
import haushaltsbuch.tables.models.IdNameListModel;

/**
 * In diesen Dialog werden die einzelnen Kategorien angezeigt.
 * 
 * In Version 0.2 wird der eigene Eingabe-Dialog zum Einfügen oder Ändern einer
 * Kategorie benutzt.
 * 
 * In Version 0.3 wurde Methode <b>actionPerformed</b> gelöscht, da sie nicht
 * mehr braucht wird. Die Elemente vom Popup-Menü benutzen die Methoden 
 * {@link change}, {@link delete} und {@link insert}.
 * 
 * In Version 0.4 wird das Drucken der Kategorie-Tabelle unterstützt.
 * 
 * @author René Majewski
 * 
 * @version 0.4
 * @since 0.1
 */
public class WndCategoryList extends WndTableFrame implements Printable {

	/**
	 * Serialisation ID
	 */
	private static final long serialVersionUID = -3602076466416544711L;
	
	/**
	 * Speichert den Fenster-Titel
	 */
	public static final String WND_TITLE = "Kategorien";
	
	/**
	 * Initalisiert den Dialog und die Tabelle. Anschließend wird der
	 * Dialog angezeigt.
	 * 
	 * @param desktop Desktop des Hauptfensters
	 */
	public WndCategoryList(Desktop desktop) {
		// Dialog initalieren
		super(desktop);
		
		// Tabelle initalisieren
		IdNameListModel model = new IdNameListModel(DbController.queries().category().select());
		initTable(model);
		
		// Namen der Tabellen-Spalten
		_table.getColumnModel().getColumn(0).setHeaderValue("ID");
		_table.getColumnModel().getColumn(1).setHeaderValue("Kategorie");

		// Sorter initalisieren
		TableRowSorter<IdNameListModel> sorter = 
				new TableRowSorter<IdNameListModel>(model);
		sorter.setSortsOnUpdates(true);
		
		// Zusätzliche Comparatoren setzen
		sorter.setComparator(0, new CompInt());
		
		// Welche Liste soll beim Start sortiert sein?
		List<SortKey> sk = new ArrayList<SortKey>();
		sk.add(new SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sk);
		
		// Sorter in Tabelle einfügen
		_table.setRowSorter(sorter);

		// Titel
		setTitle(WND_TITLE);
		
		// Anzeigen
		pack();
		setVisible(true);
		
		// Kategorien können gedruckt werden
		setEnablePrint(true);
	}

	/**
	 * Wird aufgerufen, wenn auf eine Tabellen-Zeile doppelt geklickt wurde.
	 */
	@Override
	protected void tableRowDoubleClick() {
		if (_table.getSelectedRow() >= 0) {
			try {
				// Daten ermitteln
				IdNameData data = 
						((IdNameListModel)_table.getModel()).getRowDataAt(
								_table.getRowSorter().convertRowIndexToModel(
										_table.getSelectedRow()));
				
				// Kategorie ändern
				DlgInputChange dlg = new DlgInputChange(
						DlgInputChange.WND_CATEGORY, DlgInputChange.TYPE_CHANGE,
						data.getName(), _desktop.getMainWindow());
				String cc = null;
				if (dlg.getExit() == DlgInputChange.EXIT_OK)
					cc = dlg.getInput();
				if ((cc != null) && !cc.isEmpty() && (cc.compareTo(data.getName()) != 0)) {
					Statement stm = DbController.getInstance().createStatement();
					if (stm.executeUpdate(DbController.queries().category().update(data.getId(), cc)) > 0) {
						StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusUpdateOk(data.getId()));
					} else {
						StatusBar.getInstance().setMessageAsError(
								DbController.queries().category()
									.statusUpdateError(data.getId()), 
								new String());
					}
	
					// Tabelle neu zeichnen
					((IdNameListModel)_table.getModel()).dataRefresh(true);
				}
			} catch (SQLException e) {
				StatusBar.getInstance().setMessageAsError(
						DbController.statusDbError(), e);
			}
		}
	}

	/**
	 * Wird aufgerufen, wenn ein neuer Datensatz eingefügt werden soll.
	 */
	@Override
	public void insert() {
		IdNameListModel model = (IdNameListModel)_table.getModel();

		try {
			Statement stm = DbController.getInstance().createStatement();
				
			DlgInputChange dlg = new DlgInputChange(
					DlgInputChange.WND_CATEGORY, DlgInputChange.TYPE_INSERT,
					null, _desktop.getMainWindow());
			String nc = null;
			if (dlg.getExit() == DlgInputChange.EXIT_OK)
				nc = dlg.getInput();
			if (nc != null && !nc.isEmpty()) {
				ResultSet rs = stm.executeQuery(DbController.queries().category().search("name", nc));
				if (!rs.next()) {
					if (stm.executeUpdate(DbController.queries().category().insert(nc)) > 0) {
						StatusBar.getInstance().setMessageAsOk(DbController.queries().category().statusInsertOk());
					} else {
						StatusBar.getInstance().setMessageAsError(
								DbController.queries().category()
									.statusInsertError(), new String());
					}
				} else {
					StatusBar.getInstance().setMessage("Kategorie schon verhanden");
				}
	
				// Tabelle neu zeichnen
				model.dataRefresh(true);
			}
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(
					DbController.statusDbError(), e);
		}
	}

	/**
	 * Wird aufgerufen, wenn ein Datensatz geändert werden soll.
	 */
	@Override
	public void change() {
		tableRowDoubleClick();
	}

	/**
	 * Wird aufgerufen, wenn ein Datensatz gelöscht werden soll.
	 */
	@Override
	public void delete() {
		IdNameListModel model = (IdNameListModel)_table.getModel();
		
		System.out.println("WndCategoryList.delete() wurde aufgerufen"); 

		if (_table.getSelectedRow() > -1)
			delete(model.getRowDataAt(
					_table.getRowSorter().convertRowIndexToModel(
							_table.getSelectedRow())).getId(), 
					DbController.queries().category());
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
		int widthColumn2 = (int)pf.getImageableWidth() - widthColumn1;
		int height = HelperPrint.calcRowHeight(g);
		g.setFont(HelperPrint.standardBoldFont());
		HelperPrint.drawCell((int)pf.getImageableX(), (int)pf.getImageableY(), 
				widthColumn1, height, "ID", g);
		HelperPrint.drawCell((int)pf.getImageableX() + widthColumn1, 
				(int)pf.getImageableY(), widthColumn2, height, "Kategorie", g);
		
		g.setFont(HelperPrint.standardFont());
		
		// Kategorien ausgeben
		int max = 0;
		if ((count * (page + 1)) < _table.getRowCount())
			max = (count * (page + 1));
		else
			max = _table.getRowCount();
		
		for (int index = (count * page); index < max; index++)
		{
			int x1 = (int)pf.getImageableX();
			int x2 = (int)pf.getImageableX() + widthColumn1;
			int y = (int)pf.getImageableY() + 
					((index + 1 - (count * page)) * height);
			
			HelperPrint.drawCell(x1, y, widthColumn1, height, 
					String.valueOf(_table.getValueAt(index, 0)), g);
			HelperPrint.drawCell(x2, y, widthColumn2, height, 
					String.valueOf(_table.getValueAt(index, 1)), g);
		}
		
		// Seite kann gedruckt werden
		return Printable.PAGE_EXISTS;
	}
}
