package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	private Node front;
	private int size;

	
	private Polynomial() {  // empty linked list to start with
		front = null;
		size = 0;
	}
	
	private void addFront(Node item) {
		front = new Node(item.term.coeff, item.term.degree,front);
		size++;
	}
	
	private void deleteLast(Node front) {
		if(this.front.next == null) {
			
		}else {
			Node prev = null;
			while(front.next != null) {
				prev = front;
				front = front.next;
			}
			prev.next = null;
		}
	}
	
	private Node addLast(Node front,float total,int degree) {
		Node ret = front;
		 if(front == null) {
			 //System.out.println("hi");
			 front = new Node(total,degree,null);
			 return front;
		 }
		while(front.next != null) {
			front = front.next;
		}
		front.next = new Node(total,degree,null);
		
		
		return ret;
	}

	


	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	/*********************************************************************************************************************/
	
	public static Node add(Node poly1, Node poly2) {
		int used =0,total=0,marker,larger=0,poly1Largest=0,poly2Largest=0;
		Node answer= new Node(0,0,null),Big,Small;
		Polynomial list = new Polynomial();
		list.addFront(answer);
		
		for(Node pointer = poly1;pointer!= null;pointer = pointer.next) {
			if(poly1Largest<pointer.term.degree) {
				poly1Largest = pointer.term.degree;
			}
		}
		for(Node pointer = poly2;pointer!= null;pointer = pointer.next) {
			if(poly2Largest<pointer.term.degree) {
				poly2Largest = pointer.term.degree;
			}
		}
		if(poly1Largest>poly2Largest) {/**********Puts poly with bigger degree first******************/
			larger =poly1Largest;
			Big = poly1;
			Small = poly2;
		}else {
			larger = poly2Largest;
			Big = poly2;
			Small = poly1;
		}
		Node ret= Big;/**********************START OF BIG POLY********************************/
		while (Big.next != null) {
			Big = Big.next;
		}
		Big.next = Small;
		/***************************First loop counts down from top coeff********************************/
		for(marker =larger; marker>=0;marker--) {
			for(Node ptr = ret;ptr!= null;ptr = ptr.next) {/***********Loops through big poly looking for degreee***********/
				if(ptr.term.degree == marker) {
					used ++;
					total += ptr.term.coeff;
				}
			}
			/*****************Putting into answer LL****************/
			if(used>0&&total!= 0) {
				Node newFront = new Node(total,marker,answer);
				list.addFront(newFront);
				//answer = answer.addFront(total, marker, answer);
			}
			used = 0;
			total = 0;
		}
		list.deleteLast(list.front);
		//answer.deleteLast(answer);
		//return answer;
		return list.front;
	}
	/******************************************************************************************************************************/
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		int coefftotal = 0,degreetotal=0;
		Node result = new Node(0,0,null),zero =new Node(0,0,null);;
		Polynomial list= new Polynomial();
		list.addFront(result);
		for(Node ptr = poly1;ptr != null;ptr = ptr.next) {
			for(Node innerptr= poly2;innerptr !=null;innerptr = innerptr.next) {
				coefftotal += (ptr.term.coeff * innerptr.term.coeff);
				degreetotal += (ptr.term.degree + innerptr.term.degree);
				list.addLast(result, coefftotal, degreetotal);
				//result=result.addLast(result, coefftotal, degreetotal);
				coefftotal=0;
				degreetotal=0;
			}
		}
		result= add(result, zero);
		
		return result;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	//*******************************************************************************************
	public static float evaluate(Node poly, float x) {
		float total =0;
		for(Node ptr = poly;ptr!= null;ptr = ptr.next) {
			
			total += ptr.term.coeff*(Math.pow(x,ptr.term.degree));
		}
		return total;
	}
	//*******************************************************************************************
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}
	
	
}
