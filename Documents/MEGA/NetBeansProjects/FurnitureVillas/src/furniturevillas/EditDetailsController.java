/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniturevillas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author study
 */
public class EditDetailsController implements Initializable {

    @FXML
    private AnchorPane addNEditAnchor;
    @FXML
    private Pane addNeditPane;
    @FXML
    private Button DoneEditDetailBtn;
    @FXML
    private Button cancelEditBtn;
    @FXML
    private TextField editDurationField;
    @FXML
    private Button doneEditBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doneEditDetailsAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("details.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) DoneEditDetailBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    private void cancelEditAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("details.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) DoneEditDetailBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    private void buttonDoneEditing(ActionEvent event) {
    }
    
}
