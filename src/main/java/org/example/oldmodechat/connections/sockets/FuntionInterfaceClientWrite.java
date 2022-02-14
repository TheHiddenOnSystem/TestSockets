package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.connections.menssaje.ModelMessage;
import org.example.oldmodechat.connections.session.ModelSession;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface FuntionInterfaceClientWrite<T extends ModelSession> {
    void write(SocketChannel socketChannel, T t, ByteBuffer byteBuffer);
}
