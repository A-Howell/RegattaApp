import classes.Crew;
import classes.CrewMember;
import classes.Regatta;
import enums.BoatType;
import classes.Team;
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

public class CreationPageCrewController implements Initializable {

    @FXML private ListView<CrewMember> crewMemberListView;
    @FXML private ListView<CrewMember> selectedCrewMemberListView;
    @FXML private ListView<CrewMember> selectedCoxListView;
    @FXML private ListView<Crew> crewListView;

    @FXML private Button selectAsRowerButton;
    @FXML private Button selectAsCoxButton;

    private Crew crew;
//    private List<CrewMember> selectedCrewMembers;

    @FXML private ComboBox<Team> teamComboBox;
    @FXML private ComboBox<BoatType> boatTypeComboBox;

    private CreationPageController parentController;

    public CreationPageCrewController (CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        this.crewMemberListView.setItems(FXCollections.observableList(r.getCrewMembers()));

        this.teamComboBox.setItems(FXCollections.observableList(r.getTeams()));

        List<BoatType> boatTypeList = Arrays.asList(BoatType.class.getEnumConstants());
        this.boatTypeComboBox.setItems(FXCollections.observableList(boatTypeList));


        this.parentController.getCreateCrewButton().setDisable(true);

        this.selectAsRowerButton.setDisable(true);
        this.selectAsCoxButton.setDisable(true);

        this.crew = new Crew(null);
        updateCrewList();
    }

    // FXML Button actions

    @FXML
    private void enterButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        BoatType boatType = this.boatTypeComboBox.getSelectionModel().getSelectedItem();

        if (this.crew.getCrewMembers().size() == crew.getBoatType().getMaxCrewSize()) {
            this.crew.setTeam(this.teamComboBox.getValue());
//            r.getTeams()..teamComboBox.getValue().addCrew(this.crew);
            if (r.getTeams().contains(this.crew.getTeam())) {
                r.getTeams()
                        .get(r.getTeams().indexOf(this.crew.getTeam()))
                            .addCrew(this.crew);
            }
            stage.setUserData(r);
            this.selectedCrewMemberListView.getItems().clear();
            this.selectedCoxListView.getItems().clear();
            this.crew = new Crew(null);
            setCrewTeamBoatType();
        }

        updateCrewList();
        this.parentController.checkEnabledButtons(event);
        this.parentController.getCreateCrewButton().setDisable(true);
    }

    @FXML
    private void selectAsRowerButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();
        CrewMember rower = this.crewMemberListView.getSelectionModel().getSelectedItem();

        setCrewTeamBoatType();
        this.crew.addCrewMember(rower);

        this.selectedCrewMemberListView.setItems(FXCollections.observableList(this.crew.getCrewMembers()));
    }

    @FXML
    private void selectAsCoxButtonAction(ActionEvent event) {
        if (this.crew.getBoatType().getCoxed()) {
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
        }

    }

    @FXML
    private void removeRowerButtonAction(ActionEvent event) {
        CrewMember selectedRower = crewMemberListView.getSelectionModel().getSelectedItem();
        this.crew.removeCrewMember(selectedRower);
        this.selectedCrewMemberListView.setItems(FXCollections.observableList(this.crew.getCrewMembers()));
        this.selectedCrewMemberListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void removeCoxButtonAction(ActionEvent event) {
        if (crew.getCox() != null) {
            crew.setCox(null);
            this.selectedCoxListView.getItems().clear();
            this.selectedCrewMemberListView.getSelectionModel().clearSelection();
        }
    }

    // FXML Combo list actions

    @FXML
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
    }

    @FXML
    private void boatTypeComboAction(ActionEvent event) {
        if (isTeamAndBoatTypeSelected()) {
            setCrewTeamBoatType();
            this.selectAsRowerButton.setDisable(false);
            this.selectAsCoxButton.setDisable(!this.crew.getBoatType().getCoxed());
        }
    }

    // Other

    private void updateCrewList() {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        List<Crew> crewList = new ArrayList<Crew>();

        for (Team team : r.getTeams()) {
            crewList.addAll(team.getTeamCrews());
        }

        this.crewListView.setItems(FXCollections.observableList(crewList));
    }

    private void setCrewTeamBoatType() {
        this.crew.setBoatType(this.boatTypeComboBox.getValue());
        this.crew.setTeam(this.teamComboBox.getValue());
    }

    private Boolean isTeamAndBoatTypeSelected() {
        BoatType selectedBoatType = this.boatTypeComboBox.getValue();
        Team selectedTeam = this.teamComboBox.getValue();
        return (selectedBoatType != null) && (selectedTeam != null);
    }
}
