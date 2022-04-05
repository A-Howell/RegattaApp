import classes.Crew;
import classes.Person;
import classes.Regatta;
import classes.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreationPageController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button createRegattaButton;
    @FXML
    private Button createTeamButton;
    @FXML
    private Button createPersonButton;
    @FXML
    private Button createCrewButton;
    @FXML
    private Button createRaceButton;
    @FXML
    private Button finishRegattaButton;

    @FXML
    private void createRegattaButtonAction(ActionEvent event) {
        createRegattaButton.setDisable(true);
        loadFXMLCPRegatta(getClass().getResource(SceneConstants.CREATION_PAGE_REGATTA_XML));
    }

    @FXML
    private void createTeamButtonAction(ActionEvent event) {
        checkEnabledButtons(event);
        createTeamButton.setDisable(true);
        loadFXMLCPTeam(getClass().getResource(SceneConstants.CREATION_PAGE_TEAM_XML));
    }

    @FXML
    private void createPersonButtonAction(ActionEvent event) {
        checkEnabledButtons(event);
        createPersonButton.setDisable(true);
        loadFXMLCPPerson(getClass().getResource(SceneConstants.CREATION_PAGE_PERSON_XML));
    }

    @FXML
    private void createCrewButtonAction(ActionEvent event) {
        checkEnabledButtons(event);
        createCrewButton.setDisable(true);
        loadFXMLCPCrew(getClass().getResource(SceneConstants.CREATION_PAGE_CREW_XML));
    }

    @FXML
    private void createRaceButtonAction(ActionEvent event) {
        checkEnabledButtons(event);
        createRaceButton.setDisable(true);
        loadFXMLCPRace(getClass().getResource(SceneConstants.CREATION_PAGE_RACE_XML));
    }

    @FXML
    private void finishRegattaButtonAction(ActionEvent event) throws JsonProcessingException {
        checkEnabledButtons(event);
        finishRegattaButton.setDisable(true);
//        loadFXMLCPRace(getClass().getResource(SceneConstants.CREATION_PAGE_RACE_XML));

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonStr = mapper.writeValueAsString(r);
        System.out.println(jsonStr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disableButtons();
    }

    @FXML
    protected void disableButtons() {
        createTeamButton.setDisable(true);
        createPersonButton.setDisable(true);
        createCrewButton.setDisable(true);
        createRaceButton.setDisable(true);
        finishRegattaButton.setDisable(true);
    }

    @FXML
    protected void enableButtons() {
        createTeamButton.setDisable(false);
        createPersonButton.setDisable(false);
        createCrewButton.setDisable(false);
        createRaceButton.setDisable(false);
    }

    private void loadFXMLCPRegatta(URL url) {
        try {
            FXMLLoader l = new FXMLLoader(url);
            CreationPageRegattaController ctrl = new CreationPageRegattaController(this);
            l.setController(ctrl);
            this.borderPane.setCenter(l.load());
            System.out.println("Loaded regatta fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXMLCPPerson(URL url) {
        try {
            FXMLLoader l = new FXMLLoader(url);
            CreationPagePersonController ctrl = new CreationPagePersonController(this);
            l.setController(ctrl);
            this.borderPane.setCenter(l.load());
            System.out.println("Loaded official fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXMLCPTeam(URL url) {
        try {
            FXMLLoader l = new FXMLLoader(url);
            CreationPageTeamController ctrl = new CreationPageTeamController(this);
            l.setController(ctrl);
            this.borderPane.setCenter(l.load());
            System.out.println("Loaded team fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXMLCPCrew(URL url) {
        try {
            FXMLLoader l = new FXMLLoader(url);
            CreationPageCrewController ctrl = new CreationPageCrewController(this);
            l.setController(ctrl);
            this.borderPane.setCenter(l.load());
            System.out.println("Loaded crew fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFXMLCPRace(URL url) {
        try {
            FXMLLoader l = new FXMLLoader(url);
            CreationPageRaceController ctrl = new CreationPageRaceController(this);
            l.setController(ctrl);
            this.borderPane.setCenter(l.load());
            System.out.println("Loaded race fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void checkEnabledButtons(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        // If a team has been created, enable team creation and person creation
        this.createTeamButton.setDisable(r.getTeams().isEmpty());
        this.createPersonButton.setDisable(r.getTeams().isEmpty());

        // If a team with a member exists, enable crew creation
        this.createCrewButton.setDisable(r.getAllCrewMembers().isEmpty());

        List<Crew> crews = new ArrayList<>();
        for (Team team : r.getTeams()) {
            crews.addAll(FXCollections.observableList(team.getTeamCrews()));
        }
        this.createRaceButton.setDisable(crews.isEmpty());

        this.finishRegattaButton.setDisable(r.getRaces().isEmpty());
    }

    // Setters

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setCreateCrewButton(Button createCrewButton) {
        this.createCrewButton = createCrewButton;
    }

    public void setCreatePersonButton(Button createPersonButton) {
        this.createPersonButton = createPersonButton;
    }

    public void setCreateRaceButton(Button createRaceButton) {
        this.createRaceButton = createRaceButton;
    }

    public void setCreateRegattaButton(Button createRegattaButton) {
        this.createRegattaButton = createRegattaButton;
    }

    public void setCreateTeamButton(Button createTeamButton) {
        this.createTeamButton = createTeamButton;
    }

    public void setFinishRegattaButton(Button finishRegattaButton) {
        this.finishRegattaButton = finishRegattaButton;
    }

    // Getters

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Button getCreateCrewButton() {
        return createCrewButton;
    }

    public Button getCreatePersonButton() {
        return createPersonButton;
    }

    public Button getCreateRaceButton() {
        return createRaceButton;
    }

    public Button getCreateRegattaButton() {
        return createRegattaButton;
    }

    public Button getCreateTeamButton() {
        return createTeamButton;
    }

    public Button getFinishRegattaButton() {
        return finishRegattaButton;
    }
}
