package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName; //readable
import org.junit.jupiter.api.Test;

/*
 * ContactTest.java
 * 
 * purpose:
 * Unit testing for Contact class to validate all field constraints.
 * 
 * Notes:
 * test maps directly to requirements - more precise to check one thing at a time
 * "fail-fast" validation principle?
 * Negative tests ensure invalid data is rejected
 * ctrl+shift+f for the win
 * assertThrows confirms that invalid input correctly triggers an exception
 * 
 * References:
 * Clean Code by Robert C. Martin - testing principles, clarity
 * Effective Java by Joshua Bloch - validation, immutability (also in Advanced Java by Andriy Redko)
 * JUnit in Action by Peter Tahchieve - Unit testing structures
 * 
 * 
 */

public class ContactTest {
	
	//Valid Object Test
	
	@Test
	@DisplayName("Valid Contact Creation")
	void testValidationContact() {
		// ensure contact object properly created
		
		Contact contact = new Contact("123", "Frodo", "Baggins", "1234567890", "123 Bagshot Row");
		
		// verify fields assigned correctly - test links
		assertEquals("123", contact.getContactId());
		assertEquals("Frodo", contact.getFirstName());
		assertEquals("Baggins", contact.getLastName());
		assertEquals("1234567890", contact.getPhone());
		assertEquals("123 Bagshot Row", contact.getAddress());
	}
	
	//Contact ID Tests
	
	@Test
    @DisplayName("Contact ID Null")
    void testContactIdNull() {
        // Fail-fast principle: object should not be created with null ID
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "someguy", "nobody", "1234567890", "123 St");
        });
    }
	
	@Test
	@DisplayName("Contact ID Empty") //empty is different than null!
	void testContactIdEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
	        new Contact("", "John", "Doe", "1234567890", "123 St");
	    });
	}

    @Test
    @DisplayName("Contact ID Too Long")
    void testContactIdTooLong() {
        // >10 characters should be rejected
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "Capiton", "longID", "1234567890", "123 St");
        });
    }
    
    //First Name Tests
    
    @Test
    @DisplayName("First Name Null")
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", null, "MONONYM", "1234567890", "123 St");
        });
    }
    
    @Test
    @DisplayName("First Name Empty")
    void testFirstNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "", "Doe", "1234567890", "123 St");
        });
    }

    @Test
    @DisplayName("First Name Too Long")
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "VeryLongName", "Lord", "1234567890", "123 St");
        });
    }
    
    @Test
    @DisplayName("Boundary:Max First Name")
    void testFirstNameMaxLengthValid() {
        String name = "a".repeat(10);
        Contact c = new Contact("123", name, "Doe", "1234567890", "123 St");
        assertEquals(name, c.getFirstName());
    }

    // Last Name Tests

    @Test
    @DisplayName("Last Name Null")
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", null, "1234567890", "123 St");
        });
    }
    
    @Test
    @DisplayName("Last Name empty")
    void testLastNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "", "1234567890", "123 St");
        });
    }

    @Test
    @DisplayName("Last Name Too Long")
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Doctor", "VeryLongName", "1234567890", "123 St");
        });
    }

    // Phone Tests

    @Test
    @DisplayName("Phone Null")
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "noCall", "cellPhone", null, "123 St");
        });
    }

    @Test
    @DisplayName("Phone Invalid Length")
    void testPhoneInvalidLength() {
        // Regex \\d{10} requires exactly 10 digits - catches empty submissions
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Limited", "Minutes", "12345", "123 St");
        });
    }

    @Test
    @DisplayName("Phone Contains Letters")
    void testPhoneInvalidCharacters() {
        // Ensures regex validation rejects non-digit characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "spamCall", "This", "12345abcde", "123 St");
        });
    }

    // Address Tests

    @Test
    @DisplayName("Address Null")
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Homeless", "Ronin", "1234567890", null);
        });
    }
    
    @Test
    @DisplayName("Address Empty")
    void testAddressEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "1234567890", "");
        });
    }

    @Test
    @DisplayName("Address Too Long")
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "iLive", "Everywhere", "1234567890",
                    "1234567890123456789012345678901"); // 31 chars
        });
    }
    
    @Test
    @DisplayName("Boundary:Max Address")
    void testAddressMaxLengthValid() {
        String addr = "a".repeat(30);
        Contact c = new Contact("123", "John", "Doe", "1234567890", addr);
        assertEquals(addr, c.getAddress());
    }

    // Setter Tests

    @Test
    @DisplayName("Update First Name Valid")
    void testSetFirstNameValid() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 St");

        contact.setFirstName("Mike");

        assertEquals("Mike", contact.getFirstName());
    }

    @Test
    @DisplayName("Update First Name Invalid")
    void testSetFirstNameInvalid() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 St");

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });
    }
    
    @Test
    void testSetLastNameInvalid() {
        Contact c = new Contact("123", "John", "Doe", "1234567890", "123 St");

        assertThrows(IllegalArgumentException.class, () -> {
            c.setLastName(null);
        });
    }

    @Test
    void testSetPhoneInvalid() {
        Contact c = new Contact("123", "John", "Doe", "1234567890", "123 St");

        assertThrows(IllegalArgumentException.class, () -> {
            c.setPhone("123"); // invalid
        });
    }

    @Test
    void testSetAddressInvalid() {
        Contact c = new Contact("123", "John", "Doe", "1234567890", "123 St");

        assertThrows(IllegalArgumentException.class, () -> {
            c.setAddress(null);
        });
    }

}
