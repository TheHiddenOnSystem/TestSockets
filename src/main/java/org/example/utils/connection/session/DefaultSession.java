package org.example.utils.connection.session;

import org.example.utils.connection.credential.DefaultCredential;
import org.example.utils.connection.menssaje.Message;
import org.example.utils.connection.menssaje.ModelMessage;

import java.util.List;

public class DefaultSession extends ModelSession<DefaultCredential, ModelMessage<String>> {


    /**
     * @param credential require credential
     * @param messages   can put null value
     */
    protected DefaultSession(DefaultCredential credential, List<ModelMessage<String>> messages) throws IllegalArgumentException {
        super(credential, messages);
    }
}
