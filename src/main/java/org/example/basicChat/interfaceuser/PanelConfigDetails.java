package org.example.basicChat.interfaceuser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * This panel have all data config to get info to navigate
 */
public class PanelConfigDetails extends Panel{
    private static final String TITLE_CONNECT="Connected";
    private static final String TITLE_DESCONNECT="Desconnect";
    private Label lblconnected=new Label(TITLE_DESCONNECT);
    private TextField textFieldToIp=textField();
    private TextField textFieldToPort=textField();
    private Button button=new Button("Connectar");

    public PanelConfigDetails() {
        button.addActionListener(actionListenerButtonConnect());
        configPanel();
    }
    private void configPanel(){
        setBackground(Color.darkGray);
        setLayout(new GridLayout(1,5));
        add(lblconnected);
        add(new Label("  IP: "));
        add(textFieldToIp);
        add(new Label("  PORT: "));
        add(textFieldToPort);
        add(button);
        notConnected();
    }

    /**
     *
     * @return TextField with custom options
     */
    private TextField textField(){
        final TextField textField=new TextField();
        return textField;
    }

    /**
     *
     * @return Action to button, with this action you can send one ping to get state server, and change color in a label
     */
    private ActionListener actionListenerButtonConnect(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("buttonCOnnectEvento");
                final InetSocketAddress a=InetSocketAddress.createUnresolved(textFieldToIp.getText(),Integer.valueOf(textFieldToPort.getText()));
                Socket ping=null;
                try {
                    ping=new Socket(textFieldToIp.getText(),Integer.valueOf(textFieldToPort.getText()));

                    if(ping.isConnected()){
                        lblconnected.setBackground(Color.GREEN);
                        lblconnected.setText(TITLE_CONNECT);
                    }
                } catch (IOException ex) {
                    lblconnected.setBackground(Color.RED);
                    lblconnected.setText(TITLE_DESCONNECT);
                }finally {
                    if(ping!=null){
                        try {
                            ping.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        };
    }

    /**
     * This other object can call to change background color at Green in a label
     */
    public void isConnected(){
        lblconnected.setBackground(Color.GREEN);
    }
    /**
     * This other object can call to change background color at Red in a label
     */
    public void notConnected(){
        lblconnected.setBackground(Color.RED);
    }

    public TextField getTextFieldToIp() {
        return textFieldToIp;
    }

    public TextField getTextFieldToPort() {
        return textFieldToPort;
    }
}
