package org.example.oldmodechat.connections.session;

import org.example.oldmodechat.connections.credential.Credential;
import org.example.oldmodechat.connections.credential.ModelCredential;

/**
 * Model Session to create other Sessions
 *
 */
public abstract class ModelSession<T extends ModelCredential> implements RequiredSession{

    protected final T credential;

    protected ModelSession(T credential) {
        this.credential=credential;
    }


    @Override
    public Credential getCredential() {
        return credential;
    }
}


