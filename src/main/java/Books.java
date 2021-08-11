import java.util.Objects;

public class Books {
    private String author;
    private String book;
    private String isbn;

    public Books(String author, String book, String isbn) {
        this.author = author;
        this.book = book;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook() {
        return book;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(author, books.author) && Objects.equals(book, books.book) && Objects.equals(isbn, books.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, book, isbn);
    }

    @Override
    public String toString() {
        return
                "Author='" + author + '\'' +
                        ", Book='" + book + '\'' +
                        ", Isbn='" + isbn + '\''+"," ;
    }
}
