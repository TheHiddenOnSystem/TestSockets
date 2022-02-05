package org.example.oldmodechat.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerSocketClient extends Thread{

    private volatile ServerSocketChannel serverSocketChannel;
    /*
    Crear un objeto credenciales y tambien para administrar el hilo y las sesiones
     */
    public final static int DEFAULT_SIZE_BUFFER=1524;


    private boolean finish=false;
    public ServerSocketClient() {

        try {
            serverSocketChannel=makeServerSocketChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private ServerSocketChannel makeServerSocketChannel() throws IOException {
        final ServerSocketChannel serverSocketChannel=SelectorProvider.provider().openServerSocketChannel();
        serverSocketChannel.bind(new InetSocketAddress(6000));

        return serverSocketChannel;
    }

    @Override
    public void run() {



        while(!finish&&serverSocketChannel.isOpen()){

            try {
                final SocketChannel s=serverSocketChannel.accept();
                if(s.isConnected()){
                    new Thread(new HandlerServerClient(s)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
