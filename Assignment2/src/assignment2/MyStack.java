package assignment2;

public class MyStack<E> {
    private MyDoublyLinkedList<E> stack;

    public MyStack(){
        this.stack = new MyDoublyLinkedList<>();
    }

    public void push(E element){
        this.stack.addLast(element);
    }

    public E pop(){
        return this.stack.removeLast();
    }

    public E peek(){
        return this.stack.peekLast();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    public void clear(){
        this.stack.clear();
    }

    public int getSize(){
        return this.stack.getSize();
    }
}
