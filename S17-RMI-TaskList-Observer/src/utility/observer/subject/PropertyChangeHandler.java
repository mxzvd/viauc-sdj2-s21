package utility.observer.subject;

import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;

import java.rmi.ConnectException;
import java.rmi.NoSuchObjectException;
import java.util.*;

/**
 * A class handling the listeners, adding removing and firing events.
 * The concrete subject is intended to delegate to this class and not
 * handling the listeners directly, i.e. having a PropertyChangeHandler
 * instance variable.
 * <br> <br>
 * There is an option to use threads when firing events.
 * When an event is fired, one thread is created to loop through the listeners to find
 * the listeners to receive the event for the given property name.
 * Not to make listeners wait too long e.g. for a
 * disconnected listener, new threads are created for each listener to
 * call the listeners propertyChange method. An event queue is used for the
 * thread looping for listeners and individual event queues for the listener
 * threads to make sure that events are received in the same order as fired.
 * However, because of threads and difference in connection and listeners
 * implementation of the propertyChange method, one listener may have received
 * the 3rd event before another lister receives the 1st event.
 * <br><br>
 *   <img src="myobserver1.PNG" alt="MyObserver" width="600">
 *   <br>
 * @param <S> type of first value in events being fired
 * @param <T> type of second value in events being fired
 */
public final class PropertyChangeHandler<S, T> implements GeneralSubject<S, T>
{
  private GeneralSubject<S, T> source;
  private Map<String, ArrayDeque<GeneralListener<S, T>>> listeners;
  private boolean oldNewCheck = false;
  private boolean usingThreads = false;
  private boolean removeDeadListeners = true;
  private EventQueue queue;

  /**
   * Constructor
   * @param source the source representing the concrete subject
   * @param usingThreads true if a separate thread is executing the logic when firing events.
   * @param oldNewCheck true if events fired only calls listeners if value1 and
   *                    value2 are different, representing e.g. old and new value
   * @param removeDeadListeners true if listeners which cannot be contacted 3
   *                            times in a row will be removed
   */
  public PropertyChangeHandler(GeneralSubject<S, T> source,
      boolean usingThreads, boolean oldNewCheck, boolean removeDeadListeners)
  {
    this.removeDeadListeners = removeDeadListeners;
    this.usingThreads = usingThreads;
    this.oldNewCheck = oldNewCheck;
    this.source = source;
    this.listeners = new HashMap<>();
    this.queue = new EventQueue();
  }

  /**
   * Constructor with default of removeDeadListeners being true
   * @param source the source representing the concrete subject
   * @param usingThreads true if a separate thread is executing the logic when firing events.
   * @param oldNewCheck true if events fired only calls listeners if value1 and
   *                    value2 are different, representing e.g. old and new value
   */
  public PropertyChangeHandler(GeneralSubject<S, T> source,
      boolean usingThreads, boolean oldNewCheck)
  {
    this(source, usingThreads, oldNewCheck, true);
  }

  /**
   * Constructor with defaults: removeDeadListeners = true,  oldNewCheck = false
   * @param source the source representing the concrete subject
   * @param usingThreads true if a separate thread is executing the logic when firing events.
   */
  public PropertyChangeHandler(GeneralSubject<S, T> source,
      boolean usingThreads)
  {
    this(source, usingThreads, false, true);
  }

  /**
   * Constructor with defaults: usingThreads = false, removeDeadListeners = true,
   * oldNewCheck = false
   * @param source the source representing the concrete subject
   */
  public PropertyChangeHandler(GeneralSubject<S, T> source)
  {
    this(source, false, false, true);
  }

  private void fire(ObserverEvent<S, T> event) throws Exception
  {
    if (oldNewCheck && ((event.getValue1() != null && event.getValue1()
        .equals(event.getValue2())) || (event.getValue1() == null
        && event.getValue2() == null)))
    {
      return;
    }
    ArrayDeque<GeneralListener<S, T>> queue;
    synchronized (this)
    {
      queue = listeners.get(event.getPropertyName());
      if (queue != null)
      {
        queue = queue.clone();
      }
    }
    if (queue != null)
    {
      for (GeneralListener<S, T> listener : queue)
      {
        if (usingThreads)
        {
          listener.propertyChange(event);
        }
        else
        {
          updateListener(event, listener);
        }
      }
    }
    synchronized (this)
    {
      queue = listeners.get(null); // all unnamed property listeners
      if (queue != null)
      {
        queue = queue.clone();
      }
    }
    if (queue != null)
    {
      for (GeneralListener<S, T> listener : queue)
      {
        if (usingThreads)
        {
          listener.propertyChange(event);
        }
        else
        {
          updateListener(event, listener);
        }
      }
    }
  }

