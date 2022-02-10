package org.example.oldmodechat.run_tries;

import org.example.oldmodechat.connections.AdministrationServerConnections;
import org.example.oldmodechat.connections.sockets.ClientConnection;

import java.io.FileNotFoundException;
import java.util.List;

public class TryRunServer {

    public static void main(String[] args) {
        try {
            final AdministrationServerConnections administrationServerConnections=new AdministrationServerConnections(6000);
            List<ClientConnection> list=administrationServerConnections.getServiceServerConnection().getClientConnections();
            System.out.println(list.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
