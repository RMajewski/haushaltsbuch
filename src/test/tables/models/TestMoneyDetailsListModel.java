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

package test.tables.models;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.MoneyDetailsData;
import db.DbController;
import tables.models.MoneyDetailsListModel;

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
	 * Initalisierungen für alle Tests
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	static public void beforeClass() throws Exception {
		System.setProperty("testing", "true");
	}
	
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

		DbController.getInstance().prepaireDatabase();
		try {
			Statement stm = DbController.getInstance().createStatement();
			stm.executeUpdate(DbController.queries().moneyDetails().insert(_moneyId, _categoryId, _sectionId, _money, _comment));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		_model = new MoneyDetailsListModel(_moneyId);
	}
	
	/**
	 * Lösche die Daten aus dem Speicher
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		DbController.getInstance().close();
	}

	/**
	 * Testet, ob die Anzahl der Spalten richtig wiedergegeben wird.
	 * 
	 * @see tables.models.MoneyDetailsModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(5, _model.getColumnCount());
	}

	/**
	 * Testet, ob die Anzahl an Zeilen richtig wiedergegeben wurde.
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(1, _model.getRowCount());
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
	 * den Namen der Kategorie liefert, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnCategoryName() {
		assertEquals("Lebensmittel", _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob {@link tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Namen des Geschäftes liefert, wenn Spalte 2 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithTwoAsColReturnSectionName() {
		assertEquals("jobcenter", _model.getValueAt(0, 2));
	}

	/**
	 * Testet, ob {@link tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Betrag liefert, wenn Spalte 3 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithThreeAsColReturnMoney() {
		assertEquals(_money, _model.getValueAt(0, 3));
	}

	/**
	 * Testet, ob {@link tables.models.IdNameListModel#getValueAt(int, int)}
	 * den Kommentar liefert, wenn Spalte 4 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFourAsColReturnComment() {
		assertEquals(_comment, _model.getValueAt(0, 4));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig zurück gegeben
	 * wurden.
	 * 
	 * @see tables.models.MoneyDetailsListModel#getRowDataAt(int)
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
