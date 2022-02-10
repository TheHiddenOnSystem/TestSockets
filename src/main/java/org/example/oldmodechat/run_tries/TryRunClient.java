package org.example.oldmodechat.run_tries;

import org.example.oldmodechat.connections.sockets.ClientConnection;

import java.io.IOException;

public class TryRunClient {
    public static void main(String[] args) {
        try {
            final ClientConnection clientConnection=new ClientConnection("prueba","127.0.0.1",6000);
            clientConnection.getWriterBuffer().put(Byte.parseByte("123"));
            clientConnection.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
