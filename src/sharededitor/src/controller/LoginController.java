/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Usuario;
import model.UsuarioDAO;

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
    public void cadastrarUsuario(){
        String nome = nomeField.getText();
        String login = loginField.getText();
        String senha = senhaField.getText();
        String email = emailField.getText();
        
        Usuario user = new Usuario();
        user.setNome(nome);
        user.setLogin(login);
        user.setSenha(senha);
        user.setEmail(email);
        
        UsuarioDAO.salvarUsuario(user);
    }
    
}
