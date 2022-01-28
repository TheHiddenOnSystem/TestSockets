package org.example.oldmodechat.gui;

import java.awt.*;

public class PanelReadChat extends Panel {

    //private final TextField textField=makeTextFieldToRead();
    private final TextArea textArea=makeTextArea();

    public PanelReadChat() {
        setLayout(new BorderLayout());
        add(textArea,BorderLayout.CENTER);
        textArea.setText("asdasdasd");
    }


    private TextArea makeTextArea(){
        final TextArea textArea=new TextArea();
        textArea.setBackground(MainFrame.COLOR_BACKGROUND);
        textArea.setForeground(MainFrame.COLOR_FONT);
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        return textArea;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
