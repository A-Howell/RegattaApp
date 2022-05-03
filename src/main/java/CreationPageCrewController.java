import classes.*;
import enums.BoatType;
import enums.Gender;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreationPageCrewController implements Initializable {

    @FXML
    private ListView<CrewMember> crewMemberListView;
    @FXML
    private ListView<CrewMember> selectedCrewMemberListView;
    @FXML
    private ListView<CrewMember> selectedCoxListView;
    @FXML
    private ListView<Crew> crewListView;

    @FXML
    private Button selectAsRowerButton;
    @FXML
    private Button selectAsCoxButton;
    @FXML
    private Button enterButton;
    @FXML
    private Button removeRower;
    @FXML
    private Button removeCox;


    @FXML
    private ComboBox<Team> teamComboBox;
    @FXML
    private ComboBox<BoatType> boatTypeComboBox;
    @FXML
    private ComboBox<Gender> genderComboBox;

//    private Crew crew;
    private List<CrewMember> allCrewMembersList;
    private ArrayList<CrewMember> selectedCrewMembers;
    private CrewMember selectedCox;

    private CreationPageController parentController;

    public CreationPageCrewController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

//        this.crewMemberListView.setItems(FXCollections.observableList(r.getCrewMembers()));

        this.allCrewMembersList = new ArrayList<>();
        for (Team team : r.getTeams()) {
            this.allCrewMembersList.addAll(team.getTeamMembers());
        }

        this.crewMemberListView.getItems().addAll(this.allCrewMembersList);

        this.teamComboBox.setItems(FXCollections.observableList(r.getTeams()));

        List<BoatType> boatTypeList = Arrays.asList(BoatType.class.getEnumConstants());
        this.boatTypeComboBox.setItems(FXCollections.observableList(boatTypeList));

        List<Gender> genderList = Arrays.asList(Gender.class.getEnumConstants());
        this.genderComboBox.setItems(FXCollections.observableList(genderList));

        this.parentController.getCreateCrewButton().setDisable(true);

//        this.crew = new Crew(null);
        this.selectedCrewMembers = new ArrayList<>();
        this.selectedCox = null;
        updateCrewList();
        infoForButtonCheck();
    }

    // FXML Button actions

    @FXML
    private void enterButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        if (this.selectedCrewMembers.size() == this.boatTypeComboBox.getValue().getMaxCrewSize()) {
//            setCrewTeamGender();
//            this.crew.setBoatType(this.boatTypeComboBox.getValue());


            Crew tempCrew = new Crew(this.boatTypeComboBox.getValue());
            if (tempCrew.getBoatType().getCoxed()) {
                tempCrew.setCox(this.selectedCox);
            }
            tempCrew.setGender(this.genderComboBox.getValue());
            tempCrew.setTeamID(this.teamComboBox.getValue().getTeamID());
//            tempCrew.setCrewMembers(this.selectedCrewMembers);

            r.getTeams()
                    .get(r.getTeams().indexOf(this.teamComboBox.getValue()))
                    .addCrew(tempCrew);

            for (CrewMember cm : selectedCrewMembers) {
                int teamIndex = r.getTeams().indexOf(this.teamComboBox.getValue());
                int crewIndex = r.getTeams().get(teamIndex).getTeamCrews().indexOf(tempCrew);
                r.getTeams().get(teamIndex).getTeamCrews().get(crewIndex).addCrewMember(cm.getCrewMemberID());
            }

            stage.setUserData(r);
            this.selectedCrewMemberListView.getItems().clear();
            this.selectedCrewMembers.clear();
            this.selectedCoxListView.getItems().clear();
            this.selectedCox = null;
            setCrewTeamGender();
        }

        updateCrewList();
        this.parentController.checkEnabledButtons(event);
        this.parentController.getCreateCrewButton().setDisable(true);
        infoForButtonCheck();
    }

    @FXML
    private void selectAsRowerButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        this.selectedCrewMembers.add(this.crewMemberListView.getSelectionModel().getSelectedItem());

        this.selectedCrewMemberListView.setItems(FXCollections.observableList(this.selectedCrewMembers));
        infoForButtonCheck();
    }

    @FXML
    private void selectAsCoxButtonAction(ActionEvent event) {
        if (this.boatTypeComboBox.getValue().getCoxed()) {
//            List<CrewMember> coxList = new ArrayList<>();
//            coxList.add(this.selectedCox);
//            this.selectedCoxListView.setItems(FXCollections.observableList(coxList));
            this.selectedCox = this.crewMemberListView.getSelectionModel().getSelectedItem();
            this.selectedCoxListView.getItems().add(this.selectedCox);
        } else {
            System.out.println("Crew not coxed");
        }
        infoForButtonCheck();
    }

    @FXML
    private void removeRowerButtonAction(ActionEvent event) {
        CrewMember selectedCrewMember = crewMemberListView.getSelectionModel().getSelectedItem();
        this.selectedCrewMembers.remove(selectedCrewMember);
        this.selectedCrewMemberListView.setItems(FXCollections.observableList(this.selectedCrewMembers));
        this.selectedCrewMemberListView.getSelectionModel().clearSelection();
        infoForButtonCheck();
    }

    @FXML
    private void removeCoxButtonAction(ActionEvent event) {
        if (this.selectedCox != null) {
            this.selectedCox = null;
            this.selectedCoxListView.getItems().clear();
            this.selectedCrewMemberListView.getSelectionModel().clearSelection();
        }
        infoForButtonCheck();
    }

    // FXML Combo list actions

    @FXML
    private void teamAndGenderComboAction(ActionEvent event) {
        if (isTeamAndGenderSelected()) {
//            setCrewTeamGender();

            Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
            Regatta r = (Regatta) stage.getUserData();

            List<CrewMember> crewMembers = new ArrayList<>();



            for (CrewMember crewMember : r.getAllCrewMembers()) {
                if (crewMember.getTeamID().equals(this.teamComboBox.getValue().getTeamID())
                    && crewMember.getGender() == this.genderComboBox.getValue()) {
                    crewMembers.add(crewMember);
                }
            }

            this.crewMemberListView.setItems(FXCollections.observableList(crewMembers));
        }

        infoForButtonCheck();
    }

    @FXML
    private void boatTypeComboAction(ActionEvent event) {
//        this.crew.setBoatType(this.boatTypeComboBox.getValue());
        infoForButtonCheck();
    }

    // Checks

    private void infoForButtonCheck() {
        // Disable/Enable enter button if all info entered or not
        // Select as rower button
        this.selectAsRowerButton.setDisable(this.crewMemberListView.getSelectionModel().isEmpty()
                || this.teamComboBox.getValue() == null
                || this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.selectedCrewMemberListView.getItems().size()
                == this.boatTypeComboBox.getValue().getMaxCrewSize());

        // Select as cox button
        this.selectAsCoxButton.setDisable(this.boatTypeComboBox.getValue() == null
                || this.teamComboBox.getValue() == null
                || !this.boatTypeComboBox.getValue().getCoxed()
                || this.crewMemberListView.getSelectionModel().isEmpty()
                || this.selectedCoxListView.getItems().size() == 1);

        // Enter button
        if (this.boatTypeComboBox.getValue() != null) {
            if (this.boatTypeComboBox.getValue().getCoxed()) {
                this.enterButton.setDisable(this.teamComboBox.getValue() == null
                        || this.genderComboBox.getValue() == null
                        || this.boatTypeComboBox.getValue() == null
                        || this.selectedCoxListView.getItems().isEmpty()
                        || this.selectedCrewMemberListView.getItems().size()
                        != this.boatTypeComboBox.getValue().getMaxCrewSize());
            } else if (!this.boatTypeComboBox.getValue().getCoxed()) {
                this.enterButton.setDisable(this.teamComboBox.getValue() == null
                        || this.genderComboBox.getValue() == null
                        || this.boatTypeComboBox.getValue() == null
                        || this.selectedCrewMemberListView.getItems().size()
                        != this.boatTypeComboBox.getValue().getMaxCrewSize());
            }
        } else {
            this.enterButton.setDisable(true);
        }

        // Remove Rower button
        this.removeRower.setDisable(this.selectedCrewMemberListView.getSelectionModel().isEmpty()
                || this.selectedCrewMemberListView.getItems().isEmpty());

        // Remove Cox button
        this.removeCox.setDisable(this.selectedCoxListView.getItems().isEmpty());
    }

    @FXML private void infoForButtonCheck(MouseEvent event) {
        // Disable/Enable enter button if all info entered or not
        // Select as rower button
        this.selectAsRowerButton.setDisable(this.crewMemberListView.getSelectionModel().isEmpty()
                || this.teamComboBox.getValue() == null
                || this.genderComboBox.getValue() == null
                || this.boatTypeComboBox.getValue() == null
                || this.selectedCrewMemberListView.getItems().size()
                == this.boatTypeComboBox.getValue().getMaxCrewSize());

        // Select as cox button
        this.selectAsCoxButton.setDisable(this.boatTypeComboBox.getValue() == null
                || this.teamComboBox.getValue() == null
                || !this.boatTypeComboBox.getValue().getCoxed()
                || this.crewMemberListView.getSelectionModel().isEmpty()
                || this.selectedCoxListView.getItems().size() == 1);

        // Enter button
        if (this.boatTypeComboBox.getValue() != null) {
            if (this.boatTypeComboBox.getValue().getCoxed()) {
                this.enterButton.setDisable(this.teamComboBox.getValue() == null
                        || this.genderComboBox.getValue() == null
                        || this.boatTypeComboBox.getValue() == null
                        || this.selectedCoxListView.getItems().isEmpty()
                        || this.selectedCrewMemberListView.getItems().size()
                        != this.boatTypeComboBox.getValue().getMaxCrewSize());
            } else if (!this.boatTypeComboBox.getValue().getCoxed()) {
                this.enterButton.setDisable(this.teamComboBox.getValue() == null
                        || this.genderComboBox.getValue() == null
                        || this.boatTypeComboBox.getValue() == null
                        || this.selectedCrewMemberListView.getItems().size()
                        != this.boatTypeComboBox.getValue().getMaxCrewSize());
            }
        } else {
            this.enterButton.setDisable(true);
        }

        // Remove Rower button
        this.removeRower.setDisable(this.selectedCrewMemberListView.getSelectionModel().isEmpty()
                || this.selectedCrewMemberListView.getItems().isEmpty());

        // Remove Cox button
        this.removeCox.setDisable(this.selectedCoxListView.getItems().isEmpty());
    }

    // Other

    private void updateCrewList() {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        List<Crew> crewList = new ArrayList<>();

        for (Team team : r.getTeams()) {
            crewList.addAll(team.getTeamCrews());
        }

        this.crewListView.setItems(FXCollections.observableList(crewList));
    }

    private void setCrewTeamGender() {
//        this.crew.setTeam(this.teamComboBox.getValue());
//        this.crew.setGender(this.genderComboBox.getValue());
    }

    private Boolean isTeamAndGenderSelected() {
        return (this.teamComboBox.getValue() != null)
                && (this.genderComboBox.getValue() != null);
    }




}
