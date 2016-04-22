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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import haushaltsbuch.datas.IdNameData;
import haushaltsbuch.db.DbController;
import haushaltsbuch.db.query.Category;
import haushaltsbuch.tables.models.IdNameListModel;

/**
 * Testet die Klasse {@link tables.models.IdNameListModel} 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest({DbController.class})
public class TestIdNameListModel {
	/**
	 * Speichert das Model
	 */
	private IdNameListModel _model;
	
	/**
	 * Speicher die Anzahl der Datensätze
	 */
	private int _count;

	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			// ResultSets mocken
			ResultSet rs = mock(ResultSet.class);
			when(rs.next()).thenReturn(true, true, true, false);
			when(rs.getString("name")).thenReturn("test 1", "test 2", "test 3");
			when(rs.getInt("id")).thenReturn(1, 2, 3);
			
			// Statment mocken
			Statement stm = mock(Statement.class);
			when(stm.executeQuery("test")).thenReturn(rs);
			
			// DbController mocken
			DbController dbc = mock(DbController.class);
			when(dbc.createStatement()).thenReturn(stm);
			
			PowerMockito.mockStatic(DbController.class);
			PowerMockito.when(DbController.getInstance()).thenReturn(dbc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Anzahl Datensätze speichern
		_count = 3;
		
		// Model initalisieren
		_model = new IdNameListModel("test");
	}

	/**
	 * Testet, ob die Anzahl der Spalten richtig gesetzt ist.
	 * 
	 * @see tables.models.IdNameListModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(2, _model.getColumnCount());
	}

	/**
	 * Testet, ob die anzahl der Zeile richtig wiedergeben wird.
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(_count, _model.getRowCount());
	}

	/**
	 * Testet, ob {@link tables.models.IdNameListModel#getValueAt(int, int)}
	 * die ID liefert, wenn Spalte 0 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithZeroAsColReturnId() {
		assertEquals(1, _model.getValueAt(0, 0));
	}
	
	/**
	 * Testet, ob {@link tables.models.IdNameListModel#getValueAt(int, int)}
	 * der Name geliefert wird, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnName() {
		assertEquals("test 1", _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig sind.
	 */
	@Test
	public void testGetRowDataAt() {
		IdNameData data = _model.getRowDataAt(0);
		assertEquals(1, data.getId());
		assertEquals("test 1", data.getName());
	}

}
