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
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datas.MoneyData;
import db.DbController;
import tables.models.MoneyListModel;

/**
 * Testet das Datenbank-Model {@link tables.models.MoneyListModel}
 * 
 * @author René Majewski
 */
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
		_date = new Date().getTime();
		_inout = true;
		_comment = "Dies ist ein Test";

		DbController.getInstance().prepaireDatabase();
		try {
			Statement stm = DbController.getInstance().createStatement();
			stm.executeUpdate(DbController.queries().money().insert(_date, _inout, _comment));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		_model = new MoneyListModel();
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
	 * @see tables.models.MoneyListModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(5, _model.getColumnCount());
	}

	/**
	 * Testet, ob die Anzahl an Zeilen richtig wiedergegeben wurde.
	 * 
	 * @see tables.models.MoneyListModel#getRowCount()
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(1, _model.getRowCount());
	}

	/**
	 * Testet, ob {@link tables.models.MoneyListModel#getValueAt(int, int)}
	 * die ID liefert, wenn Spalte 0 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithZeroAsColReturnId() {
		assertEquals(1, _model.getValueAt(0, 0));
	}

	/**
	 * Testet, ob {@link tables.models.MoneyListModel#getValueAt(int, int)}
	 * das Datum liefert, wenn Spalte 1 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithOneAsColReturnDate() {
		MoneyData data = new MoneyData(-1,_date, _inout, _comment);
		assertEquals(data.getDateAsString(), _model.getValueAt(0, 1));
	}

	/**
	 * Testet, ob {@link tables.models.MoneyListModel#getValueAt(int, int)}
	 * die Einnahme bzw die Ausgabe liefert, wenn Spalte 2 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithTwoAsColReturnInOut() {
		MoneyData data = new MoneyData(-1,_date, _inout, _comment);
		assertEquals(data.getInOutAsString(), _model.getValueAt(0, 2));
	}

	/**
	 * Testet, ob {@link tables.models.MoneyListModel#getValueAt(int, int)}
	 * die Beschreibung liefert, wenn Spalte 4 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithFourAsColReturnComment() {
		assertEquals(_comment, _model.getValueAt(0, 4));
	}

	/**
	 * Testet, ob {@link tables.models.MoneyListModel#getValueAt(int, int)}
	 * den Betrag liefert, wenn Spalte 3 ausgewählt ist.
	 */
	@Test
	public void testGetValueAtWithThreeAsColReturnMoney() {
		assertEquals(0.00, _model.getValueAt(0, 3));
	}

	/**
	 * Testet, ob die Daten der angegebenen Zeile richtig zurück gegeben
	 * wurden.
	 * 
	 * @see tables.models.MoneyListModel#getRowDataAt(int)
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
