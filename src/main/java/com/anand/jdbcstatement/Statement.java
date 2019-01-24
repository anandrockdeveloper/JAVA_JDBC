package com.anand.jdbcstatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.anand.jdbcconnection.DbConnection;
import com.anand.jdbcconnection.DbConnectivityPoJo;

public class Statement {

	public static void main(String[] args) throws Exception {
		// Connect to DB
		DbConnectivityPoJo dbConnection= new DbConnectivityPoJo();
		dbConnection.setPropertyFilename("D:\\Git Repo\\JDBC\\src\\main\\resources\\jdbcproject.properties");
		DbConnection mDbConnection= new DbConnection();
		dbConnection=mDbConnection.connectToDb(dbConnection);

		if(dbConnection.getDbConnectionStatus())
		{
			callableExecuteResultSet(dbConnection.getDbConnection());
			callableExecuteUpdateResultSet(dbConnection.getDbConnection());
			callableExecuteQuerryResultSet(dbConnection.getDbConnection());

			dbConnection.getDbConnection().close();

		}
		else{
			throw new Exception("Database Connectivity Failed");
		}


	}

	/*
	 * Returns a ResultSet object. 
	 * Use this method when you expect to get a result set, 
	 * as you would with a SELECT statement.
	 */
	private static void callableExecuteQuerryResultSet(Connection connection) {
		String sql1="Select * from Persons";
		java.sql.Statement stmt=null;
		try {
			stmt=connection.createStatement();
			ResultSet resultset=stmt.executeQuery(sql1);
			long d1= new Date().getTime();
			while(resultset.next())
			{
				int PersonID=resultset.getInt(1);
				String LastName=resultset.getString(2);
				String FirstName=resultset.getString(3);
				String Address=resultset.getString(4);
				String City=resultset.getString(5);
				System.out.println(PersonID+","+LastName+","+FirstName+","+Address+","+City);		
			}
			long d2= new Date().getTime();
			System.out.println(d2-d1);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+" "+e.getMessage());
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode()+" "+e.getMessage());
			}
		}

	}

	/*
	 * Returns the number of rows affected by the execution of the SQL statement. 
	 * Use this method to execute SQL statements, 
	 * for which you expect to get a number of rows affected - for example, 
	 * an INSERT, UPDATE, or DELETE statement.
	 */
	private static void callableExecuteUpdateResultSet(Connection connection) {
		String sql1="INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (1, 'xyz', 'abc', 'India', 'Bangalore')";
		String sql2="INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (2, 'xyz', 'def', 'Pakistan', 'Lahore')";
		String sql3="INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (3, 'xyz', 'ghi', 'China', 'Beijing')";
		java.sql.Statement stmt=null;
		try {
			stmt=connection.createStatement();
			int resultset=0;
			resultset+=stmt.executeUpdate(sql1);
			resultset+=stmt.executeUpdate(sql2);
			resultset+=stmt.executeUpdate(sql3);

			System.out.println("Rows Affected"+resultset);

		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+" "+e.getMessage());
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getErrorCode()+" "+e.getMessage());
			}
		}

	}

	/*Returns a boolean value of true if a ResultSet object can be retrieved; 
	 *otherwise, it returns false. 
	 * Use this method to execute SQL DDL statements or 
	 *when you need to use the truly dynamic SQL.
	 */
	private static void callableExecuteResultSet(Connection connection) {

		String sql="CREATE TABLE Persons (PersonID int, LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255))";
		java.sql.Statement stmt=null;
		try {
			stmt=connection.createStatement();
			boolean resultset=stmt.execute(sql);
			if(resultset)
			{
				System.out.println("Success");
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()+" "+e.getMessage());
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
