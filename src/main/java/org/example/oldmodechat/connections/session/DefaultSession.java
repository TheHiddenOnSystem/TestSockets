package org.example.oldmodechat.connections.session;

import org.example.oldmodechat.connections.credential.DefaultCredential;
import org.example.oldmodechat.connections.credential.ModelCredential;
import org.example.oldmodechat.connections.menssaje.DefaultMessage;

import java.net.InetAddress;
import java.util.List;

public class DefaultSession extends ModelSession<DefaultCredential, DefaultMessage> {

    public DefaultSession(DefaultCredential credential) {
        super(credential);
    }
}
