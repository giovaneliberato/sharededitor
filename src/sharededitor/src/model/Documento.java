package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.Date;
import org.bson.types.BasicBSONList;

public class Documento {
    
    private String nome;
    private String owner;
    private String texto;
    private BasicBSONList compartilhados;
    private Date ultimaAlteracao;

    public BasicDBObject toJSON(){
        BasicDBObject json = new BasicDBObject();
        json.append("nome", nome);
        json.append("owner", owner);
        json.append("texto", texto);
        json.append("compartilhados", compartilhados);
        json.append("ultimaAlteracao", ultimaAlteracao);
        return json;
    }
    
    public static Documento toObj(DBObject json){
        Documento doc = new Documento();
        doc.setNome((String) json.get("nome"));
        doc.setOwner((String) json.get("owner"));
        doc.setTexto((String) json.get("texto"));
        doc.setCompartilhados((BasicBSONList) json.get("compartilhados"));
        doc.setUltimaAlteracao((Date) json.get("ultimaAlteracao"));
        
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

    public BasicBSONList getCompartilhados() {
        return compartilhados;
    }

    public void setCompartilhados(BasicBSONList compartilhados) {
        this.compartilhados = compartilhados;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
    
    
}
