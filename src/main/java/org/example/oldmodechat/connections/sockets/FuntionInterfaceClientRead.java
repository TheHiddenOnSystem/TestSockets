package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.connections.menssaje.ModelMessage;
import org.example.oldmodechat.connections.session.ModelSession;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface FuntionInterfaceClientRead<T extends ModelSession> {
    void read(T t, SocketChannel socketChannel);
}
