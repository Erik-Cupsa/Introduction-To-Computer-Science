package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E>{
    private DNode head;
    private DNode tail;

    public void add(E element){
        DNode node = new DNode();
        node.element = element;

        if(this.head == null){
            this.head = node;
            this.tail = node;
        }
        else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size ++;
    }

    public E remove(){
        if(this.tail == null) throw new NoSuchElementException();
        else if (this.head==this.tail){
            E element = this.tail.element;
            this.head = null;
            this.tail = null;
            this.size --;
            return element;
        }
        else{
            E element = this.tail.element;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size --;
            return element;
        }
    }

    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(E element){
        DNode node = new DNode();
        node.element = element;

        if(this.head == null) {
            this.head = node;
            this.tail = node;
        }
        else{
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.size++;
    }

    public void addLast(E element){
        add(element);
    }

    public E removeFirst(){
        if(this.head == null){
            throw new NoSuchElementException();
        }
        else if (this.head==this.tail){
            E element = this.head.element;
            this.head = null;
            this.tail = null;
            this.size --;
            return element;
        }
        else{
            E element = this.head.element;
            this.head = this.head.next;
            this.head.prev = null;
            this.size --;
            return element;
        }
    }

    public E removeLast(){
       return remove();
    }

    public E peekFirst(){
        if(this.head == null){
            throw new NoSuchElementException();
        }
        else{
            return this.head.element;
        }
    }

    public E peekLast(){
        if(this.tail == null){
            throw new NoSuchElementException();
        }
        else{
            return this.tail.element;
        }
    }

    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof MyDoublyLinkedList)){
            return false;
        }

        MyDoublyLinkedList<E> toCompare = (MyDoublyLinkedList<E>) obj;
        if (toCompare.getSize() != this.getSize()){
            return false;
        }

        Iterator<E> iter = this.iterator();
        Iterator<E> otherIter = toCompare.iterator();

        while(iter.hasNext() && otherIter.hasNext()) {
            E thisE = iter.next();
            E otherE = otherIter.next();

            if(!(thisE.equals(otherE))){
                return false;
            }
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private class DNode {
        private E element;
        private DNode next;
        private DNode prev;
    }

    private class DLLIterator implements Iterator<E> {
        DNode curr;

        public DLLIterator() {
            this.curr = head;
        }

        public boolean hasNext() {
            return this.curr != null;
        }

        public E next() {
            if (!this.hasNext())
                throw new NoSuchElementException();

            E element = this.curr.element;
            this.curr = this.curr.next;
            return element;
        }
    }
}

