import java.util.Objects;

public class BookIssue {
    BookAndOwner bookAndOwner;
    Member member;
    IssueReturnDate issueReturnDate;

    public BookIssue(BookAndOwner bookAndOwner, Member member, IssueReturnDate issueReturnDate) {
        this.bookAndOwner = bookAndOwner;
        this.member = member;
        this.issueReturnDate = issueReturnDate;
    }

    public BookAndOwner getBookAndOwner() {
        return bookAndOwner;
    }

    public IssueReturnDate getIssueReturnDate() {
        return issueReturnDate;
    }

    public Member getMember() {
        return member;
    }


    @Override
    public String toString() {
        return "Author='" + bookAndOwner.getBooks().getAuthor() + '\'' +
                ", Book='" + bookAndOwner.getBooks().getBook() + '\'' +
                ", Isbn='" + bookAndOwner.getBooks().getIsbn() + '\'' +
                ", Owner of Book='" + bookAndOwner.getOwner() + '\'' +
                ", Issued to " + member + '\'' +
                ", IssueAndReturn='" + issueReturnDate + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookIssue bookIssue = (BookIssue) o;
        return Objects.equals(bookAndOwner, bookIssue.bookAndOwner) && Objects.equals(member, bookIssue.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookAndOwner, member, issueReturnDate);
    }
}
