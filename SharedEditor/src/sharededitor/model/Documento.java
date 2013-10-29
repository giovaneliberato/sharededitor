package sharededitor.model;

import java.sql.Date;

public class Documento {
    
    private String nome;
    private int id;
    private Date dataCri;
    private int idUserDoc;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCri() {
        return dataCri;
    }

    public void setDataCri(Date dataCri) {
        this.dataCri = dataCri;
    }

    public int getIdUserDoc() {
        return idUserDoc;
    }

    public void setIdUserDoc(int idUserDoc) {
        this.idUserDoc = idUserDoc;
    }  
    
}
