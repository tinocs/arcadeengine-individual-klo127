package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {
    int dx;
    public Paddle(){
        Image image = new Image(getClass().getResource("/breakoutresources/paddle.png").toString());
        setImage(image);
        dx = 8;
    }
    @Override
    public void act(long now) {
        BallWorld w = (BallWorld) getWorld();
        ImageView b = w.getB();
        Image bi = w.getBImage();

        if(getWorld().isKeyPressed(KeyCode.LEFT)){
            if(getX()-dx>=0){
                setX(getX() - dx);
            }
            if(b.getX()+dx<=0&&(b.getX()-dx>=(w.getWidth()-bi.getWidth())||getX()+getWidth()/2<=w.getWidth()/2)){

                w.scroll(-dx);

            }

        }
        if(getWorld().isKeyPressed(KeyCode.RIGHT)){
            if(getX()+getWidth()+dx<=w.getWidth()){
                setX(getX() + dx);
            }

            if((b.getX()+dx<=0||getX()+getWidth()/2>=w.getWidth()/2)&&b.getX()-dx>=(w.getWidth()-bi.getWidth())){

                w.scroll(dx);

            }
        }
        if(w.isPaused()){
            w.getBall().setX(getX()+getWidth()/2-w.getBall().getWidth()/2);
            w.getBall().setY(getY()-w.getBall().getHeight());
        }


    }


}
