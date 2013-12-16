
package model;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import db.ConnectionFactory;


public class UsuarioDAO {

    public static void salvarUsuario(Usuario u){
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("usuarios");
        coll.insert(u.toJSON());
    }
    
}
