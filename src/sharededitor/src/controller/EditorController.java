package controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import model.Documento;
import model.DocumentoDAO;
import model.Usuario;



public class EditorController implements Initializable{
    @FXML
    private TreeView<String> filesTree;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private TextField nome;
    
    @FXML
    private HTMLEditor editor;
    
    @FXML
    public void salvarDocumento(){
        Documento novoDoc = new Documento();
        novoDoc.setNome(nome.getText());
        novoDoc.setTexto(editor.getHtmlText());
        novoDoc.setOwner(LoggedUser.getInstance().getLoggedUser().getLogin());
        novoDoc.setUltimaAlteracao(new Date());
        
        DocumentoDAO.salvarDocumento(novoDoc);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TreeItem<String> root = new TreeItem<>("root");
        TreeItem<String> meusArquivos = new TreeItem<>("Meus arquivos");        
        TreeItem<String> compartilhados = new TreeItem<>("Compartilhados comigo");
        
        Usuario logged = LoggedUser.getInstance().getLoggedUser();
        popularTreeItem(meusArquivos, DocumentoDAO.buscarTodosPorUsuario(logged.getLogin()));

        
        root.getChildren().add(meusArquivos);
        root.getChildren().add(compartilhados);
        root.setExpanded(true);
        
        filesTree.setRoot(root);
        filesTree.setShowRoot(false);        
    }
    
    private void popularTreeItem(TreeItem t, List<Documento> documentos){
        for(Documento d: documentos){
            t.getChildren().add(new TreeItem<>(d.getNome()));
        }
    }
}
