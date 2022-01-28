package org.example.oldmodechat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class ChannelClient extends Thread{
    private volatile SocketChannel socketChannel;

    public ChannelClient(String host,int port){
        try {
            socketChannel=SelectorProvider.provider().openSocketChannel();
            socketChannel.connect(new InetSocketAddress(host,port));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        while(socketChannel.isConnected()){
            try {

                ByteBuffer buffer= ByteBuffer.allocate(100);

                socketChannel.read(buffer);
                socketChannel.close();
                System.out.println(buffer.asCharBuffer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
