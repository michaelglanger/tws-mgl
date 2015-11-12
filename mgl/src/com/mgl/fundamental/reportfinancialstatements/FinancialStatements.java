
package com.mgl.fundamental.reportfinancialstatements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}COAMap"/>
 *         &lt;element ref="{}AnnualPeriods"/>
 *         &lt;element ref="{}InterimPeriods"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "coaMap",
    "annualPeriods",
    "interimPeriods"
})
@XmlRootElement(name = "FinancialStatements")
public class FinancialStatements {

    @XmlElement(name = "COAMap", required = true)
    protected COAMap coaMap;
    @XmlElement(name = "AnnualPeriods", required = true)
    protected AnnualPeriods annualPeriods;
    @XmlElement(name = "InterimPeriods", required = true)
    protected InterimPeriods interimPeriods;

    /**
     * Gets the value of the coaMap property.
     * 
     * @return
     *     possible object is
     *     {@link COAMap }
     *     
     */
    public COAMap getCOAMap() {
        return coaMap;
    }

    /**
     * Sets the value of the coaMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link COAMap }
     *     
     */
    public void setCOAMap(COAMap value) {
        this.coaMap = value;
    }

    /**
     * Gets the value of the annualPeriods property.
     * 
     * @return
     *     possible object is
     *     {@link AnnualPeriods }
     *     
     */
    public AnnualPeriods getAnnualPeriods() {
        return annualPeriods;
    }

    /**
     * Sets the value of the annualPeriods property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnualPeriods }
     *     
     */
    public void setAnnualPeriods(AnnualPeriods value) {
        this.annualPeriods = value;
    }

    /**
     * Gets the value of the interimPeriods property.
     * 
     * @return
     *     possible object is
     *     {@link InterimPeriods }
     *     
     */
    public InterimPeriods getInterimPeriods() {
        return interimPeriods;
    }

    /**
     * Sets the value of the interimPeriods property.
     * 
     * @param value
     *     allowed object is
     *     {@link InterimPeriods }
     *     
     */
    public void setInterimPeriods(InterimPeriods value) {
        this.interimPeriods = value;
    }

}
