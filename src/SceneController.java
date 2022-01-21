import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.HashMap;

public class SceneController {
    private HashMap<String, Pane> sceneMap = new HashMap<>();
    private Scene main;

    public SceneController(Scene main) {
        this.main = main;
    }

    protected void addScene(String name, Pane pane){
        sceneMap.put(name, pane);
    }

    protected void removeScene(String name){
        sceneMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot(sceneMap.get(name));
    }

}
