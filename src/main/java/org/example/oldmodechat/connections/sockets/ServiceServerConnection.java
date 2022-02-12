package org.example.oldmodechat.connections.sockets;

import org.example.utils.connection.credential.DefaultCredential;
import org.example.utils.connection.credential.ModelCredential;
import org.example.utils.connection.menssaje.ModelMessage;
import org.example.utils.connection.session.DefaultSession;
import org.example.utils.connection.session.ModelSession;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class ServiceServerConnection<T extends ModelCredential,K extends ModelMessage> extends Thread {
    private volatile ServerSocketChannel serverSocketChannel;
    private FuntionInterfaceServerSocketService funtionInterfaceServerSocketService=null;
    private List<ClientConnection<T,K>> clientConnections=null;

    public ServiceServerConnection(ServerSocketChannel serverSocketChannel,FuntionInterfaceServerSocketService funtionInterfaceServerSocketService) {
        this.serverSocketChannel = serverSocketChannel;
        this.funtionInterfaceServerSocketService=funtionInterfaceServerSocketService;
        this.clientConnections=new ArrayList<>();
    }
    public ServiceServerConnection(ServerSocketChannel serverSocketChannel){
        this.serverSocketChannel=serverSocketChannel;
        this.clientConnections=new ArrayList<>();
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
                final SocketChannel socketAccpet=this.serverSocketChannel.accept();
                /**
                 * In try
                 */
                clientConnections.add(
                        new ClientConnection<T,K>(
                                socketAccpet,
                                String.format("Client-%d",clientConnections.size()),
                                (ModelSession<T, K>) new ModelSession<>(
                                        new ModelCredential("asd","asd"),
                                        new ArrayList<>())

                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(serverSocketChannel.isOpen());
    }

    public synchronized ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public List<ClientConnection<T, K>> getClientConnections() {
        return clientConnections;
    }
}
