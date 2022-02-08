package org.example.oldmodechat.gui.connections;

import org.example.oldmodechat.gui.connections.sockets.ServiceServerConnection;
import org.example.oldmodechat.util.CustomLogger;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class AdministrationServerConnections {

    private CustomLogger logger=new CustomLogger(AdministrationServerConnections.class.getName(),AdministrationServerConnections.class.getName());
    private final ExecutorService threadPoolExecutor= Executors.newSingleThreadExecutor();
    private String ip="localhost";
    private ServiceServerConnection serviceServerConnection;



    public AdministrationServerConnections(int serverPort) throws FileNotFoundException {
        configInitLogger();
        configServiceServerConnection(serverPort);

    }

    private void configServiceServerConnection(int port){
        try {
            final ServerSocketChannel serverSocketChannel= SelectorProvider.provider().openServerSocketChannel();
            serverSocketChannel.bind(new InetSocketAddress("localhost",port));
            serviceServerConnection=new ServiceServerConnection(serverSocketChannel,null);
            serviceServerConnection.start();
        } catch (IOException e) {
            CustomLogger.Use_Log(logger.getLogger(),Level.INFO,"Error update service connection");
            e.printStackTrace();
        }
    }

    private void configInitLogger(){
        try {
            //Config Info Handler config
            logger.getLogger().addHandler(CustomLogger.StreamHandler(Level.INFO, logger.getBase_path()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
