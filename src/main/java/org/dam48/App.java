package org.dam48;

import com.formdev.flatlaf.FlatLightLaf;
import org.dam48.controllers.LoginDialogController;
import org.dam48.controllers.MainFrameController;
import org.dam48.views.LoginDialog;
import org.dam48.views.MainFrame;


public class App 
{
    public static void main( String[] args )
    {
        FlatLightLaf.setup();
        LoginDialog login = new LoginDialog();
        login.showWindow();

        MainFrame mainFrame = new MainFrame();
        LoginDialog loginDialog = new LoginDialog();

        MainFrameController mainController= new MainFrameController(mainFrame,loginDialog);
        LoginDialogController loginDialogController = new LoginDialogController(loginDialog);

        mainFrame.addListener(mainController);
        loginDialog.addListener(loginDialogController);
        mainFrame.showWindow();

    }
}
