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
        Book[] bookArray = GenArray(liblist);

        //Title Sort test
        //AlphaSort(bookArray);

        //Author Sort test
        AuthorSort(bookArray);


        //Test print array
        for(int j = 0; j <liblist.GetSize(); j ++){
            System.out.println(bookArray[j]);
        }

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

    public static void AlphaSort(Book[] bookarr) {
        int j;
        boolean flag = true;  // will determine when the sort is finished
        Book temp;

        //algorithm from mathbits.com
        while (flag) {
            flag = false;
            for (j = 0; j < bookarr.length - 1; j++) {
                if (bookarr[j].GetTitle().compareToIgnoreCase(bookarr[j + 1].GetTitle()) > 0) {                                             // ascending sort
                    temp = bookarr[j];
                    bookarr[j] = bookarr[j + 1];     // swapping
                    bookarr[j + 1] = temp;
                    flag = true;
                }
            }

        }
    }

    public static void AuthorSort(Book[] bookarr){
        int j;
        boolean flag = true;  // will determine when the sort is finished
        Book temp;

        //algorithm from mathbits.com
        while (flag) {
            flag = false;
            for (j = 0; j < bookarr.length - 1; j++) {
                if (bookarr[j].GetAuthor().compareToIgnoreCase(bookarr[j + 1].GetAuthor()) > 0) {                                             // ascending sort
                    temp = bookarr[j];
                    bookarr[j] = bookarr[j + 1];     // swapping
                    bookarr[j + 1] = temp;
                    flag = true;
                }
            }

        }

    }

    //Create Array from books
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


