package org.example.oldmodechat.connections;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class TestConnections {

    @Test
    public void upServer() throws FileNotFoundException {


    }
    @Test
    public void connectClient(){
        try {
            final SocketChannel socketChannel= SelectorProvider.provider().openSocketChannel();
            socketChannel.connect(new InetSocketAddress("localhost",6000));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
