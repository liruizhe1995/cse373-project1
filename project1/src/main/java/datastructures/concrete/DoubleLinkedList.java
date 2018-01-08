package datastructures.concrete;

import datastructures.interfaces.IList;
import misc.exceptions.EmptyContainerException;
import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Note: For more info on the expected behavior of your methods, see
 * the source code for IList.
 */
public class DoubleLinkedList<T> implements IList<T> {
    // You may not rename these fields or change their types.
    // We will be inspecting these in our private tests.
    // You also may not add any additional fields.
    private Node<T> front;
    private Node<T> back;
    private int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }
    
    // in progress (need test)
    @Override
    public void add(T item) {
        if (this.front == null) {                                                    
            // empty list                                        
            this.front = new Node<T>(item);
            this.back = this.front;
        } else {                               
            // non-empty list
            back.next = new Node<T>(item);
            back.next.prev = back;
            back = back.next;
        }
        this.size++;
    }
    
    // in progress (need test)
    @Override
    public T remove() {
        if(size == 0) {
            throw new EmptyContainerException();
        }
        T result = back.data;
        back = back.prev;
        back.next = null;
        size--;
        return result;
    }

    // in progress (need test)
    @Override
    public T get(int index) {
        checkIndexInBound(index);
        Node<T> current = goTo(index);
        return current.data;
    }
    
    // in progress (incomplete)
    @Override
    public void set(int index, T item) {
        checkIndexInBound(index);
        // use insert and delete to implement?
    }

    // in progress (need test)
    @Override
    public void insert(int index, T item) {
        checkIndexInBound(index);
        if(index == size-1) {
            // end case
            add(item);
        } else if(index == 0){
            // front case
            Node<T> temp = new Node<T>(item);
            front.prev = temp;
            temp.next = front;
            front = temp;
        } else {
            //middle case 
            Node<T> temp = new Node<T>(item);
            Node<T> current = goTo(index);
            temp.prev = current.prev;
            current.prev.next = temp;
            current.prev=temp;
            temp.next=current;
        }
        size++;
    }
  
    // in progress (need test)
    @Override
    public T delete(int index) {
        checkIndexInBound(index);
        T result;
        if(index == size-1) {
            // end case
            result=remove();
        } else if(index == 0){
            // front case
            result = front.data;
            front.next.prev = null;
            front = front.next;
        } else {
            //middle case 
            Node<T> current = goTo(index);
            result = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return result;
    }
    
    // in progress (need test)
    @Override
    public int indexOf(T item) {
        int index = 0;
        Node<T> current = front;
        while (current != null) {
            if (current.data.equals(item)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T other) {
        return indexOf(other) != -1;
    }
    
    private void checkIndexInBound(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    
 // Returns a reference to the node object representing the index'th element
    // in the list.  Used as a helper by many of the public methods.
    private Node<T> goTo(int index) {
        Node<T> current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    @Override
    public Iterator<T> iterator() {
        // Note: we have provided a part of the implementation of
        // an iterator for you. You should complete the methods stubs
        // in the DoubleLinkedListIterator inner class at the bottom
        // of this file. You do not need to change this method.
        return new DoubleLinkedListIterator<>(this.front);
    }

    private static class Node<E> {
        // You may not change the fields in this node or add any new fields.
        public final E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(E data) {
            this(null, data, null);
        }

        // Feel free to add additional constructors or methods to this class.
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> current;

        public DoubleLinkedListIterator(Node<T> current) {
            // You do not need to make any changes to this constructor.
            this.current = current;
        }

        /**
         * Returns 'true' if the iterator still has elements to look at;
         * returns 'false' otherwise.
         */
        public boolean hasNext() {
            throw new NotYetImplementedException();
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() {
            throw new NotYetImplementedException();
        }
    }
}
