package iterators;

import java.util.LinkedList;

public class MiniStack<T> {
	LinkedList<T> list;
	
	public MiniStack(){
		list = new LinkedList<T>();
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void push(T newEntry) {
		list.add(0, newEntry);		
	}
	
	public T pop() {
		return list.removeFirst();
	}
	
	public T peek() {
		return list.get(0);
	}

}