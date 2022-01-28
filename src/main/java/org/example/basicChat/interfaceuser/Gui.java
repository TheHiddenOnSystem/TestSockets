package org.example.basicChat.interfaceuser;

import org.example.basicChat.sockets.ClientSocket;
import org.example.basicChat.sockets.ServiceServerSocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;

public class Gui extends JFrame {
    public static int HEIGHT=700;
    public static int WITHD=700;
    public static final int PORT_SERVER=6000;

    private final PanelConfigDetails configDetails=new PanelConfigDetails();
    private final PanelToReadChat panelToRead=new PanelToReadChat();
    private final PanelToSendChat panelToSendChat=new PanelToSendChat();
    private ServiceServerSocket serviceServerSocket;

    public Gui() throws HeadlessException {
        configFrame();
        secondaryConfig();
        try {
            serviceServerSocket=new ServiceServerSocket(new ServerSocket(PORT_SERVER),panelToRead);
            serviceServerSocket.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configFrame(){
        setSize(WITHD,HEIGHT);
        setVisible(true);
        addWindowListener(windowAdapter());
        setLayout(new BorderLayout());
        //addobjectsToGrid(configDetails,0,0,1,1);
        //gridBagConstraints.fill=GridBagConstraints.BOTH;
        //addobjectsToGrid(panelToRead,0,1,1,1);

        add(configDetails,BorderLayout.NORTH);
        add(panelToRead,BorderLayout.CENTER);
        add(panelToSendChat,BorderLayout.SOUTH);
    }
    private void secondaryConfig(){
        panelToSendChat.getBtnSend().addActionListener(actionListenerToBtnSend());
    }
    private WindowAdapter windowAdapter(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        };
    }

    private ActionListener actionListenerToBtnSend(){
        return (l)->{
            try {
                ClientSocket clientSocket=new ClientSocket(configDetails.getTextFieldToIp().getText(),Integer.valueOf(configDetails.getTextFieldToPort().getText()));
                clientSocket.sendMessage(panelToSendChat.getTextArea().getText());
                panelToSendChat.getTextArea().setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }

        };
    }
    /*
    private final GridBagLayout gridBagLayout=new GridBagLayout();
    private final GridBagConstraints gridBagConstraints=new GridBagConstraints();

    public void addobjectsToGrid(Component componente, int gridx, int gridy, int gridwidth, int gridheigth){
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheigth;

        gridBagLayout.setConstraints(componente, gridBagConstraints);
        add(componente);
    }
    */

}
