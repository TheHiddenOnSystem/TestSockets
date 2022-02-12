package org.example.utils.connection.session;

import org.example.utils.connection.credential.Credential;
import org.example.utils.connection.credential.ModelCredential;
import org.example.utils.connection.menssaje.Message;
import org.example.utils.connection.menssaje.ModelMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Model Session to create other Sessions
 *
 */
public class ModelSession<T extends ModelCredential,K extends ModelMessage> implements RequiredSession<T,K>{

    protected final T credential;

    protected List<K> messages;

    /**
     *
     * @param credential require credential
     * @param messages can put null value
     */
    public ModelSession(T credential, List<K> messages)throws IllegalArgumentException {

        if(credential!=null){
            this.credential=credential;
        }else{
            throw new IllegalArgumentException("Require credential");
        }
        this.messages=makeMessageListIfIsNull(messages);
    }
    protected List<K> makeMessageListIfIsNull(List<K> list){
        return list!=null?messages:new ArrayList<>();
    }

    @Override
    public ModelCredential getCredential() {
        return credential;
    }

    @Override
    public List<K> getMessages() {
        return messages;
    }
}


