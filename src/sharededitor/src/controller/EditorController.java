package controller;

import com.mongodb.BasicDBObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
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
    private TitledPane compartilharPopUp;
    
    @FXML
    private TextField compartilharLogin;
    
    @FXML
    private ComboBox compartilharPerm;
    
    @FXML
    private TitledPane erroPopUp;
    
    @FXML
    public void salvarDocumento(){
        String nomeTexto = nome.getText();
        Documento doc = DocumentoDAO.buscarPorNome(nomeTexto);
        
        if (doc != null){   
            int i = 1;
            StringBuilder novoNome;
            do{
                novoNome = new StringBuilder(nomeTexto);
                novoNome.append(" (").append(i).append(")");
                i += 1;
            } while(DocumentoDAO.buscarPorNome(novoNome.toString()) != null);
            nomeTexto = novoNome.toString();
        } else {
            doc = new Documento();
        }
        
        doc.setNome(nomeTexto);
        doc.setOwner(LoggedUser.getInstance().getLoggedUser().getLogin());
        doc.setTexto(editor.getHtmlText());
        
        DocumentoDAO.salvarDocumento(doc);
        inicializarTreeView();
    }
    
    @FXML
    public void criarNovoDocumento(){
        nome.setText("");
        editor.setHtmlText("");
    }
    
    @FXML
    public void excluirDocumento(){
        String nometexto = nome.getText();
        DocumentoDAO.removerPorNome(nometexto);
        inicializarTreeView();
    }
    
    @FXML
    public void compartilharDocumento(){
        compartilharPopUp.setVisible(true);
    }
    
    @FXML
    public void cancelarPopUp(){
        compartilharPopUp.setVisible(false);
        erroPopUp.setVisible(false);
    }
    
    
    @FXML
    public void confirmarCompartilhar(){
        String doc = nome.getText();
        Documento d = DocumentoDAO.buscarPorNome(doc);
        
        String usuarioLogin = compartilharLogin.getText();
        String perm = (String) compartilharPerm.getValue();
        
        ArrayList compartilhados = d.getCompartilhados();
        BasicDBObject toSave = new BasicDBObject("nome", usuarioLogin);
        toSave.append("perm", perm);
        compartilhados.add(toSave);
        d.setCompartilhados(compartilhados);
        
        DocumentoDAO.salvarDocumento(d);
        compartilharPopUp.setVisible(false);
    }
    
    
    @FXML
    public void deslogar(){
        LoggedUser.getInstance().setLoggedUser(null);
        try {
            Parent login = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            rootPane.getChildren().setAll(login);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTreeView();
        compartilharPopUp.setVisible(false);
        erroPopUp.setVisible(false);
    }
    
    
    private void inicializarTreeView(){
        TreeItem<String> root = new TreeItem<>("root");
        TreeItem<String> meusArquivos = new TreeItem<>("Meus arquivos");        
        TreeItem<String> compartilhados = new TreeItem<>("Compartilhados comigo");
        
        Usuario logged = LoggedUser.getInstance().getLoggedUser();
        popularTreeItem(meusArquivos, DocumentoDAO.buscarPorUsuario(logged.getLogin()));
        meusArquivos.setExpanded(true);
        
        popularTreeItem(compartilhados, DocumentoDAO.buscarCompartilhadosPorUsuario(logged.getLogin()));
        
        root.getChildren().add(meusArquivos);
        root.getChildren().add(compartilhados);
        root.setExpanded(true);
        
        filesTree.setRoot(root);
        filesTree.setShowRoot(false);


        filesTree.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){            
                if(mouseEvent.getClickCount() == 2){
                    TreeItem<String> item = filesTree.getSelectionModel().getSelectedItem();
                    Documento d = DocumentoDAO.buscarPorNome(item.getValue());
                                        
                }
            }
        });
    }
        
    private void popularTreeItem(TreeItem t, List<Documento> documentos){
        for(Documento d: documentos){
            TreeItem item = new TreeItem<>(d.getNome());
            t.getChildren().add(item);
        }
    }
}
