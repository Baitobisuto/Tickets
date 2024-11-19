package org.dam48.views;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.dam48.utils.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog implements InterfaceView {

    private JPanel mainPanel;

    public LoginDialog() {
        initWindow();
        initComponents();
        setCommands();

    }

    @Override
    public void initWindow() {
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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

    }

    @Override
    public void addListener(ActionListener listener) {

    }

    @Override
    public void initComponents() {
        setContentPane(mainPanel);
        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);
        setSize(400, 500);
        Platform.runLater(() -> {

            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();

            webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
                if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                    String currentUrl = webEngine.getLocation();

                    if (currentUrl.contains("login_ok")) {
                        String cookies = (String) webEngine.executeScript("document.cookie");

                        getCookies(cookies);
                        showLoginSuccessMessage();
                    }
                }
            });

            // Carga la página de login en el WebView
            webEngine.load("http://127.0.0.1:5000/form_login");

            // Establece la escena del fxPanel con el WebView para mostrar la página cargada
            fxPanel.setScene(new Scene(webView));
        });
    }
    private void getCookies(String cookies) {
        // Imprime las cookies obtenidas para depuración
        System.out.println("Cookies: " + cookies);

        // Separa las cookies por el delimitador "; " (espacio y punto y coma) para obtener cada cookie individualmente
        String[] cookieArray = cookies.split("; ");

        // Inicializa la variable que almacenará el token
        String token = null;
        // Itera sobre el array de cookies para buscar la cookie que empieza con "token="
        for (String cookie : cookieArray) {
            if (cookie.startsWith("token=")) {
                // Si encuentra el "token", extrae el valor después del "="
                token = cookie.split("=")[1];
                break;  // Sale del bucle después de encontrar el token
            }
        }

        // Inicializa la variable que almacenará el login del usuario
        String userlogin = null;
        // Itera sobre el array de cookies para buscar la cookie que empieza con "userlogin="
        for (String cookie : cookieArray) {
            if (cookie.startsWith("userlogin=")) {
                // Si encuentra el "userlogin", extrae el valor después del "="
                userlogin = cookie.split("=")[1];
                break;  // Sale del bucle después de encontrar el userlogin
            }
        }
        // Establece el nombre de usuario en la sesión usando la clase Session
        Session.setUsername(userlogin);
        // Establece el token en la sesión usando la clase Session
        Session.setToken(token);
    }

    private void showLoginSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Login exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
}
