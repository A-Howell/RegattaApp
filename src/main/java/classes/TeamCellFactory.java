package classes;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TeamCellFactory implements Callback<ListView<Team>, ListCell<Team>> {
    @Override
    public ListCell<Team> call(ListView<Team> param) {
        return new ListCell<Team>(){
            @Override
            public void updateItem(Team team, boolean empty) {
                super.updateItem(team, empty);
                if (empty || team == null) {
                    setText(null);
                } else {
                    setText(team.getTeamName());
                }
            }
        };
    }
}