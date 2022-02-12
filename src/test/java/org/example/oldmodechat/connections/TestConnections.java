package org.example.oldmodechat.connections;

import org.example.oldmodechat.connections.sockets.ClientConnection;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class TestConnections {

    @Test
    public void upServer() throws FileNotFoundException {
        final AdministrationServerConnections administrationServerConnections=new AdministrationServerConnections(6000);

    }
    @Test
    public void connectClient(){


    }

}
