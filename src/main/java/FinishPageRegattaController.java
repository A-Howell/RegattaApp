import classes.Crew;
import classes.Race;
import classes.Regatta;
import classes.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.BoatType;
import enums.Division;
import enums.Gender;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FinishPageRegattaController implements Initializable {

    @FXML
    private Button enterButton;

    private final CreationPageController parentController;

    public FinishPageRegattaController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        this.parentController.getFinishRegattaButton().setDisable(true);

    }

    // Button actions

    @FXML
    private void enterButtonAction(ActionEvent event) throws JsonProcessingException {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonStr = mapper.writeValueAsString(r);

        try {
            FileWriter myWriter = new FileWriter(r.getName().replace(" ", "")
                    + r.getDate().getDayOfMonth()
                    + r.getDate().getMonth().toString().toLowerCase()
                    + r.getDate().getYear() + ".json");
            
            myWriter.write(jsonStr);
            myWriter.close();
            System.out.println("Wrote the file");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parentController.checkEnabledButtons(event);
        this.parentController.getFinishRegattaButton().setDisable(true);
    }

}
