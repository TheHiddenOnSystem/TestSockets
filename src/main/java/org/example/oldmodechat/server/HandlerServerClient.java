package org.example.oldmodechat.server;

import sun.awt.CharsetString;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class HandlerServerClient implements Runnable{
    public static final int SIZE_BUFFER=1000;
    private final SocketChannel client;
    //private ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
    private volatile BufferedReader reader=null;
    public HandlerServerClient(SocketChannel serverSocket) {
        this.client = serverSocket;

    }


    @Override
    public void run() {

        try {
            while(!client.isConnected()){
                final ByteBuffer buffer=ByteBuffer.allocate(SIZE_BUFFER);
                buffer.asCharBuffer().append('a');
                client.write(buffer);

                client.close();

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public synchronized BufferedReader getReader() {
        return reader;
    }
}
