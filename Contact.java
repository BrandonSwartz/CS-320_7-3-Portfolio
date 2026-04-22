package contact;

//imports - none

/**
 * Contact.java
 * 
 * Purpose:
 * Represents a single contact with validated fields.
 * 
 * Notes:
 * - contactId is immutable after creation.
 * - itemized field_to_validation to make more modular, incremental for extended building
 * 
 * References:
 * https://docs.oracle.com
 * Head First Java 2e by Kathy Sierra & Bert Bates
 * Java Complete Reference 9e by Herbert Schildt
 * Hands-On Design Patterns with Java by Edward Lavieri
 */
public class Contact {

	//Fields
	
	private final String contactId;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	//Constructor
	
	public Contact(String contactID, String firstName, String lastName, String phone, String address) {
		validateContactId(contactID);
		validateFirstName(firstName);
		validateLastName(lastName);
		validatePhone(phone);
		validateAddress(address);	

		this.contactId = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}
	
	//Getters "public *type* get###() {return ###; }"
	
	public String getContactId() {return contactId; }
	public String getFirstName() {return firstName; }
	public String getLastName() {return lastName; }
	public String getPhone() {return phone; }
	public String getAddress() {return address; }
	
	//Setters (for fields that can update) "public void set###(*type* ###) { validate###(###); this.### = ###;"
	
	public void setFirstName(String firstName) {
		validateFirstName(firstName);
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		validateLastName(lastName);
		this.lastName = lastName;
	}
	
	public void setPhone(String phone) {
		validatePhone(phone);
		this.phone = phone;
	}
	
	public void setAddress(String address) {
		validateAddress(address);
		this.address = address;
	}
	
	//Validation Methods
	
	/**
	 * == null, checks but no value not allow
	 * |Java Complete Reference by Herbert Schildt chapter 4
	 * A.K.A Sanity Checks
	 * Advanced Java by Andriy Redko section 6.11
	 */
	
	private void validateContactId(String contactId) {
		if (contactId == null || contactId.length() > 10 || contactId.isEmpty()) {
			throw new IllegalArgumentException("Contact ID must be non-null, character limit is 10."); //throw is to send an exception immediately
		}
	}
	
	private void validateFirstName(String firstName) {
		if (firstName == null || firstName.length() > 10 || firstName.isEmpty()) {
			throw new IllegalArgumentException("First name must be non-null, character limit is 10.");
		}
	}
	
	private void validateLastName(String lastName) {
		if (lastName == null || lastName.length() > 10 || lastName.isEmpty()) {
			throw new IllegalArgumentException("Last name must be non-null, character limit is 10.");
		}
	}
	
	private void validatePhone(String phone) {
		if (phone == null || !phone.matches("\\d{10}")) { // "\\d{10}" ensures its exactly 10 digits
			throw new IllegalArgumentException("Phone must be exactly 10 digits.");
		}
	}
	
	private void validateAddress(String address) {
		if (address == null || address.length() > 30 || address.isEmpty()) {
			throw new IllegalArgumentException("Address must be non-null, character limit is 30.");
		}
	}
}
