/* ***************************************************
 * Blake Till
 * 9/19/19
 *
 * List Class - handles any form of data
 *************************************************** */

public class List<Type>
{
    // We don't actually have to set a max size with linked lists
    // But it is a good idea.
    // Just picture an infinite loop adding to the list! :O
    // Yes, you may change this when you do your word count program.
    public static final int MAX_SIZE = 200;

    private Node<Type> head;
    private Node<Type> tail;
    private Node<Type> curr;
    private int num_items;

    // constructor
    // remember that an empty list has a "size" of -1 and its "position" is at -1
    public List()
    {
        this.head = new Node<Type>();
        this.tail = new Node<Type>();
        this.curr = new Node<Type>();
        this.curr.setLink(head);
        this.num_items = 0;

    }

    // copy constructor
    // clones the list l and sets the last element as the current
    // (notice we're not just copying the whole list at once?)
    public List(List<Type> l)
    {
        Node<Type> n = l.head.getLink();

        this.head = new Node();
        this.curr = new Node();
        this.tail = new Node();

        this.num_items = 0;

        while (n != null)
        {
            this.InsertAfter(n.getData());
            n = n.getLink();
        }
    }

    // navigates to the beginning of the list
    public void First()
    {
        this.curr.setLink(this.head.getLink());

    }

    // navigates to the end of the list
    // the end of the list is at the last valid item in the list
    public void Last()
    {
        if(this.tail.getLink() != null) {
            this.curr.setLink(this.tail.getLink());
        }

    }

    // navigates to the specified element (0-index)
    // this should not be possible for an empty list
    // this should not be possible for invalid positions
    public void SetPos(int pos)
    {
        this.curr.setLink(this.head.getLink());
        for(int i=0; i <= pos-1; i++){
            this.curr.setLink(this.curr.getLink().getLink());
        }

    }

    // navigates to the previous element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void Prev()
    {
        Node<Type> temp = this.curr.getLink();
        curr.setLink(this.head.getLink());
        if(this.head.getLink() == null){
            System.out.println("Empty List.");
        }else if(temp == this.head.getLink()){
            this.curr.setLink(this.head.getLink());
        } else{
            while(this.curr.getLink().getLink() != temp){
                this.curr.setLink(this.curr.getLink().getLink());
            }
        }

    }


    // navigates to the next element
    // this should not be possible for an empty list
    // there should be no wrap-around
    public void Next()
    {
        if(curr.getLink()  != null && curr.getLink().getLink() != null){
            curr.setLink(curr.getLink().getLink());
        }
    }

    // returns the location of the current element (or -1)
    public int GetPos()
    {
        Node<Type> temp = this.curr.getLink();
        int counter = 0;
        this.curr.setLink(this.head.getLink());
        if(this.head.getLink() == null){
            counter = -1;
        }
        while(this.curr.getLink() != null){
            if(temp.getLink() == curr.getLink()){
                this.curr.setLink(temp);
                return counter;
            }else{
                this.curr.setLink(this.curr.getLink().getLink());
                counter ++;
            }
        }
        this.curr.setLink(temp);
        return counter;

    }

    // returns the value of the current element (or -1)
    public Type GetValue()
    {
        if(this.curr.getLink() != null){
            return curr.getLink().getData();
        }else{
            return null;
        }

    }

    // returns the size of the list
    // size does not imply capacity
    public int GetSize()
    {
        return this.num_items;

    }

    // inserts an item before the current element
    // the new element becomes the current
    // this should not be possible for a full list
    public void InsertBefore(Type data)
    {
        if(this.head.getLink() == null){
            Node<Type> temp = new Node();
            temp.setData(data);
            this.head.setLink(temp);
            this.tail.setLink(temp);
            this.curr.setLink(temp);
            this.num_items ++;
        }else if(this.head.getLink() == this.curr.getLink()){
            Node<Type> temp = new Node();
            temp.setData(data);
            temp.setLink(this.head.getLink());
            this.head.setLink(temp);
            curr.setLink(temp);
            this.num_items ++;
        } else if(this.num_items <= MAX_SIZE) {
            Node<Type> temp = new Node();
            temp.setData(data);
            temp.setLink(this.curr.getLink());
            Prev();
            this.curr.setLink(temp);
            this.num_items++;
        }else{
            System.out.println("Cannot insert.  List is full.");
        }

    }

