package breakout;

import engine.Actor;
import engine.Sound;
import javafx.scene.image.Image;

public class Bullet extends Actor {
    private int dy;
    Image image;

    public Bullet(){
        image = new Image(getClass().getResource("/breakoutresources/bullet.png").toString(),10,50,true,true);
        dy = -4;
        setImage(image);
    }

    @Override
    public void act(long now) {
        move(0,dy);
    }

    ;

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }



}
