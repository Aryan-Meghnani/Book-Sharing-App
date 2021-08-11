import java.util.Objects;

public class BookAndOwner {
    Books books;
    Member member;
    int copies;

    public BookAndOwner(Books books, Member member, int copies) {
        this.books = books;
        this.member = member;
        this.copies = copies;
    }

    public Member getOwner() {
        return member;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Books getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return books +" " + member +
                ", Copies=" + copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAndOwner that = (BookAndOwner) o;
        return  Objects.equals(books, that.books) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, member, copies);
    }
}
