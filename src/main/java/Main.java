import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        List<BookAndOwner> bookAndOwner = new ArrayList<>();
//        List<BookIssue> bookIssue = new ArrayList<>();
//        List<BookRequest> bookRequest = new ArrayList<>();
        BookService bookManager = new BookManager();

        System.out.println("Hello, Welcome to Book Sharing App: ");
        Scanner sc = new Scanner(System.in);

        List<Member> members = bookManager.getMembers();
        Member member;
        outer: while (true) {
            member=members.get(0);
            System.out.println("Enter ID");
            int memeberid=sc.nextInt();
            sc.nextLine();
            for(Member m: members){
                if(m.getId()==memeberid)
                    member=m;
            }
            inner: while (true) {
                System.out.println("1. To Add Books");
                System.out.println("2. To Show Books");
                System.out.println("3. To Issue Books");
                System.out.println("4. To Return Books/Show Issued Books");
                System.out.println("5. To Show all Issued/Requested Books");
                System.out.println("6. To Switch User");
                System.out.println("7. To Show Book Requests");
                System.out.println("8. To Exit");

                int point = sc.nextInt();
                if (point == 1) {
                    System.out.println(bookManager.addBooks());
                    sc.nextLine();

                } else if (point == 2) {
//                System.out.println(bookAndOwner);
                    int i = 0;
                    for (BookAndOwner b : bookManager.getBookAndOwners()) {
                        System.out.println(++i + ": " + b);
                    }
                    System.out.println();

                } else if (point == 3) {
                    while (true) {
                        System.out.println("1. Search By Book Name");
                        System.out.println("2. Search By Author");
                        System.out.println("3. Search By Isbn Number");
                        int p = sc.nextInt();
                        sc.nextLine();
                        while (p > 3) {
                            p = sc.nextInt();
                            System.out.println("Incorrect Input: Try Again");
                        }
                        System.out.println("Enter Keyword");
                        String search = sc.nextLine();

                        List<BookAndOwner> bookAndOwnerFilter = bookManager.search(p, search);
                        if (bookAndOwnerFilter.isEmpty()) {
                            System.out.println("No Book Found");
                            continue;
                        } else {
                            int i = 0;
                            for (BookAndOwner b : bookAndOwnerFilter) {
                                System.out.println(++i + ": " + b);
                            }
                        }

                        System.out.println();
                        System.out.println("1. Issue");
                        System.out.println("2. Search for New");
                        int s = sc.nextInt();
                        sc.nextLine();
                        String issueStatus;
                        if (s == 1) {
                            System.out.println("1. Issue Book at .... Index Number");
                            int bookAndOwnerFilterIndex = (sc.nextInt()) - 1;
                            sc.nextLine();
                            issueStatus = bookManager.issue(bookAndOwnerFilterIndex, member);
                        }else
                            continue;
                        System.out.println(issueStatus);
//                        if(!issueStatus.equals("Book Issued")){
//                            System.out.println("Enter the block");
//                            ScheduledExecutorService es=Executors.newScheduledThreadPool(10);
//
//                            es.scheduleAtFixedRate(new BookManager(),1,1, SECONDS);
//                        }
                        sc.nextLine();

                        System.out.println("1. To Continue");
                        System.out.println("2. To Exit");
                        int cont = sc.nextInt();
                        if (cont == 1)
                            continue inner;
                        else
                            System.exit(0);
                    }
                } else if (point == 4) {
                    List<BookIssue> bookIssues= bookManager.getBookIssues();
                    List<BookIssue> bookIssueFilter = new ArrayList<>();
                    Member finalMember = member;
                    bookIssueFilter = bookIssues.stream().filter(i -> i.getMember().equals(finalMember) && i.getIssueReturnDate().getStatus().contains("Issued")).collect(Collectors.toList());
                    int i = 0;
                    for (BookIssue b : bookIssueFilter) {
                        System.out.println(++i + ": " + b);
                    }
                    System.out.println();
                    if (bookIssueFilter.isEmpty())
                        System.out.println("No Issued Books");
                    else {
                        System.out.println("1. To Return");
                        System.out.println("2. To Continue");
                        int p = sc.nextInt();
                        if (p == 1) {
                            System.out.println("1. Return Book at .... Index Number");
                            //here k is the index of the book that is to be returned
                            int bookIssueFilterIndex = (sc.nextInt()) - 1;
                            sc.nextLine();
                            String returnStatus = bookManager.bookReturn(bookIssueFilter,bookIssueFilterIndex);
                            System.out.println(returnStatus);
                        } else
                            continue;
                    }

                    sc.nextLine();
                } else if (point == 5) {
                    int i = 0;
                    for (BookIssue b : bookManager.getBookIssues()) {
                        System.out.println(++i + ": " + b);
                    }
                    System.out.println();
                    sc.nextLine();
                } else if (point == 6) {
                    break;
                } else if (point == 7) {
                    int i=0;
                    PriorityQueue<BookRequest> bookRequest = bookManager.getBookRequests();
                    for(BookRequest b: bookRequest){
                        System.out.println(++i +": "+b);
                    }
                    System.out.println();
                    sc.nextLine();
                } else if (point == 8) {
//                    es.shutdown();
                    System.exit(0);
                } else {
                    System.out.println("Incorrect Input, Try Again\n");
                }
            }
        }
    }
}
