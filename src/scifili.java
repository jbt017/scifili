//SciFiLi Project CSC220
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class scifili {
    public static void main(String args[]) throws FileNotFoundException {
        //Setup user directory and import booklist
        File starting = new File(System.getProperty("user.dir"));
        File library = new File(starting, "booklist.txt");
        //Init list for importing data
        List<Book> liblist = new List();

        //Init scanners for file and user input
        Scanner libread = new Scanner(library);
        Scanner rawinput = new Scanner(System.in);
        String input;

        //Init temp variables for parsing
        String author;
        String title;
        int priority;
        int check;

        //Read file and create book object from each line
        while(libread.hasNextLine()){
            String line = libread.nextLine();
            String[] tokens = line.split(", ");
            title = tokens[0];
            author = tokens[1];
            priority = Integer.parseInt(tokens[2]);
            check = Integer.parseInt(tokens[3]);
            Book temp = new Book(author, title, priority, check);
            liblist.InsertAfter(temp);
        }

        //Test Print for list replace with startup print
        System.out.println(liblist);

        //Create Array
        Book[] bookArray = Book.GenArray(liblist);

        //Spacing
        //System.out.println("\n\n\n\n\n\nSee Below\n\n\n\n\n\n");

        //Test print array
//        for(int j = 0; j <liblist.GetSize(); j ++){
//            System.out.println(bookArray[j]);
//        }

        //Main loop
        while(true){
            System.out.println("Welcome to library resource center, please select from the following options: quit");
            input = rawinput.nextLine();

            if(input.equalsIgnoreCase("quit")){
                System.out.println("exiting");
                break;
            }

        }

    }
    public void TitleSearch(Book[] bookarr){
    }

    public void AuthorSearch(Book[] bookarr){
    }

    public void AlphaSort(Book[] bookarr){

    }


}

//Book class for storing data from text file
class Book{
    private String author;
    private String title;
    private int priority;
    private int checkedin;

    //Default Constructor
    Book(){
        this.priority = 0;
        this.checkedin = 0;
    }

    //param constructor
    Book(String auth, String title, int pri, int check){
        this.author = auth;
        this.title = title;
        this.priority = pri;
        this.checkedin = check;
    }

    //Get Author
    public String GetAuthor(){
        return this.author;
    }

    //Set Author
    public void SetAuthor(String auth){
        this.author = auth;
    }

    //Get Title
    public String GetTitle(){
        return this.title;
    }

    //Set Title
    public void SetTitle(String title){
        this.title = title;
    }

    //Get Priority
    public int GetPriority(){
        return this.priority;
    }

    //Set Priority
    public void SetPriority(int pri){
        this.priority = pri;
    }

    //Get Checked State
    public int GetCheckedin(){
        return this.checkedin;
    }

    //Set Checked State
    public void SetCheckedin(int check){
        this.checkedin = check;
    }

    //String override
    public String toString(){
        String printdata = "";
        printdata = printdata + this.GetTitle() + ", " + this.GetAuthor() + ", " + this.GetCheckedin() + ", " + this.GetPriority();
        return printdata;
    }

    public static Book[] GenArray(List<Book> lst){
        Book[] tempArray = new Book[lst.GetSize()];
        lst.First();
        for(int i=0; i<lst.GetSize(); i++){
            tempArray[i] = lst.GetValue();
            lst.Next();
        }
        return tempArray;
    }
}
