package com.anand.jdbc.objectMapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	int PersonID;
	String LastName;
	String FirstName;
	String Address;
	String City;

	public int getPersonID() {
		return PersonID;
	}
	@XmlAttribute
	public void setPersonID(int personID) {
		PersonID = personID;
	}
	public String getLastName() {
		return LastName;
	}
	@XmlElement
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	@XmlElement
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getAddress() {
		return Address;
	}
	@XmlElement
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	@XmlElement
	public void setCity(String city) {
		City = city;
	}

}
