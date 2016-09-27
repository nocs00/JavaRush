package com.javarush.test.level33.lesson10.bonus01;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "anExample")
@XmlRootElement
public class First {
    @XmlElement(nillable = false)
    public String[] second = new String[]{"some string", "some string", "need CDATA because of < and >", ""};
}
