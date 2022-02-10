package org.example.oldmodechat.connections.menssaje;

import java.util.Date;

public abstract class ModelMessage <T> implements Message<T> {
    private Date date;
    private T message;
    private ReferenceOptionsMessage referenceOptionsMessage;

    protected ModelMessage(Date date, T message, ReferenceOptionsMessage referenceOptionsMessage) {
        this.date = date;
        this.message = message;
        this.referenceOptionsMessage = referenceOptionsMessage;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public T getMessage() {
        return message;
    }

    @Override
    public ReferenceOptionsMessage getReferenceOptionsMessage() {
        return referenceOptionsMessage;
    }
}
