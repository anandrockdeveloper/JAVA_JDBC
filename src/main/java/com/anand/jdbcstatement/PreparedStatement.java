package com.anand.jdbcstatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.anand.jdbcconnection.DbConnection;
import com.anand.jdbcconnection.DbConnectivityPoJo;

public class PreparedStatement {

	public static void main(String[] args) throws Exception {
		// Connect to DB
		DbConnectivityPoJo dbConnection= new DbConnectivityPoJo();
		dbConnection.setPropertyFilename("D:\\Git Repo\\JDBC\\src\\main\\resources\\jdbcproject.properties");
		DbConnection mDbConnection= new DbConnection();
		dbConnection=mDbConnection.connectToDb(dbConnection);

		if(dbConnection.getDbConnectionStatus())
		{

			//preparedExecuteResultSet(dbConnection.getDbConnection());// No Execute statement.
			preparedExecuteUpdateResultSet(dbConnection.getDbConnection());
			preparedExecuteQuerryResultSet(dbConnection.getDbConnection());

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
	private static void preparedExecuteQuerryResultSet(Connection connection) {
		String sql1="Select * from Persons where Address=?";
		java.sql.PreparedStatement stmt=null;
		try {
			stmt=connection.prepareStatement(sql1);
			stmt.setString(1,"India");

			ResultSet resultset=stmt.executeQuery();
			while(resultset.next())
			{
				int PersonID=resultset.getInt(1);
				String LastName=resultset.getString(2);
				String FirstName=resultset.getString(3);
				String Address=resultset.getString(4);
				String City=resultset.getString(5);
				System.out.println(PersonID+","+LastName+","+FirstName+","+Address+","+City);		
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

	/*
	 * Returns the number of rows affected by the execution of the SQL statement. 
	 * Use this method to execute SQL statements, 
	 * for which you expect to get a number of rows affected - for example, 
	 * an INSERT, UPDATE, or DELETE statement.
	 */
	private static void preparedExecuteUpdateResultSet(Connection connection) {
		String sql1="INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (?, ?, ?, 'India', 'Bangalore')";
		String sql2="INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (?, ?, ?, 'Pakistan', 'Lahore')";
		String sql3="INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (?, ?, ?, 'China', 'Beijing')";
		java.sql.PreparedStatement stmt=null;
		try {
			stmt=connection.prepareStatement(sql1);
			int resultset=0;
			stmt.setInt(1,4);
			stmt.setString(2,"qwer");
			stmt.setString(3,"zxcv");
			resultset+=stmt.executeUpdate();
			stmt.close();

			stmt=connection.prepareStatement(sql2);
			stmt.setInt(1,5);
			stmt.setString(2,"rfv");
			stmt.setString(3,"tgb");
			resultset+=stmt.executeUpdate();
			stmt.close();
			stmt=connection.prepareStatement(sql3);

			stmt.setInt(1,6);
			stmt.setString(2,"yhn");
			stmt.setString(3,"ujm");
			resultset+=stmt.executeUpdate();
			stmt.close();

			System.out.println("Rows Affected "+resultset);

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
