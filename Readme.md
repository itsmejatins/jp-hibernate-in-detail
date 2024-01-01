# JPA, Hibernate in detail

- This repository demonstrates the details use of hibernate. It contains examples which demonstrates various use cases of using ORM.
- The database that will be used for demonstrations is H2.

# Example - Relationships

- The example to demonstrate creating entities, establishing relationships between them, and querying them is present in the package `springjpa.example.relashionships`.
- It contains four tables in `springjpa.example.relashionships.entity`.
    - Course
    - Student
    - Review
    - Passport
- These tables are related to each other as follows -
    - **Student and Passport** **→** One to one relationship between Student and Passport as one student can have only one passport (ignoring dual citizenship, etc.).
    - **Student and Course** **→** Many to many as one student can enroll many courses and one course can have many students enrolled.
    - ******************************************Course and Review →****************************************** One to many w.r.t Course and many to one w.r.t. reviews as one course has many reviews and many reviews can be given for the same course.
- We use corresponding annotations and mappings inside the entities created for these tables which can be found in the code.
- Finally we have repositories for all these entities which can be found in `springjpa.example.relashionships.repository`. These repositories contain several methods for querying these entities. They don’t make use of `Spring Data JPA` and instead use `Entity Manager`.
    - These repositories use appropriate fetching strategies like `FetchType.LAZY` and `FetchType.EAGER` depending upon the way the entities are related.
    - It makes use of **************namedQueries**************, and other methods for querying the data. The details can be found in the code.
- The corresponding unit tests for these methods of repositories are present in `src/test/java` under similar package structure as per JUnit naming conventions.

### How to run

- Open `data.sql` in `src/main/resources` and make sure that *SQL STATEMENTS FOR example.relationships* are uncommented. This file is used to put some initial data into the tables.
- Go to `springjpa.example.relationships.repository` in `src/test/java`. This package contains various unit tests for all the repositories. You can run these tests.
- These test contains methods which query the database. Later they also print the relevant database contents after modifying them. From the printed result on the console, correctness of the operations can be verified.

# Example - Inheritance

- This example shows how we can entities can be mapped to the relations in case there is IS-A relationship between the entities.
- `springjpa.example.inheritance` contains an abstract class `Employee` which has two attributes -
    - id
    - name.
- `Employee` is super class of two classes
    - `FullTimeEmployee` → has `salary` attribute.
    - `PartTimeEmploye` → has `hourlyWage` attribute.
- `EmployeeRepository.java` present in `springjpa.example.inheritance.repository` contains methods to interact with the Employee.
- We can set different inheritance strategies on `Employee` using `@Inheritance`and specify the inheritance strategy inside it. There are different inheritance strategies which are -
    - `InheritanceType.SINGLE_COLUMN` → Creates single table for all the subclasses. The common values between the subclasses have non-null values in the table. The uncommon columns are filled with null.
    - `InheritanceType.JOINED` → Creates one table for common attributes and remaining tables for subclasses with uncommon attributes.
    - `InheritanceType.TABLE_PER_CLASS` → Full fledged separate table for all sub classes. The relation corresponding to the parent class is empty.
- Another strategy is to use *************************Mapped Super Class************************* instead of inheritance.
- To see the schemas created for different inheritance strategies, open h2-console by going to `localhost:8080/h2-console`.

### How to run

- Open `data.sql` in `src/main/resources` and make sure that *SQL STATEMENTS FOR example.relationships* are commented. This is because they populate the tables created for entities in first example.
- Now run `InheritanceDemoApplication.java`.
- Once can put different strategies in `@Inheritance` can open h2 console to see the different schemas that are created.