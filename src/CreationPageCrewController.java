import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CreationPageCrewController implements Initializable {
    private CreationPageController parentController;

    public CreationPageCrewController (CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void enterButtonAction(ActionEvent event) {

    }


}
