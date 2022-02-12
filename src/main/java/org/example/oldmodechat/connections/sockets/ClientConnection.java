package org.example.oldmodechat.connections.sockets;

import org.example.oldmodechat.util.CustomLogger;
import org.example.utils.connection.credential.ModelCredential;
import org.example.utils.connection.menssaje.ModelMessage;
import org.example.utils.connection.menssaje.ReferenceOptionsMessage;
import org.example.utils.connection.session.ModelSession;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Calendar;
import java.util.logging.Level;

/**
 * This has two options simple client, or handler to accept client,
 *
 *
 *
 * Require Correction generic development in this class,
 * if you write with other object can have a error
 *
 * @param <T> Is ModelSession
 * @param <A> Is Type ModelMessage in ModelSession
 * A solution to up error description
 */
public class ClientConnection<T extends ModelCredential,A extends ModelMessage> {
    /**
     * This class define a conditional option to two model,
     * you can Use Buffers
     */
    public enum OptionBuffers{
        ON,OFF
    }

    /**
     * you can change size option to make buffer
     */
    public static int MAX_MAKE_BUFFERS=1024;
    protected String nameFriend;
    protected CustomLogger logger;
    protected final SocketChannel socket;
    protected final ModelSession<T,A> session;



    /**
     * To simple client
     * @param nameFriend name
     * @param ip ip
     * @param port port

     * @throws IOException
     */
    public ClientConnection(String nameFriend, String ip, int port, ModelSession<T,A> session) throws IOException {

        logger=customLogger();
        socket= SelectorProvider.provider().openSocketChannel();
        socket.connect(new InetSocketAddress(ip,port));
        this.nameFriend=nameFriend;
        this.session=session;
    }

    /**
     * Handler to accept client with server and other use
     * @param socket
     * @param nameFriend

     */
    public ClientConnection(SocketChannel socket, String nameFriend, ModelSession<T,A> session) {
        this.socket = socket;
        logger=customLogger();
        this.session=session;
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
     *
     */
    public void read(A msg){
        try {
            final ByteBuffer buffer=ByteBuffer.allocate(MAX_MAKE_BUFFERS);
            this.socket.read(buffer);
            msg.setDate(Calendar.getInstance().getTime());
            msg.setMessage(nameFriend);
            msg.setReferenceOptionsMessage(ReferenceOptionsMessage.OTHER);
            session.getMessages().add(msg);

        } catch (IOException e) {
            //e.printStackTrace();
            CustomLogger.Use_Log(logger.getLogger(),Level.INFO,"Error en byte buffer reader");
        }
    }

    /**
     * Write in buffer
     *
     *
     * can have error in documentation this class, require revision
     * @param msg
     */
    public void write(A msg){
        try {
            final ByteBuffer buffer=ByteBuffer.allocate(1);
            buffer.put(Byte.parseByte(msg.getMessage().toString()));
            this.socket.write(buffer);
            session.getMessages().add(msg);

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


    public ModelSession<T, A> getSession() {
        return session;
    }
}
