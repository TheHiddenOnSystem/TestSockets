package org.example.basicChat.sockets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Simple simulate connection socket to send one String
 */
public class ClientSocket {

    private String host;
    private int port;

    /**
     *
     * @param host ip
     * @param port port
     * @throws IOException
     */
    public ClientSocket(String host,int port) throws IOException {
        this.host=host;
        this.port=port;
    }

    /**
     * Send String to target
     * @param msg message
     * @throws IOException
     */
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
