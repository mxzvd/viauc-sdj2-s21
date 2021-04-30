package version1.utility.collection;

public class LinearLinkedList<T> implements ListADT<T>
{
   private version1.utility.collection.LinearNode<T> front;
   private int count;

   public LinearLinkedList()
   {
      // version 1
      front = null;
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
      if (index == 0)
      {
         front = new version1.utility.collection.LinearNode<>(element, front);
      }
      else
      {
         version1.utility.collection.LinearNode<T> previous = getNode(index - 1);
         version1.utility.collection.LinearNode<T> newNode = new version1.utility.collection.LinearNode<>(element, previous.getNext());
         previous.setNext(newNode);
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
      if (index == 0)
      {
         element = front.getElement();
         front = front.getNext();
      }
      else
      {
         version1.utility.collection.LinearNode<T> previous = getNode(index - 1);
         element = previous.getNext().getElement();
         previous.setNext(previous.getNext().getNext());
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
         if (front != null && element.equals(front.getElement()))
         {
            returnedElement = front.getElement();
            front = front.getNext();
            count--;
            return returnedElement;
         }
         version1.utility.collection.LinearNode<T> previous = front;
         for (int i = 1; i < count; i++)
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
         if (front != null && null == front.getElement())
         {
            returnedElement = front.getElement();
            front = front.getNext();
            count--;
            return returnedElement;
         }
         version1.utility.collection.LinearNode<T> previous = front;
         for (int i = 1; i < count; i++)
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
      version1.utility.collection.LinearNode<T> current = front;
      if (element != null)
      {
         for (int i = 0; i < count; i++)
         {
            if (element.equals(current.getElement()))
            {
               return i;
            }
            current = current.getNext();
         }
      }
      else
      {
         for (int i = 0; i < count; i++)
         {
            if (null == current.getElement())
            {
               return i;
            }
            current = current.getNext();
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
      version1.utility.collection.LinearNode<T> current = front;
      String s = "{";
      for (int i=0; i<count; i++)
      {
         s += current.getElement();
         if (i < count-1)
         {
            s += ", ";
         }
         current = current.getNext();
      }
      s += "}";
       return s;
   }

   private version1.utility.collection.LinearNode<T> getNode(int index)
   {
      LinearNode<T> current = front;
      for (int i = 0; i < index; i++)
      {
         current = current.getNext();
      }
      return current;
   }

}
