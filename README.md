# CS-320_7-3-Portfolio_Reflection

### How can I ensure that my code, program, or software is functional and secure?
To ensure that my code is both functional and secure, I rely on a combination of strict input validation, defensive programming, and comprehensive unit testing. Across the Contact, Task, and Appointment services, I implemented fail-fast validation to immediately reject invalid data, such as null inputs, improperly formatted fields, or values exceeding defined constraints. 

Functionality is verified through JUnit testing, where I tested both valid and invalid scenarios to ensure correct behavior across all execution paths. Security, in this context, is reinforced through input sanitization and validation, preventing improper data from entering the system. By combining validation at the model level with enforcement at the service level, I ensured data integrity and reduced the likelihood of unexpected system behavior.

## How do I interpret user needs and incorporate them into a program?
Interpreting user needs begins with translating requirements into clearly defined constraints and behaviors within the system. In these projects, requirements such as field length limits, unique identifiers, and date restrictions were treated as contractual obligations that the system must enforce.

I approached this by mapping each requirement directly to test cases. For example, if a requirement specified that a phone number must be exactly 10 digits, I created both positive tests (valid input) and negative tests (invalid formats) to ensure compliance. This approach ensures that user expectations are not only implemented but also verifiable through automated testing.

Additionally, working with structured requirements reinforced the importance of clarity. When requirements are ambiguous, breaking them down into smaller, testable components helps ensure that the final implementation aligns with the intended functionality.

## How do I approach designing software?

My approach to software design is incremental and validation-driven. I focus on building small, well-defined components that enforce their own constraints, which promotes reliability and maintainability. Each class is designed with a clear responsibility, following principles such as encapsulation and separation of concerns.

I also prioritize designing with testing in mind. By structuring code to be easily testable, I can validate behavior early and often, reducing the cost of defects. This includes using helper methods, maintaining clean and reusable test structures, and ensuring that each test is independent.

Overall, my design approach emphasizes clarity, modularity, and alignment with requirements. By combining disciplined design practices with thorough testing, I am able to produce software that is both reliable and adaptable.
