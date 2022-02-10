package org.example.oldmodechat.connections.credential;

import java.net.InetAddress;

/**
 * Default Credential
 *
 * sample create Credential
 *
 * example "paco",InetAddres(ip:...)
 *
 */
public class DefaultCredential extends ModelCredential<String, InetAddress> {
    public DefaultCredential(String name, InetAddress identifier) {
        super(name, identifier);
    }
}
