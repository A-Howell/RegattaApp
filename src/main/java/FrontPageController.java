import classes.Regatta;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {

    @FXML
    private Button newRegattaButton;
    @FXML
    private Button loadRegattaButton;
    @FXML
    private Button unlockFileButton;
    @FXML
    private Button continueButton;

    @FXML
    private PasswordField passwordEntry;

    @FXML
    private Label errorText;

    private File loadedZipFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableLoading();

    }

    @FXML
    protected void newRegattaButtonAction() throws IOException {
        Main m = new Main();
        m.changeScene(SceneConstants.CREATION_PAGE_XML);
    }

    @FXML
    protected void loadRegattaButtonAction() {
        Stage s = Main.getStage();
        // Open file picker
        FileChooser fileChooser = new FileChooser();

        // Set extension filter (.zip)
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("ZIP files (*.zip)", "*.zip");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open regatta zip file...");
        this.loadedZipFile = fileChooser.showOpenDialog(s);

        if (this.loadedZipFile != null) {
            this.loadRegattaButton.setDisable(true);
            this.passwordEntry.setDisable(false);
        }
    }

    @FXML
    protected void unlockFileButtonAction() throws IOException {
        // Use zip4j to decrypt and open file, store to variable using jackson to convert to Regatta object
        try {
            ZipFile zipFile = new ZipFile(this.loadedZipFile, this.passwordEntry.getText().toCharArray());
            zipFile.extractAll(System.getProperty("user.dir") + "/tmp");

            File jsonFile = new File(System.getProperty("user.dir") + "/tmp/"
                    + (this.loadedZipFile.getName().substring(0, this.loadedZipFile.getName().length() - 4)) + ".json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

            Regatta r = objectMapper.readValue(jsonFile, Regatta.class);
            r.loadCounters();
            Main.getStage().setUserData(r);
            System.out.println(r.toString());

            if (jsonFile.delete()) {
                System.out.println("Successfully deleted json file");
            } else {
                System.out.println("Error deleting json file");
            }

            this.errorText.setText("");
            this.passwordEntry.setDisable(true);
            this.unlockFileButton.setDisable(true);
            this.continueButton.setDisable(false);
            zipFile.close();

        } catch (ZipException e) {
            switch (e.getType()) {
                case WRONG_PASSWORD:
                    this.errorText.setText("Wrong password");
                    break;
                case UNKNOWN:
                    this.errorText.setText("Unknown error");
                    break;
                default:
                    this.errorText.setText("Error");
                    break;
            }
//            if (e.getType() == Zip)
        }

    }

    @FXML
    protected void continueButtonAction() throws IOException {
        // Move to normal regatta editing screen
        Main m = new Main();
        m.changeScene(SceneConstants.CREATION_PAGE_XML);
    }

    @FXML
    protected void passwordEntryFieldAction() {
        this.unlockFileButton.setDisable(this.passwordEntry.getText().isEmpty());
    }

    private void disableLoading() {
        this.passwordEntry.setDisable(true);
        this.unlockFileButton.setDisable(true);
        this.continueButton.setDisable(true);
        this.continueButton.setDisable(true);
    }
}
