package org.example.oldmodechat.gui.connections.sockets;

import org.example.oldmodechat.gui.connections.Session;
import org.example.oldmodechat.util.CustomLogger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;

public class ClientConnection {

    public static int Max_BufferRangeSum=1024;
    protected String nameFriend;
    protected CustomLogger logger;
    protected final SocketChannel socket;
    protected ByteBuffer readerBuffer =ByteBuffer.allocate(Max_BufferRangeSum);
    protected ByteBuffer writerBuffer =ByteBuffer.allocate(Max_BufferRangeSum);
    protected Session session=new Session();

    public ClientConnection(String nameFriend, String ip, String port) throws IOException {
        logger=customLogger();
        socket=new Socket(ip, Integer.parseInt(port)).getChannel();
        this.nameFriend=nameFriend;
    }

    public ClientConnection(SocketChannel socket,String nameFriend) {
        this.socket = socket;
        logger=customLogger();
    }

    private CustomLogger customLogger(){
        CustomLogger logger=null;
        try {
            logger=new CustomLogger("ClientConnection",this.getClass().getName());
            logger.getLogger().addHandler(CustomLogger.StreamHandler(Level.INFO,"clientConnection"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return logger;
    }



    public static boolean clientPingServer(String ip,String port){
        try {
            if(new Socket(ip, Integer.parseInt(port)).isConnected()){
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void read(){
        try {
                this.socket.read(readerBuffer);
                this.session.add(Session.OptionSession.OTHER,readerBuffer);
        } catch (IOException e) {
            //e.printStackTrace();
            CustomLogger.Use_Log(logger.getLogger(),Level.INFO,"Error en byte buffer reader");
        }
    }
    public void write(){
        try {
            this.socket.write(writerBuffer);
            this.session.add(Session.OptionSession.ME,writerBuffer);
        } catch (IOException e) {
            //e.printStackTrace();
            CustomLogger.Use_Log(logger.getLogger(),Level.INFO,"Error en byte buffer write");
        }
    }



    public String getNameFriend() {
        return nameFriend;
    }
}
