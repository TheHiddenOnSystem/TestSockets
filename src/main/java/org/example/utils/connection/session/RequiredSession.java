package org.example.utils.connection.session;

import org.example.utils.connection.credential.Credential;
import org.example.utils.connection.credential.ModelCredential;
import org.example.utils.connection.menssaje.Message;
import org.example.utils.connection.menssaje.ModelMessage;

import java.util.List;

public interface RequiredSession<T extends ModelCredential,K extends ModelMessage> {

     ModelCredential getCredential();

    List<K> getMessages();
}
