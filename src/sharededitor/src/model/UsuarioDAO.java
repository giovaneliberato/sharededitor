
package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import db.ConnectionFactory;


public class UsuarioDAO {

    public static void salvarUsuario(Usuario u){
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("usuarios");
        coll.insert(u.toJSON());
    }

    public static Usuario buscaLogin(String login) {
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("usuarios");
        DBObject query = new BasicDBObject("login", login);
        DBCursor cursor = coll.find(query);
        if (cursor.hasNext()){
            Usuario u = Usuario.toObj(cursor.next());
            return u;
        }
        return null;
       
    }
    
}
