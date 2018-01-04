
/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS. ___Yibo_Wang___ 

Comments to explain the code: 
First this code creates a circular linked list and the code removes the K element until there is only
one element left in the linked list, which is the last worker laid off.  
*/
import java.util.LinkedList;

public class Mary{
	private Node head; //first Node
	private Node pointer = head; //Node that points to the position

	private static class Node { //creates the Node Object 
		 Integer worker; //creates a integer that stores the worker's position
		 Node next;	//references to the next node
		 
			public Node() { //Initializes the Node constructor
				worker = 0; //worker is zero
				next = null; //next is null
			}
	}
	
	public static int mary(int N, int K){  
  
		Node head = new Node(); //creates a Node head
		head.worker = 1; //sets the first worker to 1
		Node tmp = new Node(); //creates a new Node tmp
		head.next = tmp; //sets the next Node of head to tmp which creates the recursive linked list
		
		for (int m = 2; m< N; m++) { // goes through and creates the linked list
			tmp.worker = m;
			tmp.next = new Node();
			tmp = tmp.next;
		}
		tmp.worker = N; //when you reach the last node set it equal to N
		tmp.next = head;//this sets the last worker to N which finishes the circular linked list
		
		Node ptr = tmp; //creates a Node pointer
		for (int i = 0; i < N; i++) { 
			for (int j = 0; j < K-1; j++) {
				//System.out.println("i: "+i+", j: "+j+", NUM: "+ptr.worker);
				ptr = ptr.next; // moves the pointer through as long as it does not reach the number that is being eliminated
			}
			//System.out.println("Eliminate: "+ptr.next.worker);
			tmp = ptr.next; //stores the Node that is going to be removed
			ptr.next = ptr.next.next; //connects the Node to the next Node
			ptr=tmp; //moves the pointer back to tmp
		}
		return ptr.worker; //returns the last Node
	}
	
    // Test from command line:
    //     java Mary N K
    public static void main(String[] args){
    	
    	int N = Integer.parseInt(args[0]);
        int K = Integer.parseInt(args[1]);
        System.out.println("Calling mary(N="+N+", K="+K+")");
        System.out.println(mary(N, K));
    }
}