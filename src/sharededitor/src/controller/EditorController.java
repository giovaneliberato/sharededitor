package controller;

import com.mongodb.BasicDBObject;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.web.WebView;
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
            doc.setNome(nomeTexto);
            doc.setTexto(editor.getHtmlText());
            doc.setEditavel(false);

            DocumentoDAO.atualizarDocumento(doc);
        } else {
            doc = new Documento();
            doc.setNome(renomear(nomeTexto));
            doc.setOwner(LoggedUser.getInstance().getLoggedUser().getLogin());
            doc.setTexto(editor.getHtmlText());
                
            DocumentoDAO.salvarDocumento(doc);
        }
        
        
        inicializarTreeView();
    }
    
    @FXML
    public void criarNovoDocumento(){
        nome.setText("Novo documento");
        editor.setHtmlText("");
        inicializarTreeView();
        editor.setDisable(false);
        nome.setDisable(false);
    }
    
    @FXML
    public void excluirDocumento(){
        String nometexto = nome.getText();
        DocumentoDAO.removerPorNome(nometexto);
        inicializarTreeView();
        nome.setText("");
        editor.setHtmlText("");
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
        
        DocumentoDAO.atualizarDocumento(d);
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
        editor.setDisable(true);
        nome.setDisable(true);
    }
    
    
    private String renomear(String original){
        int i = 1;
        StringBuilder novoNome = new StringBuilder(original);
        while(DocumentoDAO.buscarPorNome(novoNome.toString()) != null){
            novoNome = new StringBuilder(original);
            novoNome.append(" (").append(i).append(")");
            i += 1;
        } 
        return novoNome.toString();
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
                    Documento paraAbrir = DocumentoDAO.buscarPorNome(item.getValue());
                    String logado = LoggedUser.getInstance().getLoggedUser().getLogin();
                    
                    if(paraAbrir.isEditavel() || item.getValue().equals(nome.getText())){
                        if (!nome.getText().isEmpty()){
                            Documento antigo = DocumentoDAO.buscarPorNome(nome.getText());
                            antigo.setEditavel(true);
                            DocumentoDAO.atualizarDocumento(antigo);
                        }
                        
                        if (podeEditar(paraAbrir, logado)){
                            editor.setDisable(false);
                            nome.setText(paraAbrir.getNome());
                            nome.setDisable(false);
                            editor.setHtmlText(paraAbrir.getTexto());
                            paraAbrir.setEditavel(false);
                            DocumentoDAO.atualizarDocumento(paraAbrir);
                        } else {
                            editor.setDisable(true);
                            nome.setDisable(true);
                            nome.setText(paraAbrir.getNome());
                        }
                        
                        editor.setHtmlText(paraAbrir.getTexto());
                        
                        
                        
                        
                    } else {
                        erroPopUp.setVisible(true);
                    }
                                        
                }
            }

            private boolean podeEditar(Documento d, String login) {
                boolean compartilhado = false;
                for(BasicDBObject obj: d.getCompartilhados()){
                    if(obj.getString("nome").equals(login) && obj.getString("perm").equals("Leitura e escrita")){
                        compartilhado = true;
                        break;
                    }
                }
                return d.getOwner().equals(login) || compartilhado;
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
