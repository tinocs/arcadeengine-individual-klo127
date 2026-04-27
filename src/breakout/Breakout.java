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
    Scene levels;
    BallWorld world;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Breakout");

        Pane menuRoot = new Pane();
        Button playButton = new Button("Play!");
        playButton.setFont(new Font(20));
        playButton.setPrefWidth(200);
        playButton.setOnAction(event -> {
            stage.setScene(levels);
            world.start();
        });
        Image menuImage = new Image("/breakoutresources/menu.png",700,500,false,true);
        ImageView menuBackground = new ImageView(menuImage);
        playButton.setLayoutX(menuImage.getWidth()/2-120);
        playButton.setLayoutY(menuImage.getHeight()*0.82-10);
        menuRoot.getChildren().addAll(menuBackground,playButton);

        BorderPane levelOneRoot = new BorderPane();
        world = new BallWorld();
        levelOneRoot.setCenter(world);
        levels = new Scene(levelOneRoot);
        stage.setScene(levels);


        Scene mainMenu = new Scene(menuRoot);
        stage.setScene(mainMenu);
        stage.show();
    }
}

