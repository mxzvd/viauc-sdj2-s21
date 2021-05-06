package common.utility.observer.listener;

import common.utility.observer.event.ObserverEvent;

/**
 * Listener interface for the remote observer pattern developed by this author.
 * This is the general interface extended by LocalListener and RemoteListener
 * and should not be extended directly by concrete listeners.
 *
 * @param <S> type of first value in the event being received
 * @param <T> type of second value in the event being received
 *
 * @author Steffen Vissing Andersen
 * @version 1.4 - april 2021
 */
public interface GeneralListener<S, T>
{
   /**
    * The method being executed when the subject fires an event.
    * @param event the event fired
    * @throws Exception in the general case an exception may be thrown
    */
   void propertyChange(ObserverEvent<S, T> event) throws Exception;
}
