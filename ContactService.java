package contact;

/*
 * Purpose:
 * manage collection of contact objects
 * perform operations of add, delete, update contact fields
 * Use in-memory storage
 * 
 * addContact(), containsKey(), deleteContact(), update###() functions
 * 
 * 
 * 
 * hashmap fast lookup ability, enforces unique, supports operations list
 */

//Imports

import java.util.HashMap; //for storing and managing key-value pairs
import java.util.Map; //for key-value storing, versatile

public class ContactService {

	//Data Structure
	
	private final Map<String, Contact> contacts = new HashMap<>(); //works like a lookup table, chosen for 0(1) lookup and built-in unique key
	
	//Add Contact
	
	public void addContact(Contact contact)	{
		if (contact == null) {
			throw new IllegalArgumentException("Contact cannot be null."); //can't add nothing
			}
		
		if (contacts.containsKey(contact.getContactId())) {
			throw new IllegalArgumentException("Contact ID already exists."); //no duplicates
		}
		
		contacts.put(contact.getContactId(), contact); //null?ID in use?add to map
	}
	
	//Delete Contact
	
	public void deleteContact(String contactId) {
		if (contactId == null) {
		    throw new IllegalArgumentException("Contact ID cannot be null.");//block null attempt
		}
		if (!contacts.containsKey(contactId)) {
			throw new IllegalArgumentException("Contact ID does not exist."); //can't remove what's not there
		}
		
		contacts.remove(contactId); //removes by ID, accurate removal methodology
	}
	
	
	
	// Get Contact test helper
	
	public Contact getContact(String contactId) {
		return contacts.get(contactId);
	}
	
	//Update Contact methods
	
	public void updateFirstName(String contactId, String firstName) {
		Contact contact = getExistingContact(contactId);
		contact.setFirstName(firstName);
	}
	
	public void updateLastName(String contactId, String lastName) {
		Contact contact = getExistingContact(contactId);
		contact.setLastName(lastName);
	}
	
	public void updatePhone(String contactId, String phone) {
        Contact contact = getExistingContact(contactId);
        contact.setPhone(phone);
	}
	
	public void updateAddress(String contactId, String address) {
		Contact contact = getExistingContact(contactId);
		contact.setAddress(address);
	}
	
	//Helper Method
	
	private Contact getExistingContact(String contactId) { //check existence, centralize validation
		Contact contact = contacts.get(contactId);
		
		if (contactId == null) {
		    throw new IllegalArgumentException("Contact ID cannot be null."); //defend against null
		}
		
		if (contact == null) {
			throw new IllegalArgumentException("Contact ID does not exist."); //can't provide what is not there

		}
		
		return contact; 
	}
}

