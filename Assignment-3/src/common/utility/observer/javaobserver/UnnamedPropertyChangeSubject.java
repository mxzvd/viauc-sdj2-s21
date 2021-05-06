package common.utility.observer.javaobserver;

import java.beans.PropertyChangeListener;

/**
 * Subject interface for the Observer pattern, when Listeners implements the
 * java.beans.PropertyChangeListener interface and listeners are added to
 * all events independent of event name.
 * Listeners should be added to a
 * collection and be notified when any type of events are fired. The convenient
 * way to implement the concrete subject is to delegate to an instance variable
 * of type java.beans.PropertyChangeSupport when implementing the two interface
 * methods and firing of events.
 *
 * @author Steffen Vissing Andersen
 * @version 1.0 - april 2021
 */
public interface UnnamedPropertyChangeSubject
{
  /**
   * Adding a listener
   *
   * @param listener the listener to be added
   */
  void addListener(PropertyChangeListener listener);
  /**
   * Removing a listener
   *
   * @param listener the listener to be removed
   */
  void removeListener(PropertyChangeListener listener);
}