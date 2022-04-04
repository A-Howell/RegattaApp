import classes.*;
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

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreationPageRaceController implements Initializable {

    @FXML
    private ListView<Crew> crewListView;
    @FXML
    private ListView<Crew> selectedCrewListView;
    @FXML
    private ListView<Race> raceListView;

    @FXML
    private Button selectCrewButton;
    @FXML
    private Button removeCrewButton;
    @FXML
    private Button enterButton;

    @FXML
    private ComboBox<BoatType> boatTypeComboBox;
    @FXML
    private ComboBox<Division> divTypeComboBox;
    @FXML
    private ComboBox<Gender> genderComboBox;

    @FXML
    private TextField hourBox;
    @FXML
    private TextField minuteBox;

    private List<Crew> crews;
    private List<Crew> selectedCrews;


    private final CreationPageController parentController;

    public CreationPageRaceController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        this.crews = new ArrayList<>();
//        this.race = new Race(null, null, null, null);
        this.selectedCrews = new ArrayList<>();

        for (Team team : r.getTeams()) {
            this.crews.addAll(FXCollections.observableList(team.getTeamCrews()));
        }

        this.crewListView.setItems(FXCollections.observableList(crews));

        List<BoatType> boatTypeList = Arrays.asList(BoatType.class.getEnumConstants());
        this.boatTypeComboBox.setItems(FXCollections.observableList(boatTypeList));

        List<Division> divTypeList = Arrays.asList(Division.class.getEnumConstants());
        this.divTypeComboBox.setItems(FXCollections.observableList(divTypeList));

        List<Gender> genderList = Arrays.asList(Gender.class.getEnumConstants());
        this.genderComboBox.setItems(FXCollections.observableList(genderList));

        this.parentController.getCreateRaceButton().setDisable(true);
        this.selectCrewButton.setDisable(true);
        this.enterButton.setDisable(true);

        updateRaceList();
    }

    // Button actions

    @FXML
    private void enterButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

//        this.race.setStartTime(LocalTime.of(Integer.parseInt(this.hourBox.getText()),
//                Integer.parseInt(this.minuteBox.getText()), 0));
//        this.race.setGender(this.genderComboBox.getValue());
//        this.race.setBoatType(this.boatTypeComboBox.getValue());
//        this.race.setDivision(this.divTypeComboBox.getValue());

        Race tempRace = new Race(
                LocalTime.of(Integer.parseInt(this.hourBox.getText()),
                        Integer.parseInt(this.minuteBox.getText()), 0),
                this.genderComboBox.getValue(),
                this.divTypeComboBox.getValue(),
                this.boatTypeComboBox.getValue());

//        tempRace.setCrewList(this.race.getCrewList());
        tempRace.setCrewList(this.selectedCrews);


        r.addRace(tempRace);
        stage.setUserData(r);

