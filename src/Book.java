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
}