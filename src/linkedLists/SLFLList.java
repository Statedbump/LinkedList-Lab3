package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		SNode<E> nd = (SNode<E>)nuevo;
		if(length == 0) {
			first = nd;
			last = nd;
			nd.setNext(null);
		}else {
			nd.setNext(first);
			first = nd;
		}
		length++;
		
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		SNode<E> nd = (SNode<E>) nuevo;
		SNode<E> td = (SNode<E>) target;
		if(target == last) {
			last.setNext(nd);
			last  = nd;
			nd.setNext(null);
		}else {
			nd.setNext(td.getNext());
			td.setNext(nd);
			
		}
		length++;
		
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		SNode<E> nd = (SNode<E>) nuevo;
		
		if(first== target ) {
			this.addFirstNode(nd);
		}else {
			SNode<E> prev = (SNode<E>) first;
			while(prev!=null && prev.getNext()!= target){
				prev = prev.getNext();
			}
			this.addNodeAfter(prev, nd);
			
		}
		length++;
		
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if(length == 0) {
			throw new NoSuchElementException("getFirstNode(): Empty list");
		}
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if(length == 0) {
			throw new NoSuchElementException("getFirstNode(): Empty list");
		}
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		if(target == last || !find(target))
			throw new NoSuchElementException("getNodeAfter(): Element not in list");
		
		
		return ((SNode<E>)target).getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if(target == first || !find(target))
			throw new NoSuchElementException("gettNodeBefore(): Element not in list");
		SNode<E> prev = first;
		while(prev != null && prev.getNext() !=target) {
			prev = prev.getNext();
		}
		return prev;
	}

	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	public void removeNode(Node<E> target) {
		if(target == first) {
			first = ((SNode<E>)target).getNext();
			((SNode<E>)target).setNext(null);
		}
		else if (target == last) {
			last = (SNode<E>)getNodeBefore(target);
			last.setNext(null);
		}
		else {
			((SNode<E>)getNodeBefore(target)).setNext((SNode<E>)getNodeAfter(target));
			((SNode<E>)target).setNext(null);
		}
		length--;

	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}
	
	
	public SLFLList<E> clone() throws CloneNotSupportedException {
		SLFLList<E> theClone = (SLFLList<E>) super.clone();
		if(length >0) {
			theClone.first =  new SNode<E>(first.getElement(),null);
			SNode<E> current =(SNode<E>) first.getNext();
			SNode<E> nTail =(SNode<E>) theClone.first;
			while(current !=null) {
				SNode<E> newest = new SNode<E>(current.getElement(),null);
				nTail.setNext(newest);
				nTail = newest;
				current = current.getNext();
			}
			theClone.last = current;
		}
		return theClone;
	}
	//this method was used to find a node on the list if the node is no there then it return false
	public boolean find(Node<E> target) throws NoSuchElementException {
		if(length == 0) {
			throw new NoSuchElementException("FindNode(): Empty list ");
		}
		SNode<E> current = first;
		while(current != null && current.getNext() !=target) {
			current = current.getNext();
		}
		if(current.getNext()==target) {
			return true;
		}
		return false;
		
	}

}