  /**
   * Firing an event, calling the propertyChange method for the listeners of
   * the specific property name and listeners listening to all events. Note that
   * the listeners are not called if the oldNewCheck field is true and the two
   * event values are identical.
   * <br>
   * If the usingThreads field is true, then a separate thread will be
   * created to make the loop to find the related listeners
   * and for each listener a new thread is created and calls the listeners
   * propertyChange method. The idea is to avoid blocking the subject when evens
   * are fired and not to make individual listeners have to wait for each other.
   * EventQueues are uses both for the thread making the loop and for the
   * listener threads to make sure that events are received in the same order
   * as send.
   * @param event the event to fire
   */
  public void firePropertyChange(ObserverEvent<S, T> event)
  {
    if (usingThreads)
    {
      synchronized (this)
      {
        queue.add(event);
      }
      new Thread(() -> {
        ObserverEvent<S, T> nextEvent;
        synchronized (PropertyChangeHandler.this)
        {
          nextEvent = queue.remove();
        }
        try
        {
          fire(nextEvent);
        }
        catch (Exception e)
        {
          //
        }
      }).start();
    }
    else
    {
      try
      {
        fire(event);
      }
      catch (Exception e)
      {
        //
      }
    }
  }

  /**
   * Firing an event, calling the propertyChange method for the listeners of
   * the specific property name and listeners listening to all events. Note that
   * the listeners are not called if the oldNewCheck field is true and the two
   * event values are identical.
   * <br>
   * If the usingThreads field is true, then a separate thread will be
   * created to make the loop to find the related listeners
   * and for each listener a new thread is created and calls the listeners
   * propertyChange method. The idea is to avoid blocking the subject when evens
   * are fired and not to make individual listeners have to wait for each other.
   * EventQueues are uses both for the thread making the loop and for the
   * listener threads to make sure that events are received in the same order
   * as send.
   * @param propertyName name of the event
   * @param value1 the 1st value
   * @param value2 the 2nd value
   */
  public void firePropertyChange(String propertyName, S value1, T value2)
  {
    ObserverEvent<S, T> event = new ObserverEvent<>(source, propertyName,
        value1, value2);
    firePropertyChange(event);
  }

  /**
   * Adding a listener either to all events or to one or more property names.
   * The listener may either be a LocalListener or a RemoteListener.
   * @param listener the listener to be added
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener will be added as a lister for all events.
   * @return true if the listener has been added to all of the property names
   * listed. If already added to all then it will not be added to a specific
   * event name also.
   */
  public synchronized boolean addListener(GeneralListener<S, T> listener,
      String... propertyNames)
  {
    GeneralListener<S, T> generalListener = listener;
    if (usingThreads)
    {
      generalListener = new GeneralListenerProxy(listener);
      GeneralListener<S, T> reference = getListenerReference(generalListener,
          false);
      if (reference != null)
      {
        generalListener = reference;
      }
    }
    if (getListenerReference(generalListener, true) != null)
    {
      return false; // already listening to all
    }
    if (propertyNames == null || propertyNames.length == 0)
    {
      ArrayDeque<GeneralListener<S, T>> queue = listeners.get(null);
      if (queue == null)
      {
        queue = new ArrayDeque<>();
        listeners.put(null, queue);
      }
      if (!queue.contains(generalListener))
      {
        queue.addLast(generalListener);
      }
      return queue.contains(generalListener);
    }
    boolean added = true;
    for (String propertyName : propertyNames)
    {
      ArrayDeque<GeneralListener<S, T>> queue = listeners.get(propertyName);
      if (queue == null)
      {
        queue = new ArrayDeque<>();
        listeners.put(propertyName, queue);
      }
      if (!queue.contains(generalListener))
      {
        queue.addLast(generalListener);
      }
      else
      {
        added = false;
      }
    }
    return added;
  }

  /**
   * Removing a local or remote listener either from all events or from one or
   * more property names.
   * @param listener the listener to be removed
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be removed as a lister for all events.
   * @return true if the listener has been removed for all or at least one of the
   * event names given.
   */
  public boolean removeListener(GeneralListener<S, T> listener,
      String... propertyNames)
  {
    boolean removed = false;
    GeneralListener<S, T> generalListener = listener;
    if (usingThreads)
    {
      generalListener = new GeneralListenerProxy(listener);
    }
    if (propertyNames == null || propertyNames.length == 0)
    {
      ArrayDeque<GeneralListener<S, T>> queue;
      synchronized (this)
      {
        queue = listeners.get(null);
      }
      if (queue == null)
      {
        return false;
      }
      removed = queue.remove(generalListener);
    }
    else
    {
      boolean removedOne;
      for (String propertyName : propertyNames)
      {
        ArrayDeque<GeneralListener<S, T>> queue;
        synchronized (this)
        {
          queue = listeners.get(propertyName);
        }
        if (queue != null)
        {
          removedOne = queue.remove(generalListener);
          if (removedOne)
          {
            removed = true;
          }
        }
      }
    }
    return removed;
  }

