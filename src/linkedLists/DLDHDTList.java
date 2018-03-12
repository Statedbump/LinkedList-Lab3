package linkedLists;

import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 

	public DLDHDTList() { 
		
		header = trailer = null;
		header = new DNode<E>();
		trailer = new DNode<E>();
		header.setNext(trailer);
		trailer.setPrev(header);

	}

	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}

	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}


	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 

	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> nd = (DNode<E>) nuevo; 
		DNode<E> nA = (DNode<E>) target; 
		DNode<E> nB = nA.getPrev(); 
		nB.setNext(nd);
		nA.setPrev(nd);
		nd.setPrev(nB);
		nd.setNext(nA);
		length++;


	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if(target == this.getLastNode()) {
			throw new NoSuchElementException("getNodeAfter: Last Node of the list");
		}

		DNode<E> td =  (DNode<E>) target;
		return td.getNext(); 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if(target == this.getFirstNode())
			throw new NoSuchElementException("getNodeBefore: First Node of the list");

		DNode<E> td = (DNode<E>)target;
		return td.getPrev(); 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {

		DNode<E> td = (DNode<E>) target; 
		DNode<E> nA = td.getNext(); 
		DNode<E> nB = td.getPrev();
		nB.setNext(nA);
		nA.setPrev(nB);
		td.setNext(null);
		td.setPrev(null);
		length --;

	}

	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}

	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		DNode<E> current = header.getNext();
		while (current != null && current.getNext() == trailer) {
			current.setElement(null);
			current.setNext(null);
			current.setPrev(null);
			current = current.getNext();
		}

		header.setNext(trailer);
		trailer.setPrev(header);
		length = 0;

	}
	public DLDHDTList<E> clone() throws CloneNotSupportedException{
		DLDHDTList<E> cDL = (DLDHDTList<E>)super.clone();
		if(length >0) {
		DNode<E> first = (DNode<E>) this.getFirstNode();
		cDL.addFirstNode(first);
		DNode<E>current = (DNode<E>)this.getNodeAfter(getFirstNode());
		while(current != null) {
			cDL.addLastNode(current);
			current = current.getNext();
		}
		}
		return cDL;
	}

	protected void finalize() throws Throwable {
		try {
			this.destroy(); 
		} finally {
			super.finalize();
		}
	}
	

}
