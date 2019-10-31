//Stack class to extend List Class
public class Stack<Type> extends List<Type> {

    Stack(){
        super();
    }

    public void Push(Type n){
        super.First();
        super.InsertBefore(n);
    }

    public Type Pop(){
        super.First();
        Type temp = super.GetValue();
        super.Remove();
        return temp;
    }

    public Type Peek(){
        super.First();
        return super.GetValue();
    }
}
