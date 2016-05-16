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

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.ReportYearData;
import haushaltsbuch.helper.HelperNumbersOut;
import haushaltsbuch.tables.models.ReportYearModel;
import tests.testcase.TestReports;

/**
 * Testet das Tabellen-Model {@link haushaltsbuch.tables.models.ReportYearModel}
 * 
 * @author René Majewski
 *
 * @version 0.1
 * @since 0.2
 */
public class TestReportYearModel extends TestReports {
	/**
	 * Speichert das Mock-Objekt für die Daten
	 */
	private ReportYearData _data;
	
	/**
	 * Speichert die Instanz des Models
	 */
	private ReportYearModel _model;

	/**
	 * Initalisiert die einzelnen Tests.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_year = 2016;
		_month = 4;
		initPreferences();
		
		// Mock-Objekt für die Daten
		_data = mock(ReportYearData.class);
		
		// Model initalisieren
		_model = new ReportYearModel(_data);
	}

	/**
	 * Testet, ob die Spalten-Anzahl richtig ermittelt wird.
	 * 
	 * @see haushaltsbuch.tables.models.ReportYearModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		int col = 4;
		when(_data.getColumnCount()).thenReturn(col);
		assertEquals(col, _model.getColumnCount());
		verify(_data, times(1)).getColumnCount();
	}

	/**
	 * Testet, ob die Zeilen-Anzahl richtig ermittelt wird.
	 * 
	 * @see haushaltsbuch.tables.models.ReportYearModel#getRowCount()
	 */
	@Test
	public void testGetRowCount() {
		int row = 12;
		when(_data.getRowCount()).thenReturn(row);
		assertEquals(row, _model.getRowCount());
		verify(_data, times(1)).getRowCount();
	}

	/**
	 * Testet, ob der Monat richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.ReportYearModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtZeroColumn() {
		int col = 10;
		String str = "November";
		when(_data.getMonthName(col)).thenReturn(str);

		assertEquals(str, _model.getValueAt(col, 0));
		
		verify(_data, times(1)).getMonthName(col);
		verify(_data, never()).incoming(col);
		verify(_data, never()).outgoing(col);
		verify(_data, never()).deviation(col);
	}

	/**
	 * Testet, ob die Einnahmen richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.ReportYearModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtOneColumn() {
		int col = 10;
		double d = 5.48;
		
		when(_data.incoming(col)).thenReturn(d);

		assertEquals(HelperNumbersOut.sum(d), _model.getValueAt(col, 1));
		
		verify(_data, never()).getMonthName(col);
		verify(_data, times(1)).incoming(col);
		verify(_data, never()).outgoing(col);
		verify(_data, never()).deviation(col);
	}

	/**
	 * Testet, ob die Ausgaben richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.ReportYearModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtTwoColumn() {
		int col = 10;
		double d = 5.48;
		
		when(_data.outgoing(col)).thenReturn(d);

		assertEquals(HelperNumbersOut.sum(d), _model.getValueAt(col, 2));
		
		verify(_data, never()).getMonthName(col);
		verify(_data, never()).incoming(col);
		verify(_data, times(1)).outgoing(col);
		verify(_data, never()).deviation(col);
	}

	/**
	 * Testet, ob die Ausgaben richtig zurück gegeben wird.
	 * 
	 * @see haushaltsbuch.tables.models.ReportYearModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtThreeColumn() {
		int col = 10;
		double d = 5.48;
		
		when(_data.deviation(col)).thenReturn(d);

		assertEquals(HelperNumbersOut.sum(d), _model.getValueAt(col, 3));
		
		verify(_data, never()).getMonthName(col);
		verify(_data, never()).outgoing(col);
		verify(_data, times(1)).deviation(col);
	}
}
