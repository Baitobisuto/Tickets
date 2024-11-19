package org.dam48.controllers;

import org.dam48.views.LoginDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialogController implements ActionListener {

    private final LoginDialog loginDialog;

    public LoginDialogController(LoginDialog loginDialog) {
        this.loginDialog = loginDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
