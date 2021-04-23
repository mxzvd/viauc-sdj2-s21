package common.utility.observer.listener;

import common.utility.observer.event.ObserverEvent;

/**
 * Local listener interface for the remote observer pattern developed by this author.
 * This is intended to be implemented by concrete listeners with a local access
 * to the concrete subject, i.e. having a dependency or association to the
 * concrete subject without a remote access.
 *
 * @param <S> type of first value in the event being received
 * @param <T> type of second value in the event being received
 *
 * @author Steffen Vissing Andersen
 * @version 1.4 - april 2021
 */
public interface LocalListener<S, T> extends GeneralListener<S, T>
{
   /**
    * The method being executed when the subject fires an event.
    * @param event the event fired
    */
   void propertyChange(ObserverEvent<S, T> event);
}
