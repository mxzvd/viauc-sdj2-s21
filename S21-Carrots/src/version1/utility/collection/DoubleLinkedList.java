package version1.utility.collection;

public class DoubleLinkedList<T> implements ListADT<T>
{
   private int count;
   private version1.utility.collection.DoubleLinkedNode<T> front;

   public DoubleLinkedList()
   {
      front = null;
      count = 0;
   }

   private version1.utility.collection.DoubleLinkedNode<T> getNode(int index) // could be more efficient
   {
      version1.utility.collection.DoubleLinkedNode<T> current = front;
      for (int i = 0; i < index; i++)
      {
         current = current.getNext();
      }
      return current;
   }

   @Override
   public void add(int index, T element)
   {
      if (index < 0 || index > count)
      {
         throw new IndexOutOfBoundsException();
      }

      if (index == 0)
      {
         version1.utility.collection.DoubleLinkedNode<T> newNode = new version1.utility.collection.DoubleLinkedNode<T>(null, element,
               front);
         if (count > 0)
         {
            newNode.setPrevious(front.getPrevious());
            front.getPrevious().setNext(newNode);
            front.setPrevious(newNode);
         }
         else
         {
            newNode.setPrevious(newNode);
            newNode.setNext(newNode);
         }
         front = newNode;
      }
      else
      {
         version1.utility.collection.DoubleLinkedNode<T> previous = getNode(index - 1);
         version1.utility.collection.DoubleLinkedNode<T> newNode = new version1.utility.collection.DoubleLinkedNode<T>(previous,
               element, previous.getNext());
         previous.getNext().setPrevious(newNode);
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
         throw new IndexOutOfBoundsException();
      }
      getNode(index).setElement(element);
   }

   @Override
   public T get(int index)
   {
      if (index < 0 || index >= count)
      {
         throw new IndexOutOfBoundsException();
      }
      return getNode(index).getElement();
   }

   @Override
   public T remove(int index)
   {
      if (index < 0 || index >= count)
      {
         throw new IndexOutOfBoundsException();
      }
      T element = null;
      if (index == 0)
      {
         element = front.getElement();
         if (count == 1)
         {
            front = null;
         }
         else
         {
            front.getPrevious().setNext(front.getNext());
            front = front.getNext();
         }
      }
      else
      {
         version1.utility.collection.DoubleLinkedNode<T> previous = getNode(index - 1);
         element = previous.getNext().getElement();
         previous.getNext().getNext().setPrevious(previous);
         previous.setNext(previous.getNext().getNext());
      }
      count--;
      return element;
   }

   @Override
   public T remove(T element)
   {
      version1.utility.collection.DoubleLinkedNode<T> current = front;
      if (element != null) // looking for a non-null element
      {
         for (int i = 0; i < count; i++)
         {
            if (element.equals(current.getElement()))
            {
               current.getPrevious().setNext(current.getNext());
               current.getNext().setPrevious(current.getPrevious());
               if (i == 0)
               {
                  front = front.getNext();
               }
               count--;
               return element;
            }
            current = current.getNext();
         }
      }
      else
      // looking for a null element
      {
         for (int i = 0; i < count; i++)
         {
            if (current.getElement() == null)
            {
               current.getNext().setPrevious(current.getPrevious());
               current.getPrevious().setNext(current.getNext());
               if (i == 0)
               {
                  front = front.getNext();
               }
               count--;
               return element;
            }
            current = current.getNext();
         }
      }
      throw new IllegalStateException(); // cannot find the element
   }


   @Override
   public int indexOf(T element)
   {
      version1.utility.collection.DoubleLinkedNode<T> current = front;
      if (element != null) // looking for a non-null element
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
      // looking for a null element
      {
         for (int i = 0; i < count; i++)
         {
            if (current.getElement() == null)
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
      return indexOf(element) > -1;
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

   @Override
   public String toString()
   {
      String s = "{";
      DoubleLinkedNode<T> current = front;
      for (int i = 0; i < count; i++)
      {
         s += current.getElement();
         if (i < count - 1)
         {
            s += ", ";
         }
         current = current.getNext();
      }
      s += "}";
      return s;
   }
}
