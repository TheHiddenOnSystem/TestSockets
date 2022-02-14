package org.example.oldmodechat.run_tries;

import org.example.oldmodechat.connections.AdministrationServerConnections;
import org.example.oldmodechat.connections.DefaultAdministrationServerConnection;
import org.example.oldmodechat.connections.menssaje.DefaultMessage;
import org.example.oldmodechat.connections.session.DefaultSession;
import org.example.oldmodechat.connections.sockets.ClientConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;

public class TryRunServer {

    public static void main(String[] args) {
        p1();
    }

    static void p1(){
        try {
            final DefaultAdministrationServerConnection administrationServerConnections=DefaultAdministrationServerConnection.make(6000);
            while(true){
                for(ClientConnection<DefaultSession> a:administrationServerConnections.getServiceServerConnection().getClientConnections()){
                    a.write("server respuesta blablablabla");
                    a.read();
                    System.out.println(a.getSession());
                }
                Thread.sleep(2000);
            }
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void p2(){
        try {
            final ServerSocketChannel serverSocketChannel= SelectorProvider.provider().openServerSocketChannel();
            serverSocketChannel.bind(new InetSocketAddress(6000));

            final SocketChannel socketChannel=serverSocketChannel.accept();

            ByteBuffer buffer=ByteBuffer.allocate(10);
            int a=socketChannel.read(buffer);

            System.out.println(a);
            System.out.println(new String(buffer.array(), StandardCharsets.UTF_8).substring(0,a));

            while(true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
