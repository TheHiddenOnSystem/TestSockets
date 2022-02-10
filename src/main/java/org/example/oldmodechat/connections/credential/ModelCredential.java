package org.example.oldmodechat.connections.credential;

/**
 * Model Credential to create other Credential
 * @param <T> is name
 * @param <K> is identifiquer
 */
public abstract class ModelCredential<T,K> implements Credential<T,K>{

    T name;
    final K identifier;

    protected ModelCredential(T name, K identifier) {
        this.name = name;
        this.identifier = identifier;
    }

    public void setName(T name) {
        this.name = name;
    }
    @Override
    public T getName() {
        return name;
    }

    @Override
    public K getIdentifier() {
        return identifier;
    }
}