//        this.race = new Race(null, null, null, null);
//        this.raceListView.setItems(FXCollections.observableList(r.getRaces()));
        updateRaceList();

        this.parentController.checkEnabledButtons(event);
        this.parentController.getCreateRaceButton().setDisable(true);
        infoForButtonCheck();
        this.selectedCrewListView.setItems(FXCollections.observableList(this.selectedCrews));
        for (Race race : r.getRaces()) {
            System.out.println("Race: " + race);
            for (Crew crew : race.getCrewList()) {
                System.out.println("    Crew: " + crew);
                for (CrewMember cm : crew.getCrewMembers()) {
                    System.out.println("        CrewMember: " + cm);
                }
            }
        }
    }

    @FXML
    private void selectCrewButtonAction(ActionEvent event) {
        // TODO check

        Crew crew = this.crewListView.getSelectionModel().getSelectedItem();

        this.selectedCrews.add(crew);
//        this.selectedCrewListView.getItems().add(crew);
        this.selectedCrewListView.setItems(FXCollections.observableList(this.selectedCrews));


        this.crewListView.getSelectionModel().clearSelection(); // FXCollections.observableList(this.race.getCrewList())
        infoForButtonCheck();
    }

    @FXML
    private void removeCrewButtonAction(ActionEvent event) {
        System.out.println(this.selectedCrews);

        Crew selectedCrew = selectedCrewListView.getSelectionModel().getSelectedItem();
        this.selectedCrews.remove(selectedCrew);
//        this.race.getCrewList().remove(selectedCrew);
        System.out.println(this.selectedCrews);

        this.selectedCrewListView.setItems(FXCollections.observableList(this.selectedCrews));
        this.selectedCrewListView.getSelectionModel().clearSelection();
        infoForButtonCheck();
    }

    // Combo list actions

    @FXML
    private void genderAndBoatTypeComboAction(ActionEvent actionEvent) {
        if (isGenderAndBoatTypeSelected()) {
            setRaceGenderAndBoatType();

            Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
            Regatta r = (Regatta) stage.getUserData();

            List<Crew> tempCrews = new ArrayList<>();

            for (Team team : r.getTeams()) {
                for (Crew crew : team.getTeamCrews()) {
                    if (crew.getGender() == this.genderComboBox.getValue()
                            && crew.getBoatType() == this.boatTypeComboBox.getValue()) {
                        tempCrews.add(crew);
                    }
                }
            }

            this.crewListView.setItems(FXCollections.observableList(tempCrews));
        }

        infoForButtonCheck();
    }

    @FXML
    private void divTypeComboAction(ActionEvent event) {
//        this.race.setDivision(this.divTypeComboBox.getValue());
        infoForButtonCheck();
    }

    // Checks

    // TODO Make check for hour and minute validation

    private void infoForButtonCheck() {
        // Disable/Enable enter button if all info entered or not
        this.selectCrewButton.setDisable(this.crewListView.getSelectionModel().isEmpty()
                || this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.divTypeComboBox.getValue() == null
                || this.hourBox.getText().isEmpty()
                || this.minuteBox.getText().isEmpty());

        this.enterButton.setDisable(this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.divTypeComboBox.getValue() == null
                || this.hourBox.getText().isEmpty()
                || this.minuteBox.getText().isEmpty()
                || this.selectedCrewListView.getItems().size() <= 1);

        this.removeCrewButton.setDisable(this.selectedCrewListView.getSelectionModel().isEmpty()
                || this.selectedCrewListView.getItems().isEmpty());
    }

    @FXML
    private void infoForButtonCheckListView(MouseEvent event) {
        // Disable/Enable enter button if all info entered or not
        this.selectCrewButton.setDisable(this.crewListView.getSelectionModel().isEmpty()
                || this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.divTypeComboBox.getValue() == null
                || this.hourBox.getText().isEmpty()
                || this.minuteBox.getText().isEmpty());

        this.enterButton.setDisable(this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.divTypeComboBox.getValue() == null
                || this.hourBox.getText().isEmpty()
                || this.minuteBox.getText().isEmpty()
                || this.selectedCrewListView.getItems().size() <= 1);

        this.removeCrewButton.setDisable(this.selectedCrewListView.getSelectionModel().isEmpty()
                || this.selectedCrewListView.getItems().isEmpty());
    }

    @FXML
    private void infoForButtonCheck(ActionEvent event) {
        // Disable/Enable enter button if all info entered or not
        this.selectCrewButton.setDisable(this.crewListView.getSelectionModel().isEmpty()
                || this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.divTypeComboBox.getValue() == null
                || this.hourBox.getText().isEmpty()
                || this.minuteBox.getText().isEmpty());

        this.enterButton.setDisable(this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.divTypeComboBox.getValue() == null
                || this.hourBox.getText().isEmpty()
                || this.minuteBox.getText().isEmpty()
                || this.selectedCrewListView.getItems().size() <= 1);

        this.removeCrewButton.setDisable(this.selectedCrewListView.getSelectionModel().isEmpty()
                || this.selectedCrewListView.getItems().isEmpty());
    }

    private Boolean isGenderAndBoatTypeSelected() {
        return (this.genderComboBox.getValue() != null)
                && (this.boatTypeComboBox.getValue() != null);
    }

    // Other

    private void updateRaceList() {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        List<Race> raceList = new ArrayList<>(r.getRaces());

        this.raceListView.setItems(FXCollections.observableList(raceList));
    }

    private void setRaceDivType() {
//        this.race.setDivision(this.divTypeComboBox.getValue());
    }

    private void setRaceGenderAndBoatType() {
//        this.race.setGender(this.genderComboBox.getValue());
//        this.race.setBoatType(this.boatTypeComboBox.getValue());
    }
}
