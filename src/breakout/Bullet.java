package breakout;

import engine.Actor;
import engine.Sound;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

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
        Alien alien = getOneIntersectingObject(Alien.class);
        if(alien!=null&&alien.getOpacity()>=1.0){
            FadeTransition fade = new FadeTransition(Duration.millis(150), alien);
            fade.setToValue(0.0);
            fade.setOnFinished(e -> {
                getWorld().remove(alien);
            });
            fade.play();
            SpaceWorld world =(SpaceWorld)getWorld();
            world.getScore().setValue(world.getScore().getValue()+10);
            getWorld().remove(this);


        }
    }

    ;

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }



}
