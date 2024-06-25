package org.example;

import org.example.library.Book;
import org.example.library.EBook;
import org.example.library.Library;
import org.example.library.MediaItem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Main class to run the Library Management System.
 */
public class LibraryManagementSystem {
    private static final Logger logger = Logger.getLogger(LibraryManagementSystem.class.getName());

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nLibrary Management System");
                System.out.println("1. Add Book");
                System.out.println("2. Add E-Book");
                System.out.println("3. Remove Item");
                System.out.println("4. Search Item by Title");
                System.out.println("5. Update Item");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String bookAuthor = scanner.nextLine();
                        System.out.print("Enter book category: ");
                        String bookCategory = scanner.nextLine();
                        System.out.print("Enter book ISBN: ");
                        String isbn = scanner.nextLine();
                        Book book = new Book(bookTitle, bookAuthor, bookCategory, isbn);
                        library.addItem(book);
                        break;

                    case 2:
                        System.out.print("Enter e-book title: ");
                        String ebookTitle = scanner.nextLine();
                        System.out.print("Enter e-book author: ");
                        String ebookAuthor = scanner.nextLine();
                        System.out.print("Enter e-book category: ");
                        String ebookCategory = scanner.nextLine();
                        System.out.print("Enter e-book download URL: ");
                        String downloadUrl = scanner.nextLine();
                        EBook eBook = new EBook(ebookTitle, ebookAuthor, ebookCategory, downloadUrl);
                        library.addItem(eBook);
                        break;

                    case 3:
                        System.out.print("Enter item title to remove: ");
                        String titleToRemove = scanner.nextLine();
                        System.out.println("1. Book");
                        System.out.println("2. E-Book");
                        System.out.print("Enter type of item to remove (1 or 2): ");
                        int removeType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        if (removeType == 1) {
                            library.removeItem(titleToRemove, Book.class);
                        } else if (removeType == 2) {
                            library.removeItem(titleToRemove, EBook.class);
                        } else {
                            System.out.println("Invalid type.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter item title to search: ");
                        String titleToSearch = scanner.nextLine();
                        System.out.println("1. Book");
                        System.out.println("2. E-Book");
                        System.out.print("Enter type of item to search (1 or 2): ");
                        int searchType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        List<MediaItem> foundItems;
                        if (searchType == 1) {
                            foundItems = library.searchItemsByTitle(titleToSearch, Book.class);
                        } else if (searchType == 2) {
                            foundItems = library.searchItemsByTitle(titleToSearch, EBook.class);
                        } else {
                            System.out.println("Invalid type.");
                            break;
                        }
                        for (MediaItem item : foundItems) {
                            System.out.println(item);
                        }
                        break;

                    case 5:
                        System.out.print("Enter current title of the item to update: ");
                        String oldTitle = scanner.nextLine();
                        System.out.println("1. Book");
                        System.out.println("2. E-Book");
                        System.out.print("Enter type of item to update (1 or 2): ");
                        int updateType = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();

                        if (updateType == 1) {
                            System.out.print("Enter new ISBN: ");
                            String newIsbn = scanner.nextLine();
                            library.updateItem(oldTitle, Book.class, newTitle, newAuthor, newCategory, newIsbn, null);
                        } else if (updateType == 2) {
                            System.out.print("Enter new download URL: ");
                            String newDownloadUrl = scanner.nextLine();
                            library.updateItem(oldTitle, EBook.class, newTitle, newAuthor, newCategory, null, newDownloadUrl);
                        } else {
                            System.out.println("Invalid type.");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        logger.info("Application exited");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                logger.warning("Invalid input: " + e.getMessage());
                scanner.nextLine();  // Consume the invalid input
            } catch (Exception e) {
                logger.severe("Error: " + e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}