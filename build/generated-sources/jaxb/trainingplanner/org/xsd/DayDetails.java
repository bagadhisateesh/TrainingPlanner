//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.13 at 07:40:31 AM EDT 
//


package trainingplanner.org.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * extensible day details which should be measureable and trackable.
 * 
 * <p>Java class for dayDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dayDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wakeupTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="sleepQuality" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="energyLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dayDetails", propOrder = {
    "wakeupTime",
    "sleepQuality",
    "energyLevel"
})
public class DayDetails {

    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar wakeupTime;
    @XmlElement(defaultValue = "0")
    protected int sleepQuality;
    @XmlElement(defaultValue = "0")
    protected int energyLevel;

    /**
     * Gets the value of the wakeupTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWakeupTime() {
        return wakeupTime;
    }

    /**
     * Sets the value of the wakeupTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWakeupTime(XMLGregorianCalendar value) {
        this.wakeupTime = value;
    }

    /**
     * Gets the value of the sleepQuality property.
     * 
     */
    public int getSleepQuality() {
        return sleepQuality;
    }

    /**
     * Sets the value of the sleepQuality property.
     * 
     */
    public void setSleepQuality(int value) {
        this.sleepQuality = value;
    }

    /**
     * Gets the value of the energyLevel property.
     * 
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Sets the value of the energyLevel property.
     * 
     */
    public void setEnergyLevel(int value) {
        this.energyLevel = value;
    }

}
