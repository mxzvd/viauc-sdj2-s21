package common.network;

import common.utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteServerInterface extends RemoteSubject<String, String> {
    String request(String messageToConvert) throws RemoteException;
}
