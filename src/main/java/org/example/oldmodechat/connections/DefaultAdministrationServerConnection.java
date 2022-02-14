package org.example.oldmodechat.connections;

import org.example.oldmodechat.connections.credential.DefaultCredential;
import org.example.oldmodechat.connections.menssaje.DefaultMessage;
import org.example.oldmodechat.connections.menssaje.ReferenceOptionsMessage;
import org.example.oldmodechat.connections.session.DefaultSession;
import org.example.oldmodechat.connections.sockets.ClientConnection;
import org.example.oldmodechat.connections.sockets.FuntionInterfaceClientRead;
import org.example.oldmodechat.connections.sockets.FuntionInterfaceClientWrite;
import org.example.oldmodechat.connections.sockets.FuntionInterfaceServerSocketService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Calendar;

public class DefaultAdministrationServerConnection extends AdministrationServerConnections<DefaultSession> {
    public DefaultAdministrationServerConnection(int serverPort,FuntionInterfaceServerSocketService<DefaultSession> defaultSessionFuntionInterfaceServerSocketService) throws FileNotFoundException {
        super(serverPort,defaultSessionFuntionInterfaceServerSocketService);
    }



    public static DefaultAdministrationServerConnection make(int serverPort) throws FileNotFoundException {
        return new DefaultAdministrationServerConnection(serverPort,defaultSessionFuntionInterfaceServerSocketService());
    }
    public static FuntionInterfaceClientRead<DefaultSession> sessionFuntionInterfaceClientRead(){
        return (session,socket)->{
            try {
                ByteBuffer buffer=ByteBuffer.allocate(ClientConnection.Max_BufferRangeSum);
                int a=0;
                int count=1;
                /*
                En principio no requiero de voltear el buffer
                 */
                while((a=socket.read(buffer))==-1){
                    count++;
                    final byte[] bytes= buffer.array();
                    buffer=ByteBuffer.allocate(ClientConnection.Max_BufferRangeSum*count);
                    buffer.put(bytes);

                }
                /*
                Uso buffer flip para marcar el limite donde me he quedado escribiendo
                 */
                buffer.flip();

                session.getMessage().add(new DefaultMessage(
                        Calendar.getInstance().getTime(), new String(buffer.array()).substring(0,buffer.limit()),
                        ReferenceOptionsMessage.OTHER
                ));

            }catch (IOException e){
                e.printStackTrace();
            }
        };
    }
    public static FuntionInterfaceClientWrite<DefaultSession> defaultSessionFuntionInterfaceClientWrite(){
        return (socket,session,buffer)->{
            try {
                int a=socket.write(buffer);
                session.getMessage().add(new DefaultMessage(
                        Calendar.getInstance().getTime(),
                        new String(buffer.array()).substring(0,buffer.limit()),
                        ReferenceOptionsMessage.ME
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
    /**
     *
     * @return get function to execute server
     */
    private static FuntionInterfaceServerSocketService<DefaultSession> defaultSessionFuntionInterfaceServerSocketService(){
        return (serverSocketChannel,clientConnections)->{
            while(serverSocketChannel.isOpen()){
                try {
                    final SocketChannel client=serverSocketChannel.accept();
                    clientConnections.add(new ClientConnection<DefaultSession>(client,new DefaultSession(
                            new DefaultCredential(
                                    String.format("Client-%03d",clientConnections.size()),
                                    client.getRemoteAddress()
                            )
                    ), sessionFuntionInterfaceClientRead(), defaultSessionFuntionInterfaceClientWrite()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }
}
