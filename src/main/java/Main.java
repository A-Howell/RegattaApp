import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            Pane root = (Pane) FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(SceneConstants.FRONT_PAGE_XML)));

            Scene scene = new Scene(root,1000,500);
            SceneController sceneController = new SceneController(scene);

            // Adding scenes to SceneMap, used to switch between different pages
            // Front Page
            sceneController.addScene(SceneConstants.FRONT_PAGE_STRING, FXMLLoader.load(getClass()
                    .getResource(SceneConstants.FRONT_PAGE_XML)));

            // Creation Page
            sceneController.addScene(SceneConstants.CREATION_PAGE_STRING, FXMLLoader.load(getClass()
                    .getResource(SceneConstants.CREATION_PAGE_XML)));

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        FlowPane root = new FlowPane();
//        root.setAlignment(javafx.geometry.Pos.CENTER);
//
//        primaryStage.setTitle("Hello World");
//
//        Button button = new Button("Click");
//
//        root.getChildren().addAll(button);
//
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene (String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }
}
