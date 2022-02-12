package org.example.oldmodechat.run_tries;

import org.example.oldmodechat.connections.sockets.ClientConnection;
import org.example.oldmodechat.util.CustomLogger;
import org.example.utils.connection.credential.ModelCredential;
import org.example.utils.connection.menssaje.ModelMessage;
import org.example.utils.connection.menssaje.ReferenceOptionsMessage;
import org.example.utils.connection.session.ModelSession;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TryRunClient {
    public static void main(String[] args) {

         try {
             final SocketChannel socketChannel= SelectorProvider.provider().openSocketChannel();
             socketChannel.connect(new InetSocketAddress("localhost",6000));
            final ClientConnection<ModelCredential<String,InetAddress>,ModelMessage<String>> clientConnection=new ClientConnection<>(
                    socketChannel,
                    "server_prueba",
                    new ModelSession<>(new ModelCredential<>("server_prueba",InetAddress.getLocalHost()),
                            new ArrayList<>())
            );
            clientConnection.write(new ModelMessage<>(
                    Calendar.getInstance().getTime(),
                    "sad", ReferenceOptionsMessage.ME
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
