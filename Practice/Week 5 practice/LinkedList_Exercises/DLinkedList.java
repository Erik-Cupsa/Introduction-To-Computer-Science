package linkedlists.LinkedList_Exercises;

public class DLinkedList<E> {

	  private DNode<E>  dummyHead;
	  private DNode<E>  dummyTail;	
	  private int size;

	  /*  
	   *  Constructor
	   *  Creates two dummy nodes in the linked list.  An empty list will contain only these dummy nodes.   
	   *  These dummy nodes simplify the code by allowing you to avoid special treatment when you are
	   *  adding or removing at the front or back (head or tail).  
	   */
	  
	  public DLinkedList() { 
	    dummyHead = new DNode<E>(null);	
	    dummyTail = new DNode<E>(null);	
	    dummyHead.next = dummyTail;	
	    dummyTail.prev = dummyHead;
	    size = 0;
	  }
	  
	  /*
	   * Adds element before index i.  Use addLast() to add at the end of the list.
	   */

	  public void add( int index, E element){
		  if ((index < 0) | (index > size)){
			  throw new IndexOutOfBoundsException();
		  }
		  else {
			  if (index == size){
				  addLast(element);
			  }
			  else
				  addBefore( element, getNode(index) );  // addBefore is a helper method - see below
			  // It increments size.
		  };
	  }
	  

	  public void addFirst( E  element) {
		  if (size == 0)
			  addBefore( element, dummyTail );
		  else
			  add(0, element);
	  }
	  
	  public void addLast( E  element) {
		 addBefore(element, dummyTail );
	  }

	  //  empty the list
	  
	  public void clear(){
		    dummyHead.next = dummyTail;	
		    dummyTail.prev = dummyHead;		
		    size = 0;
	  }

	  public boolean isEmpty() { 
	  	return (size == 0); 
	  }
	  
	 
	  public   E   get( int index ){
		  if ((index < 0) || (index >= size)){
			  throw new IndexOutOfBoundsException();
		  }
		  else{
			  return getNode(index).element;
		  }
	  }  
	  
	  public   E  getFirst(){
		  return getNode(0).element;
	  }
	  
	  public   E  getLast(){
		  return getNode(size-1).element;
	  }

	  public int getSize(){
		  return size;
	  }

	  public void remove(int  index){
		  DNode<E> node  = getNode(index);           
		  node.prev.next = node.next ;
		  node.next.prev = node.prev;
		  node.prev      = null;   //  unnecessary  
		  node.next      = null;   //  unnecessary
		  size--;
	  }
	 
	  /*
	   *    To reverse the nodes in the list,  loop through starting at the node after dummyHead.
	   *    In each pass, we have a current node 'cur'.   At the start of the pass,  cur's next and prev
	   *    references correspond to the original list.   During that pass, these 
	   *    references should be swapped.   
	   */

	  public void reverse(){
		  DNode<E>  cur,  curNext;
		  cur = dummyHead.next;
		  
		  //  swap the next and prev references,  and advance cur
		  
		  while (cur != dummyTail){
			  curNext  = cur.next;
			  cur.next = cur.prev;
			  cur.prev = curNext;
			  cur = curNext;
		  }
		  
		  //  All nodes in the list point have correct prev and next field, except for the dummy nodes.
		  
		  dummyTail.next = dummyTail.prev;
		  dummyTail.prev = null;			
		  dummyHead.prev = dummyHead.next;
		  dummyHead.next = null;			
		  
		  //  swap dummyHead and dummyTail
		  cur = dummyHead;
		  dummyHead = dummyTail;
		  dummyTail = cur;
	  }
	 
	  
	  public void show(){
		  DNode<E> node = dummyHead;
		  System.out.print("[ ");
		  while (node.next != dummyTail){
			  node = node.next;
			  System.out.print(" " + node.element);
		  }
		  System.out.println( " ]");
	  }
	  
	  //  Helper methods
	  
	  /**
	   * @return address of the index-th node in the list, where index is in 0 to size-1
	   */
	  
	  private  DNode<E>  getNode( int index) {
		  DNode<E>  node;
		  if ((index < 0) || (index > size) || (size == 0)){
			throw new IndexOutOfBoundsException();
		  }
		  else{
			  
			  //  If you're looking for an element in the first half of the list,
			  //  then search from the beginning. 
			  //  Otherwise, search backwards from the end.
			  
			  if (index < size/2){
				  node = dummyHead.next;
				  //  the loop below will exit with node at the index-th node in the list
				  for (int j = 0; j < index; j++)
					  node = node.next;
			  }
			  else{
				  node = dummyTail.prev;
				  //  the loop below will exit with node at the index-th node in the list
				  for (int j = size-1; j > index; j--)
					  node = node.prev;
			  }
		  }
		  return node; 
	  }	  

	  /*
	   *  Make a new node with a given element.  Insert it before a given node.  
	   *  If we are inserting at the end of the list, then the given node is 
	   *  the dummy tail node. 
	   */
	  
	  private  void  addBefore(E element, DNode<E> givenNode ){
		    DNode<E>   newNode = new DNode<E>(element);	
		    newNode.prev = givenNode.prev ;
		    newNode.next = givenNode ;
		    newNode.prev.next = newNode;
		    givenNode.prev = newNode ;
		    size++;
	  }
	  
	
	  class DNode<T> {    // use a different generic parameter here to avoid
		                  // warning conflict with DLinkedList's generic parameter.

		  private T element;	
		  private DNode<T> next;
		  private DNode<T> prev;	

		  DNode(T e) {
		    element = e;
		    prev = null;
		    next = null;
		  }

		  T getElement() {
			 return element; 
			}

		  void setElement(T newElem) { 
			     element = newElem; 
			  }
		  
		  DNode<T> getPrev() { 
			return prev; 
		  }

		  void setPrev(DNode<T> newPrev) { 
		     prev = newPrev; 
		  }

		  DNode<T> getNext() { 
			    return next; 
			  }

		  void setNext(DNode<T> newNext) { 
		 	next = newNext; 
		  }  
	}
	  
}
