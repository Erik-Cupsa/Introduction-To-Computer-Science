package linkedlists.LinkedList_Exercises;


public class DLinkedList_Test{
	 public static void main(String[] args){
		 
		  DLinkedList<String> list = new DLinkedList<String>(); 

		  //  HERE I HAVE A VERY BASIC TEST.   ADD MORE !
		  
		  list.addFirst("a");
		  list.addLast("b");
		  list.addLast("c");
		  list.addLast("d");
		  list.addLast("e");
		  list.show();
		  
		  list.reverse();
  	      list.show();

	 }	  
}
