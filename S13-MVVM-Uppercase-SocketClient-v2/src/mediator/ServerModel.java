package mediator;

import java.io.IOException;

public interface ServerModel {
    void connect() throws IOException;
    void disconnect() throws IOException;
    String convert(String source) throws IOException, InterruptedException;
}
