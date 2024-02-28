package iterators;

import java.util.*;


public class ReverseIterator<E> implements Iterator<E> {

  MiniStack<E> stack = new MiniStack<E>();

  public ReverseIterator(Iterator<E> oldIterator){
      while(oldIterator.hasNext()) {
    	  stack.push(oldIterator.next());
      }
  }

  public boolean hasNext(){
    return !(stack.isEmpty());
  }

  public E next(){
    return stack.pop();
  }
  
  public static void setUpList(List<Integer> l, int n) {
    for(int i = 0; i < n; i++) {
      l.add(i);
    }
  }
  
  public static void main(String[] args) {
    List<Integer> l = new ArrayList<Integer>();
    setUpList(l, 10);
    ReverseIterator<Integer> it = new ReverseIterator<Integer>(l.iterator());

    while(it.hasNext()){
      System.out.println(it.next());
    }

  }
}