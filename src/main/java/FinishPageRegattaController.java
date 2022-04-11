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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class FinishPageRegattaController implements Initializable {

    @FXML
    private Button enterButton;

    @FXML
    private PasswordField passwordEntry;

    @FXML
    private Label savedText;

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
        r.saveCounters();
        r.setSaved(true);
        stage.setUserData(r);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonStr = mapper.writeValueAsString(r);

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_HH_mm_ss");
            LocalDateTime now = LocalDateTime.now();

            String fileName = r.getName().replace(" ", "").toLowerCase()
                    + r.getDate().getDayOfMonth()
                    + r.getDate().getMonth().getValue()
                    + r.getDate().getYear()
                    + dtf.format(now);
            FileWriter myWriter = new FileWriter(fileName + ".json");
            myWriter.write(jsonStr);
            myWriter.close();

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setEncryptFiles(true);
            zipParameters.setCompressionLevel(CompressionLevel.MAXIMUM);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);

            ZipFile zipFile = new ZipFile(fileName + ".zip", this.passwordEntry.getText().toCharArray());
            File tempFile = new File(fileName + ".json");

            zipFile.addFile(tempFile, zipParameters);
            if (tempFile.delete()) {
                System.out.println("File successfully deleted after zip encryption");
            } else {
                System.out.println("File deletion failed");
            }
            zipFile.close();
            this.savedText.setText("Password secured zip file containing regatta information stored as "
                + fileName + ".zip");

            this.passwordEntry.setDisable(true);
            this.enterButton.setDisable(true);


        } catch (IOException e) {
            e.printStackTrace();
        }

        this.parentController.checkEnabledButtons(event);
        this.parentController.getFinishRegattaButton().setDisable(true);
    }

}
