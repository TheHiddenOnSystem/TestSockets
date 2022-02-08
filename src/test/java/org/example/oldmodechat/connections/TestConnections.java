package org.example.oldmodechat.connections;

import org.example.oldmodechat.gui.connections.AdministrationServerConnections;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TestConnections {

    @Test
    public void upServer() throws FileNotFoundException {
        final AdministrationServerConnections administrationServerConnections=new AdministrationServerConnections(6000);

    }

}
