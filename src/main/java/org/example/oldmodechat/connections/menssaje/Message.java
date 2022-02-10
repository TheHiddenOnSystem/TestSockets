package org.example.oldmodechat.connections.menssaje;

import java.util.Date;

public interface Message<T> {
    Date getDate();
    T getMessage();
    ReferenceOptionsMessage getReferenceOptionsMessage();
}
