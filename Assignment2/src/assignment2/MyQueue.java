package assignment2;

import java.util.Iterator;

public class MyQueue<E> {
    private MyDoublyLinkedList<E> queue;

    public MyQueue(){
        this.queue = new MyDoublyLinkedList<>();
    }

    public void enqueue(E element){
        this.queue.addLast(element);
    }

    public E dequeue(){
        return this.queue.removeFirst();
    }

    public boolean isEmpty(){
        return this.queue.isEmpty();
    }

    public void clear(){
        this.queue.clear();
    }

    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof MyQueue)){
            return false;
        }

        MyQueue<E> toCompare = (MyQueue<E>) obj;
        if (toCompare.queue.getSize() != this.queue.getSize()){
            return false;
        }

        Iterator<E> iter = this.queue.iterator();
        Iterator<E> otherIter = toCompare.queue.iterator();

        while(iter.hasNext() && otherIter.hasNext()) {
            E thisE = iter.next();
            E otherE = otherIter.next();

            if(thisE != otherE){
                return false;
            }
        }
        return true;
    }
}
