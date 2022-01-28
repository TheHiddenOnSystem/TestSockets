package org.example.basicChat.interfaceuser;

import java.awt.*;

public class PanelToSendChat extends Panel{



    private final TextArea textArea=new TextArea();
    private final Button btnSend=makeBtnSend();
    public PanelToSendChat() {
        setLayout(new BorderLayout());
        add(textArea,BorderLayout.CENTER);

        add(btnSend,BorderLayout.EAST);


    }

    private Button makeBtnSend(){
        final Button button=new Button("Enviar");
        button.setBackground(Color.GRAY);

        return button;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Button getBtnSend() {
        return btnSend;
    }
}
