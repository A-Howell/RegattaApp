import classes.*;
import enums.BoatType;
import enums.Division;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreationPageRaceController implements Initializable {

    @FXML private ListView<Crew> crewListView;
    @FXML private ListView<Crew> selectedCrewListView;
    @FXML private ListView<Race> raceListView;

    @FXML private Button selectBoatButton;
    @FXML private Button enterButton;

    @FXML private ComboBox<BoatType> boatTypeComboBox;
    @FXML private ComboBox<Division> divTypeComboBox;

    private Race race;
    List<Crew> crews;


    private CreationPageController parentController;

    public CreationPageRaceController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        crews = new ArrayList<>();

        for (Team team : r.getTeams()) {
            crews.addAll(FXCollections.observableList(team.getTeamCrews()));
        }

        this.crewListView.setItems(FXCollections.observableList(crews));

        List<BoatType> boatTypeList = Arrays.asList(BoatType.class.getEnumConstants());
        this.boatTypeComboBox.setItems(FXCollections.observableList(boatTypeList));

        List<Division> divTypeList = Arrays.asList(Division.class.getEnumConstants());
        this.divTypeComboBox.setItems(FXCollections.observableList(divTypeList));

        this.parentController.getCreateCrewButton().setDisable(true);

        this.selectBoatButton.setDisable(true);
        this.enterButton.setDisable(true);

        this.race = new Race(1, null, null, null);
        updateRaceList();
    }

    // FXML Button actions

    @FXML
    private void enterButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        BoatType boatType = this.boatTypeComboBox.getSelectionModel().getSelectedItem();

        /*if (this.crew.getCrewMembers().size() == crew.getBoatType().getMaxCrewSize()) {
            this.crew.setTeam(this.teamComboBox.getValue());
            this.teamComboBox.getValue().addCrew(this.crew);
            this.selectedCrewMemberListView.getItems().clear();
            this.selectedCoxListView.getItems().clear();
            this.crew = new Crew(null);
            setCrewTeamBoatType();
        }

        updateCrewList();*/
    }

    @FXML
    private void selectBoatButtonAction(ActionEvent event) {

    }

    @FXML
    private void selectAsCoxButtonAction(ActionEvent event) {
        /*if (this.crew.getBoatType().getCoxed()) {
            Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
            Regatta r = (Regatta) stage.getUserData();

            CrewMember cox = this.crewMemberListView.getSelectionModel().getSelectedItem();

            setCrewTeamBoatType();
            this.crew.setCox(cox);

            List<CrewMember> coxList = new ArrayList<>();
            coxList.add(this.crew.getCox());
            this.selectedCoxListView.setItems(FXCollections.observableList(coxList));
        } else {
            System.out.println("Crew not coxed");
        }*/

    }

    @FXML
    private void removeBoatButtonAction(ActionEvent event) {
        /*CrewMember selectedRower = crewMemberListView.getSelectionModel().getSelectedItem();
        this.crew.removeCrewMember(selectedRower);
        this.selectedCrewMemberListView.setItems(FXCollections.observableList(this.crew.getCrewMembers()));
        this.selectedCrewMemberListView.getSelectionModel().clearSelection();*/
    }

    // FXML Combo list actions

    /*@FXML
    private void teamComboAction(ActionEvent event) {
        setCrewTeamBoatType();
        Team selectedTeam = teamComboBox.getValue();

        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        List<CrewMember> crewMembers = new ArrayList<>();

        for (CrewMember crewMember : r.getCrewMembers()) {
            if (crewMember.getTeam() == selectedTeam) {
                crewMembers.add(crewMember);
            }
        }

        this.crewMemberListView.setItems(FXCollections.observableList(crewMembers));
    }*/

    @FXML
    private void boatTypeComboAction(ActionEvent event) {
        List<Crew> tempCrews = new ArrayList<>();
        for (Crew crew : this.crews) {
            if (crew.getBoatType() == this.boatTypeComboBox.getValue()) {
                tempCrews.add(crew);
            }
        }
        this.crewListView.setItems(FXCollections.observableList(tempCrews));
//        if (isTeamAndBoatTypeSelected()) {
//            setCrewTeamBoatType();
//            this.selectAsRowerButton.setDisable(false);
//            this.selectAsCoxButton.setDisable(!this.crew.getBoatType().getCoxed());
//        }
    }

    // Other

    private void updateRaceList() {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        List<Race> raceList = new ArrayList<>(r.getRaces());

        this.raceListView.setItems(FXCollections.observableList(raceList));
    }

    private void setRaceBoatType() {
        this.race.setBoatType(this.boatTypeComboBox.getValue());
    }

    private void setRaceDivType() {
        this.race.setDivision(this.divTypeComboBox.getValue());
    }

    // TODO setGender
    private void setRaceGenderType() {
        //this.race.setGender(this.genderComboBox.getValue());
    }

   /* private Boolean isTeamAndBoatTypeSelected() {
        BoatType selectedBoatType = this.boatTypeComboBox.getValue();
        Team selectedTeam = this.teamComboBox.getValue();
        return (selectedBoatType != null) && (selectedTeam != null);
    }*/
}
