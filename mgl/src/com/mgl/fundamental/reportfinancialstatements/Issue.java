
package com.mgl.fundamental.reportfinancialstatements;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}IssueID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Exchange"/>
 *         &lt;element ref="{}MostRecentSplit" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Order" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "issueID",
    "exchange",
    "mostRecentSplit"
})
@XmlRootElement(name = "Issue")
public class Issue {

    @XmlElement(name = "IssueID")
    protected List<IssueID> issueID;
    @XmlElement(name = "Exchange", required = true)
    protected Exchange exchange;
    @XmlElement(name = "MostRecentSplit")
    protected MostRecentSplit mostRecentSplit;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "Desc")
    protected String desc;
    @XmlAttribute(name = "Order")
    protected String order;

    /**
     * Gets the value of the issueID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issueID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssueID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IssueID }
     * 
     * 
     */
    public List<IssueID> getIssueID() {
        if (issueID == null) {
            issueID = new ArrayList<IssueID>();
        }
        return this.issueID;
    }

    public String getTicker() {
        for (IssueID iid : getIssueID()) {
            if ("Ticker".equals(iid.type)) {
                return iid.value;
            }
        }
        return null;
    }
    
    /**
     * Gets the value of the exchange property.
     * 
     * @return
     *     possible object is
     *     {@link Exchange }
     *     
     */
    public Exchange getExchange() {
        return exchange;
    }

    /**
     * Sets the value of the exchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Exchange }
     *     
     */
    public void setExchange(Exchange value) {
        this.exchange = value;
    }

    /**
     * Gets the value of the mostRecentSplit property.
     * 
     * @return
     *     possible object is
     *     {@link MostRecentSplit }
     *     
     */
    public MostRecentSplit getMostRecentSplit() {
        return mostRecentSplit;
    }

    /**
     * Sets the value of the mostRecentSplit property.
     * 
     * @param value
     *     allowed object is
     *     {@link MostRecentSplit }
     *     
     */
    public void setMostRecentSplit(MostRecentSplit value) {
        this.mostRecentSplit = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
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
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrder(String value) {
        this.order = value;
    }

}
