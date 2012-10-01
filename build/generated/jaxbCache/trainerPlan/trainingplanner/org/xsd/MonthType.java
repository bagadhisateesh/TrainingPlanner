//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.01 at 04:40:36 PM EDT 
//


package trainingplanner.org.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Month details
 * 
 * <p>Java class for MonthType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonthType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="monthName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monthId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;group ref="{}weeks"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="trainingPlanId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonthType", propOrder = {
    "monthName",
    "year",
    "monthId",
    "weekType"
})
@XmlSeeAlso({
    trainingplanner.org.xsd.ICalendarType.MonthType.class
})
public class MonthType {

    @XmlElement(required = true)
    protected String monthName;
    protected int year;
    protected int monthId;
    @XmlElement(name = "WeekType", required = true)
    protected List<MonthType.WeekType> weekType;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "trainingPlanId")
    protected String trainingPlanId;

    /**
     * Gets the value of the monthName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     * Sets the value of the monthName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthName(String value) {
        this.monthName = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the monthId property.
     * 
     */
    public int getMonthId() {
        return monthId;
    }

    /**
     * Sets the value of the monthId property.
     * 
     */
    public void setMonthId(int value) {
        this.monthId = value;
    }

    /**
     * Gets the value of the weekType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weekType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeekType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonthType.WeekType }
     * 
     * 
     */
    public List<MonthType.WeekType> getWeekType() {
        if (weekType == null) {
            weekType = new ArrayList<MonthType.WeekType>();
        }
        return this.weekType;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the trainingPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainingPlanId() {
        return trainingPlanId;
    }

    /**
     * Sets the value of the trainingPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainingPlanId(String value) {
        this.trainingPlanId = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{}WeekType">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class WeekType
        extends trainingplanner.org.xsd.WeekType
    {


    }

}
