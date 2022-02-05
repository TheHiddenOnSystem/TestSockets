package org.example.oldmodechat.server;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class HandlerServerClient implements Runnable{
    public static final int SIZE_BUFFER=1524;
    private final SocketChannel client;
    private ByteBuffer byteBufferReader = getByteBufferReader();
    private ByteBuffer byteBufferWriter=getByteBufferWriter();
    //private ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

    public HandlerServerClient(SocketChannel serverSocket) {
        this.client = serverSocket;

    }


    @Override
    public void run() {

        try {
            while(client.isConnected()){
                client.read(getByteBufferReader());

                System.out.println(getByteBufferReader().asCharBuffer());

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public synchronized ByteBuffer getByteBufferReader() {
        if(byteBufferReader ==null){
            byteBufferReader =ByteBuffer.allocate(SIZE_BUFFER);
        }
        return byteBufferReader;
    }

    public synchronized ByteBuffer getByteBufferWriter() {
        if(byteBufferWriter==null){
            byteBufferWriter=ByteBuffer.allocate(SIZE_BUFFER);
        }

        return byteBufferWriter;
    }
}
