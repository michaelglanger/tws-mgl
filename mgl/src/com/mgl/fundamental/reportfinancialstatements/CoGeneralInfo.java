
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
 *         &lt;element ref="{}CoStatus"/>
 *         &lt;element ref="{}CoType"/>
 *         &lt;element ref="{}LastModified"/>
 *         &lt;element ref="{}LatestAvailableAnnual"/>
 *         &lt;element ref="{}LatestAvailableInterim"/>
 *         &lt;element ref="{}ReportingCurrency"/>
 *         &lt;element ref="{}MostRecentExchange"/>
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
    "coStatus",
    "coType",
    "lastModified",
    "latestAvailableAnnual",
    "latestAvailableInterim",
    "reportingCurrency",
    "mostRecentExchange"
})
@XmlRootElement(name = "CoGeneralInfo")
public class CoGeneralInfo {

    @XmlElement(name = "CoStatus", required = true)
    protected CoStatus coStatus;
    @XmlElement(name = "CoType", required = true)
    protected CoType coType;
    @XmlElement(name = "LastModified", required = true)
    protected String lastModified;
    @XmlElement(name = "LatestAvailableAnnual", required = true)
    protected String latestAvailableAnnual;
    @XmlElement(name = "LatestAvailableInterim", required = true)
    protected String latestAvailableInterim;
    @XmlElement(name = "ReportingCurrency", required = true)
    protected ReportingCurrency reportingCurrency;
    @XmlElement(name = "MostRecentExchange", required = true)
    protected MostRecentExchange mostRecentExchange;

    /**
     * Gets the value of the coStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CoStatus }
     *     
     */
    public CoStatus getCoStatus() {
        return coStatus;
    }

    /**
     * Sets the value of the coStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoStatus }
     *     
     */
    public void setCoStatus(CoStatus value) {
        this.coStatus = value;
    }

    /**
     * Gets the value of the coType property.
     * 
     * @return
     *     possible object is
     *     {@link CoType }
     *     
     */
    public CoType getCoType() {
        return coType;
    }

    /**
     * Sets the value of the coType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoType }
     *     
     */
    public void setCoType(CoType value) {
        this.coType = value;
    }

    /**
     * Gets the value of the lastModified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * Sets the value of the lastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModified(String value) {
        this.lastModified = value;
    }

    /**
     * Gets the value of the latestAvailableAnnual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestAvailableAnnual() {
        return latestAvailableAnnual;
    }

    /**
     * Sets the value of the latestAvailableAnnual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestAvailableAnnual(String value) {
        this.latestAvailableAnnual = value;
    }

    /**
     * Gets the value of the latestAvailableInterim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestAvailableInterim() {
        return latestAvailableInterim;
    }

    /**
     * Sets the value of the latestAvailableInterim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestAvailableInterim(String value) {
        this.latestAvailableInterim = value;
    }

    /**
     * Gets the value of the reportingCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link ReportingCurrency }
     *     
     */
    public ReportingCurrency getReportingCurrency() {
        return reportingCurrency;
    }

    /**
     * Sets the value of the reportingCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportingCurrency }
     *     
     */
    public void setReportingCurrency(ReportingCurrency value) {
        this.reportingCurrency = value;
    }

    /**
     * Gets the value of the mostRecentExchange property.
     * 
     * @return
     *     possible object is
     *     {@link MostRecentExchange }
     *     
     */
    public MostRecentExchange getMostRecentExchange() {
        return mostRecentExchange;
    }

    /**
     * Sets the value of the mostRecentExchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link MostRecentExchange }
     *     
     */
    public void setMostRecentExchange(MostRecentExchange value) {
        this.mostRecentExchange = value;
    }

}
