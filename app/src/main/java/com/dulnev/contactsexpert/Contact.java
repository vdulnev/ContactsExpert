package com.dulnev.contactsexpert;


public class Contact implements Comparable<Contact>{
	Integer Id;
	String LookupKey;
	String DisplayName;
	Integer count;
	
	public Integer getCount() {
		return count;
	}

	public Contact(Integer anId, String anLookupKey, String aDisplayName, Integer aCount){
		Id = anId;
		LookupKey = anLookupKey;
		DisplayName = aDisplayName;
		count = aCount;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLookupKey() {
		return LookupKey;
	}

	public void setLookupKey(String lookupKey) {
		LookupKey = lookupKey;
	}

	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Contact) {
			Contact c = (Contact)o;
			return (c.LookupKey.equals(LookupKey)) && (c.DisplayName.equals(DisplayName));
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
        return DisplayName + ": " + String.valueOf(getCount());
	}
	@Override
	public int compareTo(Contact another) {
		if (DisplayName == null) return 1;
        if (another == null) return -1;
        return (DisplayName.compareTo(another.DisplayName));
	}

}
