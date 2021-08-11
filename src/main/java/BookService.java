import java.util.List;
import java.util.PriorityQueue;

public interface BookService {
    String addBooks();
    List<BookIssue> getBookIssues();
    List<Member> getMembers();
    List<BookAndOwner> getBookAndOwners();
    String issue(int bookAndOwnerFilterIndex, Member member);
    List<BookAndOwner> search(int p, String search);
    PriorityQueue<BookRequest> getBookRequests();
    String bookReturn(List<BookIssue> bookIssueFilter, int bookIssueFilterIndex);

}
