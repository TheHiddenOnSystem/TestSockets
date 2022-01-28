package org.example.oldmodechat;

import org.example.oldmodechat.client.ChannelClient;

import java.io.IOException;

public class RunChannelClient {
    public static void main(String[] args) {
        new ChannelClient("127.0.0.1",6000).start();

    }
}
