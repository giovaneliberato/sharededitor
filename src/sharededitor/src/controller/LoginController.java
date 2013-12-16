/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
                System.out.println("match!");
            } else {
                error = true;
            }
        } else {
            error = true;
        }
        
        if (error){
            loginFormError.setText("Login ou senha incorretos");
        } else {
            System.out.println("match!");
        }
    }
    
}
