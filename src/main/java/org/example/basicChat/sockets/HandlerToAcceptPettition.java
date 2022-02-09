package org.example.basicChat.sockets;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This is one option get and use connection with client,
 * server.accept()
 * {@link java.net.ServerSocket}
 * This is an example to handler petitions
 */
class HandlerToAcceptPettition extends Thread{
    private volatile Socket socket;
    private final ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor) Executors.newSingleThreadExecutor();
    private OutputStreamWriter outputStreamWriter=null;
    private InputStreamReader inputStreamReader=null;
    public HandlerToAcceptPettition(Socket socket){
        this.socket=socket;
    }

    /**
     * Send Mensaje
     * @param s
     */
    public void sendMensaje(String s){
        if(notNullSocket()&&socket.isConnected()){

            try {
                outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(outputStreamWriter!=null){
                        outputStreamWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Thread
     */
    @Override
    public void run() {

    }

    private boolean notNullSocket(){
        return (socket!=null);
    }
}
