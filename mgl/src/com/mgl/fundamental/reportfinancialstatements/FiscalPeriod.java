
package com.mgl.fundamental.reportfinancialstatements;

import com.mgl.fundamental.StatementTypeEnum;

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
 *         &lt;element ref="{}Statement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EndDate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FiscalYear" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FiscalPeriodNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statement"
})
@XmlRootElement(name = "FiscalPeriod")
public class FiscalPeriod {

    @XmlElement(name = "Statement")
    protected List<Statement> statement;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "EndDate")
    protected String endDate;
    @XmlAttribute(name = "FiscalYear")
    protected String fiscalYear;
    @XmlAttribute(name = "FiscalPeriodNumber")
    protected String fiscalPeriodNumber;

    /**
     * Gets the value of the statement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Statement }
     * 
     * 
     */
    public List<Statement> getStatement() {
        if (statement == null) {
            statement = new ArrayList<Statement>();
        }
        return this.statement;
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
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the fiscalYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Sets the value of the fiscalYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalYear(String value) {
        this.fiscalYear = value;
    }

    /**
     * Gets the value of the fiscalPeriodNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalPeriodNumber() {
        return fiscalPeriodNumber;
    }

    /**
     * Sets the value of the fiscalPeriodNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalPeriodNumber(String value) {
        this.fiscalPeriodNumber = value;
    }

    public Statement getIncomeStatement() {

        for (Statement s : statement) {
            if (StatementTypeEnum.INC.equalsName(s.getType())) {
                return s;
            }
        }

        return null;
    }

    public Statement getBalanceSheet() {

        for (Statement s : statement) {
            if (StatementTypeEnum.BAL.equalsName(s.getType())) {
                return s;
            }
        }

        return null;
    }

    public Statement getCashflowStatement() {

        for (Statement s : statement) {
            if (StatementTypeEnum.CAS.equalsName(s.getType())) {
                return s;
            }
        }

        return null;
    }

}
