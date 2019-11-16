import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Book {
    String name;
    Integer id;
    Boolean isReserved;
    Boolean isIssued;
    Date issuedOn;

    public Book(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.isReserved = false;
        this.isIssued = false;
        this.issuedOn = null;
    }
}

class Account {
    String name;
    Integer id;
    LinkedList<Book> reservedBooks;
    LinkedList<Book> issuedBooks;

    public Account(String name, Integer id) {
        this.name = name;
        this.id = id;
        this.reservedBooks = new LinkedList<>();
        this.issuedBooks = new LinkedList<>();
    }

    public void reserveBook(Book b) {
        this.reservedBooks.add(b);
        b.isReserved = true;
    }

    public void unReserveBook(Book b) {
        this.reservedBooks.remove(b);
        b.isReserved = false;
    }

    public void issueBook(Book b) {
        this.issuedBooks.add(b);
        b.isIssued = true;
        b.issuedOn = new Date();
    }

    public void returnBook(Book b) {
        this.issuedBooks.remove(b);
        b.isIssued = false;
        b.issuedOn = null;
    }


}

public class Library {
    //    account, book
    public Map<Integer, Book> books;
    public Map<Integer, Account> accounts;

    Library() {
        books = new HashMap<Integer, Book>();
        accounts = new HashMap<Integer, Account>();

    }

    void init() {
        Account acc1 = new Account("owner_name1", 1);
        Account acc2 = new Account("owner_name2", 2);
        Account acc3 = new Account("owner_name3", 3);
        this.accounts.put(acc1.id, acc1);
        this.accounts.put(acc2.id, acc2);
        this.accounts.put(acc3.id, acc3);
        Book b1 = new Book("book_name1", 1);
        Book b2 = new Book("book_name2", 2);
        Book b3 = new Book("book_name3", 3);
        Book b4 = new Book("book_name4", 4);
        this.books.put(b1.id, b1);
        this.books.put(b2.id, b2);
        this.books.put(b3.id, b3);
        this.books.put(b4.id, b4);


    }

    Account createAccount(String name, Integer id) {
        Account acc = new Account(name, id);
        this.accounts.put(acc.id, acc);
        return acc;
    }

    Book createBook(String name, Integer id) {
        Book b = new Book(name, id);
        this.books.put(b.id, b);
        return b;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Library library = new Library();
        library.init();

        library.createAccount("Acc4", 4);

        Book bookToReserve = library.books.get(1);
        library.accounts.get(1).reserveBook(bookToReserve);
//        get book object from book id
        library.accounts.get(1).unReserveBook(bookToReserve);

        Book bookToIssue = library.books.get(2);
        library.accounts.get(1).issueBook(bookToIssue);
//        get book object from book id
        library.accounts.get(1).returnBook(bookToIssue);

        LinkedList<Book> availableBooks = new LinkedList<>();
        for (Book book : library.books.values()) {
            if (!book.isIssued && !book.isReserved)
                availableBooks.add(book);
        }
        System.out.println(availableBooks);

    }

}
