package assignment2;

public abstract class MyLinkedList<E> implements MyList<E> {
    protected int size;

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }
}

