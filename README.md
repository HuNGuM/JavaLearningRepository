# Service for booking a pool visit

The service defines 3 roles:
- Administrator – can add, delete, and edit pool staff.
- Sales Manager – can book a pool visit (with or without a coach).
- Coach – can view which visits are booked with them.

# Technologies
- Java
- Spring
- Postgres
- Kafka
- Hibernate

# Using my LiquibaseClone
- Start the MigrationMain class
- Ensure all the scripts have been applied
- Start the JavaLearningApplication
- Use Postman (or any other tool) to test some requests (ensure employee with "Administrator" rights has been added to "employees" table)
