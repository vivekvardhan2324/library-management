# Library Management System

Servlets + JDBC + MySQL + HTML/CSS (Tomcat 9, Java 25+, Maven).

## Setup
1. Run `sql/schema.sql` in MySQL (creates `librarydb.books`: id, bname, author, type, price).
2. Set `LIBRARY_DB_USER` and `LIBRARY_DB_PASSWORD` in the environment used to start Tomcat.
   Optionally set `LIBRARY_DB_URL`; it defaults to the local `librarydb` database.
3. `mvn clean package`, then deploy `target/library-management.war` to Tomcat 9
   (or import as a Maven project in Eclipse/IntelliJ and run on Tomcat 9).
4. Open http://localhost:8080/library-management/

To load sample Class 10 books, run `sql/sample-books.sql` after creating the schema.

## Features
- Add Book (`/add`), View Books (`/books`), Update Book (`/update?id=`), Delete Book (`/delete`)

For Tomcat 10+, switch imports to `jakarta.servlet` and the dependency to `jakarta.servlet-api` 5/6.
