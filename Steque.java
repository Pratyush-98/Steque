/*
 *  File: Steque.java
 *  Author: 
 *  Date: 18th Nov, 2021
 *  ---------------------------------------
 *  Steque is stack-ended queue data structure which supports
 *  stack operations: pop and push, along with queue enqueue 
 *  operation.
 *  
 *  Salient features:
 *  1. Operations like push, pop, enqueue are supported.
 *  2. NullPointerException is thrown when null element is inserted.
 *  3. UnsupportedOperationException is thrown when using remove() method.
 *  4. The data structure is iterable and is implemented for generic type.
 *  
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * Steque is a stack-ended data structure which 
 * supports stack operations as well as queue's 
 * enqueue operation.
 * 
 * @author 
 * @version 1.0
 *
 */
public class Steque<Item> implements Iterable<Item> 
{

    private Item[] stack;
    private int size;
    private int rear;
    private int last;
    private static int capacity = 10;
    /**
     * constructs a steque object.
     */
    public Steque() 
    {
        stack = (Item[]) new Object[capacity];
        size = 0;
        rear = 0;

    }
    private void resize() 
    {
		int newCapacity = 2*stack.length;
		Item[] newstack = (Item[]) new Object[newCapacity];
		for(int i=0; i<stack.length; i++) 
        {
			newstack[i] = stack[i];
		}
		stack = newstack;
    }
    
    
    /**
     * inserts an item in the steque in queue fashion.
     * @param item Item to be inserted.
     */
    public void enqueue(Item item) 
    {
        if (item == null) throw new IllegalArgumentException();
        if(size >= stack.length) resize();
        for(int i=stack.length-1; i > 0;i--) stack[i] = stack[i-1];
        stack[0] = item;
        size++;
    }
    
    
    /**
     * inserts an item in the steque in stack fashion.
     * @param item Item to be inserted.
     */
    public void push(Item item) 
    {
        if(item==null) throw new IllegalArgumentException();
        if(size >= stack.length) resize();
        stack[size] = item;
        size++;
    }
    
    /**
     * pops a least recent item in steque.
     * @return Item object from steque.
     */
    public Item pop() 
    {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = stack[size-1];
        stack[size-1] = null;
        size--;
        return item;
    }
    
    /**
     * checks to see if steque is empty.
     * @return true if steque is empty, false otherwise.
     */
    public boolean isEmpty() 
    {
        return size==0;
    }
    
    /**
     * return the number of elements currently in the steque.
     * @return size as integer.
     */
    public int size() 
    {
        return size;
    }
    
    /**
     * returns an iterator over the elements 
     * stored in steque.
     * 
     */
    public Iterator<Item> iterator() 
    {
        return new ArrayIterator();
    }
    public class ArrayIterator implements Iterator<Item> 
    {
        public int i = size-1;

     
        public boolean hasNext()
        {
            return i >= 0;
        }

      
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }

        
        public Item next() 
        {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = stack[i];
            i--;
            return item;
        }
    }
    public static void main(String[] args)
    {
        Steque<Integer> pratyush = new Steque<Integer>();
        pratyush.enqueue(14);
        pratyush.enqueue(17);
        pratyush.enqueue(20);
        pratyush.push(5);
        pratyush.push(10);
        pratyush.push(15);
        pratyush.enqueue(25);
        System.out.println("empty:"+pratyush.isEmpty());
        System.out.println("size:"+pratyush.size());
        Iterator<Integer> stequeue = pratyush.iterator();
        System.out.println("steque elements");
        while(stequeue.hasNext())
        System.out.println(stequeue.next());
        System.out.println("popped elements");
        while(!pratyush.isEmpty())
        {
           System.out.println(pratyush.pop());
        }
    }
}
