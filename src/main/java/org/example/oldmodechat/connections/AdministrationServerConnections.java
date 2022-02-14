package org.example.oldmodechat.connections;

import org.example.oldmodechat.connections.session.ModelSession;
import org.example.oldmodechat.connections.sockets.*;
import org.example.oldmodechat.util.CustomLogger;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.logging.Level;

/**
 * This is an administration ServiceConnection
 */
public abstract class AdministrationServerConnections<T extends ModelSession> {

    private CustomLogger logger=new CustomLogger(AdministrationServerConnections.class.getName(), AdministrationServerConnections.class.getName());
    protected ServiceServerConnection<T> serviceServerConnection;
    protected final FuntionInterfaceServerSocketService<T> funtionInterfaceServerSocketService;


    public AdministrationServerConnections(int serverPort, FuntionInterfaceServerSocketService<T> funtionInterfaceServerSocketService) throws FileNotFoundException {
        this.funtionInterfaceServerSocketService = funtionInterfaceServerSocketService;
        configInitLogger();
        configServiceServerConnection(serverPort);

        this.serviceServerConnection.start();
    }

    /**
     * Configuration server connection
     * @param port
     */
    private void configServiceServerConnection(int port){
        try {
            final ServerSocketChannel serverSocketChannel=SelectorProvider.provider().openServerSocketChannel();
            serverSocketChannel.bind(new InetSocketAddress(port));
            this.serviceServerConnection=new ServiceServerConnection(serverSocketChannel,funtionInterfaceServerSocketService);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Configuration Logger
     */
    private void configInitLogger(){
        try {
            //Config Info Handler config
            logger.getLogger().addHandler(CustomLogger.StreamHandler(Level.INFO, logger.getBase_path()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ServiceServerConnection<T> getServiceServerConnection() {
        return serviceServerConnection;
    }
}
