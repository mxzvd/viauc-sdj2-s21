package utility.collection;

public class CircularLinkedList<T> implements ListADT<T>
{
   private LinearNode<T> rear;
   private int count;

   public CircularLinkedList()
   {
      rear = null;
      count = 0;
   }

   @Override
   public void add(int index, T element)
   {
      if (index < 0 || index > count)
      {
         throw new IndexOutOfBoundsException("index = " + index
               + " but size = " + count);
      }
      if (count == 0)
      {
         rear = new LinearNode<>(element, rear);
         rear.setNext(rear); // circular
      }
      else
      {
         LinearNode<T> previous = getNode(index - 1);
         LinearNode<T> newNode = new LinearNode<>(element, previous.getNext());
         previous.setNext(newNode);
         if (index == count)
         {
            rear = newNode;
         }
      }
      count++;
   }

   @Override
   public void add(T element)
   {
      add(count, element);
   }

   @Override
   public void set(int index, T element)
   {
      if (index < 0 || index >= count)
      {
         throw new IndexOutOfBoundsException("index = " + index
               + " but size = " + count);
      }
      getNode(index).setElement(element);
   }

   @Override
   public T get(int index)
   {
      if (index < 0 || index >= count)
      {
         throw new IndexOutOfBoundsException("index = " + index
               + " but size = " + count);
      }
      return getNode(index).getElement();
   }

   @Override
   public T remove(int index)
   {
      if (index < 0 || index >= count)
      {
         throw new IndexOutOfBoundsException("index = " + index
               + " but size = " + count);
      }
      T element = null;
      LinearNode<T> previous = getNode(index - 1);
      element = previous.getNext().getElement();
      previous.setNext(previous.getNext().getNext());
      if (index == count - 1)
      {
         rear = previous;
      }
      count--;
      return element;
   }

   @Override
   public T remove(T element)
   {
      T returnedElement = null;
      if (element != null)
      {
         if (rear != null && element.equals(rear.getElement()))
         {
            returnedElement = rear.getElement();
            if (count == 1)
            {
               rear = null;
            }
            else
            {
               rear = rear.getNext();
            }
            count--;
            return returnedElement;
         }
         LinearNode<T> previous = rear;
         for (int i = 0; i < count-1; i++)
         {
            if (element.equals(previous.getNext().getElement()))
            {
               returnedElement = previous.getNext().getElement();
               previous.setNext(previous.getNext().getNext());
               count--;
               return returnedElement;
            }
            previous = previous.getNext();
         }
      }
      else
      {
         if (rear != null && null == rear.getElement())
         {
            if (count == 1)
            {
               rear = null;
            }
            else
            {
               rear = rear.getNext();
            }
            count--;
            return null;
         }
         LinearNode<T> previous = rear;
         for (int i = 0; i < count-1; i++)
         {
            if (null == previous.getNext().getElement())
            {
               returnedElement = previous.getNext().getElement();
               previous.setNext(previous.getNext().getNext());
               count--;
               return returnedElement;
            }
            previous = previous.getNext();
         }
      }
      throw new IllegalStateException("Element " + element + " not found");
   }

   @Override
   public int indexOf(T element)
   {
      LinearNode<T> current = rear;
      if (current == null)
      {
         return -1;
      }
      if (element != null)
      {
         for (int i = 0; i < count; i++)
         {
            current = current.getNext();
            if (element.equals(current.getElement()))
            {
               return i;
            }
         }
      }
      else
      {
         for (int i = 0; i < count; i++)
         {
            current = current.getNext();
            if (null == current.getElement())
            {
               return i;
            }
         }
      }
      return -1;
   }

   @Override
   public boolean contains(T element)
   {
      return indexOf(element) != -1;
   }

   @Override
   public boolean isEmpty()
   {
      return count == 0;
   }

   @Override
   public boolean isFull()
   {
      return false;
   }

   @Override
   public int size()
   {
      return count;
   }

   public String toString()
   {
      LinearNode<T> current = rear;
      String s = "{";
      for (int i = 0; i < count; i++)
      {
         current = current.getNext();
         s += current.getElement();
         if (i < count - 1)
         {
            s += ", ";
         }
      }
      s += "}";
      return s;
   }

   private LinearNode<T> getNode(int index)
   {
      if (index == count - 1) // last
      {
         return rear;
      }
      LinearNode<T> current = rear;
      for (int i = -1; i < index; i++)
      {
         current = current.getNext();
      }
      return current;
   }

}
