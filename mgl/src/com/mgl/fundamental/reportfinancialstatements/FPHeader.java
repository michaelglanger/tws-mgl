
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
 *         &lt;element ref="{}PeriodLength" minOccurs="0"/>
 *         &lt;element ref="{}periodType" minOccurs="0"/>
 *         &lt;element ref="{}UpdateType"/>
 *         &lt;element ref="{}AccountingStd" minOccurs="0"/>
 *         &lt;element ref="{}StatementDate"/>
 *         &lt;element ref="{}AuditorName" minOccurs="0"/>
 *         &lt;element ref="{}AuditorOpinion" minOccurs="0"/>
 *         &lt;element ref="{}Source"/>
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
    "periodLength",
    "periodType",
    "updateType",
    "accountingStd",
    "statementDate",
    "auditorName",
    "auditorOpinion",
    "source"
})
@XmlRootElement(name = "FPHeader")
public class FPHeader {

    @XmlElement(name = "PeriodLength")
    protected Integer periodLength;
    protected PeriodType periodType;
    @XmlElement(name = "UpdateType", required = true)
    protected UpdateType updateType;
    @XmlElement(name = "AccountingStd")
    protected String accountingStd;
    @XmlElement(name = "StatementDate", required = true)
    protected String statementDate;
    @XmlElement(name = "AuditorName")
    protected AuditorName auditorName;
    @XmlElement(name = "AuditorOpinion")
    protected AuditorOpinion auditorOpinion;
    @XmlElement(name = "Source", required = true)
    protected Source source;

    /**
     * Gets the value of the periodLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPeriodLength() {
        return periodLength;
    }

    /**
     * Sets the value of the periodLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPeriodLength(Integer value) {
        this.periodLength = value;
    }

    /**
     * Gets the value of the periodType property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodType }
     *     
     */
    public PeriodType getPeriodType() {
        return periodType;
    }

    /**
     * Sets the value of the periodType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *     
     */
    public void setPeriodType(PeriodType value) {
        this.periodType = value;
    }

    /**
     * Gets the value of the updateType property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateType }
     *     
     */
    public UpdateType getUpdateType() {
        return updateType;
    }

    /**
     * Sets the value of the updateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateType }
     *     
     */
    public void setUpdateType(UpdateType value) {
        this.updateType = value;
    }

    /**
     * Gets the value of the accountingStd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountingStd() {
        return accountingStd;
    }

    /**
     * Sets the value of the accountingStd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountingStd(String value) {
        this.accountingStd = value;
    }

    /**
     * Gets the value of the statementDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatementDate() {
        return statementDate;
    }

    /**
     * Sets the value of the statementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatementDate(String value) {
        this.statementDate = value;
    }

    /**
     * Gets the value of the auditorName property.
     * 
     * @return
     *     possible object is
     *     {@link AuditorName }
     *     
     */
    public AuditorName getAuditorName() {
        return auditorName;
    }

    /**
     * Sets the value of the auditorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuditorName }
     *     
     */
    public void setAuditorName(AuditorName value) {
        this.auditorName = value;
    }

    /**
     * Gets the value of the auditorOpinion property.
     * 
     * @return
     *     possible object is
     *     {@link AuditorOpinion }
     *     
     */
    public AuditorOpinion getAuditorOpinion() {
        return auditorOpinion;
    }

    /**
     * Sets the value of the auditorOpinion property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuditorOpinion }
     *     
     */
    public void setAuditorOpinion(AuditorOpinion value) {
        this.auditorOpinion = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

}
