//SciFiLi Project CSC220
//Blake Till and Bryce Ditto\
//Data structure one: Uses Linked list to dynamically input data
//Data structure two: Uses Array to quickly sort and search and access data
//Data structure three: Uses Stack to emulate checking in process of books
//Data structure four:  Uses priority queue to quickly list out ordered evacuation list

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class scifili {
    public static void main(String args[]) throws IOException {
        //Setup user directory and import booklist and export booklistout
        File starting = new File(System.getProperty("user.dir"));
        File library = new File(starting, "booklist.txt");
        FileWriter writeout = new FileWriter("booklistout.txt");

        //Init list for importing data
        List<Book> liblist = new List();

        //Init stack for taking in returns
        Stack<Book> stackret = new Stack();


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

        //Create Array
        Book[] bookArray = GenArray(liblist);

        //temp int for search result
        int result;

        //Main loop for parsing user input and executing desired commands, maybe convert to switch case if time allows
        while(true){
            System.out.println("Welcome to library resource center, please select from the following options:");
            System.out.println("Sort by Title, Sort by Author, Search by Author, Search by Title, Return Titles, Quit.");
            input = rawinput.nextLine();

            if(input.equalsIgnoreCase("quit")){
                System.out.println("exiting");
                AlphaSort(bookArray);
                //write array to output txt file
                for(Book book: bookArray){
                    writeout.write(book.toString() + "\n");
                }
                writeout.close();
                break;
            }else if(input.equalsIgnoreCase("Sort by Author")){
                AuthorSort(bookArray);
                PrintArray(bookArray);
            }else if(input.equalsIgnoreCase("Sort by Title")){
                AlphaSort(bookArray);
                PrintArray(bookArray);
            }else if(input.equalsIgnoreCase("Search by Author")){
                System.out.println("Which author would you like to search for?");
                //user input author
                input = rawinput.nextLine();
                AuthorSearch(bookArray, input);
            }else if(input.equalsIgnoreCase("Search by Title")){
                System.out.println("Which title would you like to search for?");
                //user input title
                input = rawinput.nextLine();
                result = TitleSearch(bookArray, input);
                if(result != -1){
                    System.out.println(bookArray[result]);
                }
            }else if(input.equalsIgnoreCase("Return Titles")){
                //Until user enters done, loops to search for titles, if they exist, adds them to the return stack
                while(input.compareToIgnoreCase("Done") != 0){
                    System.out.println("Please enter a title to be returned.  Or enter Done.");
                    input=rawinput.nextLine();
                    result = TitleSearch(bookArray, input);
                    if(result != -1){
                        stackret.Push(bookArray[result]);
                    }
                }
                //pop off the stack(if it is not empty) and check the list to edit checked in status, may be redundant (might can edit object in array)
                if(stackret.GetSize() != -1){
                    for(int i = 0; i < stackret.GetSize(); i++){
                        for(int j = 0; j <liblist.GetSize() ; j++){
                            liblist.SetPos(j);
                            if(liblist.GetValue().GetTitle().equalsIgnoreCase(stackret.Peek().GetTitle())){
                                //set node to checked in
                                liblist.GetValue().SetCheckedin(1);
                                stackret.Pop();
                                //use j to exit for loop early for success
                                j = liblist.GetSize();
                            }
                        }
                    }
                    System.out.println("All titles checked in.");
                }
                //remake array with updated values, may not be needed (redundant)
                bookArray = GenArray(liblist);
            }else if(input.equalsIgnoreCase("Checkout Titles")){
                //Until user enters done, loops to search for titles, if they exist, adds them to the checkout stack
                while(input.compareToIgnoreCase("Done") != 0){
                    System.out.println("Please enter a title to be returned.  Or enter Done.");
                    input=rawinput.nextLine();
                    result = TitleSearch(bookArray, input);
                    if(result != -1){
                        stackret.Push(bookArray[result]);
                    }
                }
                //pop off the stack(if it is not empty) and check the list to edit checked in status, may be redundant (might can edit object in array)
                if(stackret.GetSize() != -1){
                    for(int i = 0; i < stackret.GetSize(); i++){
                        for(int j = 0; j <liblist.GetSize() ; j++){
                            liblist.SetPos(j);
                            if(liblist.GetValue().GetTitle().equalsIgnoreCase(stackret.Peek().GetTitle())){
                                //set node to checked out
                                liblist.GetValue().SetCheckedin(0);
                                stackret.Pop();
                                //use j to exit for loop early for success
                                j = liblist.GetSize();
                            }
                        }
                    }
                    System.out.println("All titles checked out.");
                }
                //remake array with updated values, may not be needed (redundant)
                bookArray = GenArray(liblist);

            }else if(input.equalsIgnoreCase("Evacuation Priority")){
                System.out.println("To be implemented");
            }else{
                System.out.println("Please enter one of the listed options");
            }

        }

    }

    //Searches array for a user selected title and prints that title
    public static int TitleSearch(Book[] bookarr, String title){
        int first = 0;
        int last = bookarr.length - 1;
        int mid;

        AlphaSort(bookarr);

        while(first <= last){
            mid = (first + last)/2;
            int test = bookarr[mid].GetTitle().compareToIgnoreCase(title);
            if(bookarr[mid].GetTitle().compareToIgnoreCase(title) == 0){
                System.out.println("Title found.\n");
                return mid;
            }else if(bookarr[mid].GetTitle().compareToIgnoreCase(title) > 0){
                last = mid - 1;
            }else{
                first = mid + 1;
            }
        }
        System.out.println("Title not found.");
        return -1;

    }

    //Creates a LL of books by the user selected author then prints that list
    public static void AuthorSearch(Book[] bookarr, String author){
        List<Book> temp = new List();
        for (Book book : bookarr) {
            String tmpauth = book.GetAuthor();
            if (tmpauth.equalsIgnoreCase(author)) {
                temp.InsertAfter(book);
            }
        }
        if(temp.GetSize() == -1){
            System.out.println("Author not found.");
        }else{
            System.out.println("Author found.  See titles below.");
            System.out.println(temp);
        }
    }

    //Sorts books alphabetically by title
    public static void AlphaSort(Book[] bookarr) {
        int j;
        boolean flag = true;  // will determine when the sort is finished
        Book temp;

        //algorithm from mathbits.com
        while (flag) {
            flag = false;
            for (j = 0; j < bookarr.length - 1; j++) {
                if (bookarr[j].GetTitle().compareToIgnoreCase(bookarr[j + 1].GetTitle()) > 0) {
                    temp = bookarr[j];
                    bookarr[j] = bookarr[j + 1];     // swapping
                    bookarr[j + 1] = temp;
                    flag = true;
                }
            }

        }
    }

    //Sorts books alphabetically by author
    public static void AuthorSort(Book[] bookarr){
        int j;
        boolean flag = true;  // will determine when the sort is finished
        Book temp;

        //algorithm from mathbits.com
        while (flag) {
            flag = false;
            for (j = 0; j < bookarr.length - 1; j++) {
                if (bookarr[j].GetAuthor().compareToIgnoreCase(bookarr[j + 1].GetAuthor()) > 0) {
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

    //Print Array
    public static void PrintArray(Book[] arr){
        for(int j = 0; j <arr.length; j ++){
            System.out.println(arr[j]);
        }
    }

}


