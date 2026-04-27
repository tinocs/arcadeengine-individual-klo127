package breakout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Breakout extends Application {


    private static Scene mainMenu;
    private BallWorld world;
    private static Stage s;
    public static Stage getS() {
        return s;
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        s=stage;
        stage.setTitle("Breakout");

        Pane menuRoot = new Pane();
        Button playButton = new Button("Play!");
        playButton.setFont(new Font(20));
        playButton.setPrefWidth(200);
        playButton.setOnAction(event -> {
            if(world!=null){
                world.stop();
            }
            BorderPane levelOneRoot = new BorderPane();
            world = new BallWorld();
            levelOneRoot.setCenter(world);
            Scene levels = new Scene(levelOneRoot);
            stage.setScene(levels);
            world.start();
        });
        Image menuImage = new Image(getClass().getResource("/breakoutresources/menu.png").toString(),700,500,false,true);
        ImageView menuBackground = new ImageView(menuImage);
        playButton.setLayoutX(menuImage.getWidth()/2-120);
        playButton.setLayoutY(menuImage.getHeight()*0.82-10);
        menuRoot.getChildren().addAll(menuBackground,playButton);



        mainMenu = new Scene(menuRoot);
        stage.setScene(mainMenu);
        stage.show();
    }
    public static Scene getMainMenu() {
        return mainMenu;
    }
}

