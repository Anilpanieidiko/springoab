//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.14 at 11:49:45 AM IST 
//


package com.oab.doubleentryrequest;

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
 *         &lt;element ref="{http://www.oab.com/doubleEntryRequest}DoubleEntryFinancialTransaction"/>
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
    "doubleEntryFinancialTransaction"
})
@XmlRootElement(name = "Data")
public class Data {

    @XmlElement(name = "DoubleEntryFinancialTransaction", required = true)
    protected DoubleEntryFinancialTransaction doubleEntryFinancialTransaction;

    /**
     * Gets the value of the doubleEntryFinancialTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link DoubleEntryFinancialTransaction }
     *     
     */
    public DoubleEntryFinancialTransaction getDoubleEntryFinancialTransaction() {
        return doubleEntryFinancialTransaction;
    }

    /**
     * Sets the value of the doubleEntryFinancialTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link DoubleEntryFinancialTransaction }
     *     
     */
    public void setDoubleEntryFinancialTransaction(DoubleEntryFinancialTransaction value) {
        this.doubleEntryFinancialTransaction = value;
    }

}
