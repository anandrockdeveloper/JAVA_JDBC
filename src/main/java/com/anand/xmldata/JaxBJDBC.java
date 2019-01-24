package com.anand.xmldata;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.anand.jdbc.objectMapping.Person;
import com.anand.jdbc.objectMapping.Persons;
import com.anand.jdbcconnection.DbConnection;
import com.anand.jdbcconnection.DbConnectivityPoJo;

public class JaxBJDBC {
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

	private static void callableExecuteQuerryResultSet(Connection dbConnection) throws SQLException, JAXBException {
		ResultSetHandler<List<Person>> h = new BeanListHandler<Person>(Person.class);
		QueryRunner run = new QueryRunner();
		List<Person> persons = run.query(dbConnection,"SELECT * FROM Persons", h);
		generteXML(persons);
		generateXMLAll(persons);
	}
	
	
	

	private static void generteXML(List<Person> persons) throws JAXBException {
		for(Person p:persons)
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(p, System.out);
		}
		
	}

	private static void generateXMLAll(List<Person> personsList) throws JAXBException {
		Persons persons= new Persons();
		persons.setPerson(personsList);
		JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(persons, System.out);

	}

}
