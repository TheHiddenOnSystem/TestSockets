package org.example.oldmodechat.connections.session;

import org.example.oldmodechat.connections.credential.Credential;
import org.example.oldmodechat.connections.credential.ModelCredential;
import org.example.oldmodechat.connections.menssaje.ModelMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Model Session to create other Sessions
 *
 */
public abstract class ModelSession<T extends ModelCredential,K extends ModelMessage> implements RequiredSession<T,K>{

    protected T credential;
    protected List<K> messages=new ArrayList<>();

    public ModelSession(T credential) {
        this.credential = credential;
    }

    @Override
    public T getCredential() {
        return credential;
    }

    @Override
    public List<K> getMessage() {
        return messages;
    }

    @Override
    public String toString() {
        return "ModelSession{" +
                "credential=" + credential +
                ", messages=" + messages +
                '}';
    }
}


