package version2.utility.collection;

/**
 * BoundedArrayQueue<T> is an implementation of QueueADT<T>, the interface to a queue
 * collection - the abstract data type: Queue. The queue allows duplicate
 * elements and <code>null</code> elements and cannot resize.
 * The implementation is an array and includes two indices for front and rear, respectively.
 * When removing from the front (method <code>enqueue</code>) no shifting is performed and
 * in order to use all allocated positions the last position is connected to the first position
 * to make a circle.
 * Note that the queue is not thread safe.
 *
 * @param <T> the data type of elements in the collection
 * @author Steffen Vissing Andersen
 * @version 1.4, 17/3/2019
 */
public class BoundedArrayQueue<T> implements QueueADT<T>
{
  private int front;
  private int count;
  private T[] queue;

  /**
   * A constructor creating a bounded queue with a capacity as specified.
   *
   * @param capacity the capacity (the length of the array).
   */
  @SuppressWarnings("unchecked")
public BoundedArrayQueue(int capacity)
  {
    this.queue = (T[]) new Object[capacity];
    this.front = 0;
    this.count = 0;
  }

  /**
   * Adds one element to the rear of this queue.
   *
   * @param element the element to be added to the rear of this queue
   * @throws IllegalStateException if the element cannot be added i.e. if the queue is full
   */
  @Override public void enqueue(T element)
  {
    if (isFull())
    {
      throw new IllegalStateException("Queue is full");
    }
    int rear = (count + front) % queue.length;
    queue[rear] = element;
    count++;
  }

  /**
   * Removes and returns the element at the front of this queue.
   *
   * @return a reference to the element removed from the front of the queue
   * @throws IllegalStateException if the queue is empty
   */
  @Override public T dequeue()
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
   * @throws IllegalStateException if the queue is empty
   */
  @Override public T first()
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
   * @param element the element to be found
   * @return the number of elements to dequeue until the next one is the
   * element specified. If the element is not in the queue then -1 is
   * returned.
   */
  @Override public int indexOf(T element)
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
  @Override public boolean isEmpty()
  {
    return count == 0;
  }

  /**
   * Returns <code>true</code> if this queue is full. Unbounded queues always
   * return <code>false</code>.
   *
   * @return boolean whether or not this queue is full
   */
  @Override public boolean isFull()
  {
    return count >= queue.length;
  }

  /**
   * Returns the number of elements in this queue.
   *
   * @return the number of elements in this queue
   */
  @Override public int size()
  {
    return count;
  }

  /**
   * Returns the maximum number of elements in this queue.
   *
   * @return the maximum number of elements in this queue
   */
  @Override public int capacity()
  {
    return queue.length;
  }

  /**
   * A string representation of the queue with elements comma separated and
   * enclosed in a set of curly braces.
   * Example (3 string elements): "{A, B, C}"<br/>
   * Example (2 Integer elements): "{7, 11}"<br/>
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

}
