
package com.mgl.fundamental.reportfinancialstatements;

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
 *         &lt;element ref="{}CoIDs"/>
 *         &lt;element ref="{}Issues"/>
 *         &lt;element ref="{}CoGeneralInfo"/>
 *         &lt;element ref="{}StatementInfo"/>
 *         &lt;element ref="{}Notes"/>
 *         &lt;element ref="{}FinancialStatements"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Major" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Minor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Revision" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "coIDs",
    "issues",
    "coGeneralInfo",
    "statementInfo",
    "notes",
    "financialStatements"
})
@XmlRootElement(name = "ReportFinancialStatements")
public class ReportFinancialStatements {

    @XmlElement(name = "CoIDs", required = true)
    protected CoIDs coIDs;
    @XmlElement(name = "Issues", required = true)
    protected Issues issues;
    @XmlElement(name = "CoGeneralInfo", required = true)
    protected CoGeneralInfo coGeneralInfo;
    @XmlElement(name = "StatementInfo", required = true)
    protected StatementInfo statementInfo;
    @XmlElement(name = "Notes", required = true)
    protected Notes notes;
    @XmlElement(name = "FinancialStatements", required = true)
    protected FinancialStatements financialStatements;
    @XmlAttribute(name = "Major")
    protected String major;
    @XmlAttribute(name = "Minor")
    protected String minor;
    @XmlAttribute(name = "Revision")
    protected String revision;

    /**
     * Gets the value of the coIDs property.
     * 
     * @return
     *     possible object is
     *     {@link CoIDs }
     *     
     */
    public CoIDs getCoIDs() {
        return coIDs;
    }

    /**
     * Sets the value of the coIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoIDs }
     *     
     */
    public void setCoIDs(CoIDs value) {
        this.coIDs = value;
    }

    /**
     * Gets the value of the issues property.
     * 
     * @return
     *     possible object is
     *     {@link Issues }
     *     
     */
    public Issues getIssues() {
        return issues;
    }

    /**
     * Sets the value of the issues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Issues }
     *     
     */
    public void setIssues(Issues value) {
        this.issues = value;
    }

    /**
     * Gets the value of the coGeneralInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CoGeneralInfo }
     *     
     */
    public CoGeneralInfo getCoGeneralInfo() {
        return coGeneralInfo;
    }

    /**
     * Sets the value of the coGeneralInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoGeneralInfo }
     *     
     */
    public void setCoGeneralInfo(CoGeneralInfo value) {
        this.coGeneralInfo = value;
    }

    /**
     * Gets the value of the statementInfo property.
     * 
     * @return
     *     possible object is
     *     {@link StatementInfo }
     *     
     */
    public StatementInfo getStatementInfo() {
        return statementInfo;
    }

    /**
     * Sets the value of the statementInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatementInfo }
     *     
     */
    public void setStatementInfo(StatementInfo value) {
        this.statementInfo = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link Notes }
     *     
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Notes }
     *     
     */
    public void setNotes(Notes value) {
        this.notes = value;
    }

    /**
     * Gets the value of the financialStatements property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialStatements }
     *     
     */
    public FinancialStatements getFinancialStatements() {
        return financialStatements;
    }

    /**
     * Sets the value of the financialStatements property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialStatements }
     *     
     */
    public void setFinancialStatements(FinancialStatements value) {
        this.financialStatements = value;
    }

    /**
     * Gets the value of the major property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the value of the major property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajor(String value) {
        this.major = value;
    }

    /**
     * Gets the value of the minor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinor() {
        return minor;
    }

    /**
     * Sets the value of the minor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinor(String value) {
        this.minor = value;
    }

    /**
     * Gets the value of the revision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevision() {
        return revision;
    }

    /**
     * Sets the value of the revision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevision(String value) {
        this.revision = value;
    }

}
