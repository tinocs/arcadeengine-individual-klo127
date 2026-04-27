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
        BallWorld w = (BallWorld) getWorld();

        if(getWorld().isKeyPressed(KeyCode.LEFT)){
            setX(getX()-8);
        }
        if(getWorld().isKeyPressed(KeyCode.RIGHT)){
            setX(getX()+8);
        }
        if(w.isPaused()){
            w.getBall().setX(getX()+getWidth()/2-w.getBall().getWidth()/2);
            w.getBall().setY(getY()-w.getBall().getHeight());
        }
    }


}
