package org.example.oldmodechat.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class MainFrame extends Frame{

    public static final Color COLOR_FONT=Color.gray;
    public static final Color COLOR_BACKGROUND=Color.ORANGE;
    public static final Color COLOR_BGCOMPONENT=Color.pink;

    private final PanelSendChat panelSendChat=new PanelSendChat();
    private final PanelReadChat panelReadChat=new PanelReadChat();

    public MainFrame() {

        configFrame();
        add(panelSendChat,BorderLayout.SOUTH);
        add(panelReadChat,BorderLayout.CENTER);

    }
    private void configFrame(){
        setVisible(true);
        setBackground(COLOR_BACKGROUND);
        setSize(800,500);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                System.exit(0);
            }
        });
        setMenuBar(makeMenuBar());


    };











    private MenuBar makeMenuBar(){
        final MenuBar menuBar=new MenuBar();
        MenuItem[] menuItems=new MenuItem[]{
                makeMenuItem("start")
        };
        Menu menu1=makeMenu("Menu1",menuItems);
        menuBar.add(menu1);
        return menuBar;
    }
    private Menu makeMenu(String lbl,MenuItem[] menuItems){
        final Menu menu=new Menu(lbl);
        Arrays.stream(menuItems).forEach(
                menuItem -> {
                    menu.add(menuItem);
                }
        );
        return menu;
    }
    private MenuItem makeMenuItem(String lbl){
        final MenuItem menuItem=new MenuItem(lbl);

        return menuItem;
    }
}
