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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.MoneyDetailsData;

/**
 * Testet die Klasse {@link haushaltsbuch.datas.MoneyDetailsData}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.1
 */
public class TestMoneyDetailsData {
	/**
	 * Speichert die ID
	 */
	private int _id;
	
	/**
	 * Speichert die ID des Datensatzes für Money
	 */
	private int _moneyId;
	
	/**
	 * Speichert die ID des Datensatzes für die Kategorie
	 */
	private int _categoryId;
	
	/**
	 * Speichert die ID des Datensatzes für das Geschäft.
	 */
	private int _sectionId;
	
	/**
	 * Speichert das Geld
	 */
	private double _money;
	
	/**
	 * Speichert die Beschreibung
	 */
	private String _comment;
	
	/**
	 * Speichert die Instanz der Daten-Klasse
	 */
	private MoneyDetailsData _data;

	/**
	 * Initalisiert die einzelnen Test.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_id = 100;
		_moneyId = 200;
		_categoryId = 300;
		_sectionId = 400;
		_money = 25.75;
		_comment = "Dies ist ein Test";
		_data = new MoneyDetailsData(_id, _moneyId, _categoryId, _sectionId, _money, _comment);
	}
	
	/**
	 * Testet ob die Klasse {@link haushaltsbuch.datas.MoneyDetailsData} eine Instanz von der
	 * Klasse {@link haushaltsbuch.datas.Data} ist.
	 */
	@Test
	public void testIdNameDataInstanceOfData() {
		assertTrue(_data instanceof Data);
	}
	
	/**
	 * Testet, ob der Konstruktor ohne Argumente richtig initalisiert
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#MoneyDetailsData()
	 */
	@Test
	public void testMoneyDetailsData() {
		_data = new MoneyDetailsData();
		assertEquals(-1, _data.getId());
		assertEquals(-1, _data.getMoneyId());
		assertEquals(-1, _data.getCategoryId());
		assertEquals(-1, _data.getSectionId());
		assertEquals(0.00, _data.getMoney(), 0.00);
		assertEquals(new String(), _data.getComment());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID richtig initalisiert.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#MoneyDetailsData(int)
	 */
	@Test
	public void testMoneyDetailsDataInt() {
		_data = new MoneyDetailsData(_id);
		assertEquals(_id, _data.getId());
		assertEquals(-1, _data.getMoneyId());
		assertEquals(-1, _data.getCategoryId());
		assertEquals(-1, _data.getSectionId());
		assertEquals(0.00, _data.getMoney(), 0.00);
		assertEquals(new String(), _data.getComment());
	}
	
	/**
	 * Testet, ob der Konstruktor mit allen übergebenen Daten richtig
	 * initalisiert.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#MoneyDetailsData(int, int, int, int, double, String)
	 */
	@Test
	public void testMoneyDetailsDataIntIntIntIntDoubleString() {
		assertEquals(_id, _data.getId());
		assertEquals(_moneyId, _data.getMoneyId());
		assertEquals(_categoryId, _data.getCategoryId());
		assertEquals(_sectionId, _data.getSectionId());
		assertEquals(_money, _data.getMoney(), 0.00);
		assertEquals(_comment, _data.getComment());
	}
	
	/**
	 * Testet, ob der Konstruktor mit allen übergebenen Daten richtig
	 * initalisiert, auch wenn als Beschreibung <b>null</b> übergebenen wird.
	 * So sollte als Beschreibunge eine leere Zeichenkette gespeichert werden.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#MoneyDetailsData(int, int, int, int, double, String)
	 */
	@Test
	public void testMoneyDetailsDataIntIntIntIntDoubleStringNullAsString() {
		_data = new MoneyDetailsData(_id, _moneyId, _categoryId, _sectionId, _money, null);
		assertEquals(_id, _data.getId());
		assertEquals(_moneyId, _data.getMoneyId());
		assertEquals(_categoryId, _data.getCategoryId());
		assertEquals(_sectionId, _data.getSectionId());
		assertEquals(_money, _data.getMoney(), 0.00);
		assertEquals(new String(), _data.getComment());
	}
	
	/**
	 * Testet, ob die ID des Money Datensatzes richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#getMoneyId()
	 */
	@Test
	public void testGetMoneyId() {
		assertEquals(_moneyId, _data.getMoneyId());
	}
	
	/**
	 * Testet, ob die ID des Money Datensatzes richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#setMoneyId(int)
	 */
	@Test
	public void testSetMoneyId() {
		int id = 2987;
		_data.setMoneyId(id);
		assertEquals(id, _data.getMoneyId());
	}
	
	/**
	 * Testet, ob die der Kategory richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#getCategoryId()
	 */
	@Test
	public void testGetCategoryId() {
		assertEquals(_categoryId, _data.getCategoryId());
	}
	
	/**
	 * Testet, ob die ID der Kategory richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#setCategoryId(int)
	 */
	@Test
	public void testSetCategoryId() {
		int id = 8376;
		_data.setCategoryId(id);
		assertEquals(id, _data.getCategoryId());
	}
	
	/**
	 * Testet, ob die ID des Geschäftes richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#getSectionId()
	 */
	@Test
	public void testGetSectionid() {
		assertEquals(_sectionId, _data.getSectionId());
	}
	
	/**
	 * Testet, ob die ID des Geschäftes richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#setSectionId(int)
	 */
	@Test
	public void testSetSectionId() {
		int id = 1579;
		_data.setSectionId(id);
		assertEquals(id, _data.getSectionId());
	}
	
	/**
	 * Testet, ob das Geld richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#getMoney()
	 */
	@Test
	public void testGetMoney() {
		assertEquals(_money, _data.getMoney(), 0.00);
	}
	
	/**
	 * Testet, ob das Geld richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#getMoney()
	 */
	@Test
	public void testSetMoney() {
		double money = 1098.76;
		_data.setMoney(money);
		assertEquals(money, _data.getMoney(), 0.00);
	}
	
	/**
	 * Testet, ob die Beschreibung richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#getComment()
	 */
	@Test
	public void testGetComment() {
		assertEquals(_comment, _data.getComment());
	}
	
	/**
	 * Testet, ob die Beschreibung richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#setComment(String)
	 */
	@Test
	public void testSetComment() {
		String comment = "Test897";
		_data.setComment(comment);
		assertEquals(comment, _data.getComment());
	}
	
	/**
	 * Testet, ob für die Beschreibung eine leere Zeichenkette gespeichert
	 * wird, <b>null</b> als Argument übergeben wird.
	 * 
	 * @see haushaltsbuch.datas.MoneyDetailsData#setComment(String)
	 */
	@Test
	public void testSetCommentNullAsParameter() {
		_data.setComment(null);
		assertEquals(new String(), _data.getComment());
	}
}
