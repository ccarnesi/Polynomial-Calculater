package poly;

/**
 * This class implements a linked list node that contains a Term instance.
 * 
 * @author runb-cs112
 *
 */
public class Node {
	
	/**
	 * Term instance. 
	 */
	Term term;
	
	/**
	 * Next node in linked list. 
	 */
	Node next;
	
	/**
	 * Initializes this node with a term with given coefficient and degree,
	 * pointing to the given next node.
	 * 
	 * @param coeff Coefficient of term
	 * @param degree Degree of term
	 * @param next Next node
	 */
	public Node(float coeff, int degree, Node next) {
		term = new Term(coeff, degree);
		this.next = next;
	}
//	public Node addFront(float coeff, int degree,Node oldfront) {
//		oldfront = new Node(coeff,degree,oldfront);
//		return oldfront ;
//	}
//	public static Node traverse(Node front) {
//		for(Node ptr = front;ptr != null; ptr = ptr.next) {
//			if(ptr.term.coeff > 0) {
//				if(ptr != front) {
//					System.out.print(" + ");
//				}
//
//			}
//			System.out.print(ptr.term);
//		}
//		return front;
//	}
//	public Node addLast(Node front, float total,int degree) {
//		Node ret = front;
//		 if(front == null) {
//			 //System.out.println("hi");
//			 front = new Node(total,degree,null);
//			 return front;
//		 }
//		while(front.next != null) {
//			front = front.next;
//		}
//		front.next = new Node(total,degree,null);
//		
//		
//		return ret;
//	}
//public void deleteLast(Node front) {
//	if(front.next == null) {
//		
//	}else {
//		Node prev = null;
//		while(front.next != null) {
//			prev = front;
//			front = front.next;
//		}
//		prev.next = null;
//	}
//	
}













