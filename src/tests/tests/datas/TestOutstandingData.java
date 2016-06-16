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

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.Data;
import haushaltsbuch.datas.OutstandingData;

/**
 * Testet die Klasse {@link haushaltsbuch.datas.OutstandingData}
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.4
 */
public class TestOutstandingData {
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
	 * Speichert die Instanz der Daten
	 */
	private OutstandingData _data;

	/**
	 * Initialisiert die einzelnen Test.
	 */
	@Before
	public void setUp() throws Exception {
		_id = 100;
		_sectionId = 200;
		_money = 39.78;
		_monthMoney = 99.32;
		_months = 12;
		_start = (new GregorianCalendar(2016, Calendar.DECEMBER, 12)).getTimeInMillis();
		_comment = "Dies ist ein Test";
		_data = new OutstandingData(_id, _sectionId, _money, _months, _start, 
				_monthMoney, _comment);
	}
	
	/**
	 * Testet ob die Klasse {@link haushaltsbuch.datas.OutstandingData} eine
	 * Instanz von der Klasse {@link haushaltsbuch.datas.Data} ist.
	 */
	@Test
	public void testDataInstanceOfData() {
		assertTrue(_data instanceof Data);
	}

	/**
	 * Testet, ob der Konstruktor ohne Argumente richtig initialisiert
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#OutstandingData()
	 */
	@Test
	public void testOutstandingData() {
		_data = new OutstandingData();
		assertEquals(-1, _data.getId());
		assertEquals(-1, _data.getSectionId());
		assertEquals(0.0, _data.getMoney(), 0.00);
		assertEquals(-1, _data.getMonths());
		assertEquals(0l, _data.getStart());
		assertEquals(0.0, _data.getMonthMoney(), 0.00);
		assertEquals(new String(), _data.getComment());
	}

	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID richtig initialisiert.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#OutstandingData(int)
	 */
	@Test
	public void testOutstandingDataInt() {
		_data = new OutstandingData(_id);
		assertEquals(_id, _data.getId());
		assertEquals(-1, _data.getSectionId());
		assertEquals(0.0, _data.getMoney(), 0.00);
		assertEquals(-1, _data.getMonths());
		assertEquals(0l, _data.getStart());
		assertEquals(0.0, _data.getMonthMoney(), 0.00);
		assertEquals(new String(), _data.getComment());
	}

	/**
	 * Testet, ob der Konstruktor mit allen übergebenen Daten richtig
	 * initalisiert.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#OutstandingData(int, int, double, int, long, double, String)
	 */
	@Test
	public void testOutstandingDataIntIntDoubleIntLongDoubleString() {
		assertEquals(_id, _data.getId());
		assertEquals(_sectionId, _data.getSectionId());
		assertEquals(_money, _data.getMoney(), 0.00);
		assertEquals(_months, _data.getMonths());
		assertEquals(_start, _data.getStart());
		assertEquals(_monthMoney, _data.getMonthMoney(), 0.00);
		assertEquals(_comment, _data.getComment());
	}

	/**
	 * Testet, ob die Beschreibung richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getComment()
	 */
	@Test
	public void testGetComment() {
		assertEquals(_comment, _data.getComment());
	}

	/**
	 * Testet, ob die Beschreibung richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#setComment(String)
	 */
	@Test
	public void testSetComment() {
		_comment = "Ein zweiter Test";
		_data.setComment(_comment);
		assertEquals(_comment, _data.getComment());
	}
	
	/**
	 * Testet, ob für die Beschreibung eine leere Zeichenkette gespeichert
	 * wird, wenn <b>null</b> als Argument übergeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#setComment(String)
	 */
	@Test
	public void testSetCommentNullAsParameter() {
		_data.setComment(null);
		assertEquals(new String(), _data.getComment());
	}
	
	/**
	 * Testet, ob das Geld richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getMoney()
	 */
	@Test
	public void testGetMoney() {
		assertEquals(_money, _data.getMoney(), 0.00);
	}
	
	/**
	 * Testet, ob das Geld richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getMoney()
	 */
	@Test
	public void testSetMoney() {
		double money = 1098.76;
		_data.setMoney(money);
		assertEquals(money, _data.getMoney(), 0.00);
	}

	/**
	 * Testet ob der Betrag der monatlichen Raten richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getMonthMoney()
	 */
	@Test
	public void testGetMonthMoney() {
		assertEquals(_monthMoney, _data.getMonthMoney(), 0.00);
	}

	/**
	 * Testet, ob der Betrag der monatlichen Raten richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#setMonthMoney(double)
	 */
	@Test
	public void testSetMonthMoney() {
		double money = 198.88;
		_data.setMonthMoney(money);
		assertEquals(money, _data.getMonthMoney(), 0.00);
	}
	
	/**
	 * Testet, ob die ID des Geschäftes richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getSectionId()
	 */
	@Test
	public void testGetSectionid() {
		assertEquals(_sectionId, _data.getSectionId());
	}
	
	/**
	 * Testet, ob die ID des Geschäftes richtig übernommen wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#setSectionId(int)
	 */
	@Test
	public void testSetSectionId() {
		int id = 1579;
		_data.setSectionId(id);
		assertEquals(id, _data.getSectionId());
	}

	/**
	 * Testet, ob das Datum der 1. Rate richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getStart()
	 */
	@Test
	public void testGetStart() {
		assertEquals(_start, _data.getStart());
	}

	/**
	 * Testet, ob das Datum der 1. Rate als Zeichnkette richtig erstellt wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getStartAsString()
	 */
	@Test
	public void testGetStartAsString() {
		assertEquals("12.12.2016", _data.getStartAsString());
	}

	/**
	 * Testet, ob das Datum der 1. Rate richtig gesetzt wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#setStart(long)
	 */
	@Test
	public void testSetStart() {
		long date = 1460387339811l;
		_data.setStart(date);
		assertEquals(date, _data.getStart());
	}

	/**
	 * Testet, ob die Anzahl der monatlichen Raten richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#getMonths()
	 */
	@Test
	public void testGetMonths() {
		assertEquals(_months, _data.getMonths());
	}

	/**
	 * Testet, ob die Anzahl der monatlichen Raten richtig gesetzt wird.
	 * 
	 * @see haushaltsbuch.datas.OutstandingData#setMonths(int)
	 */
	@Test
	public void testSetMonths() {
		int months = 4;
		_data.setMonths(months);
		assertEquals(months, _data.getMonths());
	}

}
