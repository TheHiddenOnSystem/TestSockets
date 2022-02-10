package org.example.oldmodechat.connections.session;

import org.example.oldmodechat.connections.credential.DefaultCredential;
import org.example.oldmodechat.connections.credential.ModelCredential;

import java.net.InetAddress;

public class DefaultSession extends ModelSession<DefaultCredential> {


    protected DefaultSession(DefaultCredential credential) {
        super(credential);
    }
}
