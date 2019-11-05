//Queue class to extend List class
public class Queue<Type> extends List<Type> {
    //Tracks Value in Next item to be removed from Queue
    private Type NodeVal;

    public Queue(){
        super();
    }

    public void Enqueue(Type n){
        super.Last();
        super.InsertAfter(n);
        super.First();
        NodeVal = super.GetValue();
    }

    public Type Dequeue(){
        super.First();
        Type temp = super.GetValue();
        super.Remove();
        super.First();
        NodeVal = super.GetValue();
        return temp;
    }

    public Type getNodeVal(){
        return this.NodeVal;
    }
}
