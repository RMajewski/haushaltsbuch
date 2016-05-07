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

package haushaltsbuch.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import haushaltsbuch.db.DbController;
import haushaltsbuch.dialogs.DlgExportSqlScript;
import haushaltsbuch.elements.StatusBar;

/**
 * Diese Klasse export die gespeicherten Daten aus der Datenbank in ein
 * SQL-Sript.
 * 
 * @author René Majewski
 * 
 * @version 0.1
 * @since 0.2
 */
public class SqlScript extends Export {
	
	/**
	 * Initalisiert diese Klasse
	 */
	public SqlScript(JFrame owner) {
		// Dialog speichern
		super(new DlgExportSqlScript(owner));
	}
	
	/**
	 * Schreibt den angegeben Text in den FileWriter und schließt die Zeile mit
	 * dem entsprechenden Linien-Ende ab.
	 * 
	 * @param writer FileWriter, in den die Daten geschrieben werden sollen.
	 * 
	 * @param line Text, der in einer Zeile stehen soll.
	 * 
	 * @throws IOException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datei gibt.
	 */
	private void addLine(FileWriter writer, String line) throws IOException {
		writer.write(line);
		writer.write(System.getProperty("line.separator"));
	}
	
	/**
	 * Liest die Daten aus der Datenbank und schreibt sie in SQL-Commands,
	 * welche in der ausgewählten Datei gespeichert werden.
	 * 
	 * @param writer Geöffnete Datei, in die die erzeugten SQL-Commands
	 * geschrieben werden sollen.
	 * 
	 * @throws IOException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datei gibt.
	 * 
	 * @throws SQLException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datenbank gibt.
	 */
	private void dataCategory(FileWriter writer) 
			throws IOException, SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery(DbController.queries().category().select());
		while (rs.next()) {
			addLine(writer, DbController.queries().category().insert(
					rs.getInt("id"), rs.getString("name")));
		}
	}
	
	/**
	 * Liest die Daten aus der Datenbank und schreibt sie in SQL-Commands,
	 * welche in der ausgewählten Datei gespeichert werden.
	 * 
	 * @param writer Geöffnete Datei, in die die erzeugten SQL-Commands
	 * geschrieben werden sollen.
	 * 
	 * @throws IOException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datei gibt.
	 * 
	 * @throws SQLException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datenbank gibt.
	 */
	private void dataSection(FileWriter writer) 
			throws IOException, SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery(DbController.queries().section().select());
		while (rs.next()) {
			addLine(writer, DbController.queries().section().insert(
					rs.getInt("id"), rs.getString("name")));
		}
	}
	
	/**
	 * Liest die Daten aus der Datenbank und schreibt sie in SQL-Commands,
	 * welche in der ausgewählten Datei gespeichert werden.
	 * 
	 * @param writer Geöffnete Datei, in die die erzeugten SQL-Commands
	 * geschrieben werden sollen.
	 * 
	 * @throws IOException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datei gibt.
	 * 
	 * @throws SQLException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datenbank gibt.
	 */
	private void dataMoney(FileWriter writer) throws IOException, SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery(DbController.queries().money().select());
		while (rs.next()) {
			addLine(writer, DbController.queries().money().insert(
					rs.getInt("id"), rs.getLong("date"), rs.getBoolean("inout"), 
					rs.getString("comment")));
		}
	}
	
	/**
	 * Liest die Daten aus der Datenbank und schreibt sie in SQL-Commands,
	 * welche in der ausgewählten Datei gespeichert werden.
	 * 
	 * @param writer Geöffnete Datei, in die die erzeugten SQL-Commands
	 * geschrieben werden sollen.
	 * 
	 * @throws IOException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datei gibt.
	 * 
	 * @throws SQLException Wird ausgelöst, wenn es Probleme mit dem Zugriff auf
	 * die Datenbank gibt.
	 */
	private void dataMoneyDetails(FileWriter writer) 
			throws IOException, SQLException {
		Statement stm = DbController.getInstance().createStatement();
		ResultSet rs = stm.executeQuery(DbController.queries().moneyDetails().select());
		while (rs.next()) {
			addLine(writer, DbController.queries().moneyDetails().insert(
					rs.getInt("id"), rs.getInt("moneyid"), 
					rs.getInt("categoryid"), rs.getInt("sectionid"),
					rs.getDouble("money"), rs.getString("comment")));
		}
	}

	/**
	 * Exportiert die angegebenen Daten als SQL-Script.
	 * 
	 * @param file Datei, in die das SQL-Script gespeichert werden soll.
	 */
	@Override
	protected void export(File file) {
		// Datei öffnen
		try {
			// FileWriter erstellen
			FileWriter fw = new FileWriter(file);
			
			// Tabellenkopf für die Tabelle 'category' exportieren?
			if (_preference.get(DlgExportSqlScript.TABLE_CATEGORY) != null)
				addLine(fw, 
						DbController.queries().category().createTable() + ";");
			
			// Daten für die Tabelle 'categorie' exportieren?
			if (_preference.get(DlgExportSqlScript.DATA_CATEGORY) != null)
				dataCategory(fw);
			
			// Tabellenkopf für die Tabelle 'section' exportieren?
			if (_preference.get(DlgExportSqlScript.TABLE_SECTION) != null)
				addLine(fw, 
						DbController.queries().section().createTable() + ";");
			
			// Daten für die Tabelle 'section' exportieren?
			if (_preference.get(DlgExportSqlScript.DATA_SECTION) != null)
				dataSection(fw);
			
			// Tabellenkopf für die Tabelle 'money' exportieren?
			if (_preference.get(DlgExportSqlScript.TABLE_MONEY) != null)
				addLine(fw, 
						DbController.queries().money().createTable() + ";");
			
			// Daten für die Tabelle 'money' exportieren?
			if (_preference.get(DlgExportSqlScript.DATA_MONEY) != null)
				dataMoney(fw);
			
			// Tabellenkopf für die Tabelle 'money_details' exportieren?
			if (_preference.get(DlgExportSqlScript.TABLE_MONEY_DETAILS) != null)
				addLine(fw, 
						DbController.queries().moneyDetails().createTable() +
						";");
			
			// Daten für die Tabelle 'money_details' exportieren?
			if (_preference.get(DlgExportSqlScript.DATA_MONEY_DETAILS) != null)
				dataMoneyDetails(fw);
			
			// Muss der FileWriter beendet werden?
			if (fw != null)
				fw.close();
		} catch (IOException e) {
			StatusBar.getInstance().setMessageAsError("Der Zugriff auf die Datei " + file.getName() + " ist Fehlerhauft.");
			e.printStackTrace();
		} catch (SQLException e) {
			StatusBar.getInstance().setMessageAsError(
					DbController.statusDbError());
			e.printStackTrace();
		}
	}

}
