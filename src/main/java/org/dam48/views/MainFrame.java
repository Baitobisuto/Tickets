package org.dam48.views;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.dam48.controllers.MainFrameController.LOGIN;

public class MainFrame extends JFrame implements InterfaceView {
    private JPanel mainPanel;
    private JButton bt_navegador;

    public MainFrame() {
        initWindow();
        initComponents();
        setCommands();
    }

    @Override
    public void initWindow() {
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void showWindow() {
        setVisible(true);

    }

    @Override
    public void closeWindow() {
        dispose();

    }

    @Override
    public void setCommands() {
        bt_navegador.setActionCommand(LOGIN);

    }

    @Override
    public void addListener(ActionListener listener) {
        bt_navegador.addActionListener(listener);

    }

    @Override
    public void initComponents() {

    }
}
