package org.example.oldmodechat.connections.credential;

import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * Default Credential
 *
 * sample create Credential
 *
 * example "paco",InetAddres(ip:...)
 *
 */
public class DefaultCredential extends ModelCredential<String, SocketAddress> {
    public DefaultCredential(String name, SocketAddress identifier) {
        super(name, identifier);
    }
}
