package org.example.oldmodechat.connections.session;

import org.example.oldmodechat.connections.credential.Credential;
import org.example.oldmodechat.connections.credential.ModelCredential;
import org.example.oldmodechat.connections.menssaje.ModelMessage;

import java.util.List;

public interface RequiredSession<T extends ModelCredential,K extends ModelMessage> {

    T getCredential();
    List<K> getMessage();
}
