package common.utility.observer.event;

import common.utility.observer.subject.GeneralSubject;

import java.io.Serializable;

/**
 * Event class for the remote observer pattern developed by this author.
 * The event to be send from subject to listeners either
 * locally or via RMI.
 * The event contains two values of generic type, a property name and the
 * source of the subject creating the event.
 * The two event values may represent old and new values as in the observer
 * model in the java API (if both types are the same). However, the two
 * values could represent different types unrelated to each other.
 *
 * @param <S> type of first value in the event, value1
 * @param <T> type of second value in the event, value2
 * @author Steffen Vissing Andersen
 * @version 1.4 - april 2021
 */
public class ObserverEvent<S, T> implements Serializable
{
  private static final long serialVersionUID = 2910;

  private final GeneralSubject<S, T> source;
  private final String propertyName;
  private final S value1;
  private final T value2;

  /**
   * Create a new ObserverEvent.
   *
   * @param source       source of the property
   * @param propertyName the property's name
   * @param value1       the 1st value, e.g. old value of the property
   * @param value2       the 2nd value, e.g. new value of the property
   */
  public ObserverEvent(GeneralSubject<S, T> source, String propertyName,
      S value1, T value2)
  {
    this.source = source;
    this.propertyName = propertyName;
    this.value1 = value1;
    this.value2 = value2;
  }

  /**
   * Getter for the source
   * @return the source
   */
  public GeneralSubject<S, T> getSource()
  {
    return source;
  }

  /**
   * Getter for the property name
   * @return the property name
   */
  public String getPropertyName()
  {
    return propertyName;
  }

  /**
   * Getter for the 1st value
   * @return the 1st value
   */
  public S getValue1()
  {
    return value1;
  }

  /**
   * Getter for the 2nd value
   * @return the 2nd value
   */
  public T getValue2()
  {
    return value2;
  }

  /**
   * A string representation of the event
   * @return a string with event information
   */
  @Override public String toString()
  {
    return "ObserverEvent [source=" + source + ", propertyName=" + propertyName
        + ", value1=" + value1 + ", value2=" + value2 + "]";
  }

  /**
   * A hash code for the event
   * @return the hash code as an int
   */
  @Override public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result =
        prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
    result = prime * result + ((source == null) ? 0 : source.hashCode());
    result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
    result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
    return result;
  }

  /**
   * Comparing two objects
   * @param obj the object to compare with this ObserverEvent
   * @return true if the argument represents an ObserverEvent with the same
   * two values, property name and source
   */
  @Override public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ObserverEvent<S, T> other = (ObserverEvent<S, T>) obj;
    if (propertyName == null)
    {
      if (other.propertyName != null)
        return false;
    }
    else if (!propertyName.equals(other.propertyName))
      return false;
    if (source == null)
    {
      if (other.source != null)
        return false;
    }
    else if (!source.equals(other.source))
      return false;
    if (value1 == null)
    {
      if (other.value1 != null)
        return false;
    }
    else if (!value1.equals(other.value1))
      return false;
    if (value2 == null)
    {
      if (other.value2 != null)
        return false;
    }
    else if (!value2.equals(other.value2))
      return false;
    return true;
  }

}
