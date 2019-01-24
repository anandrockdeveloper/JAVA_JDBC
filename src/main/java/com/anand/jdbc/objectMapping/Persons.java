package com.anand.jdbc.objectMapping;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {
	
	private List<Person> person;

	public List<Person> getPerson() {
		return person;
	}
	
	public void setPerson(List<Person> person) {
		this.person = person;
	}

	

}
