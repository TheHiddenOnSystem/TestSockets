package org.example.oldmodechat.connections.menssaje;

import java.util.Date;

public class DefaultMessage extends ModelMessage<String>{
    public DefaultMessage(Date date, String message, ReferenceOptionsMessage referenceOptionsMessage) {
        super(date, message, referenceOptionsMessage);
    }
}
