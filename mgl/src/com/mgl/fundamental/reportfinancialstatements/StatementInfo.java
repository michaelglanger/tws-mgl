
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
 *         &lt;element ref="{}COAType"/>
 *         &lt;element ref="{}BalanceSheetDisplay"/>
 *         &lt;element ref="{}CashFlowMethod"/>
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
    "coaType",
    "balanceSheetDisplay",
    "cashFlowMethod"
})
@XmlRootElement(name = "StatementInfo")
public class StatementInfo {

    @XmlElement(name = "COAType", required = true)
    protected COAType coaType;
    @XmlElement(name = "BalanceSheetDisplay", required = true)
    protected BalanceSheetDisplay balanceSheetDisplay;
    @XmlElement(name = "CashFlowMethod", required = true)
    protected CashFlowMethod cashFlowMethod;

    /**
     * Gets the value of the coaType property.
     * 
     * @return
     *     possible object is
     *     {@link COAType }
     *     
     */
    public COAType getCOAType() {
        return coaType;
    }

    /**
     * Sets the value of the coaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link COAType }
     *     
     */
    public void setCOAType(COAType value) {
        this.coaType = value;
    }

    /**
     * Gets the value of the balanceSheetDisplay property.
     * 
     * @return
     *     possible object is
     *     {@link BalanceSheetDisplay }
     *     
     */
    public BalanceSheetDisplay getBalanceSheetDisplay() {
        return balanceSheetDisplay;
    }

    /**
     * Sets the value of the balanceSheetDisplay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceSheetDisplay }
     *     
     */
    public void setBalanceSheetDisplay(BalanceSheetDisplay value) {
        this.balanceSheetDisplay = value;
    }

    /**
     * Gets the value of the cashFlowMethod property.
     * 
     * @return
     *     possible object is
     *     {@link CashFlowMethod }
     *     
     */
    public CashFlowMethod getCashFlowMethod() {
        return cashFlowMethod;
    }

    /**
     * Sets the value of the cashFlowMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashFlowMethod }
     *     
     */
    public void setCashFlowMethod(CashFlowMethod value) {
        this.cashFlowMethod = value;
    }

}
