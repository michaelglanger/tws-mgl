
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
 *         &lt;element ref="{}CFAAvailability"/>
 *         &lt;element ref="{}IAvailability"/>
 *         &lt;element ref="{}ISIAvailability"/>
 *         &lt;element ref="{}BSIAvailability"/>
 *         &lt;element ref="{}CFIAvailability"/>
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
    "cfaAvailability",
    "iAvailability",
    "isiAvailability",
    "bsiAvailability",
    "cfiAvailability"
})
@XmlRootElement(name = "Notes")
public class Notes {

    @XmlElement(name = "CFAAvailability", required = true)
    protected CFAAvailability cfaAvailability;
    @XmlElement(name = "IAvailability", required = true)
    protected IAvailability iAvailability;
    @XmlElement(name = "ISIAvailability", required = true)
    protected ISIAvailability isiAvailability;
    @XmlElement(name = "BSIAvailability", required = true)
    protected BSIAvailability bsiAvailability;
    @XmlElement(name = "CFIAvailability", required = true)
    protected CFIAvailability cfiAvailability;

    /**
     * Gets the value of the cfaAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link CFAAvailability }
     *     
     */
    public CFAAvailability getCFAAvailability() {
        return cfaAvailability;
    }

    /**
     * Sets the value of the cfaAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFAAvailability }
     *     
     */
    public void setCFAAvailability(CFAAvailability value) {
        this.cfaAvailability = value;
    }

    /**
     * Gets the value of the iAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link IAvailability }
     *     
     */
    public IAvailability getIAvailability() {
        return iAvailability;
    }

    /**
     * Sets the value of the iAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link IAvailability }
     *     
     */
    public void setIAvailability(IAvailability value) {
        this.iAvailability = value;
    }

    /**
     * Gets the value of the isiAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link ISIAvailability }
     *     
     */
    public ISIAvailability getISIAvailability() {
        return isiAvailability;
    }

    /**
     * Sets the value of the isiAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISIAvailability }
     *     
     */
    public void setISIAvailability(ISIAvailability value) {
        this.isiAvailability = value;
    }

    /**
     * Gets the value of the bsiAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link BSIAvailability }
     *     
     */
    public BSIAvailability getBSIAvailability() {
        return bsiAvailability;
    }

    /**
     * Sets the value of the bsiAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link BSIAvailability }
     *     
     */
    public void setBSIAvailability(BSIAvailability value) {
        this.bsiAvailability = value;
    }

    /**
     * Gets the value of the cfiAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link CFIAvailability }
     *     
     */
    public CFIAvailability getCFIAvailability() {
        return cfiAvailability;
    }

    /**
     * Sets the value of the cfiAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link CFIAvailability }
     *     
     */
    public void setCFIAvailability(CFIAvailability value) {
        this.cfiAvailability = value;
    }

}
