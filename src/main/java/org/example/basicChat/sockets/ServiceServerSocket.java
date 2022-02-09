package org.example.basicChat.sockets;

import org.example.basicChat.interfaceuser.PanelToReadChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CustomService ServerSocket
 */
public class ServiceServerSocket extends Thread{
    private final ServerSocket serverSocket;
    private final PanelToReadChat panelToReadChat;
    public ServiceServerSocket(ServerSocket serverSocket, PanelToReadChat panelToReadChat) throws IOException {
        this.serverSocket=serverSocket;
        this.panelToReadChat=panelToReadChat;
    }

    @Override
    public void run() {
        //super.run();
        System.out.println("Servidor conectado");
        while(!serverSocket.isClosed()){
            try {
                final Socket socket=serverSocket.accept();
                final BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while(reader.ready()){
                    panelToReadChat.getStringBuffer().append(reader.readLine()+"\n");
                }
                panelToReadChat.updateTextFieldWithBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
