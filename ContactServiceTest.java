package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach; //before each test
import org.junit.jupiter.api.DisplayName; //Provides a readable name for the test method or class
import org.junit.jupiter.api.Test;

/*
 * ContactServiceTest.java
 * 
 * Purpose:
 * Test service level operations: add, delete, update
 * 
 * References:
 * Clean Code by Robert C. Martin, chapter 1
 * Effective Java by Joshua Bloch, 'check parameters for validity'
 * JUnit in Action by Petar Tahchiev, emph on isolation and ability to repeat of tests using setup methods
 * 
 * so much reading
 */

public class ContactServiceTest {
	
	private ContactService service;
	
	//SETUP - Run before each?yes
	
	@BeforeEach
	void setUp() {
		//Clean Code Principle
		// Each test must run in isolation (no shared state between tests)
        // This prevents "test pollution"? nickname by sister or actual term?

        service = new ContactService();
    }

    // Helper method to reduce duplication
    private Contact createValidContact(String id) {
        // JUnit in Action:
        // Reusable test data improves maintainability and readability
        return new Contact(id, "John", "Doe", "1234567890", "123 Main St");
    }


    // Add contact Test
    
    // Possible TODO's:
    // "Should" pattern?
    // "Given/When/Then" pattern (Condensed)?


    @Test
    @DisplayName("Add Contact - Valid") //The customization provides really good flexibility
    void testAddContactValid() {
        Contact contact = createValidContact("1");

        service.addContact(contact);

        // Verify the contact exists in the system
        assertNotNull(service.getContact("1"));
    }
    
    @Test
    void testGetContactNullId() {
        assertNull(service.getContact(null)); //verify nothing is null
    }

    @Test
    @DisplayName("Add Contact - Duplicate ID")
    void testAddContactDuplicateId() {
        Contact contact1 = createValidContact("1");
        Contact contact2 = createValidContact("1");

        service.addContact(contact1);

        // Effective Java:
        // Validate method arguments -> duplicate IDs must be rejected
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2);
        });
    }

    @Test
    @DisplayName("Add Contact - Null Input")
    void testAddContactNull() {
        // Defensive programming (Effective Java):
        // Methods should reject null inputs explicitly
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }

    // Delete Contact Test
  
    @Test
    @DisplayName("Delete Contact - Valid ID")
    void testDeleteContactValid() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        service.deleteContact("1");

        // After deletion, contact should no longer exist
        assertNull(service.getContact("1"));
    }

    @Test
    @DisplayName("Delete Contact - Invalid ID")
    void testDeleteContactInvalid() {
        // Clean Code:
        // Tests should verify both success AND failure paths
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("999");
        });
    }
    
    @Test
    void testDeleteContactNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(null);
        });
    }

    // Update Contact Test

    @Test
    @DisplayName("Update First Name")
    void testUpdateFirstName() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        service.updateFirstName("1", "Mike");

        // Verify update occurred
        assertEquals("Mike", service.getContact("1").getFirstName());
    }

    @Test
    @DisplayName("Update Last Name")
    void testUpdateLastName() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        service.updateLastName("1", "Smith");

        assertEquals("Smith", service.getContact("1").getLastName());
    }

    @Test
    @DisplayName("Update Phone")
    void testUpdatePhone() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        service.updatePhone("1", "0987654321");

        assertEquals("0987654321", service.getContact("1").getPhone());
    }

    @Test
    @DisplayName("Update Address")
    void testUpdateAddress() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        service.updateAddress("1", "456 New St");

        assertEquals("456 New St", service.getContact("1").getAddress());
    }

    // Update Fail class
    
    @Test
    void testUpdateWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName(null, "Test");
        });
    }

    @Test
    @DisplayName("Update Nonexistent Contact")
    void testUpdateNonexistentContact() {
        // Ensures system does not silently fail
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("999", "Ghost");
        });
    }
    
    @Test
    void testUpdateLastNameInvalid() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("1", null);
        });
    }

    @Test
    void testUpdateAddressInvalid() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("1", null);
        });
    }

    @Test
    @DisplayName("Update Invalid Phone")
    void testUpdateInvalidPhone() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        // Validation should propagate from Contact class
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("1", "123"); // invalid format
        });
    }

    // Design Validation Test

    @Test
    @DisplayName("Ensure Contact ID Cannot Be Modified")
    void testContactIdImmutable() {
        Contact contact = createValidContact("1");
        service.addContact(contact);

        // No setter exists, design enforces immutability

        assertEquals("1", service.getContact("1").getContactId());
    }
}
