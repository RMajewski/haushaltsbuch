/**
 * 
 */
package test.datas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datas.LogData;

/**
 * @author René Majewski
 *
 */
public class TestLogData {
	/**
	 * Speichert die erstellten Daten
	 */
	private static LogData _data;
	
	/**
	 * Speichert die Standard Nachricht
	 */
	private static String _message;
	
	/**
	 * Speichert die 2. Test Nachricht
	 */
	private static String _message2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_message = new String("Test Nachricht");
		_message2 = new String("Test Nachricht 2");
		_data = new LogData(_message);
	}

	/**
	 * Test method for {@link datas.LogData#LogData()}.
	 */
	@Test
	public void testLogData() {
		_data = new LogData();
		assertTrue(_data.getOut() == LogData.NO_OUT);
		assertTrue(_data.getMessage().isEmpty());
	}

	/**
	 * Test method for {@link datas.LogData#LogData(java.lang.String)}.
	 */
	@Test
	public void testLogDataString() {
		assertEquals(LogData.NONE, _data.getOut());
		assertEquals(_message, _data.getMessage());
	}

	/**
	 * Test method for {@link datas.LogData#LogData(java.lang.String, short)}.
	 */
	@Test
	public void testLogDataStringShort() {
		_data = new LogData(_message, LogData.OK);
		assertEquals(LogData.OK, _data.getOut());
		assertEquals(_message, _data.getMessage());
	}

	/**
	 * Test method for {@link datas.LogData#getMessage()}.
	 */
	@Test
	public void testGetMessage() {
		assertEquals(_message, _data.getMessage());
	}

	/**
	 * Test method for {@link datas.LogData#getOut()}.
	 */
	@Test
	public void testGetOut() {
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage() {
		_data.setMessage(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsNoOut(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsNoOut() {
		_data.setMessageAsNoOut(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.NO_OUT, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsError(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsError() {
		_data.setMessageAsError(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.ERROR, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsWarning(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsWarning() {
		_data.setMessageAsWarning(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsOk(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsOk() {
		_data.setMessageAsOk(_message2);
		assertEquals(_message2, _data.getMessage());
		assertEquals(LogData.OK, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setOut(short)}.
	 */
	@Test
	public void testSetOut() {
		_data.setOut(LogData.WARNING);
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#LogData(java.lang.String)}.
	 */
	@Test
	public void testLogDataStringWithNull() {
		_data = new LogData(null);
		assertEquals(LogData.NONE, _data.getOut());
		assertEquals(new String(), _data.getMessage());
	}

	/**
	 * Test method for {@link datas.LogData#LogData(java.lang.String, short)}.
	 */
	@Test
	public void testLogDataStringShortWithString() {
		_data = new LogData(null, LogData.OK);
		assertEquals(LogData.OK, _data.getOut());
		assertEquals(new String(), _data.getMessage());
	}

	/**
	 * Test method for {@link datas.LogData#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessageWithNull() {
		_data.setMessage(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.NONE, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsNoOut(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsNoOutWithNull() {
		_data.setMessageAsNoOut(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.NO_OUT, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsError(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsErrorWithNull() {
		_data.setMessageAsError(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.ERROR, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsWarning(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsWarningWithNull() {
		_data.setMessageAsWarning(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.WARNING, _data.getOut());
	}

	/**
	 * Test method for {@link datas.LogData#setMessageAsOk(java.lang.String)}.
	 */
	@Test
	public void testSetMessageAsOkWithNull() {
		_data.setMessageAsOk(null);
		assertEquals(new String(), _data.getMessage());
		assertEquals(LogData.OK, _data.getOut());
	}
}
