package org.example;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static void addobjects(Component componente, Container yourcontainer, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheigth){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheigth;
        layout.setConstraints(componente, gbc);
        yourcontainer.add(componente);
    }


}
