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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import haushaltsbuch.datas.ReportCategoryData;
import haushaltsbuch.datas.ReportMonthData;
import haushaltsbuch.tables.models.ReportCategoryModel;
import tests.testcase.TestReports;

public class TestReportCategoryModel extends TestReports {
	/**
	 * Speichert die Instanz des Models
	 */
	private ReportCategoryModel _model;
	
	/**
	 * Speichert das Mockobjekt für die Daten
	 */
	private ReportCategoryData _data;

	/**
	 * Initalisiert die einzelnen Tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		_year = 2016;
		_month = 4;
		initPreferences();
		
		// Mock-Objekt für die Daten
		_data = mock(ReportCategoryData.class);
		
		// Model initalisieren
		_model = new ReportCategoryModel(_data);
	}

	/**
	 * Testet, ob die Spalten-Anzahl richtig ermittelt wird.
	 * 
	 * @see tables.models.ReportCategoryModel#getColumnCount()
	 */
	@Test
	public void testGetColumnCount() {
		int col = 1;
		when(_data.getColumnCount()).thenReturn(col);
		assertEquals(col, _model.getColumnCount());
		verify(_data, times(1)).getColumnCount();
	}

	/**
	 * Testet, ob die Zeilen-Anzahl richtig ermittelt wird.
	 * 
	 * @see tables.models.ReportCategoryModel#getRowCount()
	 */
	@Test
	public void testGetRowCount() {
		int row = 20;
		when(_data.getRowCount()).thenReturn(row);
		assertEquals(row, _model.getRowCount());
		verify(_data, times(1)).getRowCount();
	}

	/**
	 * Testet, ob der Name der Kategorie richtig zurück gegeben wird.
	 * 
	 * @see tables.models.ReportCategoryModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtZeroColumn() {
		int col = 10;
		String category = "Test";
		
		when(_data.getCategory(col)).thenReturn(category);

		assertEquals(category, _model.getValueAt(col, 0));
		
		verify(_data, times(1)).getCategory(col);
		verify(_data, never()).incoming(col);
		verify(_data, never()).outgoing(col);
		verify(_data, never()).deviation(col);
	}

	/**
	 * Testet, ob die Einnahmen richtig zurück gegeben wird.
	 * 
	 * @see tables.models.ReportCategoryModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtOneColumn() {
		int col = 10;
		double d = 5.48;
		
		when(_data.incoming(col)).thenReturn(d);

		assertEquals(new DecimalFormat("0.00").format(d),
				_model.getValueAt(col, 1));
		
		verify(_data, never()).getCategory(col);
		verify(_data, times(1)).incoming(col);
		verify(_data, never()).outgoing(col);
		verify(_data, never()).deviation(col);
	}

	/**
	 * Testet, ob die Ausgaben richtig zurück gegeben wird.
	 * 
	 * @see tables.models.ReportCategoryModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtTwoColumn() {
		int col = 10;
		double d = 5.48;
		
		when(_data.outgoing(col)).thenReturn(d);

		assertEquals(new DecimalFormat("0.00").format(d),
				_model.getValueAt(col, 2));
		
		verify(_data, never()).getCategory(col);
		verify(_data, never()).incoming(col);
		verify(_data, times(1)).outgoing(col);
		verify(_data, never()).deviation(col);
	}

	/**
	 * Testet, ob die Differenz richtig zurück gegeben wird.
	 * 
	 * @see tables.models.ReportCategoryModel#getValueAt(int, int)
	 */
	@Test
	public void testGetValueAtThreeColumn() {
		int col = 10;
		double d = 5.48;
		
		when(_data.deviation(col)).thenReturn(d);

		assertEquals(new DecimalFormat("0.00").format(d),
				_model.getValueAt(col, 3));
		
		verify(_data, never()).getCategory(col);
		verify(_data, never()).incoming(col);
		verify(_data, never()).outgoing(col);
		verify(_data, times(1)).deviation(col);
	}
}
