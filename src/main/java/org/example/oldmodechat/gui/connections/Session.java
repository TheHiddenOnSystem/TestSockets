package org.example.oldmodechat.gui.connections;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Session {
    public enum OptionSession{
        ME,OTHER
    }

    protected Map<OptionSession,String> menssages=new HashMap<>();

    public void add(OptionSession session, ByteBuffer byteBuffer){
        getMenssages().put(session, byteBuffer.toString());
    }

    public synchronized Map<OptionSession, String> getMenssages() {
        return menssages;
    }
}


