package iterators;

// full demo of list iterator within a simple singly-linked list
// partly adopted from the textbook code

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
A class that implements the ADT list by using a chain of nodes.
The list has an iterator. 
*/

public class LinkedListWithIterator<T> implements Iterable<T> {
	private Node<T> head;
	private int  size;

	private int count;  // debug variable to count number of operations

	public LinkedListWithIterator() {
		head = null;
		size = 0;
		count = 0;
	} 

	public void clear()	{
		head = null;
		size = 0;
		count = 0;
	} 
	
	// Method to return value of count variable
	public int getCount() {
	   return count;
	}	
	
	public void resetCount() {
	   this.count = 0;
	}
	
	public boolean isEmpty()  {
		boolean result;

		if (size == 0)   {
			assert head == null;
			result = true;
		}
		else  {
			assert head != null;
			result = false;
		} 

		return result;
	} 
	
	// adds to the end by default
	public void add(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);

		if (isEmpty())
			head = newNode;
		else {                                      // Add to end of non-empty list
			Node<T> lastNode = getNodeAt(size-1);
			lastNode.setNext(newNode);         		// Make last node reference new node
		} 
		
		size++;
	}  
	
	//adds at a specified position
	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 0) && (newPosition <= size)) {
			Node<T> newNode = new Node<T>(newEntry);
			
			// Case 1: new position is 0
			if (newPosition == 0) 	{
				newNode.setNext(head);
				head = newNode;
			}
			
			//case 2: list is not empty and new Position > 0
			else {                                      
				Node<T> nodeBefore = getNodeAt(newPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNext();
				newNode.setNext(nodeAfter);
				nodeBefore.setNext(newNode);
			} // end if
      
			size++;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	public T remove(int givenPosition) {
		T result = null;                          // Return value
   
		if ((givenPosition >= 0) && (givenPosition <= size))  {
			assert !isEmpty();
      
			// Case 1: remove first entry
			if (givenPosition == 0)   {
				result = head.getData();       // Save entry to be removed
				head = head.getNext();
			}
		  
			// Case 2: not first entry
			else {
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeToRemove = nodeBefore.getNext();
				Node<T> nodeAfter = nodeToRemove.getNext();
				nodeBefore.setNext(nodeAfter);
				result = nodeToRemove.getData();    // Save entry to be removed
			} // end if
	      
			size--;
			return result;                         // Return removed entry
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} 

	

	public T get(int givenPosition) {
		if ((givenPosition >= 0) && (givenPosition <= size)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} 

	public int getSize() {
		return size;
	} 

	public Iterator<T> iterator()   {
	   return new LinkedListIterator(this);
	} // end iterator


	
// Returns a reference to the node at a given position.
// Preconditions: List is not empty;
//               0 <= givenPosition < size.
	private Node<T> getNodeAt(int givenPosition) {
		assert !isEmpty() && (0 <= givenPosition) && (givenPosition < size);
		Node<T> currentNode = head;
		
   // Traverse the chain to locate the desired node (skipped
   // if givenPosition is 0)
		for (int counter = 0; counter < givenPosition; counter++) {
			currentNode = currentNode.getNext();
		
			/*******************************/
			count++;							//counting operations
			/*******************************/
		}
		
		assert currentNode != null;
   
		return currentNode;
	} // end getNodeAt

//==================================

	private class LinkedListIterator implements Iterator<T>	{
		private Node<T> currentNode;  // Node containing next entry in iteration
		private LinkedListWithIterator<T> list;
		
		public LinkedListIterator(LinkedListWithIterator<T> list) {
			this.list = list;
			currentNode = list.head;
		} 
	
		public boolean hasNext() {
			return currentNode != null;
		} 

		public T next()	{
			if (hasNext())	{
				Node<T> returnNode = currentNode;        	// Get next node
				currentNode = currentNode.getNext(); 			// Advance iterator

				/*******************************/
				count++;							//counting operations
				/*******************************/

				return returnNode.getData();       // Return next entry in iteration
			}
			else
				throw new NoSuchElementException("Illegal call to next(); " +
						"iterator is after end of list.");
		} // end next

		public void remove() {
			throw new UnsupportedOperationException("remove() is not " +
		       "supported by this iterator");
		} 
	} 

	// Method to iterate through the list, using the get()
	// method.  Note that from the user's point of view, getEntry()
	// is a single operation, but the getCount() method reveals that
	// it actually is not.
	public static void LoopWithGet(LinkedListWithIterator<Integer> L)	{
		System.out.println("Iterating using get() method");
		L.resetCount();
		for (int i = 0; i < L.getSize(); i++)
			System.out.print(L.get(i) + " ");			
		
		System.out.println();
		System.out.println("Count of operations: " + L.getCount() + "\n");
	}

	// Method to iterate through the list, using an iterator as
	// shown.  To the user it does not seem much different from the
	// method above, but the getCount() method reveals the difference
	public static void LoopWithIterator(LinkedListWithIterator<Integer> L)	{
		System.out.println("Iterating using iterator");
		L.resetCount();
		
		Iterator<Integer> I = L.iterator();
		while (I.hasNext()) 
			System.out.print(I.next() + " ");
					
		System.out.println();
		System.out.println("Count: " + L.getCount() + "\n");
	}

	public static void main(String [] args)	{
		LinkedListWithIterator<Integer> L = new LinkedListWithIterator<Integer>();
		for (int i = 0; i < 100; i++)
			L.add(Integer.valueOf(i));
		LoopWithGet(L);
		LoopWithIterator(L);
	}
} // end LinkedListWithIterator



