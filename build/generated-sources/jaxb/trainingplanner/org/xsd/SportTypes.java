//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.27 at 02:18:18 PM EDT 
//


package trainingplanner.org.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sportTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sportTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Strength"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Race"/>
 *     &lt;enumeration value="Day Off"/>
 *     &lt;enumeration value="Swim"/>
 *     &lt;enumeration value="Bike"/>
 *     &lt;enumeration value="Run"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sportTypes")
@XmlEnum
public enum SportTypes {

    @XmlEnumValue("Strength")
    STRENGTH("Strength"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Race")
    RACE("Race"),
    @XmlEnumValue("Day Off")
    DAY_OFF("Day Off"),
    @XmlEnumValue("Swim")
    SWIM("Swim"),
    @XmlEnumValue("Bike")
    BIKE("Bike"),
    @XmlEnumValue("Run")
    RUN("Run");
    private final String value;

    SportTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SportTypes fromValue(String v) {
        for (SportTypes c: SportTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
