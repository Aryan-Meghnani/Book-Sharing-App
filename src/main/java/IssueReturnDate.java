import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class IssueReturnDate {
    private String status;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;
    private LocalDateTime requestDate;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return  "Status='" + status + '\'' +
                ", IssueDate=" + issueDate +
                ", ReturnDate=" + returnDate +
                ", RequestDate=" + requestDate;
    }
}
