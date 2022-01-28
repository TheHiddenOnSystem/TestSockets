package org.example.basicChat.interfaceuser;

import java.awt.*;

public class PanelToReadChat extends Panel {

    private final StringBuffer stringBuffer=new StringBuffer();
    private final TextArea textFieldRead=makeTexFieldRead();


    public PanelToReadChat() {
        config();
        configReads();
    }
    private void config(){
        setLayout(new BorderLayout());
    }
    private void configReads(){
        add(textFieldRead);
        //Utils.addobjects(textFieldRead,this,gridBagLayout,gridBagConstraints,1,0,5,1);
        //Utils.addobjects(scrollbarRead,this,gridBagLayout,gridBagConstraints,0,0,1,1);
    }
    private TextArea makeTexFieldRead(){
        TextArea textField=new TextArea();
        textField.setEditable(false);
        textField.setText("asd");
        return textField;
    }
    public void updateTextFieldWithBuffer(){
        textFieldRead.setText(stringBuffer.toString());
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public TextArea getTextFieldRead() {
        return textFieldRead;
    }
}
