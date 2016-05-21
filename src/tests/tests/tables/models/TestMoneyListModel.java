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

package tests.tests.tables.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.MoneyData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Money;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Queries;
import haushaltsbuch.helper.HelperNumbersOut;
import haushaltsbuch.tables.models.MoneyListModel;

/**
 * Testet das Datenbank-Model {@link haushaltsbuch.tables.models.MoneyListModel}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestMoneyListModel {
	/**
	 * Speichert das Model
	 */
	private MoneyListModel _model;
	
	/**
	 * Speichert das Datum
	 */
	private long _date;
	
	/**
	 * Speichert ob es eine Einnahme oder eine Aussgabe ist.
	 */
	private boolean _inout;
	
	/**
	 * Speichert die Beschreibung
	 */
	private String _comment;
	
	/**
	 * Speichert die Instanz des DbController-Mock-Objektes
	 */
	private DbController _dbc;
	
	/**
	 * Speichert die Anzahl der Spalten
	 */
	private int _columnCount;
	
	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_date = new Date().getTime();
		_inout = MoneyData.INCOMING;
		_comment = "Dies ist ein Test";
		_columnCount = 43;

		try {
			// ResultSets mocken
			ResultSet rs = mock(ResultSet.class);
			when(rs.next()).thenReturn(true, false);
			when(rs.getInt("id")).thenReturn(1);
			when(rs.getLong("date")).thenReturn(_date);
			when(rs.getBoolean("inout")).thenReturn(_inout);
			when(rs.getString("comment")).thenReturn(_comment);
			
			// Statment mocken
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("test")).thenReturn(rs);
			
			// Money mocken
			Money money = mock(Money.class);
			when(money.select()).thenReturn("test");
			when(money.getCloumnCount()).thenReturn(_columnCount);
			
			// MonayDetails mocken
			MoneyDetails details = mock(MoneyDetails.class);
			when(details.sumMoneyId(1)).thenReturn("sum");
			
			// Queries mocken
			Queries queries = mock(Queries.class);
			when(queries.money()).thenReturn(money);
			when(queries.moneyDetails()).thenReturn(details);
			
			// DbController mocken
			_dbc = mock(DbController.class);
			when(_dbc.createStatement()).thenReturn(stm);
			
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(_dbc);
			PowerMockito.when(DbController.queries()).thenReturn(queries);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		_model = new MoneyListModel();
	}

	/**
	 * Testet, ob die Anzahl der Spalten richtig wiedergegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.MoneyListModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(_columnCount + 1, _model.getColumnCount());
	}

	/**
	 * Testet, ob die Anzahl an Zeilen richtig wiedergegeben wurde.
	 * 
	 * @see haushaltsbuch.tables.models.MoneyListModel#getRowCount()
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(1, _model.getRowCount());
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.MoneyListModel#getValueAt(int, int)}
	 * die ID liefert, wenn Spalte 0 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithZeroAsColReturnId() {
		assertEquals(1, _model.getValueAt(0, 0));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.MoneyListModel#getValueAt(int, int)}
	 * das Datum liefert, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnDate() {
		MoneyData data = new MoneyData(-1,_date, _inout, _comment);
		assertEquals(data.getDateAsString(), _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.MoneyListModel#getValueAt(int, int)}
	 * die Einnahme bzw die Ausgabe liefert, wenn Spalte 2 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithTwoAsColReturnInOut() {
		MoneyData data = new MoneyData(-1,_date, _inout, _comment);
		assertEquals(data.getInOutAsString(), _model.getValueAt(0, 2));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.MoneyListModel#getValueAt(int, int)}
	 * die Beschreibung liefert, wenn Spalte 4 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFourAsColReturnComment() {
		assertEquals(_comment, _model.getValueAt(0, 4));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.MoneyListModel#getValueAt(int, int)}
	 * den Betrag liefert, wenn Spalte 3 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithThreeAsColReturnMoney() {
		double sum = 67.88;
		
		try {
			// ResultSet
			ResultSet rs = mock(ResultSet.class);
			when(rs.getDouble(1)).thenReturn(sum);
			
			// Statement
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("sum")).thenReturn(rs);
			
			// In DbController einfügen
			when(_dbc.createStatement()).thenReturn(stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(HelperNumbersOut.sum(sum), _model.getValueAt(0, 3));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig zurück gegeben
	 * wurden.
	 * 
	 * @see haushaltsbuch.tables.models.MoneyListModel#getRowDataAt(int)
	 */
	@Test
	public void testGetRowDataAt() {
		MoneyData data = _model.getRowDataAt(0);
		assertEquals(1, data.getId());
		assertEquals(_date, data.getDate());
		assertEquals(_inout, data.getInOut());
		assertEquals(_comment, data.getComment());
	}

}
