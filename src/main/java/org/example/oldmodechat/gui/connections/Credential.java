package org.example.oldmodechat.gui.connections;

public interface Credential<T,K> {
    T getName();
    K getIdentifiquer();
}
