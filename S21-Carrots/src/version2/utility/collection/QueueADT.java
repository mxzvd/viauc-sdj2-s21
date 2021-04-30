package version2.utility.collection;

/**
 * QueueADT defines the interface to a queue collection - the abstract data
 * type: Queue. The Queue should allow duplicate elements and could allow
 * <code>null</code> elements.
 *
 * @author Steffen Vissing Andersen
 * @version 1.4, 17/3/2019
 * @version (Version 1.0, 8/12/2008 by Lewis and Chase)
 * @param <T>
 *           the data type of elements in the collection
 */

public interface QueueADT<T>
{
   /**
    * Adds one element to the rear of this queue.
    * 
    * @param element
    *           the element to be added to the rear of this queue
    * @exception IllegalStateException
    *               if the element cannot be added i.e. if the queue is full
    * @exception IllegalArgumentException
    *               if there is a mismatch in the input, e.g. if a
    *               <code>null</code> element is not allowed
    */
   public void enqueue(T element);

   /**
    * Removes and returns the element at the front of this queue.
    * 
    * @return a reference to the element removed from the front of the queue
    * @exception IllegalStateException
    *               if the queue is empty
    */
   public T dequeue();

   /**
    * Returns without removing the element at the front of this queue.
    * 
    * @return a reference to the element in the front of the queue
    * @exception IllegalStateException
    *               if the queue is empty
    */
   public T first();

   /**
    * The index of the first occurrence of the element specified, i.e. the
    * number of elements to dequeue until the next one is the element specified.
    * If the element is not in the queue then -1 is returned.
    * 
    * @param element
    *           the element to be found
    * @return the number of elements to dequeue until the next one is the
    *         element specified. If the element is not in the queue then -1 is
    *         returned.
    */
   public int indexOf(T element);

   /**
    * Returns <code>true</code> if this queue contains no elements.
    * 
    * @return boolean whether or not this queue is empty
    */
   public boolean isEmpty();

   /**
    * Returns <code>true</code> if this queue is full.
    * 
    * @return boolean whether or not this queue is full
    */
   public boolean isFull();

   /**
    * Returns the number of elements in this queue.
    * 
    * @return the number of elements in this queue
    */
   public int size();

   /**
    * Returns the maximum number of elements in this queue. Unbounded queues
    * return -1.
    * 
    * @return the maximum number of elements in this queue or -1 if unbounded
    */
   public int capacity();

}
