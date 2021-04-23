package client.mediator;

import common.utility.observer.listener.RemoteListener;
import java.io.IOException;

public interface ServerModel extends RemoteListener<String, String> {
    String convert(String source) throws IOException, InterruptedException;
}