  private GeneralListener<S, T> getListenerReference(
      GeneralListener<S, T> listener, boolean onlyNullListening)
  {
    ArrayDeque<GeneralListener<S, T>> queue;
    synchronized (this)
    {
      queue = listeners.get(null);
    }
    if (queue != null)
    {
      for (GeneralListener<S, T> storedListener : queue)
      {
        if (storedListener.equals(listener))
        {
          return storedListener;
        }
      }
    }
    if (!onlyNullListening)
    {
      Set<String> propertyNames;
      synchronized (this)
      {
        propertyNames = listeners.keySet();
      }
      for (String propertyName : propertyNames)
      {
        synchronized (this)
        {
          queue = listeners.get(propertyName);
        }
        if (queue != null)
        {
          for (GeneralListener<S, T> storedListener : queue)
          {
            if (storedListener.equals(listener))
            {
              return storedListener;
            }
          }
        }
      }
    }
    return null;
  }

  private void updateListener(ObserverEvent<S, T> event, GeneralListener<S, T> listener)
  {
    for (int attempt = 1; attempt <= 3; attempt++)
    {
      try
      {
        listener.propertyChange(event);
        break;
      }
      catch (ConnectException | NoSuchObjectException e)
      {
        if (attempt >= 3)
        {
          if (removeDeadListeners)
          {
            removeDeadListener(listener);
          }
          return;
        }
      }
      catch (Exception e)
      {
        // could not access listener, maybe recover?
      }
    }
  }

  private boolean removeDeadListener(GeneralListener<S, T> listener)
  {
    boolean removedUnnamed = removeListener(listener);
    Set<String> propertyNames;
    synchronized (PropertyChangeHandler.this)
    {
      propertyNames = listeners.keySet();
    }
    String[] names = new String[propertyNames.size()];
    propertyNames.toArray(names);
    boolean removedNamed = removeListener(listener, names);
    return removedUnnamed || removedNamed;
  }

  private class GeneralListenerProxy implements GeneralListener<S, T>
  {
    private GeneralListener<S, T> listener;
    private EventQueue queue;

    public GeneralListenerProxy(GeneralListener<S, T> listener)
    {
      this.listener = listener;
      this.queue = new EventQueue();
    }

    @Override public void propertyChange(ObserverEvent<S, T> event)
    {
      synchronized (queue)
      {
        queue.add(event);
      }
      new Thread(() -> {
        try
        {
          ObserverEvent<S, T> nextEvent;
          synchronized (queue)
          {
            try
            {
              nextEvent = queue.remove();
            }
            catch (IllegalStateException e)
            {
              nextEvent = null;
            }
          }
          if (nextEvent != null)
          {
            for (int attempt = 1; attempt <= 3; attempt++)
            {
              try
              {
                synchronized (queue)
                {
                  if (getListenerReference(this, false) != null)
                  {
                    listener.propertyChange(nextEvent);
                  }
                }
                break;
              }
              catch (ConnectException | NoSuchObjectException e)
              {
                if (attempt >= 3)
                {
                  if (removeDeadListeners)
                  {
                    synchronized (queue)
                    {
                      while (queue.size() > 0)
                      {
                        queue.remove();
                      }
                      removeDeadListener(listener);
                    }
                    return;
                  }
                }
              }
              catch (Exception e)
              {
                // could not access listener, maybe recover?
              }
            }
          }
        }
        catch (IllegalStateException e)
        {
          // no events - has been removed
        }
        catch (Exception e)
        {
          // another exception?
        }
      }).start();
    }

    @Override public boolean equals(Object o)
    {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      GeneralListenerProxy that = (GeneralListenerProxy) o;
      return Objects.equals(listener, that.listener);
    }
  }

  private class EventQueue
  {
    private ArrayDeque<ObserverEvent<S, T>> events;

    public EventQueue()
    {
      events = new ArrayDeque<>();
    }

    public synchronized void add(ObserverEvent<S, T> event)
    {
      events.addLast(event);
    }

    public synchronized ObserverEvent<S, T> remove()
    {
      if (events.isEmpty())
      {
        throw new IllegalStateException("Event queue is empty");
      }
      ObserverEvent<S, T> event = events.removeFirst();
      return event;
    }

    public synchronized int size()
    {
      return events.size();
    }

    @Override public synchronized String toString()
    {
      return "EventQueue{" + "queue=" + events + '}';
    }
  }

}