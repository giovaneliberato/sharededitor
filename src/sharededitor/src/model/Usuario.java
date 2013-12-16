
package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Security;

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
    
     public static Usuario toObj(DBObject json) {
         Usuario u = new Usuario();
         u.setNome((String) json.get("nome"));
         u.setLogin((String) json.get("login"));
         u.setSenha((String) json.get("senha"));
         u.setEmail((String) json.get("email"));
         
         return u;
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
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
