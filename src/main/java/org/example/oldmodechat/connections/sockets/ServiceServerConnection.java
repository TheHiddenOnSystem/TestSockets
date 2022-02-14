package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.connections.session.ModelSession;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @param <T> To Define Session to make wth clients
 *
 */
public class ServiceServerConnection<T extends ModelSession> extends Thread {
    protected volatile ServerSocketChannel serverSocketChannel;
    protected FuntionInterfaceServerSocketService<T> funtionInterfaceServerSocketService=null;
    protected List<ClientConnection<T>> clientConnections=new ArrayList<>();

    public ServiceServerConnection(ServerSocketChannel serverSocketChannel,FuntionInterfaceServerSocketService funtionInterfaceServerSocketService) {
        this.serverSocketChannel = serverSocketChannel;
        this.funtionInterfaceServerSocketService=funtionInterfaceServerSocketService;
    }


    @Override
    public void run() {
        if(funtionInterfaceServerSocketService!=null){
            funtionInterfaceServerSocketService.serviceServerCustom(getServerSocketChannel(), getClientConnections());
        }else{
            configDefault();
        }
    }

    /**
     * !!!Don`t Use!!
     * Config method run() default
     */
    private void configDefault(){

        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public synchronized List<ClientConnection<T>> getClientConnections() {
        return clientConnections;
    }


}
