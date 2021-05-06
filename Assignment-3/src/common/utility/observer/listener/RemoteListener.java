package common.utility.observer.listener;

import common.utility.observer.event.ObserverEvent;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote listener interface for the remote observer pattern developed by this author.
 * This is intended to be implemented by concrete listeners with a remote access
 * using RMI, e.g. using the RMI registry to lookup a subject stub.
 *
 * @param <S> type of first value in the event being received
 * @param <T> type of second value in the event being received
 * @author Steffen Vissing Andersen
 * @version 1.4 - april 2021
 */
public interface RemoteListener<S, T> extends Remote, GeneralListener<S, T>
{
  /**
   * The method being executed when the subject fires an event.
   *
   * @param event the event fired
   * @throws RemoteException when something goes wrong e.g. in connection or
   *                         receiving
   */
  void propertyChange(ObserverEvent<S, T> event) throws RemoteException;
}
