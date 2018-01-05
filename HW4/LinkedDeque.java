//package homework;

/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS.  ___Yibo_Wang____
KEEP CALM AND CODE ON!!! \"/(-_-)\"/
*/

// Homework: revise this file, see TODO items below.

// This LinkedDeque class is a linked list, and a partial
// implementation of the java.util.Deque interface.  It is already
// sufficient for its use by PathFinder.  You should improve it, by
// fixing the TODO items.

// TODO: make this a doubly-linked list.  We already declared a
// "previous" link in the Node class, but you need to properly
// maintain those links.

// All methods here (except toString() and reverse()) should run in
// constant time.   Iterators do not need to be fail-fast.

// As given, this is nearly a copy of LinkedQueue.java from our
// textbook.  We added the second link in Node, We removed check() and
// main().  We removed the "private" declarations, to allow
// testing your code from external classes (like Test1.java,
// which should still be able to compile when you are done).


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements Deque<Item> 
{
    // Not private, just to allow testing.
    int N;         // number of elements on deque
    Node first;    // beginning of deque 
    Node last;     // end of deque

    // Linked list node.
    class Node
    {
        Item item;
        Node next;
        Node previous;          // new!
    }
    
    // Initialize empty deque
    public LinkedDeque()
    {
    	first = null;
        last  = null;
    	N = 0;
    }
    
    public boolean isEmpty() { return first == null; }

    public int size() { return N; }

    public void addLast(Item item) 
    {	// TODO: fix .previous links as necessary
        // This is enqueue from LinkedQueue.java
    	
    	Node oldlast = last; //saves a link to the last Node
    	last = new Node();
        last.item = item; //sets the item of the new last Node
        last.previous = oldlast; //sets the previous Node of last to oldlast 	
    	
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last; //sets the next node after oldlast to last
        }
        ++N; //increases the number of elements in the deque
    }

    public void addFirst(Item item) 
    {	// TODO: fix .previous links as necessary
        // This is push from LinkedStack.java (also updating last)
    	
    	 Node oldfirst = first;
         first = new Node();
         first.item = item; //sets the item to first
         first.next = oldfirst; //sets the next Node of first to the oldfirst
         
         if (last == null) {
        	 last = first;
         	}
         else {
        	 oldfirst.previous = first; //the previous node of the oldfirst is first
         
         }
         ++N; //increases the number of elements in the deque
    }

    public Item removeFirst() 
    {
    	// TODO: fix .previous links as necessary
        // This is dequeue from LinkedQueue.java
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next; 

        
        if(first !=null)
        //If you have only one node, then first.next is already null so then you can't do first.previous so that's why there is a if statement. 
        first.previous = null; //sets the previous link to null
       
        N--;
        if (isEmpty())
            last = null;
        return item;
    }

      public Item removeLast()
    {   
        if (isEmpty()) throw new NoSuchElementException();
        
        //Base cases: when the linked list is empty & when there is only one node 
        Item item = last.item; //stores the item of the last Node 
        
        if(first == last ){ //if there is only one Node
            first =null; 
            last =null;
            --N;  
            return item;
        }
        else if (isEmpty()){
            first=null; //if the linked list is empty, then first is null
            last=null; 
        }
        else {
            last = last.previous; //if there is more than one node in the list, removes the last Node
            last.next = null; // sets the next node to the last to null
            N--;
        }
        
        return item; //returns the item removed
        
    }


    // Return a string representation: "[first, second, last]".
    public String toString() 
    {
        // TODO: this takes time at least quadratic in N!
        // Modify it to use a StringBuilder, so it takes time
        // that is linear in the length of its output.
    	
    	if(isEmpty()){ //if the array is empty just return a null string
    		return "[]";
    	}
      
        StringBuilder list = new StringBuilder(); //calls the string builder constructor and creates a stringbuilder called list
        
        for (Item it : this) {
        	
	    list.append(it + ", ");
	   
        }
        
		return "[" + list.substring(0, list.length()-2) + "]";  //return the Stringbuilder list
    	
    }

    // A standard iterator (visits items from first to last).
    public Iterator<Item> iterator() { return new Iter(); }

    private class Iter implements Iterator<Item>
    {
        protected Node current = first;
        protected Node lastcurrent = null;
        
        public boolean hasNext()
        {
            return current != null;
        }
        public void remove()
        {
            // TODO(EC): implement this method.
            // It should be constant time.
            // Note remove() applies to the item last returned by
            // next().  The user should make at most one call to
            // remove() between two calls of next().  If remove()
            // is called a second time (without another next()), it
            // should throw an IllegalStateException.
        }
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
	    lastcurrent = current;
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class RevIter extends Iter
    {
        public RevIter() { this.current = last; }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
	    lastcurrent = current;
            Item item = current.item;
            current = current.previous;
            return item;
        }
          
    }

    //A reverse iterator (visits items from last to first).
    public Iterator<Item> descendingIterator()
    {
    	return new RevIter(); //returns the reverse Iterator
    	
    }

}
