package version2.utility.collection;

/**
 * ArrayQueue is an implementation of QueueADT, the interface to a queue
 * collection - the abstract data type: Queue. The queue allows duplicate
 * elements and <code>null</code> elements. The implementation is an array and
 * includes two indices for front and rear, respectively. When removing from the
 * front (method <code>enqueue</code>) no shifting is performed and in order to
 * use all allocated positions the last position is connected to the first
 * position to make a circle. The queue is unbounded/resizable but it is not
 * thread safe.
 *
 * @param <T>
 *           the data type of elements in the collection
 * @author Steffen Vissing Andersen
 * @version 1.4, 21/3/2019
 */
public class ArrayQueue<T> implements QueueADT<T>
{
   private static final int DEFAULT_CAPACITY = 16;
   private int front;
   private int count;
   private T[] queue;

   /**
    * A constructor creating an unbounded queue with an initial capacity
    * specified.
    *
    * @param initialCapacity
    *           the initial capacity (the length of the array).
    */
   @SuppressWarnings("unchecked")
   public ArrayQueue(int initialCapacity)
   {
      this.queue = (T[]) new Object[initialCapacity];
      this.front = 0;
      this.count = 0;
   }

   /**
    * A constructor creating an unbounded queue. The default capacity is 16 and
    * every time <code>enqueue</code> is called on a full queue, the queue
    * capacity is doubled.
    */
   public ArrayQueue()
   {
      this(DEFAULT_CAPACITY);
   }

   /**
    * Adds one element to the rear of this queue.
    *
    * @param element
    *           the element to be added to the rear of this queue
    */
   @Override
   public void enqueue(T element)
   {
      if (count >= queue.length)
      {
         expandCapacity();
      }
      int rear = (count + front) % queue.length;
      queue[rear] = element;
      count++;
   }

   /**
    * Removes and returns the element at the front of this queue.
    *
    * @return a reference to the element removed from the front of the queue
    * @throws IllegalStateException
    *            if the queue is empty
    */
   @Override
   public T dequeue()
   {
      T tmp = first();
      queue[front] = null; // clean up
      front = (front + 1) % queue.length;
      count--;
      return tmp;
   }

   /**
    * Returns without removing the element at the front of this queue.
    *
    * @return a reference to the element in the front of the queue
    * @throws IllegalStateException
    *            if the queue is empty
    */
   @Override
   public T first()
   {
      if (isEmpty())
      {
         throw new IllegalStateException("Queue is empty");
      }
      return queue[front];
   }

   /**
    * The index of the first occurrence of the element specified, i.e. the
    * number of elements to dequeue until the next one is the element specified.
    * In other words if <code>element</code> is not <code>null</code> then the
    * method return the index of the first occurrence when
    * <code>element.equals(queue[index])</code> returns <code>true</code>. If
    * the element is not in the queue then -1 is returned.
    *
    * @param element
    *           the element to be found
    * @return the number of elements to dequeue until the next one is the
    *         element specified. If the element is not in the queue then -1 is
    *         returned.
    */
   @Override
   public int indexOf(T element)
   {
      if (element == null)
      {
         for (int i = 0; i < count; i++)
         {
            int index = (i + front) % queue.length;
            if (element == (queue[index]))
            {
               return i;
            }
         }
      }
      else
      {
         for (int i = 0; i < count; i++)
         {
            int index = (i + front) % queue.length;
            if (element.equals(queue[index]))
            {
               return i;
            }
         }
      }
      return -1;
   }

   /**
    * Returns <code>true</code> if this queue contains no elements.
    *
    * @return boolean whether or not this queue is empty
    */
   @Override
   public boolean isEmpty()
   {
      return count == 0;
   }

   /**
    * Returns <code>true</code> if this queue is full but because this queue is
    * unbounded the method always return <code>false</code>.
    *
    * @return boolean whether or not this queue is full
    */
   @Override
   public boolean isFull()
   {
      return false;
   }

   /**
    * Returns the number of elements in this queue.
    *
    * @return the number of elements in this queue
    */
   @Override
   public int size()
   {
      return count;
   }

   /**
    * Returns the maximum number of elements in this queue but because this
    * queue is unbounded it always return <code>Integer.MAX_VALUE</code>.
    *
    * @return the maximum number of elements in this queue or
    *         <code>Integer.MAX_VALUE</code> if unbounded
    */
   @Override
   public int capacity()
   {
      return Integer.MAX_VALUE;
   }

   /**
    * A string representation of the queue with elements comma separated and
    * enclosed in a set of curly braces. <br>
    * Example (3 string elements): "{A, B, C}"<br>
    * Example (2 Integer elements): "{7, 11}"<br>
    * Example (an empty queue): "{}"
    *
    * @return a string representation of the queue
    */
   public String toString()
   {
      String s = "{";
      for (int i = 0; i < count; i++)
      {
         int index = (i + front) % queue.length;
         s += queue[index];
         if (i < count - 1)
         {
            s += ", ";
         }
      }
      s += "}";
      return s;
   }

   private void expandCapacity()
   {
      @SuppressWarnings("unchecked")
      T[] tmp = (T[]) new Object[queue.length * 2];
      for (int i = 0; i < count; i++)
      {
         int index = (i + front) % queue.length;
         tmp[i] = queue[index];
      }
      queue = tmp;
      front = 0;
   }

}
