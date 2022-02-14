package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.connections.session.ModelSession;

import java.nio.channels.ServerSocketChannel;
import java.util.List;

/**
 * This interface open options to make lamdba with road follow server
 */
@FunctionalInterface
public interface FuntionInterfaceServerSocketService<T extends ModelSession> {

    /**
     * @param serverSocketChannel road service
     */
    void serviceServerCustom(ServerSocketChannel serverSocketChannel, List<ClientConnection<T>> clientConnections );
}
