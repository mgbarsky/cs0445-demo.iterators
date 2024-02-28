package iterators;

public class Node<T> {
    private T    data; // Entry in list
    private Node<T> next; // Reference variable storing the address of the next node
    
    public Node(T data, Node<T> next) {
       this.data = data;
       this.next = next;
    } 
    
    public Node(T data) {
        this(data, null);
    } 
     
    public T getData() {
       return this.data;
    } 
    
    public void setData(T newData) {
       this.data = newData;
    } 
    
    public Node<T> getNext() {
       return this.next;
    } 
    
    public void setNext(Node<T> nextNode) {
       this.next = nextNode;
    } 
} 
