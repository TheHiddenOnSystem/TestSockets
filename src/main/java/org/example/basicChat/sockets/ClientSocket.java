package org.example.basicChat.sockets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSocket {

    private String host;
    private int port;


    public ClientSocket(String host,int port) throws IOException {
        this.host=host;
        this.port=port;
    }
    public void sendMessage(String msg) throws IOException{
        final Socket socket=new Socket(host,port);
        if(socket!=null){
            if(socket.isConnected()){

                    final OutputStreamWriter outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());
                    outputStreamWriter.write(msg);
                    outputStreamWriter.close();
                    socket.isClosed();

            }
        }
    }


}
