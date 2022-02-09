package org.example.oldmodechat.gui.connections;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class BasicCredential {
    private String name;
    private InetSocketAddress inetSocketAddress;

    public BasicCredential(String name, InetSocketAddress inetSocketAddress) {
        this.name = name;
        this.inetSocketAddress = inetSocketAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public void setInetSocketAddress(InetSocketAddress inetSocketAddress) {
        this.inetSocketAddress = inetSocketAddress;
    }
}
