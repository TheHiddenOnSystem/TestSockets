package org.example.utils.connection.menssaje;

import java.util.Date;

public class ModelMessage <T extends String> implements Message<T> {
    private Date date;
    private T message;
    private ReferenceOptionsMessage referenceOptionsMessage;

    public ModelMessage(Date date, T message, ReferenceOptionsMessage referenceOptionsMessage) {
        this.date = date;
        this.message = message;
        this.referenceOptionsMessage = referenceOptionsMessage;
    }
    public ModelMessage(){

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

    public void setMessage(T message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReferenceOptionsMessage(ReferenceOptionsMessage referenceOptionsMessage) {
        this.referenceOptionsMessage = referenceOptionsMessage;
    }
}
