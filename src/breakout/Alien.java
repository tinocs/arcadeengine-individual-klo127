package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Alien extends Actor {
    public Alien(){
        Image image = new Image(getClass().getResource("/breakoutresources/green-alien.png").toString(),30,50,true,true);
        setImage(image);
    }

    @Override
    public void act(long now) {

    }
}