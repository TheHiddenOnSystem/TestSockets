package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.connections.menssaje.DefaultMessage;
import org.example.oldmodechat.connections.menssaje.ReferenceOptionsMessage;
import org.example.oldmodechat.connections.session.DefaultSession;
import org.example.oldmodechat.connections.session.ModelSession;
import org.example.oldmodechat.util.CustomLogger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.logging.Level;

/**
 * This has two options simple client, or handler to accept client
 */
public class ClientConnection<T extends ModelSession> {

    public static int Max_BufferRangeSum=1024;
    protected CustomLogger logger;
    protected final SocketChannel socket;
    protected T session;
    protected final FuntionInterfaceClientRead<T> funtionInterfaceClientRead;
    protected final FuntionInterfaceClientWrite<T> funtionInterfaceClientWrite;




    /**
     * To simple client
     * @param ip ip
     * @param port port
     * @param session
     * @param funtionInterfaceClientRead
     * @param funtionInterfaceClientWrite
     * @param funtionInterfaceClientWrite
     * @throws IOException
     */
    public ClientConnection(String ip, int port, T session, FuntionInterfaceClientRead<T> funtionInterfaceClientRead, FuntionInterfaceClientWrite<T> funtionInterfaceClientWrite) throws IOException {
        this.session = session;
        this.funtionInterfaceClientRead = funtionInterfaceClientRead;
        this.funtionInterfaceClientWrite = funtionInterfaceClientWrite;

        logger=customLogger();
        socket= SelectorProvider.provider().openSocketChannel();
        socket.connect(new InetSocketAddress(ip,port));
    }

    /**
     * Handler to accept client with server
     * @param socket
     * @param session
     * @param funtionInterfaceClientRead
     * @param funtionInterfaceClientWrite
     */
    public ClientConnection(SocketChannel socket, T session, FuntionInterfaceClientRead<T> funtionInterfaceClientRead, FuntionInterfaceClientWrite<T> funtionInterfaceClientWrite) {
        this.socket = socket;
        this.session = session;
        this.funtionInterfaceClientRead = funtionInterfaceClientRead;
        this.funtionInterfaceClientWrite = funtionInterfaceClientWrite;
        logger=customLogger();
    }

    public T getSession() {
        return session;
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


    public void read(){
        funtionInterfaceClientRead.read(session,socket);
    }

    public void write(ByteBuffer byteBuffer){
        byteBuffer.flip();
        funtionInterfaceClientWrite.write(socket,session,byteBuffer);
    }
    public void write(String msh){
        final ByteBuffer buffer=ByteBuffer.wrap(msh.getBytes(StandardCharsets.UTF_8));
        funtionInterfaceClientWrite.write(socket,session,buffer);
    }




    public SocketChannel getSocket() {
        return socket;
    }


}
