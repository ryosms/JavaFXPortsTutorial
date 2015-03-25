package com.ryosms.address.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Personリストのラッパークラス
 * PersonリストをXMLとして保存する際に使用する
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {
    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
