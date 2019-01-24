package com.anand.xmldata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.Date;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.anand.jdbcconnection.DbConnection;
import com.anand.jdbcconnection.DbConnectivityPoJo;

public class JdomLibrary {
	public static void main(String[] args) throws Exception {
		DbConnectivityPoJo dbConnection= new DbConnectivityPoJo();
		dbConnection.setPropertyFilename("D:\\Git Repo\\JDBC\\src\\main\\resources\\jdbcproject.properties");
		DbConnection mDbConnection= new DbConnection();
		dbConnection=mDbConnection.connectToDb(dbConnection);

		if(dbConnection.getDbConnectionStatus())
		{
			callableExecuteQuerryResultSet(dbConnection.getDbConnection());

			dbConnection.getDbConnection().close();

		}
		else{
			throw new Exception("Database Connectivity Failed");
		}
	}

	private static void callableExecuteQuerryResultSet(Connection dbConnection) {
		String sql1="Select * from Persons";
		java.sql.Statement stmt=null;
		try {
			stmt=dbConnection.createStatement();
			ResultSet resultset=stmt.executeQuery(sql1);
			long d1= new Date().getTime();
			int numCols = resultset.getMetaData().getColumnCount();
			Element persons = new Element("Persons");
			Document doc = new Document(persons);
			doc.setRootElement(persons);

			while(resultset.next())
			{
				Element person = new Element("Person");

				for(int i=1;i<=numCols;i++)
				{
					if(i==1)
					{
						person.setAttribute(new Attribute(resultset.getMetaData().getColumnName(i),Integer.toString(resultset.getInt(i))));
					}
					else{
						person.addContent(new Element(resultset.getMetaData().getColumnName(i)).setText(resultset.getString(i)));
					}
				}
				doc.getRootElement().addContent(person);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, System.out);
			long d2= new Date().getTime();
			System.out.println(d2-d1);
			

		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+" "+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode()+" "+e.getMessage());
			}
		}
	}
}
