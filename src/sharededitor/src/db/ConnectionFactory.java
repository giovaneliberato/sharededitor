package db;

import com.mongodb.DB;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {
    public static DB create(){
        try {
            return new Mongo().getDB("paperless");
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
