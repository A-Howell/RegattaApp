import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreationPageTeamController implements Initializable {
    @FXML private CreationPageController parentController;

    @FXML private TextField teamNameBox;

    @FXML private Button enterButton;

    @FXML private ListView<Team> teamListView;

    public CreationPageTeamController (CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();
        this.teamListView.setItems(FXCollections.observableList(r.getTeams()));
//        this.parentController.getCreateTeamButton().setDisable(true);
    }

    @FXML private void enterButtonAction(ActionEvent event) {
        Team t = new Team(teamNameBox.getText());

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        // Pass regatta object to stage for all scenes to access
        try {
            Regatta r = (Regatta) stage.getUserData();
            r.addTeam(t);
            stage.setUserData(r);

            System.out.println("Populating list");
            this.teamListView.setItems(FXCollections.observableList(r.getTeams()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.teamNameBox.clear();
        this.parentController.checkEnabledButtons(event);
        this.parentController.getCreateTeamButton().setDisable(true);
    }

    /*public ObservableList<String> getTeamList(List<Team> teams) {
        List<String> teamStringList = new ArrayList<String>();
        for (Team team : teams) {
            teamStringList.add(team.getTeamName());
        }
        return FXCollections.observableList(teamStringList);
    }*/

}
