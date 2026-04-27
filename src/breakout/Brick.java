package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {
    public Brick(){
        Image image = new Image(getClass().getResource("/breakoutresources/brick.png").toString());
        setImage(image);
    }

    @Override
    public void act(long now) {

    }
}
