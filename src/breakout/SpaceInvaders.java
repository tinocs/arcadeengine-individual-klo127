package breakout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SpaceInvaders extends Application {


    private static Scene mainMenu;
    private SpaceWorld world;
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
        stage.setTitle("Space Invaders");

        Pane menuRoot = new Pane();
        Button playButton = new Button("Play");
        playButton.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        playButton.setPrefWidth(210);
        playButton.setOnAction(event -> {
            if(world!=null){
                world.stop();
            }
            BorderPane levelOneRoot = new BorderPane();
            world = new SpaceWorld();
            levelOneRoot.setCenter(world);
            Scene levels = new Scene(levelOneRoot);
            stage.setScene(levels);
            world.start();
        });
        Image menuImage = new Image(getClass().getResource("/breakoutresources/menu.png").toString(),700,500,false,true);
        ImageView menuBackground = new ImageView(menuImage);
        playButton.setLayoutX(menuImage.getWidth()/2-playButton.getPrefWidth() /2);
        playButton.setLayoutY(menuImage.getHeight()*0.7);
        menuRoot.getChildren().addAll(menuBackground,playButton);



        mainMenu = new Scene(menuRoot);
        stage.setScene(mainMenu);
        stage.show();
    }
    public static Scene getMainMenu() {
        return mainMenu;
    }
}

