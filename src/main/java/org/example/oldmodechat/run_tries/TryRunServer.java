package org.example.oldmodechat.run_tries;

import org.example.oldmodechat.connections.AdministrationServerConnections;
import org.example.oldmodechat.connections.sockets.ClientConnection;
import org.example.utils.connection.credential.ModelCredential;
import org.example.utils.connection.menssaje.ModelMessage;
import org.example.utils.connection.session.ModelSession;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.util.List;

public class TryRunServer {

    public static void main(String[] args) {
        try {
            final AdministrationServerConnections<ModelCredential<String,InetAddress>,ModelMessage<String>> administrationServerConnections=new AdministrationServerConnections(6000);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
