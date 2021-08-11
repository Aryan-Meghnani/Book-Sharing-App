import java.time.LocalDateTime;
import java.util.Objects;

public class BookRequest implements Comparable<BookRequest>{
    BookAndOwner bookAndOwner;
    Member member;
    LocalDateTime date;

    public BookRequest(BookAndOwner bookAndOwner, Member member, LocalDateTime date) {
        this.bookAndOwner = bookAndOwner;
        this.date = date;
        this.member = member;
    }

    @Override
    public String toString() {
        return bookAndOwner +
                ", date=" + date +
                ", Request Made By " + member;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BookAndOwner getBookAndOwner() {
        return bookAndOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return Objects.equals(bookAndOwner, that.bookAndOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookAndOwner, member, date);
    }

    @Override
    public int compareTo(BookRequest bookRequest) {
        if(date.isAfter(bookRequest.date)){
            return -1;
        }else if(date.isBefore(bookRequest.date))
            return 1;
        return 0;
    }
}
