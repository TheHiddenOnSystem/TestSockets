package org.example.oldmodechat.run_tries;

import org.example.oldmodechat.connections.AdministrationServerConnections;
import org.example.oldmodechat.connections.DefaultAdministrationServerConnection;
import org.example.oldmodechat.connections.credential.DefaultCredential;
import org.example.oldmodechat.connections.session.DefaultSession;
import org.example.oldmodechat.connections.sockets.ClientConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.StandardCharsets;

public class TryRunClient {
    public static void main(String[] args) {

        p1();

    }

    static void p1(){
        try {
            final SocketChannel socketChannel=SelectorProvider.provider().openSocketChannel();
            socketChannel.connect(new InetSocketAddress("localhost",6000));
            ClientConnection<DefaultSession> clientConnection=new ClientConnection<DefaultSession>(socketChannel,
                    new DefaultSession(new DefaultCredential("server", socketChannel.getRemoteAddress())),
                    DefaultAdministrationServerConnection.sessionFuntionInterfaceClientRead(),
                    DefaultAdministrationServerConnection.defaultSessionFuntionInterfaceClientWrite()
            );

            while(true){
                clientConnection.write("asdasdas,kdjaksd");
                clientConnection.read();
                System.out.println(clientConnection.getSession());
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    static void p2(){
        try {
            final SocketChannel socketChannel= SelectorProvider.provider().openSocketChannel();
            socketChannel.connect(new InetSocketAddress("localhost",6000));
            final ByteBuffer buffer=ByteBuffer.allocate(100);
            buffer.put("aasdas".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            socketChannel.write(buffer);

            System.out.println("escritro");

            while(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
