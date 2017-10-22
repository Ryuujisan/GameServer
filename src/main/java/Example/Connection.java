package Example;

import io.yuri.yuriserver.player.AbstractConnection;

import java.io.IOException;
import java.net.Socket;

public class Connection extends AbstractConnection {
    public Connection(Socket socket) throws IOException {
        super(socket);
    }
}
