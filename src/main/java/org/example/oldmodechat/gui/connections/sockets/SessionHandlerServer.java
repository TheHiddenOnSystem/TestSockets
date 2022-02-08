package org.example.oldmodechat.gui.connections.sockets;

import org.example.oldmodechat.gui.connections.sockets.ClientConnection;

import java.nio.channels.SocketChannel;

public class SessionHandlerServer extends ClientConnection {


    public SessionHandlerServer(SocketChannel socket, String nameFriend) {
        super(socket, nameFriend);
    }
}
