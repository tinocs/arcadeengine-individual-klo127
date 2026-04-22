package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor {
    private int dx;
    private int dy;
    Image image;

    public Ball(){
        image = new Image("/breakoutresources/ball.png");
        dx = 5;
        dy = 5;
        setImage(image);
    };
    @Override
    public void act(long now) {
        move(dx,dy);
        if(getX()<=0||(getX()+getWidth())>getWorld().getWidth()){
            dx =-dx;
        }
        if(getY()<=0||(getY()+getHeight())>getWorld().getHeight()){
            dy =-dy;
        }
        if(getOneIntersectingObject(Paddle.class)!=null){
            dy = -dy;
        }
        Brick brick = getOneIntersectingObject(Brick.class);
        if(brick!=null){
            if(getX()>=brick.getX()&&getX()<=(brick.getX()+brick.getWidth())){
                dy= -dy;
            }else if(getY()>=brick.getY()&&getY()<=(brick.getY()+brick.getHeight())){
                dx= -dx;
            }else{
                dy= -dy;
                dx = -dx;
            }
            getWorld().remove(brick);
        }
    }
}
