package version1.utility.collection;

public class DoubleLinkedNode<T>
{
   private T element;
   private DoubleLinkedNode<T> next;
   private DoubleLinkedNode<T> prev;

   public DoubleLinkedNode(DoubleLinkedNode<T> prev, T data,
         DoubleLinkedNode<T> next)
   {
      this.element = data;
      this.next = next;
      this.prev = prev;
   }

   public DoubleLinkedNode(T data)
   {
      this(null, data, null);
      this.prev = this;
      this.next = this;
   }

   public T getElement()
   {
      return element;
   }

   public DoubleLinkedNode<T> getNext()
   {
      return next;
   }

   public DoubleLinkedNode<T> getPrevious()
   {
      return prev;
   }

   public void setElement(T element)
   {
      this.element = element;
   }

   public void setNext(DoubleLinkedNode<T> next)
   {
      this.next = next;
   }

   public void setPrevious(DoubleLinkedNode<T> prev)
   {
      this.prev = prev;
   }

   @Override
   public String toString()
   {
      if (element == null)
      {
         return null;
      }
      else
      {
         return element.toString();
      }
   }
}