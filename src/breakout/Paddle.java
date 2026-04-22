package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {

    public Paddle(){
        Image image = new Image("/breakoutresources/paddle.png");
        setImage(image);
    }
    @Override
    public void act(long now) {
        if(getWorld().isKeyPressed(KeyCode.LEFT)){
            setX(getX()-6);
        }
        if(getWorld().isKeyPressed(KeyCode.RIGHT)){
            setX(getX()+6);
        }
    }


}
