package org.example.oldmodechat.gui;

import java.awt.*;

public class PanelSendChat extends Panel {

    private final TextField textField =makeTextFieldToRead();
    private final Button buttonSend=makeButtonSend();


    public PanelSendChat() {
        setLayout(new BorderLayout());
        add(textField,BorderLayout.CENTER);
        add(buttonSend,BorderLayout.EAST);

    }

    private TextField makeTextFieldToRead(){
        final TextField textField=new TextField();
        textField.setBackground(MainFrame.COLOR_BGCOMPONENT);
        textField.setFont(new Font(Font.SANS_SERIF,Font.BOLD,28));
        textField.setForeground(MainFrame.COLOR_FONT);

        return textField;
    }
    private Button makeButtonSend(){
        final Button button=new Button("Enviar");
        button.setBackground(MainFrame.COLOR_BGCOMPONENT);
        button.setForeground(MainFrame.COLOR_FONT);
        button.setMinimumSize(new Dimension(50,100));
        button.setMaximumSize(new Dimension(300,button.getHeight()));

        return button;
    }

    public TextField getTextField() {
        return textField;
    }


}
