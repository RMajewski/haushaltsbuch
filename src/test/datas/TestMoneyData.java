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

package test.datas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import datas.Data;
import datas.MoneyData;

/**
 * Testet die Klasse {@link datas.MoneyData}
 * 
 * @author René Majewski
 */
public class TestMoneyData {
	/**
	 * Speichert die Instanz der Datan-Klasse
	 */
	private MoneyData _data;
	
	/**
	 * Speichert die ID
	 */
	private int _id;
	
	/**
	 * Speichert das Datum
	 */
	private long _date;
	
	/**
	 * Speichert ob es sich um eine Ein- oder Ausgabe handelt.
	 */
	private boolean _inout;
	
	/**
	 * Speichert den Kommentar
	 */
	private String _comment;

	/**
	 * Initalisiert die einzelnen Tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_id = 100;
		_date = (new GregorianCalendar( 2016, Calendar.APRIL, 8 )).getTimeInMillis();
		_inout = true;
		_comment = "Dies ist ein Test!";
		_data = new MoneyData(_id, _date, _inout, _comment);
	}
	
	/**
	 * Testet ob die Klasse {@link datas.MoneyData} eine Instanz von der
	 * Klasse {@link datas.Data} ist.
	 */
	@Test
	public void testIdNameDataInstanceOfData() {
		assertTrue(_data instanceof Data);
	}
	
