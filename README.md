Digital Library Book Management System
A Java console application for librarians to manage book records with CRUD operations.

📥 Setup
Prerequisites
Java JDK 11+ (tested on OpenJDK 17).

Git (to clone the repository).

Installation
Clone the repository:

bash
Copy
git clone https://github.com/yourusername/java-library-system.git  
cd java-library-system  
Compile the Java files:

bash
Copy
javac -d bin src/*.java  
🚀 Running the Application
Run the compiled program:

bash
Copy
java -cp bin Main  
📋 Features
Add a Book

Input Book ID (unique), Title, Author, Genre, and Availability Status.

Validate inputs (non-empty strings, valid status).

View All Books

Display all books in a formatted list.

Search by ID/Title

Search for books using partial or full ID/title matches.

Update Book Details

Modify title, author, genre, or availability status.

Delete a Book

Remove a book by ID.

Exit

Gracefully terminate the application.

🛠️ Assumptions
Data is stored in-memory (no database/file persistence).

Availability status is case-sensitive (Available or Checked Out).

Book IDs are unique and validated during addition.

🧩 Code Structure
Copy
src/  
├── Main.java            # Entry point with menu handling  
├── Book.java            # Model class for book data  
└── LibraryManager.java  # Service class for CRUD operations  
💡 Usage Example
Copy
===== Digital Library System =====  
1. Add a Book  
2. View All Books  
3. Search Book  
4. Update Book  
5. Delete Book  
6. Exit  

Enter your choice: 1  
Enter Book ID: 101  
Enter Title: The Great Gatsby  
Enter Author: F. Scott Fitzgerald  
Enter Genre: Classic  
Enter Availability (Available/Checked Out): Available  

Book added successfully!  
🧠 Challenges Faced
Input Validation: Ensuring non-empty strings and valid status without external libraries.

Modularity: Decoupling Book (model), LibraryManager (service), and Main (UI) for scalability.

In-Memory Storage: Managing a HashMap/ArrayList for books and ensuring thread safety.

🔮 Future Improvements
Persistence: Save/load data to/from a file (e.g., CSV, JSON) or database (SQLite).

Enhanced Search: Add filters by genre/author or fuzzy search.

GUI: Migrate to JavaFX or Swing for a user-friendly interface.

Unit Tests: Implement JUnit tests for core logic.

🔗 Links
Repository: GitHub Link

Demo: (Optional: Add a link to a screen recording or hosted demo if applicable)

📝 Notes for Reviewers
The code uses vanilla Java (no external dependencies).

Inputs are validated using basic checks (e.g., String.isEmpty()).

To test edge cases, try adding duplicate IDs or invalid availability statuses.

