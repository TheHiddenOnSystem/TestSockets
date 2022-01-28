package org.example.oldmodechat;

import org.example.oldmodechat.server.ServerSocketClient;

public class RunServerGui {
    public static void main(String[] args) {
        new ServerSocketClient().start();
    }
}
