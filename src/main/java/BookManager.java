import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


public class BookManager implements BookService, Runnable {


    LocalDateTime date;
    private List<BookAndOwner> bookAndOwners = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<BookIssue> bookIssues = new ArrayList<>();
    private PriorityQueue<BookRequest> bookRequests = new PriorityQueue<>();
    private List<BookAndOwner> bookAndOwnerFilter = new ArrayList<>();

    public BookManager() {
        members.add(new Member("Aryan", 101));
        members.add(new Member("Rahul", 102));
        members.add(new Member("Tanish", 103));
        members.add(new Member("Rohit", 104));

        bookAndOwners.add(new BookAndOwner(new Books("John Bidwell", "A Boy at Seven", "1-86092-022-5"), members.get(0), 1));
        bookAndOwners.add(new BookAndOwner(new Books("JK Rowling", "Harry Potter", "9780747532743"), members.get(1), 3));
        bookAndOwners.add(new BookAndOwner(new Books("Charles Dickens ", "The Signalman", "9780747532743"), members.get(2), 3));
        bookAndOwners.add(new BookAndOwner(new Books("Stephen Crane", "The Open Boat", "1-86092-038-1"), members.get(3), 4));
        bookAndOwners.add(new BookAndOwner(new Books("Ngaio Marsh", "Death on the Air", "1-86092-020-9"), members.get(2), 5));
        bookAndOwners.add(new BookAndOwner(new Books("Ngaio Marsh", "Death on the Air", "1-86092-020-9"), members.get(1), 5));
    }

    public List<Member> getMembers() {
        return members;
    }

    public String addBooks() {

        return "Books Added Successfully";
    }

    // Showing booklist with owner
    public List<BookAndOwner> getBookAndOwners() {
        return bookAndOwners;
    }


    public PriorityQueue<BookRequest> getBookRequests() {
        return bookRequests;
    }

    // Searching for books, by name , author, isbn number using filter and issuing books by index number
    // calling recursively if want to search again or book not found
    // here bookAndOwner1 is the filtered list from bookAndOwner  List
    public List<BookAndOwner> search(int p, String search) {

        if (p == 1) {
            bookAndOwnerFilter = bookAndOwners.stream().filter(i -> i.getBooks().getBook().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        } else if (p == 2) {
            bookAndOwnerFilter = bookAndOwners.stream().filter(i -> i.getBooks().getAuthor().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        } else if (p == 3) {
            bookAndOwnerFilter = bookAndOwners.stream().filter(i -> i.getBooks().getIsbn().contains(search)).collect(Collectors.toList());
        }
        return bookAndOwnerFilter;
    }

    //here the main issue of book is happening if status of book is requested then if condition will happen
    //and book will be issue to requested else book will be issued
    public String issue(int bookAndOwnerFilterIndex, Member member) {
        //bookAndOwnerFilterIndex to get the index of book to be issued and bookAndOwner1 is filtered list of bookAndOwner
        date = LocalDateTime.now();
        IssueReturnDate issueReturnDate = new IssueReturnDate();
        int index = bookAndOwners.indexOf(bookAndOwnerFilter.get(bookAndOwnerFilterIndex));
        int copy = bookAndOwners.get(index).getCopies();
        if (copy <= 0) {
            issueReturnDate.setStatus("Requested");
            issueReturnDate.setRequestDate(date);
            bookIssues.add(new BookIssue(bookAndOwnerFilter.get(bookAndOwnerFilterIndex), member, issueReturnDate));
            bookRequests.add(new BookRequest(bookAndOwnerFilter.get(bookAndOwnerFilterIndex), member, date));
            return "Not Enough Copies to Issue, Your Request hase been added";
        } else {
            issueReturnDate.setStatus("Issued");
            issueReturnDate.setIssueDate(date);
            bookIssues.add(new BookIssue(bookAndOwnerFilter.get(bookAndOwnerFilterIndex), member, issueReturnDate));
            bookAndOwners.get(index).setCopies(copy - 1);
            return "Book Issued";
        }
    }

    //To get the list of item to be returned
    public List<BookIssue> getBookIssues() {
        return bookIssues;
    }

    public String bookReturn(List<BookIssue> bookIssueFilter, int bookIssueFilterIndex) {
        date = LocalDateTime.now();
        int index = bookIssues.indexOf(bookIssueFilter.get(bookIssueFilterIndex));
        bookIssues.get(index).issueReturnDate.setStatus("Returned");
        bookIssues.get(index).issueReturnDate.setReturnDate(date);

        int indexBookAndOwner = bookAndOwners.indexOf(bookIssues.get(index).bookAndOwner);
        int copy = bookAndOwners.get(indexBookAndOwner).getCopies();

        bookAndOwners.get(indexBookAndOwner).setCopies(copy + 1);
        return "Book Returned Successfully";
    }

    @Override
    public void run() {
        int index = bookAndOwners.indexOf(bookRequests.peek().getBookAndOwner());
        int copy = bookAndOwners.get(index).getCopies();
        if (copy > 0) {
            date = LocalDateTime.now();
            System.out.println("\nBook = (" + bookAndOwners.get(index) + " \nis available for Request Made by " + bookRequests.peek().getMember() + ")");
            IssueReturnDate issueReturnDate = new IssueReturnDate();
            System.out.println("Book Issued to " + bookRequests.peek().getMember() + "\n");
            int bookIssueIndex = bookIssues.indexOf(new BookIssue(bookRequests.peek().getBookAndOwner(), bookRequests.peek().getMember(), issueReturnDate));
            int copies = bookAndOwners.get(index).getCopies();
            bookAndOwners.get(index).setCopies(copies - 1);
            bookIssues.get(bookIssueIndex).getIssueReturnDate().setIssueDate(date);
            bookIssues.get(bookIssueIndex).getIssueReturnDate().setIssueDate(date);
            bookRequests.poll();
        }
    }
}
