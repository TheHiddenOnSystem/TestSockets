package org.example.oldmodechat;

import org.example.oldmodechat.connections.AdministrationServerConnections;

import java.io.FileNotFoundException;

public class RunMainFrame {
    public static void main(String[] args) {
        //new MainFrame();
        AdministrationServerConnections administrationServerConnections=null;
        try {
            administrationServerConnections=new AdministrationServerConnections(6000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
