

package com.journaldev.spring.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;

import iso.std.iso._20022.tech.xsd.pacs_002_001.Document;


@XmlRootElement(name="request", namespace="urn:iso:std:psys:request")
public class OABResponse {
	private String id;
	private String format;
	
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String doct) {
		this.content = doct;
	}

}
