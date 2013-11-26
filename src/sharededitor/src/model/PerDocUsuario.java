package model;

public class PerDocUsuario {

    private int idDoc;
    private int idUsuario;
    private boolean perLeitura;
    private boolean perEditar;
    private boolean perExcluir;

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isPerLeitura() {
        return perLeitura;
    }

    public void setPerLeitura(boolean perLeitura) {
        this.perLeitura = perLeitura;
    }

    public boolean isPerEditar() {
        return perEditar;
    }

    public void setPerEditar(boolean perEditar) {
        this.perEditar = perEditar;
    }

    public boolean isPerExcluir() {
        return perExcluir;
    }

    public void setPerExcluir(boolean perExcluir) {
        this.perExcluir = perExcluir;
    }
    
    
    
}
