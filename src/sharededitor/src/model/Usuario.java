
package model;

import com.mongodb.BasicDBObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {
    
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;

    public BasicDBObject toJSON(){
        BasicDBObject json = new BasicDBObject();
        json.append("nome", this.getNome());
        json.append("login", this.getLogin());
        json.append("senha", this.getSenha());
        json.append("email", this.getEmail());
        
        return json;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            this.senha = senha;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static boolean validarUsuario(Usuario u) { //talvez nao precise
        return true;
    }
}
