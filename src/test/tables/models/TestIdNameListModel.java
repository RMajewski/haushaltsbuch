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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.IdNameData;
import db.DbController;
import tables.models.IdNameListModel;

public class TestIdNameListModel {
	/**
	 * Speichert das Model
	 */
	private IdNameListModel _model;
	
	/**
	 * Initalisiert die Klasse
	 */
	@BeforeClass
	static public void beforClass() throws Exception {
		System.setProperty("testing", "true");
	}

	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		DbController.getInstance().prepaireDatabase();
		_model = new IdNameListModel(DbController.queries().category().select());
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
		assertEquals(14, _model.getRowCount());
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
	 * Testet, ob {@link tables.models.IdNameListModelk#getValueAt(int, int)}
	 * der Name geliefert wird, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnName() {
		assertEquals("Lebensmittel", _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig sind.
	 */
	@Test
	public void testGetRowDataAt() {
		IdNameData data = _model.getRowDataAt(0);
		assertEquals(1, data.getId());
		assertEquals("Lebensmittel", data.getName());
	}

}
