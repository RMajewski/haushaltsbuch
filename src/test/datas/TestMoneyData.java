package test.datas;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

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
	 * Testet, ob die ID richtig zurück gegeben wird.
	 * 
	 * @see datas.MoneyData#getId()
	 */
	@Test
	public void testGetId() {
		assertEquals(_id, _data.getId());
	}
	
	/**
	 * Testet, ob die ID richtig übernommen wird.
	 * 
	 * @see datas.MoneyData#setId(int)
	 */
	@Test
	public void testSetId() {
		_data.setId(5400);
		assertEquals(5400, _data.getId());
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
	 * Testet, ob das Datum richtig übernommen wird.
	 * 
	 * @see datas.MoneyData#setDate(long)
	 */
	 @Test
	 public void testSetDate() {
		 long date = 1345918385878L;
		 _data.setDate(date);
		 assertEquals(date, _data.getDate());
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
	  * Testet, ob die Konstante für eine Einzahlung richtig initalisiert
	  * wurde.
	  * 
	  * @see datas.MoneyData#INCOMING
	  */
	 @Test
	 public void testIncoming() {
		 assertTrue(MoneyData.INCOMING);
	 }
}
