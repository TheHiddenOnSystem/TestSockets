package org.example.basicChat.sockets;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class HandlerToAcceptPettition extends Thread{
    private volatile Socket socket;
    private final ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor) Executors.newSingleThreadExecutor();
    private OutputStreamWriter outputStreamWriter=null;
    private InputStreamReader inputStreamReader=null;
    public HandlerToAcceptPettition(Socket socket){
        this.socket=socket;
    }

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

    @Override
    public void run() {

    }

    private boolean notNullSocket(){
        return (socket!=null);
    }
}
