package org.example.oldmodechat.gui.connections;

import java.nio.channels.ServerSocketChannel;

@FunctionalInterface
public interface FuntionInterfaceServerSocketService {

    void serviceServerCustom(ServerSocketChannel serverSocketChannel);
}
