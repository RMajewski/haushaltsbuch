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

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.LogData;

/**
 * Testet die Klasse {@link haushaltsbuch.datas.LogData}.
 * 
 * @author René Majewski
 * 
 * @version 0.2
 * @since 0.1
 */
public class TestLogData {
	/**
	 * Speichert die erstellten Daten
	 */
	private LogData _data;
	
	/**
	 * Speichert die Standard Nachricht
	 */
	private String _message;
	
	/**
	 * Speichert die 2. Test Nachricht
	 */
	private String _message2;
	
	/**
	 * Speichert die standard Fehlerbeschreibung
	 */
	private String _error;
	
	/**
	 * Speichert die 2. Fehlerbeschreibung
	 */
	private String _error2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_message = new String("Test Nachricht");
		_message2 = new String("Test Nachricht 2");
		
		_error = new String("Fehlerbeschreibung");
		_error2 = new String("Fehlerbeschreibung 2");
		
		_data = new LogData(_message, _error);
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData()}.
	 */
	@Test
	public void testLogData() {
		_data = new LogData();
		assertEquals(LogData.NO_OUT, _data.getOut());
		assertTrue(_data.getMessage().isEmpty());
		assertTrue(_data.getError().isEmpty());
	}
	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String)}.
	 * 
	 * @deprecated
	 */
	@Test
	public void testLogDataString() {
		_data = new LogData(_message);
		assertEquals(LogData.NONE, _data.getOut());
		assertEquals(_message, _data.getMessage());
		assertTrue(_data.getError().isEmpty());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogDataStringString() {
		assertEquals(LogData.NONE, _data.getOut());
		assertEquals(_message, _data.getMessage());
		assertEquals(_error, _data.getError());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String, java.lang.String, short)}.
	 */
	@Test
	public void testLogDataStringStringShort() {
		_data = new LogData(_message, _error, LogData.OK);
		assertEquals(LogData.OK, _data.getOut());
		assertEquals(_message, _data.getMessage());
		assertEquals(_error, _data.getError());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String, short)}.
	 * 
	 * @deprecated
	 */
	@Test
	public void testLogDataStringShort() {
		_data = new LogData(_message, LogData.OK);
		assertEquals(LogData.OK, _data.getOut());
		assertEquals(_message, _data.getMessage());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#getMessage()}.
	 */
	@Test
	public void testGetMessage() {
		assertEquals(_message, _data.getMessage());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#getError()}.
	 */
	@Test
	public void testGetError() {
		assertEquals(_error, _data.getError());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#getOut()}.
	 */
	@Test
	public void testGetOut() {
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage() {
		_data.setMessage(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(_error, _data.getError());
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setError(java.lang.String)}.
	 */
	@Test
	public void testSetError() {
		_data.setError(_error2);
		assertEquals(_message, _data.getMessage());
		assertEquals(_error2, _data.getError());
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsNoOut(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsNoOutDeprecated() {
		_data.setMessageAsNoOut(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.NO_OUT, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsNoOut(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsNoOut() {
		_data.setMessageAsNoOut(_message2, _error2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(_error2, _data.getError());
		assertEquals(LogData.NO_OUT, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsError(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsErrorDeprecated() {
		_data.setMessageAsError(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.ERROR, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsError(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsError() {
		_data.setMessageAsError(_message2, _error2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(_error2, _data.getError());
		assertEquals(LogData.ERROR, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsWarning(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsWarningDeptrecated() {
		_data.setMessageAsWarning(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsOk(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsOkDeprecated() {
		_data.setMessageAsOk(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.OK, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsOk(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsOk() {
		_data.setMessageAsOk(_message2, _error2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(_error2, _data.getError());
		assertEquals(LogData.OK, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setOut(short)}.
	 */
	@Test
	public void testSetOut() {
		_data.setOut(LogData.WARNING);
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testLogDataStringWithNullDeprecated() {
		_data = new LogData(null);
		assertEquals(LogData.NONE, _data.getOut());
		assertEquals(new String(), _data.getMessage());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogDataStringStringWithNull() {
		_data = new LogData(null, null);
		assertEquals(LogData.NONE, _data.getOut());
		assertEquals(new String(), _data.getMessage());
		assertEquals(new String(), _data.getError());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String, short)}.
	 * @deprecated
	 */
	@Test
	public void testLogDataStringShortWithStringDeprecated() {
		_data = new LogData(null, LogData.OK);
		assertEquals(LogData.OK, _data.getOut());
		assertEquals(new String(), _data.getMessage());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#LogData(java.lang.String, short)}.
	 */
	@Test
	public void testLogDataStringStringShortWithString() {
		_data = new LogData(null, null, LogData.OK);
		assertEquals(LogData.OK, _data.getOut());
		assertEquals(new String(), _data.getMessage());
		assertEquals(new String(), _data.getError());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessageWithNull() {
		_data.setMessage(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(_error, _data.getError());
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setError(java.lang.String)}.
	 */
	@Test
	public void testSetErrorWithNull() {
		_data.setError(null);
		assertEquals(_message, _data.getMessage());
		assertEquals(new String(), _data.getError());
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsNoOut(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsNoOutWithNullDeprecated() {
		_data.setMessageAsNoOut(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.NO_OUT, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsNoOut(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsNoOutWithNull() {
		_data.setMessageAsNoOut(null, null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(new String(), _data.getError());
		assertEquals(LogData.NO_OUT, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsError(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsErrorWithNullDeprecated() {
		_data.setMessageAsError(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.ERROR, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsError(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsErrorWithNull() {
		_data.setMessageAsError(null, null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(new String(), _data.getError());
		assertEquals(LogData.ERROR, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsWarning(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsWarningWithNullDeprecated() {
		_data.setMessageAsWarning(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsWarning(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsWarningWithNull() {
		_data.setMessageAsWarning(null, null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(new String(), _data.getError());
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsOk(java.lang.String)}.
	 * @deprecated
	 */
	@Test
	public void testSetMessageAsOkWithNullDeprecated() {
		_data.setMessageAsOk(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.OK, _data.getOut());
	}

	/**
	 * Test method for {@link haushaltsbuch.datas.LogData#setMessageAsOk(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsOkWithNull() {
		_data.setMessageAsOk(null, null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(new String(), _data.getError());
		assertEquals(LogData.OK, _data.getOut());
	}
	
	/**
	 * Testet, ob die Farbe für Normale-Nachrichten richtig gesetzt wurde.
	 */
	@Test
	public void testColorNormalRightClor() {
		assertEquals(Color.LIGHT_GRAY, LogData.COLOR_NONE);
	}
	
	/**
	 * Testet, ob die Farbe für Warnungen richtig gesetzt wurde.
	 */
	@Test
	public void testColorWarnungRightClor() {
		assertEquals(Color.ORANGE, LogData.COLOR_WARNING);
	}
	
	/**
	 * Testet, ob die Farbe für "In Ordung"-Nachrichten richtig gesetzt wurde.
	 */
	@Test
	public void testColorOkRightClor() {
		assertEquals(Color.GREEN, LogData.COLOR_OK);
	}
	
	/**
	 * Testet, ob die Farbe für Fehler richtig gesetzt wurde.
	 */
	@Test
	public void testColorErrorRightClor() {
		assertEquals(Color.RED, LogData.COLOR_ERROR);
	}
}
