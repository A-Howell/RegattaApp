import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreationPageRegattaController {

    @FXML private TextField nameBox;
    @FXML private DatePicker datePicker;
    @FXML private TextField locationBox;

    @FXML private Button enterButton;

    @FXML private CreationPageController parentController;

    public CreationPageRegattaController (CreationPageController parentController) {
        this.parentController = parentController;
    }

    @FXML private void enterButtonAction(ActionEvent event) {
        disableFields();

        Regatta r = new Regatta(
            nameBox.getText(),
            datePicker.getValue(),
            locationBox.getText()
        );

        Node node = (Node) event.getSource();
        System.out.println(node.getParent().getParent().getParent());
        Stage stage = (Stage) node.getScene().getWindow();

        // Pass regatta object to stage for all scenes to access
        stage.setUserData(r);
        System.out.println(stage.getUserData());

        try {
            parentController.getCreateTeamButton().setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML private void disableFields() {
        nameBox.setDisable(true);
        datePicker.setDisable(true);
        locationBox.setDisable(true);
        enterButton.setDisable(true);
    }
}
