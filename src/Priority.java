//Priority Queue Class
public class Priority<Type> extends Queue<Type>{

    //Default constructor
    Priority(){
        super();
    }

    //Compare node to items in the queue and insert based on numerical position of Priority
    public void Insert(Type data){
        super.First();
        Book datat = (Book) data;
        while(super.GetValue() != null){
            Book temp = (Book) super.GetValue();
            int checknext = super.Next();
            Book ntemp = (Book) super.GetValue();
            if(checknext == 1) {
                super.Prev();
            }
            if(checknext == -1){
                if(datat.compareTo(temp) == 1){
                    super.InsertAfter(data);
                }else {
                    super.InsertBefore(data);
                    break;
                }
            }else if ((datat.compareTo(temp) == 1) && (datat.compareTo(ntemp) == -1)){
                super.InsertAfter(data);
                break;
            }else if (datat.compareTo(temp) == 0) {
                super.InsertAfter(data);
                break;
            }else{
                super.Next();
            }
        }
        if(super.GetValue() == null){
            super.InsertAfter(data);
        }
    }

    //Remove Highest Priority, lowest number is highest priority
    public void RemoveMax(){
        super.First();
        super.Dequeue();
    }

    //Peek at highest priority object, lowest number is highest priority
    public Type getHighestPriority(){
        super.First();
        return super.GetValue();
    }
}


