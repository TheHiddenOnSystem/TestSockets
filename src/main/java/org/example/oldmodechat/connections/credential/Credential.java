package org.example.oldmodechat.connections.credential;

public interface Credential<T,K> {
    /**
     * @return it is can "mary"
     */
    T getName();

    /**
     * @return it is can ip,token,etc..
     */
    K getIdentifiquer();
}
