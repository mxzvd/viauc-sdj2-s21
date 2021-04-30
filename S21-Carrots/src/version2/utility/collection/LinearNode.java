package version2.utility.collection;

public class LinearNode<T>
{
   private LinearNode<T> next;
   private T element;

   public LinearNode(T element, LinearNode<T> next)
   {
      this.next = next;
      this.element = element;
   }

   public LinearNode(T element)
   {
      this.next = this;       // circular 
      this.element = element;
   }

   public LinearNode()
   {
      this.next = null;      
      this.element = null;
   }

   public T getElement()
   {
      return element;
   }

   public void setElement(T element)
   {
      this.element = element;
   }

   public LinearNode<T> getNext()
   {
      return next;
   }

   public void setNext(LinearNode<T> next)
   {
      this.next = next;
   }
   
   
}
