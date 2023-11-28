package linkedlists.LinkedList_Exercises;

//  import java.util.Iterator;

public class TestSLinkedList {

	public	static	void   main(String[] args){	 
		
		//  HERE I HAVE A FEW SIMPLE TESTS.  YOU SHOULD WRITE YOUR OWN !!!!
		
		SLinkedList<String> list = new SLinkedList<String>();		
		
		list.addFirst("a"); 
		list.show();
		list.addLast("b");  
		list.show();
		list.addLast("c"); 
		list.show();
		list.add(1,"d"); 
		list.show();
		list.addFirst("e");
		list.show();
	    list.reverse();
	    list.show();
	}
}
