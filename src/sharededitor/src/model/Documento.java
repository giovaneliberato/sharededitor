package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;

public class Documento {
    
    private String nome;
    private String owner;
    private String texto;
    private ArrayList compartilhados = new ArrayList();
    private boolean editavel = true;

    public BasicDBObject toJSON(){
        BasicDBObject json = new BasicDBObject();
        json.append("nome", nome);
        json.append("owner", owner);
        json.append("texto", texto);
        json.append("compartilhados", compartilhados);
        json.append("editavel", editavel);
        return json;
    }
    
    public static Documento toObj(DBObject json){
        Documento doc = new Documento();
        doc.setNome((String) json.get("nome"));
        doc.setOwner((String) json.get("owner"));
        doc.setTexto((String) json.get("texto"));
        doc.setCompartilhados((ArrayList) json.get("compartilhados"));
        doc.setEditavel((boolean) json.get("editavel"));
        
        return doc;
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String text) {
        this.texto = text;
    }

    public ArrayList getCompartilhados() {
        return compartilhados;
    }

    public void setCompartilhados(ArrayList compartilhados) {
        this.compartilhados = compartilhados;
    }

    public void setEditavel(boolean editavel) {
        this.editavel = editavel;
    }
    
    public boolean isEditavel(){
        return editavel;
    }

    
    
}
