/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Usuario;
import model.UsuarioDAO;
import utils.Security;

public class LoginController {
    @FXML
    private TextField nomeField;
    
    @FXML
    private TextField loginField;
    
    @FXML
    private PasswordField senhaField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField loginFormField;
    
    @FXML
    private PasswordField senhaFormField;
    
    @FXML
    private Label loginError;
    
    @FXML
    private Label senhaError;
    
    @FXML
    private Label emailError;
    
    @FXML
    private Label loginFormError;
    
    @FXML
    private AnchorPane rootPane;
    
    
    
    @FXML
    public void cadastrarUsuario(){
        String nome = nomeField.getText();
        String login = loginField.getText();
        String senha = senhaField.getText();
        String email = emailField.getText();
        boolean error = false;
        
        if(UsuarioDAO.buscaLogin(login) != null){
            error = true;
            loginError.setText("Login ja existe");
        } else {
            loginError.setText("");
        }
        
        if(senha.length() < 6){
            error = true;
            senhaError.setText("Mínimo 6");
        } else {
            senhaError.setText("");
        }
        
        if(email.indexOf('@') == -1){
            error = true;
            emailError.setText("Email inválido");
        } else{
            emailError.setText("");
        }
        
        
        if(!error){
            Usuario user = new Usuario();
            user.setNome(nome);
            user.setLogin(login);
            user.setSenha(senha);
            user.setEmail(email);
            UsuarioDAO.salvarUsuario(user);
            
            try {
                Parent editor = FXMLLoader.load(getClass().getResource("../view/Editor.fxml"));
                rootPane.getChildren().setAll(editor);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @FXML
    public void logar(){
        String login = loginFormField.getText();
        String senha = senhaFormField.getText();
        boolean error = false;
        
        Usuario user = UsuarioDAO.buscaLogin(login);
        if(user != null){
            if( user.getSenha().equals(senha)){
            } else {
                error = true;
            }
        } else {
            error = true;
        }
        
        if (error){
            loginFormError.setText("Login ou senha incorretos");
        } else {
            System.out.println("logou");
            try {
                Parent editor = FXMLLoader.load(getClass().getResource("../view/Editor.fxml"));
                rootPane.getChildren().setAll(editor);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
