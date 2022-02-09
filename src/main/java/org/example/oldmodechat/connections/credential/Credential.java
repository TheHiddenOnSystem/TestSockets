package org.example.oldmodechat.connections.credential;

/**
 *
 * @param <T>
 * @param <K>
 */
public interface Credential<T,K> {
    /**
     * @return it is can "mary"
     */
    T getName();

    /**
     * @return it is can ip,token,etc..
     */
    K getIdentifier();
}
