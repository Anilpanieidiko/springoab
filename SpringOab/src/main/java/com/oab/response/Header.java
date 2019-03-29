//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.14 at 10:46:59 AM IST 
//


package com.oab.response;

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
 *         &lt;element ref="{http://www.oab.com/Response}channel"/>
 *         &lt;element ref="{http://www.oab.com/Response}user"/>
 *         &lt;element ref="{http://www.oab.com/Response}workstation"/>
 *         &lt;element ref="{http://www.oab.com/Response}transactionDate"/>
 *         &lt;element ref="{http://www.oab.com/Response}transactionTime"/>
 *         &lt;element ref="{http://www.oab.com/Response}transactionReference"/>
 *         &lt;element ref="{http://www.oab.com/Response}channelReserved"/>
 *         &lt;element ref="{http://www.oab.com/Response}replyDate"/>
 *         &lt;element ref="{http://www.oab.com/Response}replyTime"/>
 *         &lt;element ref="{http://www.oab.com/Response}replyStatus"/>
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
    "channel",
    "user",
    "workstation",
    "transactionDate",
    "transactionTime",
    "transactionReference",
    "channelReserved",
    "replyDate",
    "replyTime",
    "replyStatus"
})
@XmlRootElement(name = "Header")
public class Header {

    @XmlElement(required = true)
    protected String channel;
    @XmlElement(required = true)
    protected String user;
    @XmlElement(required = true)
    protected String workstation;
    @XmlElement(required = true)
    protected String transactionDate;
    @XmlElement(required = true)
    protected String transactionTime;
    @XmlElement(required = true)
    protected String transactionReference;
    @XmlElement(required = true)
    protected String channelReserved;
    @XmlElement(required = true)
    protected String replyDate;
    @XmlElement(required = true)
    protected String replyTime;
    @XmlElement(required = true)
    protected String replyStatus;

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the workstation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkstation() {
        return workstation;
    }

    /**
     * Sets the value of the workstation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkstation(String value) {
        this.workstation = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDate(String value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the transactionTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTime() {
        return transactionTime;
    }

    /**
     * Sets the value of the transactionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTime(String value) {
        this.transactionTime = value;
    }

    /**
     * Gets the value of the transactionReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionReference() {
        return transactionReference;
    }

    /**
     * Sets the value of the transactionReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionReference(String value) {
        this.transactionReference = value;
    }

    /**
     * Gets the value of the channelReserved property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelReserved() {
        return channelReserved;
    }

    /**
     * Sets the value of the channelReserved property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelReserved(String value) {
        this.channelReserved = value;
    }

    /**
     * Gets the value of the replyDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyDate() {
        return replyDate;
    }

    /**
     * Sets the value of the replyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyDate(String value) {
        this.replyDate = value;
    }

    /**
     * Gets the value of the replyTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyTime() {
        return replyTime;
    }

    /**
     * Sets the value of the replyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyTime(String value) {
        this.replyTime = value;
    }

    /**
     * Gets the value of the replyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplyStatus() {
        return replyStatus;
    }

    /**
     * Sets the value of the replyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplyStatus(String value) {
        this.replyStatus = value;
    }

}
