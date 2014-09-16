package com.dulnev.contactsexpert;

public class RawContactDataRecord {
	String caption;
	String data;
	
	public RawContactDataRecord(String aCaption, String aData){
		caption = aCaption;
		data = aData;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
