package com.dulnev.contactsexpert;

import android.graphics.Bitmap;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class RawContact {
	
	Integer 				id;
	String 					structuredName;
	String					accountName;
	String					accountType;
	List<Phone> 			phones = new ArrayList<RawContact.Phone>();
	List<Email>				emails = new ArrayList<RawContact.Email>();
	Photo					photo;
	Organization 			organization = new Organization();
	String					note;
	List<StructuredPostal>	structuredPostals = new ArrayList<RawContact.StructuredPostal>();
	List<Group>				groups = new ArrayList<RawContact.Group>();
	List<WebSite>			webSites = new ArrayList<RawContact.WebSite>();
	List<Event>				events = new ArrayList<RawContact.Event>();
	List<Relation>			relations = new ArrayList<RawContact.Relation>();
	SipAddress				sipAddress;
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	class Phone {
		String 	Number;
		Integer Type;
		String	Label;

		public String getNumber() {
			return Number;
		}
		public void setNumber(String number) {
			Number = number;
		}
		public Integer getType() {
			return Type;
		}
		public void setType(Integer type) {
			Type = type;
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
		
		public String getTypeAsString(){
			switch (Type){
				case ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM:
					return "Custom";
				case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
					return "Home";
				case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
					return "Mobile";
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
					return "Work";
				case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK:
					return "Fax work";
				case ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME:
					return "Fax home";
				case ContactsContract.CommonDataKinds.Phone.TYPE_PAGER:
					return "Pager";
				case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
					return "Other";
				case ContactsContract.CommonDataKinds.Phone.TYPE_CALLBACK:
					return "Callback";
				case ContactsContract.CommonDataKinds.Phone.TYPE_CAR:
					return "Car";
				case ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN:
					return "Company main";
				case ContactsContract.CommonDataKinds.Phone.TYPE_ISDN:
					return "ISDN";
				case ContactsContract.CommonDataKinds.Phone.TYPE_MAIN:
					return "Main";
				case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX:
					return "Other fax";
				case ContactsContract.CommonDataKinds.Phone.TYPE_RADIO:
					return "Radio";
				case ContactsContract.CommonDataKinds.Phone.TYPE_TELEX:
					return "Telex";
				case ContactsContract.CommonDataKinds.Phone.TYPE_TTY_TDD:
					return "Tty Tdd";
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
					return "Work mobile";
				case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER:
					return "Work pager";
				case ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT:
					return "Assistant";
				case ContactsContract.CommonDataKinds.Phone.TYPE_MMS:
					return "MMS";
			}
			return "Unknown";
		}
	}
	
	class Email {
		String 	Address;
		Integer Type;
		String 	Label;
	}
	
	class Photo {
		Number Photo_File_Id;
		Bitmap Photo;
	}
	
	class Organization {
		public String getCompany() {
			return Company;
		}
		public void setCompany(String company) {
			Company = company;
		}
		public Integer getType() {
			return Type;
		}
		public void setType(Integer type) {
			Type = type;
		}
		public String getTypeAsString() {
			switch (Type) {
			case ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM:
				return "Custom";
			case ContactsContract.CommonDataKinds.Organization.TYPE_WORK:
				return "Work";
			case ContactsContract.CommonDataKinds.Organization.TYPE_OTHER:
				return "Other";
			}
			return "Unknown";
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDepartment() {
			return Department;
		}
		public void setDepartment(String department) {
			Department = department;
		}
		public String getJob_Description() {
			return Job_Description;
		}
		public void setJob_Description(String job_Description) {
			Job_Description = job_Description;
		}
		public String getSymbol() {
			return Symbol;
		}
		public void setSymbol(String symbol) {
			Symbol = symbol;
		}
		public String getPhonetic_Name() {
			return Phonetic_Name;
		}
		public void setPhonetic_Name(String phonetic_Name) {
			Phonetic_Name = phonetic_Name;
		}
		public String getOffice_Location() {
			return Office_Location;
		}
		public void setOffice_Location(String office_Location) {
			Office_Location = office_Location;
		}
		public String getPhonetic_Name_Style() {
			return Phonetic_Name_Style;
		}
		public void setPhonetic_Name_Style(String phonetic_Name_Style) {
			Phonetic_Name_Style = phonetic_Name_Style;
		}
		String 	Company;
		Integer	Type;
		String	Label;
		String	Title;
		String	Department;
		String	Job_Description;
		String	Symbol;
		String	Phonetic_Name;
		String	Office_Location;
		String	Phonetic_Name_Style;
	}
	
	class Im {
		String	Data;
		Integer	Type;
		String	Label;
		String	Protocol;
		String	CustomProtocol;
	}
	
	class Nickname {
		String	Name;
		Integer	Type;
		String	Label;
	}
	
	class StructuredPostal {
		String	Formatted_Address;
		Integer	Type;
		String	Label;
		String	Street;
		String	POBox;
		String	Neighborhood;
		String 	City;
		String	Region;
		String	Postcode;
		String	Country;
		public String getFormatted_Address() {
			return Formatted_Address;
		}
		public void setFormatted_Address(String formatted_Address) {
			Formatted_Address = formatted_Address;
		}
		public Integer getType() {
			return Type;
		}
		public void setType(Integer type) {
			Type = type;
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
		public String getStreet() {
			return Street;
		}
		public void setStreet(String street) {
			Street = street;
		}
		public String getPOBox() {
			return POBox;
		}
		public void setPOBox(String pOBox) {
			POBox = pOBox;
		}
		public String getNeighborhood() {
			return Neighborhood;
		}
		public void setNeighborhood(String neighborhood) {
			Neighborhood = neighborhood;
		}
		public String getCity() {
			return City;
		}
		public void setCity(String city) {
			City = city;
		}
		public String getRegion() {
			return Region;
		}
		public void setRegion(String region) {
			Region = region;
		}
		public String getPostcode() {
			return Postcode;
		}
		public void setPostcode(String postcode) {
			Postcode = postcode;
		}
		public String getCountry() {
			return Country;
		}
		public void setCountry(String country) {
			Country = country;
		}
	}
	
	class Group {
		Integer Id;
		String 	Name;
	}
	
	class WebSite {
		String 	Url;
		Integer	Type;
		String	Label;
		
		public String getTypeAsString(){
			switch (Type){
			case ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM:
				return Label;
			case ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE:
				return "Homepage";
			case ContactsContract.CommonDataKinds.Website.TYPE_BLOG:
				return "Blog";
			case ContactsContract.CommonDataKinds.Website.TYPE_PROFILE:
				return "Profile";
			case ContactsContract.CommonDataKinds.Website.TYPE_HOME:
				return "Home";
			case ContactsContract.CommonDataKinds.Website.TYPE_WORK:
				return "Work";
			case ContactsContract.CommonDataKinds.Website.TYPE_FTP:
				return "Ftp";
			case ContactsContract.CommonDataKinds.Website.TYPE_OTHER:
				return "Other";
			default:
				return "Unknown";
			}
		}
	}
	
	class Event {
		String 	StartDate;
		Integer	Type;
		String	Label;
		
		public String getTypeAsString(){
			switch (Type){
			case ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM:
				return Label;
			case ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY:
				return "Anniversary";
			case ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY:
				return "Birthday";
			case ContactsContract.CommonDataKinds.Event.TYPE_OTHER:
				return "Other";
			default:
				return "Unknown";
			}
		}

		public String getStartDate() {
			return StartDate;
		}

		public void setStartDate(String startDate) {
			StartDate = startDate;
		}

		public Integer getType() {
			return Type;
		}

		public void setType(Integer type) {
			Type = type;
		}

		public String getLabel() {
			return Label;
		}

		public void setLabel(String label) {
			Label = label;
		}
	}
	
	class Relation {
		String 	Name;
		Integer	Type;
		String	Label;
		
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public Integer getType() {
			return Type;
		}
		public void setType(Integer type) {
			Type = type;
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
		
		public String getTypeAsString() {
			switch (Type) {
			case ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM:
				return Label;
			case ContactsContract.CommonDataKinds.Relation.TYPE_ASSISTANT:
				return "Assistant";
			case ContactsContract.CommonDataKinds.Relation.TYPE_BROTHER:
				return "Brother";
			case ContactsContract.CommonDataKinds.Relation.TYPE_CHILD:
				return "Child";
			case ContactsContract.CommonDataKinds.Relation.TYPE_DOMESTIC_PARTNER:
				return "Domestic partner";
			case ContactsContract.CommonDataKinds.Relation.TYPE_FATHER:
				return "Father";
			case ContactsContract.CommonDataKinds.Relation.TYPE_FRIEND:
				return "Friend";
			case ContactsContract.CommonDataKinds.Relation.TYPE_MANAGER:
				return "Manager";
			case ContactsContract.CommonDataKinds.Relation.TYPE_MOTHER:
				return "Mother";
			case ContactsContract.CommonDataKinds.Relation.TYPE_PARENT:
				return "Parent";
			case ContactsContract.CommonDataKinds.Relation.TYPE_PARTNER:
				return "Partner";
			case ContactsContract.CommonDataKinds.Relation.TYPE_REFERRED_BY:
				return "Referred by";
			case ContactsContract.CommonDataKinds.Relation.TYPE_RELATIVE:
				return "Relative";
			case ContactsContract.CommonDataKinds.Relation.TYPE_SISTER:
				return "Sister";
			case ContactsContract.CommonDataKinds.Relation.TYPE_SPOUSE:
				return "Spouse";
			default:
				return "Unknown";
			}
		}
	}
	
	class SipAddress {
		String	SipAddress;
		Integer	Type;
		String	Label;
		
		public String getSipAddress() {
			return SipAddress;
		}



		public void setSipAddress(String sipAddress) {
			SipAddress = sipAddress;
		}



		public Integer getType() {
			return Type;
		}



		public void setType(Integer type) {
			Type = type;
		}



		public String getLabel() {
			return Label;
		}



		public void setLabel(String label) {
			Label = label;
		}



		public String getTypeAsString(){
			switch(Type){
			case ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM:
				return "Custom";
			case ContactsContract.CommonDataKinds.SipAddress.TYPE_HOME:
				return "Home";
			case ContactsContract.CommonDataKinds.SipAddress.TYPE_OTHER:
				return "Other";
			case ContactsContract.CommonDataKinds.SipAddress.TYPE_WORK:
				return "Work";
			default:
				return "Unknown";
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStructuredName() {
		return structuredName;
	}

	public void setStructuredName(String structuredName) {
		this.structuredName = structuredName;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<StructuredPostal> getStructuredPostals() {
		return structuredPostals;
	}

	public void setStructuredPostals(List<StructuredPostal> structuredPostals) {
		this.structuredPostals = structuredPostals;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<WebSite> getWebSites() {
		return webSites;
	}

	public void setWebSites(List<WebSite> webSites) {
		this.webSites = webSites;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public SipAddress getSipAddress() {
		return sipAddress;
	}

	public void setSipAddress(SipAddress sipAddress) {
		this.sipAddress = sipAddress;
	}

	@Override
	public String toString() {
		return "RawContact [structuredName=" + structuredName
				+ ", accountName=" + accountName + ", accountType="
				+ accountType + "]";
	}
}
