import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

enum Availability {
    AVAILABLE,
    CHECKED_OUT;


    public static Availability fromNumber(int num) {
        return num == 1 ? AVAILABLE : CHECKED_OUT;
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private Availability availability;

    public Book(String id, String title, String author, String genre, Availability availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Getters/setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Availability getAvailability() { return availability; }
    public void setAvailability(Availability availability) { this.availability = availability; }
}

public class LibraryManagementSystem {
    private static final List<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
   // private static boolean debugMode = false;

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        while(true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add New Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Find a Book");
            System.out.println("4. Edit Book Details");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit Program");
            System.out.print("What would you like to do? (1-6): ");

            String choice = scanner.nextLine().trim();

            switch(choice) {  // case handling
                case "1": addBook(); break;
                case "2": showAllBooks(); break;
                case "3": searchBooks(); break;
                case "4": updateBook(); break;
                case "5": deleteBook(); break;
                case "6":
                    System.out.println("\nThanks for using Library Management System!");
                    return;
                default:
                    System.out.println("Please pick correct option. Try 1-6!");
            }
        }
    }

    private static void addBook() {
        try {  // try-block
            System.out.println("\n~~ Add New Book ~~");

            String id = getUniqueId();
            if(id == null) return;

            String title = getInputWithBack("Book title: ", true);
            if(title == null) return;

            String author = getInputWithBack("Author name: ", true);
            if(author == null) return;

            String genre = getInputWithBack("Genre (optional): ", false);

            Availability status = getStatus();
            if(status == null) return;

            books.add(new Book(id, title, author, genre, status));
            System.out.println("\n✓ Book added successfully!");

        } catch(Exception e) {  //  catch
            System.out.println("Whoops! Something went wrong.");
        }
    }

    private static String getUniqueId() {
        while(true) {
            String input = getInputWithBack("Enter unique ID: ", true);
            if(input == null) return null;

            // loop check
            boolean exists = books.stream()
                    .anyMatch(b -> b.getId().equalsIgnoreCase(input));
            if(!exists) return input;

            System.out.println("ID already exists! Try another.");
        }
    }

    private static Availability getStatus() {
        System.out.println("\nAvailability Status:");
        System.out.println("1. Available");
        System.out.println("2. Checked Out");
        System.out.println("3. Cancel and Return");

        while(true) {
            String choice = getInputWithBack("Choose (1-3): ", false);
            if(choice == null) return null;

            switch(choice) {
                case "1": return Availability.AVAILABLE;
                case "2": return Availability.CHECKED_OUT;
                case "3": return null;
                default: System.out.println("Just 1-3 please!");
            }
        }
    }

    private static void showAllBooks() {
        if(books.isEmpty()) {
            System.out.println("\nThe library is empty!");
            return;
        }

        System.out.println("\n=== Library Collection ===");
        books.forEach(book -> {
            System.out.println("\nID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + (book.getGenre().isEmpty() ? "-" : book.getGenre()));
            System.out.println("Status: " + book.getAvailability());
            System.out.println("-------------------");
        });
    }

    private static void searchBooks() {
        String term = getInputWithBack("\nSearch by ID or title (type 'back' to cancel): ", false);
        if(term == null || term.isEmpty()) return;

        List<Book> results = new ArrayList<>();
        for(Book b : books) {  // loop choice
            if(b.getId().equalsIgnoreCase(term) ||
                    b.getTitle().toLowerCase().contains(term.toLowerCase())) {
                results.add(b);
            }
        }

        if(results.isEmpty()) {
            System.out.println("No matches found!");
            return;
        }

        System.out.println("\nFound " + results.size() + " matches:");
        results.forEach(b -> {
            System.out.println("[" + b.getId() + "] " + b.getTitle() +
                    " by " + b.getAuthor());
        });
    }

    private static void updateBook() {
        String id = getInputWithBack("\nEnter book ID to update (or 'back'): ", false);
        if(id == null) return;

        Book target = null;
        for(Book b : books) {  //loop running
            if(b.getId().equalsIgnoreCase(id)) {
                target = b;
                break;
            }
        }

        if(target == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.println("\nCurrent Details:");
        showBookDetails(target);

        System.out.println("\nWhat to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Status");
        System.out.println("5. Cancel");
        String choice = getInputWithBack("Pick (1-5): ", false);

        if(choice == null || choice.equals("5")) return;

        switch(choice) {
            case "1":
                String newTitle = getInputWithBack("New title: ", true);
                if(newTitle != null) target.setTitle(newTitle);
                break;
            case "2":
                String newAuthor = getInputWithBack("New author: ", true);
                if(newAuthor != null) target.setAuthor(newAuthor);
                break;
            case "3":
                String newGenre = getInputWithBack("New genre: ", false);
                target.setGenre(newGenre != null ? newGenre : "");
                break;
            case "4":
                Availability newStatus = getStatus();
                if(newStatus != null) target.setAvailability(newStatus);
                break;
            default:
                System.out.println("Didn't change anything.");
        }

        System.out.println("\nBook details updated!");
    }

    private static void deleteBook() {
        String id = getInputWithBack("\nEnter ID to delete (or 'back'): ", false);
        if(id == null) return;

        boolean removed = false;
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getId().equalsIgnoreCase(id)) {
                showBookDetails(books.get(i));
                String confirm = getInputWithBack("Really delete? (y/n): ", false);
                if(confirm != null && confirm.equalsIgnoreCase("y")) {
                    books.remove(i);
                    removed = true;
                }
                break;
            }
        }

        System.out.println(removed ? "Book deleted!" : "Nothing deleted.");
    }


    private static String getInputWithBack(String prompt, boolean required) {
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if(input.equalsIgnoreCase("back")) return null;
            if(required && input.isEmpty()) {
                System.out.println("This can't be empty!");
                continue;
            }
            return input;
        }
    }

    private static void showBookDetails(Book b) {
        System.out.println("\nID: " + b.getId());
        System.out.println("Title: " + b.getTitle());
        System.out.println("Author: " + b.getAuthor());
        System.out.println("Genre: " + (b.getGenre().isEmpty() ? "N/A" : b.getGenre()));
        System.out.println("Status: " + b.getAvailability());
    }


//    private static void toggleDebug() {
//        debugMode = !debugMode;
//        System.out.println("Debug mode: " + (debugMode ? "ON" : "OFF"));
//    }
}