import classes.Crew;
import classes.CrewMember;
import classes.Race;
import classes.Regatta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class FinishPageRaceTimeEntryController implements Initializable {
    @FXML
    BorderPane borderPane;

    @FXML
    ListView<Race> raceListView;

    @FXML
    Button startRaceButton;


//    private List<Race> raceList;

    private final CreationPageController parentController;

    public FinishPageRaceTimeEntryController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startRaceButton.setDisable(true);
        updateRaceListView();
    }

    // Button actions
    // TODO need start race button
    @FXML
    private void startRaceButtonAction(ActionEvent event) {
        for (Node button : ((GridPane) this.borderPane.getRight()).getChildren()   ) {
            button.setDisable(false);
        }
        this.startRaceButton.setDisable(true);
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        Race race = this.raceListView.getSelectionModel().getSelectedItem();
        r.getRaces().get(r.getRaces().indexOf(race)).setActualRaceStartTime(LocalTime.now());
    }

    // ListView Actions

    @FXML
    private void raceListViewAction(MouseEvent event) {
        updateRaceListView();
        // get selection model at selected race
        // Generate finish buttons for each boat in race
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();

        if (!((GridPane) this.borderPane.getRight()).getChildren().isEmpty()) {
            List<Node> buttonsToRemove = new ArrayList<>(((GridPane) this.borderPane.getRight()).getChildren());
            ((GridPane) this.borderPane.getRight()).getChildren().removeAll(buttonsToRemove);
        }

        if (!this.raceListView.getSelectionModel().isEmpty()) {

            Race race = this.raceListView.getSelectionModel().getSelectedItem();
            this.startRaceButton.setDisable(false);
            System.out.println(race.getCrewList());
            Button temp;
            int i = 1; // Start from row index 1 when adding buttons

            Regatta tempR = (Regatta) stage.getUserData();
            List<Crew> crewsFromIDs = new ArrayList<>();
            for (String crewID : race.getCrewList()) {
                crewsFromIDs.add(tempR.getCrewByID(crewID));
            }

            for (Crew crew : crewsFromIDs) {
                temp = new Button(crew.toString() + " finished");
                Button finalTemp = temp;
                temp.setOnAction(e -> {
                    Regatta r = (Regatta) stage.getUserData();

                    r.getRaces().get(r.getRaces().indexOf(race)).addFinishTime(crew.getCrewID(), LocalTime.now());

                    this.raceListView.getSelectionModel().getSelectedItem()
                            .addFinishTime(crew.getCrewID(), LocalTime.now());

                    System.out.println(LocalTime.now());
                    finalTemp.setDisable(true);
                    if (isRaceFinished()) {
                        r.getRaces().get(r.getRaces().indexOf(race)).setFinished(true);

                        int comparison = 0;
                        int j = 0;
                        LocalTime lowestTime = LocalTime.now();
                        String lowestTimeCrew = "";
                        for (Map.Entry<String,LocalTime> curr :
                                r.getRaces().get(r.getRaces().indexOf(race)).getCrewFinishTimes().entrySet()) {
                            if (j == 0) {
                                lowestTime = curr.getValue();
                                lowestTimeCrew = curr.getKey();
                            } else {
                                comparison = lowestTime.compareTo(curr.getValue());
                                if (comparison > 0) {
                                    System.out.println("Current is quicker");
                                    lowestTime = curr.getValue();
                                    lowestTimeCrew = curr.getKey();
                                } else if (comparison == 0) {
                                    System.out.println("They drew");
                                } else {
                                    System.out.println("Current is slower");
                                }
                            }
                            j++;
                        }
                        r.getRaces().get(r.getRaces().indexOf(race)).setWinnerCrewID(lowestTimeCrew);
                        System.out.println("Winners: " + lowestTimeCrew);
                        updateRaceListView();
                    }
                    stage.setUserData(r);
                });
                temp.setDisable(true);
                ((GridPane) this.borderPane.getRight()).add(temp, 0, i);
                System.out.println("Added button");
                i++;
            }
        }

    }

    private boolean isRaceFinished() {
        return this.raceListView.getSelectionModel().getSelectedItem().getCrewList().size()
                == this.raceListView.getSelectionModel().getSelectedItem().getCrewFinishTimes().size();
    }

    private void updateRaceListView() {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        List<Race> raceList = new ArrayList<>();
        for (Race race : r.getRaces()) {
            if (!race.isFinished()) {
                raceList.add(race);
            }
        }
        this.raceListView.setItems(FXCollections.observableList(raceList));
    }

    // Setters

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setRaceListView(ListView<Race> raceListView) {
        this.raceListView = raceListView;
    }

// Getters

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public ListView<Race> getRaceListView() {
        return raceListView;
    }
}
