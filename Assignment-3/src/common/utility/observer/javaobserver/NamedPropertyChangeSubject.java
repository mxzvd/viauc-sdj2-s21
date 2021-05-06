package common.utility.observer.javaobserver;

import java.beans.PropertyChangeListener;

/**
 * Subject interface for the Observer pattern, when Listeners implements the
 * java.beans.PropertyChangeListener interface and listeners are added to
 * a specified event name.
 * Listeners should be added to a collection and be notified when an event
 * with the specified property name is fired.
 * The convenient way to implement the concrete subject is to delegate to an
 * instance variable of type java.beans.PropertyChangeSupport when implementing
 * the two interface methods and firing of events.
 *
 * @author Steffen Vissing Andersen
 * @version 1.0 - april 2021
 */
public interface NamedPropertyChangeSubject
{
   /**
    * Adding a listener for a specified property name
    * @param propertyName event name
    * @param listener listener to be added
    */
   void addListener(String propertyName, PropertyChangeListener listener);
   /**
    * Removing a listener for a specified property name
    * @param propertyName event name
    * @param listener listener to be removed
    */
   void removeListener(String propertyName, PropertyChangeListener listener);
}