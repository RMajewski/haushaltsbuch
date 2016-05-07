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

package tests.tests.datas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.datas.ReportCategoryData;
import haushaltsbuch.datas.ReportPreferencesData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Category;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Queries;
import tests.testcase.TestReports;

/**
 * Testet die Klasse {@link datas.ReportCategoryData}.
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestReportCategoryData extends TestReports {
	/**
	 * Instanz der Monats-Daten
	 */
	private ReportCategoryData _data;
	
	/**
	 * Speichert die Einnahmen
	 */
	private double _in;
	
	/**
	 * Speichert die Ausgaben
	 */
	private double _out;
	
	/**
	 * Speichert den Namen für die 1. Kategorie
	 */
	private String _cat1;
	
	/**
	 * Speichert den Namen für die 2. Kategorie
	 */
	private String _cat2;
	
	/**
	 * Speichert den Namen für die 3. Kategorie
	 */
	private String _cat3;
	
	/**
	 * Speichert den Mock für den DbController
	 */
	private DbController _dbc;

	/**
	 * Initalisiert die einzelnen Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Einstellungen mocken
		_year = 2016;
		_month = GregorianCalendar.JANUARY;
		_type = ReportPreferencesData.TYPE_MONTH;
		initPreferences();
		
		// Daten für die Mocks festlegen
		_in = 9.93;
		_out = 7.66;
		_cat1 = "Test1";
		_cat2 = "Test2";
		_cat3 = "Test3";
		
		// Kategorien mocken
		Category category = mock(Category.class);
		when(category.count()).thenReturn("category_count");
		when(category.sort("name")).thenReturn("category_sort");

		// MoneyDetails mocken
		MoneyDetails details = mock(MoneyDetails.class);
		when(details.sumCategoryId(_cat1, MoneyData.INT_INCOMING)).thenReturn(
				"sum_in");
		when(details.sumCategoryId(_cat1, MoneyData.INT_OUTGOING)).thenReturn(
				"sum_out");
		when(details.sumCategoryId(_cat2, MoneyData.INT_INCOMING)).thenReturn(
				"sum_0");
		when(details.sumCategoryId(_cat2, MoneyData.INT_OUTGOING)).thenReturn(
				"sum_0");
		when(details.sumCategoryId(_cat3, MoneyData.INT_INCOMING)).thenReturn(
				"sum_0");
		when(details.sumCategoryId(_cat3, MoneyData.INT_OUTGOING)).thenReturn(
				"sum_0");
		
		// Queries mocken
		Queries queries = mock(Queries.class);
		when(queries.category()).thenReturn(category);
		when(queries.moneyDetails()).thenReturn(details);
		
		// Statements, ResultSets und DbController-Mock
		try {
			// ResultSets mocken
			ResultSet rsEmpty = mock(ResultSet.class);
			when(rsEmpty.next()).thenReturn(false);
			
			ResultSet rsCount = mock(ResultSet.class);
			when(rsCount.getInt(1)).thenReturn(3, 3);
			
			ResultSet rsCats = mock(ResultSet.class);
			when(rsCats.next()).thenReturn(true, true, true, false);
			when(rsCats.getString("name")).thenReturn(_cat1, _cat2, _cat3);
			
			ResultSet rsIn = mock(ResultSet.class);
			when(rsIn.getDouble(1)).thenReturn(_in);
			
			ResultSet rsOut = mock(ResultSet.class);
			when(rsOut.getDouble(1)).thenReturn(_out);
			
			ResultSet rsSum0 = mock(ResultSet.class);
			when(rsSum0.getDouble(1)).thenReturn(0.0);
			
			// Statements mocken
			Statement stmCount = mock(Statement.class);
			when(stmCount.executeQuery("category_count")).thenReturn(rsCount);
			
			Statement stmCats = mock(Statement.class);
			when(stmCats.executeQuery("category_sort")).thenReturn(rsCats);
			
			Statement stmIn = mock(Statement.class);
			when(stmIn.executeQuery("sum_in")).thenReturn(rsIn);
			
			Statement stmOut = mock(Statement.class);
			when(stmOut.executeQuery("sum_out")).thenReturn(rsOut);
			
			Statement stmSum0 = mock(Statement.class);
			when(stmSum0.executeQuery("sum_0")).thenReturn(rsSum0);
			
			// DbController mocken
			_dbc = mock(DbController.class);
			when(_dbc.createStatement()).thenReturn(stmCount, stmCats, stmIn,
					stmOut, stmSum0, stmSum0, stmSum0, stmSum0);
			
			// Statische Methoden von DbController mocken
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(_dbc);
			PowerMockito.when(DbController.queries()).thenReturn(queries);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Instanz der Daten-Klassen erzeugen
		_data = new ReportCategoryData(_rpd);
	}
	
	/**
	 * Testet, ob die Klase {@link datas.ReportCategoryData} von der Klasse
	 * {@link datas.ReportData} abgeleitet wurde.
	 */
	@Test
	public void testReportCategoryDataExtendsReportData() {
		assertEquals("haushaltsbuch.datas.ReportData", 
				_data.getClass().getSuperclass().getName());
	}
	
	/**
	 * Überprüft, ob die Richtige Anzahl an Spalten zurück gegeben werden.
	 * 
	 * @see datas.ReportCategoryData#getColumnCount()
	 */
	@Test
	public void testGetColumnCountReturnRightCount() {
		assertEquals(4, _data.getColumnCount());
	}
	
	/**
	 * Überprüft, ob die richtige Anzahl an Zeilen zurück gegeben werden.
	 * @throws SQLException 
	 * 
	 * @see datas.ReportCategoryData#getRowCount()
	 */
	@Test
	public void testGetRowCount() throws SQLException {
		// Tests
		assertEquals(3, _data.getRowCount());
	}
	
	/**
	 * Überprüft, ob die richtigen Spalten-Namen gesetzt werden.
	 * 
	 * @see datas.ReportCategoryData#setColumnHeader(javax.swing.table.TableColumnModel)
	 */
	@Test
	public void testSetColumnHeader() {
		// Mock-Objekte vorbereiten
		TableColumn tc = mock(TableColumn.class);
		TableColumnModel tcm = mock(TableColumnModel.class);
		when(tcm.getColumn(0)).thenReturn(tc);
		when(tcm.getColumn(1)).thenReturn(tc);
		when(tcm.getColumn(2)).thenReturn(tc);
		when(tcm.getColumn(3)).thenReturn(tc);
		InOrder order = inOrder(tc);
		
		// Spalten-Überschriften setzen
		_data.setColumnHeader(tcm);
		
		// Überprüfen, ob die entsprechenden Methoden aufgerufen wurden
		verify(tcm, times(1)).getColumn(0);
		verify(tcm, times(1)).getColumn(1);
		verify(tcm, times(1)).getColumn(2);
		verify(tcm, times(1)).getColumn(3);
		verify(tcm, never()).getColumn(4);
		order.verify(tc, times(1)).setHeaderValue("Kategorie");
		order.verify(tc, times(1)).setHeaderValue("Einnahmen");
		order.verify(tc, times(1)).setHeaderValue("Ausgaben");
		order.verify(tc, times(1)).setHeaderValue("Differenz");
	}
	
	/**
	 * Überprüft, ob die richtigen Kategorien zurück gegeben werden.
	 * 
	 * @see datas.ReportCategoryData#getCategory(int)
	 */
	@Test
	public void testGetCategory() {
		assertEquals(_cat1, _data.getCategory(0));
		assertEquals(_cat2, _data.getCategory(1));
		assertEquals(_cat3, _data.getCategory(2));
	}
	
	/**
	 * Überprüft, ob für die 1. Kategorie Einnahmen bestehen.
	 * 
	 * @see datas.ReportCategoryData#incoming(int)
	 */
	@Test
	public void testIncomingWithCategoryOneReturnRight() {
		assertEquals(_in, _data.incoming(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die restlichen Kategorien keine Einnahmen bestehen.
	 * 
	 * @see datas.ReportCategoryData#incoming(int)
	 */
	@Test
	public void testIncomingWithCategoryTwoAndThreelveReturnZero() {
		assertEquals(0.0, _data.incoming(1), 0.0);
		assertEquals(0.0, _data.incoming(2), 0.0);
	}
	
	/**
	 * Überprüft, ob für die 1. Kategorie Ausgaben bestehen.
	 * 
	 * @see datas.ReportCatgoryData#outgoing(int)
	 */
	@Test
	public void testOutgoingWithCategoryOneReturnRight() {
		assertEquals(_out, _data.outgoing(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die Tage 2 bis 31 keine Ausgaben bestehen.
	 * 
	 * @see datas.ReportCategoryData#outgoing(int)
	 */
	@Test
	public void testOutgoingWithCategoryTwoAntThreeReturnZero() {
		assertEquals(0.0, _data.outgoing(1), 0.0);
		assertEquals(0.0, _data.outgoing(2), 0.0);
	}
	
	/**
	 * Überprüft, ob für die 1. Kategorie die Differenz richtig ist.
	 * 
	 * @see datas.ReportCategoryData#deviation(int)
	 */
	@Test
	public void testDeviationWithCategoryOneReturnRight() {
		assertEquals(_in - _out, _data.deviation(0), 0.0);
	}
	
	/**
	 * Überprüft, ob für die restlichen Kategorie die Differenz 0.00 ist.
	 * 
	 * @see datas.ReportCategoryData#deviation(int)
	 */
	@Test
	public void testDeviationWithCategoryTwoAndThreeReturnZero() {
		assertEquals(0.0, _data.deviation(1), 0.0);
		assertEquals(0.0, _data.deviation(2), 0.0);
	}
}
