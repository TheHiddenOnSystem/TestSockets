package org.example.oldmodechat.connections.sockets;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ServiceServerConnection extends Thread {
    private volatile ServerSocketChannel serverSocketChannel;
    private FuntionInterfaceServerSocketService funtionInterfaceServerSocketService=null;
    private List<ClientConnection> clientConnections=null;

    public ServiceServerConnection(ServerSocketChannel serverSocketChannel,FuntionInterfaceServerSocketService funtionInterfaceServerSocketService) {
        this.serverSocketChannel = serverSocketChannel;
        this.funtionInterfaceServerSocketService=funtionInterfaceServerSocketService;
    }
    public ServiceServerConnection(ServerSocketChannel serverSocketChannel){
        this.serverSocketChannel=serverSocketChannel;
        clientConnections=new ArrayList<>();
    }

    @Override
    public void run() {
        if(funtionInterfaceServerSocketService!=null){
            funtionInterfaceServerSocketService.serviceServerCustom(getServerSocketChannel());
        }else{
            configDefault();
        }
    }
    private void configDefault(){
        do{
            try {
                clientConnections.add(new ClientConnection(this.serverSocketChannel.accept(),String.format("Client_%d",getClientConnections().size())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(serverSocketChannel.isOpen());
    }

    public synchronized ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public List<ClientConnection> getClientConnections() {
        return clientConnections;
    }
}
