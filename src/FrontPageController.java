import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FrontPageController {

    @FXML private Button startButton;

//    @FXML private void handleCalculateButtonAction(ActionEvent event) {
//        int x = Integer.parseInt(value1Box.getText());
//        int y = Integer.parseInt(value2Box.getText());
//        int z = x + y;
//        resultBox.setText(Integer.toString(z));
//    }

    @FXML
    protected void startButtonAction() throws IOException {
        Main m = new Main();
        m.changeScene(SceneConstants.CREATION_PAGE_XML);

    }
}
