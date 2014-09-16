package com.dulnev.contactsexpert;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.RawContacts.Entity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static void setTextforView(String text, View view, Integer id) {
		TextView lTView = (TextView) view.findViewById(id);
		lTView.setText(text);
	}

	public static Contact getContact(Context context, Integer id,
			String lookupKey, String dislayName) {

        return new Contact(id, lookupKey, dislayName,
                getRawContactsCount(context, id));
	}

	public static Cursor getContactsCursor(Context context) {
		Uri lUri = ContactsContract.Contacts.CONTENT_URI;
		String[] mProjection = { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.LOOKUP_KEY,
				ContactsContract.Contacts.DISPLAY_NAME };
		String mSelectionClause = null;
		String[] mSelectionArgs = null;
		String mSortOrder = ContactsContract.Contacts.DISPLAY_NAME;
        return context.getContentResolver().query(lUri, mProjection,
                mSelectionClause, mSelectionArgs, mSortOrder, null);
	}

	public static void getRawContactData(Context context, RawContact rawContact) {
		Uri lUri = ContentUris.withAppendedId(RawContacts.CONTENT_URI,
				rawContact.getId());
		Uri entityUri = Uri.withAppendedPath(lUri, Entity.CONTENT_DIRECTORY);
		Cursor lRawContactDataCursor = context.getContentResolver().query(
				entityUri,
				new String[] { Entity._ID, Entity.MIMETYPE, Entity.DATA1,
						Entity.DATA2, Entity.DATA3, Entity.DATA4, Entity.DATA5,
						Entity.DATA6, Entity.DATA7, Entity.DATA8, Entity.DATA9,
						Entity.DATA10, Entity.DATA11, Entity.DATA12,
						Entity.DATA13, Entity.DATA14, Entity.DATA15 }, null,
				null, null);
		if (lRawContactDataCursor.getCount() > 0) {
			lRawContactDataCursor.moveToPosition(-1);
			// read data from cursor and fill customer's details
			while (lRawContactDataCursor.moveToNext()) {
				String lMIMEType = lRawContactDataCursor
						.getString(lRawContactDataCursor
								.getColumnIndex(ContactsContract.Contacts.Entity.MIMETYPE));
				switch (lMIMEType) {
				case ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE:
					rawContact
							.setStructuredName(lRawContactDataCursor.getString(lRawContactDataCursor
									.getColumnIndex(ContactsContract.Contacts.Entity.DATA1)));
					break;
				case ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE:
					RawContact.Organization lOrg = rawContact.getOrganization();
					lOrg.setCompany(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA1)));
					lOrg.setType(lRawContactDataCursor.getInt(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA2)));
					lOrg.setLabel(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA3)));
					lOrg.setTitle(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA4)));
					lOrg.setDepartment(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA5)));
					lOrg.setJob_Description(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA6)));
					lOrg.setSymbol(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA7)));
					lOrg.setPhonetic_Name(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA8)));
					lOrg.setOffice_Location(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA9)));
					lOrg.setPhonetic_Name_Style(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA10)));
					break;
				case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
					RawContact.Phone lPhone = rawContact.new Phone();
					lPhone.setNumber(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA1)));
					lPhone.setType(lRawContactDataCursor.getInt(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA2)));
					lPhone.setLabel(lRawContactDataCursor.getString(lRawContactDataCursor
							.getColumnIndex(ContactsContract.Contacts.Entity.DATA3)));
					rawContact.getPhones().add(lPhone);
					break;
				case ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE:
					RawContact.Photo lPhoto = rawContact.new Photo();
					lPhoto.Photo_File_Id = lRawContactDataCursor
							.getInt(lRawContactDataCursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_FILE_ID));
					byte[] lData = lRawContactDataCursor
							.getBlob(lRawContactDataCursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO));
					if (lData != null) {
						lPhoto.Photo = BitmapFactory.decodeByteArray(lData, 0,
								lData.length);
					}
					rawContact.photo = lPhoto;
					break;
				}
			}
		}
	}

	public static List<RawContact> getRawContacts(Context context,
			Integer contactID) {
		ArrayList<RawContact> rawContacts = new ArrayList<RawContact>();
		Cursor lRawContactsCursor = context.getContentResolver().query(
				RawContacts.CONTENT_URI,
				new String[] { RawContacts._ID,
						RawContacts.DISPLAY_NAME_PRIMARY,
						RawContacts.ACCOUNT_NAME, RawContacts.ACCOUNT_TYPE },
				RawContacts.CONTACT_ID + "=?",
				new String[] { String.valueOf(contactID) }, null);
		if (lRawContactsCursor.getCount() > 0) {
			lRawContactsCursor.moveToPosition(-1);
			while (lRawContactsCursor.moveToNext()) {
				RawContact lRawContact = new RawContact();
				lRawContact.setId(lRawContactsCursor.getInt(lRawContactsCursor
						.getColumnIndex(RawContacts._ID)));
				lRawContact.accountName = lRawContactsCursor
						.getString(lRawContactsCursor
								.getColumnIndex(RawContacts.ACCOUNT_NAME));
				lRawContact.accountType = lRawContactsCursor
						.getString(lRawContactsCursor
								.getColumnIndex(RawContacts.ACCOUNT_TYPE));

				rawContacts.add(lRawContact);
			}
		}
		return rawContacts;
	}

	public static Integer getRawContactsCount(Context context, Integer contactID) {
		Cursor lRawContactsCursor = context.getContentResolver().query(
				RawContacts.CONTENT_URI,
				new String[] { RawContacts._ID,
						RawContacts.DISPLAY_NAME_PRIMARY },
				RawContacts.CONTACT_ID + "=?",
				new String[] { String.valueOf(contactID) }, null);
		return lRawContactsCursor.getCount();
	}

	public static List<RawContactData> setupRawContacts(Context context,
			Contact contact) {
		ArrayList<RawContactData> rawContacts = new ArrayList<RawContactData>();
		for (RawContact rawContact : Utils.getRawContacts(context,
				contact.getId())) {
			RawContactData c = new RawContactData();
			c.id = rawContact.getId();
			c.structuredName = rawContact.getStructuredName();
			c.photo = rawContact.getPhoto();
			c.data = new ArrayList<RawContactDataRecord>();
			for (RawContact.Phone p : rawContact.getPhones()) {
				c.data.add(new RawContactDataRecord("phone: " + p.Type,
						p.Number));
			}
			for (RawContact.Email e : rawContact.getEmails()) {
				c.data.add(new RawContactDataRecord("email: " + e.Type,
						e.Address));
			}
			c.data.add(new RawContactDataRecord("organization: "
					+ rawContact.getOrganization().getCompany(), rawContact
					.getOrganization().getDepartment()));
			c.data.add(new RawContactDataRecord("note: ", rawContact.getNote()));
			for (RawContact.StructuredPostal s : rawContact
					.getStructuredPostals()) {
				c.data.add(new RawContactDataRecord("address: ", s
						.getFormatted_Address()));
			}
			for (RawContact.Group g : rawContact.getGroups()) {
				c.data.add(new RawContactDataRecord("group: ", g.Name));
			}
			for (RawContact.WebSite s : rawContact.getWebSites()) {
				c.data.add(new RawContactDataRecord("web site: "
						+ s.getTypeAsString(), s.Url));
			}
			for (RawContact.Event e : rawContact.getEvents()) {
				c.data.add(new RawContactDataRecord("event: "
						+ e.getTypeAsString(), e.getLabel()));
			}
			for (RawContact.Relation r : rawContact.getRelations()) {
				c.data.add(new RawContactDataRecord("relation: "
						+ r.getTypeAsString(), r.Name));
			}
			c.data.add(new RawContactDataRecord("SipAddress: "
					+ rawContact.getSipAddress().getTypeAsString(), rawContact
					.getSipAddress().getSipAddress()));
		}
		return rawContacts;
	}
}
