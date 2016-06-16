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

import static org.junit.Assert.*;
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
import org.testsuite.helper.HelperCalendar;

import haushaltsbuch.datas.OutstandingData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Outstanding;
import haushaltsbuch.db.query.Queries;
import haushaltsbuch.db.query.Section;
import haushaltsbuch.helper.HelperNumbersOut;
import haushaltsbuch.tables.models.OutstandingListModel;

/**
 * Testet die Klasse {@link haushaltsbuch.tables.models.OutstandingListModel}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestOutstandingListModel {
	/**
	 * Speichert das Model
	 */
	private OutstandingListModel _model;
	
	/**
	 * Speichert die ID
	 */
	private int _id;
	
	/**
	 * Speichert die ID des Geschäftes
	 */
	private int _sectionId;
	
	/**
	 * Speichert den Betrag
	 */
	private double _money;
	
	/**
	 * Speichert die Anzahl der Raten
	 */
	private int _months;
	
	/**
	 * Speichert das Start-Datum
	 */
	private long _start;
	
	/**
	 * Speichert den Betrag der monatlichen Rate
	 */
	private double _monthMoney;
	
	/**
	 * Speichert den Kommentar.
	 */
	private String _comment;
	
	/**
	 * Speichert die Anzahl der Spalten
	 */
	private int _columnCount;
	
	/**
	 * Speichert die Instanz des DbController-Mock-Objektes
	 */
	private DbController _dbc;

	/**
	 * Initialisiert die einzelnen Tests
	 */
	@Before
	public void setUp() throws Exception {
		_id = 1;
		_money = 19.99;
		_monthMoney = 20.00;
		_months = 10;
		_sectionId = 1;
		_start = new Date().getTime();
		_comment = "Dies ist ein Test";
		
		_columnCount = 7;
		
		// ResultSets mocken
		ResultSet rs = mock(ResultSet.class);
		when(rs.next()).thenReturn(true, false);
		when(rs.getInt("id")).thenReturn(_id);
		when(rs.getInt("sectionid")).thenReturn(_sectionId);
		when(rs.getDouble("money")).thenReturn(_money);
		when(rs.getInt("months")).thenReturn(_months);
		when(rs.getLong("start")).thenReturn(_start);
		when(rs.getDouble("monthmoney")).thenReturn(_monthMoney);
		when(rs.getString("comment")).thenReturn(_comment);
		
		// Statment mocken
		Statement stm = mock(Statement.class);
		when(stm.executeQuery("test")).thenReturn(rs);
		
		// Schulden mocken
		Outstanding outstanding = mock(Outstanding.class);
		when(outstanding.select()).thenReturn("test");
		when(outstanding.getCloumnCount()).thenReturn(_columnCount);
		
		// Section mocken
		Section section = mock(Section.class);
		when(section.search("id", _sectionId)).thenReturn("section");
		
		// Queries mocken
		Queries queries = mock(Queries.class);
		when(queries.section()).thenReturn(section);
		when(queries.outstanding()).thenReturn(outstanding);
		
		// DbController mocken
		_dbc = mock(DbController.class);
		when(_dbc.createStatement()).thenReturn(stm);
		
		PowerMockito.mockStatic(DbController.class);
		PowerMockito.when(DbController.getInstance()).thenReturn(_dbc);
		PowerMockito.when(DbController.queries()).thenReturn(queries);
		
		_model = new OutstandingListModel();
	}

	/**
	 * Testet, ob die Anzahl der Spalten richtig wiedergegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.OutstandingListModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(_columnCount, _model.getColumnCount());
	}

	/**
	 * Testet, ob die Anzahl an Zeilen richtig wiedergegeben wurde.
	 * 
	 * @see haushaltsbuch.tables.models.OutstandingListModel#getRowCount()
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(1, _model.getRowCount());
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * die ID liefert, wenn Spalte 0 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithZeroAsColReturnId() {
		assertEquals(_id, _model.getValueAt(0, 0));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Namen des Geschäftes liefert, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnCategoryName() {
		try {
			// ResultSet mocken
			ResultSet rs = mock(ResultSet.class);
			when(rs.getString("name")).thenReturn("test2");
			
			//Statement
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("section")).thenReturn(rs);
			
			// DbController
			when(_dbc.createStatement()).thenReturn(stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals("test2", _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * die Höhe der Schulden liefert, wenn Spalte 2 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithTwoAsColReturnSectionName() {
		assertEquals(HelperNumbersOut.sum(_money), _model.getValueAt(0, 2));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * die Anzahl monatlicher Raten liefert, wenn Spalte 3 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithThreeAsColReturnMoney() {
		assertEquals(_months, _model.getValueAt(0, 3));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * das Datum der 1. Rate liefert, wenn Spalte 4 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFourAsColReturnComment() {
		assertEquals(HelperCalendar.dateToString(new Date().getTime()), 
				_model.getValueAt(0, 4));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Betrag der monatlichen Raten liefert, wenn Spalte 5 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFifeAsColReturnComment() {
		assertEquals(HelperNumbersOut.sum(_monthMoney), _model.getValueAt(0, 5));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Kommentar liefert, wenn Spalte 6 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithSixAsColReturnComment() {
		assertEquals(_comment, _model.getValueAt(0, 6));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig zurück gegeben
	 * wurden.
	 * 
	 * @see haushaltsbuch.tables.models.OutstandingListModel#getRowDataAt(int)
	 */
	@Test
	public void testGetRowDataAt() {
		OutstandingData data = (OutstandingData)_model.getRowDataAt(0);
		assertEquals(_id, data.getId());
		assertEquals(_sectionId, data.getSectionId());
		assertEquals(_money, data.getMoney(), 0.00);
		assertEquals(_months, data.getMonths());
		assertEquals(_start, data.getStart());
		assertEquals(_monthMoney, data.getMonthMoney(), 0.00);
		assertEquals(_comment, data.getComment());
	}

}
