package com.anand.jdbc.objectMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anand.jdbcconnection.DbConnection;
import com.anand.jdbcconnection.DbConnectivityPoJo;

public class DbUtilsClass {
	public static void main(String[] args) throws Exception {
		// Connect to DB
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

	/* Converts DB Result set into Java Beans by using ResultSetHandler from Apache */
	private static void callableExecuteQuerryResultSet(Connection dbConnection) throws SQLException {
		ResultSetHandler<List<Person>> h = new BeanListHandler<Person>(Person.class);
		QueryRunner run = new QueryRunner();
		long d1= new Date().getTime();
		List<Person> persons = run.query(dbConnection,"SELECT * FROM Persons", h);
		long d2= new Date().getTime();
		System.out.println(d2-d1);
		for(Person p:persons)
		{
			System.out.println(p.getPersonID()+","+p.getLastName()+","+p.getFirstName()+","+p.getAddress()+","+p.getCity());
		}
		
	}

}
