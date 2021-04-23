package common.utility.observer.subject;

import common.utility.observer.listener.GeneralListener;

/**
 * Local subject interface for the remote observer pattern developed by this author.
 * This is intended to be extended by a concrete subject without any remote
 * listeners.
 *
 * @param <S> type of first value in events to be fired
 * @param <T> type of second value in events to be fired
 *
 * @author Steffen Vissing Andersen
 * @version 1.4 - april 2021
 */
public interface LocalSubject<S, T> extends GeneralSubject<S, T>
{
  /**
   * Adding a listener either to all events or to one or more property names.
   * @param listener the listener to be added
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be added as a lister for all events.
   * @return true if the listener has been added. It is optional if this return
   * true if the listener has been added to at least one of the property names
   * or if it successfully has been added to all listed
   */
  boolean addListener(GeneralListener<S, T> listener, String... propertyNames);

  /**
   * Removing a listener either from all events or from one or more property names.
   * @param listener the listener to be removed
   * @param propertyNames a var-args list of property names. If empty, then the
   *                      listener should be removed as a lister for all events.
   * @return true if the listener has been removed. It is optional if this return
   * true if the listener has been removed from at least one of the property names
   * or if it successfully has been removed from all listed
   */
  boolean removeListener(GeneralListener<S, T> listener,
      String... propertyNames);
}
