package org.example.oldmodechat.connections.sockets;

import java.nio.channels.ServerSocketChannel;

/**
 * This interface open options to make lamdba with road follow server
 */
@FunctionalInterface
public interface FuntionInterfaceServerSocketService {

    /**
     * @param serverSocketChannel road service
     */
    void serviceServerCustom(ServerSocketChannel serverSocketChannel);
}
