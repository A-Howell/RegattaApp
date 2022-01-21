import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreationPagePersonController implements Initializable {

    @FXML private TextField fNameBox;
    @FXML private TextField lNameBox;
    @FXML private TextField phoneNumBox;

    @FXML private DatePicker birthdayPicker;

    @FXML private Button enterButton;

    @FXML private RadioButton officialRadio;
    @FXML private RadioButton crewMemberRadio;

    @FXML private ListView<Team> teamListView;
    @FXML private ListView<Person> personListView;

    @FXML private CreationPageController parentController;

    public CreationPagePersonController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();
        this.teamListView.setItems(FXCollections.observableList(r.getTeams()));
        this.parentController.getCreatePersonButton().setDisable(true);
    }

    @FXML private void enterButtonAction(ActionEvent event) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();
        if (this.officialRadio.isSelected()) {
            Official o = new Official(
                this.fNameBox.getText(),
                this.lNameBox.getText(),
                this.phoneNumBox.getText(),
                this.birthdayPicker.getValue()
            );
            r.addOfficial(o);
        } else if (this.crewMemberRadio.isSelected()) {
            Team team = this.teamListView.getSelectionModel().getSelectedItem();
            CrewMember c = new CrewMember(
                    this.fNameBox.getText(),
                    this.lNameBox.getText(),
                    this.phoneNumBox.getText(),
                    this.birthdayPicker.getValue(),
                    team
            );
            r.addCrewMember(c);
        }
        this.personListView.setItems(getPeopleList(r));
        this.parentController.checkEnabledButtons(event);
        this.parentController.getCreatePersonButton().setDisable(true);
    }

    private ObservableList<Person> getPeopleList(Regatta r) {
        List<Person> peopleList = new ArrayList<Person>(r.getOfficials());
        peopleList.addAll(r.getCrewMembers());
        return FXCollections.observableList(peopleList);
    }
}