    // inserts an item after the current element
    // the new element becomes the current
    // this should not be possible for a full list
    public void InsertAfter(Type data)
    {
        if(this.head.getLink() == null){
            Node<Type> temp = new Node();
            temp.setData(data);
            this.head.setLink(temp);
            this.curr.setLink(temp);
            this.tail.setLink(temp);
            this.num_items ++;
        }else if(this.num_items <= MAX_SIZE) {
            Node<Type> temp = new Node();
            temp.setData(data);
            temp.setLink(this.curr.getLink().getLink());
            this.curr.getLink().setLink(temp);
            this.curr.setLink(temp);
            this.num_items++;
            if(this.tail.getLink().getLink() == temp){
                this.tail.setLink(temp);
            }
        }else{
            System.out.println("Cannot insert.  List is full.");
        }

    }

    // removes the current element
    // this should not be possible for an empty list
    public void Remove()
    {
        Node<Type> temp = this.curr.getLink();
        if(this.head.getLink() != null){
            this.Prev();
            if(temp != this.head.getLink()){
                this.curr.getLink().setLink(temp.getLink());
            }else{
                this.head.setLink(this.head.getLink().getLink());
            }
            this.num_items--;
        }else{
            System.out.println("Empty List");
        }

    }

    // replaces the value of the current element with the specified value
    // this should not be possible for an empty list
    public void Replace(Type data)
    {
        if(this.num_items != 0){
            this.curr.getLink().setData(data);
        }

    }

    // returns if the list is empty
    public boolean IsEmpty()
    {
        if(this.num_items == 0){
            return true;
        }
        else{
            return false;
        }

    }

    // returns if the list is full
    public boolean IsFull()
    {
        if(num_items == MAX_SIZE){
            return true;
        }else{
            return false;
        }

    }

    // returns if two lists are equal (by value)
    public boolean Equals(List<Type> l)
    {
        int counter = 0;
        boolean result = false;
        Node<Type> temp = this.curr.getLink();
        Node<Type> temp2 = l.curr.getLink();

        if(this.num_items == l.num_items){
            this.curr.setLink(this.head.getLink());
            l.curr.setLink(l.head.getLink());

            while(this.curr.getLink() != null && counter == 0){
                if(this.curr.getLink().getData() != l.curr.getLink().getData()){
                    counter++;
                }else{
                    this.curr.setLink(this.curr.getLink().getLink());
                    l.curr.setLink(l.curr.getLink().getLink());
                }
            }
        }

        if(counter == 0){
            result = true;
        }
        this.curr.setLink(temp);
        l.curr.setLink(temp2);
        return result;

    }

    // returns the concatenation of two lists
    // l should not be modified
    // l should be concatenated to the end of *this
    // the returned list should not exceed MAX_SIZE elements
    // the last element of the new list is the current
    public List<Type> Add(List<Type> l)
    {
        Node<Type> temp = l.curr.getLink();
        List<Type> conlist = new List(this);
        l.curr.setLink(l.head.getLink());
        conlist.Last();
        while(l.curr.getLink() != null){
            conlist.InsertAfter(l.curr.getLink().getData());
        }
        l.curr.setLink(temp);
        return conlist;
    }

    // returns a string representation of the entire list (e.g., 1 2 3 4 5)
    // the string "NULL" should be returned for an empty list
    public String toString()
    {
        String printdata = "";
        Node<Type> temp = curr.getLink();
        if(this.num_items == 0){
            printdata = "NULL";
        }
        this.curr.setLink(this.head.getLink());
        while(this.curr.getLink() != null){
            Type var = curr.getLink().getData();
            printdata =  printdata + var + " \n";
            this.curr.setLink(this.curr.getLink().getLink());
        }

        this.curr.setLink(temp);
        return printdata;

    }
}