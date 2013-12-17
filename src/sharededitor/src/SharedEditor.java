/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import controller.LoggedUser;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Documento;
import model.DocumentoDAO;


public class SharedEditor extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception{
        String logado = LoggedUser.getInstance().getLoggedUser().getLogin();
        List<Documento> docs = DocumentoDAO.buscarPorUsuario(logado);
        for(Documento d: docs){
            Documento novo = Documento.toObj(d.toJSON());
            novo.setEditavel(true);
            DocumentoDAO.atualizarDocumento(novo);
        }
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}