	/**
	 * Testet, ob der Konstruktor ohne Argumente richtig initalisiert.
	 * 
	 * @see datas.MoneyData#MoneyData()
	 */
	@Test
	public void testMoneyData() {
		_data = new MoneyData();
		assertEquals(-1, _data.getId());
		assertEquals(-1, _data.getDate());
		assertEquals(false, _data.getInOut());
		assertEquals(new String(), _data.getComment());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen ID richtig initalisiert.
	 * 
	 * @see datas.MoneyData#MoneyData(int)
	 */
	@Test
	public void testMoneyDataInt() {
		_data = new MoneyData(_id);
		assertEquals(_id, _data.getId());
		assertEquals(-1, _data.getDate());
		assertEquals(false, _data.getInOut());
		assertEquals(new String(), _data.getComment());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen Id, des übergebenen
	 * Datums, der übergebenen Ein- oder Auszahlung und der übergebenen
	 * Beschreibung richtig initalisiert.
	 * 
	 * @see datas.MoneyData#MoneyData(int, long, boolean, String)
	 */
	@Test
	public void testMoneyDataIntDateBooleanString() {
		assertEquals(_id, _data.getId());
		assertEquals(_date, _data.getDate());
		assertEquals(_inout, _data.getInOut());
		assertEquals(_comment, _data.getComment());
	}
	
	/**
	 * Testet, ob der Konstruktor mit der übergebenen Id, des übergebenen
	 * Datums, der übergebenen Ein- oder Auszahlung und <b>null</b> als
	 * Beschreibung richtig initalisiert.
	 * 
	 * @see datas.MoneyData#MoneyData(int, long, boolean, String)
	 */
	@Test
	public void testMoneyDataInitDateBooleanStringNullAsString() {
		_data = new MoneyData(_id, _date, _inout, null);
		assertEquals(_id, _data.getId());
		assertEquals(_date, _data.getDate());
		assertEquals(_inout, _data.getInOut());
		assertEquals(new String(), _data.getComment());
	}

	/**
	 * Testet, ob das Datum richtig zurück gegeben wird.
	 * 
	 * @see datas.MoneyData#getDate()
	 */
	@Test
	public void testGetDate() {
		assertEquals(_date, _data.getDate());
	}
	
	/**
	 * Testet, ob das Datum als Zeichenkette richtig zurück gegeben wird.
	 * 
	 * @see datas.MoneyData#getDateAsString()
	 */
	@Test
	public void testGetDateAsString() {
		_data.setDate(1460387339811l);
		assertEquals("11.04.2016", _data.getDateAsString());
	}
	
	/**
	 * Testet, ob das Datum richtig übernommen wird.
	 * 
	 * @see datas.MoneyData#setDate(long)
	 */
	 @Test
	 public void testSetDateLong() {
		 long date = 1345918385878L;
		 _data.setDate(date);
		 assertEquals(date, _data.getDate());
	 }
	 
	 /**
	  * Testet, ob aus einer Zeichenkette mit einem Datum der long-Wert richtig
	  * ermittelt werden kann.
	  * 
	  * @see datas.MoneyData#setDate(String)
	  */
	 @Test
	 public void testSetDateString() {
		 _data.setDate("01.01.2016");
		 assertEquals(1451602800000l, _data.getDate(), 0);
	 }
	 
	 /**
	  * Teste, die Methode {@link datas.MoneyData#setDate(String)} das aktuelle
	  * Datum speichert, wenn <b>null</b> als Zeichenkette übergeben wird.
	  */
	 @Test
	 public void testSetDateStringWithNull() {
		 _data.setDate(null);
		 assertEquals(new Date().getTime(), _data.getDate(), 100);
	 }
	 
	 /**
	  * Testet, ob die Methode {@link datas.MoneyData#setDate(String)} auch
	  * das ermittelte Datum als long-Wert zurück gibt.
	  */
	 @Test
	 public void testSetDateStringReturnIsRight() {
		 assertEquals(1451602800000l, _data.setDate("01.01.2016"), 0);
	 }
	 
	 /**
	  * Testet, ob die Ein- oder Auszahlung richtig zurück gegeben wird.
	  * 
	  * @see datas.MoneyData#getInOut()
	  */
	 @Test
	 public void testGetInOut() {
		 assertEquals(_inout, _data.getInOut());
	 }
	 
	 /**
	  * Testet, ob Einnahme ausgegeben wird, wenn an
	  * {@link datas.MoneyData#setInOut(boolean)} {@link datas.MoneyData#INCOMING}
	  * übergeben wurde.
	  * 
	  * @see datas.MoneyData#getInOutAsString()
	  */
	 @Test
	 public void testGetInOutAsStringWithIncoming() {
		 _data.setInOut(MoneyData.INCOMING);
		 assertEquals("Einnahme", _data.getInOutAsString());
	 }
	 
	 /**
	  * Testet, ob Ausgabe ausgegeben wird, wenn an
	  * {@link datas.MoneyData#setInOut(boolean)} {@link datas.MoneyData#OUTGOING}
	  * übergeben wurde.
	  * 
	  * @see datas.MoneyData#getInOutAsString()
	  */
	 @Test
	 public void testGetInOutAsStringWithOutgoing() {
		 _data.setInOut(MoneyData.OUTGOING);
		 assertEquals("Ausgabe", _data.getInOutAsString());
	 }
	 
	 /**
	  * Testet, ob die Ein- oder Auszahlung richtig übernommen wird.
	  * 
	  * @see datas.MoneyData#setInOut(boolean)
	  */
	 @Test
	 public void testSetInOut() {
		 _data.setInOut(true);
		 assertTrue(_data.getInOut());
	 }
	 
	 /**
	  * Testet, ob die Beschreibung richtig zurück gegeben wird.
	  * 
	  * @see datas.MoneyData#getComment()
	  */
	 @Test
	 public void testGetComment() {
		 assertEquals(_comment, _data.getComment());
	 }
	 
	 /**
	  * Testet, ob die Beschreibung richtig übernommen wird.
	  * 
	  * @see datas.MoneyData#setComment(String)
	  */
	 @Test
	 public void testSetComment() {
		 String test = "Test21";
		 _data.setComment(test);
		 assertEquals(test, _data.getComment());
	 }
	 
	 /**
	  * Testet, ob eine leere Zeichenkette erzeugt wird, wenn <b>null</b>
	  * übergeben wird.
	  * 
	  * @see datas.MoneyData#setComment(String)
	  */
	 @Test
	 public void testSetCommentNullAsParameter() {
		 _data.setComment(null);
		 assertEquals(new String(), _data.getComment());
	 }
	 
	 /**
	  * Testet, ob die Konstante für die Ausgabe richtig initalisiert
	  * wurde.
	  * 
	  * @see datas.MoneyData#OUTGOING
	  */
	 @Test
	 public void testOutgoing() {
		 assertFalse(MoneyData.OUTGOING);
	 }
	 
	 /**
	  * Testet, ob die int-Konstante für die Ausgabe richtig initalisiert
	  * wurde.
	  * 
	  * @see datas.MoneyData#INT_OUTGOING
	  */
	 @Test
	 public void testIntOutgoing() {
		 assertEquals(0, MoneyData.INT_OUTGOING);
	 }
	 
	 /**
	  * Testet, ob die Konstante für eine Einzahlung richtig initalisiert
	  * wurde.
	  * 
	  * @see datas.MoneyData#INCOMING
	  */
	 @Test
	 public void testIncoming() {
		 assertTrue(MoneyData.INCOMING);
	 }
	 
	 /**
	  * Testet, ob die int-Konstante für die Einnahme richtig initalisiert
	  * wurde.
	  * 
	  * @see datas.MoneyData#INT_INCOMING
	  */
	 @Test
	 public void testIntIncoming() {
		 assertEquals(1, MoneyData.INT_INCOMING);
	 }
}
