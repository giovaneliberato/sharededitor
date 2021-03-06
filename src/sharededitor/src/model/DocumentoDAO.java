/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import controller.LoggedUser;
import db.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giovane
 */
public class DocumentoDAO {
    
    public static void salvarDocumento(Documento d){
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        coll.insert(d.toJSON());
    }
    
    public static List<Documento> buscarPorUsuario(String login){
        List<Documento> documentos = new ArrayList<>();
        
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        DBObject query = new BasicDBObject("owner", login);
        DBCursor resultado = coll.find(query);
        
        while(resultado.hasNext()){
            documentos.add(Documento.toObj(resultado.next()));
        }
        return documentos;
        
    }

    public static Documento buscarPorNome(String nomeTexto) {
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        DBObject query = new BasicDBObject("nome", nomeTexto);
        DBCursor resultado = coll.find(query);
        
        if (resultado.hasNext()){
            return Documento.toObj(resultado.next());
        }
        
        return null;
    }
    
    public static List<Documento> buscarTodos(){
        List<Documento> achados = new ArrayList<>();
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        DBCursor resultado = coll.find();
        
        while (resultado.hasNext()){
            achados.add(Documento.toObj(resultado.next()));
        }
        
        return achados;
    }

    public static void removerPorNome(String nomeTexto) {
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        DBObject query = new BasicDBObject("nome", nomeTexto);
        DBObject resultado = coll.findOne(query);
        coll.remove(resultado);
    }

    public static List<Documento> buscarCompartilhadosPorUsuario(String login) {
        List<Documento> documentos = new ArrayList<>();
        
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        DBObject query = new BasicDBObject("compartilhados.nome", login);
        DBCursor resultado = coll.find(query);
        
        while(resultado.hasNext()){
            documentos.add(Documento.toObj(resultado.next()));
        }
        
        return documentos;
    }
    
    
    public static Documento buscarCompartilhadoPorNome(String nome, String login){
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        DBObject query = new BasicDBObject("compartilhados.nome", login).append("nome", nome);
        DBCursor resultado = coll.find(query);
        
        return Documento.toObj(resultado.next());
        
    }
    
    public static void atualizarDocumento(Documento d){
        Documento old = buscarPorNome(d.getNome());
        
        DB db = ConnectionFactory.create();
        DBCollection coll = db.getCollection("documentos");
        coll.update(old.toJSON(), d.toJSON());
    }
    
    
}
