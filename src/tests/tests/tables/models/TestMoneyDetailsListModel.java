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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.MoneyDetailsData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Category;
import haushaltsbuch.db.query.MoneyDetails;
import haushaltsbuch.db.query.Payment;
import haushaltsbuch.db.query.Queries;
import haushaltsbuch.db.query.Section;
import haushaltsbuch.helper.HelperNumbersOut;
import haushaltsbuch.tables.models.MoneyDetailsListModel;

/**
 * Testet die Klasse {@link haushaltsbuch.tables.models.MoneyDetailsListModel}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestMoneyDetailsListModel {
	/**
	 * Speichert das Model
	 */
	private MoneyDetailsListModel _model;
	
	/**
	 * Speichert die Money-ID
	 */
	private int _moneyId;
	
	/**
	 * Speichert die Kategorie
	 */
	private int _categoryId;
	
	/**
	 * Speichert das Geschäft
	 */
	private int _sectionId;
	
	/**
	 * Speichert den Betrag
	 */
	private double _money;
	
	/**
	 * Speichert die Beschreibung
	 */
	private String _comment;
	
	/**
	 * Speichert die Zahlungsmethode
	 */
	private int _paymentId;
	
	/**
	 * Speichert die Anzahl der Spalten
	 */
	private int _columnCount;
	
	/**
	 * Speichert die Instanz des DbController-Mock-Objektes
	 */
	private DbController _dbc;
	
	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_moneyId = 1;
		_categoryId = 1;
		_sectionId = 1;
		_money = 9.99;
		_comment = "Dies ist ein Test";
		_paymentId = 1;
		_columnCount = 6;

		try {
			// ResultSets mocken
			ResultSet rs = mock(ResultSet.class);
			when(rs.next()).thenReturn(true, false);
			when(rs.getInt("id")).thenReturn(1);
			when(rs.getInt("moneyid")).thenReturn(_moneyId);
			when(rs.getInt("categoryid")).thenReturn(_categoryId);
			when(rs.getInt("sectionid")).thenReturn(_sectionId);
			when(rs.getDouble("money")).thenReturn(_money);
			when(rs.getString("comment")).thenReturn(_comment);
			when(rs.getInt("paymentid")).thenReturn(_paymentId);
			
			// Statment mocken
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("test")).thenReturn(rs);
			
			// MoneyDetails mocken
			MoneyDetails details = mock(MoneyDetails.class);
			when(details.select(_moneyId)).thenReturn("test");
			when(details.getCloumnCount()).thenReturn(_columnCount);
			
			// Category mocken
			Category category = mock(Category.class);
			when(category.search("id", _categoryId)).thenReturn("category");
			
			// Section mocken
			Section section = mock(Section.class);
			when(section.search("id", _sectionId)).thenReturn("section");
			
			// Payment mocken
			Payment payment = mock(Payment.class);
			when(payment.search("id", _paymentId)).thenReturn("payment");
			
			// Queries mocken
			Queries queries = mock(Queries.class);
			when(queries.moneyDetails()).thenReturn(details);
			when(queries.category()).thenReturn(category);
			when(queries.section()).thenReturn(section);
			when(queries.payment()).thenReturn(payment);
			
			// DbController mocken
			_dbc = mock(DbController.class);
			when(_dbc.createStatement()).thenReturn(stm);
			
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(_dbc);
			PowerMockito.when(DbController.queries()).thenReturn(queries);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Model initalisieren
		_model = new MoneyDetailsListModel(_moneyId);
	}

	/**
	 * Testet, ob die Anzahl der Spalten richtig wiedergegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.MoneyDetailsListModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(_columnCount - 1, _model.getColumnCount());
	}

	/**
	 * Testet, ob die Anzahl an Zeilen richtig wiedergegeben wurde.
	 * 
	 * @see haushaltsbuch.tables.models.MoneyDetailsListModel#getRowCount()
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
		assertEquals(1, _model.getValueAt(0, 0));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Namen der Kategorie liefert, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnCategoryName() {
		try {
			// ResultSet mocken
			ResultSet rs = mock(ResultSet.class);
			when(rs.getString("name")).thenReturn("test");
			
			//Statement
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("category")).thenReturn(rs);
			
			// DbController
			when(_dbc.createStatement()).thenReturn(stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals("test", _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Namen des Geschäftes liefert, wenn Spalte 2 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithTwoAsColReturnSectionName() {
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
		
		assertEquals("test2", _model.getValueAt(0, 2));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Betrag liefert, wenn Spalte 3 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithThreeAsColReturnMoney() {
		assertEquals(HelperNumbersOut.sum(_money), _model.getValueAt(0, 3));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Kommentar liefert, wenn Spalte 4 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFourAsColReturnComment() {
		assertEquals(_comment, _model.getValueAt(0, 4));
	}

	/**
	 * Testet, ob {@link haushaltsbuch.tables.models.IdNameListModel#getValueAt(int, int)}
	 * die Zahlungsmethode liefert, wenn Spalte 4 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFifeAsColReturnComment() {
		try {
			// ResultSet mocken
			ResultSet rs = mock(ResultSet.class);
			when(rs.getString("name")).thenReturn("test3");
			
			//Statement
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("payment")).thenReturn(rs);
			
			// DbController
			when(_dbc.createStatement()).thenReturn(stm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals("test3", _model.getValueAt(0, 5));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig zurück gegeben
	 * wurden.
	 * 
	 * @see haushaltsbuch.tables.models.MoneyDetailsListModel#getRowDataAt(int)
	 */
	@Test
	public void testGetRowDataAt() {
		MoneyDetailsData data = _model.getRowDataAt(0);
		assertEquals(1, data.getId());
		assertEquals(_moneyId, data.getMoneyId());
		assertEquals(_categoryId, data.getCategoryId());
		assertEquals(_sectionId, data.getSectionId());
		assertEquals(_money, data.getMoney(), 0);
		assertEquals(_comment, data.getComment());
	}

}
