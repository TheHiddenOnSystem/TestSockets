package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.util.CustomLogger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.logging.Level;

/**
 * This has two options simple client, or handler to accept client
 */
public class ClientConnection {

    public static int Max_BufferRangeSum=1024;
    protected String nameFriend;
    protected CustomLogger logger;
    protected final SocketChannel socket;
    protected ByteBuffer readerBuffer =ByteBuffer.allocate(Max_BufferRangeSum);
    protected ByteBuffer writerBuffer =ByteBuffer.allocate(Max_BufferRangeSum);



    /**
     * To simple client
     * @param nameFriend name
     * @param ip ip
     * @param port port
     * @throws IOException
     */
    public ClientConnection(String nameFriend, String ip, int port) throws IOException {
        logger=customLogger();
        socket= SelectorProvider.provider().openSocketChannel();
        socket.connect(new InetSocketAddress(ip,port));
        this.nameFriend=nameFriend;
    }

    /**
     * Handler to accept client with server
     * @param socket
     * @param nameFriend
     */
    public ClientConnection(SocketChannel socket,String nameFriend) {
        this.socket = socket;
        logger=customLogger();
    }

    /**
     *
     * @return Custom logger to class
     */
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


    /**
     * Typical ping with try connection
     * @param ip ip
     * @param port port
     * @return if can connection return true
     */
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

    /**
     * Read in buffer
     */
    public void read(){
        try {
                this.socket.read(readerBuffer);

        } catch (IOException e) {
            //e.printStackTrace();
            CustomLogger.Use_Log(logger.getLogger(),Level.INFO,"Error en byte buffer reader");
        }
    }

    /**
     * Write in buffer
     */
    public void write(){
        try {
            this.socket.write(writerBuffer);

        } catch (IOException e) {
            //e.printStackTrace();
            CustomLogger.Use_Log(logger.getLogger(),Level.INFO,"Error en byte buffer write");
        }
    }



    public String getNameFriend() {
        return nameFriend;
    }

    public SocketChannel getSocket() {
        return socket;
    }



    public ByteBuffer getReaderBuffer() {
        return readerBuffer;
    }

    public ByteBuffer getWriterBuffer() {
        return writerBuffer;
    }
}
