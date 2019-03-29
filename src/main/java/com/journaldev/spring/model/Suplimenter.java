package com.journaldev.spring.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="supplementaryData")
public class Suplimenter {
	private Items items;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

}
